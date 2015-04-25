<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="edu.neu.cs5200.myCar.dao.*, edu.neu.cs5200.myCar.models.*, java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Insert title here</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
	String me = request.getParameter("me");
	
	Integer idInt = Integer.parseInt(id);
	User user = dao.findUser(idInt);
	%>
	<div class="container">
		<form class="form-signin" action="reportControl">
			<h2>Report a user</h2>
			<div style="color: #FF0000;">${errorMessage}</div>
			<input type="hidden" class="form-control" name="userid" value="<%=user.getUserId()%>"/>
			<input type="hidden" class="form-control" name="me" value="<%=me%>"/>
			<div class="form-group">
				<label for="reason">Report reason:</label>
      			<textarea class="form-control" rows="5" id="reason" name="reason"></textarea>
			</div>
			<br>
			<button type="submit" class="btn btn-primary" name="action" value="report">Submit</button>
		</form>
	</div>
</body>
</html>