package com.landlord_comments.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class landlord_commentsJDBCDAO implements Landlord_commentsDAO_interface {

	String driver="oracle.jdbc.driver.OracleDriver";
	String url="jdbc:oracle:thin:@localhost:1521:XE";
	String userid="G2DB";
	String passwd="123456";
	
	//房客評價房東
	private static final String TNT_INSERT_STMT="INSERT INTO LANDLORD_COMMENTS (LCM_NO, TNT_NO, LLD_NO, LCM_CLEAN, LCM_COMMUT, LCM_SATISFY, LCM_COMMNT, LCM_TIME) VALUES(LCM_NO_ID.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";
	//房東回覆
	private static final String LLD_RESP_STMT="INSERT INTO LANDLORD_COMMENTS (LCM_RESPON) VALUES (?) WHERE LCM_NO=?";
	//用 一筆 評論編號 找評分與評論 
	private static final String GET_ONE_STMT="SELECT LCM_CLEAN, LCM_COMMUT,  LCM_SATISFY, LCM_COMMNT, to_char(LCM_TIME,'yyyy-mm-dd') LCM_TIME FROM LANDLORD_COMMENTS WHERE LCM_NO=?";
	//取得該房客評論的所有房東評論(用評論日期推是屬於入住前，入住中，退房後)+還需顯示房東的名子
	private static final String GET_ALL_TNT_LLD_COM_STMT = "SELECT LLD_NO, LCM_CLEAN, LCM_COMMUT, LCM_SATISFY, LCM_COMMNT, to_char(LCM_TIME,'yyyy-mm-dd')  LCM_TIME FROM LANDLORD_COMMENTS WHERE TNT_NO=?";
	//取得與房東相關的所有評論(新到舊)+還須加上房客名稱
	private static final String GET_ALL_LLD_LLD_COM_STMT="SELECT TNT_NO, LCM_CLEAN, LCM_COMMUT,  LCM_SATISFY, LCM_COMMNT, to_char(LCM_TIME,'yyyy-mm-dd') LCM_TIME FROM LANDLORD_COMMENTS WHERE LLD_NO=? ORDER BY LCM_TIME DESC";
	
	
	@Override
	public void tnt_insert(Landlord_commentsVO Landlord_commentsVO) {
		Connection con = null;
		PreparedStatement pstmt=null;

		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(TNT_INSERT_STMT);
			
			pstmt.setString(1, Landlord_commentsVO.getTnt_no());
			pstmt.setString(2, Landlord_commentsVO.getLld_no());
			pstmt.setInt(3, Landlord_commentsVO.getLcm_clean());
			pstmt.setInt(4, Landlord_commentsVO.getLcm_commut());
			pstmt.setInt(5, Landlord_commentsVO.getLcm_satisfy());
			pstmt.setString(6, Landlord_commentsVO.getLcm_commnt());
			pstmt.setDate(7, Landlord_commentsVO.getLcm_time());
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load LANDLORD_COMMENTS database driver."+e.getMessage());
		} catch(SQLException e) {
			throw new RuntimeException("Couldn't load  FROM LANDLORD_COMMENTS database error occured."+e.getMessage());
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
	public void lld_insert(Landlord_commentsVO Landlord_commentsVO) {
		Connection con = null;
		PreparedStatement pstmt=null;

		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(LLD_RESP_STMT);
			
			pstmt.setString(1, Landlord_commentsVO.getLcm_respon());
		}	catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load  Landlord_comments database driver."+e.getMessage());
		} catch(SQLException e) {
			throw new RuntimeException("Couldn't load  Landlord_comments database error occured."+e.getMessage());
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



//	@Override
//	public void delete(Integer lcm_no) {
//		
//		
//	}

	@Override
	public Landlord_commentsVO findByPrimaryKey(Integer lcm_no) {
		Landlord_commentsVO Landlord_commentsVO = null;
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, lcm_no);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Landlord_commentsVO = new Landlord_commentsVO();
				Landlord_commentsVO.setLcm_clean(rs.getInt("HCM_EQPMT"));
				Landlord_commentsVO.setLcm_commut(rs.getInt("HCM_TIDY"));
				Landlord_commentsVO.setLcm_satisfy(rs.getInt("HCM_CONVNT"));
				Landlord_commentsVO.setLcm_commnt(rs.getString("HCM_COMMNT"));
				Landlord_commentsVO.setLcm_time(rs.getDate("HCM_TIME"));
				Landlord_commentsVO.setLcm_respon(rs.getString("LCM_RESPON"));
			}	
		} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load LANDLORD_COMMENTS database driver. "
						+ e.getMessage());
				// Handle any SQL errors
			} catch (SQLException se) {
				throw new RuntimeException("A database from LANDLORD_COMMENTS error occured. "
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
		
		
		return Landlord_commentsVO;
	}

	@Override
	public List<Landlord_commentsVO> lld_getAll(Integer lld_no) {
		List<Landlord_commentsVO> list = new ArrayList<Landlord_commentsVO>();
		Landlord_commentsVO Landlord_commentsVO = null;
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_LLD_LLD_COM_STMT);
			
			pstmt.setInt(1, lld_no);
			
			rs = pstmt.executeQuery();
		
			while(rs.next()) {
				Landlord_commentsVO = new Landlord_commentsVO();
				Landlord_commentsVO.setTnt_no(rs.getString("TNT_NO"));
				Landlord_commentsVO.setLcm_clean(rs.getInt("LCM_CLEAN"));
				Landlord_commentsVO.setLcm_commut(rs.getInt("LCM_COMMUT"));
				Landlord_commentsVO.setLcm_satisfy(rs.getInt("LCM_SATISFY"));
				Landlord_commentsVO.setLcm_commnt(rs.getString("LCM_COMMNT"));
				Landlord_commentsVO.setLcm_time(rs.getDate("LCM_TIME"));
				Landlord_commentsVO.setLcm_respon(rs.getString("LCM_RESPON"));
				list.add(Landlord_commentsVO);
			}	
		} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load LANDLORD_COMMENTS database driver. "
						+ e.getMessage());
				// Handle any SQL errors
			} catch (SQLException se) {
				throw new RuntimeException("A database LANDLORD_COMMENTS error occured. "
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
	public List<Landlord_commentsVO> tnt_getAll(Integer tnt_no) {
		List<Landlord_commentsVO> list = new ArrayList<Landlord_commentsVO>();
		Landlord_commentsVO Landlord_commentsVO = null;
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_TNT_LLD_COM_STMT);
			
			pstmt.setInt(1, tnt_no);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Landlord_commentsVO = new Landlord_commentsVO();
				Landlord_commentsVO.setLld_no(rs.getString("LLD_NO"));
				Landlord_commentsVO.setLcm_clean(rs.getInt("LCM_CLEAN"));
				Landlord_commentsVO.setLcm_commut(rs.getInt("LCM_COMMUT"));
				Landlord_commentsVO.setLcm_satisfy(rs.getInt("LCM_SATISFY"));
				Landlord_commentsVO.setLcm_commnt(rs.getString("LCM_COMMNT"));
				Landlord_commentsVO.setLcm_time(rs.getDate("LCM_TIME"));
				Landlord_commentsVO.setLcm_respon(rs.getString("LCM_RESPON"));
				list.add(Landlord_commentsVO);
			}	
		} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load LANDLORD_COMMENTS database driver. "
						+ e.getMessage());
				// Handle any SQL errors
			} catch (SQLException se) {
				throw new RuntimeException("A database LANDLORD_COMMENTS error occured. "
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
