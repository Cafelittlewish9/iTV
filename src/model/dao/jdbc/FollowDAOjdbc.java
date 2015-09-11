package model.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.dao.FollowDAO;
import model.vo.FollowVO;

public class FollowDAOjdbc implements FollowDAO {
	private static final String URL = "jdbc:sqlserver://y56pcc16br.database.windows.net:1433;database=iTV";
	private static final String USER = "iTVSoCool@y56pcc16br";
	private static final String PASSWORD = "iTVisgood911";

	private static final String SELECT_BY_MEMBERID = "SELECT * FROM Follow WHERE memberId=?";

	@Override
	public List<FollowVO> selectAll(int memberId) {
		List<FollowVO> list = null;
		FollowVO follow = null;
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(SELECT_BY_MEMBERID);) {
			stmt.setInt(1, memberId);
			ResultSet rset = stmt.executeQuery();
			list = new ArrayList<FollowVO>();
			while (rset.next()) {
				follow = new FollowVO();
				follow.setMemberId(rset.getInt("memberId"));
				follow.setFollowId(rset.getInt("followId"));
				list.add(follow);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	private static final String SELECT_ALL = "select * from Follow";

	@Override
	public List<FollowVO> selectAll() {
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

	@Override
	public int insert(FollowVO bean) {
		int result = -1;
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(INSERT);) {
			if (bean != null) {
				stmt.setInt(1, bean.getMemberId());
				stmt.setInt(2, bean.getFollowId());
				result = stmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	private static final String DELETE = "delete from Follow where followId=? and memberId=?";

	@Override
	public boolean delete(int followId, int memberId) {
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(DELETE);) {
			stmt.setInt(1, followId);
			stmt.setInt(2, memberId);
			int i = stmt.executeUpdate();
			if (i == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static void main(String[] args) {
		FollowDAO follow = new FollowDAOjdbc();
//		System.out.println(follow.selectAll());
		
//		System.out.println(follow.select(4));
		
		FollowVO bean = new FollowVO();
		bean.setMemberId(4);
		bean.setFollowId(2);
//		System.out.println(follow.insert(bean));
		
		System.out.println(follow.delete(2, 4));
	}
}
