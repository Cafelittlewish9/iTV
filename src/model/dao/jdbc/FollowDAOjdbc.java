package model.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.vo.FollowVO;

public class FollowDAOjdbc {
	private static final String URL = "jdbc:sqlserver://localhost:1433;database=iTV";
	private static final String USER = "sa";
	private static final String PASSWORD = "passw0rd";

	private static final String SELECT_BY_FOLLOWID_AND_MEMBERID = "select * from Follow where followId =? and memberId=?";

	public FollowVO select(int followId, int memberId) {
		FollowVO result = null;

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			stmt = conn.prepareStatement(SELECT_BY_FOLLOWID_AND_MEMBERID);

			stmt.setInt(1, followId);
			stmt.setInt(2, memberId);
			rset = stmt.executeQuery();

			if (rset.next()) {
				result = new FollowVO();
				result.setMemberId(rset.getInt("memberId"));
				result.setFollowId(rset.getInt("followId"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	private static final String SELECT_ALL = "select * from Follow";

	public List<FollowVO> select() {
		List<FollowVO> result = null;

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			stmt = conn.prepareStatement(SELECT_ALL);
			rset = stmt.executeQuery();

			result = new ArrayList<FollowVO>();
			while (rset.next()) {
				FollowVO bean = new FollowVO();
				bean.setMemberId(rset.getInt("memberId"));
				bean.setFollowId(rset.getInt("followId"));
				result.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	private static final String INSERT = "insert into follow(memberId, followId) values(?, ?)";

	public FollowVO insert(FollowVO bean) {
		FollowVO result = null;

		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			stmt = conn.prepareStatement(INSERT);

			if (bean != null) {
				stmt.setInt(1, bean.getMemberId());
				stmt.setInt(2, bean.getFollowId());
				int i = stmt.executeUpdate();
				if (i == 1) {
					result = bean;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	private static final String DELETE = "delete from Follow where followId=? and memberId=?";

	public boolean delete(int followId, int memberId) {

		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			stmt = conn.prepareStatement(DELETE);

			stmt.setInt(1, followId);
			stmt.setInt(2, memberId);
			int i = stmt.executeUpdate();
			if (i == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
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
