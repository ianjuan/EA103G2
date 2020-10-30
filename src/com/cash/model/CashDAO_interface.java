package com.cash.model;

import java.util.List;

import com.tnt.model.TntVO;

public interface CashDAO_interface {
	
	public void insert_cashAll(CashVO cashVO);
	public String insert_cash(CashVO cashVO);
	public void insert_cash_Con(CashVO cashVO);
	
	public List<CashVO> findByMemNo_Cashlogs(String mem_no);
}
