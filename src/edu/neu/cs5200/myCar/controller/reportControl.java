package edu.neu.cs5200.myCar.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.neu.cs5200.myCar.dao.ReportDao;
import edu.neu.cs5200.myCar.dao.UserDao;
import edu.neu.cs5200.myCar.models.Report;
import edu.neu.cs5200.myCar.models.User;

/**
 * Servlet implementation class reportControl
 */
@WebServlet("/reportControl")
public class reportControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public reportControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String reason = request.getParameter("reason");
		String me = request.getParameter("me");
		
		UserDao dao = new UserDao();
		String userid = request.getParameter("userid");
		int useridInt = Integer.parseInt(userid);
		User user = dao.findUser(useridInt);
		
		ReportDao reportdao = new ReportDao();
		if("report".equals(action))
	    {
			if(reason.equals("")){
				request.setAttribute("errorMessage", "Please enter report reason!");
				request.getRequestDispatcher("report.jsp?userid="+user.getUserId()+"&me="+me).forward(request, response);
			}
			else{
				Report report = new Report();
				report.setReason(reason);
				report.setUser(user);
				reportdao.createReport(report);
				request.getRequestDispatcher("reportUser.jsp?userid="+user.getUserId()).forward(request, response);
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
