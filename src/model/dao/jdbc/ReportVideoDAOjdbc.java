package model.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.dao.ReportVideoDAO;
import model.vo.MemberVO;
import model.vo.ReportVideoVO;
import model.vo.VideoVO;
import util.ConvertType;
import util.GC;
public class ReportVideoDAOjdbc implements ReportVideoDAO {
	private static final String URL = GC.URL;
	private static final String USERNAME = GC.USERNAME;
	private static final String PASSWORD = GC.PASSWORD;

	private static final String SELECT_ALL = "SELECT orderId, reportedVideoId, reportTime, reportReason, "
			+ "v.memberId, videoWebsite, videoClassName, videoTitle, videoUploadTime, videoDescription, "
			+ "videoDescriptionModifyTime, memberAccount FROM ReportVideo JOIN Video v ON reportedVideoId = "
			+ "videoId JOIN Member m ON v.memberId = m.memberId";

	@Override
	public List<ReportVideoVO> selectAll() {
		List<ReportVideoVO> list = new ArrayList<ReportVideoVO>();
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			PreparedStatement pstmt = conn.prepareStatement(SELECT_ALL);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				ReportVideoVO reportVideo = new ReportVideoVO();
				reportVideo.setOrderId(rs.getInt("orderId"));
				reportVideo.setReportedVideoId(rs.getInt("reportedVideoId"));
				reportVideo.setReportTime(ConvertType.convertToLocalTime(rs.getTimestamp("reportTime")));
				reportVideo.setReportReason(rs.getString("reportReason"));
				VideoVO video = new VideoVO();
				video.setVideoId(rs.getInt("reportedVideoId"));
				video.setMemberId(rs.getInt("v.memberId"));
				video.setVideoWebsite(rs.getString("videoWebsite"));
				video.setVideoClassName(rs.getString("videoClassName"));
				video.setVideoTitle(rs.getString("videoTitle"));
				video.setVideoUploadTime(ConvertType.convertToLocalTime(rs.getTimestamp("videoUploadTime")));
				video.setVideoDescription(rs.getString("videoDescription"));
				video.setVideoDescriptionModifyTime(ConvertType.convertToLocalTime(rs.getTimestamp("videoDescriptionModifyTime")));
				MemberVO member = new MemberVO();
				member.setMemberId(rs.getInt("v.memberId"));
				member.setMemberAccount(rs.getString("memberAccount"));
				video.setMember(member);
				reportVideo.setVideo(video);
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

	@Override
	public boolean insert(ReportVideoVO reportVideo) {
		Connection conn = null;
		boolean result = false;
		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
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
		ReportVideoDAO dao = new ReportVideoDAOjdbc();
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