package com.tenant_comments.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Tenant_commentsJDBCDAO implements Tenant_commentsDAO_interface{
	String driver="oracle.jdbc.driver.OracleDriver";
	String url="jdbc:oracle:thin:@localhost:1521:XE";
	String userid="G2DB";
	String passwd="123456";
	
	//房東評價房客
	private static final String LLD_INSERT_STMT="INSERT INTO TENANT_COMMENTS (TCM_NO, LLD_NO, TNT_NO, TCM_CLEAN, TCM_COMMUT, TCM_SATISFY, TCM_COMMNT, TCM_TIME) VALUES(TCM_NO_ID.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";
	//房客回覆
	private static final String TNT_RESP_STMT="INSERT INTO TENANT_COMMENTS (TCM_RESPON) VALUES (?) WHERE TCM_NO=?";
	//用 一筆 評論編號 找評分與評論 
	private static final String GET_ONE_STMT="SELECT TCM_CLEAN, TCM_COMMUT, TCM_SATISFY, TCM_COMMNT, to_char(TCM_TIME,'yyyy-mm-dd') TCM_TIME FROM TENANT_COMMENTS WHERE TCM_NO=?";
	//取得該房東評論的所有房客評論(用評論日期推是屬於入住前，入住中，退房後)+還需顯示房客的名子:顯示於於房東的評價紀錄表
	private static final String GET_ALL_LLD_TNT_COM_STMT = "SELECT TNT_NO, TCM_CLEAN, TCM_COMMUT, TCM_SATISFY, TCM_COMMNT, to_char(TCM_TIME,'yyyy-mm-dd') TCM_TIME FROM TENANT_COMMENTS WHERE LLD_NO=?";
	//取得與房客相關的所有評論(新到舊)+還須加上房東名稱:顯示於房客的會員資料
	private static final String GET_ALL_TNT_TNT_COM_STMT="SELECT LLD_NO, TCM_CLEAN, TCM_COMMUT,  TCM_SATISFY, TCM_COMMNT, to_char(TCM_TIME,'yyyy-mm-dd') TCM_TIME FROM TENANT_COMMENTS WHERE TNT_NO=? ORDER BY TCM_TIME DESC";
	

	@Override
	public void lld_insert(Tenant_commentsVO Tenant_commentsVO) {
		Connection con = null;
		PreparedStatement pstmt=null;

		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(LLD_INSERT_STMT);
			
			pstmt.setString(1, Tenant_commentsVO.getLld_no());
			pstmt.setString(2, Tenant_commentsVO.getTnt_no());
			pstmt.setInt(3, Tenant_commentsVO.getTcm_clean());
			pstmt.setInt(4, Tenant_commentsVO.getTcm_commut());
			pstmt.setInt(5, Tenant_commentsVO.getTcm_satisfy());
			pstmt.setString(6, Tenant_commentsVO.getTcm_commnt());
			pstmt.setDate(7, Tenant_commentsVO.getTcm_time());
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load Tenant_comments database driver."+e.getMessage());
		} catch(SQLException e) {
			throw new RuntimeException("Couldn't load  Tenant_comments database error occured."+e.getMessage());
		} finally {
			if(pstmt!=null) {
				if(pstmt!=null) {
					try {
						pstmt.close();
					} catch (SQLException e) {
						
						e.printStackTrace(System.err);
					}
			
				}
				if(con !=null) {
					try {
						con.close();
					} catch (SQLException e) {
						
						e.printStackTrace(System.err);
					}
				}
			}
		}
	}
		

	@Override
	public void tnt_insert(Tenant_commentsVO Tenant_commentsVO) {
		Connection con = null;
		PreparedStatement pstmt=null;

		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(TNT_RESP_STMT);
			
			pstmt.setString(1, Tenant_commentsVO.getTcm_respon());
		}	catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load  Tenant_comments database driver."+e.getMessage());
		} catch(SQLException e) {
			throw new RuntimeException("Couldn't load  Tenant_comments database error occured."+e.getMessage());
		} finally {
			if(pstmt!=null) {
				if(pstmt!=null) {
					try {
						pstmt.close();
					} catch (SQLException e) {
						
						e.printStackTrace(System.err);
					}
			
				}
				if(con !=null) {
					try {
						con.close();
					} catch (SQLException e) {
						
						e.printStackTrace(System.err);
					}
				}
			}
	}
	
}

	@Override
	public Tenant_commentsVO findByPrimaryKey(Integer tcm_no) {
		Tenant_commentsVO Tenant_commentsVO = null;
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, tcm_no);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Tenant_commentsVO = new Tenant_commentsVO();
				Tenant_commentsVO.setTcm_clean(rs.getInt("TCM_EQPMT"));
				Tenant_commentsVO.setTcm_commut(rs.getInt("TCM_TIDY"));
				Tenant_commentsVO.setTcm_satisfy(rs.getInt("TCM_CONVNT"));
				Tenant_commentsVO.setTcm_commnt(rs.getString("HTCM_COMMNT"));
				Tenant_commentsVO.setTcm_time(rs.getDate("TCM_TIME"));
				Tenant_commentsVO.setTcm_respon(rs.getString("TCM_RESPON"));
			}	
		} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load Tenant_comments database driver. "
						+ e.getMessage());
				// Handle any SQL errors
			} catch (SQLException se) {
				throw new RuntimeException("A database from Tenant_comments error occured. "
						+ se.getMessage());
				// Clean up JDBC resources
			} finally {
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException se) {
						se.printStackTrace(System.err);
					}
				}
				if (pstmt != null) {
					try {
						pstmt.close();
					} catch (SQLException se) {
						se.printStackTrace(System.err);
					}
				}
				if (con != null) {
					try {
						con.close();
					} catch (Exception e) {
						e.printStackTrace(System.err);
					}
				}
			}
		
		
		return Tenant_commentsVO;
	}

		

	@Override
	public List<Tenant_commentsVO> lld_getAll(Integer lld_no) {
		List<Tenant_commentsVO> list = new ArrayList<Tenant_commentsVO>();
		Tenant_commentsVO Tenant_commentsVO = null;
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_LLD_TNT_COM_STMT);
			
			pstmt.setInt(1, lld_no);
			
			rs = pstmt.executeQuery();
		
			while(rs.next()) {
				Tenant_commentsVO = new Tenant_commentsVO();
				Tenant_commentsVO.setTnt_no(rs.getString("TNT_NO"));
				Tenant_commentsVO.setTcm_clean(rs.getInt("TCM_CLEAN"));
				Tenant_commentsVO.setTcm_commut(rs.getInt("TCM_COMMUT"));
				Tenant_commentsVO.setTcm_satisfy(rs.getInt("TCM_SATISFY"));
				Tenant_commentsVO.setTcm_commnt(rs.getString("TCM_COMMNT"));
				Tenant_commentsVO.setTcm_time(rs.getDate("TCM_TIME"));
				Tenant_commentsVO.setTcm_respon(rs.getString("TCM_RESPON"));
				list.add(Tenant_commentsVO);
			}	
		} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load Tenant_comments database driver. "
						+ e.getMessage());
				// Handle any SQL errors
			} catch (SQLException se) {
				throw new RuntimeException("A database Tenant_comments error occured. "
						+ se.getMessage());
				// Clean up JDBC resources
			} finally {
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException se) {
						se.printStackTrace(System.err);
					}
				}
				if (pstmt != null) {
					try {
						pstmt.close();
					} catch (SQLException se) {
						se.printStackTrace(System.err);
					}
				}
				if (con != null) {
					try {
						con.close();
					} catch (Exception e) {
						e.printStackTrace(System.err);
					}
				}
			}
		return list;
	}

	@Override
	public List<Tenant_commentsVO> tnt_getAll(Integer tnt_no) {
		List<Tenant_commentsVO> list = new ArrayList<Tenant_commentsVO>();
		Tenant_commentsVO Tenant_commentsVO = null;
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_TNT_TNT_COM_STMT);
			
			pstmt.setInt(1, tnt_no);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Tenant_commentsVO = new Tenant_commentsVO();
				Tenant_commentsVO.setLld_no(rs.getString("LLD_NO"));
				Tenant_commentsVO.setTcm_clean(rs.getInt("TCM_CLEAN"));
				Tenant_commentsVO.setTcm_commut(rs.getInt("TCM_COMMUT"));
				Tenant_commentsVO.setTcm_satisfy(rs.getInt("TCM_SATISFY"));
				Tenant_commentsVO.setTcm_commnt(rs.getString("TCM_COMMNT"));
				Tenant_commentsVO.setTcm_time(rs.getDate("TCM_TIME"));
				Tenant_commentsVO.setTcm_respon(rs.getString("TCM_RESPON"));
				list.add(Tenant_commentsVO);
			}	
		} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load Tenant_comments database driver. "
						+ e.getMessage());
				// Handle any SQL errors
			} catch (SQLException se) {
				throw new RuntimeException("A database Tenant_comments error occured. "
						+ se.getMessage());
				// Clean up JDBC resources
			} finally {
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException se) {
						se.printStackTrace(System.err);
					}
				}
				if (pstmt != null) {
					try {
						pstmt.close();
					} catch (SQLException se) {
						se.printStackTrace(System.err);
					}
				}
				if (con != null) {
					try {
						con.close();
					} catch (Exception e) {
						e.printStackTrace(System.err);
					}
				}
			}
		
		return list;
	}
	
public static void main(String[] args) {
		

	}

}
