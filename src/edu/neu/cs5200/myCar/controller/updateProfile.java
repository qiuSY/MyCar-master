package edu.neu.cs5200.myCar.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.neu.cs5200.myCar.dao.CommentDao;
import edu.neu.cs5200.myCar.dao.LikeDao;
import edu.neu.cs5200.myCar.dao.UserDao;
import edu.neu.cs5200.myCar.models.Comment;
import edu.neu.cs5200.myCar.models.Follower;
import edu.neu.cs5200.myCar.models.Like;
import edu.neu.cs5200.myCar.models.User;

/**
 * Servlet implementation class updateProfile
 */
@WebServlet("/updateProfile")
public class updateProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateProfile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String email = request.getParameter("email");
		String firstName = request.getParameter("fname");
		String lastName = request.getParameter("lname");
		
		UserDao dao = new UserDao();
		String id = request.getParameter("userid");
		
		Integer idInt = Integer.parseInt(id);
		User user = dao.findUser(idInt);

		if("update".equals(action))
	    {
			if(email.equals("") || firstName.equals("") || lastName.equals("")){
				request.setAttribute("errorMessage", "Please fill out all fields!");
				request.getRequestDispatcher("update.jsp?userid="+user.getUserId()).forward(request, response);
			}
			else if(user.getType().equals("user")){
				User userUpdate = new User();
				userUpdate.setUsername(user.getUsername());
				userUpdate.setPassword(user.getPassword());
				userUpdate.setType(user.getType());
			
				userUpdate.setEmail(email);
				userUpdate.setFirstName(firstName);
				userUpdate.setLastName(lastName);
				dao.updateUser(idInt, userUpdate);
				request.getRequestDispatcher("user.jsp?userid="+user.getUserId()).forward(request, response);
			}
			else if(user.getType().equals("admin")){
				
				User userUpdate = new User();
				userUpdate.setUsername(user.getUsername());
				userUpdate.setPassword(user.getPassword());
				userUpdate.setType(user.getType());
			
				userUpdate.setEmail(email);
				userUpdate.setFirstName(firstName);
				userUpdate.setLastName(lastName);
				
				dao.updateUser(idInt, userUpdate);
				request.getRequestDispatcher("admin.jsp?userid="+user.getUserId()).forward(request, response);
			}
	    }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
