package model.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.dao.ChannelDAO;
import model.vo.ChannelVO;
import model.vo.MemberVO;
import util.GC;

/**
 * @author iTV小組成員
 *
 */
public class ChannelDAOjdbc implements ChannelDAO {
	private static final String URL = GC.URL;
	private static final String USERNAME = GC.USERNAME;
	private static final String PASSWORD = GC.PASSWORD;


	/**
	 * 查詢某會員設定的某頻道網址
	 * @param memberId 設定頻道表之會員編號
	 * @param channelNo 頻道編號
	 * @return ChannelVO
	 */	
	private static final String SELECT_BY_ID_CHANNELNO = "SELECT c.memberId, c.channelNo, c.broadcastWebsite, m.memberAccount, m.broadcastTitle FROM Channel c join Member m ON c.broadcastWebsite = m.broadcastWebsite where c.memberId = ? and channelNo = ?";

	@Override
	public ChannelVO selectByChannelNo(int memberId, int channelNo) {
		ChannelVO result = null;
		ResultSet rset = null;
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(SELECT_BY_ID_CHANNELNO);) {
			stmt.setInt(1, memberId);
			stmt.setInt(2, channelNo);
			rset = stmt.executeQuery();
			if (rset.next()) {
				result = new ChannelVO();
				result.setMemberId(rset.getInt("memberId"));
				result.setChannelNo(rset.getByte("channelNo"));
				result.setBroadcastWebsite(rset.getString("broadcastWebsite"));
				MemberVO bean = new MemberVO();
				bean.setMemberAccount(rset.getString("memberAccount"));
				bean.setBroadcastTitle(rset.getString("broadcastTitle"));
				result.setMember(bean);
			}
		} catch (SQLException e) {
			System.out.println(e.getErrorCode() + " : " + e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 查詢某會員設定的所有頻道網址
	 * @param memberId 設定頻道表之會員編號
	 * @return List<ChannelVO>
	 */	
	private static final String SELECT_BY_ID = "SELECT c.memberId, c.channelNo, c.broadcastWebsite, m.memberAccount, m.broadcastTitle FROM Channel c join Member m ON c.broadcastWebsite = m.broadcastWebsite where c.memberId = ?";

	@Override
	public List<ChannelVO> selectByMemberId(int memberId) {
		List<ChannelVO> list = null;
		ChannelVO result = null;
		ResultSet rset = null;
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(SELECT_BY_ID);) {
			stmt.setInt(1, memberId);
			rset = stmt.executeQuery();
			list = new ArrayList<ChannelVO>();
			while (rset.next()) {
				result = new ChannelVO();
				result.setMemberId(rset.getInt("memberId"));
				result.setChannelNo(rset.getByte("channelNo"));
				result.setBroadcastWebsite(rset.getString("broadcastWebsite"));
				MemberVO bean = new MemberVO();
				bean.setMemberAccount(rset.getString("memberAccount"));
				bean.setBroadcastTitle(rset.getString("broadcastTitle"));
				result.setMember(bean);
				list.add(result);
			}
		} catch (SQLException e) {
			System.out.println(e.getErrorCode() + " : " + e.getMessage());
			e.printStackTrace();
		}
		return list;
	}

	private static final String SELECT_ALL = "SELECT c.*,m.memberAccount FROM channel c Join member m ON c.memberId = m.memberId ";
	/**
	 * 查詢網站內所有頻道表
	 * @return List<ChannelVO>
	 */	
	@Override

	public List<ChannelVO> selectAll() {
		List<ChannelVO> list = null;
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(SELECT_ALL);
				ResultSet rset = stmt.executeQuery();) {
			list = new ArrayList<ChannelVO>();
			while (rset.next()) {
				ChannelVO bean = new ChannelVO();
				bean.setMemberId(rset.getInt("memberId"));
				bean.setChannelNo(rset.getByte("channelNo"));
				bean.setBroadcastWebsite(rset.getString("broadcastWebsite"));
				list.add(bean);
			}
		} catch (SQLException e) {
			System.out.println(e.getErrorCode() + " : " + e.getMessage());
			e.printStackTrace();
		}
		return list;
	}

