package codel.ejb;

import codel.jpa.Address;
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
 * Session Bean implementation class ContactServiceLocalBean
 */
@Stateless
@Local(ContactServiceLocal.class)
@LocalBean
public class ContactServiceLocalBean implements ContactServiceLocal {

	@PersistenceContext
	private EntityManager em;

	/**
	 * Default constructor.
	 */
	public ContactServiceLocalBean() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see ContactServiceLocal#getAllContacts()
	 */
	public List<Contact> getAllContacts() {
		// TODO Auto-generated method stub
		return em.createQuery("FROM Contact", Contact.class).getResultList();
	}

	/**
	 * @see ContactServiceLocal#getContactById(String)
	 */
	public Contact getContactById(int contactId) {
		Contact c = em.find(Contact.class, contactId);
		return c;
	}

	/**
	 * @see ContactServiceLocal#delContact(String)
	 */
	public boolean delContact(int contactId) {
		Contact c = em.find(Contact.class, contactId);
		if (c != null) {
			em.remove(c);
			return true;
		}
		return false;
	}

	/**
	 * @see ContactServiceLocal#addContact(Contact)
	 */
	public Contact addContact(Contact contact) {
		// TODO Auto-generated method stub
		em.persist(contact);
		return contact;
	}

	@Override
	public boolean addPhoneNumberToContact(Contact contact,
			PhoneNumber phoneNumber) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removePhoneNumberFromContact(Contact contact,
			PhoneNumber phoneNumber) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkPhoneNumberExist(Contact contact,
			PhoneNumber phoneNumber) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAddressToContact(Contact contact, Address address) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAddressFromContact(Contact contact, Address address) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateContact(Contact oldContact, Contact newContact) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkContactInGroup(Contact contact, Group group) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeContactFromGroup(Contact contact, Group group) {
		// TODO Auto-generated method stub
		return false;
	}

}
