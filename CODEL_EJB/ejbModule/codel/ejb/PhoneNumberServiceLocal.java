package codel.ejb;

import java.util.List;

import javax.ejb.EJBException;
import javax.ejb.Local;
import javax.validation.ConstraintViolationException;

import codel.jpa.Contact;
import codel.jpa.PhoneNumber;

@Local
public interface PhoneNumberServiceLocal {
	/**
	 * Retrieves all phone number stored in the system.
	 * 
	 * @return All phone numbers.
	 */
	public List<PhoneNumber> getAllPhoneNumber();
	
	/**
	 * Retrieves all phone number stored in the system of given contact.
	 * 
	 * @return All phone numbers of given contact.
	 */
	public List<PhoneNumber> getAllPhoneNumberOfContact(Contact contact);

	/**
	 * Create a new phone number in the database with properties as specified by
	 * <code>phoneNumber</code>.
	 * 
	 * @param phoneNumber
	 *            The newly created phoneNumber has the same property values as this
	 *            parameter.
	 * @return The newly created phoneNumber.
	 * @throws ConstraintViolationException
	 *             (wrapped in an {@link EJBException})
	 */
	public PhoneNumber addPhoneNumber(PhoneNumber phoneNumber);
	
	/**
	 * 
	 * @param phoneNumber
	 * @param contact
	 * @return
	 */
	public PhoneNumber addPhoneNumberToContact(PhoneNumber phoneNumber, Contact contact);

	/**
	 * Delete a phone number in the database with properties as specified by
	 * <code>phoneNumberId</code>
	 * 
	 * @param phoneNumberId
	 * @return
	 * 	- true if the the phone number is successful removed from the database
	 * 	- false if not
	 * @throws IllegalArgumentException
	 *             If no phone number exists for the given ID.
	 */
	public boolean delPhoneNumber(int phoneNumberId);

	/**
	 * Returns the phone number with the specified ID.
	 * 
	 * @param phoneNumberId
	 *            The ID of the phone number in the database.
	 * @return The phone number.
	 * @throws IllegalArgumentException
	 *             If the given ID is not valid.
	 */
	public PhoneNumber getPhoneNumberByNumber(String number);
}
