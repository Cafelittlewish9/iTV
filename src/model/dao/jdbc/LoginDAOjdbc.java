package model.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import model.vo.LoginVO;

public class LoginDAOjdbc {
	private static final String URL = "jdbc:sqlserver://localhost:1433;database=iTV";
	private static final String USER = "sa";
	private static final String PASSWORD = "passw0rd";
	
	private static final String SELECT_BY_MEMBERACCOUNT = "select * from Login where memberAccount =?";
	public LoginVO select(String memberAccount){
		LoginVO result = null;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			stmt = conn.prepareStatement(SELECT_BY_MEMBERACCOUNT);
			
			stmt.setString(1, memberAccount);
			
			rset = stmt.executeQuery();
			
			if(rset.next()){
				
				result = new LoginVO();
				result.setMemberAccount(rset.getString("memberAccount"));
				result.setIp(rset.getString("ip"));
				result.setLoginTime(rset.getTimestamp("loginTime"));
				 
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
	
	
	private static final String SELECT_ALL = "select * from Login";
	public List<LoginVO> select() throws Exception{
		List<LoginVO> result = null;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			stmt = conn.prepareStatement(SELECT_ALL);
			rset = stmt.executeQuery();
			
			result = new ArrayList<LoginVO>();
			
			
			while(rset.next()){
				
				LoginVO bean = new LoginVO();
				bean.setLoginTime(rset.getTimestamp("loginTime"));
				bean.setIp(rset.getString("ip"));
				bean.setMemberAccount(rset.getString("memberAccount"));
				
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
	
	private static final String INSERT ="insert into Login(loginTime, ip, memberAccount) values(?, ?, ?)";
	public LoginVO insert(LoginVO bean){
		LoginVO result = null;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			stmt = conn.prepareStatement(INSERT);
			
			if(bean!=null){
				
				stmt.setString(1, df.format(new java.util.Date()));
				stmt.setString(2, bean.getIp());
				stmt.setString(3, bean.getMemberAccount());
				
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
	
	
	
	

}
