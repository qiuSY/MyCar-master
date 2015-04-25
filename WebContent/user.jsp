<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="edu.neu.cs5200.myCar.dao.*, edu.neu.cs5200.myCar.models.*, java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Hello User</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<style>
	body {
		background-color: #E8E8E8;
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
	#sc{
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
		Integer useridInt=Integer.parseInt(userid);
		
		CommentDao commentdao=new CommentDao();
		LikeDao likedao=new LikeDao();
		String action = request.getParameter("action");
		String commentid = request.getParameter("commentid");
		String likeid = request.getParameter("likeid");
		
		if("deletecomment".equals(action)){
			commentdao.removecomment(Integer.parseInt(commentid));
		}
		else if ("deletelike".equals(action)){
			likedao.removelike(Integer.parseInt(likeid));
		}
		User user = dao.findUser(useridInt);
		List<Comment> comments=commentdao.findCommentsbyUser(useridInt);
		List<Like> likes=likedao.findUser(useridInt);
	%>
	<div class="container">
		<h1>Hello User</h1>
		<a id="lo" class="btn btn-info" href="homepage.jsp" >Log out</a>
		<br>
		<br>
		<div id="error" style="color: #FF0000;">${errorMessage}</div>
		<br>
		<button id="up" type="button" class="btn btn-warning" onclick="showConfirm()">Update Profile</button>
		<br>
		<br>
		<a id="sc" class="btn btn-primary" href="makes.jsp?userid=<%=user.getUserId()%>">Search cars</a>
		
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
	<form action="user.jsp">
		<table class="table table-striped">
			<h2>User's Comments</h2>
			<thead>
				<tr>
					<th>StyleId</th>
					<th>Title</th>
					<th>Content</th>
				</tr>
			</thead>
			<tbody>
				<%
				for (Comment comment : comments) {
				%>
				<tr>
					<td><a href="styleDetail.jsp?styleid=<%=comment.getStyleid()%>&userid=<%=useridInt%>"><%=comment.getStyleid()%></a></td>
					<td><%=comment.getTitle()%></td>
					<td><%=comment.getContent()%></td>
					<td><a class="btn btn-danger" href="user.jsp?action=deletecomment&commentid=<%=comment.getId()%>&userid=<%=comment.getUser().getUserId()%>">Delete</a></td>
				</tr>
				<%
				}
				%>
			</tbody>
		</table>
		<table class="table table-striped">
			<h2>User's Likes</h2>
			<thead>
				<tr>
					<th>StyleId</th>
				</tr>
			</thead>
			<tbody>
				<%
				for (Like like : likes) {
				%>
				<tr>
					<td><a href="styleDetail.jsp?styleid=<%=like.getStyleid()%>&userid=<%=useridInt%>"><%=like.getStyleid()%></a></td>
					<td><a class="btn btn-danger" href="user.jsp?action=deletelike&likeid=<%=like.getId()%>&userid=<%=like.getUser().getUserId()%>">Delete</a></td>
				</tr>
			<%
			}
			%>
			</tbody>
		</table>
	</form>
	<form>
		<table class="table table-striped">
			<h2>User's Followers</h2>
			<thead>
				<tr>
					<th>Username:</th>
					<th>Email:</th>
					<th>First Name:</th>
					<th>Last Name</th>
				</tr>
			</thead>
			<tbody>
				<%
				List<Follower> followers = new ArrayList<Follower>();
				followers = user.getFollowers();
				for (int i=0; i<followers.size(); i++) {
				%>
				<tr>
					<td><a href="reportUser.jsp?userid=<%=followers.get(i).getFollower().getUserId()%>&me=<%=userid%>"><%= followers.get(i).getFollower().getUsername()%></a></td>
					<td><%=followers.get(i).getFollower().getEmail()%></td>
					<td><%=followers.get(i).getFollower().getFirstName()%></td>
					<td><%=followers.get(i).getFollower().getLastName()%></td>
				</tr>
			<%
			}
			%>
			</tbody>
		</table>
		<table class="table table-striped">
			<h2>Users I followed</h2>
			<thead>
				<tr>
					<th>Username:</th>
					<th>Email:</th>
					<th>First Name:</th>
					<th>Last Name</th>
				</tr>
			</thead>
			<tbody>
				<%
				List<Follower> follows = new ArrayList<Follower>();
				follows = user.getFollowings();
				for (int i=0; i<follows.size(); i++) {
				%>
				<tr>
					<td><a href="reportUser.jsp?userid=<%=follows.get(i).getFollow().getUserId()%>&me=<%=userid%>"><%= follows.get(i).getFollow().getUsername()%></a></td>
					<td><%=follows.get(i).getFollow().getEmail()%></td>
					<td><%=follows.get(i).getFollow().getFirstName()%></td>
					<td><%=follows.get(i).getFollow().getLastName()%></td>
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