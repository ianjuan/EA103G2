package com.repair.model;

import java.util.List;

import com.repair_picture.model.Repair_pictureVO;

public interface RepairDAO_interface {

	public RepairVO tnt_insert(RepairVO RepairVO) ;
	
	public void tnt_updateDes(RepairVO RepairVO) ;
	
	public void enddate_update(RepairVO RepairVO);
	
	public void update_Pro(RepairVO RepairVO);
	
	public void tnt_update(RepairVO RepairVO);
	
    public RepairVO findByPrimaryKey(String rep_no);
  
    public List<RepairVO> lld_getAll(String hos_no);
   
    public List<RepairVO> tnt_getAll(String con_no);
    //增加圖片到修繕申請
	public void insert_pic(Repair_pictureVO repair_pictureVO);
	//取出圖片
	public List<Repair_pictureVO> getPicsNo(String rep_no);
	
	public void del_pic(String reppic_no, String new_reppic_no);
	


}
