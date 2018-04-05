  package com.pbookapp;

import java.io.IOException;

import java.util.ArrayList;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.hibernate.Contact1;
import com.hibernate.Phone1;
import com.hibernate.PhoneBook1;

import com.hibernate.User;

@Controller
@SessionAttributes("user")
public class MyApp {
	

 
	

	public PhoneBook1 pb;
    @Autowired
	public void setPb(PhoneBook1 pb) {
		this.pb = pb;
	}

	@RequestMapping(value = "/createaccount")
	public ModelAndView createaccount(@ModelAttribute("user") User user) {

		boolean result = this.pb.createAccount(user);
		if (result == true) {
			return new ModelAndView("acctcreatesuccess", "userName", user.getUserName());
		} else {
			return new ModelAndView("acctcreatefail", "userName", user.getUserName());

		}
	}

	@ModelAttribute("user")
	public User setUpUserForm() {
		return new User();
	}

	@RequestMapping(value = "/login")
	public ModelAndView login(@ModelAttribute("user") User user, HttpServletRequest request) {

		boolean result = pb.login(user);
		if (result == true) {
			return new ModelAndView("phonebook", "userName", user.getUserName());
		} else {
			return new ModelAndView("loginfailure", "userName", user.getUserName());

		}

	}

	@RequestMapping(value = "/createcontact")
	public ModelAndView createContact(@ModelAttribute("contact") Contact1 contact,
			@ModelAttribute("phone") Phone1 phone, HttpServletRequest request) {

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		System.out.println(user.getUserName() + phone.getPhoneType());

		boolean result = pb.createContact(user, contact, phone);
		if (result == true) {
			return new ModelAndView("contactcreatesuccess", "firstName", contact.getFirstName());
		} else {
			return new ModelAndView("contactcreatefail", "userName", user.getUserName());

		}

	}

	@RequestMapping(value = "/searchcontact")
	public ModelAndView searchContact(HttpServletRequest request) {

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		String contactName = request.getParameter("name");

		List<Contact1> contactList = new ArrayList<Contact1>();
		contactList = pb.searchContact(contactName, user);
		if (contactList.isEmpty() == true) {
			return new ModelAndView("searchfail", "contactName", contactName);
		} else {

			ModelAndView mv = new ModelAndView("searchsuccess");
			mv.addObject("contactList", contactList);
			return mv;
		}

	}

	@RequestMapping(value = "/updatecontact")
	public ModelAndView updateContact(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		String contactName = request.getParameter("name");
		List<Contact1> contactList = new ArrayList<Contact1>();
		contactList = pb.searchContact(contactName, user);
		if (contactList.isEmpty() == true) {
			return new ModelAndView("searchfail", "contactName", contactName);
		} else {
			
			for (int j = 0; j < contactList.size(); j++) {
				Contact1 c = contactList.get(j);

				request.setAttribute("firstName", c.getFirstName());
				request.setAttribute("lastName", c.getLastName());
				request.setAttribute("cid", c.getId());

				for (int i = 0; i < c.getPhones().size(); i++) {
					request.setAttribute("phoneType", c.getPhones().get(i).getPhoneType());
					request.setAttribute("phoneNumber", c.getPhones().get(i).getPhoneNumber());
					request.setAttribute("pid", c.getPhones().get(i).getId());

					request.getRequestDispatcher("edit.jsp").include(request, response);
				}

			}
			return null;
		}

	}

	@RequestMapping(value = "/executeupdate")
	public ModelAndView executeUpdate(HttpServletRequest request) {

		String cid = request.getParameter("cid");

		String pid = request.getParameter("pid");
		System.out.println(pid);
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String phoneType = request.getParameter("phoneType");
		String phoneNumber = request.getParameter("phoneNumber");
		Contact1 c1 = new Contact1();
		Phone1 p1 = new Phone1();
		c1.setFirstName(firstName);
		c1.setLastName(lastName);
		p1.setPhoneNumber(phoneNumber);
		p1.setPhoneType(phoneType);
		p1.setContact(c1);
		c1.getPhones().add(p1);
		boolean result = pb.updateContact(cid, pid, c1, p1);
		if (result == true) {
			return new ModelAndView("updatesuccess");

		}
		return null;

	}

	@RequestMapping(value = "/addphonetocontact")
	public ModelAndView addPhone(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		String contactName = request.getParameter("name");

		List<Contact1> contactList = new ArrayList<Contact1>();
		contactList = pb.searchContact(contactName, user);
		if (contactList.isEmpty() == true) {
			return new ModelAndView("searchfail", "contactName", contactName);
		} else {
			ModelAndView mv = new ModelAndView("searchsucforaddphone");
			mv.addObject("contactList", contactList);
			return mv;

		}

	}

	@RequestMapping(value = "/addphonexecute")
	public ModelAndView executeAddPhone(@ModelAttribute("phone") Phone1 phone, HttpServletRequest request) {

		String cid = request.getParameter("contactid");

		boolean result = pb.addPhoneNumberToExistingContact(cid, phone);
		if (result == true) {
			return new ModelAndView("addphonesuccess");

		}
		return null;

	}

	@RequestMapping(value = "/deletecontact")
	public ModelAndView deleteContact(HttpServletRequest request) {

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		String contactName = request.getParameter("name");
		List<Contact1> contactList = new ArrayList<Contact1>();
		contactList = pb.searchContact(contactName, user);
		if (contactList.isEmpty() == true) {
			return new ModelAndView("searchfail", "contactName", contactName);
		} else {
			ModelAndView mv = new ModelAndView("searchsucfordelete");
			mv.addObject("contactList", contactList);
			return mv;

		}

	}

	@RequestMapping(value = "/executedelete")
	public ModelAndView executeDelete(HttpServletRequest request, HttpServletResponse response) {

		String cid = request.getParameter("cid");

		boolean result = pb.deleteContact(cid);
		if (result == true) {
			return new ModelAndView("deletesuccess");

		}
		return null;

	}

	@RequestMapping(value = "/displaycontact")
	public ModelAndView displayContact(HttpServletRequest request) {

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		List<Contact1> contactList = new ArrayList<Contact1>();
		contactList = pb.displayContact(user);
		if (contactList.isEmpty() == true) {
			return new ModelAndView("displayfail", "name", user.getUserName());
		} else {

			ModelAndView mv = new ModelAndView("displaysuccess");
			mv.addObject("contactList", contactList);
			return mv;
		}

	}

	@RequestMapping("/logout")
	public ModelAndView logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();

		return new ModelAndView("logoutsuccess");

	}
}
