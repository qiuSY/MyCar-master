<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="edu.neu.cs5200.myCar.dao.*, edu.neu.cs5200.myCar.models.*, java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Report User</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<style>
	body {
		background-color:#D8D8D8;
		color: #110022;
	}
	.form-signin{
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
	#home{
		float:right;
	}
	#rp{
		float:right;
	}
	#follow{
		float:right;
	}
	#unfollow{
		float:right;
	}
</style>
</head>
<body>
	<%
		UserDao dao = new UserDao();
		String userid = request.getParameter("userid");
		String me = request.getParameter("me");
		String action = request.getParameter("action");
		Integer useridInt=Integer.parseInt(userid);
		Integer meInt=Integer.parseInt(me);
		
		CommentDao commentdao=new CommentDao();
		LikeDao likedao=new LikeDao();
		
		if("follow".equals(action))
		{
			Follower follower = new Follower();
			dao.createFollowing(meInt, useridInt, follower);
		}
		if("unfollow".equals(action))
		{
			Integer id=dao.Getremoveid(meInt, useridInt);
			dao.removeFollower(id);
		}
		User user = dao.findUser(useridInt);
		List<Comment> comments=commentdao.findCommentsbyUser(useridInt);
		List<Like> likes=likedao.findUser(useridInt);

	%>
	<div class="container">
		<form class="form-signin" action="reportUser.jsp">
			<h1>Username: &nbsp;<%=user.getUsername()%></h1>
			<%
			if(dao.findUser(meInt).getType().equals("user")){
			%>
				<a id="home" class="btn btn-info" href="user.jsp?userid=<%=me %>">Home</a>
			<%
			}else if(dao.findUser(meInt).getType().equals("admin")){
			%>
				<a id="home" class="btn btn-info" href="admin.jsp?userid=<%=me %>">Home</a>
			<%
			}
			%>
			<br>
			<br>
			<a id="rp" class="btn btn-warning" href="report.jsp?userid=<%=user.getUserId()%>&me=<%=me%>">Report</a>
			<br>
			<br>
			<%
			List<Follower> fans = new ArrayList<Follower>();
			fans = dao.findUser(meInt).getFollowings();
			if(fans.size() != 0){
				int find = 0;
				for(int i=0; i<fans.size(); i++){
					if((find == 0) && fans.get(i).getFollow().getUserId()==useridInt){
						find = find + 1;
			%>
					<form class="form-signin" action="reportUser.jsp">
					<input type="hidden" name="userid" value="<%=userid%>" class="form-control" />
					<input type="hidden" name="me" value="<%=me%>" class="form-control" />
					<div id="unfollow" class="form-group">
						<button class="btn btn-danger" name="action" value="unfollow">Unfollow</button>
					</div>
					</form>
					
					<%
					break;
					}
					else{
						continue;
					}
				}
				if(find == 0){
				%>
					<form class="form-signin" action="reportUser.jsp">
					<input type="hidden" name="userid" value="<%=userid%>" class="form-control" />
					<input type="hidden" name="me" value="<%=me%>" class="form-control" />
					<div id="follow" class="form-group">
						<button class="btn btn-success" name="action" value="follow">Follow</button>
					</div>
					</form>
					<%
				}
			}else{%>
				<form class="form-signin" action="reportUser.jsp">
				<input type="hidden" name="userid" value="<%=userid%>" class="form-control" />
				<input type="hidden" name="me" value="<%=me%>" class="form-control" />
				<div id="follow" class="form-group">
					<button class="btn btn-success" name="action" value="follow">Follow</button>
				</div>
				</form>
			<%
			}
			%>
			<ul>
				<li>UserId: &nbsp;<%=user.getUserId() %></li>
				<li>Username: &nbsp;<%=user.getUsername() %></li>
				<li>Email: &nbsp;<%=user.getEmail() %></li>
				<li>First Name: &nbsp;<%=user.getFirstName() %></li>
				<li>Last Name: &nbsp;<%=user.getLastName()%></li>
				<li>User Type: &nbsp;<%=user.getType()%></li>
			</ul>
		</form>
		<form class="form-signin">
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
					<td><a href="styleDetailUser.jsp?styleid=<%=comment.getStyleid()%>&userid=<%=useridInt%>&me=<%=me%>"><%=comment.getStyleid()%></a></td>
					<td><%=comment.getTitle()%></td>
					<td><%=comment.getContent()%></td>
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
					<td><a href="styleDetailUser.jsp?styleid=<%=like.getStyleid()%>&userid=<%=useridInt%>&me=<%=me%>"><%=like.getStyleid()%></a></td>
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
					<td><a href="reportUser.jsp?userid=<%=follows.get(i).getFollower().getUserId()%>&me=<%=userid%>"><%= follows.get(i).getFollow().getUsername()%></a></td>
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
</body>
</html>