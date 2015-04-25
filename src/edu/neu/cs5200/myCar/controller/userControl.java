package edu.neu.cs5200.myCar.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.neu.cs5200.myCar.dao.UserDao;
import edu.neu.cs5200.myCar.models.User;

/**
 * Servlet implementation class userControl
 */
@WebServlet("/userControl")
public class userControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public userControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String id = request.getParameter("userid");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		UserDao dao = new UserDao();
		if ("submit".equals(action)) {
			User user = dao.findUser(Integer.parseInt(id));
			if(user.getUsername().equals(username) && user.getPassword().equals(password)){
					response.sendRedirect("update.jsp?userid="+user.getUserId());
			}
			else if(user.getType().equals("user")){
				request.setAttribute("errorMessage", "Wrong username&password combination");
				request.getRequestDispatcher("user.jsp?userid="+user.getUserId()).forward(request, response);
			}
			else if(user.getType().equals("admin")){
				request.setAttribute("errorMessage", "Wrong username&password combination");
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
