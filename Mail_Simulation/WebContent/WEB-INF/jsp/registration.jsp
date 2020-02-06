<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body align="center">
<h1>Registration Page</h1>
<form action="registrationdata" method="post">
<input type="text" name="username" placeholder="username"><br>
<input type="text" name="email" placeholder="email" ><br>
<input type="text" name="fb" placeholder="favourite book" ><br>
<input type="password" name="password" placeholder="password" ><br>
<input type="submit" value="Register" >
</form><br>
<a href="login">Login</a><br>
${msg }

</body>
</html>