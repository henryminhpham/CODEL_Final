package codel.web.managedBean;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import codel.ejb.ContactServiceLocal;
import codel.jpa.Contact;

@ManagedBean(name = "listContacts")
@SessionScoped
public class ListContactsBean {
	@EJB
	private ContactServiceLocal ejb;

	private List<Contact> contacts;

	public List<Contact> getContacts() {
		if (contacts == null)
			contacts = ejb.getAllContacts();
		return contacts;
	}

	public String delContact(Contact c) {
		if (ejb.delContact(c.getId())) {
			contacts.remove(c);
			return "deleteSuccess";
		}
		return "error";
	}
}