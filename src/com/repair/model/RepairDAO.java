package com.repair.model;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.repair_picture.model.Repair_pictureVO;


public class RepairDAO implements RepairDAO_interface{
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/G2DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	

	private static final String INSERT_REPAIR_STMT="INSERT INTO REPAIR (REP_NO, CON_NO, REP_DAM_OBJ, REP_DAM_OBJ_DES, REP_CASE_STR, REP_PRO) VALUES ('REP' || lpad(SEQ_REP_NO.NEXTVAL, 6,'0'), ?, ?, ?, ?, ?) ";
	private static final String UPDATE_DES="UPDATE REPAIR SET REP_DAM_OBJ_DES=? WHERE REP_NO=?"; 
	private static final String LLD_UPDATE_ENDDATE_STMT="UPDATE REPAIR SET REP_EST_ENDDATE=? WHERE REP_NO=?"; 
	private static final String LLD_UPDATE_PRO_STMT="UPDATE REPAIR SET REP_PRO=? WHERE REP_NO = ?";
	private static final String UPDATE_STMT=" UPDATE REPAIR SET REP_TNT_RPT=?, REP_TNT_RPTTIME=?, REP_END_TIME=?, REP_PRO=? WHERE REP_NO=?";
	private static final String GET_ONE_STMT="SELECT REP_NO, CON_NO, REP_DAM_OBJ, REP_DAM_OBJ_DES, REP_PRO, to_char(REP_EST_ENDDATE, 'yyyy-mm-dd')REP_EST_ENDDATE, to_char(REP_CASE_STR, 'yyyy-mm-dd')REP_CASE_STR, to_char(REP_TNT_RPTTIME, 'yyyy-mm-dd')REP_TNT_RPTTIME, REP_TNT_RPT, to_char(REP_END_TIME, 'yyyy-mm-dd')REP_END_TIME FROM REPAIR WHERE REP_NO=?";
	private static final String LLD_GET_ALL_STMT="SELECT REP_NO, R.CON_NO, REP_DAM_OBJ, REP_DAM_OBJ_DES, REP_PRO, to_char(REP_EST_ENDDATE, 'yyyy-mm-dd')REP_EST_ENDDATE, to_char(REP_CASE_STR, 'yyyy-mm-dd')REP_CASE_STR, to_char(REP_TNT_RPTTIME, 'yyyy-mm-dd')REP_TNT_RPTTIME, REP_TNT_RPT, to_char(REP_END_TIME, 'yyyy-mm-dd')REP_END_TIME FROM REPAIR R INNER JOIN CONTRACT C ON C.CON_NO=R.CON_NO INNER JOIN HOUSE H ON H.HOS_NO= C.HOS_NO INNER JOIN LANDLORD L ON H.LLD_NO= L.LLD_NO ORDER BY REP_NO DESC";
	
	private static final String TNT_GET_ALL_STMT="SELECT REP_NO, CON_NO, REP_DAM_OBJ, REP_DAM_OBJ_DES, REP_PRO, to_char(REP_EST_ENDDATE, 'yyyy-mm-dd')REP_EST_ENDDATE, to_char(REP_CASE_STR, 'yyyy-mm-dd')REP_CASE_STR, to_char(REP_TNT_RPTTIME, 'yyyy-mm-dd')REP_TNT_RPTTIME, REP_TNT_RPT, to_char(REP_END_TIME, 'yyyy-mm-dd')REP_END_TIME FROM REPAIR WHERE CON_NO=?";
	private static final String INSERT_REPAIR_PIC="INSERT INTO REPAIR_PICTURE (REPPIC_NO, REP_NO, REPPIC_PIC) VALUES ('REPPIC' || lpad(SEQ_REPPIC_NO.NEXTVAL, 6,'0'), ?, ?)";
	private static final String GET_ALL_PIC_BY_REPNO = "SELECT REPPIC_NO, REP_NO FROM REPAIR_PICTURE WHERE REP_NO=?";
	private static final String DEL_REPAIR_PIC="UPDATE REPAIR_PICTURE SET REPPIC_NO=? WHERE REPPIC_NO=?";
	
