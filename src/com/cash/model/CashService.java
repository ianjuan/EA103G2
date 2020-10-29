package com.cash.model;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

public class CashService {
	TenantDAO_interface dao;

	public CashService() {
		dao = new TntDAO();
	}

	public TntVO addTnt(String tnt_email, String tnt_acc, String tnt_pwd, String tnt_id, String tnt_name,
			Date tnt_birth, Boolean tnt_sex, String tnt_mobile, String tnt_city, String tnt_dist, String tnt_add, byte[] tnt_pic) {
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
	
	

}

