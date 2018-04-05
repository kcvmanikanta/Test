package com.hibernate;

import java.util.ArrayList;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.SelectBeforeUpdate;

@Entity
@Table(name="phone")
@SelectBeforeUpdate(true)
public class Phone1 {
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	@Column(name="phoneid")
	private Integer id;
@Column(name="phonetype")	
private String phoneType;


@Column(name="phonenumber")
private String phoneNumber;
@ManyToOne//(cascade=CascadeType.ALL)
private Contact1 contact;



public Contact1 getContact() {
	return contact;
}
public void setContact(Contact1 contact) {
	this.contact = contact;
}
public String getPhoneType() {
	return phoneType;
}
public void setPhoneType(String phoneType) {
	this.phoneType = phoneType;
}
public String getPhoneNumber() {
	return phoneNumber;
}
public void setPhoneNumber(String phoneNumber) {
	this.phoneNumber = phoneNumber;
}
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
@Override
public String toString() {
	
	return phoneType+"  "+phoneNumber;
}



}
