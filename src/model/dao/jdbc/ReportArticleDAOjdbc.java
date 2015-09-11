package model.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.dao.ReportArticleDAO;
import model.vo.ReportArticleVO;

public class ReportArticleDAOjdbc implements ReportArticleDAO {
	private static final String URL = "jdbc:sqlserver://y56pcc16br.database.windows.net:1433;database=iTV";
	private static final String USER = "iTVSoCool@y56pcc16br";
	private static final String PASSWORD = "iTVisgood911";

	private static final String SELECT_ALL = "SELECT * FROM ReportArticle ORDER BY reportTime DESC";

	@Override
	public List<ReportArticleVO> selectAll() {
		List<ReportArticleVO> list = null;
		ReportArticleVO reportArticle = null;
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement pstmt = conn.prepareStatement(SELECT_ALL);
			ResultSet rs = pstmt.executeQuery();
			list = new ArrayList<ReportArticleVO>();
			while (rs.next()) {
				reportArticle = new ReportArticleVO();
				reportArticle.setOrderId(rs.getInt("orderId"));
				reportArticle.setReportedArticleId(rs.getInt("reportedArticleId"));
				reportArticle.setReportTime(rs.getTimestamp("reportTime"));
				reportArticle.setReportReason(rs.getString("reportReason"));
				list.add(reportArticle);
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

	private static final String INSERT = " INSERT INTO ReportArticle(reportedArticleId, reportReason) VALUES(?, ?) ";

	@Override
	public boolean insert(ReportArticleVO reportArticle) {
		Connection conn = null;
		boolean result = false;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement pstmt = conn.prepareStatement(INSERT);
			pstmt.setInt(1, reportArticle.getReportedArticleId());
			pstmt.setString(2, reportArticle.getReportReason());
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

	private static final String DELETE = " DELETE FROM ReportArticle WHERE orderId = ?";

	@Override
	public boolean delete(int orderId) {
		Connection conn = null;
		boolean result = false;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
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
		ReportArticleDAO dao = new ReportArticleDAOjdbc();
		// INSERT
		// ReportArticleVO temp1 = new ReportArticleVO();
		// temp1.setOrderId(11);
		// temp1.setReportedArticleId(12);
		// temp1.setReportTime(new java.util.Date());
		// temp1.setReportReason("測試新增");
		// boolean test1 = dao.insert(temp1);
		// System.out.println(test1);
		// DELETE
		boolean test3 = dao.delete(13);
		System.out.println(test3);
		// SELECT_ALL
		List<ReportArticleVO> list = dao.selectAll();
		for (ReportArticleVO dept : list) {
			System.out.println(dept.getOrderId() + ",");
			System.out.println(dept.getReportedArticleId() + ",");
			System.out.println(dept.getReportTime() + ",");
			System.out.println(dept.getReportReason());
		}
	}
}