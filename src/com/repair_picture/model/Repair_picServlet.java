package com.repair_picture.model;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;


@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
// 當數據量大於fileSizeThreshold值時，內容將被寫入磁碟
// 上傳過程中無論是單個文件超過maxFileSize值，或者上傳的總量大於maxRequestSize 值都會拋出IllegalStateException 異常
public class Repair_picServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String saveDirectory = "/images_uploaded"; // 上傳檔案的目的地目錄;
											   // 將由底下的第26~30行用 java.io.File 於 ContextPath 之下, 自動建立目地目錄

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8"); // 處理中文檔名
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();
		
		System.out.println("ContentType="+req.getContentType()); // 測試用

		String realPath = getServletContext().getRealPath(saveDirectory);
		System.out.println("realPath="+realPath); // 測試用
		
		File fsaveDirectory = new File(realPath);
		if (!fsaveDirectory.exists())
			 fsaveDirectory.mkdirs(); // 於 ContextPath 之下,自動建立目地目錄

		Collection<Part> parts = req.getParts(); // Servlet3.0新增了Part介面，讓我們方便的進行檔案上傳處理
		out.write("<h2> Total parts : " + parts.size() + "</h2>");
		
		
		for (Part part : parts) {
			out.println("for");
			String filename = getFileNameFromPart(part);
			out.println(part.getContentType());
			if (filename!= null && part.getContentType()!=null) {
				out.println("<PRE>");
				String name = part.getName();
				String ContentType = part.getContentType();
				long size = part.getSize();
				File f = new File(fsaveDirectory, filename);

				out.println("name: " + name);
				out.println("filename: " + filename);
				out.println("ContentType: " + ContentType);
				out.println("size: " + size);
				out.println("File: " + f);

				// 利用File物件,寫入目地目錄,上傳成功
				part.write(f.toString());

				// 額外測試 InputStream 與 byte[] (幫將來model的VO預作準備)
				InputStream in = part.getInputStream();
				byte[] buf = new byte[in.available()];
				in.read(buf);
				in.close();
				out.println("buffer length: " + buf.length);
				
				// 額外測試秀圖
				out.println("<br><img src=\""+req.getContextPath()+saveDirectory+"/"+filename+"\">");

				out.println();
				out.println("</PRE>");
			}
		}
		out.println("out-for");
	}

	// 取出上傳的檔案名稱 (因為API未提供method,所以必須自行撰寫)
	public String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition");
		System.out.println("header=" + header); // 測試用
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
		System.out.println("filename=" + filename); // 測試用
		if (filename.length() == 0) {
			return null;
		}
		return filename;
	}
}