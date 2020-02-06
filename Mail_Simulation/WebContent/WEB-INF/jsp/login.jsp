<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body align="center">
<h1>Welcome to login</h1>
<form  action="logindata" method="post">
  <input type="email" id="email" placeholder="Enter email" name="email"><br>
  <input type="password" id="pwd" placeholder="Enter password" name="password"><br>
  <button type="submit">Submit</button>
</form><br>
<a href="forget">Forget Password</a>
<a href="registration">Register</a><br>
${em }<br>
${msg }
${ms }
</body>
</html>