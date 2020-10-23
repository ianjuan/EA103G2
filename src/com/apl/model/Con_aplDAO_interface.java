package com.apl.model;

import java.util.List;
import java.util.Map;

import com.housemanage.model.HouseVO;

public interface Con_aplDAO_interface {
	
		public void insert(Con_aplVO con_aplVO);
		public void update(Con_aplVO con_aplVO);
	    public void tntUpdate(Con_aplVO con_aplVO);
	    public void lldUpdate(Con_aplVO con_aplVO);
	    public void delete(String apl_no);
	    public Con_aplVO findByPrimaryKey(String apl_no);
	    public Con_aplVO getaplbyhos(String hos_no);
	    public List<Con_aplVO> getAll();
	    public List<Con_aplVO> lldgetAll(String lld_no);
	    public List<Con_aplVO> tntgetAll(String tnt_no);
	    public List<HouseVO> getAll(Map<String, String> map);
}
