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

	public List<TntVO> getAllVrf(Integer Number, Integer Number2) {
		return dao.getAll_vrf(Number, Number2);
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

	public TntVO updateTntAuth(String tnt_no, Integer tnt_reported_count, Integer tnt_auth_chat, Integer tnt_auth_res,
			Integer tnt_auth_cmt, Integer tnt_auth_rpt, Integer tnt_auth_live) {

		TntVO tntVO = new TntVO();
		tntVO.setTnt_no(tnt_no);
		tntVO.setTnt_reported_count(tnt_reported_count);
		tntVO.setTnt_auth_chat(tnt_auth_chat);
		tntVO.setTnt_auth_res(tnt_auth_res);
		tntVO.setTnt_auth_cmt(tnt_auth_cmt);
		tntVO.setTnt_auth_rpt(tnt_auth_rpt);
		tntVO.setTnt_auth_live(tnt_auth_live);
		dao.update_auth(tntVO);

		return tntVO;

	}

	public TntVO getOneTntProfile(String tnt_no) {
		return dao.findByPK_profile(tnt_no);
	}

	// 以下只是懶得再對房東的vo編輯的偷吃步
	public TntVO getOneLandlordProfile(String tnt_no) {
		return dao.findByPK_profile1(tnt_no);
	}

}
