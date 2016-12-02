package com.dbproject.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dbproject.model.BOOK;
import com.dbproject.model.BORROWER;
import com.dbproject.model.DBLibMgmt;
import com.dbproject.model.FINES;
import com.dbproject.model.MessageBean;
import com.dbproject.model.OverdueBean;
import com.google.gson.Gson;

/**
 * Servlet implementation class BorrowerDetailsController
 */
public class BorrowerDetailsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BorrowerDetailsController() {
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
		
		String CardID=request.getParameter("cardid");
		DBLibMgmt dblib = new DBLibMgmt();
		MessageBean M = new MessageBean();
		String message=null;
		
		 if (request.getParameter("Fine") != null) {
	           List<FINES> F=dblib.getFineDetails(CardID);
	           System.out.println(F.size() + " is the size ");
	           request.setAttribute("FineDetails", F);
	           RequestDispatcher rd = getServletContext().getRequestDispatcher("/FineDetails.jsp");
				rd.forward(request, response);
	           
	        }
		 
		 else if (request.getParameter("BowD") != null) {
			  
	            BORROWER b= dblib.ViewBorrowerDetails(CardID);
	            if(b==null)
	            {
	            	M.setMessage("No borrower exists in the database with given SSN or card id");
					message = new Gson().toJson(M);
					response.getWriter().write(message);
					RequestDispatcher rd = getServletContext().getRequestDispatcher("/BorrowerDetails.jsp");
					rd.forward(request, response);
	            }
	            else
	            {
	            request.setAttribute("borrower", b);			
				request.getRequestDispatcher("DisplayBorrower Details.jsp").forward(request, response);
	            }
	            
		 }
		 else if (request.getParameter("OverDB") != null) {
			 List<OverdueBean> ODBook= dblib.listOverDueBooks(CardID);
			 request.setAttribute("OverdueR", ODBook);			
			 //request.getRequestDispatcher("OverDueBooks.jsp").forward(request, response);
			 RequestDispatcher rd = getServletContext().getRequestDispatcher("/OverDueBooks.jsp");
				rd.forward(request, response);
	        } 
		 
		 else {
	            
	        }
		
	}

}
