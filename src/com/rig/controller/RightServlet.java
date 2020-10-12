package com.rig.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.rig.model.*;

/**
 * Servlet implementation class FunctionServlet
 */

@WebServlet("/RightServlet")
public class RightServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		接收參數
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		if("search".equals(action)) {
			String emp_no = req.getParameter("emp_no");
			String url="/back-end/right_test/right_show.jsp";
			String path = getServletContext().getRealPath("")+"\\back-end\\img\\tomcat.png";
			String img64 = encryptToBase64(path);
//		查詢參數
			RightService rigSvc = new RightService();
			List<RightVO> list = rigSvc.getAll(emp_no);
			req.setAttribute("rigVO", list);
//			System.out.println(img);

//		轉交
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		if("update".equals(action)) {
			String emp_no = req.getParameter("emp_no");
			String url="/back-end/right_test/right_show.jsp";
		}
		
	}

	public String encryptToBase64(String filePath) {
		if (filePath == null) {
			return null;
		}
		try {
			byte[] b = Files.readAllBytes(Paths.get(filePath));
			return Base64.getEncoder().encodeToString(b);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
}
