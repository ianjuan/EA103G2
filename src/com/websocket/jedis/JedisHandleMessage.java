package com.websocket.jedis;

import java.util.Collections;
import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisHandleMessage {
	// 此範例key的設計為(發送者名稱:接收者名稱)，實際應採用(發送者會員編號:接收者會員編號)

	private static JedisPool pool = JedisPoolUtil.getJedisPool();

	public static List<String> getHistoryMsg() {
		String key = "後台公告";
		Jedis jedis = null;
		jedis = pool.getResource();
		jedis.auth("123456");
		//再取出所有資料jedis.lrange是按範圍取出， 
		// 第一個是key，第二個是起始位置，第三個是結束位置，jedis.llen獲取長度 -1表示取得所有 
		List<String> historyData = jedis.lrange(key, 0, -1);
		System.out.println("historyData"+historyData);
		Collections.reverse(historyData);//把List元素倒轉
		jedis.close();
		return historyData;
	}

	public static void saveNews(String announce, String message) {
		// 對雙方來說，都要各存著歷史聊天記錄
		String news = new StringBuilder("後台公告").toString();
		Jedis jedis = pool.getResource();
		jedis.auth("123456");
		jedis.rpush(news, message);

		jedis.close();
	}

}
