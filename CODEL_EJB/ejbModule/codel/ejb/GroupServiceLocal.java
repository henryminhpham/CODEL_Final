package codel.ejb;

import java.util.List;

import javax.ejb.EJBException;
import javax.ejb.Local;
import javax.validation.ConstraintViolationException;

import codel.jpa.Contact;
import codel.jpa.Group;

@Local
public interface GroupServiceLocal {
	/**
	 * Retrieves all groups stored in the system.
	 * 
	 * @return All groups.
	 */
	public List<Group> getAllGroups();

	/**
	 * Create a new group in the database with properties as specified by
	 * <code>group</code>.
	 * 
	 * @param group
	 *            The newly created group has the same property values as this
	 *            parameter.
	 * @return The newly created group.
	 * @throws ConstraintViolationException
	 *             (wrapped in an {@link EJBException})
	 */
	public Group addGroup(Group group);

	/**
	 * Delete a contact in the database with properties as specified by
	 * <code>groupId</code>
	 * 
	 * @param groupId
	 * @return - true if the group is successful removed from the database -
	 *         false if not
	 * @throws IllegalArgumentException
	 *             If no group exists for the given ID.
	 */
	public boolean delGroup(int groupId);

	/**
	 * Returns the group with the specified ID.
	 * 
	 * @param goupId
	 *            The ID of the contact.
	 * @return The contact.
	 * @throws IllegalArgumentException
	 *             If no group exists for the given ID.
	 */
	public Group getGroupById(int groupId);

	/**
	 * Add a contact to a group given in the database
	 * 
	 * @param c
	 *            the contact object to be added to group
	 * @param g
	 *            the group object
	 * @return - true if the contact is successful added to group - false if not
	 * @throws IllegalArgumentException
	 *             If the group or contact given is not valid.
	 */
	public boolean addContactToGroup(Contact c, Group g);
	
	/**
	 * Get all contacts in the given group
	 * @return
	 * 		the list of all contacts contained in the given group
	 */
	public List<Contact> getAllContactsInGroup(Group group);

	/**
	 * Remove a contact from a group in the database
	 * 
	 * @param c
	 *            the contact object to be removed
	 * @param g
	 *            the group object
	 * @return - true if the contact is successful removed from group - false if
	 *         not
	 * @throws IllegalArgumentException
	 *             If the group or contact given is not valid.
	 */
	public boolean removeContactFromGroup(Contact c, Group g);
}
