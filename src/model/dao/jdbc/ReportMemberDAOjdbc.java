package model.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.vo.ReportMemberVO;

public class ReportMemberDAOjdbc implements ReportMemberDAO {
	private static final String URL = "jdbc:sqlserver://y56pcc16br.database.windows.net:1433;database=iTV";
	private static final String USER = "iTVSoCool@y56pcc16br";
	private static final String PASSWORD = "iTVisgood911";

	private static final String SELECT_BY_ID = "select * from ReportMember where reportedMemberId = ?";

	@Override
	public List<ReportMemberVO> select(int reportedMemberId) {
		List<ReportMemberVO> list = null;
		ReportMemberVO result = null;
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(SELECT_BY_ID);) {
			stmt.setInt(1, reportedMemberId);
			ResultSet rest = stmt.executeQuery();
			list = new ArrayList<>();
			while (rest.next()) {
				result = new ReportMemberVO();
				result.setOrderId(rest.getInt("orderId"));
				result.setReportedMemberId(rest.getInt("reportedMemberId"));
				result.setReportTime(rest.getTimestamp("reportTime"));
				result.setReportReason(rest.getString("reportReason"));
				list.add(result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	private static final String SELECT_ALL = "select * from ReportMember";

	@Override
	public List<ReportMemberVO> selectAll() {
		List<ReportMemberVO> result = null;
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(SELECT_ALL);
				ResultSet rset = stmt.executeQuery();) {
			result = new ArrayList<ReportMemberVO>();
			while (rset.next()) {
				ReportMemberVO bean = new ReportMemberVO();
				bean.setOrderId(rset.getInt("orderId"));
				bean.setReportedMemberId(rset.getInt("reportedMemberId"));
				bean.setReportTime(rset.getTimestamp("reportTime"));
				bean.setReportReason(rset.getString("reportReason"));
				result.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	private static final String INSERT = "insert into ReportMember(reportedMemberId, reportTime, reportReason) values(?, ?, ?)";

	@Override
	public ReportMemberVO insert(ReportMemberVO bean) {
		ReportMemberVO result = null;
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(INSERT);) {
			if (bean != null) {
				stmt.setInt(1, bean.getReportedMemberId());
				stmt.setTimestamp(2, new java.sql.Timestamp(bean.getReportTime().getTime()));
				stmt.setString(3, bean.getReportReason());
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

	// private static final String UPDATE = "update ReportMember set
	// reportedMemberId=?, reportTime=?, reportReason=? where orderId=?";
	//
	// public ReportMemberVO update(int reportedMemberId, java.util.Date
	// reportTime, String reportReason, int orderId) {
	// ReportMemberVO result = null;
	//
	// Connection conn = null;
	// PreparedStatement stmt = null;
	//
	// try {
	// conn = DriverManager.getConnection(URL, USER, PASSWORD);
	// stmt = conn.prepareStatement(UPDATE);
	//
	// stmt.setInt(1, reportedMemberId);
	//
	// if (reportTime != null) {
	// long time = reportTime.getTime();
	// stmt.setDate(2, new java.sql.Date(time));
	// } else {
	// stmt.setDate(2, null);
	// }
	//
	// stmt.setString(3, reportReason);
	// stmt.setInt(4, orderId);
	//
	// int i = stmt.executeUpdate();
	// if (i == 1) {
	// result = this.select(orderId);
	// }
	// } catch (SQLException e) {
	// e.printStackTrace();
	// } finally {
	// if (conn != null) {
	// try {
	// conn.close();
	// } catch (SQLException e) {
	// e.printStackTrace();
	// }
	// }
	// }
	//
	// return result;
	// }

	private static final String DELETE = "delete from ReportMember where orderId = ?";

	/* (non-Javadoc)
	 * @see model.dao.jdbc.ReportMemberDAO#delete(int)
	 */
	@Override
	public boolean delete(int orderId) {
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
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
		// System.out.println(temp.selectAll());
		System.out.println(temp.select(1));
	}
}
