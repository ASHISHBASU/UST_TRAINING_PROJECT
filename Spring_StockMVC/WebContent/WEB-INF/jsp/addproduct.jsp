<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body align="center">
<h1>Enter the detail here</h1>
<form action="stockdata" method="post">
PRODUCTNAME:<br> <input type="text" name="productname" placeholder="productname" ><br>
CATEGORY:<br> <input type="text" name="category" placeholder="category" ><br>
COMPANY :<br> <input type="text" name="company" placeholder="company" ><br>
QUANTITY:<br> <input type="text" name="quantity" placeholder="quantity"  value="0"><br>
PRICE:<br> <input type="text" name="price" placeholder="price" value="0"><br>
<input type="submit" value="Submit" >
</form>
<a href="fetchproduct">SearchProduct</a>
<a href="logout">Logout</a>

</body>
</html>