package com.hibernate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.SelectBeforeUpdate;

@Entity
@SelectBeforeUpdate(true)
@Table(name = "contact")
public class Contact1 {
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	@Column(name="contactid")
	private Integer id;
	@Column(name = "firstname")
    private String firstName;
	@Column(name = "lastname")
	private String lastName;
	@ManyToOne
	
	private User user1;
	
	public User getUser() {
		return user1;
	}

	public void setUser(User user1) {
		this.user1 = user1;
	}

	
    
	@OneToMany(fetch=FetchType.EAGER, mappedBy = "contact",cascade = CascadeType.ALL,orphanRemoval= true)
	private List<Phone1> phones = new ArrayList<Phone1>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public List<Phone1> getPhones() {
		return phones;
	}

	public void setPhones(List<Phone1> phones) {
		this.phones = phones;
	}

	@Override
	public String toString() {
		
		return  id +"  "+ firstName +"  "+lastName+"  "+phones  ;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
