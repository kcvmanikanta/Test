package com.hibernate;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.SelectBeforeUpdate;
@Entity
@SelectBeforeUpdate(true)
@Table(name="user1")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	private String userName;
	private String passWord;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user1", cascade = CascadeType.ALL)
	private List<Contact1> contacts =new ArrayList<Contact1>();

	

	public List<Contact1> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact1> contacts) {
		this.contacts = contacts;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

}
