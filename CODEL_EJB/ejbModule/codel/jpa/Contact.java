package codel.jpa;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REFRESH;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import codel.util.JpaUtil;

@Entity
@Table(name = "Contact")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Discriminator", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("C")
public class Contact implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4223476323928758935L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	protected int id;
	@Column(name = "firstName")
	protected String firstName;
	@Column(name = "lastName")
	protected String lastName;
	@Column(name = "email")
	protected String email;
	@OneToOne(optional = false, cascade = { CascadeType.MERGE,
			CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE })
	@JoinColumn(name = "address", unique = true, nullable = false, updatable = false)
	protected Address adr = new Address();
	@ManyToMany(fetch = FetchType.EAGER, cascade = { MERGE, PERSIST, REFRESH })
	@JoinTable(name = "contactgroups", joinColumns = @JoinColumn(name = "contactId", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "groupId", referencedColumnName = "groupId"))
	protected Set<Group> books = new HashSet<Group>(0);
	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "contact")
	protected Set<PhoneNumber> profiles = new HashSet<PhoneNumber>(0);

	public Contact() {
	}

	public Contact(String firstName, String lastName, String email, Address adr) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.adr = adr;
	}

	public Contact(String firstName, String lastName, String email,
			Address adr, Set<PhoneNumber> profiles) {
		this(firstName, lastName, email, adr);
		this.profiles = profiles;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Address getAdr() {
		return adr;
	}

	public void setAdr(Address adr) {
		this.adr = adr;
	}

	public Set<Group> getBooks() {
		return books;
	}

	public void setBooks(Set<Group> books) {
		this.books = books;
	}

	public Set<PhoneNumber> getProfiles() {
		return profiles;
	}

	public void setProfiles(Set<PhoneNumber> profiles) {
		this.profiles = profiles;
	}

	public String getContactType() {
		if (this instanceof Enterprise) {
			return JpaUtil.CONTACT_TYPE.ENTERPRISE.toString();
		}
		return JpaUtil.CONTACT_TYPE.CONTACT.toString();
	}

	public String getNumberKind(String kindNumber) {
		System.out.println("Test recherche num telephone taille liste "
				+ profiles.size());
		for (PhoneNumber obj : profiles) {
			System.out.println(obj.getPhoneKind());
			if (obj.getPhoneKind().toLowerCase().compareTo(kindNumber) == 0)
				return obj.getPhoneNumber();
		}
		return null;
	}

	public void setNumberKind(String kindNumber, String phoneNumber) {
		if (phoneNumber.compareTo("") != 0) {
			PhoneNumber p = null;
			System.out.println("je dois ajouter " + kindNumber + " "
					+ phoneNumber);
			for (PhoneNumber obj : profiles) {
				System.out.println(obj.getPhoneKind());
				if (obj.getPhoneKind().toLowerCase().compareTo(kindNumber) == 0) {
					p = obj;
				}
			}
			if (p != null) {
				System.out.println("j'ai deja p dans la liste de telephone");
				p.setPhoneNumber(phoneNumber);
			} else {
				System.out.println("j'ai pas le telephone donc je l'ajoute");
				p = new PhoneNumber(kindNumber, phoneNumber);
				if (profiles == null) {
					profiles = new HashSet<PhoneNumber>();
				}
				p.setContact(this);
				profiles.add(p);
			}
		}
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (PhoneNumber obj : profiles) {
			sb.append(obj.getPhoneKind()).append(" : ")
					.append(obj.getPhoneNumber()).append("\n");
		}
		return lastName + " " + firstName + " " + email + " \n " + adr + " \n "
				+ sb;
	}

}
