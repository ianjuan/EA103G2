package com.repair_picture.model;

import java.util.List;

public interface Repair_pictureDAO_interface {
	//�Ыȷs�W�@�i�ݭת��~���Ӥ��b��µ������
	public void insert(Repair_pictureVO repair_pictureVO);
	//�d�ߤ@����µ�����̪��䤤�@�i�Ϥ�
	public Repair_pictureVO findByPrimaryKey(String reppic_no);
	//���o������µ�������Ҧ���µ�Ӥ�
    public List<Repair_pictureVO> lld_getAll(String rep_no);
}
