<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body align="center">

<c:forEach var="m" items="${mlist }">
<h5>From:</h5><br>
${m.getFrommail()}<br>
<h5>Subject:</h5><br>
${ m.getSubject()}<br>
<h5>Message:</h5><br>
${ m.getText()}

</c:forEach><br>
<a href="inbox">Inbox</a>
<a href="sent">Sent</a>
<a href="composenormal">Compose</a>
<a href="logout">Logout</a>
<a href="deleteitem">DeleteItems</a>


</body>
</html>