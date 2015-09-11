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

public class ChannelDAOjdbc implements ChannelDAO {
	private static final String URL = "jdbc:sqlserver://y56pcc16br.database.windows.net:1433;database=iTV";
	private static final String USERNAME = "iTVSoCool@y56pcc16br";
	private static final String PASSWORD = "iTVisgood911";

	private static final String SELECT_BY_ID_CHANNELNO = "select * from channel where memberId=? and channelNo=?";

	@Override
	public ChannelVO select(int memberId, int channelNo) {
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
			}
		} catch (SQLException e) {
			System.out.println(e.getErrorCode() + " : " + e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	private static final String SELECT_BY_ID = "select * from channel where memberId = ?";

	@Override
	public List<ChannelVO> selectAll(int memberId) {
		List<ChannelVO> list = null;
		ChannelVO result = null;
		ResultSet rset = null;
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(SELECT_BY_ID);) {
			stmt.setInt(1, memberId);
			rset = stmt.executeQuery();
			list = new ArrayList<ChannelVO>();
			if (rset.next()) {
				result = new ChannelVO();
				result.setMemberId(rset.getInt("memberId"));
				result.setChannelNo(rset.getByte("channelNo"));
				result.setBroadcastWebsite(rset.getString("broadcastWebsite"));
				list.add(result);
			}
		} catch (SQLException e) {
			System.out.println(e.getErrorCode() + " : " + e.getMessage());
			e.printStackTrace();
		}
		return list;
	}

	private static final String SELECT_ALL = "select * from channel";

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

	private static final String DELETE = "delete from channel where memberId=? and channelNo=?";

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

	public static void main(String[] args) {
		// Select
		ChannelDAO dao = new ChannelDAOjdbc();
		ChannelVO channel = dao.select(1, 5);
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
