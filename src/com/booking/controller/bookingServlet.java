package com.booking.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.booking.model.BookingDAO;
import com.booking.model.BookingService;
import com.google.gson.Gson;

/**
 * Servlet implementation class booking
 */
public class bookingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public bookingServlet() {
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		//
		String action=request.getParameter("action");
		String data =request.getParameter("data");
		String lld_no=request.getParameter("lld_no");
		if ("insert".equals(action)) {
			BookingDAO dao = new BookingDAO();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			// 進行轉換
			try {
			    BookingService bks= new BookingService();
				System.out.println(bks.chang(data)+"jasdipsd");
				ArrayList<String> thow =dao.insert(bks.chang(data),lld_no);
				PrintWriter out = response.getWriter();
				out.println(thow);

				out.close();
			
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if ("getByHoid".equals(action)) {
			BookingDAO dao = new BookingDAO();
			dao.getBookingInfoListBylldno(request.getParameter("hoId"));
			
		} else if ("delete".equals(action)) {
			BookingDAO dao =new BookingDAO();
			try {
				dao.delet(data);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}}
		else if ("update".equals(action)) {
				BookingDAO dao =new BookingDAO();
				try {
					dao.update(data);
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}}

		 else {
			response.getWriter().append("Served at: ").append(request.getContextPath());
			RequestDispatcher failureView = request.getRequestDispatcher("/front-end/booking/bookingforhouse.jsp");
			failureView.forward(request, response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
