package com.rpth.model;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

public class LldService {
	LandlordDAO_interface dao;

	public LldService() {
		dao = new LldDAO();
	}

	public List<LldVO> getAllVrf(Integer Number, Integer Number2) {
		return dao.getAll_vrf(Number, Number2);
	}

	public List<LldVO> getUnvrf_Unresult(Integer Number, Integer Number2) {
		return dao.getUnvrf_Unresult(Number, Number2);
	}

	public LldVO passVrf(String lld_no, Integer lld_id_result, String emp_no) {
		LldVO lldVO = new LldVO();
		lldVO.setLld_no(lld_no);
		lldVO.setEmp_no(emp_no);
		lldVO.setLld_id_result(lld_id_result);
		dao.pass_Vrf(lldVO);
		return lldVO;
	}

	public LldVO failVrf(String lld_no, Integer lld_id_result, String emp_no, String lld_id_disapprove,
			Integer lld_id_isupload) {
		LldVO lldVO = new LldVO();
		lldVO.setLld_no(lld_no);
		lldVO.setEmp_no(emp_no);
		lldVO.setLld_id_result(lld_id_result);
		lldVO.setLld_id_disapprove(lld_id_disapprove);
		lldVO.setLld_id_isupload(lld_id_isupload);
		dao.fail_Vrf(lldVO);
		return lldVO;
	}

	public LldVO getVrfLldPic(String lld_no) {
		return dao.findByPK_pic(lld_no);
	}

	public List<LldVO> getLld(String Number) {
		return dao.findByNo(Number);
	}

	public LldVO getEmail(String lld_no) {
		return dao.findEmail(lld_no);
	}

	public LldVO updateLldAuth(String lld_no, Integer lld_reported_count, Integer lld_auth_chat, Integer lld_auth_res,
			Integer lld_auth_cmt, Integer lld_auth_rpt, Integer lld_auth_hos) {

		LldVO lldVO = new LldVO();
		lldVO.setLld_no(lld_no);
		lldVO.setLld_reported_count(lld_reported_count);
		lldVO.setLld_auth_chat(lld_auth_chat);
		lldVO.setLld_auth_res(lld_auth_res);
		lldVO.setLld_auth_cmt(lld_auth_cmt);
		lldVO.setLld_auth_rpt(lld_auth_rpt);
		lldVO.setLld_auth_hos(lld_auth_hos);
		dao.update_auth(lldVO);

		return lldVO;

	}

	public LldVO getOneLldProfile(String lld_no) {
		return dao.findByPK_profile(lld_no);
	}

	// 以下只是懶得再對房東的vo編輯的偷吃步
	public LldVO getOneLandlordProfile(String lld_no) {
		return dao.findByPK_profile1(lld_no);
	}

}
