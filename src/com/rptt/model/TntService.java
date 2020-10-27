package com.rptt.model;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

public class TntService {
	TenantDAO_interface dao;

	public TntService() {
		dao = new TntDAO();
	}

	public TntVO addTnt(String tnt_email, String tnt_acc, String tnt_pwd, String tnt_id, String tnt_name,
			Date tnt_birth, Boolean tnt_sex, String tnt_mobile, String tnt_city, String tnt_dist, String tnt_add,
			byte[] tnt_pic) {
		//
		TntVO tntVO = new TntVO();
//		tntVO.setTnt_no(tnt_no);
		tntVO.setTnt_email(tnt_email);
		tntVO.setTnt_acc(tnt_acc);
		tntVO.setTnt_pwd(tnt_pwd);
		tntVO.setTnt_id(tnt_id);
		tntVO.setTnt_name(tnt_name);
		tntVO.setTnt_birth(tnt_birth);
		tntVO.setTnt_sex(tnt_sex);
		tntVO.setTnt_mobile(tnt_mobile);
		tntVO.setTnt_city(tnt_city);
		tntVO.setTnt_dist(tnt_dist);
		tntVO.setTnt_add(tnt_add);
		tntVO.setTnt_pic(tnt_pic);
		dao.insert_profile(tntVO);

		return tntVO;
	}

	public TntVO updateTntProfile(String tnt_no, String tnt_email, String tnt_acc, String tnt_pwd, String tnt_id,
			String tnt_name, Date tnt_birth, Boolean tnt_sex, String tnt_mobile, String tnt_city, String tnt_dist,
			String tnt_add, byte[] tnt_pic, int tnt_status) {

		TntVO tntVO = new TntVO();
		tntVO.setTnt_no(tnt_no);
		tntVO.setTnt_email(tnt_email);
		tntVO.setTnt_acc(tnt_acc);
		tntVO.setTnt_pwd(tnt_pwd);
		tntVO.setTnt_id(tnt_id);
		tntVO.setTnt_name(tnt_name);
		tntVO.setTnt_birth(tnt_birth);
		tntVO.setTnt_sex(tnt_sex);
		tntVO.setTnt_mobile(tnt_mobile);
		tntVO.setTnt_city(tnt_city);
		tntVO.setTnt_dist(tnt_dist);
		tntVO.setTnt_add(tnt_add);
		tntVO.setTnt_pic(tnt_pic);
		tntVO.setTnt_status(tnt_status);
		dao.update_profile(tntVO);

		return tntVO;
	}

	public TntVO updateTntVrf(byte[] tnt_id_picf, byte[] tnt_id_picb, byte[] tnt_id_pic2, Timestamp tnt_id_uploadtime,
			int tnt_id_isupload, int tnt_id_result, String tnt_id_disapprove, Timestamp tnt_id_vrftime) {

		TntVO tntVO = new TntVO();

		tntVO.setTnt_id_picf(tnt_id_picf);
		tntVO.setTnt_id_picb(tnt_id_picb);
		tntVO.setTnt_id_pic2(tnt_id_pic2);
		tntVO.setTnt_id_uploadtime(tnt_id_uploadtime);
		tntVO.setTnt_id_isupload(tnt_id_isupload);
		tntVO.setTnt_id_result(tnt_id_result);
		tntVO.setTnt_id_disapprove(tnt_id_disapprove);
		tntVO.setTnt_id_vrftime(tnt_id_vrftime);
		dao.update_vrf(tntVO);

		return tntVO;
	}

	public List<TntVO> getAllVrf(Integer Number, Integer Number2) {
		return dao.getAll_vrf(Number, Number2);
	}

	public List<TntVO> getUnvrf(String Number) {
		return dao.get_unvrf(Number);
	}

	public List<TntVO> getUnvrf_Unresult(Integer Number, Integer Number2) {
		return dao.getUnvrf_Unresult(Number, Number2);
	}

	public TntVO passVrf(String tnt_no, Integer tnt_id_result, String emp_no) {
		TntVO tntVO = new TntVO();
		tntVO.setTnt_no(tnt_no);
		tntVO.setEmp_no(emp_no);
		tntVO.setTnt_id_result(tnt_id_result);
		dao.pass_Vrf(tntVO);
		return tntVO;
	}

	public TntVO failVrf(String tnt_no, Integer tnt_id_result, String emp_no, String tnt_id_disapprove,
			Integer tnt_id_isupload) {
		TntVO tntVO = new TntVO();
		tntVO.setTnt_no(tnt_no);
		tntVO.setEmp_no(emp_no);
		tntVO.setTnt_id_result(tnt_id_result);
		tntVO.setTnt_id_disapprove(tnt_id_disapprove);
		tntVO.setTnt_id_isupload(tnt_id_isupload);
		dao.fail_Vrf(tntVO);
		return tntVO;
	}

	public TntVO getVrfTntPic(String tnt_no) {
		return dao.findByPK_pic(tnt_no);
	}

	public List<TntVO> getTnt(String Number) {
		return dao.findByNo(Number);
	}

	public TntVO getEmail(String tnt_no) {
		return dao.findEmail(tnt_no);
	}

	public TntVO getOneTnt(String tnt_no) {
		return dao.findByPK_profile(tnt_no);
	}

	public TntVO updateTntAuth(String tnt_no, Integer tnt_reported_count, Integer tnt_auth_chat, Integer tnt_auth_res,
			Integer tnt_auth_cmt, Integer tnt_auth_rpt) {
  
		TntVO tntVO = new TntVO();
		tntVO.setTnt_no(tnt_no);		
		tntVO.setTnt_reported_count(tnt_reported_count);		
		tntVO.setTnt_auth_chat(tnt_auth_chat);	
		tntVO.setTnt_auth_res(tnt_auth_res);		
		tntVO.setTnt_auth_cmt(tnt_auth_cmt);
		tntVO.setTnt_auth_rpt(tnt_auth_rpt);
		dao.update_auth(tntVO);
	
		return tntVO;
		
	}

}
