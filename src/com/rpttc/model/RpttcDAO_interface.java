package com.rpttc.model;

import java.util.List;
import com.rpttc.model.RpttcVO;

public interface RpttcDAO_interface {
	public void insert(RpttcVO rpttcVo);
	public void update(RpttcVO rpttcVo);
	public void updateEmp(RpttcVO rpttcVo);
	public void assignEmp(RpttcVO rpttcVo);
	public void saveNote(RpttcVO rpttcVo);
	public void fail(RpttcVO rpttcVo);
	public void delete(String rpttc_no);
    public RpttcVO findByPrimaryKey(String rpttc_no);
    public List<RpttcVO> findByNo(String Number);
    public List<RpttcVO> getAll();
}
