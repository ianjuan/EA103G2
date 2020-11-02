package com.news.model;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.*;
import javax.servlet.ServletOutputStream;
import javax.sql.DataSource;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.websocket.jedis.JedisHandleMessage;




public class NewsDAO implements NewsDAO_interface{

	@Override
	public void insert(NewsVO newsVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public NewsVO findByPrimaryKey(String new_no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NewsVO> getAll() {
		Gson gson= new Gson();
//		List<String> historyData = JedisHandleMessage.getHistoryMsg();
//		String jsonStr = "";
//		jsonStr = historyData.toString();
//		Type collectionType = new TypeToken<List<NewsVO>>() {
//		}.getType();
//		List<NewsVO> myNewsVOList = gson.fromJson(jsonStr, collectionType);
		return null;
	}

	@Override
	public void update(NewsVO newsVO) {
		// TODO Auto-generated method stub
		
	}
	
	
}
