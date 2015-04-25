<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="edu.neu.cs5200.myCar.dao.*, edu.neu.cs5200.myCar.models.*, java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Hello Admin</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<style>
	body {
		background-color: #C8C8C8;
		color: #110022;
	}
	.form-signin{
		max-width: 330px;
		padding: 15px;
		margin: 0 auto;
	}
	.sr-only{
		position: absolute;
		width: 1px;
  		height: 1px;
  		padding: 0;
  		margin: -1px;
  		overflow: hidden;
  		clip: rect(0,0,0,0);
 	 	border: 0;
	}
	#up{
		float:right;
	}
	#lo{
		float:right;
	}
	#error{
		float:right;
	}
</style>
</head>
<body>
	<%
		UserDao dao = new UserDao();
		String userid = request.getParameter("userid");
		User user = dao.findUser(Integer.parseInt(userid));
		
		ReportDao reportdao = new ReportDao();
		String action = request.getParameter("action");
		String reportid = request.getParameter("reportid");
		if("delete".equals(action))
	    {
			Integer reportidInt = Integer.parseInt(reportid);
	    	dao.removeUser(reportdao.findReport(reportidInt).getUser().getUserId());
	    	//reportdao.removeReport(reportidInt);
	    }
		List<Report> reports = reportdao.findAllReports();
	%>
	<div class="container">
		<h1>Hello Admin</h1>
		<a id="lo" class="btn btn-info" href="homepage.jsp" >Log out</a>
		<br>
		<br>
		<div id="error" style="color: #FF0000;">${errorMessage}</div>
		<br>
		<button id="up" type="button" class="btn btn-warning" onclick="showConfirm()">Update Profile</button>
		<ul>
				<li>UserId: &nbsp;<%=user.getUserId() %></li>
				<li>Username: &nbsp;<%=user.getUsername() %></li>
				<li>Email: &nbsp;<%=user.getEmail() %></li>
				<li>First Name: &nbsp;<%=user.getFirstName() %></li>
				<li>Last Name: &nbsp;<%=user.getLastName()%></li>
				<li>User Type: &nbsp;<%=user.getType()%></li>
		</ul>
		<form class="form-signin" action="userControl">
			<div id="confirm" style="display:none;" class="form-group">
				<input type="hidden" class="form-control" name="userid" value="<%=user.getUserId()%>"/>
				<label for="username">Username:</label>
				<input type="text" class="form-control" id="username" name="username" placeholder="Enter username">
				<label for="pwd">Password:</label>
				<input type="password" class="form-control" id="pwd" name="password" placeholder="Enter password">
				<button class="btn btn-primary" name="action" value="submit">confirm</button>
			</div>
				
		</form>
		<script type="text/javascript"> 
			function showConfirm(){
				document.getElementById("confirm").style.display="block";
			}
		</script>
	<div>
		<form action="admin.jsp">
		<table class="table table-striped">
			<thead>
				<tr>
					<th>ReportId</th>
					<th>Reason</th>
					<th>UserId</th>
					<th>Username</th>
				</tr>
			</thead>
			<tbody>
				<%
				for(Report report : reports){
				%>
					<tr>
						<td><%=report.getReportId() %></td>
						<td><%=report.getReason() %></td>
						<td><%=report.getUser().getUserId() %></td>
						<td><a href="reportUser.jsp?userid=<%=report.getUser().getUserId()%>&me=<%=userid%>"><%=report.getUser().getUsername() %></a></td>
						<td><a class="btn btn-danger" href="admin.jsp?action=delete&reportid=<%=report.getReportId() %>&userid=<%=user.getUserId()%>">Delete</a></td>
					</tr>
				<%
				}
				%>
			</tbody>
		</table>
		</form>
	</div>
	</div>
</body>
</html>