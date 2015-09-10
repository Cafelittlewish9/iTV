package model.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.vo.ReportVideoVO;

public class ReportVideoDAOjdbc {
	
	private static final String URL = "jdbc:sqlserver://y56pcc16br.database.windows.net:1433;database=iTV";
	private static final String USER = "iTVSoCool@y56pcc16br";
	private static final String PASSWORD = "iTVisgood911";
	private static final String SELECT_ALL = "SELECT * FORM ReportVideo";
	private static final String INSERT = "";
	private static final String UPDATE = "";
	private static final String DELETE = "";

	public List<ReportVideoVO> selectAll() {
		List<ReportVideoVO> list = new ArrayList<ReportVideoVO>();
		
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL,USER,PASSWORD);
			PreparedStatement pstmt = conn.prepareStatement(SELECT_ALL);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				ReportVideoVO reportVideo = new ReportVideoVO();
				reportVideo.setOrderId(rs.getInt("orderId"));
				reportVideo.setReportedVideoId(rs.getInt("reportedVideoId"));
				reportVideo.setReportReason(rs.getString("reportReason"));
				list.add(reportVideo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(conn != null){
			}try{
				conn.close();			
			}catch(SQLException e){
				e.printStackTrace();
			}
		}		
		
		return list;
	}

	public boolean insert(ReportVideoVO reportVideo) {
		
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL,USER,PASSWORD);
			PreparedStatement pstmt = conn.prepareStatement(INSERT);
			
			pstmt.setInt(1, reportVideo.getOrderId());
			pstmt.setInt(2, reportVideo.getReportedVideoId());
			pstmt.setString(4, reportVideo.getReportReason());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally{
			if(conn != null){
			}try{
				conn.close();			
			}catch(SQLException e){
				e.printStackTrace();
			}
		}		
		return false;
	}

	public boolean update(ReportVideoVO reportVideo) {
		
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL,USER,PASSWORD);
			PreparedStatement pstmt = conn.prepareStatement(UPDATE);
			
			pstmt.setInt(1, reportVideo.getOrderId());
			pstmt.setInt(2, reportVideo.getReportedVideoId());
			pstmt.setString(4, reportVideo.getReportReason());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally{
			if(conn != null){
			}try{
				conn.close();			
			}catch(SQLException e){
				e.printStackTrace();
			}
		}			
		return false;
	}

	public boolean delete(ReportVideoVO reportVideo) {
		
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL,USER,PASSWORD);
			PreparedStatement pstmt = conn.prepareStatement(DELETE);
			
			pstmt.setInt(1, reportVideo.getOrderId());
			pstmt.setInt(2, reportVideo.getReportedVideoId());
			pstmt.setString(4, reportVideo.getReportReason());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally{
			if(conn != null){
			}try{
				conn.close();			
			}catch(SQLException e){
				e.printStackTrace();
			}
		}			
		return false;
	}

}
