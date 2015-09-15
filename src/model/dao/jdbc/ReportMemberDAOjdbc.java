package model.dao.jdbc;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.dao.ReportMemberDAO;
import model.vo.MemberVO;
import model.vo.ReportMemberVO;
import util.ConvertType;
import util.GC;

public class ReportMemberDAOjdbc implements ReportMemberDAO {
	private static final String URL = GC.URL;
	private static final String USERNAME = GC.USERNAME;
	private static final String PASSWORD = GC.PASSWORD;

	private static final String SELECT_ALL = "SELECT orderId, reportedMemberId, reportTime, reportReason, "
			+ "memberAccount, memberName, memberNickname, memberPhoto, broadcastTitle, broadcastClassName, "
			+ "broadcastDescription FROM ReportMember JOIN Member ON reportedMemberId = memberId "
			+ "ORDER BY reportTime DESC";

	@Override
	public List<ReportMemberVO> selectAll() {
		List<ReportMemberVO> result = null;
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(SELECT_ALL);
				ResultSet rset = stmt.executeQuery();) {
			result = new ArrayList<ReportMemberVO>();
			while (rset.next()) {
				ReportMemberVO bean = new ReportMemberVO();
				bean.setOrderId(rset.getInt("orderId"));
				bean.setReportedMemberId(rset.getInt("reportedMemberId"));
				bean.setReportTime(ConvertType.convertToLocalTime(rset.getTimestamp("reportTime")));
				bean.setReportReason(rset.getString("reportReason"));
				MemberVO member = new MemberVO();
				member.setMemberId(rset.getInt("reportedMemberId"));
				member.setMemberAccount(rset.getString("memberAccount"));
				member.setMemberName(rset.getString("memberName"));
				member.setMemberNickname(rset.getString("memberNickname"));
				Blob b = rset.getBlob("memberPhoto");
				member.setMemberPhoto(b.getBytes(1, (int)b.length()));
				member.setBroadcastTitle(rset.getString("broadcastTitle"));
				member.setBroadcastClassName(rset.getString("broadcastClassName"));
				member.setBroadcastDescription(rset.getString("broadcastDescription"));
				bean.setMember(member);
				result.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	private static final String INSERT = "insert into ReportMember(reportedMemberId, reportReason) values(?, ?)";

	@Override
	public ReportMemberVO insert(ReportMemberVO bean) {
		ReportMemberVO result = null;
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(INSERT);) {
			if (bean != null) {
				stmt.setInt(1, bean.getReportedMemberId());
				stmt.setString(2, bean.getReportReason());
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

	private static final String DELETE = "delete from ReportMember where orderId = ?";

	@Override
	public boolean delete(int orderId) {
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(DELETE);) {
			stmt.setInt(1, orderId);
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
		ReportMemberDAO temp = new ReportMemberDAOjdbc();
	}
}
