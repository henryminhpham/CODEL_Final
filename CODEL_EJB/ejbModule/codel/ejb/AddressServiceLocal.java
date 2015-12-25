package codel.ejb;

import java.util.List;

import javax.ejb.EJBException;
import javax.ejb.Local;
import javax.validation.ConstraintViolationException;

import codel.jpa.Address;

@Local
public interface AddressServiceLocal {
	/**
	 * Retrieves all addresses stored in the system.
	 * 
	 * @return All addresses.
	 */
	public List<Address> getAllAddress();

	/**
	 * Create a new address in the database with properties as specified by
	 * <code>address</code>.
	 * 
	 * @param address
	 *            The newly created address has the same property values as this
	 *            parameter.
	 * @return The newly created address.
	 * @throws ConstraintViolationException
	 *             (wrapped in an {@link EJBException})
	 */
	public Address addAddress(Address adr);

	/**
	 * Delete a contact in the database with properties as specified by
	 * <code>adrId</code>
	 * 
	 * @param adrId
	 * @return
	 * @true if the address is successful removed from the database
	 * @false if not
	 * @throws IllegalArgumentException
	 *             If no address exists for the given ID.
	 */
	public boolean delAddress(String adrId);

	/**
	 * Returns the address with the specified ID.
	 * 
	 * @param adrId
	 *            The ID of the address.
	 * @return The address.
	 * @throws IllegalArgumentException
	 *             If no address exists for the given ID.
	 */
	public Address getAddressById(String adrId);

}
