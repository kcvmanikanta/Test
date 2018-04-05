 package com.hibernate;

import java.util.ArrayList;



import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PhoneBook1 {
//	private HibernateTemplate template;
	@Autowired
	public SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
public  boolean createAccount(User user)
{
	System.out.println(sessionFactory+"");
	Session session =sessionFactory.openSession();
	session.beginTransaction();
	
	Query query=session.createQuery("from User c where c.userName='" + user.getUserName()+ "' and c.passWord='" + user.getPassWord() + "' ");
	List<User> newList = new ArrayList<User>();
	newList =query.list();
	if(newList.isEmpty()==true)
	{
		session.saveOrUpdate(user);
		session.getTransaction().commit();
		session.close();
	   return true;
	}
	else
	{
	   
		session.getTransaction().commit();
		session.close();
	return false;
	}
}
	


public  boolean login(User user)
{
	String userName=user.getUserName();
	String passWord=user.getPassWord();
	Session session = sessionFactory.openSession();
	session.beginTransaction();
	
	Query query=session.createQuery("from User c where c.userName='" + userName+ "' and c.passWord='" + passWord + "' ");
	List<User> newList = new ArrayList<User>();
	newList =query.list();
	if(newList.isEmpty()==true)
	{
		
		session.getTransaction().commit();
		session.close();
	   return false;
	}
	else
	{
		session.getTransaction().commit();
		session.close();
	return true;
	}
}
	public  boolean createContact(User user,Contact1 contact,Phone1 phone) {
		
		String userName=user.getUserName();
		String passWord=user.getPassWord();
		contact.getPhones().add(phone);
		phone.setContact(contact);
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Query query=session.createQuery("from User c where c.userName='" + userName+ "' and c.passWord='" + passWord + "' ");
		List<User> newList = new ArrayList<User>();
		newList =query.list();
		String q = "from Contact1 C where C.firstName='" + contact.getFirstName() + "'and C.lastName='" + contact.getLastName() + "'and C.user1="+ newList.get(0).getId()+"";
		Query query1 = session.createQuery(q);
		List<Contact1> newList1 = new ArrayList<Contact1>();
		newList1 = query1.list();
	
	
		if(newList1.isEmpty()==true)	
		{ 
			contact.setUser(newList.get(0));
		    newList.get(0).getContacts().add(contact);
			session.getTransaction().commit();
			session.close();
		   return true;
		}
		else
		{
			session.getTransaction().commit();
			session.close();
		return false;
		
		}
		
		
		
	}

	public boolean updateContact(String cid, String pid, Contact1 contact,Phone1 phone) {
		Integer cid1 = Integer.parseInt(cid);
		Integer pid1 = Integer.parseInt(pid);
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		 Contact1 c1 = (Contact1) session.get(Contact1.class, cid1);
		 Phone1 p1=(Phone1)session.get(Phone1.class, pid1);
//		if (c1 == null)
//		{
//			session.getTransaction().commit();
//			session.close();
//			return false;
//		}
//		
//		else {
			System.out.println(contact.getFirstName());
			 c1.setFirstName(contact.getFirstName());
		       c1.setLastName(contact.getLastName());
		       p1.setPhoneType(phone.getPhoneType());
		       p1.setPhoneNumber(phone.getPhoneNumber());	       
			session.update(c1);
			session.update(p1);
			session.getTransaction().commit();
			session.close();
			return true;
//			//System.out.println("Contact updated");
//		}
		
	}

	public List<Contact1> searchContact(String contactName,User user) {
		String userName=user.getUserName();
		String passWord=user.getPassWord();
		List<Contact1> newList = new ArrayList<Contact1>();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query1=session.createQuery("from User c where c.userName='" + userName+ "' and c.passWord='" + passWord + "' ");
		List<User> newList1 = new ArrayList<User>();
		newList1 =query1.list();
		System.out.println( newList1.get(0).getId());
		String q = "from Contact1 C where C.firstName='" + contactName + "'and C.user1="+ newList1.get(0).getId()+" OR C.lastName='" + contactName + "'and C.user1="+ newList1.get(0).getId()+"";
		Query query = session.createQuery(q);
		newList = query.list();
	
		if (newList.isEmpty() == false)
		{
			session.getTransaction().commit();
		    session.close();
			return newList;
		}
		else 
		{
			
			System.out.print("ContactName " + contactName + " Not found");
			session.getTransaction().commit();
			session.close();
			return newList;
		}
	}

	public boolean addPhoneNumberToExistingContact(String Id, Phone1 phone) {
		Integer contactId = Integer.parseInt(Id);
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Contact1 c1 = (Contact1) session.get(Contact1.class, contactId);
		if (c1 == null) {
			System.out.println("Contact with Id :" + contactId + " not found");
		session.getTransaction().commit();
		session.close();
		return false;
		}
		else {
			phone.setContact(c1);
		c1.getPhones().add(phone);
		session.getTransaction().commit();
		session.close();
		return true;
		}
	}

	public boolean deleteContact(String contactId) {
		Integer id = Integer.parseInt(contactId);
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Contact1 c1 = (Contact1) session.get(Contact1.class, id);
		System.out.println(""+id + c1);
		c1.setUser(null);
		  

			session.delete(c1);
			session.detach(c1);
			
			session.getTransaction().commit();
			session.close();
			return true;

	}
	public List<Contact1> displayContact(User user) {
		String userName=user.getUserName();
		String passWord=user.getPassWord();
		List<Contact1> newList = new ArrayList<Contact1>();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query1=session.createQuery("from User c where c.userName='" + userName+ "' and c.passWord='" + passWord + "' ");
		List<User> newList1 = new ArrayList<User>();
		newList1 =query1.list();
		String q = "from Contact1 C where  C.user1="+ newList1.get(0).getId()+" ";
		Query query = session.createQuery(q);
		newList = query.list();
		
			
			
			session.getTransaction().commit();
			session.close();
			return newList;
		
	}


}
