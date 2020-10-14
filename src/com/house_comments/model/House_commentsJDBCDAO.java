package com.house_comments.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class House_commentsJDBCDAO implements House_commentsDAO_interface {

		String driver="oracle.jdbc.driver.OracleDriver";
		String url="jdbc:oracle:thin:@localhost:1521:XE";
		String userid="G2DB";
		String passwd="123456";
		
		private static final String TNT_INSERT_STMT="INSERT INTO HOUSE_COMMENTS (HCM_NO, TNT_NO, HOS_NO, HCM_EQPMT, HCM_CONVMT, HCM_NEIBOR, HCM_COMMNT, HCM_TIME) VALUES('HCM' || lpad(SEQ_HCM_NO.NEXTVAL, 6, '0'), ?, ?, ?, ?, ?, ?, ?)";
		
		private static final String LLD_RESP_STMT="UPDATE HOUSE_COMMENTS SET HCM_RESPON=?  WHERE HCM_NO=?";

		private static final String GET_ONE_STMT="SELECT HCM_NO, TNT_NO, HOS_NO, HCM_EQPMT, HCM_CONVMT, HCM_NEIBOR, HCM_COMMNT, to_char(HCM_TIME,'yyyy-mm-dd') HCM_TIME, HCM_RESPON FROM HOUSE_COMMENTS WHERE HCM_NO=?";
		
		private static final String TNT_GET_ALL_HCM_STMT = "SELECT HCM_NO, HOS_NO, HCM_EQPMT, HCM_CONVMT, HCM_NEIBOR, HCM_COMMNT, to_char(HCM_TIME,'yyyy-mm-dd') HCM_TIME, HCM_RESPON FROM HOUSE_COMMENTS WHERE TNT_NO=? ORDER BY HCM_NO DESC";
		
		private static final String GET_ALL_HCM_STMT="SELECT HCM_NO, TNT_NO, HOS_NO, HCM_EQPMT, HCM_CONVMT, HCM_NEIBOR, HCM_COMMNT, to_char(HCM_TIME,'yyyy-mm-dd') HCM_TIME, HCM_RESPON FROM HOUSE_COMMENTS WHERE HOS_NO=? ORDER BY HCM_NO DESC";
		
		
	@Override
	public void tnt_insert(House_commentsVO House_commentsVO) {
		Connection con = null;
		PreparedStatement pstmt=null;

		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(TNT_INSERT_STMT);
			
			pstmt.setString(1, House_commentsVO.getTnt_no());
			pstmt.setString(2, House_commentsVO.getHos_no());
			pstmt.setInt(3, House_commentsVO.getHcm_eqpmt());
			pstmt.setInt(4, House_commentsVO.getHcm_convmt());
			pstmt.setInt(5, House_commentsVO.getHcm_neibor());
			pstmt.setString(6, House_commentsVO.getHcm_commnt());
			pstmt.setDate(7, House_commentsVO.getHcm_time());
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load  House_comments database driver."+e.getMessage());
		} catch(SQLException e) {
			throw new RuntimeException("Couldn't load  House_comments database error occured."+e.getMessage());
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
	public void lld_update(House_commentsVO House_commentsVO) {
			Connection con = null;
			PreparedStatement pstmt=null;

			try {
				Class.forName(driver);
				con=DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(LLD_RESP_STMT);
				
				pstmt.setString(1, House_commentsVO.getHcm_respon());
				pstmt.setString(2, House_commentsVO.getHcm_no());
				pstmt.executeUpdate();
				
			}	catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load  House_comments database driver."+e.getMessage());
			} catch(SQLException e) {
				throw new RuntimeException("Couldn't load  House_comments database error occured."+e.getMessage());
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
	public House_commentsVO findByPrimaryKey(String hcm_no) {
		House_commentsVO House_commentsVO = null;
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, hcm_no);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				House_commentsVO = new House_commentsVO();
				House_commentsVO.setHcm_no(rs.getString("HCM_NO"));
				House_commentsVO.setTnt_no(rs.getString("TNT_NO"));
				House_commentsVO.setHos_no(rs.getString("HOS_NO"));
				House_commentsVO.setHcm_eqpmt(rs.getInt("HCM_EQPMT"));
				House_commentsVO.setHcm_convmt(rs.getInt("HCM_CONVMT"));
				House_commentsVO.setHcm_neibor(rs.getInt("HCM_NEIBOR"));
				House_commentsVO.setHcm_commnt(rs.getString("HCM_COMMNT"));
				House_commentsVO.setHcm_time(rs.getDate("HCM_TIME"));
				House_commentsVO.setHcm_respon(rs.getString("HCM_RESPON"));
			}	
		} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load House_comments database driver. "
						+ e.getMessage());
				// Handle any SQL errors
			} catch (SQLException se) {
				throw new RuntimeException("A database House_comments error occured. "
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
			return House_commentsVO;
		}

	
	@Override
	public List<House_commentsVO> tnt_getAll(String tnt_no) {
		List<House_commentsVO> list = new ArrayList<House_commentsVO>();
		House_commentsVO House_commentsVO = null;
		
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(TNT_GET_ALL_HCM_STMT);
			
			pstmt.setString(1, tnt_no);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				House_commentsVO = new House_commentsVO();
				House_commentsVO.setHcm_no(rs.getString("HCM_NO"));
				House_commentsVO.setHos_no(rs.getString("HOS_NO"));
				House_commentsVO.setHcm_eqpmt(rs.getInt("HCM_EQPMT"));
				House_commentsVO.setHcm_convmt(rs.getInt("HCM_CONVMT"));
				House_commentsVO.setHcm_neibor(rs.getInt("HCM_NEIBOR"));
				House_commentsVO.setHcm_commnt(rs.getString("HCM_COMMNT"));
				House_commentsVO.setHcm_time(rs.getDate("HCM_TIME"));
				House_commentsVO.setHcm_respon(rs.getString("HCM_RESPON"));
				list.add(House_commentsVO);
			}	
		} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load House_comments database driver. "
						+ e.getMessage());
				// Handle any SQL errors
			} catch (SQLException se) {
				throw new RuntimeException("A database House_comments error occured. "
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
	public List<House_commentsVO> hos_getAll(String hos_no) {
		List<House_commentsVO> list = new ArrayList<House_commentsVO>();
		House_commentsVO House_commentsVO = null;
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_HCM_STMT);
			
			pstmt.setString(1, hos_no);
			
			rs = pstmt.executeQuery();
		
			while(rs.next()) {
				House_commentsVO = new House_commentsVO();
				House_commentsVO.setHcm_no(rs.getString("HCM_NO"));
				House_commentsVO.setTnt_no(rs.getString("TNT_NO"));
				House_commentsVO.setHos_no(rs.getString("HOS_NO"));
				House_commentsVO.setHcm_eqpmt(rs.getInt("HCM_EQPMT"));
				House_commentsVO.setHcm_convmt(rs.getInt("HCM_CONVMT"));
				House_commentsVO.setHcm_neibor(rs.getInt("HCM_NEIBOR"));
				House_commentsVO.setHcm_commnt(rs.getString("HCM_COMMNT"));
				House_commentsVO.setHcm_time(rs.getDate("HCM_TIME"));
				House_commentsVO.setHcm_respon(rs.getString("HCM_RESPON"));
				list.add(House_commentsVO);
			}	
		} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load House_comments database driver. "
						+ e.getMessage());
				// Handle any SQL errors
			} catch (SQLException se) {
				throw new RuntimeException("A database House_comments error occured. "
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
