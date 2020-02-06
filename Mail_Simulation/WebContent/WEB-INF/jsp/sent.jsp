<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body align="center">
<h1>WELCOME TO SENT </h1>
<table align='center' border='1'><tr><th>From mail</th> <th>Subject</th> <th>Message</th> <th>To Mail</th> <th>Delete_Mail</th></tr><br>
<c:forEach var="m" items="${mlist }">
<tr> <td>${m.getFrommail()}</td> <td>${ m.getSubject()}</td> <td><a href="message?id=${ m.getMid()}">show_mail</a></td> <td>${ m.getTomail()}</td> <td><a href="delete?id=${ m.getMid()}">Delete_mail</a></td><tr>
</c:forEach>
</table><br>
<a href="inbox">Inbox</a>
<a href="sent">Sent</a>
<a href="composenormal">Compose</a>
<a href="logout">Logout</a>
<a href="deleteitem">DeleteItems</a>

</body>
</html>