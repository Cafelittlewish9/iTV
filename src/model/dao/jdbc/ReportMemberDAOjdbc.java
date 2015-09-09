package model.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.vo.ReportMemberVO;

public class ReportMemberDAOjdbc {
	private static final String URL = "jdbc:sqlserver://localhost:1433;database=iTV";
	private static final String USER = "sa";
	private static final String PASSWORD = "passw0rd";
	
	private static final String SELECT_BY_ID = "select * from ReportMember where orderId=?";
	public ReportMemberVO select(int orderId){
		ReportMemberVO result = null;
		ResultSet rest = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			stmt = conn.prepareStatement(SELECT_BY_ID);
			
			stmt.setInt(1, orderId);
			rest = stmt.executeQuery();
			
			if(rest.next()){
				result = new ReportMemberVO();
				result.setOrderId(rest.getInt(orderId));
				result.setReportedMemberId(rest.getInt("reportedMemberId"));
				result.setReportTime(rest.getDate("reportTime"));
				result.setReportReason(rest.getString("reportReason"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	
	
	
	private static final String SELECT_ALL = "select * from ReportMember";
	public List<ReportMemberVO> select(){
		List<ReportMemberVO> result = null;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			stmt = conn.prepareStatement(SELECT_ALL);
			rset = stmt.executeQuery();
			
			result = new ArrayList<ReportMemberVO>();
			while(rset.next()){
				ReportMemberVO bean = new ReportMemberVO();
				bean.setOrderId(rset.getInt("orderId"));
				bean.setReportedMemberId(rset.getInt("reportedMemberId"));
				bean.setReportTime(rset.getDate("reportTime"));
				bean.setReportReason(rset.getString("reportReason"));
				
				result.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	
	
	private static final String INSERT = 
			"set identity_insert ReportMember on;"
			+ "insert into ReportMember(orderId, reportedMemberId, reportTime, reportReason) values(?, ?, ?, ?)"
			+ "set identity_insert ReportMember off;";
	public ReportMemberVO insert(ReportMemberVO bean){
		ReportMemberVO result = null;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			stmt = conn.prepareStatement(INSERT);
			
			if(bean!=null){
				stmt.setInt(1,  bean.getOrderId());
				stmt.setInt(2, bean.getReportedMemberId());
				
				java.util.Date reportTime = bean.getReportTime();
				if(reportTime!=null){
					long time = reportTime.getTime();
					stmt.setDate(3, new java.sql.Date(time));
				}else{
					stmt.setDate(3, null);
				}
				
				stmt.setString(4, bean.getReportReason());
				int i = stmt.executeUpdate();
				if(i == 1){
					result = bean;
				}
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return result;
	}
	
	
	private static final String UPDATE = 
			"update ReportMember set reportedMemberId=?, reportTime=?, reportReason=? where orderId=?";
	
	public ReportMemberVO update(int reportedMemberId, java.util.Date reportTime, String reportReason, int orderId){
		ReportMemberVO result = null;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			stmt = conn.prepareStatement(UPDATE);
			
			stmt.setInt(1, reportedMemberId);
			
			if(reportTime!=null){
				long time = reportTime.getTime();
				stmt.setDate(2, new java.sql.Date(time));
			}else{
				stmt.setDate(2, null);
			}
			
			stmt.setString(3, reportReason);
			stmt.setInt(4, orderId);
			
			int i = stmt.executeUpdate();
			if(i == 1){
				result = this.select(orderId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return result;
	}
	
	
	private static final String DELETE ="delete from ReportMember where orderId = ?";
	
	public boolean delete(int orderId){
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			stmt = conn.prepareStatement(DELETE);
			stmt.setInt(1, orderId);
			int i = stmt.executeUpdate();
			if(i==1){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return false;
	}
	
	
	
	
	
}
