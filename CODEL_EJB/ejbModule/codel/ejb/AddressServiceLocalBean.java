package codel.ejb;

import codel.jpa.Address;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class AddressServiceLocalBean
 */
@Stateless
@Local(AddressServiceLocal.class)
@LocalBean
public class AddressServiceLocalBean implements AddressServiceLocal {

    /**
     * Default constructor. 
     */
    public AddressServiceLocalBean() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see AddressServiceLocal#getAddressById(String)
     */
    public Address getAddressById(String adrId) {
        // TODO Auto-generated method stub
			return null;
    }

	/**
     * @see AddressServiceLocal#addAddress(Address)
     */
    public Address addAddress(Address adr) {
        // TODO Auto-generated method stub
			return null;
    }

	/**
     * @see AddressServiceLocal#getAllAddress()
     */
    public List<Address> getAllAddress() {
        // TODO Auto-generated method stub
			return null;
    }

	/**
     * @see AddressServiceLocal#delAddress(String)
     */
    public boolean delAddress(String adrId) {
        // TODO Auto-generated method stub
			return false;
    }

}
