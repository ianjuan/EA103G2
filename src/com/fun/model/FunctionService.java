package com.fun.model;
import java.util.List;


public class FunctionService {
	private FunctionDAO_interface dao;
	public FunctionService() {
		dao = new FunctionDAO();
	}
	public FunctionVO getOneFun(String fun_no) {
		return dao.findByPrimaryKey(fun_no);
	}

	public List<FunctionVO> getAll() {
		return dao.getAll();
	}
	
	public FunctionVO addFun(String fun_no, String fun_name) {

		FunctionVO funVO = new FunctionVO();

		funVO.setFun_no(fun_no);
		funVO.setFun_name(fun_name);
		dao.insert(funVO);
		return funVO;
	}

	public void updateFun() {

	}
}
