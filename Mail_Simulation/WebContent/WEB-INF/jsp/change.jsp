<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body align="center">
<h1>Welcome to ChangePassword Page</h1>
<form action="changedata" method="post">
<input type="text" name="email" placeholder="Enter Email"><br>
<input type="password" name="oldpassword" placeholder="Enter Old Password"><br>
<input type="password" name="newpassword" placeholder="Enter New Password" ><br>
<input type="submit" value="Submit" >
</form><br>
${msg }

</body>
</html>