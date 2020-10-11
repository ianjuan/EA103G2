package com.houserch.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.houserch.model.HousearchService;

/**
 * Servlet implementation class mapServlet
 */
public class GMapServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GMapServlet() {
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
		
		if ("search".equals(request.getParameter("action"))) {
			System.out.println("igotu"+request.getParameter("sort"));
			
			HousearchService gs= new HousearchService();
//			String data =gs.getMapfromSearchKey(request.getParameter("data"),request.getParameter("sort"));

			String data =gs.getMapfromSearchKey(request.getParameter("data"),
					request.getParameter("sort"),request.getParameter("money"),request.getParameter("house"));

//			String data =gs.getMapfromSearchKey(request.getParameter("data"));
			PrintWriter out = response.getWriter();
//			System.out.println("data="+data);
			out.print(data);
			out.close();

		}
//		if("searchbypage".equals(request.getParameter("action"))) {
//			
//		}
		else {
			RequestDispatcher failureView = request.getRequestDispatcher("/front-end/mapserach/gmap2.jsp");

			failureView.forward(request, response);
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
