package com.repair_picture.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Repair_pictureJDBCDAO implements Repair_pictureDAO_interface {
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "hr";
	String passwd = "123456";
	
	//房客新增修繕申請的照片
	private static final String INSERT_STMT="INSERT INTO REPAIR_PICTURE (REPPIC_NO, REPPIC_PIC)VALUES(REPPIC_NO_ID.NEXTVAL, ?)WHERE REP_NO=?";
	//查詢一筆修繕紀錄裡的其中一張圖片
	private static final String GET_ONE_STMT="SELECT REPPIC_PIC FROM REPAIR_PICTURE WHERE REPPIC_NO=?";
	//取得此筆修繕紀錄的所有修繕照片
	private static final String GET_ALL_STMT="SELECT REPPIC_PIC FROM REPAIR_PICTURE WHERE REP_NO=?";
	
	//房客新增一張待修物品的照片在修繕紀錄時
	@Override
	public void insert(Repair_pictureVO repair_pictureVO) {
		Connection con = null;
		PreparedStatement pstmt=null;
		PreparedStatement pstmt1=null;
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setBytes(1, repair_pictureVO.getReppic_pic());
		}catch (ClassNotFoundException e) {
					throw new RuntimeException("Couldn't load Repair_picture database driver."+e.getMessage());
				} catch(SQLException e) {
					throw new RuntimeException("Couldn't load  Repair_picture database error occured."+e.getMessage());
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
				
	//查詢一筆修繕紀錄裡的其中一張圖片
	@Override
	public Repair_pictureVO findByPrimaryKey(String reppic_no) {
		Repair_pictureVO repair_pictureVO = null;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, reppic_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				repair_pictureVO = new Repair_pictureVO();
				repair_pictureVO.setReppic_pic(rs.getBytes("REPPIC_PIC"));
				}}catch (ClassNotFoundException e) {
					throw new RuntimeException("Couldn't load Repair_picture database driver. "
							+ e.getMessage());
				} catch (SQLException se) {
					throw new RuntimeException("A database Repair_picture error occured. "
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
		return repair_pictureVO;
	}

	//取得此筆修繕紀錄的所有修繕照片
	@Override
	public List<Repair_pictureVO> lld_getAll(String rep_no) {
		List<Repair_pictureVO> list = new ArrayList<Repair_pictureVO>();
		Repair_pictureVO repair_pictureVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				repair_pictureVO = new Repair_pictureVO();
				repair_pictureVO.setReppic_pic(rs.getBytes("REPPIC_PIC"));
				list.add(repair_pictureVO);
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load Repair_picture database driver. "
					+ e.getMessage());
			
		} catch (SQLException se) {
				throw new RuntimeException("A database Repair_picture error occured. "
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

	
	public static void main(String[] args) {
		

	}

	
}
