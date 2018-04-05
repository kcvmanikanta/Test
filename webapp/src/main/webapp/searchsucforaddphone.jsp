<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<p>
<c:forEach  var="contact" items="${contactList}">
<form action="addphone1.jsp" method="post">
<input type="hidden" name ="cid" value="${contact.id}">
First Name: ${contact.firstName}<br>
Last Name :${contact.lastName} <br>
<c:forEach  var="phone" items="${contact.phones}">
<input type="hidden" name ="pid" value="${phone.id}">
PhoneType:   ${phone.phoneType}<br>
PhoneNumber:  ${phone.phoneNumber}<br>       
</c:forEach> 
<input type="submit" value="select contact"> 
</form>
<p/>    
</c:forEach>
</p>


<p><a href='phonebook.jsp'>click here to go Home!</a><b>|</b>
		<!-- <a href='update.jsp'>click here to update contact!</a><b>|</b>
		<a href='search.jsp'>click here to search another contact!</a><b>|</b>
		<a href='logout'>Log Out!</a></p> -->
</body>
</html>