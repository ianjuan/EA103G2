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
import com.notify.controller.NotifyServlet;

/**
 * Servlet implementation class mapServlet
 */
public class GMapServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       int i=0;//測試用
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
		String city = request.getParameter("city");
		String town = request.getParameter("town");
		String searchbox = request.getParameter("searchbox");
		String sort =request.getParameter("sort");
		String money=request.getParameter("money");
		String house=request.getParameter("house");
		if ("search".equals(request.getParameter("action"))) {
			System.out.println("igotu"+request.getParameter("sort"));
			i++;//測試用
			String word="這是第"+i+"個訊息";//測試用
			new NotifyServlet().broadcast("LLD000002", word, "我是內文", "");//測試用
			HousearchService gs= new HousearchService();

			String data =gs.getMapfromSearchKey(city,town,searchbox,sort,money,house,request.getParameter("page")
					);

			PrintWriter out = response.getWriter();
			out.print(data);
			out.close();
			return;
		}
		if ("gmapsearch".equals(request.getParameter("action"))) {
			System.out.println("igotu"+request.getParameter("sort"));
			
			HousearchService gs= new HousearchService();

			String data =gs.getGMapfromSearchKey(city,town,searchbox,sort,money,house);

			PrintWriter out = response.getWriter();
			out.print(data);
			out.close();
			return;
		}

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
