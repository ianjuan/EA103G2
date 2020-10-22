package com.tenant_comments.model;

import java.util.List;
import java.util.Set;



public class Tenant_commentsService {
	private Tenant_commentsDAO_interface dao;
	
	
	public Tenant_commentsService() {
		dao = new Tenant_commentsJDBCDAO();
	}
	
	public Tenant_commentsVO addTcm(String tnt_no, String lld_no, Integer tcm_clean, Integer tcm_commut, Integer tcm_satisfy, String tcm_commet, java.sql.Date tcm_time) {
		Tenant_commentsVO tenant_commentsVO = new Tenant_commentsVO();
		
		tenant_commentsVO.setTnt_no(tnt_no);
		tenant_commentsVO.setLld_no(lld_no);
		tenant_commentsVO.setTcm_clean(tcm_clean);
		tenant_commentsVO.setTcm_commut(tcm_commut);
		tenant_commentsVO.setTcm_satisfy(tcm_satisfy);
		tenant_commentsVO.setTcm_commet(tcm_commet);
		tenant_commentsVO.setTcm_time(tcm_time);
		dao.lld_insert(tenant_commentsVO);
		System.out.println("偶有道service");
		return tenant_commentsVO;
		
	}
	
	public Tenant_commentsVO replyTcm(String tcm_no, String tcm_respon) {
		Tenant_commentsVO tenant_commentsVO = new Tenant_commentsVO();
		
		tenant_commentsVO.setTcm_no(tcm_no);
		tenant_commentsVO.setTcm_respon(tcm_respon);
		dao.tnt_insert(tenant_commentsVO);
		
		return tenant_commentsVO;
		
	}
	
	public Set<String> getAllTntByLld(String lld_no){
		return dao.lld_getAllTnt(lld_no);
	}
	
	public Tenant_commentsVO getOneTcm(String tcm_no) {
		return dao.findByPrimaryKey(tcm_no);
	}
	
	public List<Tenant_commentsVO> getAllbyLld(String lld_no){
		return dao.lld_getAll(lld_no);
	}
	
	public List<Tenant_commentsVO> getAllbyTnt(String tnt_no){
		return dao.tnt_getAll(tnt_no);
	}
	
	public Tenant_commentsVO updateTcm(String tcm_no, String tcm_commet) {
		Tenant_commentsVO tenant_commentsVO = new Tenant_commentsVO();
		tenant_commentsVO.setTcm_no(tcm_no);
		tenant_commentsVO.setTcm_commet(tcm_commet);
		dao.lld_update(tenant_commentsVO);
		
		return tenant_commentsVO;
	}

	public List<Tenant_commentsVO> getAllbyLld_tnt(String tnt_no, String lld_no) {
		return dao.lld_getAllTntTcm(tnt_no, lld_no);
	}
	
	
	
	

	
}
	