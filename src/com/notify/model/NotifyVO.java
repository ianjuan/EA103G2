package com.notify.model;

public class NotifyVO {
private static final long serialVersionUID = 1L;
	
	private String title;
	private String content;
	private String url;
	private Long time;
	
	public NotifyVO(String title,String content,Long time, String url) {
		super();
		this.title=title;
		this.content=content;
		this.time=time;
		this.url=url;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
