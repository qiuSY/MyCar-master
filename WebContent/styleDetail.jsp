<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="edu.neu.cs5200.myCar.dao.*, edu.neu.cs5200.myCar.models.*, java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Style</title>
<script src="//code.jquery.com/jquery-1.11.2.min.js"></script>
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
		background-image: url("http://www.allmacwallpaper.com/get/MacBook-Pro-13-inch-wallpapers/Ocean-skyline-1280x800/2897-5.jpg");
		background-size: cover;
		overflow: hidden;
	}
	.v-center { 
		margin: 0 auto;
		width: 90%;
		border-radius: 1em; 
	}
	.v-center h1{
		position: relative;
	}
	.cm{
		filter:alpha(opacity=50);
		-moz-opacity:0.5;
		-khtml-opacity:0.5;
		opacity:0.8;
		border-radius: 1em; 
	}
	#home{
		float:right;
	}
	#like{
		float:right;
	}
	#unlike{
		float:right;
	}
	#photo{
		border-radius: 1em; 
	}
	.borderless tbody tr td, .borderless tbody tr th, .borderless thead tr th {
    	border: none;
	}
</style>
</head>
<body>
	<div class="container-full">
	<div class="col-lg-12 v-center">
		<h1>Styles Detail</h1>
			<%
			String styleid=request.getParameter("styleid");
			String userid=request.getParameter("userid");
			Integer useridInt=Integer.parseInt(userid);
			String action = request.getParameter("action");
			String content  = request.getParameter("content");
			String title  = request.getParameter("title");
		
			CommentDao dao = new CommentDao();
			LikeDao likedao=new LikeDao();
			UserDao userdao=new UserDao();
		
			if("submit".equals(action))
			{
				Comment comment = new Comment(null,title,content,styleid,null);
				userdao.createComment(useridInt,comment);
			}
			if("like".equals(action))
			{
				Integer id1=likedao.Getremoveid(useridInt,styleid);
				Like like = new Like(null,styleid,null); 
				userdao.createLike(useridInt,like);
			}
			if("unlike".equals(action))
			{
				Integer id=likedao.Getremoveid(useridInt,styleid);
				likedao.removelike(id);
			}
			List<Comment> comments = dao.findCommentsbystyleid(styleid);
			makesDAO client=new makesDAO();
			photo[] photos=client.findphotobystyleid(styleid);
			engineholder enginesh =client.findbystyleid(styleid);
			if(photos==null||enginesh==null)
			{
				
				User user = userdao.findUser(useridInt);
			%>
				<div style="color: #FF0000;">There's no detail for this style!</div>	
				<script>
					setTimeout(window.location="styles.jsp?userid=<%=user.getUserId()%>",5000);
				</script>
			<%
			} else {
				String base = "http://media.ed.edmunds-media.com/";
				photo p = new photo();
				p = photos[0];
				String pp[] = p.getPhotoSrcs();
			%>
			<a id="home" class="btn btn-info" href="user.jsp?userid=<%=userid %>">Home</a>
			<br>
			<br>
			<%
			List<Like> likes = likedao.findUser(useridInt);
			if(likes.size() != 0){
				int find = 0;
				for(int i=0; i<likes.size(); i++){
					if((find == 0) && likes.get(i).getStyleid().equals(styleid)){
						find = find + 1;
			%>
					<form class="form-signin" action="styleDetail.jsp">
					<input type="hidden" name="userid" value="<%=userid%>" class="form-control" />
					<input type="hidden" name="styleid" value="<%=styleid%>" class="form-control" />
					<div id="unlike" class="form-group">
						<button class="btn btn-danger" name="action" value="unlike">Unlike</button>
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
					<form class="form-signin" action="styleDetail.jsp">
					<input type="hidden" name="userid" value="<%=userid%>" class="form-control" />
					<input type="hidden" name="styleid" value="<%=styleid%>" class="form-control" />
					<div id="like" class="form-group">
						<button class="btn btn-success" name="action" value="like">Like</button>
					</div>
					</form>
					<%
				}
			}else{%>
				<form class="form-signin" action="styleDetail.jsp">
				<input type="hidden" name="userid" value="<%=userid%>" class="form-control" />
				<input type="hidden" name="styleid" value="<%=styleid%>" class="form-control" />
				<div id="like" class="form-group">
					<button class="btn btn-success" name="action" value="like">Like</button>
				</div>
				</form>
			<%
			}
			%>
			<p style="font-size: 160%">Images:</p>
				<center>
				<p>
					<img id="photo" src="<%=base + pp[0]%>" style="width: 400px; height: 300px" />
				</p>
				</center>
				
			<div class="einfo-right">
			<p style="font-size: 160%">Engine Info:</p>
			
			<ul>
			<%
				for (engine engines : enginesh.getEngines()) {
			%>
				<li>Engine Id: <%=engines.getId()%></li>
				<li>Engine EquipmentType: <%=engines.getEquipmentType()%></li>
				<li>Engine Cylinder: <%=engines.getCylinder()%></li>
				<li>Engine Size: <%=engines.getSize()%></li>
				<li>Engine Displacement: <%=engines.getDisplacement()%></li>
				<li>Engine Configuration: <%=engines.getConfiguration()%></li>
				<li>Engine FuelType: <%=engines.getFuelType()%></li>
				<li>Engine Horsepower: <%=engines.getHorsepower()%></li>
				<li>Engine Torque: <%=engines.getTorque()%></li>
				<li>Engine TotalValves: <%=engines.getTotalValves()%></li>
				<li>Engine Type: <%=engines.getType()%></li>
				<li>Engine Code: <%=engines.getCode()%></li>
				<li>Engine CompressorType: <%=engines.getCompressorType()%></li>
			<%
				}
			}
			%>
			</div>
			<br>
			
			<p style="font-size: 160%">Comments Field</p>
			<ul>
				<%
					for (Comment comment : comments) {	
						if(comment.getUser().getUserId() == useridInt){
				%>
						<li><a href="user.jsp?userid=<%=comment.getUser().getUserId()%>"><%=comment.getUser().getUsername()%></a></li>
					<% 	} 
						else{
					%>
						<li><a href="reportUser.jsp?userid=<%=comment.getUser().getUserId()%>&me=<%=userid%>"><%=comment.getUser().getUsername()%></a></li>
						<%} %>
						<tr>Title: <%=comment.getTitle()%></tr><br>
						<tr>Content: <%=comment.getContent()%></tr>
				
				<br>
				<%
					}
				%>
			</ul>
			
			<p style="font-size: 160%">Make Comments:</p>
			<div class="cm">
			<form class="form-signin" action="styleDetail.jsp">
				<input type="hidden" name="username" value="<%=userdao.findUser(useridInt).getUsername()%>" style="width: 50px;" class="form-control" />
				<input type="hidden" name="userid" value="<%=userid%>" class="form-control" />
				<input type="hidden" name="styleid" value="<%=styleid%>" class="form-control" /> 
				<div style="color: #FF0000;">${errorMessage}</div>
				Title: <br> 
				<input type="text" name="title" style="width: 500px;" class="form-control" /> <br>
				Content:<br>
				<textarea class="form-control" rows="5" id="content" name="content"></textarea>
				<br>
				<button class="btn btn-primary" type="submit" name="action" value="submit">Submit</button>
			</form>
			</div>
		</div>
	</div>
</body>
</html>