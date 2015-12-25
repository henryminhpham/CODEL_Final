package codel.ejb;

import codel.jpa.Contact;
import codel.jpa.Group;
import codel.jpa.PhoneNumber;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Session Bean implementation class GroupServiceLocalBean
 */
@Stateless
@Local(GroupServiceLocal.class)
@LocalBean
public class GroupServiceLocalBean implements GroupServiceLocal {

	@PersistenceContext
	private EntityManager em;

	/**
	 * Default constructor.
	 */
	public GroupServiceLocalBean() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see GroupServiceLocal#removeContactFromGroup(Contact, Group)
	 */
	public boolean removeContactFromGroup(Contact c, Group g) {
		try {
			Contact tContact = em.find(Contact.class, c.getId());
			Group tGroup = em.find(Group.class, g.getId());
			tContact.getBooks().remove(tGroup);
			tGroup.getContacts().remove(tContact);
			em.merge(tContact);
			em.merge(tGroup);
		} catch (Exception ex) {
			return true;
		}
		return false;
	}

	/**
	 * @see GroupServiceLocal#getContactById(String)
	 */
	public Group getGroupById(int groupId) {
		Group g = em.find(Group.class, groupId);
		return g;
	}

	/**
	 * @see GroupServiceLocal#getAllGroups()
	 */
	public List<Group> getAllGroups() {
		return em.createQuery("FROM Group", Group.class).getResultList();
	}

	/**
	 * @see GroupServiceLocal#addContactToGroup(Contact, Group)
	 */
	public boolean addContactToGroup(Contact c, Group g) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @see GroupServiceLocal#delGroup(String)
	 */
	public boolean delGroup(int groupId) {
		Group g = em.find(Group.class, groupId);
		if (g != null) {
			em.remove(g);
			return true;
		}
		return false;
	}

	/**
	 * @see GroupServiceLocal#addGroup(Group)
	 */
	public Group addGroup(Group group) {
		em.persist(group);
		return group;
	}

	@Override
	public List<Contact> getAllContactsInGroup(Group group) {
		return em
				.createQuery("SELECT c FROM Group g JOIN g.contacts c WHERE g = :group",
						Contact.class).setParameter("group", group)
				.getResultList();
	}

}
