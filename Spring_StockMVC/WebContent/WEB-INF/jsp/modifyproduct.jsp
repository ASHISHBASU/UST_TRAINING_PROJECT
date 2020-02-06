<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body align="center">
	<h1 align="center">Welcome To Modify Page</h1>
	<h4 align="center">
	<table align='center' border='1'><tr><th>Product Name</th> <th>Category</th> <th>Company</th> <th>Quantity</th> <th>Price</th> <th>Modify</th></tr><br>
		<form action="modifydata?id=${m.getId() }" method="post">
		<tr> <td><input type="text" name="productname" placeholder="productname" value="${m.getProductname()}" ></td> <td><input type="text" name="category" placeholder="category" value="${m.getCategory()}"></td> <td> <input type="text" name="company" placeholder="company" value="${m.getCompany()}"></td> <td><input type="text" name="quantity" placeholder="quantity" value="${m.getQuantity()}"></td> <td><input type="text" name="price" placeholder="price" value="${m.getPrice()}"></td> <td><input type="submit" value="Modify"></td><tr>
		 
		</form>
		</table>
	</h4>
	<a href="addproduct">AddProduct</a>
<a href="fetchproduct">SearchProduct</a>
<a href="logout">Logout</a>


</body>
</html>