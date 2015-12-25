package codel.web.managedBean;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import codel.ejb.ContactServiceLocal;
import codel.ejb.GroupServiceLocal;
import codel.jpa.Contact;
import codel.jpa.Group;
import codel.web.util.Util;

@ManagedBean(name = "groupPage")
@SessionScoped
public class GroupPageBean {
	@EJB
	private GroupServiceLocal groupEjb;
	@EJB
	private ContactServiceLocal contactEjb;

	private int groupId;
	private Group group;

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	/**
	 * Service to get the group by given group ID - this ID is passed through
	 * jsf in the <f:metadata> tag.
	 */
	public Group getGroup() {
		if (this.group == null)
			this.group = this.groupEjb.getGroupById(this.getGroupId());
		return this.group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	/**
	 * Service to initialize page content each time calling the page
	 */
	public void ensureInitialized() {
		try {
			if (this.getGroup() != null)
				// Success
				return;
		} catch (EJBException e) {
			e.printStackTrace();
		}
		Util.redirectToRoot();
	}

	/**
	 * Service to get all contact in the group
	 */
	public List<Contact> getContactsInGroup() {
		/**
		 * re-fresh the group attribute by re-getting the group object in the
		 * database to keep updated all modifications made in group before.
		 */
		this.group = this.groupEjb.getGroupById(this.getGroupId());
		return this.groupEjb.getAllContactsInGroup(this.group);
	}

	public boolean removeContactFromGroup(Contact contact) {
		try {
			this.groupEjb.removeContactFromGroup(contact, this.group);
			this.group = this.groupEjb.getGroupById(this.getGroupId());
			/**
			 * "Alternative" solution to refresh list contact in group and
			 * forward it to jsf page: this.group.getContacts().remove(contact)
			 * 
			 * @Problem: org.hibernate.LazyInitializationException: failed to
			 *           lazily initialize a collection contacts - no session or
			 *           session was closed
			 * 
			 * @Explain: The problem is that the scope of the database/JPA
			 *           transaction only contains the service (a stateless
			 *           session bean) and does NOT contain the rest resource
			 *           bean. At the end of transaction, connection is closed
			 *           even if all contacts in contacts list of group haven't
			 *           been loaded yet.
			 * 
			 *           1. Web server dispatches request to JAX-RS service - 2.
			 *           JAX-RS service calls EJB Stateless - 3. Session Bean
			 *           Transaction starts - 4. EJB Stateless Session Bean
			 *           loads data from database (other beans might be
			 *           involved) - 5. EJB Stateless Session Bean returns
			 *           result - 6. Transaction ends - 7. JAX-RS service
			 *           returns result.
			 * 
			 * @Solution: We try to remove the contact in group getting object
			 *            and merging it in the database. After removing the
			 *            contact in group, re-fresh the group attribute by
			 *            re-getting the group object in the database to keep
			 *            updated all modifications made in group.
			 */
			return true;
		} catch (Exception ex) {
			System.out.print(ex.getMessage());
			return false;
		}
	}
}
