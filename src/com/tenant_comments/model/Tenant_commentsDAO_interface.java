package com.tenant_comments.model;

import java.util.List;


public interface Tenant_commentsDAO_interface {
	//房東新增一筆對房客的評價
	public void lld_insert(Tenant_commentsVO Tenant_commentsVO);
	//房客回覆房東的評價
	public void tnt_insert(Tenant_commentsVO Tenant_commentsVO);

    //房東:刪除評價/後台:刪除評價(檢舉成功)
    //public void delete(Integer tcm_no);
    
    public Tenant_commentsVO findByPrimaryKey(Integer tcm_no);
    //房東:顯示評價紀錄(條件:lld_no)
    public List<Tenant_commentsVO> lld_getAll(Integer lld_no);
    //房客:顯示並計算評分與評價(條件:tnt_no)/
    public List<Tenant_commentsVO> tnt_getAll(Integer tnt_no);
}
