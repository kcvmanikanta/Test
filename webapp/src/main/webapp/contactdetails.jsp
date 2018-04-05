<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Save contact</title>
</head>
<body  >
<h1>Enter Contact details</h1>

<form action="createcontact" method="post">
<p><!--  Id: <input type="text" name="id"> -->
   First Name: <input type="text" name="firstName"  title="firstName">
   Last Name :<input type="text" name ="lastName">
   <br>
   Phone type: 
  <select name="phoneType"  >
  <option value="mobile" >Mobile</option>
  <option value="office">Office</option>
  <option value="home">Home</option>
  </select>
   Phone Number :<input type="text" name ="phoneNumber"><br>        
   <input type="submit" value="Save">               
</p>

</form>
</body>
</html>