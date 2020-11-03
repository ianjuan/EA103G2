package com.landlord_comments.model;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class Landlord_commentsDAO implements Landlord_commentsDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/G2DB");
		}catch(NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String TNT_INSERT_STMT="INSERT INTO LANDLORD_COMMENTS (LCM_NO, TNT_NO, LLD_NO, LCM_CLEAN, LCM_COMMUT, LCM_SATISFY, LCM_COMMET, LCM_TIME) VALUES('LCM' || lpad(SEQ_LCM_NO.NEXTVAL, 6, '0'), ?, ?, ?, ?, ?, ?, ?)";
	
	private static final String LLD_RESP_STMT="UPDATE LANDLORD_COMMENTS SET LCM_RESPON=?  WHERE LCM_NO=?";
	
	private static final String GET_ONE_STMT="SELECT LCM_NO, TNT_NO, LLD_NO, LCM_CLEAN, LCM_COMMUT, LCM_SATISFY, LCM_COMMET, to_char(LCM_TIME,'yyyy-mm-dd') LCM_TIME, LCM_RESPON FROM LANDLORD_COMMENTS WHERE LCM_NO=?";	
	
	private static final String TNT_GET_ALL_lCM_STMT = "SELECT TNT_NO, LCM_NO, LLD_NO, LCM_CLEAN, LCM_COMMUT, LCM_SATISFY, LCM_COMMET, to_char(LCM_TIME,'yyyy-mm-dd') LCM_TIME, LCM_RESPON FROM LANDLORD_COMMENTS WHERE TNT_NO=? ORDER BY LCM_NO DESC";

	private static final String GET_ALL_LCM_STMT="SELECT LCM_NO, TNT_NO, LLD_NO, LCM_CLEAN, LCM_COMMUT,  LCM_SATISFY, LCM_COMMET, to_char(LCM_TIME,'yyyy-mm-dd') LCM_TIME, LCM_RESPON FROM LANDLORD_COMMENTS WHERE LLD_NO=? ORDER BY LCM_TIME DESC";
	
	private static final String TNT_UPD_STMT = "UPDATE LANDLORD_COMMENTS SET LCM_COMMET=? WHERE LCM_NO=?";
	
	private static final String GET_ALL_LLD_STMT = "SELECT LLD_NO FROM LANDLORD_COMMENTS WHERE TNT_NO=?";
	
	private static final String GET_ALL_BY_LLD_STMT="SELECT LCM_NO, TNT_NO, LLD_NO, LCM_CLEAN, LCM_COMMUT,  LCM_SATISFY, LCM_COMMET, to_char(LCM_TIME,'yyyy-mm-dd') LCM_TIME, LCM_RESPON FROM LANDLORD_COMMENTS WHERE LLD_NO=? AND TNT_NO=? ORDER BY LCM_TIME DESC";
	
	@Override
	public void tnt_insert(Landlord_commentsVO Landlord_commentsVO) {
		Connection con = null;
		PreparedStatement pstmt=null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(TNT_INSERT_STMT);
			
			pstmt.setString(1, Landlord_commentsVO.getTnt_no());
			pstmt.setString(2, Landlord_commentsVO.getLld_no());
			pstmt.setInt(3, Landlord_commentsVO.getLcm_clean());
			pstmt.setInt(4, Landlord_commentsVO.getLcm_commut());
			pstmt.setInt(5, Landlord_commentsVO.getLcm_satisfy());
			pstmt.setString(6, Landlord_commentsVO.getLcm_commet());
			pstmt.setDate(7, Landlord_commentsVO.getLcm_time());
			pstmt.executeUpdate();
			
		
		} catch(SQLException e) {
			System.out.println("Couldn't load  FROM LANDLORD_COMMENTS database error occured."+e.getMessage());
			e.printStackTrace(System.err);
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
public void lld_update(Landlord_commentsVO Landlord_commentsVO) {
		Connection con = null;
		PreparedStatement pstmt=null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(LLD_RESP_STMT);
			
			pstmt.setString(1, Landlord_commentsVO.getLcm_respon());
			pstmt.setString(2, Landlord_commentsVO.getLcm_no());
			pstmt.executeUpdate();
			
		
		} catch(SQLException e) {
			System.out.println("Couldn't load LANDLORD_COMMENTS database driver."+e.getMessage());
			e.printStackTrace(System.err);
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
	public Landlord_commentsVO findByPrimaryKey(String lcm_no) {
		Landlord_commentsVO Landlord_commentsVO = null;
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, lcm_no);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Landlord_commentsVO = new Landlord_commentsVO();
				Landlord_commentsVO.setLcm_no(rs.getString("LCM_NO"));
				Landlord_commentsVO.setTnt_no(rs.getString("TNT_NO"));
				Landlord_commentsVO.setLld_no(rs.getString("LLD_NO"));
				Landlord_commentsVO.setLcm_clean(rs.getInt("LCM_CLEAN"));
				Landlord_commentsVO.setLcm_commut(rs.getInt("LCM_COMMUT"));
				Landlord_commentsVO.setLcm_satisfy(rs.getInt("LCM_SATISFY"));
				Landlord_commentsVO.setLcm_commet(rs.getString("LCM_COMMET"));
				Landlord_commentsVO.setLcm_time(rs.getDate("LCM_TIME"));
				Landlord_commentsVO.setLcm_respon(rs.getString("LCM_RESPON"));
			}	
		
			} catch (SQLException se) {
				System.out.println("Couldn't load LANDLORD_COMMENTS database driver."+se.getMessage());
				se.printStackTrace(System.err);
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
	public List<Landlord_commentsVO> lld_getAll(String lld_no) {
		List<Landlord_commentsVO> list = new ArrayList<Landlord_commentsVO>();
		Landlord_commentsVO Landlord_commentsVO = null;
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_LCM_STMT);
			
			pstmt.setString(1, lld_no);
			
			rs = pstmt.executeQuery();
		
			while(rs.next()) {
				Landlord_commentsVO = new Landlord_commentsVO();
				Landlord_commentsVO.setLcm_no(rs.getString("LCM_NO"));
				Landlord_commentsVO.setTnt_no(rs.getString("TNT_NO"));
				Landlord_commentsVO.setLld_no(rs.getString("LLD_NO"));
				Landlord_commentsVO.setLcm_clean(rs.getInt("LCM_CLEAN"));
				Landlord_commentsVO.setLcm_commut(rs.getInt("LCM_COMMUT"));
				Landlord_commentsVO.setLcm_satisfy(rs.getInt("LCM_SATISFY"));
				Landlord_commentsVO.setLcm_commet(rs.getString("LCM_COMMET"));
				Landlord_commentsVO.setLcm_time(rs.getDate("LCM_TIME"));
				Landlord_commentsVO.setLcm_respon(rs.getString("LCM_RESPON"));
				list.add(Landlord_commentsVO);
			}	
		
			} catch (SQLException se) {
				System.out.println("Couldn't load LANDLORD_COMMENTS database driver."+se.getMessage());
				se.printStackTrace(System.err);
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
	public List<Landlord_commentsVO> tnt_getAll(String tnt_no) {
		List<Landlord_commentsVO> list = new ArrayList<Landlord_commentsVO>();
		Landlord_commentsVO Landlord_commentsVO = null;
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(TNT_GET_ALL_lCM_STMT);
			
			pstmt.setString(1, tnt_no);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Landlord_commentsVO = new Landlord_commentsVO();
				Landlord_commentsVO.setTnt_no(rs.getString("TNT_NO"));
				Landlord_commentsVO.setLcm_no(rs.getString("LCM_NO"));
				Landlord_commentsVO.setLld_no(rs.getString("LLD_NO"));
				Landlord_commentsVO.setLcm_clean(rs.getInt("LCM_CLEAN"));
				Landlord_commentsVO.setLcm_commut(rs.getInt("LCM_COMMUT"));
				Landlord_commentsVO.setLcm_satisfy(rs.getInt("LCM_SATISFY"));
				Landlord_commentsVO.setLcm_commet(rs.getString("LCM_COMMET"));
				Landlord_commentsVO.setLcm_time(rs.getDate("LCM_TIME"));
				Landlord_commentsVO.setLcm_respon(rs.getString("LCM_RESPON"));
				list.add(Landlord_commentsVO);
			}	
		
			} catch (SQLException se) {
				System.out.println("Couldn't load LANDLORD_COMMENTS database driver."+se.getMessage());
				se.printStackTrace(System.err);
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
	public void tnt_update(Landlord_commentsVO landlord_commentsVO) {
		
		Connection con = null;
		PreparedStatement pstmt=null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(TNT_UPD_STMT);
			
			pstmt.setString(1, landlord_commentsVO.getLcm_commet());
			pstmt.setString(2, landlord_commentsVO.getLcm_no());
			pstmt.executeUpdate();
			
		
		} catch(SQLException e) {
			System.out.println("Couldn't load LANDLORD_COMMENTS database driver."+e.getMessage());
			e.printStackTrace(System.err);
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
	public Set<String> getAll_lld_no(String tnt_no) {
		Set<String> set = new HashSet<>();
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_LLD_STMT);
			
			pstmt.setString(1, tnt_no);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				set.add(rs.getString("LLD_NO"));
			}	
	
			} catch (SQLException se) {
				System.out.println("Couldn't load LANDLORD_COMMENTS database driver."+se.getMessage());
				se.printStackTrace(System.err);
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
		
		return set;
	}

	@Override
	public List<Landlord_commentsVO> tnt_getAllByLld(String tnt_no, String lld_no) {
		List<Landlord_commentsVO> list = new ArrayList<Landlord_commentsVO>();
		Landlord_commentsVO Landlord_commentsVO = null;
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_BY_LLD_STMT);
			
			pstmt.setString(1, lld_no);
			pstmt.setString(2, tnt_no);
			rs = pstmt.executeQuery();
		
			while(rs.next()) {
				Landlord_commentsVO = new Landlord_commentsVO();
				Landlord_commentsVO.setLcm_no(rs.getString("LCM_NO"));
				Landlord_commentsVO.setTnt_no(rs.getString("TNT_NO"));
				Landlord_commentsVO.setLld_no(rs.getString("LLD_NO"));
				Landlord_commentsVO.setLcm_clean(rs.getInt("LCM_CLEAN"));
				Landlord_commentsVO.setLcm_commut(rs.getInt("LCM_COMMUT"));
				Landlord_commentsVO.setLcm_satisfy(rs.getInt("LCM_SATISFY"));
				Landlord_commentsVO.setLcm_commet(rs.getString("LCM_COMMET"));
				Landlord_commentsVO.setLcm_time(rs.getDate("LCM_TIME"));
				Landlord_commentsVO.setLcm_respon(rs.getString("LCM_RESPON"));
				list.add(Landlord_commentsVO);
			}	
		
			} catch (SQLException se) {
				System.out.println("Couldn't load LANDLORD_COMMENTS database driver."+se.getMessage());
				se.printStackTrace(System.err);
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
	

	
}
