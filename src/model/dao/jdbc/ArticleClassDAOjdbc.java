package model.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.dao.ArticleClassDAO;
import model.vo.ArticleClassVO;
import util.GC;

/**
 * @author iTV小組成員
 *
 */
public class ArticleClassDAOjdbc implements ArticleClassDAO {
	private static final String URL = GC.URL;
	private static final String USERNAME = GC.USERNAME;
	private static final String PASSWORD = GC.PASSWORD;
	
	private static final String SELECT_ALL = "SELECT subclassNo,subclassName,className FROM articleclass";
	/**
	 * 查詢資料庫內所有文章
	 * @return List<ArticleClassVO>
	 */
	@Override
	public List<ArticleClassVO> selectAll() {
		ArticleClassVO acvo;
		List<ArticleClassVO> acvos = new ArrayList<ArticleClassVO>();
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement pstmt = conn.prepareStatement(SELECT_ALL);) {
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				acvo = new ArticleClassVO();
				acvo.setClassName(rs.getString("subclassNo"));
				acvo.setSubclassName(rs.getString("subclassName"));
				acvo.setSubclassNo(rs.getString("className"));
				acvos.add(acvo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return acvos;
	}

	private static final String SELECT = "SELECT subclassNo,subclassName,className FROM articleclass WHERE subclassNo=?";
	/**
	 * 以文章子分類代碼查詢該分類下資料庫內的所有文章
	 * @param subclassNo 文章子分類代碼
	 * @return List<ArticleClassVO>
	 */
	@Override
	public List<ArticleClassVO> select(String subclassNo) {
		ArticleClassVO acvo = null;
		List<ArticleClassVO> acvos = new ArrayList<ArticleClassVO>();
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement pstmt = conn.prepareStatement(SELECT);) {
			pstmt.setString(1, subclassNo);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				acvo = new ArticleClassVO();
				acvo.setSubclassNo(rs.getString("subclassNo"));
				acvo.setSubclassName(rs.getString("subclassName"));
				acvo.setClassName(rs.getString("className"));
				acvos.add(acvo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return acvos;
	}

	private static final String INSERT = "INSERT INTO ArticleClass VALUES (?,?,?)";
	/**
	 * 新增文章分類、子分類代碼與子分類名稱
	 * @param  bean 必須包含<b>subclassNo</b>、<b>subclassName</b>與<b>className</b>
	 * @return true 新增成功；false 新增失敗
	 */
	@Override
	public boolean insert(ArticleClassVO bean) {
		boolean result = false;
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement pstmt = conn.prepareStatement(INSERT);) {
			pstmt.setString(1, bean.getSubclassNo());
			pstmt.setString(2, bean.getSubclassName());
			pstmt.setString(3, bean.getClassName());
			int updateCount = pstmt.executeUpdate();
			if (updateCount == 1) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	private static final String UPDATE = "UPDATE ArticleClass SET SubclassName=?,ClassName=? WHERE subclassNo=?";
	/**
	 * 修改文章分類、子分類代碼或子分類名稱
	 * @param  bean 必須包含<b>subclassNo</b>
	 * @return true 修改成功；false 修改失敗
	 */
	@Override
	public boolean update(ArticleClassVO bean) {
		boolean result = false;
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement pstmt = conn.prepareStatement(UPDATE);) {
			if (bean != null) {
				pstmt.setString(1, bean.getSubclassName());
				pstmt.setString(2, bean.getClassName());
				pstmt.setString(3, bean.getSubclassNo());
				int updateCount = pstmt.executeUpdate();
				if (updateCount == 1) {
					result = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	private static final String DELETE = "DELETE FROM ArticleClass WHERE subclassNo=?";
	/**
	 * 刪除文章分類、子分類代碼與子分類名稱
	 * @param  subclassNo 文章子分類代碼
	 * @return true 刪除成功；false 刪除失敗
	 */
	@Override
	public boolean delete(String subclassNo) {
		boolean result = false;
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement pstmt = conn.prepareStatement(DELETE);) {
			pstmt.setString(1, subclassNo);
			int updateCount = pstmt.executeUpdate();
			if (updateCount >= 1) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// 測試程式
	public static void main(String[] args) throws SQLException {
		ArticleClassDAO temp = new ArticleClassDAOjdbc();
		ArticleClassVO acvo = new ArticleClassVO();
		acvo.setSubclassNo("o");
		acvo.setSubclassName("聯航");
		acvo.setClassName("交通");
		// System.out.println(temp.selectAll());
		// System.out.println(temp.select("o"));
		// System.out.println(temp.insert(acvo));
		System.out.println(temp.update(acvo));
		// System.out.println(temp.delete("o"));

	}

}
