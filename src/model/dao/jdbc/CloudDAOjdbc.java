package model.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.dao.CloudDAO;
import model.vo.CloudVO;
import util.GC;

public class CloudDAOjdbc implements CloudDAO {
	private static final String URL = GC.URL;
	private static final String USERNAME = GC.USERNAME;
	private static final String PASSWORD = GC.PASSWORD;

	private static final String SELECT_ALL = "SELECT * FROM Cloud";

	@Override
	public List<CloudVO> selectAll() {
		List<CloudVO> list = null;
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(SELECT_ALL);
				ResultSet rs = stmt.executeQuery();) {
			list = new ArrayList<CloudVO>();
			while (rs.next()) {
				CloudVO file = new CloudVO();
				file.setFileId(rs.getInt("fileId"));
				file.setMemberId(rs.getInt("memberId"));
				file.setFileName(rs.getString("fileName"));
				file.setFileType(rs.getString("fileType"));
				file.setFilePath(rs.getString("filePath"));
				file.setFileSize(rs.getLong("fileSize"));
				file.setModifyTime(rs.getTimestamp("modifyTime"));
				list.add(file);
			}
		} catch (SQLException e) {
			System.out.println(e.getErrorCode() + " : " + e.getMessage());
			e.printStackTrace();
		}
		return list;
	}

	private static final String SELECT_BY_MEMBERID = "SELECT * FROM Cloud WHERE memberId = ?";

	@Override
	public List<CloudVO> selectAll(int memberId) {
		List<CloudVO> list = null;
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(SELECT_BY_MEMBERID);) {
			stmt.setInt(1, memberId);
			ResultSet rs = stmt.executeQuery();
			list = new ArrayList<CloudVO>();
			while (rs.next()) {
				CloudVO file = new CloudVO();
				file.setFileId(rs.getInt("fileId"));
				file.setMemberId(rs.getInt("memberId"));
				file.setFileName(rs.getString("fileName"));
				file.setFileType(rs.getString("fileType"));
				file.setFilePath(rs.getString("filePath"));
				file.setFileSize(rs.getLong("fileSize"));
				file.setModifyTime(rs.getTimestamp("modifyTime"));
				list.add(file);
			}
		} catch (SQLException e) {
			System.out.println(e.getErrorCode() + " : " + e.getMessage());
			e.printStackTrace();
		}
		return list;
	}

	private static final String SELECT_BY_FILENAME = "SELECT * FROM Cloud WHERE fileName like ?";

	@Override
	public List<CloudVO> select(String fileName) {
		List<CloudVO> list = null;
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(SELECT_BY_FILENAME);) {
			stmt.setString(1, "%" + fileName + "%");
			ResultSet rs = stmt.executeQuery();
			list = new ArrayList<CloudVO>();
			while (rs.next()) {
				CloudVO file = new CloudVO();
				file.setFileId(rs.getInt("fileId"));
				file.setMemberId(rs.getInt("memberId"));
				file.setFileName(rs.getString("fileName"));
				file.setFileType(rs.getString("fileType"));
				file.setFilePath(rs.getString("filePath"));
				file.setFileSize(rs.getLong("fileSize"));
				file.setModifyTime(rs.getTimestamp("modifyTime"));
				list.add(file);
			}
		} catch (SQLException e) {
			System.out.println(e.getErrorCode() + " : " + e.getMessage());
			e.printStackTrace();
		}
		return list;
	}

	private static final String SELECT_BY_TIME = "SELECT * FROM Cloud WHERE modifyTime BETWEEN ? AND ?";

	@Override
	public List<CloudVO> select(java.util.Date fromTime, java.util.Date toTime) {
		List<CloudVO> list = null;
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(SELECT_BY_TIME);) {
			stmt.setTimestamp(1, new java.sql.Timestamp(fromTime.getTime()));
			stmt.setTimestamp(2, new java.sql.Timestamp(toTime.getTime()));
			ResultSet rs = stmt.executeQuery();
			list = new ArrayList<CloudVO>();
			while (rs.next()) {
				CloudVO file = new CloudVO();
				file.setFileId(rs.getInt("fileId"));
				file.setMemberId(rs.getInt("memberId"));
				file.setFileName(rs.getString("fileName"));
				file.setFileType(rs.getString("fileType"));
				file.setFilePath(rs.getString("filePath"));
				file.setFileSize(rs.getLong("fileSize"));
				file.setModifyTime(rs.getTimestamp("modifyTime"));
				list.add(file);
			}
		} catch (SQLException e) {
			System.out.println(e.getErrorCode() + " : " + e.getMessage());
			e.printStackTrace();
		}
		return list;
	}

	private static final String SELECT_BY_FILENAME_AND_TIME = "SELECT * FROM Cloud WHERE fileName like ? and modifyTime BETWEEN ? AND ?";

	@Override
	public List<CloudVO> select(String fileName, java.util.Date fromTime, java.util.Date toTime) {
		List<CloudVO> list = null;
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(SELECT_BY_FILENAME_AND_TIME);) {
			stmt.setString(1, "%" + fileName + "%");
			stmt.setTimestamp(2, new java.sql.Timestamp(fromTime.getTime()));
			stmt.setTimestamp(3, new java.sql.Timestamp(toTime.getTime()));
			ResultSet rs = stmt.executeQuery();
			list = new ArrayList<CloudVO>();
			while (rs.next()) {
				CloudVO file = new CloudVO();
				file.setFileId(rs.getInt("fileId"));
				file.setMemberId(rs.getInt("memberId"));
				file.setFileName(rs.getString("fileName"));
				file.setFileType(rs.getString("fileType"));
				file.setFilePath(rs.getString("filePath"));
				file.setFileSize(rs.getLong("fileSize"));
				file.setModifyTime(rs.getTimestamp("modifyTime"));
				list.add(file);
			}
		} catch (SQLException e) {
			System.out.println(e.getErrorCode() + " : " + e.getMessage());
			e.printStackTrace();
		}
		return list;
	}

	private static final String INSERT = "INSERT INTO Cloud(memberId, fileName, fileType, filePath, fileSize) VALUES (?, ?, ?, ?, ?)";

	@Override
	public int insert(CloudVO file) {
		int result = -1;
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(INSERT);) {
			stmt.setInt(1, file.getMemberId());
			stmt.setString(2, file.getFileName());
			stmt.setString(3, file.getFileType());
			stmt.setString(4, file.getFilePath());
			stmt.setLong(5, file.getFileSize());
			result = stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getErrorCode() + " : " + e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	private static final String UPDATE = "UPDATE Cloud SET fileName = ?, fileType = ?, filePath = ?, fileSize = ?, modifyTime = GETUTCDATE() WHERE fileId = ?";

	@Override
	public int update(CloudVO file) {
		int result = -1;
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(UPDATE);) {
			stmt.setString(1, file.getFileName());
			stmt.setString(2, file.getFileType());
			stmt.setString(3, file.getFilePath());
			stmt.setLong(4, file.getFileSize());
			stmt.setInt(5, file.getFileId());
			result = stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getErrorCode() + " : " + e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	private static final String DELETE = "DELETE FROM Cloud WHERE fileId = ?";

	@Override
	public int delete(int fileId) {
		int result = -1;
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(DELETE);) {
			stmt.setInt(1, fileId);
			result = stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getErrorCode() + " : " + e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	public static void main(String[] args) {
		CloudDAO temp = new CloudDAOjdbc();

		// List<CloudVO> all = temp.selectAll();
		// System.out.println("SELECT_ALL result = " + all);

		// CloudVO file1 = new CloudVO();
		// file1.setMemberId(4);
		// file1.setFileName("ohohohohh");
		// file1.setFilePath("dhdhdhdh");
		// file1.setFileSize(7788);
		// file1.setFileType("khkhkh");
		// file1.setModifyTime(new java.util.Date());
		// int i = temp.insert(file1);
		// System.out.println("INSERT result = " + i);

		// CloudVO file2 = new CloudVO();
		// file2.setFileId(13);
		// file2.setFileName("XXX");
		// file2.setFilePath("OOO");
		// file2.setFileSize(5566);
		// file2.setFileType("VVV");
		// file2.setModifyTime(new java.util.Date(5566));
		// int j = temp.update(file2);
		// System.out.println("UPDATE result = " + j);
		//
		// CloudVO file3 = new CloudVO();
		// file3.setFileId(13);
		// int k = temp.delete(file3);
		// System.out.println("DELETE result = " + k);

		List<CloudVO> list = temp.select("檔案1");
		System.out.println(list);

		List<CloudVO> list2 = temp.select(new java.sql.Timestamp(5464646L), new java.sql.Timestamp(46535436436L));
		System.out.println(list2);
	}
}
