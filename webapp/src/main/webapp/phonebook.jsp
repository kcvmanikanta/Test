<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
Hai : ${sessionScope.user.userName}
<h1>Phone book</h1>
<p>
<form action="contactdetails.jsp">
<button type="submit" >create contact</button>
</form>
<form action="search.jsp">
<button type="submit" >search contact</button>
</form>
<form action="update.jsp">
<button type="submit" >update contact</button>
</form>
<form action="addphone.jsp">
<button type="submit" >add phone number</button>
</form>
<form action="displaycontact" method="post">
<button type="submit" >Contact list</button>
</form>
<form action="delete.jsp">
<button type="submit" >delete contact</button>
</form>
<br><a href='logout'>Log Out!</a>

</body>
</html>