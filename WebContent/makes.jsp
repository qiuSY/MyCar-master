<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="edu.neu.cs5200.myCar.dao.*, edu.neu.cs5200.myCar.models.*, java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Makes List</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<style>
	html, body {
		height: 100%;
	}
	h1 {
		font-family: Arial, sans-serif font-size : 80px;
	}
	.container-full {
		margin: 0 auto;
		width: 100%;
		min-height: 100%;
		background-image: url("http://www.allmacwallpaper.com/get/MacBook-Pro-13-inch-wallpapers/Antelope-Island-Utah-1280x800/1909-5.jpg");
		background-size: cover;
		overflow: hidden;
	}
	.v-center {
		background-color: #FFFFFF;
		margin-left: 100px; 
		margin-right: 100px;
		margin-top: 7%;
		filter:alpha(opacity=50);
		-moz-opacity:0.5;
		-khtml-opacity:0.5;
		opacity:0.7;
		border-radius: 1em; 
	}
	.v-center h1{
		position: relative;
	}
	.borderless tbody tr td, .borderless tbody tr th, .borderless thead tr th {
    	border: none;
	}
	#home{
		float:right;
		margin-right: 10px;
		margin-top: 10px;
	}
</style>
</head>
<body>
	<%
		String userid=request.getParameter("userid");
		Integer useridInt = Integer.parseInt(userid);
		makesDAO client = new makesDAO();
		makesholder makesh = client.findAllMakes();
	%>
	<div class="container-full">
	<a id="home" class="btn btn-info" href="user.jsp?userid=<%=userid %>">Home</a>
		<div class="col-lg-12 text-center v-center">
			<h1>Makes List</h1>
			<table class="table borderless">
				<thread>
					<tr>
						<th>Makes ID:</th>
						<th>Makes Name:</th>
					</tr>
				</thread>
				<tbody>
					<%
					for (makes make : makesh.getMake()) {
					%>
					<tr>
						<td><%=make.getId()%></td>
						<td><a href="models.jsp?makesname=<%=make.getName()%>&userid=<%=useridInt%>"><%=make.getName()%></a></td>
					</tr>
					<%
					}
					%>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>