	private static final String INSERT = "insert into channel(memberId,channelNo,broadcastWebsite) values (?,?,?)";
	/**
	 * 新增觀看頻道
	 * @param bean 必須包含 <b>memberId</b>、<b>channelNo</b>與<b>broadcastWebsite</b>
	 * @return List<ChannelVO>
	 */	
	@Override
	public int insert(ChannelVO bean) {
		int result = -1;
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(INSERT);) {
			if (bean != null) {
				stmt.setInt(1, bean.getMemberId());
				stmt.setInt(2, bean.getChannelNo());
				stmt.setString(3, bean.getBroadcastWebsite());
				result = stmt.executeUpdate();
			}
		} catch (SQLException e) {
			System.out.println(e.getErrorCode() + " : " + e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	private static final String UPDATE = "update channel set broadcastWebsite=? where memberId=? and channelNo=?";
	/**
	 * 修改頻道表
	 * @param broadcastWebsite 頻道網址
	 * @param memberId 
	 * @param channelNo
	 * @return int 修改成功為1；不成功回傳-1
	 */
	@Override
	public int update(String broadcastWebsite, int memberId, int channelNo) {
		int result = -1;
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(UPDATE);) {
			stmt.setString(1, broadcastWebsite);
			stmt.setInt(2, memberId);
			stmt.setInt(3, channelNo);
			result = stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getErrorCode() + " : " + e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int update(ChannelVO bean) {
		int result = -1;
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(UPDATE);) {
			stmt.setString(1, bean.getBroadcastWebsite());
			stmt.setInt(2, bean.getMemberId());
			stmt.setInt(3, bean.getChannelNo());
			result = stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getErrorCode() + " : " + e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	private static final String DELETE = "delete from channel where memberId=? and channelNo=?";
	/**
	 * 刪除頻道表
	 * @param broadcastWebsite 頻道網址
	 * @param memberId 
	 * @param channelNo
	 * @return int 修改成功為1；不成功回傳-1
	 */
	@Override
	public boolean delete(int memberId, int channelNo) {
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(DELETE);) {
			stmt.setInt(1, memberId);
			stmt.setInt(2, channelNo);
			int i = stmt.executeUpdate();
			if (i == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	private static final String DELETE_ALL = "delete from channel where memberId = ?";

	@Override
	public boolean deleteAll(int memberId) {
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(DELETE_ALL);) {
			stmt.setInt(1, memberId);
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
		// Select
		ChannelDAO dao = new ChannelDAOjdbc();
		ChannelVO channel = dao.selectByChannelNo(1, 5);
		System.out.println(channel);

		// Insert
		// String s = "3";
		//
		// ChannelVO insert = new ChannelVO();
		// insert.setMemberId(3);
		// insert.setChannelNo(Byte.parseByte(s));
		// insert.setBroadcastWebsite("http://nextinnovation.cloudapp.net/ITV/live/kimura");
		//
		// ChannelDAO dao = new ChannelDAOjdbc();
		// ChannelVO list = dao.insert(insert);
		// System.out.println("Insert : " + list.getMemberId());

		// Update
		// String channel = "4";
		//
		// ChannelVO update = new ChannelVO();
		// update.setMemberId(3);
		// update.setChannelNo(Byte.parseByte(channel));
		// update.setBroadcastWebsite("http://nextinnovation.cloudapp.net/ITV/live/kimura");
		//
		// ChannelDAO dao = new ChannelDAOjdbc();
		// ChannelVO list = dao.update(update.getBroadcastWebsite(),
		// update.getMemberId(), update.getChannelNo());
		// System.out.println("Update : " + list.getMemberId());

	}

}
