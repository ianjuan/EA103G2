package com.apl.model;

import java.util.List;
import java.util.Map;

import com.housemanage.model.HouseVO;

public class Con_aplService {

	private Con_aplDAO_interface dao;

	public Con_aplService() {
		dao = new Con_aplDAO();
	}

	public Con_aplVO addCon_apl(String tnt_no, String hos_no, java.sql.Date apl_str, java.sql.Date apl_end,
			java.sql.Date apl_time, Integer apl_status) {

		Con_aplVO con_aplVO = new Con_aplVO();

		con_aplVO.setTnt_no(tnt_no);
		con_aplVO.setHos_no(hos_no);
		con_aplVO.setApl_str(apl_str);
		con_aplVO.setApl_end(apl_end);
		con_aplVO.setApl_time(apl_time);
		con_aplVO.setApl_status(apl_status);
		dao.insert(con_aplVO);

		return con_aplVO;
	}
	
	public Con_aplVO addfromhouse(String tnt_no, String hos_no, java.sql.Date apl_str, java.sql.Date apl_end) {

		Con_aplVO con_aplVO = new Con_aplVO();

		con_aplVO.setTnt_no(tnt_no);
		con_aplVO.setHos_no(hos_no);
		con_aplVO.setApl_str(apl_str);
		con_aplVO.setApl_end(apl_end);
		con_aplVO.setApl_status(0);
		dao.insert(con_aplVO);

		return con_aplVO;
	}

	public Con_aplVO updateCon_apl(String apl_no, String tnt_no, String hos_no, java.sql.Date apl_str,
			java.sql.Date apl_end, java.sql.Date apl_time, Integer apl_status) {

		Con_aplVO con_aplVO = new Con_aplVO();

		con_aplVO.setApl_no(apl_no);
		con_aplVO.setTnt_no(tnt_no);
		con_aplVO.setHos_no(hos_no);
		con_aplVO.setApl_str(apl_str);
		con_aplVO.setApl_end(apl_end);
		con_aplVO.setApl_time(apl_time);
		con_aplVO.setApl_status(apl_status);
		dao.tntUpdate(con_aplVO);

		return con_aplVO;

	}

	public Con_aplVO tntupdateCon_apl(String apl_no, java.sql.Date apl_str, java.sql.Date apl_end) {

		Con_aplVO con_aplVO = new Con_aplVO();

		con_aplVO.setApl_no(apl_no);
		con_aplVO.setApl_str(apl_str);
		con_aplVO.setApl_end(apl_end);
		dao.tntUpdate(con_aplVO);

		return con_aplVO;

	}

	public Con_aplVO lldUpdateCon_apl(String apl_no, Integer apl_status) {

		Con_aplVO con_aplVO = new Con_aplVO();

		con_aplVO.setApl_no(apl_no);
		con_aplVO.setApl_status(apl_status);
		dao.lldUpdate(con_aplVO);

		return con_aplVO;
	}

	public Con_aplVO getOneCon_apl(String apl_no) {
		return dao.findByPrimaryKey(apl_no);
	}
	
	public Con_aplVO getaplbyhos(String hos_no) {
		return dao.getaplbyhos(hos_no);
	}

	public List<Con_aplVO> getAll() {
		return dao.getAll();
	}
	
	public List<Con_aplVO> lldgetAll(String lld_no){
		return dao.lldgetAll(lld_no);
	}
	
	public List<Con_aplVO> tntgetAll(String tnt_no){
		return dao.tntgetAll(tnt_no);
	}
	
	public List<Con_aplVO> hosgetall(String hos_no){
		return dao.hosgetall(hos_no);
	}
	
	public List<HouseVO> getAll(Map<String, String> map) {
		return dao.getAll(map);
	}

	public Con_aplStatus[] getCon_aplStatusAll() {
		return Con_aplStatus.values();
	}

	public String getCon_statusText(Integer num) {
		return Con_aplStatus.findByPrimaryKey(num).getText();
	}
}
