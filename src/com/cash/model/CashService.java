package com.cash.model;

import java.sql.Date;
import java.util.List;

import com.google.gson.Gson;


public class CashService {
	CashDAO_interface dao;

	public CashService() {
		dao = new CashDAO();
	}
	
	public List<CashVO> getOneCashlogs(String mem_no) {
		return dao.findByMemNo_Cashlogs(mem_no);
	}
	
	public String getOneCashlogs_query(String mem_no,String con_status) {
		List<CashVO> cashlog=dao.findByMemNo_Cashlogs(mem_no, con_status);
		 Gson gson= new Gson();
		 return gson.toJson(cashlog);
	}
	
	public String getOneCashlogs_query(String mem_no,String con_status, String cash_inout) {
		List<CashVO> cashlog=dao.findByMemNo_Cashlogs(mem_no,con_status, cash_inout);
		 Gson gson= new Gson();
		 return gson.toJson(cashlog);
	}
	
	public String getOneCashlogs_query(String mem_no, String cash_inout,  String cash_type, String con_status) {
		List<CashVO> cashlog=dao.findByMemNo_Cashlogs(mem_no, cash_inout, cash_type, con_status);
		 Gson gson= new Gson();
		 return gson.toJson(cashlog);
	}

	// 沒有合約編號、沒有帳單編號 -- 自增主鍵值
	public String addCash(Date cash_date, String mem_no, String cash_inout, String cash_type, Integer cash_amount, Integer cash_status) {
		System.out.println("Service addCash");
		CashVO cashVO = new CashVO();
		cashVO.setCash_date(cash_date);
		cashVO.setMem_no(mem_no);
		cashVO.setCash_inout(cash_inout);
		cashVO.setCash_type(cash_type);
		cashVO.setCash_amount(cash_amount);
		cashVO.setCash_status(cash_status);

		String cash_no = dao.insert_cash(cashVO);

		return cash_no;
	}

	// 全部
	public CashVO addCash(Date cash_date, String mem_no, String cash_inout, String cash_type, Integer cash_amount,
			String con_no, String rec_no, Integer cash_status) {

		CashVO cashVO = new CashVO();
		cashVO.setCash_date(cash_date);
//			cashVO.setMem_identity(mem_identity);
		cashVO.setMem_no(mem_no);
		cashVO.setCash_inout(cash_inout);
		cashVO.setCash_type(cash_type);
		cashVO.setCash_amount(cash_amount);
		cashVO.setCon_no(con_no);
		cashVO.setRec_no(rec_no);
		cashVO.setCash_status(cash_status);

		dao.insert_cashAll(cashVO);

		return cashVO;
	}

	// 沒有帳單編號
	public CashVO addCash(Date cash_date, String mem_no, String cash_inout, String cash_type, Integer cash_amount,
			String con_no, Integer cash_status) {

		CashVO cashVO = new CashVO();
		cashVO.setCash_date(cash_date);
//		cashVO.setMem_identity(mem_identity);
		cashVO.setMem_no(mem_no);
		cashVO.setCash_inout(cash_inout);
		cashVO.setCash_type(cash_type);
		cashVO.setCash_amount(cash_amount);
		cashVO.setCon_no(con_no);
		cashVO.setCash_status(cash_status);
		dao.insert_cash_Con(cashVO);

		return cashVO;
	}
	
	public String getConNoByRecNo(String rec_no) {
		return dao.findByRec_no(rec_no);
	}
	
	public void updateStatus(CashVO cashVO) {
		dao.update_status(cashVO);
	}
	
	
//	//沒有合約編號、沒有帳單編號
//	public CashVO addCash(Date cash_date, String mem_no, String cash_inout, String cash_type,
//			Integer cash_amount) {
//		//
//		CashVO cashVO = new CashVO();
//		cashVO.setCash_date(cash_date);
////		cashVO.setMem_identity(mem_identity);
//		cashVO.setMem_no(mem_no);
//		cashVO.setCash_inout(cash_inout);
//		cashVO.setCash_type(cash_type);
//		cashVO.setCash_amount(cash_amount);
//
//		dao.insert_cash(cashVO);
//
//		return cashVO;
//	}

}
