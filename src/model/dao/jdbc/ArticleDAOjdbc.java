package model.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import model.dao.ArticleDAO;
import model.vo.ArticleClassVO;
import model.vo.ArticleVO;

public class ArticleDAOjdbc implements ArticleDAO {
	 private final String URL =
	 "jdbc:sqlserver://y56pcc16br.database.windows.net:1433;database=iTV";
	 private final String USERNAME = "iTVSoCool";//iTVSoCool@y56pcc16br
	 private final String PASSWORD = "iTVisgood911";

	private static final String SELECT_ALL = "SELECT articleId,memberId,subclassNo,articleTitle,articleContent,publishTime,modifyTime,watchTimes FROM article";

	/* (non-Javadoc)
	 * @see model.dao.jdbc.ArticleDAO#selectAll()
	 */
	@Override
	public List<ArticleVO> selectAll() {
		ArticleVO avo;
		List<ArticleVO> avos = null;
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement pstmt = conn.prepareStatement(SELECT_ALL);
				ResultSet rs = pstmt.executeQuery();) {
			avos = new ArrayList<ArticleVO>();
			while (rs.next()) {
				avo = new ArticleVO();
				avo.setArticleId(rs.getInt("articleId"));
				avo.setMemberId(rs.getInt("memberId"));
				avo.setSubclassNo(rs.getString("subclassNo"));
				avo.setArticleTitle(rs.getString("articleTitle"));
				avo.setArticleContent(rs.getString("articleContent"));
				avo.setPublishTime(rs.getTimestamp("publishTime"));
				avo.setModifyTime(rs.getTimestamp("modifyTime"));
				avo.setWatchTimes(rs.getLong("watchTimes"));
				avos.add(avo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return avos;
	}

	private static final String INSERT = "INSERT INTO Article (memberId, subclassNo,articleTitle,articleContent) VALUES (?,?,?,?)";

	/* (non-Javadoc)
	 * @see model.dao.jdbc.ArticleDAO#insert(model.vo.ArticleVO)
	 */
	@Override
	public boolean insert(ArticleVO article) {
		boolean result = false;
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement pstmt = conn.prepareStatement(INSERT);) {
			pstmt.setInt(1, article.getMemberId());
			pstmt.setString(2, article.getSubclassNo());
			pstmt.setString(3, article.getArticleTitle());
			pstmt.setString(4, article.getArticleContent());
			int updateCount = pstmt.executeUpdate();
			if (updateCount == 1) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	// 必須是發文的作者本人才能修改該篇文章
	private static final String UPDATE = "UPDATE Article SET subclassNo=?,articleTitle=?,articleContent=?,modifyTime=?WHERE articleId=? AND memberId=?";

	/* (non-Javadoc)
	 * @see model.dao.jdbc.ArticleDAO#update(model.vo.ArticleVO)
	 */
	@Override
	public boolean update(ArticleVO article) {
		boolean result = false;
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement pstmt = conn.prepareStatement(UPDATE);) {
			pstmt.setString(1, article.getSubclassNo());
			pstmt.setString(2, article.getArticleTitle());
			pstmt.setString(3, article.getArticleContent());
			long modify = article.getModifyTime().getTime();
			pstmt.setTimestamp(4, new java.sql.Timestamp(modify));
			pstmt.setInt(5, article.getArticleId());
			pstmt.setInt(6, article.getMemberId());

			int updateCount = pstmt.executeUpdate();
			if (updateCount == 1) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	// 同理，必須是發文的作者本人才能刪除該篇文章
	private static final String DELETE = "DELETE FROM article WHERE articleId=? AND memberId=?";

	/* (non-Javadoc)
	 * @see model.dao.jdbc.ArticleDAO#delete(int, int)
	 */
	@Override
	public boolean delete(int articleId, int memberId) {
		boolean result = false;
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement pstmt = conn.prepareStatement(DELETE);) {
			pstmt.setInt(1, articleId);
			pstmt.setInt(2, memberId);
			int updateCount = pstmt.executeUpdate();
			if (updateCount == 1) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	// 測試程式
	public static void main(String[] args) throws SQLException, ParseException {
		ArticleDAO temp = new ArticleDAOjdbc();
		ArticleVO avo = new ArticleVO();
		// System.out.println(temp.selectAll());
		// System.out.println(temp.delete(13, 2));
		avo.setMemberId(1);
		avo.setArticleId(14);
		avo.setSubclassNo("A");
		avo.setArticleTitle("Dear");
		avo.setArticleContent("I hate the world");
		// System.out.println(temp.insert(avo));
		System.out.println(temp.update(avo));

	}
}
