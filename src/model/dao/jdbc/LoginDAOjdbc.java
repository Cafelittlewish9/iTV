package model.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.vo.LoginVO;

public class LoginDAOjdbc {
	private static final String URL = "jdbc:sqlserver://y56pcc16br.database.windows.net:1433;database=iTV";
	private static final String USER = "iTVSoCool@y56pcc16br";
	private static final String PASSWORD = "iTVisgood911";

	private static final String SELECT_BY_MEMBERACCOUNT = "select * from Login where memberAccount = ?";

	public List<LoginVO> select(String memberAccount) {
		LoginVO bean = null;
		List<LoginVO> list = null;
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(SELECT_BY_MEMBERACCOUNT);) {
			stmt.setString(1, memberAccount);
			ResultSet rset = stmt.executeQuery();
			list = new ArrayList<LoginVO>();
			while (rset.next()) {
				bean = new LoginVO();
				bean.setMemberAccount(rset.getString("memberAccount"));
				bean.setIp(rset.getString("ip"));
				bean.setLoginTime(rset.getTimestamp("loginTime"));
				list.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

//	private static final String SELECT_ALL = "select * from Login";
//
//	public List<LoginVO> selectAll() throws Exception {
//		List<LoginVO> result = null;
//		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
//				PreparedStatement stmt = conn.prepareStatement(SELECT_ALL);) {
//			ResultSet rset = stmt.executeQuery();
//			result = new ArrayList<LoginVO>();
//			while (rset.next()) {
//				LoginVO bean = new LoginVO();
//				bean.setLoginTime(rset.getTimestamp("loginTime"));
//				bean.setIp(rset.getString("ip"));
//				bean.setMemberAccount(rset.getString("memberAccount"));
//				result.add(bean);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return result;
//	}

	private static final String INSERT = "insert into Login(loginTime, ip, memberAccount) values(?, ?, ?)";

	public LoginVO insert(LoginVO bean) {
		LoginVO result = null;
		try(Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(INSERT);) {
			if (bean != null) {
				stmt.setTimestamp(1, new java.sql.Timestamp(bean.getLoginTime().getTime()));
				stmt.setString(2, bean.getIp());
				stmt.setString(3, bean.getMemberAccount());
				int i = stmt.executeUpdate();
				if (i == 1) {
					result = bean;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static void main(String[] args) {
		LoginDAOjdbc temp = new LoginDAOjdbc();
		System.out.println(temp.select("Pikachu"));
		
//		LoginVO bean = new LoginVO();
//		bean.setIp("192.168.26.39");
//		bean.setLoginTime(new java.util.Date());
//		bean.setMemberAccount("Pikachu");
//		System.out.println(temp.insert(bean));
	}
}