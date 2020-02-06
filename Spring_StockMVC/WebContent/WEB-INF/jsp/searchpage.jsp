 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body align="center">
<h1>WELCOME TO SEARCH ITEM PAGE </h1>
<table align='center' border='1'><tr><th>Product Name</th> <th>Category</th> <th>Company</th> <th>Quantity</th> <th>Price</th> <th>Modify</th> <th>ADDCART</th></tr><br>
<c:forEach var="m" items="${mlist }">
<tr> <td>${m.getProductname()}</td> <td>${m.getCategory()}</td> <td>${m.getCompany()}</td> <td>${m.getQuantity()}</td> <td>${m.getPrice()}</td> <td><a href="modifyprod?id=${ m.getId()}">Modify</a></td> <td><a href="addcart?id=${ m.getId()}">AddCart</a></td><tr>
</c:forEach>
</table>
<a href="addproduct">AddProduct</a>
<a href="fetchproduct">SearchProduct</a>
<a href="logout">Logout</a>
</body>
</html>