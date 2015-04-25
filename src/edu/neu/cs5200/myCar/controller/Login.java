package edu.neu.cs5200.myCar.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.neu.cs5200.myCar.dao.UserDao;
import edu.neu.cs5200.myCar.models.User;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String type = request.getParameter("type");
		
		UserDao dao = new UserDao();
		if ("login".equals(action)) {
			List<User> users = new ArrayList<User>();
			users = dao.findUserByUsername(username);
			if(users.size() != 0){
				for(int i=0; i<users.size(); i++){
					if(users.get(i).getPassword().equals(password)){
						if(users.get(i).getType().equals(type)){
							if(type.equals("admin")){
								request.getRequestDispatcher("admin.jsp?userid="+users.get(i).getUserId()).forward(request, response);
								//response.sendRedirect("admin.jsp?userid="+users.get(i).getUserId());
								break;
							}
							else if(type.equals("user")){
								request.getRequestDispatcher("user.jsp?userid="+users.get(i).getUserId()).forward(request, response);
								//response.sendRedirect("user.jsp?userid="+users.get(i).getUserId());
								break;
							}
						}
						else{
							request.setAttribute("errorMessage", "Please choose correct type to login");
						}
					}
					else{
						request.setAttribute("errorMessage", "Wrong username&password combination");
					}
				}
			}
			else{
				request.setAttribute("errorMessage", "Cannot find such user");
			}
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}






