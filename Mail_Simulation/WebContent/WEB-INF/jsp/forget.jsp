<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body align="center">
<h1>Welcome to ForgetPassword Page</h1>
<form action="forgetdata" method="post">
<input type="text" name="email" placeholder="Enter Email"><br>
<input type="text" name="fb" placeholder="Enter Favourite book"><br>
<input type="password" name="newpass" placeholder="Enter New Password" ><br>
<input type="submit" value="Submit" >
</form>
${msg }

</body>
</html>