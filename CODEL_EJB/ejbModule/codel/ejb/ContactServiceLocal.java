package codel.ejb;

import java.util.List;

import javax.ejb.EJBException;
import javax.ejb.Local;
import javax.validation.ConstraintViolationException;

import codel.jpa.Address;
import codel.jpa.Contact;
import codel.jpa.Group;
import codel.jpa.PhoneNumber;

@Local
public interface ContactServiceLocal {
	/**
	 * Retrieves all contacts stored in the system.
	 * @return All contacts. 
	 */
	public List<Contact> getAllContacts();
	/**
	 * Create a new contact in the database with properties as specified by <code>contact</code>.
	 * @param contact The newly created contact has the same property values as this parameter.
	 * @return The newly created contact.
	 * @throws ConstraintViolationException (wrapped in an {@link EJBException})
	 */
	public Contact addContact(Contact contact);
	/**
	 * Delete a contact in the database with properties as specified by <code>contactId</code>
	 * @param contactId
	 * @return 
	 * 	@true if the contact is successful removed from the database 
	 *  @false if not 
	 * @throws IllegalArgumentException If no contact exists for the given ID.
	 */
	public boolean delContact(int contactId);
	/**
	 * Returns the contact with the specified ID.
	 * @param contactId The ID of the contact.
	 * @return The contact.
	 * @throws IllegalArgumentException If no contact exists for the given ID.
	 */
	public Contact getContactById(int contactId);
	public boolean addPhoneNumberToContact(Contact contact, PhoneNumber phoneNumber);
	public boolean removePhoneNumberFromContact(Contact contact, PhoneNumber phoneNumber);
	public boolean checkPhoneNumberExist(Contact contact, PhoneNumber phoneNumber);
	public boolean addAddressToContact(Contact contact, Address address);
	public boolean removeAddressFromContact(Contact contact, Address address);
	public boolean updateContact(Contact oldContact, Contact newContact);
	public boolean checkContactInGroup(Contact contact, Group group);
	public boolean removeContactFromGroup(Contact contact, Group group);
}
