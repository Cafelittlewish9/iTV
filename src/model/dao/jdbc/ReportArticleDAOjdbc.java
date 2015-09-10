package model.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.vo.ReportArticleVO;

public class ReportArticleDAOjdbc {
	private static final String URL = "jdbc:sqlserver://y56pcc16br.database.windows.net:1433;database=iTV";
	private static final String USER = "iTVSoCool@y56pcc16br";
	private static final String PASSWORD = "iTVisgood911";
	private static final String SELECT_ALL = "SELECT * FROM ReportArticle";
	private static final String INSERT = "";
	private static final String UPDATE = "";
	private static final String DELETE = "";
	

	public List<ReportArticleVO> selectAll() {
		List<ReportArticleVO> list = new ArrayList<ReportArticleVO>();
		
		ReportArticleVO reportArticle = null;
		
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL,USER,PASSWORD);
			PreparedStatement pstmt = conn.prepareStatement(SELECT_ALL);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				reportArticle = new ReportArticleVO();
				reportArticle.setOrderId(rs.getInt("orderId"));
				reportArticle.setReportedArticleId(rs.getInt("reportedArticleId"));
				reportArticle.setReportReason(rs.getString("reportReason"));
				list.add(reportArticle);		
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(conn != null){
				try{
					conn.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	public boolean insert(ReportArticleVO reportArticle) {
		
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL,USER,PASSWORD);
			PreparedStatement pstmt = conn.prepareStatement(INSERT);
			
			pstmt.setInt(1, reportArticle.getOrderId());
			pstmt.setInt(2, reportArticle.getReportedArticleId());
			pstmt.setString(4,reportArticle.getReportReason());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(conn != null){
				try{
					conn.close();
				}catch(SQLException e){
					e.printStackTrace();
				}				
			}
		}
		return false;
	}

	public boolean update(ReportArticleVO reportArticle) {
		
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL,USER,PASSWORD);
			PreparedStatement pstmt = conn.prepareStatement(UPDATE);
			
			pstmt.setInt(1, reportArticle.getOrderId());
			pstmt.setInt(2, reportArticle.getReportedArticleId());
			pstmt.setString(4, reportArticle.getReportReason());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(conn != null){
				try{
					conn.close();			
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
		}	
		return false;
	}

	public boolean delete(ReportArticleVO reportArticle) {
		
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL,USER,PASSWORD);
			PreparedStatement pstmt = conn.prepareStatement(DELETE);
			
			pstmt.setInt(1, reportArticle.getOrderId());
			pstmt.setInt(2, reportArticle.getReportedArticleId());
			pstmt.setString(4, reportArticle.getReportReason());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(conn != null){
				try{
					conn.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
		}
		return false;
	}

}
