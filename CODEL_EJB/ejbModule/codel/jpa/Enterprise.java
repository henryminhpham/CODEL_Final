package codel.jpa;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("E")
public class Enterprise extends Contact {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1376455719732952693L;
	@Column(name="numSiret")
	protected Integer numSiret;
	
	public Enterprise(){}
	
	public Enterprise(Contact c){
		this.setAdr(c.getAdr());
		this.setEmail(c.getEmail());
		this.setFirstName(c.getFirstName());
		this.setLastName(c.getLastName());
		this.setId(c.getId());
		this.setProfiles(c.getProfiles());
		this.setBooks(c.getBooks());
	}
	
	public int getNumSiret(){
		if(this.numSiret == null)
			this.numSiret = new Integer(0);
		return numSiret.intValue();
	}
	
	public void setNumSiret(int siret){
		numSiret = new Integer(siret);
	}
}