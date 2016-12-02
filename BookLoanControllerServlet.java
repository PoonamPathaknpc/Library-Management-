package com.dbproject.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.dbproject.model.BOOK;
import com.dbproject.model.BORROWER;
import com.dbproject.model.DBLibMgmt;
import com.dbproject.model.FINES;
import com.dbproject.model.MessageBean;

/**
 * Servlet implementation class BookLoanControllerServlet
 */
public class BookLoanControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookLoanControllerServlet() {
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
		String Cardid=request.getParameter("CardID");
		String ISBN=request.getParameter("ISBN");
		MessageBean M = new MessageBean();
		DBLibMgmt DBLib = new DBLibMgmt();
		
		
		if (request.getParameter("checkout") != null) {
			
			int flag = DBLib.createBookLoanRecord(Cardid, ISBN);
			
			if (flag==1 )
			{M.setMessage("The borrower with given card id does not exists. Please register the borrower first");
			message = new Gson().toJson(M);
			response.getWriter().write(message);
			
			}
			else if(flag==2)
			{
		     M.setMessage("The borrower isnt allowed to loan the book as he/she has either 3 or more Active book loans already or hasnt paid the fine for one or more checked in books"	);
		     message = new Gson().toJson(M);
		     response.getWriter().write(message);
			}
		     else if(flag==3)
			{
			M.setMessage("The borrower isnt allowed to loan the book as he/she currenlty an overdue book ");
			message = new Gson().toJson(M);
			response.getWriter().write(message);
			}
			else if (flag==4)
			{
			M.setMessage("The book is not available to be loaned anymore");
			message = new Gson().toJson(M);
			response.getWriter().write(message);
			}
			else
			{
				M.setMessage("The Book is successfully Checked Out");
				message = new Gson().toJson(M);
				response.getWriter().write(message);	
			}
			
			request.setAttribute("message", message);

			RequestDispatcher rd = getServletContext().getRequestDispatcher("/BookCheckout.jsp");
			rd.forward(request, response);
	           
	           
	        }
		 
		 else if (request.getParameter("checkin") != null) {
			  
	            Boolean status=DBLib.borrowerBookCheckin(Cardid, ISBN);
	            if(status==false)
	            {	            	
	            M.setMessage("The borrower does not have any book loans");
				message = new Gson().toJson(M);
	            request.setAttribute("message", message);

				RequestDispatcher rd = getServletContext().getRequestDispatcher("/BookCheckout.jsp");
				rd.forward(request, response);
	            }
	            else
	            {
	            	M.setMessage("The Book is successfully Checkedin ");
					message = new Gson().toJson(M);
		            request.setAttribute("message", message);

					RequestDispatcher rd = getServletContext().getRequestDispatcher("/BookCheckout.jsp");
					rd.forward(request, response);
	            }
	            
		 }
		
		 else  {			 
		 
			    Boolean status = DBLib.payFineForBorrower(Cardid, ISBN);
			    if(status==true)
			    {
			    System.out.print("successfully fine paid");
		        request.setAttribute("message", message);
		        M.setMessage("The Book is successfully Checked Out");
				message = new Gson().toJson(M);
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/BookCheckout.jsp");
				rd.forward(request, response);
			    }
			    
			    else
			    {			    	
			    	request.setAttribute("message", message);
			        M.setMessage("The borrower with given card id does not exists. Please register the borrower first");
					message = new Gson().toJson(M);
					RequestDispatcher rd = getServletContext().getRequestDispatcher("/BookCheckout.jsp");
					rd.forward(request, response);
			    }
		 
		        }
		
		
	
	}

}
