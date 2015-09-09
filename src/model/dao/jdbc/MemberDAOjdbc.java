package model.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.vo.MemberVO;

public class MemberDAOjdbc {
	private final String URL = "jdbc:sqlserver://localhost:1433;database=iTV";
	private final String USERNAME = "sa";
	private final String PASSWORD = "sa123456";
	
	public List<MemberVO> selectAll () {
		List<MemberVO> list = null;
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Member");
				ResultSet rs = stmt.executeQuery();) {
			list = new ArrayList<MemberVO>();
			while(rs.next()) {
				MemberVO member = new MemberVO();
				member.setMemberId(rs.getInt("memberId"));
				member.setMemberAccount(rs.getString("memberAccount"));
				member.setMemberPassword(rs.getBytes("memberPassword"));
				member.setMemberEmail(rs.getString("memberEmail"));
				member.setMemberName(rs.getString("memberName"));
				member.setMemberNickname(rs.getString("memberNickname"));
				member.setMemberBirthday(rs.getDate("memberBirthday"));
				member.setMemberRegisterTime(rs.getDate("memberRegisterTime"));
				member.setMemberSelfIntroduction(rs.getString("memberSelfIntroduction"));
				member.setBroadcastWebsite(rs.getString("broadcastWebsite"));
				member.setBroadcastTitle(rs.getString("broadcastTitle"));
				member.setBroadcastClassName(rs.getString("broadcastClassName"));
				member.setBroadcastWatchTimes(rs.getLong("broadcastWatchTimes"));
				list.add(member);
			}
		} catch (SQLException e) {
			System.out.println(e.getErrorCode() + " : " + e.getMessage());
			e.printStackTrace();
		}
		return list;
	}
	 
	public boolean insert(MemberVO member) {
		return false;
	}

	public boolean update() {
		return false;
	}
	
	public boolean delete() {
		return false;
	}

	public static void main (String[] args) throws SQLException {
		MemberDAOjdbc temp = new MemberDAOjdbc();
		List<MemberVO> list = temp.selectAll();
		System.out.println(list);
	}
}
