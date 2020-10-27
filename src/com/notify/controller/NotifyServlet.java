package com.notify.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;

import com.google.gson.Gson;
import com.notify.model.NotifyDAO;
import com.notify.model.NotifyVO;

public class NotifyServlet {
	private static Map<String, Session> sessionsMap = new ConcurrentHashMap<>();
	Gson gson = new Gson();

	@OnOpen
	public void onOpen(@PathParam("userNo") String userNo, Session session) throws IOException {
		sessionsMap.put(userNo, session);
		List<String> historyData = NotifyDAO.getHistoryNotify(userNo);
		System.out.println(historyData);
		String historyStr = gson.toJson(historyData);
		if (historyStr != null) {
			if (session.isOpen()) {
				session.getAsyncRemote().sendText(historyStr);

			}
		}
		//測試用的code
		String text = String.format("Session ID = %s, connected; userNo = %s", session.getId(),
				userNo);
		System.out.println(text);
	}
	
	@OnMessage
	public void onMessage(Session session, String message) {
		
	}
	
	
	@OnClose
	public void onClose(Session session,CloseReason reason) {
		
		Set<String> userNos = sessionsMap.keySet();
		for (String userNo : userNos) {
			if (sessionsMap.get(userNo).equals(session)) {
				sessionsMap.remove(userNo);
				break;
			}
		}
		
		//測試用的code
		String text = String.format("session ID = %s, disconnected; close code = %d%n userNos: %s", session.getId(),
				reason.getCloseCode().getCode(), userNos);
		System.out.println(text);
	}
	
	
	@OnError
	public void onError(Session userSession, Throwable e) {
		System.out.println("Error: " + e.toString());
	}
	
	
	public void broadcast(String userNo,String title,String content) {
		
		if(userNo==null)return;
		
		//將提醒存進redis資料庫
		NotifyVO notify =new NotifyVO(title, content, new Date().getTime());
		String notifyJson = gson.toJson(notify);
		NotifyDAO.saveNotify(userNo, notifyJson);
		System.out.println("存進資料庫的訊息："+notifyJson);
		
		//判斷目前是否有連線，有連線才需要推播
		if(sessionsMap.get(userNo)!=null) {
			//直接送出給前端時間設定0表示即時訊息，由前端進行判斷顯示時間
			String notifyJsonNow = gson.toJson(notify);
			sessionsMap.get(userNo).getAsyncRemote().sendText(notifyJsonNow);
			System.out.println("直接傳送至前台的訊息"+notifyJsonNow);

		}
	}
}
