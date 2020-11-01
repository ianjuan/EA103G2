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


	public LldVO getVrfLldPic(String lld_no) {
		return dao.findByPK_pic(lld_no);
	}

	public List<LldVO> getLld(String Number) {
		return dao.findByNo(Number);
	}

	public LldVO getEmail(String hos_no) {
		return dao.findEmail(hos_no);
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

	public LldVO getOneLandlordProfile(String lld_no) {
		return dao.findByPK_profile(lld_no);
	}

	
}
