<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<form action="executeupdate" method="post">
<p>

      <input type="hidden" name ="cid" value="${requestScope.cid}">
     <input type="hidden" name ="pid" value="${requestScope.pid }">
   First Name: <input type="text" name="firstName"  value="${requestScope.firstName }" title="firstName">
   Last Name :<input type="text" name ="lastName" value="${requestScope.lastName }">
   <br>
   Phone type: 
  <select name="phoneType"  >
  <option value="mobile" >Mobile</option>
  <option value="office">Office</option>
  <option value="home">Home</option>
  </select>
  
   Phone Number :<input type="text" name ="phoneNumber" value="${requestScope.phoneNumber }"><br>        
   <input type="submit" value="update">               
</p>
</form>
</body>
</html>