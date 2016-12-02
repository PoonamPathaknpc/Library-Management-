package com.dbproject.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dbproject.model.DBLibMgmt;
import com.dbproject.model.MessageBean;
import com.google.gson.Gson;

/**
 * Servlet implementation class BorrowerRegisterControllerServlet
 */
public class BorrowerRegisterControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BorrowerRegisterControllerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		String message = null;
		String Fname = request.getParameter("fName");
		String Lname = request.getParameter("lName");
		String Phone = request.getParameter("tnum");
		String Addr1= request.getParameter("fadd");
		String Addr2= request.getParameter("counties");
		String Addr3= request.getParameter("zcode");
		String ssn= request.getParameter("ssn");;
		String Address = Addr1 +", " + Addr2 + "," + Addr3;
		MessageBean M = new MessageBean();
		
		DBLibMgmt DBLib = new DBLibMgmt();
		String status = DBLib.createBorrowerRecord(Fname, Lname, Phone, ssn, Address);
		if(status=="false")
		{
			M.setMessage("This Borrower is already registered in the system");
		message = new Gson().toJson(M);
		response.getWriter().write(message);
		}
		else
		{
		M.setMessage("The borrower is registered with Card ID: " + status);
			message = new Gson().toJson(M);
			response.getWriter().write(message);
		}
		
		request.setAttribute("message", message);

		RequestDispatcher rd = getServletContext().getRequestDispatcher("/Register.jsp");
		rd.forward(request, response);
		
		
	}

}
