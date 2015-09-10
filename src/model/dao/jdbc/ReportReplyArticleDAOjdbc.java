package model.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.vo.ReportReplyArticleVO;

public class ReportReplyArticleDAOjdbc {
	
	private static final String URL = "jdbc:sqlserver://y56pcc16br.database.windows.net:1433;database=iTV";
	private static final String USER = "iTVSoCool@y56pcc16br";
	private static final String PASSWORD = "iTVisgood911";
	private static final String SELECT_ALL = "SELECT * FORM ReportReplyArticle";
	private static final String INSERT = "INSERT INTO ReportReplyArticle() VALUES (?,?,?,?)";
	private static final String UPDATE = "UPDATE ReportReplyArticle SET WHERE orderId";
	private static final String DELETE = "DELETE FROM ReportReplyArticle WHERE ";

	public List<ReportReplyArticleVO> selectAll() {
		List<ReportReplyArticleVO> list = new ArrayList<ReportReplyArticleVO>();
		ReportReplyArticleVO reportReplyArticle = null;
		
		Connection conn =null;
		try {
			conn = DriverManager.getConnection(URL,USER,PASSWORD);
			PreparedStatement pstmt = conn.prepareStatement(SELECT_ALL);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				reportReplyArticle = new ReportReplyArticleVO();
				reportReplyArticle.setOrderId(rs.getInt("orderId"));
				reportReplyArticle.setReportedReplyArticleId(rs.getInt("reportedReplyArticleId"));
				reportReplyArticle.setReportReason(rs.getString("reportReason"));
				list.add(reportReplyArticle);	
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

	public boolean insert(ReportReplyArticleVO reportReplyArticle) {
		
		Connection conn = null ;
		try{
			conn = DriverManager.getConnection(URL ,USER , PASSWORD);
			PreparedStatement pstmt = conn.prepareStatement(INSERT);
			
			pstmt.setInt(1, reportReplyArticle.getOrderId());
			pstmt.setInt(2, reportReplyArticle.getReportedReplyArticleId());
//			java.util.Date(3, reportReplyArticle.getReportTime());
			pstmt.setString(4, reportReplyArticle.getReportReason());
			
			pstmt.executeUpdate();			
		}catch(SQLException e){
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

	public boolean update(ReportReplyArticleVO reportReplyArticle) {
		
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL ,USER , PASSWORD);
			PreparedStatement pstmt = conn.prepareStatement(UPDATE);
			
			pstmt.setInt(1, reportReplyArticle.getOrderId());
			pstmt.setInt(2, reportReplyArticle.getReportedReplyArticleId());
//			java.util.Date(3, reportReplyArticle.getReportTime());
			pstmt.setString(4, reportReplyArticle.getReportReason());
			
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

	public boolean delete(ReportReplyArticleVO reportReplyArticle) {
		
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL ,USER , PASSWORD);
			PreparedStatement pstmt = conn.prepareStatement(DELETE);
			
			pstmt.setInt(1, reportReplyArticle.getOrderId());
			pstmt.setInt(2, reportReplyArticle.getReportedReplyArticleId());
			
			pstmt.setString(4, reportReplyArticle.getReportReason());
			
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
