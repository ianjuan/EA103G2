package com.repair_picture.model;

import java.util.List;

public interface Repair_pictureDAO_interface {
	//房客新增一張待修物品的照片在修繕紀錄時
	public void insert(Repair_pictureVO repair_pictureVO);
	//查詢一筆修繕紀錄裡的其中一張圖片
	public Repair_pictureVO findByPrimaryKey(String reppic_no);
	//取得此筆修繕紀錄的所有修繕照片
    public List<Repair_pictureVO> lld_getAll(String rep_no);
}
