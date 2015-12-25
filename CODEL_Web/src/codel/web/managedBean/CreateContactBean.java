package codel.web.managedBean;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import codel.ejb.ContactServiceLocalBean;
import codel.jpa.Contact;
import codel.web.util.Util;

@ManagedBean(name = "createContact")
@SessionScoped
public class CreateContactBean {
	private Contact contact;
	private Contact lastContact;
	private String errorMessage;

	private boolean batch;

	@EJB
	ContactServiceLocalBean contactServiceLocalBean;

	public Contact getContact() {
		if (contact == null)
			contact = new Contact();
		return contact;
	}
	
	public boolean isBatch() {
		return batch;
	}

	public void setBatch(boolean batch) {
		this.batch = batch;
	}

	public String persist() {
		// Action
		try {
			lastContact = contactServiceLocalBean.addContact(getContact());
			contact = null;
			errorMessage = null;
		} catch (EJBException e) {
			errorMessage = "" + Util.getConstraintMessage(e);
		}
		
		// Navigation
		if(isBatch() || errorMessage != null){
			return null;
		}
		else 
			return "listContacts";
	}
	
	public String getLastResult() {
		if (lastContact != null) {
			return "Contact created: " + lastContact.toString();
		}
		return errorMessage != null ? errorMessage : "";
	}

	public String getSuccess() {
		return errorMessage != null ? "error" : "success";
	}
}
