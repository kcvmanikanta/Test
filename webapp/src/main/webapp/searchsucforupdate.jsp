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
<form action="executeupdate" method="post">
<input type="hidden" name ="cid" value="${contact.id}">
 
First Name: <input type="text" name="firstName"  value="${contact.firstName}" title="firstName">
Last Name :<input type="text" name ="lastName" value="${contact.lastName}"> <br>
<c:forEach  var="phone" items="${contact.phones}">
<!-- Selectphone to update:<br> -->

<input type="hidden" name ="pid" value="${phone.id}"> 
  Phone type: 
  <select name="phoneType"  >
  <option value="mobile" >Mobile</option>
  <option value="office">Office</option>
  <option value="home">Home</option>
  </select>
   Phone Number :<input type="text" name ="phoneNumber"  value="${phone.phoneNumber}"><br> 
           
 
</c:forEach> 
<p/> 
<input type="submit" value="update">
</form>   
</c:forEach>



<p><a href='phonebook.jsp'>click here to go Home!</a><b>|</b>
		<!-- <a href='update.jsp'>click here to update contact!</a><b>|</b>
		<a href='search.jsp'>click here to search another contact!</a><b>|</b>
		<a href='logout'>Log Out!</a></p> -->
</body>
</html>