package com.landlord_comments.model;

import java.util.List;


public interface Landlord_commentsDAO_interface {
	//房客新增一筆房東評價
	public void tnt_insert(Landlord_commentsVO Landlord_commentsVO);
	//房東回覆房客的評價
	public void lld_insert(Landlord_commentsVO Landlord_commentsVO);
	
	//房客:修改評價/房東:回應評價，修改回應
    //public void update(Landlord_commentsVO Landlord_commentsVO);
	
    //房客:刪除評價/後台刪除評價(檢舉成功)
   // public void delete(Integer lcm_no);
    
    public Landlord_commentsVO findByPrimaryKey(Integer lcm_no);
    //房東:計算並顯示評價與評分(條件:lld_no)
    public List<Landlord_commentsVO> lld_getAll(Integer lld_no);
    //房客:顯示評論紀錄(條件:tnt_no)
    public List<Landlord_commentsVO> tnt_getAll(Integer tnt_no);
}
