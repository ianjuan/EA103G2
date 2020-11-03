package com.landlord_comments.model;

import java.util.List;
import java.util.Set;



public class Landlord_commentsService {
	private Landlord_commentsDAO_interface dao;
	
	public Landlord_commentsService() {
		dao = new Landlord_commentsDAO();
	}
	
	public Landlord_commentsVO addLcm(String tnt_no, String lld_no, Integer lcm_clean, Integer lcm_commut, Integer lcm_satisfy, String lcm_commet, java.sql.Date lcm_time) {
		Landlord_commentsVO landlord_commentsVO = new Landlord_commentsVO();
		
		landlord_commentsVO.setTnt_no(tnt_no);
		landlord_commentsVO.setLld_no(lld_no);
		landlord_commentsVO.setLcm_clean(lcm_clean);
		landlord_commentsVO.setLcm_commut(lcm_commut);
		landlord_commentsVO.setLcm_satisfy(lcm_satisfy);
		landlord_commentsVO.setLcm_commet(lcm_commet);
		landlord_commentsVO.setLcm_time(lcm_time);
		dao.tnt_insert(landlord_commentsVO);
		
		return landlord_commentsVO;
		
	}
	
	public Landlord_commentsVO replyLcm(String lcm_no, String lcm_respon) {
		Landlord_commentsVO landlord_commentsVO = new Landlord_commentsVO();
		
		landlord_commentsVO.setLcm_no(lcm_no);
		landlord_commentsVO.setLcm_respon(lcm_respon);
		dao.lld_update(landlord_commentsVO);
		
		return landlord_commentsVO;
		
	}

	public Landlord_commentsVO getOneLcm(String lcm_no) {
		return dao.findByPrimaryKey(lcm_no);
	}
	
	public List<Landlord_commentsVO> getAllbyTnt(String tnt_no){
		return dao.tnt_getAll(tnt_no);
	}
	
	public List<Landlord_commentsVO> getAllbyTnt_lld(String tnt_no, String lld_no){
		return dao.tnt_getAllByLld(tnt_no, lld_no);
	}
	
	
	public List<Landlord_commentsVO> getAllbyLld(String lld_no){
		return dao.lld_getAll(lld_no);
	}
	
	public Landlord_commentsVO updateLcm(String lcm_no, String lcm_commet) {
		Landlord_commentsVO landlord_commentsVO = new Landlord_commentsVO();
		landlord_commentsVO.setLcm_no(lcm_no);
		landlord_commentsVO.setLcm_commet(lcm_commet);
		dao.tnt_update(landlord_commentsVO);
		
		return landlord_commentsVO;
	}

	public Set<String> getAllLld_no(String tnt_no){
		return dao.getAll_lld_no(tnt_no);
		
	}



}
