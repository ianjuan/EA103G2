package com.landlord_comments.model;

import java.util.List;


public interface Landlord_commentsDAO_interface {
	//�Ыȷs�W�@���ЪF����
	public void tnt_insert(Landlord_commentsVO Landlord_commentsVO);
	//�ЪF�^�ЩЫȪ�����
	public void lld_insert(Landlord_commentsVO Landlord_commentsVO);
	
	//�Ы�:�ק����/�ЪF:�^�������A�ק�^��
    //public void update(Landlord_commentsVO Landlord_commentsVO);
	
    //�Ы�:�R������/��x�R������(���|���\)
   // public void delete(Integer lcm_no);
    
    public Landlord_commentsVO findByPrimaryKey(Integer lcm_no);
    //�ЪF:�p�����ܵ����P����(����:lld_no)
    public List<Landlord_commentsVO> lld_getAll(Integer lld_no);
    //�Ы�:��ܵ��׬���(����:tnt_no)
    public List<Landlord_commentsVO> tnt_getAll(Integer tnt_no);
}
