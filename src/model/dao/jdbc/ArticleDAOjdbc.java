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

public class ArticleDAOjdbc implements ArticleDAO {
	private final String URL = "jdbc:sqlserver://y56pcc16br.database.windows.net:1433;database=iTV";
	private final String USERNAME = "iTVSoCool";// iTVSoCool@y56pcc16br
	private final String PASSWORD = "iTVisgood911";

	private static final String SELECT_ALL = "SELECT articleId,memberId,subclassNo,articleTitle,articleContent,publishTime,modifyTime,watchTimes FROM article";
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
	
	private static final String SEARCH_BY_CLASS1="SELECT articleId, a.memberId, subclassNo, articleTitle, articleContent, publishTime, modifyTime, watchTimes, memberaccount"
	+"FROM article a join member m ON a.memberid = m.memberid WHERE memberaccount like '%'+?+'%' AND subclassNo = ? ORDER BY modifytime";
	public List<ArticleVO> selectAll(String memberacc,String subclassNo) {
		ArticleVO avo = new ArticleVO();
		List<ArticleVO> avos = null;
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement pstmt = conn.prepareStatement(SEARCH_BY_CLASS1);) {
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

	private static final String SEARCH_BY_CLASS = "SELECT * FROM article WHERE subclassNo =?";
	@Override
	public List<ArticleVO> selectAll(String subclassNo) {
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

	private static final String SEARCH_BY_TITLE = "SELECT * FROM article WHERE articleTitle like ?";
	@Override
	public List<ArticleVO> select(String articleTitle) {
		ArticleVO avo = new ArticleVO();
		List<ArticleVO> avos = null;
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement pstmt = conn.prepareStatement(SEARCH_BY_TITLE);) {
			pstmt.setString(1, "%"+articleTitle+"%");
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

	private static final String SEARCH_BY_MEMBERID2="SELECT articleId, a.memberId, subclassNo, articleTitle, articleContent, publishTime, modifyTime, watchTimes, memberaccount"
	+"FROM article a JOIN member m ON a.memberid = m.memberid WHERE memberaccount like '%'+?+'%' ORDER BY modifytime";	
	public List<ArticleVO> selectByMemacc(String memberacc) {
		ArticleVO avo = new ArticleVO();
		List<ArticleVO> avos = null;
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement pstmt = conn.prepareStatement(SEARCH_BY_MEMBERID2);) {
			pstmt.setString(1, memberacc);
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
	
	//getUTCdate	
	private static final String SEARCH_BY_MEMBERID = "SELECT * FROM article WHERE memberId like '%' + ? + '%'";
	@Override
	public List<ArticleVO> select(int memberId) {
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
	private static final String UPDATE = "UPDATE Article SET subclassNo=?,articleTitle=?,articleContent=?,modifyTime=?WHERE articleId=? AND memberId=?";
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
		System.out.println(temp.select("一天"));
		
		
//		avo.setMemberId(1);
//		avo.setArticleId(14);
//		avo.setSubclassNo("A");
//		avo.setArticleTitle("Dear");
//		avo.setArticleContent("I hate the world");
		// System.out.println(temp.insert(avo));
//		System.out.println(temp.update(avo));

	}
}
