package codel.jpa;

import java.util.Set;

public class User {
	protected int userId;
	protected String username;
	protected String password;
	protected String birthday = "";
	protected boolean gender = true;
	protected String telephone = "";
	protected String email = "";
	protected Set<Contact> contacts = null;

	public User() {
	}

	public User(String username, String password, String birthday,
			boolean gender, String telephone, String email) {
		this.username = username;
		this.password = password;
		this.birthday = birthday;
		this.gender = gender;
		this.telephone = telephone;
		this.email = email;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Set<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(Set<Contact> contacts) {
		this.contacts = contacts;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
