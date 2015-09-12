package model.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.dao.LoginDAO;
import model.vo.LoginVO;
import util.GC;

public class LoginDAOjdbc implements LoginDAO {
	private static final String URL = GC.URL;
	private static final String USERNAME = GC.USERNAME;
	private static final String PASSWORD = GC.PASSWORD;

	private static final String SELECT_BY_MEMBERACCOUNT = "select * from Login where memberAccount = ?";

	@Override
	public List<LoginVO> selectAll(String memberAccount) {
		LoginVO bean = null;
		List<LoginVO> list = null;
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
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

	private static final String SELECT_ALL = "select * from Login";

	@Override
	public List<LoginVO> selectAll() {
		List<LoginVO> result = null;
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(SELECT_ALL);
				ResultSet rset = stmt.executeQuery();) {
			result = new ArrayList<LoginVO>();
			while (rset.next()) {
				LoginVO bean = new LoginVO();
				bean.setLoginTime(rset.getTimestamp("loginTime"));
				bean.setIp(rset.getString("ip"));
				bean.setMemberAccount(rset.getString("memberAccount"));
				result.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	private static final String SELECT_LAST_TIME = "SELECT TOP 1 * FROM Login WHERE memberAccount = ? ORDER BY logintime DESC";

	@Override
	public LoginVO select(String memberAccount) {
		LoginVO bean = null;
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(SELECT_LAST_TIME);) {
			stmt.setString(1, memberAccount);
			ResultSet rset = stmt.executeQuery();
			if (rset.next()) {
				bean = new LoginVO();
				bean.setLoginTime(rset.getTimestamp("loginTime"));
				bean.setIp(rset.getString("ip"));
				bean.setMemberAccount(rset.getString("memberAccount"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bean;
	}

	private static final String INSERT = "insert into Login(ip, memberAccount) values(?, ?)";

	@Override
	public LoginVO insert(LoginVO bean) {
		LoginVO result = null;
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(INSERT);) {
			if (bean != null) {
				stmt.setString(1, bean.getIp());
				stmt.setString(2, bean.getMemberAccount());
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
		LoginDAO temp = new LoginDAOjdbc();
		System.out.println(temp.select("Pikachu"));

		// LoginVO bean = new LoginVO();
		// bean.setIp("192.168.26.39");
		// bean.setLoginTime(new java.util.Date());
		// bean.setMemberAccount("Pikachu");
		// System.out.println(temp.insert(bean));
	}
}