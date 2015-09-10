package model.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import model.vo.MemberVO;

public class MemberDAOjdbc {
	private final String URL = "jdbc:sqlserver://y56pcc16br.database.windows.net:1433;database=iTV";
	private final String USERNAME = "iTVSoCool";//iTVSoCool@y56pcc16br
	private final String PASSWORD = "iTVisgood911";
	
	private static final String INSERT=
			"INSERT INTO member (memberAccount,memberPassword,memberEmail,broadcastWebsite) VALUES (?, cast( ? as varbinary(50)), ?,?)";
	public int insert(MemberVO member) throws SQLException {
		//要先檢查bean是否為null
		int updateCount=0;
		try (Connection  conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
			PreparedStatement pstmt = conn.prepareStatement(INSERT);){						
			pstmt.setString(1, member.getMemberAccount());
			pstmt.setBytes(2, member.getMemberPassword());
			pstmt.setString(3, member.getMemberEmail());
			pstmt.setString(4,"http://iTV.com/broadcast/"+member.getMemberAccount());
			updateCount = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return updateCount;
	}
	//用戶只需要輸入密碼跟信箱，自動產生memberAccount和broadcastWebsite網址
	//問題在於若不同人在不同網站申請相同帳號的信箱，後者會無法申請，因為自動產生的broadcastWebsite會重複
	private static final String INSERT2=
			"INSERT INTO member (memberEmail,memberPassword,memberAccount,broadcastWebsite) VALUES (?,cast( ? as varbinary(50)), ?,?)";
	public int insert2(MemberVO member)  {
		//要先檢查bean是否為null
		int updateCount = 0;
		try(Connection  conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
				PreparedStatement pstmt = conn.prepareStatement(INSERT2);) {
			pstmt.setString(1, member.getMemberEmail());			
			pstmt.setBytes(2, member.getMemberPassword());
			pstmt.setString(3, member.getMemberEmail().substring(0,member.getMemberEmail().indexOf("@")));
			pstmt.setString(4,"http://iTV.com/broadcast/"+member.getMemberEmail().substring(0,member.getMemberEmail().indexOf("@")));
			updateCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return updateCount;
	}		
	
	private static final String SELECT_ALL_MEMBER=
			"SELECT memberId,memberAccount, broadcastWebsite FROM member ORDER BY memberAccount";
	public List<MemberVO> getMemberList()  {
		List<MemberVO> members=null;
		MemberVO member = null;
		try(Connection  conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
			PreparedStatement pstmt = conn.prepareStatement(SELECT_ALL_MEMBER);
			ResultSet rs = pstmt.executeQuery();) {
			members = new ArrayList<MemberVO>();
			while (rs.next()) {
				member = new MemberVO();
				member.setMemberId(rs.getInt("memberId"));//會抓但不會顯示在jsp上
				member.setMemberAccount(rs.getString("memberAccount"));
				member.setBroadcastWebsite(rs.getString("broadcastWebsite"));
				members.add(member);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return members;
	}
	
	private static final String GET_ID="SELECT memberId FROM member WHERE memberAccount=?";		
	public int getId(String memberAccount) {
		int result =0;
		try (Connection  conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
			PreparedStatement pstmt=conn.prepareStatement(GET_ID);
			ResultSet rs=pstmt.executeQuery();){			
			pstmt.setString(1, memberAccount);
			if (rs.next()) {
				result=rs.getInt("memberId");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return result;
	}
	
	//新增
	private static final String UPDATE_INFO=
			"UPDATE member SET memberPassword=?, memberEmail=?, memberFB=?, memberGoogle=?, memberTwitter=?, memberNickname=?,"
			+"memberBirthday=?,memberPhoto=?,memberSelfIntroduction=?,broadcastTitle=?,broadcastClassName=?,"
			+"broadcastTime=?,broadcastDescription=? WHERE memberId=?";
	public int update(MemberVO member) {
		//要先檢查bean是否為null
		int updateCount = 0;
		try (Connection  conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
			PreparedStatement pstmt = conn.prepareStatement(UPDATE_INFO);){
			pstmt.setBytes(1, member.getMemberPassword());
			pstmt.setString(2, member.getMemberEmail());
			pstmt.setString(3, member.getMemberFB());
			pstmt.setString(4, member.getMemberGoogle());
			pstmt.setString(5, member.getMemberTwitter());
			pstmt.setString(6, member.getMemberNickname());
			long birth=member.getMemberBirthday().getTime();			
			pstmt.setDate(7, new java.sql.Date(birth));
			pstmt.setBytes(8, member.getMemberPhoto());
			pstmt.setString(9, member.getMemberSelfIntroduction());
			pstmt.setString(10, member.getBroadcastTitle());
			pstmt.setString(11, member.getBroadcastClassName());
			long broad=member.getBroadcastTime().getTime();
			pstmt.setDate(12, new java.sql.Date(broad));
			pstmt.setString(13, member.getBroadcastDescription());
			pstmt.setInt(14,member.getMemberId());
			updateCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return updateCount;		
	}
	
	
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
	
	private static final String SELECT_BY_ID=
			"SELECT memberId,memberAccount,memberEmail,memberFB,memberGoogle,memberTwitter,memberName,"
			+"memberNickname,memberBirthday,memberPhoto,memberRegisterTime,memberSelfIntroduction,"
			+"broadcastWebsite,broadcastTitle,broadcastClassName,broadcastTime,broadcastDescription,"
			+"broadcastWatchTimes FROM member WHERE memberId=?";
	
	public MemberVO findByPK(int memberId) {
		MemberVO member=null;
		try (Connection  conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
			PreparedStatement pstmt = conn.prepareStatement(SELECT_BY_ID);
			ResultSet rs = pstmt.executeQuery();){			
			pstmt.setInt(1, memberId);			
			if (rs.next()) {
				member = new MemberVO();			
				member.setMemberId(rs.getInt("memberId"));
				member.setMemberAccount(rs.getString("memberAccount"));
				member.setMemberEmail(rs.getString("memberEmail"));
				member.setMemberFB(rs.getString("memberFB"));
				member.setMemberGoogle(rs.getString("memberGoogle"));
				member.setMemberTwitter(rs.getString("memberTwitter"));
				member.setMemberName(rs.getString("memberName"));
				member.setMemberNickname(rs.getString("memberNickname"));
				member.setMemberBirthday(rs.getDate("memberBirthday"));
				member.setMemberPhoto(rs.getBytes("memberPhoto"));
				member.setMemberRegisterTime(rs.getDate("memberRegisterTime"));
				member.setMemberSelfIntroduction(rs.getString("memberSelfIntroduction"));
				member.setBroadcastWebsite(rs.getString("broadcastWebsite"));
				member.setBroadcastTitle(rs.getString("broadcastTitle"));
				member.setBroadcastClassName(rs.getString("broadcastClassName"));
				member.setBroadcastTime(rs.getDate("broadcastTime"));
				member.setBroadcastDescription(rs.getString("broadcastDescription"));
				member.setBroadcastWatchTimes(rs.getLong("broadcastWatchTimes"));				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return member;
	}

	
	//測試程式
	public static void main (String[] args) throws SQLException, ParseException {
		MemberDAOjdbc temp = new MemberDAOjdbc();		
		//memberDao的insert，與insert的差異在於用戶需輸入memberAccount
//		MemberVO member1 = new MemberVO();
//		member1.setMemberAccount("Micky Wong");
//		member1.setMemberPassword("micky".getBytes());
//		member1.setMemberEmail("MWong@gmail.com");
//		int count1 = temp.insert(member1);
//		System.out.println("insert " + count1 + " rows");
		
		//memberDao的insert2，與insert的差異在於用戶少輸入memberAccount
//		MemberVO member2 = new MemberVO();
//		member2.setMemberPassword("shu".getBytes());
//		member2.setMemberEmail("crazy@gmail.com");
//		int count2 = temp.insert2(member2);
//		System.out.println("insert2 " + count2 + " rows");
		
		//memberDao的get member list 
		List<MemberVO> members = temp.getMemberList();
		for (MemberVO member : members){ 
			System.out.print(member.getMemberId()+", ");
			System.out.print(member.getMemberAccount() + ", ");
			System.out.println(member.getBroadcastWebsite());
		}			

		//memberDao的getId
		System.out.print(temp.getId("Shekx"));
		
		//memberDao的find by PrimaryKey
		MemberVO member3 = temp.findByPK(3);
		System.out.println("memberId ="+member3.getMemberId());
		System.out.println("memberAccount = "+member3.getMemberAccount());
		System.out.println("memberPassword = "+"怎麼可以告訴你");
		System.out.println("memberEmail = "+member3.getMemberEmail());
		System.out.println("memberFB = "+member3.getMemberFB());
		System.out.println("memberGoogle = "+member3.getMemberGoogle());
		System.out.println("memberTwitter = "+member3.getMemberTwitter());
		System.out.println("memberName = "+member3.getMemberName());
		System.out.println("memberNickname = "+member3.getMemberNickname());
		System.out.println("memberBirthday = "+member3.getMemberBirthday());
		System.out.println("memberPhoto = "+member3.getMemberPhoto());
		System.out.println("memberRegisterTime = "+member3.getMemberRegisterTime());
		System.out.println("memberSelfIntroduction = "+member3.getMemberSelfIntroduction());
		System.out.println("broadcastWebsite = "+member3.getBroadcastWebsite());
		System.out.println("broadcastTitle = "+member3.getBroadcastTitle());
		System.out.println("broadcastClassName = "+member3.getBroadcastClassName());
		System.out.println("broadcastTime = "+member3.getBroadcastTime());
		System.out.println("broadcastDescription = "+member3.getBroadcastDescription());
		System.out.println("broadcastWatchTimes = "+member3.getBroadcastWatchTimes());
		
		// update
		MemberVO member4 = new MemberVO();
		member4.setMemberPassword("normal".getBytes());
		member4.setMemberEmail("normal@yahoo.com");
		member4.setMemberFB("abuse");
		member4.setMemberGoogle("abnormal@gmail.com");
		member4.setMemberTwitter("cure");
		member4.setMemberNickname("insane");
		java.text.SimpleDateFormat converter=new java.text.SimpleDateFormat("yyyy-MM-dd");		
		member4.setMemberBirthday(converter.parse("2015-2-29"));
		//member4.setMemberPhoto("".getBytes());
		member4.setMemberSelfIntroduction("Why so serious?");
		member4.setBroadcastTitle("crazy world");
		member4.setBroadcastClassName("life");
		member4.setBroadcastTime(converter.parse("2015-11-30"));
		member4.setBroadcastDescription("I try to be a good person");
		member4.setMemberId(10);
		int count3 =temp.update(member4); 
		System.out.println("update " + count3 +" rows");
		
		List<MemberVO> list = temp.selectAll();
		System.out.println(list);
		
	}
}
