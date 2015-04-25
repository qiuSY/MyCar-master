<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="edu.neu.cs5200.myCar.dao.*, edu.neu.cs5200.myCar.models.*, java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Up</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<style>
	body {
		background-color:#E6E6FF;
		color: #110022;
	}
	.form-signin{
		max-width: 330px;
		padding: 15px;
		margin: 0 auto;
	}
</style>
</head>
<body>
	<%
		UserDao dao = new UserDao();
		String id = request.getParameter("userid");
	
		Integer idInt = Integer.parseInt(id);
		User user = dao.findUser(idInt);
	%>
	<div id="update" class="form-group">
		<form class="form-signin" action="updateProfile">
			<h2>Update profile</h2>
			<div style="color: #FF0000;">${errorMessage}</div>
			<input type="hidden" class="form-control" name="userid" value="<%=user.getUserId()%>"/>
			<label for="email">Email:</label>
			<input type="text" class="form-control" id="email" name="email" placeholder="Reset email">
			<label for="fname">First name:</label>
			<input type="text" class="form-control" id="fname" name="fname" placeholder="Reset first name">
			<label for="lname">Last name:</label>
			<input type="text" class="form-control" id="lname" name="lname" placeholder="Reset last name">
			<br>
			<button class="btn btn-lg btn-primary" name="action" value="update">Update</button>
		</form>
	</div>
	
</body>
</html>