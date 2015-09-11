package model.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.vo.ReportVideoVO;

public class ReportVideoDAOjdbc {

	private static final String URL = "jdbc:sqlserver://y56pcc16br.database.windows.net:1433;database=iTV";
	private static final String USER = "iTVSoCool@y56pcc16br";
	private static final String PASSWORD = "iTVisgood911";

	private static final String SELECT_ALL = "SELECT * FROM ReportVideo";

	public List<ReportVideoVO> selectAll() {
		List<ReportVideoVO> list = new ArrayList<ReportVideoVO>();
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement pstmt = conn.prepareStatement(SELECT_ALL);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				ReportVideoVO reportVideo = new ReportVideoVO();
				reportVideo.setOrderId(rs.getInt("orderId"));
				reportVideo.setReportedVideoId(rs.getInt("reportedVideoId"));
				reportVideo.setReportTime(rs.getTimestamp("reportTime"));
				reportVideo.setReportReason(rs.getString("reportReason"));
				list.add(reportVideo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
			}
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	private static final String INSERT = "INSERT INTO ReportVideo(reportedVideoId, reportReason) VALUES(?, ?)";

	public boolean insert(ReportVideoVO reportVideo) {
		Connection conn = null;
		boolean result = false;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement pstmt = conn.prepareStatement(INSERT);
			pstmt.setInt(1, reportVideo.getReportedVideoId());
			pstmt.setString(2, reportVideo.getReportReason());
			int demo = pstmt.executeUpdate();
			if (demo == 1) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
			}
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	private static final String DELETE = "DELETE FROM ReportVideo WHERE orderId = ?";

	public boolean delete(int reportVideo) {
		Connection conn = null;
		boolean result = false;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement pstmt = conn.prepareStatement(DELETE);
			pstmt.setInt(1, reportVideo);
			int demo = pstmt.executeUpdate();
			if (demo == 1) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
			}
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public static void main(String[] args) {
		ReportVideoDAOjdbc dao = new ReportVideoDAOjdbc();
		// INSERT
		ReportVideoVO temp1 = new ReportVideoVO();
		temp1.setOrderId(11);
		temp1.setReportedVideoId(12);
		temp1.setReportTime(new java.util.Date());
		temp1.setReportReason("測試新增");
		boolean test1 = dao.insert(temp1);
		System.out.println(test1);
		// DELETE
		boolean test3 = dao.delete(14);
		System.out.println(test3);
		// SELECT_ALL
		List<ReportVideoVO> list = dao.selectAll();
		for (ReportVideoVO dept : list) {
			System.out.println(dept.getOrderId() + ",");
			System.out.println(dept.getReportedVideoId() + ",");
			System.out.println(dept.getReportTime() + ",");
			System.out.println(dept.getReportReason());
		}
	}
}