package model.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.dao.ShowDAO;
import model.vo.ShowVO;
import util.ConvertType;
import util.GC;

public class ShowDAOjdbc implements ShowDAO {
	private static final String URL = GC.URL;
	private static final String USERNAME = GC.USERNAME;
	private static final String PASSWORD = GC.PASSWORD;

	private static final String SELECT_BY_ID = "select * from show where memberId = ?";

	@Override
	public List<ShowVO> select(int memberId) {
		ShowVO result = null;
		List<ShowVO> list = null;
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(SELECT_BY_ID);) {
			stmt.setInt(1, memberId);
			ResultSet rset = stmt.executeQuery();
			list = new ArrayList<ShowVO>();
			while (rset.next()) {
				result = new ShowVO();
				result.setMemberId(rset.getInt("memberId"));
				result.setShowTime(ConvertType.convertToLocalTime(rset.getTimestamp("showTime")));
				result.setWebsite(rset.getString("website"));
				list.add(result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	private static final String SELECT_ALL = "select * from show";

	@Override
	public List<ShowVO> selectAll() {
		List<ShowVO> list = null;
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(SELECT_ALL);
				ResultSet rset = stmt.executeQuery();) {
			list = new ArrayList<ShowVO>();
			while (rset.next()) {
				ShowVO bean = new ShowVO();
				bean.setMemberId(rset.getInt("memberId"));
				bean.setShowTime(ConvertType.convertToLocalTime(rset.getTimestamp("showTime")));
				bean.setWebsite(rset.getString("website"));
				list.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	private static final String INSERT = "insert into show(memberId,showTime,website) values (?, ?, ?)";

	@Override
	public int insert(ShowVO bean) {
		int result = -1;
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(INSERT);) {
			if (bean != null) {
				stmt.setInt(1, bean.getMemberId());
				java.util.Date showTime = bean.getShowTime();
				if (showTime != null) {
					long time = showTime.getTime();
					stmt.setTimestamp(2, new java.sql.Timestamp(time));
				} else {
					stmt.setDate(2, null);
				}
				stmt.setString(3, bean.getWebsite());
				result = stmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	private static final String UPDATE = "update show set showTime = ?, website = ? where memberId = ? and showTime = ?";

	@Override
	public int update(java.util.Date showTime, String website, int memberId, java.util.Date showTimed) {
		int result = -1;
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(UPDATE);) {
			if (showTime != null) {
				long time = showTime.getTime();
				stmt.setTimestamp(1, new java.sql.Timestamp(time));
			} else {
				stmt.setTimestamp(1, null);
			}
			stmt.setString(2, website);
			stmt.setInt(3, memberId);
			stmt.setTimestamp(4, new java.sql.Timestamp(showTimed.getTime()));
			result= stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	private static final String DELETE = "delete from show where memberId = ?, showTime = ?";

	@Override
	public boolean delete(int memberId, java.util.Date showTime) {
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(DELETE);) {
			stmt.setInt(1, memberId);
			stmt.setTimestamp(2, new java.sql.Timestamp(showTime.getTime()));
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
		// Date date = new Date();
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// System.out.println(sdf.format(date));
		// java.sql.Timestamp d = new java.sql.Timestamp(date.getTime());
		// System.out.println(d);

		// Select
		// ShowDAO dao = new ShowDAOjdbc();
		// ShowVO list = dao.select(1);
		// System.out.println(list.getShowTime());

		// Insert
		// String showTime = "2015-08-29 22:00:00";
		//
		// ShowVO insert = new ShowVO();
		// insert.setMemberId(3);
		// insert.setShowTime(java.sql.Timestamp.valueOf(showTime));
		// insert.setWebsite("http://nextinnovation.cloudapp.net/ITV/live/kimura");
		//
		// ShowDAO dao = new ShowDAOjdbc();
		// ShowVO list = dao.insert(insert);
		// System.out.println("Insert : " + list.getMemberId());

		// Update
		String showTime = "2015-09-04 20:30:00";

		ShowVO update = new ShowVO();
		update.setMemberId(3);
		update.setShowTime(java.sql.Timestamp.valueOf(showTime));
		update.setWebsite("http://nextinnovation.cloudapp.net/ITV/live/kimura");

		
		ShowDAO dao = new ShowDAOjdbc();
	}
}
