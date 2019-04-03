package com.exam;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*import com.p1.Details;
import com.p1.SignUpDao;*/

/**
 * Servlet implementation class AdminSignUp
 */
@WebServlet("/AdminSignUp")
public class AdminSignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminSignUp() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html");	
		PrintWriter out = response.getWriter();
		HttpSession ses = request.getSession(true);
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		String password=request.getParameter("pwd");
		String contact = request.getParameter("contact");
		String company=request.getParameter("company");
		Details d=new Details();
		d.setName(name);
		d.setEmail(email);
		d.setPassword(password);
		d.setContact(contact);
		d.setCompany(company);
		
		try 
		{
			
			   int i= AdminDao.createTable(d);
			   System.out.println(i);
			   
			// request.getRequestDispatcher("check.html").forward(request,response);
			 
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
