package codel.web.managedBean;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import codel.ejb.ContactServiceLocalBean;
import codel.jpa.Contact;
import codel.jpa.Enterprise;
import codel.web.util.Util;

@ManagedBean(name = "createEnterprise")
@SessionScoped
public class CreateEnterprise {
	private Enterprise enterprise;
	private Enterprise lastEnterprise;
	private String errorMessage;

	private boolean batch;

	@EJB
	ContactServiceLocalBean contactServiceLocalBean;

	public Enterprise getEnterprise() {
		if (enterprise == null)
			enterprise = new Enterprise();
		return enterprise;
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
			lastEnterprise = (Enterprise) contactServiceLocalBean.addContact(getEnterprise());
			enterprise = null;
			errorMessage = null;
		} catch (EJBException e) {
			errorMessage = "" + Util.getConstraintMessage(e);
		}

		// Navigation
		if (isBatch() || errorMessage != null) {
			return null;
		} else
			return "listContacts";
	}

	public String getLastResult() {
		if (lastEnterprise != null) {
			return "Contact created: " + lastEnterprise.toString();
		}
		return errorMessage != null ? errorMessage : "";
	}

	public String getSuccess() {
		return errorMessage != null ? "error" : "success";
	}
}
