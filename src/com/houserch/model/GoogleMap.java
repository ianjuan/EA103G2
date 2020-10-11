package com.houserch.model;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import com.houserch.model.HousearchVO;
public class GoogleMap {

	private static String getLatlng(String address) {
		String ret = "";
		if (address != null && !address.equals("")) {
			try {
				address = URLEncoder.encode(address, "UTF-8");//避免亂碼
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
			String url = MessageFormat.format(
					"https://maps.googleapis.com/maps/api/geocode/json?address={0}&key=AIzaSyDxL16LHes_Y4e96wJGKpsPGMXQJ_VlBL8",
					address);
			URL urlmy = null;
			try {
				urlmy = new URL(url);
				HttpURLConnection con = (HttpURLConnection) urlmy.openConnection();
				con.setFollowRedirects(true);
				con.setInstanceFollowRedirects(false);
				con.connect();
				BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
				String s = "";
				StringBuffer sb = new StringBuffer("");
				while ((s = br.readLine()) != null) {
					sb.append(s);
				}
				ret = "" + sb;
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return ret;
	}
	public static void main(String[] args) {
//		System.out.println(getLatlng("中壢區中央路300號"));
		Gson gson = new Gson();

//		for(int i=0;i<list.size();i++) {
//		Map map=gson.fromJson(getLatlng(list.get(i)), new TypeToken<HashMap>(){}.getType());
//		Double lat=(Double)((LinkedTreeMap)( (LinkedTreeMap)((LinkedTreeMap)((ArrayList)map.get("results")).get(0)).get("geometry")).get("location")).get("lat");
//		Double lng=(Double)((LinkedTreeMap)( (LinkedTreeMap)((LinkedTreeMap)((ArrayList)map.get("results")).get(0)).get("geometry")).get("location")).get("lng");
//
//		System.out.println(lat+","+lng);
//		}
	}
}