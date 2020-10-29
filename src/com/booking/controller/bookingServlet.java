package com.booking.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.booking.model.BookingDAO;
import com.booking.model.BookingService;
import com.booking.model.BookingVO;
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
		String time=request.getParameter("time");
		String tntno=request.getParameter("tntno");
		if ("insert".equals(action)) {
			// 進行轉換
			try {
			    BookingService bks= new BookingService();
			    String thow =bks.insert(data, lld_no);
				PrintWriter out = response.getWriter();
				out.println(thow);

				out.close();
			
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} 
		else if ("findTheDayBytnt".equals(action)) {
			BookingDAO dao = new BookingDAO();
			Boolean answer=dao.findTheDayBytnt(tntno,time);
			PrintWriter out = response.getWriter();
			out.println(answer);

			out.close();
	} 
		else if ("deletelld".equals(action)) {
			BookingDAO dao =new BookingDAO();
			
				dao.deletelld(data);
		}
		else if ("deletetnt".equals(action)) {
			BookingDAO dao =new BookingDAO();
			
				dao.deletetnt(data,time);
		}
		else if ("update".equals(action)) {
				  BookingService bks= new BookingService();
				try {
					bks.insertorder(request.getParameter("resdno"),
					request.getParameter("house"),
					request.getParameter("date"),
					request.getParameter("tntno"),
					request.getParameter("type"),
					request.getParameter("resstatus"));

					
				} catch (Exception e) {
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
