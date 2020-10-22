package com.house_comments.model;

import java.util.List;
import java.util.Set;

public class House_commentsService {
		private House_commentsDAO_interface dao;
		
		public House_commentsService() {
			dao = new House_commentsJDBCDAO();
		}
		
		
	
		public House_commentsVO addHcm(String tnt_no, String hos_no, Integer hcm_eqpmt, Integer hcm_convmt, Integer hcm_neibor, String hcm_commnt, java.sql.Date hcm_time) {
			House_commentsVO house_commentsVO = new House_commentsVO();
			
			house_commentsVO.setTnt_no(tnt_no);
			house_commentsVO.setHos_no(hos_no);
			house_commentsVO.setHcm_eqpmt(hcm_eqpmt);
			house_commentsVO.setHcm_convmt(hcm_convmt);
			house_commentsVO.setHcm_neibor(hcm_neibor);
			house_commentsVO.setHcm_commnt(hcm_commnt);
			house_commentsVO.setHcm_time(hcm_time);
			dao.tnt_insert(house_commentsVO);
			
			return house_commentsVO;
			
		}
		
		public House_commentsVO replyHcm(String hcm_no, String hcm_respon) {
			House_commentsVO house_commentsVO = new House_commentsVO();
			
			house_commentsVO.setHcm_no(hcm_no);
			house_commentsVO.setHcm_respon(hcm_respon);
			dao.lld_update(house_commentsVO);
			
			return house_commentsVO;
			
		}

		public House_commentsVO getOneHcm(String hcm_no) {
			return dao.findByPrimaryKey(hcm_no);
		}
		
		//後台用
		public List<House_commentsVO> getAllbyTnt(String tnt_no){
			return dao.tnt_getAll(tnt_no);
		}
		//房客用
		public List<House_commentsVO> getAllbyTnt_hos(String hos_no, String tnt_no){
			return dao.tnt_getAllByHos(hos_no, tnt_no);
		}
		
		public List<House_commentsVO> getAllbyHos(String hos_no){
			return dao.hos_getAll(hos_no);
		}


		public Set <String> getAllHos_no(String tnt_no){
			
			return dao.getAll_hos_no(tnt_no);
		}


		//房客增加文字評論
		public House_commentsVO updateHcm(String hcm_no, String hcm_commnt) {
			House_commentsVO house_commentsVO = new House_commentsVO();
			
			house_commentsVO.setHcm_no(hcm_no);
			house_commentsVO.setHcm_commnt(hcm_commnt);
			dao.tnt_update(house_commentsVO);
			
			return house_commentsVO;
		}
		


	}

