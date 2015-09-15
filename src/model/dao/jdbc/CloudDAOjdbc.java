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

/**
 * @author iTV小組成員
 *
 */
public class CloudDAOjdbc implements CloudDAO {
	private static final String URL = GC.URL;
	private static final String USERNAME = GC.USERNAME;
	private static final String PASSWORD = GC.PASSWORD;

	private static final String SELECT_ALL = "SELECT * FROM Cloud";
	/**
	 * 查詢所有會員雲端硬碟裡的所有檔案 
	 * @return List<CloudVO>
	 */
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
	/**
	 * 查詢某會員雲端硬碟內所有檔案
	 * 
	 * @param memberId 文章類別名稱
	 * @return true 增加成功; false 增加失敗
	 */	
	@Override
	public List<CloudVO> selectByMemberId(int memberId) {
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

	private static final String SELECT_BY_FILENAME = "SELECT * FROM Cloud WHERE memberId = ? And fileName like ?";

	@Override
	public List<CloudVO> selectByFileName(int memberId, String fileName) {
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

	private static final String SELECT_BY_TIME = "SELECT * FROM Cloud WHERE memberId = ? AND (modifyTime BETWEEN ? AND ? )";

	@Override
	public List<CloudVO> selectByTime(int memberId, java.util.Date fromTime, java.util.Date toTime) {
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

	private static final String SELECT_BY_FILETYPE = "SELECT * FROM Cloud WHERE memberId = ? AND fileType = ?";

	@Override
	public List<CloudVO> selectByFileType(int memberId, String fileType) {
		List<CloudVO> list = null;
		return list;
	}

	private static final String SELECT_BY_FILENAME_AND_TIME = "SELECT * FROM Cloud WHERE memberId = ? AND fileName like ? AND (modifyTime BETWEEN ? AND ? )";

	@Override
	public List<CloudVO> selectByFileNameAndTime(int memberId, String fileName, java.util.Date fromTime,
			java.util.Date toTime) {
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

	private static final String SELECT_BY_FILENAME_AND_FILETYPE = "SELECT * FROM Cloud WHERE memberId = ? AND fileType = ? AND fileName like ?";

	@Override
	public List<CloudVO> selectByFileNameAndFileType(int memberId, String fileName, String fileType) {
		List<CloudVO> list = null;
		return list;
	}

	private static final String SELECT_BY_FILETYPE_AND_TIME = "SELECT * FROM Cloud WHERE memberId = ? AND fileType = ? AND (modifyTime BETWEEN ? AND ? )";

	@Override
	public List<CloudVO> selectByFileTypeAndTime(int memberId, java.util.Date fromTime, java.util.Date toTime,
			String fileType) {
		List<CloudVO> list = null;
		return list;
	}

	private static final String SELECT_BY_FILENAME_FILETYPE_AND_TIME = "SELECT * FROM Cloud WHERE memberId = ? AND fileType = ? AND fileName like ? AND (modifyTime BETWEEN ? AND ? )";

	@Override
	public List<CloudVO> selectByFileNameFileTypeAndTime(int memberId, String fileName, java.util.Date fromTime,
			java.util.Date toTime, String fileType) {
		List<CloudVO> list = null;
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

	private static final String UPDATE_FILE = "UPDATE Cloud SET filePath = ?, fileSize = ?, modifyTime = GETUTCDATE() WHERE fileId = ?";

	@Override
	public int updateFile(String filePath, long fileSize, int fileId) {
		int result = -1;
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(UPDATE_FILE);) {
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
	
	private static final String UPDATE_FILENAME = "UPDATE Cloud SET fileName = ?, filePath = ? WHERE fileId = ?";

	@Override
	public int updateFileName(int fileId, String fileName, String filePath) {
		int result = -1;
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(UPDATE_FILENAME);) {
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

	}
}
