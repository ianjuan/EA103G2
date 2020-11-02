package com.houserch.controller;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.houserch.model.HousearchDAO;




@WebServlet("/hos/hosPic")
public class pic extends HttpServlet {
 private static final long serialVersionUID = 1L;
       
 public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
  res.setContentType("image/*");
  String pic="";
  ServletOutputStream out = res.getOutputStream();
 HousearchDAO dao =new HousearchDAO();
  if(req.getParameter("housedet")!=null) {
	  pic  = req.getParameter("housedet");
	   out.write(new ImageUtil().shrink(dao.getpic(pic),600));
	   out.close();

  }
  else if(req.getParameter("search")!=null) {
	  pic  = req.getParameter("search");

	   out.write(new ImageUtil().shrink(dao.getpic(pic),200));
	   out.close();
  }

 }
}