	public List<Repair_pictureVO> getPicsNo (String rep_no) {
		List<Repair_pictureVO> listNo = new ArrayList<Repair_pictureVO>();
		Repair_pictureVO repair_pictureVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
			
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_PIC_BY_REPNO);
			pstmt.setString(1,rep_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				repair_pictureVO = new Repair_pictureVO();
				
				repair_pictureVO.setReppic_no(rs.getString("REPPIC_NO"));
				repair_pictureVO.setRep_no(rs.getString("REP_NO"));
				listNo.add(repair_pictureVO);
			}
		} catch (SQLException se) {
	System.out.println(("A database Repair error occured. "));
			se.getMessage();
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

return listNo;
}
	
		
	public void insert_pic(Repair_pictureVO repair_pictureVO) {
		Connection con = null;
		PreparedStatement pstmt=null;
		System.out.println("dao");
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_REPAIR_PIC);
			
			byte[] pic = repair_pictureVO.getReppic_pic();
			
			System.out.println("dao rep_no = "+repair_pictureVO.getRep_no());
			System.out.println("dao reppic = "+repair_pictureVO.getReppic_pic().length);
			
			pstmt.setString(1, repair_pictureVO.getRep_no());
			pstmt.setBytes(2, pic);
			pstmt.executeUpdate();
			System.out.println("dao executed");
		
		} catch (SQLException se) {
			System.out.println(se);
			
			se.printStackTrace(System.err);
		} finally {
			// 依建立順序關閉資源 (越晚建立越早關閉)
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					System.out.println(se);
					se.getMessage();
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					System.out.println(se);
					se.getMessage();
				}
			}
		}
	}
	
	@Override
	public RepairVO tnt_insert(RepairVO RepairVO) {
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		RepairVO repairVO = new RepairVO();
		try {
			con = ds.getConnection();
			//放要取出的欄位資料
			String[] cols = {"rep_no", "con_no", "rep_dam_obj", "rep_dam_obj_des", "rep_case_str"};
			pstmt = con.prepareStatement(INSERT_REPAIR_STMT, cols);
			pstmt.setString(1, RepairVO.getCon_no());
			pstmt.setString(2, RepairVO.getRep_dam_obj());
			pstmt.setString(3, RepairVO.getRep_dam_obj_des());
			pstmt.setDate(4, RepairVO.getRep_case_str());
			//�]�wREP_PRO��0:�B�z��
			pstmt.setInt(5, 0);
			pstmt.executeUpdate();
			
			//取回此筆新增資料的rep_no
			rs = pstmt.getGeneratedKeys();

			if (rs.next()) {
				repairVO = new RepairVO();
				repairVO.setRep_no(rs.getString(1)); 
				repairVO.setCon_no(rs.getString(2));
				repairVO.setRep_dam_obj(rs.getString(3));
				repairVO.setRep_dam_obj_des(rs.getString(4));
				repairVO.setRep_case_str(rs.getDate(5));
				
				}
		}catch(SQLException e) {
			System.out.println("Couldn't load  FROM Repair database error occured.");
			e.getMessage();
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
return repairVO;
	}
				
	@Override
	public void enddate_update(RepairVO repairVO) {
		Connection con = null;
		PreparedStatement pstmt=null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(LLD_UPDATE_ENDDATE_STMT);
			
			pstmt.setDate(1, repairVO.getRep_est_enddate());
			pstmt.setString(2, repairVO.getRep_no());
			pstmt.executeUpdate();
		} catch(SQLException e) {
			throw new RuntimeException("Couldn't load  RepairVO database error occured."+e.getMessage());
		} finally {
			if(pstmt!=null) {
				if(pstmt!=null) {
					try {
						pstmt.close();
					} catch (SQLException e) {
						e.getMessage();
						e.printStackTrace(System.err);
					}
			
				}
				if(con !=null) {
					try {
						con.close();
					} catch (SQLException e) {
						e.getMessage();
						e.printStackTrace(System.err);
					}
				}
			}
		}
		
	}
	
	@Override
	public void update_Pro(RepairVO RepairVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(LLD_UPDATE_PRO_STMT);

			pstmt.setInt(1, RepairVO.getRep_pro());
			pstmt.setString(2, RepairVO.getRep_no());
			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database Repair error occured. "
					+ se.getMessage());
		} finally {
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

	}

	@Override
	public void tnt_update(RepairVO RepairVO) {
		System.out.println("進來Dao的tnt_upd");
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setInt(1, RepairVO.getRep_tnt_rpt());
			pstmt.setDate(2, RepairVO.getRep_tnt_rpttime());
			pstmt.setDate(3, RepairVO.getRep_end_time());
			pstmt.setInt(4, RepairVO.getRep_pro());
			pstmt.setString(5, RepairVO.getRep_no());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database Repair error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
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

	}

	@Override
	public RepairVO findByPrimaryKey(String rep_no) {
		
		RepairVO repairVO = null;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1,rep_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				repairVO = new RepairVO();
				repairVO.setRep_no(rs.getString("REP_NO"));
				repairVO.setCon_no(rs.getString("CON_NO"));
				repairVO.setRep_dam_obj(rs.getString("REP_DAM_OBJ"));
				repairVO.setRep_dam_obj_des(rs.getString("REP_DAM_OBJ_DES"));
				repairVO.setRep_pro(rs.getInt("REP_PRO"));
				repairVO.setRep_est_enddate(rs.getDate("REP_EST_ENDDATE"));
				repairVO.setRep_case_str(rs.getDate("REP_CASE_STR"));
				repairVO.setRep_tnt_rpttime(rs.getDate("REP_TNT_RPTTIME"));
				repairVO.setRep_tnt_rpt(rs.getInt("REP_TNT_RPT"));
				repairVO.setRep_end_time(rs.getDate("REP_END_TIME"));
				}
			
				} catch (SQLException se) {
					throw new RuntimeException("A database Repair error occured. "
							+ se.getMessage());
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
		return repairVO;
	}

	@Override
	public List<RepairVO> lld_getAll(String hos_no) {
		List<RepairVO> list = new ArrayList<RepairVO>();
		RepairVO repairVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(LLD_GET_ALL_STMT);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				repairVO = new RepairVO();
				repairVO.setRep_no(rs.getString("REP_NO"));
				repairVO.setCon_no(rs.getString("CON_NO"));
				repairVO.setRep_dam_obj(rs.getString("REP_DAM_OBJ"));
				repairVO.setRep_dam_obj_des(rs.getString("REP_DAM_OBJ_DES"));
				repairVO.setRep_pro(rs.getInt("REP_PRO"));
				repairVO.setRep_est_enddate(rs.getDate("REP_EST_ENDDATE"));
				repairVO.setRep_case_str(rs.getDate("REP_CASE_STR"));
				repairVO.setRep_tnt_rpttime(rs.getDate("REP_TNT_RPTTIME"));
				repairVO.setRep_tnt_rpt(rs.getInt("REP_TNT_RPT"));
				repairVO.setRep_end_time(rs.getDate("REP_END_TIME"));
				
				list.add(repairVO);
			}
			} catch (SQLException se) {
				throw new RuntimeException("A database Repair error occured. "
						+ se.getMessage());
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
	public List<RepairVO> tnt_getAll(String con_no) {
		List<RepairVO> list = new ArrayList<RepairVO>();
		RepairVO repairVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(TNT_GET_ALL_STMT);
			pstmt.setString(1, con_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				repairVO = new RepairVO();
				repairVO.setRep_no(rs.getString("REP_NO"));
				repairVO.setCon_no(rs.getString("CON_NO"));
				repairVO.setRep_dam_obj(rs.getString("REP_DAM_OBJ"));
				repairVO.setRep_dam_obj_des(rs.getString("REP_DAM_OBJ_DES"));
				repairVO.setRep_pro(rs.getInt("REP_PRO"));
				repairVO.setRep_est_enddate(rs.getDate("REP_EST_ENDDATE"));
				repairVO.setRep_case_str(rs.getDate("REP_CASE_STR"));
				repairVO.setRep_tnt_rpttime(rs.getDate("REP_TNT_RPTTIME"));
				repairVO.setRep_tnt_rpt(rs.getInt("REP_TNT_RPT"));
				repairVO.setRep_end_time(rs.getDate("REP_END_TIME"));
				list.add(repairVO); 
			}
			} catch (SQLException se) {
				throw new RuntimeException("A database Repair error occured. "
						+ se.getMessage());
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
	public void tnt_updateDes(RepairVO RepairVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_DES);
			pstmt.setString(1, RepairVO.getRep_dam_obj_des());
			pstmt.setString(2, RepairVO.getRep_no());
			pstmt.executeUpdate();

			
		} catch (SQLException se) {
			throw new RuntimeException("A database Repair error occured. "
					+ se.getMessage());
		} finally {
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

	}


	@Override
	public void del_pic(String reppic_no, String new_reppic_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DEL_REPAIR_PIC);
			pstmt.setString(1, new_reppic_no);
			pstmt.setString(2, reppic_no);
			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database Repair_picture error occured. "
					+ se.getMessage());
		} finally {
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

	}

	}


