package com.hibernate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PhonebookService {

	
    private PhoneBook1 phonebook;

	public PhoneBook1 getPhonebook() {
		return phonebook;
	}
	@Autowired
	public void setPhonebook(PhoneBook1 phonebook) {
		this.phonebook = phonebook;
	}
	@Transactional
	public  boolean createAccount(User user)
	{
		return this.phonebook.createAccount(user);
	}
	@Transactional
	public  boolean login(User user)
	{
		return this.phonebook.login(user);
		
	}
	@Transactional
	public  boolean createContact(User user,Contact1 contact,Phone1 phone) 
	{
		return this.phonebook.createContact(user, contact, phone);
	}
	@Transactional
	public boolean updateContact(String cid, String pid, Contact1 contact,Phone1 phone)
	{
		return this.phonebook.updateContact(cid, pid, contact, phone);
		
	}
	@Transactional
	public List<Contact1> searchContact(String contactName,User user) 
	{
		return this.phonebook.searchContact(contactName, user);
	}
	@Transactional
	public boolean addPhoneNumberToExistingContact(String Id, Phone1 phone)
	{
		return this.addPhoneNumberToExistingContact(Id, phone);
	}
	@Transactional
	public boolean deleteContact(String contactId) 
	{
		return this.deleteContact(contactId);
	}
	@Transactional
	public List<Contact1> displayContact(User user)
	{
		return this.displayContact(user);
	}
	
	
}
