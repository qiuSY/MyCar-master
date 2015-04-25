<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Homepage</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<style>
	html, body {
		height: 100%;
	}
	h1 {
		font-family: Arial, sans-serif font-size : 80px;
		color: #DDCCEE;
	}
	.lead {
		color: #DDCCEE;
	}
	.container-full {
		margin: 0 auto;
		width: 100%;
		min-height: 100%;
		background-image:url("http://www.allmacwallpaper.com/get/MacBook-Pro-13-inch-wallpapers/Red-sports-car-1280x800/3958-5.jpg");
		background-size: 1200px 700px;
		background-repeat: no-repeat;
		overflow: hidden;
	}
	.v-center {
		margin-top: 7%;
	}
</style>
</head>
<body>
	<div class="container-full">
		<div class="row">
			<div class="col-lg-12 text-center v-center">
				<h1>Welcome to MyCar</h1>
				<p class="lead">Search for special car styles!</p>
				<br>
				<br>
				<br>
				<a class="btn btn-primary" href="register.jsp">register</a>
				<a class="btn btn-success" href="login.jsp">login</a>
			</div>
		</div>
	</div>
</body>
</html>