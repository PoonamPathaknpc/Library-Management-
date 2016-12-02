package com.dbproject.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dbproject.model.Book_AuthorBean;
import com.dbproject.model.DBLibMgmt;
import com.dbproject.model.MessageBean;
import com.google.gson.Gson;

/**
 * Servlet implementation class Search_Controller
 */
public class Search_Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Search_Controller() {
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
		response.setContentType("text/html");
		MessageBean M = new MessageBean();
		String message = null;
		
		if (request.getParameter("UpdateFines") != null) {
			DBLibMgmt dblib = new DBLibMgmt();
			dblib.UpdateFines();
			M.setMessage("All fines are updated... ");
			message = new Gson().toJson(M);
            request.setAttribute("message", message);
            
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
    		dispatcher.forward(request, response);
		}
		
		else
		{
		String SearchString = request.getParameter("isbn");
		DBLibMgmt dblib = new DBLibMgmt();
		
		List<Book_AuthorBean> ResultBean = dblib.getSearchDetails(SearchString);
		System.out.println("result   " + ResultBean.size() );
		request.setAttribute("SResult", ResultBean);
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/SearchDisplay.jsp");
		dispatcher.forward(request, response);
		}	
		
	}

}
