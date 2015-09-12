package model.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.dao.ReportReplyArticleDAO;
import model.vo.ReportReplyArticleVO;
import util.GC;

public class ReportReplyArticleDAOjdbc implements ReportReplyArticleDAO {
	private static final String URL = GC.URL;
	private static final String USERNAME = GC.USERNAME;
	private static final String PASSWORD = GC.PASSWORD;

	private static final String SELECT_ALL = "SELECT * FROM ReportReplyArticle";

	@Override
	public List<ReportReplyArticleVO> selectAll() {
		List<ReportReplyArticleVO> list = null;
		ReportReplyArticleVO reportReplyArticle = null;
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			PreparedStatement pstmt = conn.prepareStatement(SELECT_ALL);
			ResultSet rs = pstmt.executeQuery();
			list = new ArrayList<ReportReplyArticleVO>();
			while (rs.next()) {
				reportReplyArticle = new ReportReplyArticleVO();
				reportReplyArticle.setOrderId(rs.getInt("orderId"));
				reportReplyArticle.setReportedReplyArticleId(rs.getInt("reportedReplyArticleId"));
				reportReplyArticle.setReportTime(rs.getTimestamp("reportTime"));
				reportReplyArticle.setReportReason(rs.getString("reportReason"));
				list.add(reportReplyArticle);
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
		return list;
	}

	private static final String INSERT = "INSERT INTO ReportReplyArticle(reportedReplyArticleId, reportReason) VALUES(?, ?)";

	@Override
	public boolean insert(ReportReplyArticleVO reportReplyArticle) {
		Connection conn = null;
		boolean result = false;
		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			PreparedStatement pstmt = conn.prepareStatement(INSERT);
			pstmt.setInt(1, reportReplyArticle.getReportedReplyArticleId());
			pstmt.setString(2, reportReplyArticle.getReportReason());
			int demo = pstmt.executeUpdate();
			if (demo == 1) {
				result = true;
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

	private static final String DELETE = "DELETE FROM ReportReplyArticle WHERE orderId = ?";

	@Override
	public boolean delete(int orderId) {
		Connection conn = null;
		boolean result = false;
		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			PreparedStatement pstmt = conn.prepareStatement(DELETE);
			pstmt.setInt(1, orderId);
			int demo = pstmt.executeUpdate();
			if (demo == 1) {
				result = true;
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

	public static void main(String[] args) {
		ReportReplyArticleDAO dao = new ReportReplyArticleDAOjdbc();
		// INSERT
		ReportReplyArticleVO temp1 = new ReportReplyArticleVO();
		temp1.setOrderId(11);
		temp1.setReportedReplyArticleId(12);
		temp1.setReportTime(new java.util.Date());
		temp1.setReportReason("測試新增");
		boolean test1 = dao.insert(temp1);
		System.out.println(test1);
		// DELETE
		boolean test3 = dao.delete(50);
		System.out.println(test3);
		// SELECT_ALL
		List<ReportReplyArticleVO> list = dao.selectAll();
		for (ReportReplyArticleVO dept : list) {
			System.out.println(dept.getOrderId() + ",");
			System.out.println(dept.getReportedReplyArticleId() + ",");
			System.out.println(dept.getReportTime() + ",");
			System.out.println(dept.getReportReason());
		}
	}
}