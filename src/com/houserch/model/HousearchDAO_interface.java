package com.houserch.model;

import java.util.ArrayList;
import java.util.Map;

public interface HousearchDAO_interface {
	public Map<String,HousearchVO> getAll();
	public Map<String,HousearchVO> getMapfromSearchKey(HousearchVO vo);
	public  ArrayList<String> getAlladd();
	public Map<String,HousearchVO> getGMapfromSearchKey(HousearchVO vo);
	public Map<String,HousearchVO> getAllGmap();
	
	
}
