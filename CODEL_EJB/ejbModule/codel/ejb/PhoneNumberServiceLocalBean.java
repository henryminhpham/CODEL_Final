/**
 * 
 */
package codel.ejb;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import codel.jpa.Contact;
import codel.jpa.PhoneNumber;

/**
 * @author MinhHieu
 *
 */
@Stateless
@Local(PhoneNumberServiceLocal.class)
@LocalBean
public class PhoneNumberServiceLocalBean implements PhoneNumberServiceLocal {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<PhoneNumber> getAllPhoneNumber() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PhoneNumber> getAllPhoneNumberOfContact(Contact contact) {
		return em
				.createQuery("FROM PhoneNumber l WHERE l.contact = :contact",
						PhoneNumber.class).setParameter("contact", contact)
				.getResultList();
	}

	@Override
	public PhoneNumber addPhoneNumber(PhoneNumber phoneNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delPhoneNumber(int phoneNumberId) {
		PhoneNumber p = em.find(PhoneNumber.class, phoneNumberId);
		if (p != null) {
			em.remove(p);
			return true;
		}
		return false;
	}

	@Override
	public PhoneNumber getPhoneNumberByNumber(String number) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PhoneNumber addPhoneNumberToContact(PhoneNumber phoneNumber,
			Contact contact) {
		Contact c = em.find(Contact.class, contact.getId());
		phoneNumber.setContact(c);
		em.persist(phoneNumber);
		return phoneNumber;
	}

}
