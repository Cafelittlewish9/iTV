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
import model.vo.MemberVO;
import util.ConvertType;
import util.GC;

/**
 * @author iTV小組成員
 *
 */
public class ArticleDAOjdbc implements ArticleDAO {
	private static final String URL = GC.URL;
	private static final String USERNAME = GC.USERNAME;
	private static final String PASSWORD = GC.PASSWORD;

	private static final String SELECT_ALL = "SELECT articleId,memberId,subclassNo,articleTitle,articleContent,publishTime,modifyTime,watchTimes FROM article ORDER BY modifytime";
	/**
	 * 查詢所有文章
	 * 
	 * @return Collection<ArticleVO>
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
				avo.setPublishTime(ConvertType.convertToLocalTime(rs.getTimestamp("publishTime")));				
				avo.setModifyTime(ConvertType.convertToLocalTime(rs.getTimestamp("modifyTime")));
				avo.setWatchTimes(rs.getLong("watchTimes"));
				avos.add(avo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return avos;
	}

	private static final String SELECT_BY_INPUT="SELECT a.articleId,a.memberId,a.subclassNo,a.articleTitle,a.articleContent,a.publishTime,a.modifyTime,a.watchTimes,m.memberAccount,m.memberNickname"
	+" FROM article a join member m on a.memberId=m.memberId WHERE a.subclassNo =? OR a.articleTitle like ? OR m.memberAccount like ? OR m.memberNickName like ?";
	/**
	 * 依照各種條件來查詢文章
	 * 	 
	 * @param memberId 會員編號
	 * @return List<ArticleVO>
	 */
	@Override
	public List<ArticleVO> selectByInput(String subclassNo, String articleTitle, String memberAccount,
			String memberNickName) {
		ArticleVO avo = new ArticleVO();
		List<ArticleVO> avos = null;
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement pstmt = conn.prepareStatement(SELECT_BY_INPUT);) {			
			pstmt.setString(1, subclassNo);
			pstmt.setString(2, "%"+articleTitle+"%");
			pstmt.setString(3, "%"+memberAccount+"%");
			pstmt.setString(4, "%"+memberNickName+"%");
			ResultSet rs = pstmt.executeQuery();
			avos = new ArrayList<ArticleVO>();
			while (rs.next()) {
				MemberVO bean=new MemberVO();
				avo = new ArticleVO();
				avo.setArticleId(rs.getInt("articleId"));
				avo.setMemberId(rs.getInt("memberId"));
				avo.setSubclassNo(rs.getString("subclassNo"));
				avo.setArticleTitle(rs.getString("articleTitle"));
				avo.setArticleContent(rs.getString("articleContent"));
				avo.setPublishTime(ConvertType.convertToLocalTime(rs.getTimestamp("publishTime")));
				avo.setModifyTime(ConvertType.convertToLocalTime(rs.getTimestamp("modifyTime")));
				avo.setWatchTimes(rs.getLong("watchTimes"));
				bean.setMemberAccount(rs.getString("memberAccount"));
				avo.setMember(bean);
				avos.add(avo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return avos;		
	}

	private static final String INSERT = "INSERT INTO Article (memberId, subclassNo,articleTitle,articleContent) VALUES (?,?,?,?)";
	/**
	 * 新增文章
	 * 	 
	 * @param bean 必須包含<b>memberId</b>、<b>subclassNo</b>、<b>articleTitle</b> 與 <b>articleContent</b>
	 * @return true 新增成功；false 新增失敗
	 */
	@Override
	public boolean insert(ArticleVO bean) {
		boolean result = false;
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement pstmt = conn.prepareStatement(INSERT);) {
			pstmt.setInt(1, bean.getMemberId());
			pstmt.setString(2, bean.getSubclassNo());
			pstmt.setString(3, bean.getArticleTitle());
			pstmt.setString(4, bean.getArticleContent());
			int updateCount = pstmt.executeUpdate();
			if (updateCount == 1) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	
	private static final String UPDATE = "UPDATE Article SET subclassNo=?,articleTitle=?,articleContent=?,modifyTime = GETUTCDATE() WHERE articleId=? AND memberId=?";
	/**
	 * 修改文章
	 * 	 
	 * @param bean 必須包含<b>articleId</b> 與 <b>memberId</b>
	 * @return true 新增成功；false 新增失敗
	 */
	@Override
	public boolean update(ArticleVO bean) {
		boolean result = false;
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement pstmt = conn.prepareStatement(UPDATE);) {
			pstmt.setString(1, bean.getSubclassNo());
			pstmt.setString(2, bean.getArticleTitle());
			pstmt.setString(3, bean.getArticleContent());
			long modify = bean.getModifyTime().getTime();
			pstmt.setTimestamp(4, new java.sql.Timestamp(modify));
			pstmt.setInt(5, bean.getArticleId());
			pstmt.setInt(6, bean.getMemberId());

			int updateCount = pstmt.executeUpdate();
			if (updateCount == 1) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}


	private static final String DELETE = "UPDATE Article SET memberId = NULL, articleContent = N'文章已被刪除', modifyTime = GETUTCDATE() WHERE articleId = ?";
	/**
	 * 刪除文章，僅有發文者本人能於登入狀態看到刪除按鈕，當文章確定刪除後，刪除按鈕即消失
	 * 	 
	 * @param articleId 文章編號
	 * @return true 刪除成功；false 刪除失敗
	 */
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
		
//		System.out.println(temp.selectByInput("","","",""));
		
		
		 System.out.println(temp.selectAll());
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
