package com.repair.model;

import java.util.List;

import com.repair_picture.model.Repair_pictureVO;

public interface RepairDAO_interface {
	//�Ыȷs�W�@����µ�ӽ�
	public RepairVO tnt_insert(RepairVO RepairVO) ;
	//�Ыȧ��y�z
	public void tnt_updateDes(RepairVO RepairVO) ;
	//�ЪF�^���ЫȪ��w�p�ײ��ɶ�
	public void enddate_update(RepairVO RepairVO);
	//�ЪF����µ�i�׬��w���� (tale Repair:Rep_pro=1, table Repair_picture:++)
	public void update_Pro(RepairVO RepairVO);
	//�ЫȽT�{��µ���G (table Repair: REP_TNT_RPT=1 or 2, REP_TNT_RPTTIME=date, REP_END_TIME=date) 
	public void tnt_update(RepairVO RepairVO);
	//�d�߬Y����µ����(��x���|)
    public RepairVO findByPrimaryKey(String rep_no);
    //�ЪF���o�Ҧ���µ����(����:hos_no)
    public List<RepairVO> lld_getAll(String hos_no);
    //�ЫȨ��o�Ҧ���µ����(����:tnt_no)
    public List<RepairVO> tnt_getAll(String tnt_no);
    //增加圖片到修繕申請
	public void insert_pic(Repair_pictureVO repair_pictureVO);
	//取出圖片
	public List<Repair_pictureVO> getPicsNo(String rep_no);




}
