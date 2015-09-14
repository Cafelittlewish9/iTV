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
import model.vo.ArticleVO;
import util.GC;

public class ArticleDAOjdbc implements ArticleDAO {
	private static final String URL = GC.URL;
	private static final String USERNAME = GC.USERNAME;
	private static final String PASSWORD = GC.PASSWORD;

	private static final String SELECT_ALL = "SELECT articleId,memberId,subclassNo,articleTitle,articleContent,publishTime,modifyTime,watchTimes FROM article ORDER BY modifytime";

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

	private static final String SEARCH_BY_CLASS = "SELECT * FROM article WHERE subclassNo = ? ORDER BY modifytime";

	@Override
	public List<ArticleVO> selectBySubclassNo(String subclassNo) {
		ArticleVO avo = new ArticleVO();
		List<ArticleVO> avos = null;
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement pstmt = conn.prepareStatement(SEARCH_BY_CLASS);) {
			pstmt.setString(1, subclassNo);
			ResultSet rs = pstmt.executeQuery();
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

	private static final String SEARCH_BY_TITLE = "SELECT * FROM article WHERE articleTitle like ? ORDER BY modifytime";

	@Override
	public List<ArticleVO> selectByArticleTitle(String articleTitle) {
		ArticleVO avo = new ArticleVO();
		List<ArticleVO> avos = null;
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement pstmt = conn.prepareStatement(SEARCH_BY_TITLE);) {
			pstmt.setString(1, "%" + articleTitle + "%");
			ResultSet rs = pstmt.executeQuery();
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

	private static final String SEARCH_BY_CLASS_AND_MEMBERACCOUNT = "SELECT articleId, a.memberId, subclassNo, articleTitle, articleContent, publishTime, modifyTime, watchTimes, memberaccount"
			+ "FROM article a join member m ON a.memberid = m.memberid WHERE memberaccount like ? AND subclassNo = ? ORDER BY modifytime";

	@Override
	public List<ArticleVO> selectByMemberAccountAndSubclassNo(String memberAccount, String subclassNo) {
		ArticleVO avo = new ArticleVO();
		List<ArticleVO> avos = null;
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement pstmt = conn.prepareStatement(SEARCH_BY_CLASS_AND_MEMBERACCOUNT);) {
			pstmt.setString(1, "%" + memberAccount + "%");
			pstmt.setString(2, subclassNo);
			ResultSet rs = pstmt.executeQuery();
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

	private static final String SEARCH_BY_MEMBERACCOUNT_ARTICLETITLE = "SELECT articleId, a.memberId, subclassNo, articleTitle, articleContent, publishTime, modifyTime, watchTimes, memberaccount"
			+ "FROM article a join member m ON a.memberid = m.memberid WHERE memberaccount like ? OR articleTitle = ? ORDER BY modifytime";

	@Override
	public List<ArticleVO> selectByMemberAccountAndArticleTitle(String memberAccount, String articleTitle) {
		ArticleVO avo = new ArticleVO();
		List<ArticleVO> avos = null;
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement pstmt = conn.prepareStatement(SEARCH_BY_MEMBERACCOUNT_ARTICLETITLE);) {
			pstmt.setString(1, "%" + memberAccount + "%");
			pstmt.setString(2, "%" + articleTitle + "%");
			ResultSet rs = pstmt.executeQuery();
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

	private static final String SEARCH_BY_CLASS_AND_MEMBERACCOUNT_OR_ARTICLETITLE = "SELECT articleId, a.memberId, subclassNo, articleTitle, articleContent, publishTime, modifyTime, watchTimes, memberaccount"
			+ "FROM article a join member m ON a.memberid = m.memberid WHERE subclassNo = ? AND memberaccount like ? OR articleTitle ? ORDER BY modifytime";

	@Override
	public List<ArticleVO> selectByMemberAccountOrArticleTitleAndSubclassNo(String subclassNo, String memberAccount,
			String articleTitle) {
		ArticleVO avo = new ArticleVO();
		List<ArticleVO> avos = null;
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement pstmt = conn.prepareStatement(SEARCH_BY_CLASS_AND_MEMBERACCOUNT_OR_ARTICLETITLE);) {
			pstmt.setString(1, subclassNo);
			pstmt.setString(2, "%" + memberAccount + "%");
			pstmt.setString(3, "%" + articleTitle + "%");
			ResultSet rs = pstmt.executeQuery();
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

	private static final String SEARCH_BY_MEMBERACCOUNT = "SELECT articleId, a.memberId, subclassNo, articleTitle, articleContent, publishTime, modifyTime, watchTimes, memberaccount"
			+ "FROM article a JOIN member m ON a.memberid = m.memberid WHERE memberaccount like '%'+?+'%' ORDER BY modifytime";

	@Override
	public List<ArticleVO> selectByMemberAccount(String memberAccount) {
		ArticleVO avo = new ArticleVO();
		List<ArticleVO> avos = null;
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement pstmt = conn.prepareStatement(SEARCH_BY_MEMBERACCOUNT);) {
			pstmt.setString(1, memberAccount);
			ResultSet rs = pstmt.executeQuery();
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

	private static final String SEARCH_BY_MEMBERID = "SELECT * FROM article WHERE memberId like '%' + ? + '%' ORDER BY modifytime";

	@Override
	public List<ArticleVO> selectByMemberId(int memberId) {
		ArticleVO avo = new ArticleVO();
		List<ArticleVO> avos = null;
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement pstmt = conn.prepareStatement(SEARCH_BY_MEMBERID);) {
			pstmt.setInt(1, memberId);
			ResultSet rs = pstmt.executeQuery();
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
	private static final String UPDATE = "UPDATE Article SET subclassNo=?,articleTitle=?,articleContent=?,modifyTime = GETUTCDATE() WHERE articleId=? AND memberId=?";

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
	private static final String DELETE = "UPDATE Article SET memberId = NULL, articleContent = N'文章已被刪除', modifyTime = GETUTCDATE() WHERE articleId = ?";

	@Override
	public boolean delete(int articleId) {
		boolean result = false;
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement pstmt = conn.prepareStatement(DELETE);) {
			pstmt.setInt(1, articleId);
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

		// avo.setMemberId(1);
		// avo.setArticleId(14);
		// avo.setSubclassNo("A");
		// avo.setArticleTitle("Dear");
		// avo.setArticleContent("I hate the world");
		// System.out.println(temp.insert(avo));
		// System.out.println(temp.update(avo));
		// getUTCdate

	}
}
