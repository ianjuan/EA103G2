package com.cont.model;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConDAO {
	private static DataSource ds =null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT_CONTRACT_STMT =
			"INSERT INTO CONTRACT(CON_NO, APL_NO, TNT_NO, HOS_NO, CON_LLD_SIGN, CON_LLD_SIGNTIME, CON_TNT_SIGH, "
			+ "CON_TNT_SIGNTIME, CON_DATE, CON_CHE_DATE, HOS_DEP, CON_DEP_STA, CON_CHKDATE," 
			+ "CON_COMCHKDATE, CON_CHK_STA, CON_CHR_FEE, CON_CHR_ITM, CON_IS_CHR, CON_RENT_AGN," 
			+ "CON_BILL_PAID, CON_LASTB_PDATE, CON_DEP_BKDATE, CON_OUT_NORMAL, CON_STA) VALUES "
			+ "('HOS' || lpad(SEQ_HOS_NO.NEXTVAL, 6, '0'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?"
			+ "?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT CON_NO, APL_NO, TNT_NO, HOS_NO, CON_LLD_SIGN, to_char(CON_LLD_SIGNTIME, 'yyyy-mm-dd'), CON_TNT_SIGH, " 
			+ "to_char(CON_TNT_SIGNTIME, 'yyyy-mm-dd'), to_char(CON_DATE, 'yyyy-mm-dd'), to_char(CON_CHE_DATE, 'yyyy-mm-dd'), HOS_DEP, CON_DEP_STA, to_char(CON_CHKDATE, 'yyyy-mm-dd'), " 
			+ "to_char(CON_COMCHKDATE, 'yyyy-mm-dd'), CON_CHK_STA, CON_CHR_FEE, CON_CHR_ITM, CON_IS_CHR, CON_RENT_AGN,"  
			+ "CON_BILL_PAID, to_char(CON_LASTB_PDATE, 'yyyy-mm-dd'), to_char(CON_DEP_BKDATE, 'yyyy-mm-dd'), CON_OUT_NORMAL, CON_STA "
			+ "FROM CONTRACT ORDER BY CON_NO";
	
	private static final String GET_ONE_STMT = 
			"SELECT CON_NO, APL_NO, TNT_NO, HOS_NO, CON_LLD_SIGN, to_char(CON_LLD_SIGNTIME, 'yyyy-mm-dd'), CON_TNT_SIGH, " 
			+ "to_char(CON_TNT_SIGNTIME, 'yyyy-mm-dd'), to_char(CON_DATE, 'yyyy-mm-dd'), to_char(CON_CHE_DATE, 'yyyy-mm-dd'), HOS_DEP, CON_DEP_STA, to_char(CON_CHKDATE, 'yyyy-mm-dd'), " 
			+ "to_char(CON_COMCHKDATE, 'yyyy-mm-dd'), CON_CHK_STA, CON_CHR_FEE, CON_CHR_ITM, CON_IS_CHR, CON_RENT_AGN,"  
			+ "CON_BILL_PAID, to_char(CON_LASTB_PDATE, 'yyyy-mm-dd'), to_char(CON_DEP_BKDATE, 'yyyy-mm-dd'), CON_OUT_NORMAL, CON_STA "
			+ "FROM CONTRACT WHERE CON_NO";
	
	private static final String UPDATE_CONTRACT_STMT =
			"UPDATE CONTRACT SET APL_NO = ?, TNT_NO = ?, HOS_NO = ?, CON_LLD_SIGN = ?, CON_LLD_SIGNTIME = ?, CON_TNT_SIGH = ?, "  
			+ "CON_TNT_SIGNTIME = ?, CON_DATE = ?, CON_CHE_DATE = ?, HOS_DEP = ?, CON_DEP_STA = ?, CON_CHKDATE = ?, "  
			+ "CON_COMCHKDATE = ?, CON_CHK_STA = ?, CON_CHR_FEE = ?, CON_CHR_ITM = ?, CON_IS_CHR = ?, CON_RENT_AGN = ?, "  
			+ "CON_BILL_PAID = ?, CON_LASTB_PDATE = ?, CON_DEP_BKDATE = ?, CON_OUT_NORMAL = ?, CON_STA = ?";
	
	private static final String DELETE = 
			"DELETE FROM CON_NO WHERE CON_NO = ?";

}
