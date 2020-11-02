package com.repair.model;

import java.sql.Date;
import java.util.List;

import com.repair_picture.model.Repair_pictureVO;

public class RepairService {
	private RepairDAO_interface dao;
	
	public RepairService() {
		dao = new RepairJDBCDAO();
	}
	
	public List<Repair_pictureVO> getAllPicNo(String rep_no){
		List<Repair_pictureVO> list = dao.getPicsNo(rep_no);
		
		return list;
	}
	
	public Repair_pictureVO addRepair_pic(String rep_no, byte[] reppic_pic) {
		Repair_pictureVO repair_pictureVO = new Repair_pictureVO();
		System.out.println("有到service");
		
		repair_pictureVO.setRep_no(rep_no);
		System.out.println("ser: rep_no已set");
		
		repair_pictureVO.setReppic_pic(reppic_pic);
		System.out.println("ser: pic已set");
		
		dao.insert_pic(repair_pictureVO);
		System.out.println("轉交dao");
		
		return repair_pictureVO;
		
	}
	
	
	
	
	public RepairVO addRepair(String con_no, String rep_dam_obj, String rep_dam_obj_des, java.sql.Date rep_case_str ) {
		RepairVO repairVO = new RepairVO();
		
		repairVO.setCon_no(con_no);
		repairVO.setRep_dam_obj(rep_dam_obj);
		repairVO.setRep_dam_obj_des(rep_dam_obj_des);
		repairVO.setRep_case_str(rep_case_str);
		RepairVO repairVO2 = dao.tnt_insert(repairVO);
		
		return repairVO2;
		
	}

	public RepairVO addEnddate(String rep_no, java.sql.Date rep_est_enddate) {
		RepairVO repairVO = new RepairVO();
		
		repairVO.setRep_no(rep_no);
		repairVO.setRep_est_enddate(rep_est_enddate);
		dao.enddate_update(repairVO);
		return repairVO;
		
	}
	
	public RepairVO updatePro(String rep_no, Integer rep_pro) {
		RepairVO repairVO = new RepairVO();
		
		repairVO.setRep_no(rep_no);
		repairVO.setRep_pro(rep_pro);
		dao.update_Pro(repairVO);
		return repairVO;
		
	}
	
	public RepairVO updateDes(String rep_no, String rep_dam_obj_des) {
		RepairVO repairVO = new RepairVO();
		
		repairVO.setRep_no(rep_no);
		repairVO.setRep_dam_obj_des(rep_dam_obj_des);
		dao.tnt_updateDes(repairVO);
		return repairVO;
		
	}

	public RepairVO updateRpt(String rep_no, Integer rep_tnt_rpt, Integer rep_pro, Date rep_tnt_rpttime, Date rep_end_time) {
		RepairVO repairVO = new RepairVO();
		
		repairVO.setRep_no(rep_no);
		repairVO.setRep_tnt_rpt(rep_tnt_rpt);
		repairVO.setRep_pro(rep_pro);
		repairVO.setRep_tnt_rpttime(rep_tnt_rpttime);
		repairVO.setRep_end_time(rep_end_time);
		dao.tnt_update(repairVO);
		return repairVO;
		
	}

	public RepairVO getOneRepair(String rep_no) {
		return dao.findByPrimaryKey(rep_no);
	}

	public List<RepairVO> tntGetAll(String con_no){
		System.out.println("tntGetAll service");
		return dao.tnt_getAll(con_no);
	}

	public List<RepairVO> lldGetAll(String lld_no){
		return dao.lld_getAll(lld_no);
	}

	public void delPic(String reppic_no, String new_reppic_no) {
		dao.del_pic(reppic_no, new_reppic_no);
	}


}
