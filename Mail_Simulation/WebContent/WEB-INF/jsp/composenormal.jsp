<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body align="center">
<h1>Welcome to compose page</h1>

	<h1 align="center">Mail Page</h1>
	<h4 align="center">
		<form action="composedetail" method="post">
			TO: <br> <input style="width: 300px;" type="text" name="tomail"
				placeholder="tomail"  ><br> Subject: <br> <input
				style="width: 300px;" type="text" name="subject"
				placeholder="Subject" ><br> Text: <br> <input
				style="width: 300px;height: 200px;" type="text" name="text"
				placeholder="text" ><br> <input type="submit"
				value="Submit">
		</form>
	</h4>


</body>
</html>