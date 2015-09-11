package model.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.dao.BlackDAO;
import model.vo.BlackVO;

public class BlackDAOjdbc implements BlackDAO {
	private final String URL = "jdbc:sqlserver://y56pcc16br.database.windows.net:1433;database=iTV";
	private final String USERNAME = "iTVSoCool@y56pcc16br";
	private final String PASSWORD = "iTVisgood911";

	private static final String INSERT = "INSERT INTO black VALUES (?,?)";
	@Override
	public boolean markBlack(int memberId, int blackedId) {
		System.out.println("xxx");
		boolean markResult = false;
		int updateCount = 0;
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement pstmt = conn.prepareStatement(INSERT);) {
			System.out.println("zzzz");
			if (memberId != blackedId) {// 請勿讓白爛使用者有設定自己為黑名單的機會
				System.out.println("ooo");
				pstmt.setInt(1, memberId);
				pstmt.setInt(2, blackedId);
				updateCount = pstmt.executeUpdate();
				System.out.println(updateCount);
				if (updateCount == 1) {
					markResult = true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return markResult;
	}

	private static final String GET_LIST = "SELECT blackedId FROM black WHERE memberId=?";
	@Override
	public List<BlackVO> getList(int memberId) {
		BlackVO blackMem = null;
		List<BlackVO> blacks = new ArrayList<BlackVO>();
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement pstmt = conn.prepareStatement(GET_LIST);) {
			pstmt.setInt(1, memberId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				blackMem = new BlackVO();
				blackMem.setBlackedId(rs.getInt("blackedId"));
				blacks.add(blackMem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return blacks;
	}

	private static final String REMOVE_BLACK = "DELETE FROM black WHERE memberId=? AND blackedId=?";
	@Override
	public boolean removeBlack(int memberId, int blackedId) {
		boolean result = false;
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement pstmt = conn.prepareStatement(REMOVE_BLACK);) {
			pstmt.setInt(1, memberId);
			pstmt.setInt(2, blackedId);
			int updateCount = pstmt.executeUpdate();
			if (updateCount == 1) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	// 突然覺得世間充滿大愛，所以將所有曾被設定為黑名單的人都解除
	private static final String REMOVE_ALL = "DELETE FROM black WHERE memberId=?";
	@Override
	public boolean removeAll(int memberId) {
		boolean removeResult = false;
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement pstmt = conn.prepareStatement(REMOVE_ALL);) {
			pstmt.setInt(1, memberId);
			int updateCount = pstmt.executeUpdate();
			if (updateCount >= 1) {
				removeResult = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return removeResult;
	}

	// 測試程式
	public static void main(String[] args) throws SQLException {
		BlackDAO blackDao = new BlackDAOjdbc();
		// System.out.println(blackDao.markBlack(2,4));
		// System.out.println(blackDao.markBlack(5,5));
		System.out.println(blackDao.getList(4));
		System.out.println(blackDao.removeBlack(4, 2));
		System.out.println(blackDao.removeAll(4));

	}

}
