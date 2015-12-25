package codel.web.managedBean;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import codel.ejb.ContactServiceLocal;
import codel.ejb.GroupServiceLocal;
import codel.ejb.PhoneNumberServiceLocal;
import codel.jpa.Contact;
import codel.jpa.Group;
import codel.jpa.PhoneNumber;
import codel.web.util.Util;

@ManagedBean(name = "contactPage")
@SessionScoped
public class ContactPageBean {
	@EJB 
	private ContactServiceLocal contactEjb;
	@EJB 
	private PhoneNumberServiceLocal phoneNumberEjb;
	@EJB 
	private GroupServiceLocal groupEjb;
	
	private int contactId;
	private Contact contact;
	private PhoneNumber phoneNumber;
	
	public void ensureInitialized() {
		try{
			if(getContact() != null)
				// Success
				return;
		} catch(EJBException e) {
			e.printStackTrace();
		}
		Util.redirectToRoot();
	}
	
	public List<Group> getAllGroupsContainContact(){
		List<Group> groups = new ArrayList<Group>(this.contact.getBooks());
		return groups;
	}
	
	public boolean removeContactFromGroup(Group group){
		this.contact.getBooks().remove(group);
		return this.groupEjb.removeContactFromGroup(this.contact, group);
	}
	
	public Contact getContact(){
		if(this.contact == null)
			this.contact = contactEjb.getContactById(getContactId());
		return this.contact;
	}
	
	public List<PhoneNumber> getPhoneNumbers(){
		return this.phoneNumberEjb.getAllPhoneNumberOfContact(this.getContact());
	}

	public int getContactId() {
		return this.contactId;
	}

	public void setContactId(int contactId) {
		this.contactId = contactId;
		contact = null;
	}

	public PhoneNumber getPhoneNumber() {
		if(this.phoneNumber==null)
			this.phoneNumber = new PhoneNumber();
		return this.phoneNumber;
	}

	public PhoneNumber addPhoneNumberToContact(){
		this.phoneNumberEjb.addPhoneNumberToContact(this.phoneNumber, this.contact);
		this.phoneNumber = new PhoneNumber();
		return this.phoneNumber;
	}
	
	public boolean delPhoneNumber(PhoneNumber p){
		return phoneNumberEjb.delPhoneNumber(p.getId());
	}
	
	public void setPhoneNumber(PhoneNumber phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
}
