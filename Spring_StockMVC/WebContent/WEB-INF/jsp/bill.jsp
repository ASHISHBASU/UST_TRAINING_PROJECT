
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body align=center>
<%double sum=0.0; %>
<%double sumgst=0.0; %>

<h1>Generated Bill</h1>
<table align='center' border='1'><tr><th>Product Name</th> <th>Category</th> <th>Company</th> <th>Quantity</th> <th>Per_Price</th> <th>Total_Price</th></tr><br>
<c:forEach var="m" items="${mlist }">

<input type="hidden" value="${sum=sum+m.getAddquantity()*m.getPrice()}">
<tr> <td>${m.getProductname()}</td> <td>${m.getCategory()}</td> <td>${m.getCompany()}</td> <td>${m.getAddquantity()}</td> <td>${m.getPrice()}</td> <td>${m.getAddquantity()*m.getPrice()}</td><tr>
</c:forEach>
</table>

<h3>Total Price:  ${sum }</h3>
<h3>Total Price with GST:  ${sumgst=sum+sum*(18/100) }</h3>
<a href="addproduct">AddProduct</a>
<a href="fetchproduct">SearchProduct</a>
<a href="logout">Logout</a>



</body>
</html>