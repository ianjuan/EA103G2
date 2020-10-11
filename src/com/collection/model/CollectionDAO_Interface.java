package com.collection.model;

import java.util.ArrayList;
import java.util.List;

public interface CollectionDAO_Interface {
	
	public List<CollectionVO> getAllCollectionVOfromTNTNO(CollectionVO vo);
	public List<CollectionVO> getCollectionVOfromTNTNO(String TNT_NO);

	public void insertCollection(String TNT_NO,String HOS_NO);
	public void deleteCollection(String TNT_NO, String HOS_NO);
	public void deleteallCollection(String TNT_NO, ArrayList<String> HOS_NO);

}
