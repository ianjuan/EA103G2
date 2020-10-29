package com.cash.model;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

public class CashService {
	CashDAO_interface dao;

	public CashService() {
		dao = new CashDAO();
	}
	
	//全部
	public CashVO addCash(Date cash_date, Integer mem_identity, String mem_no, Integer cash_inout, String cash_type,
			Integer cash_amout, String con_no, String rec_no) {
		
		CashVO cashVO = new CashVO();
		cashVO.setCash_date(cash_date);
		cashVO.setMem_identity(mem_identity);
		cashVO.setMem_no(mem_no);
		cashVO.setCash_inout(cash_inout);
		cashVO.setCash_type(cash_type);
		cashVO.setCash_amout(cash_amout);
		cashVO.setCon_no(con_no);
		cashVO.setRec_no(rec_no);

		dao.insert_cashAll(cashVO);

		return cashVO;
	}
	
	//沒有合約編號、沒有帳單編號
	public CashVO addCash(Date cash_date, Integer mem_identity, String mem_no, Integer cash_inout, String cash_type,
			Integer cash_amout) {
		//
		CashVO cashVO = new CashVO();
		cashVO.setCash_date(cash_date);
		cashVO.setMem_identity(mem_identity);
		cashVO.setMem_no(mem_no);
		cashVO.setCash_inout(cash_inout);
		cashVO.setCash_type(cash_type);
		cashVO.setCash_amout(cash_amout);

		dao.insert_cash(cashVO);

		return cashVO;
	}

	//沒有帳單編號
	public CashVO addCash(Date cash_date, Integer mem_identity, String mem_no, Integer cash_inout, String cash_type,
			Integer cash_amout, String con_no) {
		
		CashVO cashVO = new CashVO();
		cashVO.setCash_date(cash_date);
		cashVO.setMem_identity(mem_identity);
		cashVO.setMem_no(mem_no);
		cashVO.setCash_inout(cash_inout);
		cashVO.setCash_type(cash_type);
		cashVO.setCash_amout(cash_amout);
		cashVO.setCon_no(con_no);

		dao.insert_cash_Con(cashVO);

		return cashVO;
	}
	

}
