package com.tenant_comments.model;

import java.util.List;


public interface Tenant_commentsDAO_interface {
	//�ЪF�s�W�@����ЫȪ�����
	public void lld_insert(Tenant_commentsVO Tenant_commentsVO);
	//�ЫȦ^�ЩЪF������
	public void tnt_insert(Tenant_commentsVO Tenant_commentsVO);

    //�ЪF:�R������/��x:�R������(���|���\)
    //public void delete(Integer tcm_no);
    
    public Tenant_commentsVO findByPrimaryKey(Integer tcm_no);
    //�ЪF:��ܵ�������(����:lld_no)
    public List<Tenant_commentsVO> lld_getAll(Integer lld_no);
    //�Ы�:��ܨíp������P����(����:tnt_no)/
    public List<Tenant_commentsVO> tnt_getAll(Integer tnt_no);
}
