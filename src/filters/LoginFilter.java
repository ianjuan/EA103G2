package filters;

import java.io.*;
import java.util.Arrays;

import javax.servlet.*;
import javax.servlet.http.*;

public class LoginFilter implements Filter {

	private FilterConfig config;

	public void init(FilterConfig config) {
		this.config = config;
	}

	public void destroy() {
		config = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws ServletException, IOException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		String servletPath = (String) req.getServletPath();
		String[] paths = servletPath.split("/");
		
		
		
		for(String s:paths) {
			System.out.println(s);
		}
//		System.out.println(Arrays.binarySearch(paths, "bookingforhouse.jsp") > 0);
		if (Arrays.binarySearch(paths, "booking") > 0) {  //front-end index folder is free
			System.out.println(paths.length);
			System.out.println(paths[paths.length-1]);
//			for(String s:paths) {
//				a=s.split(".");
//			}
		}
		
		
		
		
		Boolean ispathprotected = true;
		if (Arrays.binarySearch(paths, "index") > 0) {  //front-end index folder is free
			ispathprotected = false;
		}
		if (Arrays.binarySearch(paths, "navbar") > 0) {  //front-end navbar folder is free
			ispathprotected = false;
		}
		if (Arrays.binarySearch(paths, "bookingforhouse.jsp") > 0) { 
			ispathprotected = false;
		}
		
//		if (Arrays.binarySearch(paths, "back-end") > 0) {  //back-end不需前台登入
//			ispathprotected = false;
//		} 

		HttpSession session = req.getSession(); // 【從 session 判斷此user是否登入過】
		String tnt_no = (String) session.getAttribute("tnt_no");
		String lld_no = (String) session.getAttribute("lld_no");


		if (ispathprotected && (tnt_no == null) && (lld_no == null)) {// protected path
				System.out.println("filter-- sourcelocation:" + req.getRequestURI());
				session.setAttribute("location", req.getRequestURI());
				res.sendRedirect(req.getContextPath() + "/front-end/index/Identify.jsp");
//				System.out.println("3.重導去Identify.jsp");
				return;

		} else {
			chain.doFilter(req, res);
		}
	}
}