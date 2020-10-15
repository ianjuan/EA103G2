package com.collection.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.collection.model.CollectionDAO;
import com.collection.model.collectionService;

/**
 * Servlet implementation class collectionServlet
 */
public class collectionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public collectionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		System.out.println("有道這");
		
		
		if ("insert".equals(request.getParameter("action"))) {			
			CollectionDAO dao=new CollectionDAO();
			System.out.println("TNTNO="+request.getParameter("tnt")+"  HOSNO="+request.getParameter("hos"));
			dao.insertCollection(request.getParameter("tnt"),request.getParameter("hos"));
		}
		
		if ("delete".equals(request.getParameter("action"))) {
			CollectionDAO dao=new CollectionDAO();
			System.out.println("TNTNO="+request.getParameter("tnt")+"  HOSNO="+request.getParameter("hos"));
			dao.deleteCollection(request.getParameter("tnt"),request.getParameter("hos"));
		}
		if ("deleteall".equals(request.getParameter("action"))) {
			collectionService ser=new collectionService();
			System.out.println("TNTNO="+request.getParameter("tnt")+"  HOSNO="+request.getParameter("hos"));
			ser.deleteallCollection(request.getParameter("tnt"),request.getParameter("hos"));
		}
		
		if ("search".equals(request.getParameter("action"))) {
			collectionService ser=new collectionService();
			String data=ser.getAllCollectionVOfromTNTNO(request.getParameter("tnt"),request.getParameter("price"),request.getParameter("type"),request.getParameter("pnum"),request.getParameter("report"));
			PrintWriter out = response.getWriter();
			out.print(data);
			out.close();
		}
		
		else {
			response.getWriter().append("Served at: ").append(request.getContextPath());

			RequestDispatcher failureView = request.getRequestDispatcher("/front-end/collection/Mycol.jsp");
			failureView.forward(request,response);
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
