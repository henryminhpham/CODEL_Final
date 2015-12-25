package codel.web.managedBean;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import codel.ejb.GroupServiceLocal;
import codel.jpa.Group;

@ManagedBean(name = "listGroups")
@SessionScoped
public class ListGroupsBean {
	@EJB
	private GroupServiceLocal groupEjb;

	private Group group;

	public void setGroup(Group g) {
		this.group = g;
	}

	public Group getGroup() {
		if (this.group == null)
			this.group = new Group();
		return this.group;
	}

	public Group createGroup() {
		groupEjb.addGroup(this.group);
		this.group = new Group();
		return this.group;
	}
	
	public List<Group> getAllGroups(){
		return groupEjb.getAllGroups(); 
	}
	
	public boolean delGroup(Group group){
		return groupEjb.delGroup(group.getId());
	}
}
