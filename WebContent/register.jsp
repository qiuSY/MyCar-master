<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="edu.neu.cs5200.myCar.dao.*, edu.neu.cs5200.myCar.models.*, java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<title>Register here</title>
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
	<div class="container">
		<form class="form-signin" action="Register">
			<h2>Sign up</h2>
			<div class="form-group">
				<label for="username">Username:</label>
				<input type="text" class="form-control" id="username" name="username" placeholder="Enter username">
			</div>
			<div class="form-group">
				<label for="pwd">Password:</label>
				<input type="password" class="form-control" id="pwd" name="password" placeholder="Enter password">
			</div>
			<div class="form-group">
				<label for="email">Email:</label>
				<input type="text" class="form-control" id="email" name="email" placeholder="Enter email">
			</div>
			<div class="form-group">
				<label for="fname">First name:</label>
				<input type="text" class="form-control" id="fname" name="fname" placeholder="Enter first name">
			</div>
			<div class="form-group">
				<label for="lname">Last name:</label>
				<input type="text" class="form-control" id="lname" name="lname" placeholder="Enter last name">
			</div>
			<div class="form-group">
				<label for="cType">Choose register type:</label><br/>
				<input type="radio" name="type" value="user" checked>&nbsp;Regular user<br/>
				<input type="radio" name="type" value="admin">&nbsp;Administrator
			</div>	
			
			<button type="submit" class="btn btn-lg btn-primary btn-block" name="action" value="create">Submit</button>
		</form>
	</div>
</body>
</html>