package com.rptrp.model;


import java.util.List;

public class RptrpService {

	private RptrpDAO_interface dao;

	public RptrpService() {
		dao = new RptrpJNDIDAO();
	}

	public RptrpVO addRptrp(String rptrp_no, String rptr_no, byte[] rptrp_pic) {

		RptrpVO rptrpVO = new RptrpVO();
		rptrpVO.setRptrp_no(rptrp_no);
		rptrpVO.setRptr_no(rptr_no);
		rptrpVO.setRptrp_pic(rptrp_pic);
		dao.insert(rptrpVO);
		return rptrpVO;
	}

	public RptrpVO updateRptrp(String rptrp_no, String rptr_no, byte[] rptrp_pic) {

		RptrpVO rptrpVO = new RptrpVO();
		rptrpVO.setRptrp_no(rptrp_no);
		rptrpVO.setRptr_no(rptr_no);
		rptrpVO.setRptrp_pic(rptrp_pic);
		return rptrpVO;
	}

	public void deleteRptrp(String rptrp_no) {
		dao.delete(rptrp_no);
	}

	public RptrpVO getOneRptrp(String rptrp_no) {
		return dao.findByPrimaryKey(rptrp_no);
	}

	public List<RptrpVO> getAllRptrp() {
		return dao.getAll();
	}

	public List<RptrpVO> getRptrp(String rptr_no) {
		return dao.findByRptr(rptr_no);
	}

}
