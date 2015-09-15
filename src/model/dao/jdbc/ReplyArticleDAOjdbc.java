package model.dao.jdbc;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.dao.ReplyArticleDAO;
import model.vo.MemberVO;
import model.vo.ReplyArticleVO;
import util.ConvertType;
import util.GC;
	
public class ReplyArticleDAOjdbc implements ReplyArticleDAO {
	private static final String URL = GC.URL;
	private static final String USERNAME = GC.USERNAME;
	private static final String PASSWORD = GC.PASSWORD;

	private static final String SELECT_ALL = "SELECT * FROM ReplyArticle";

	@Override
	public List<ReplyArticleVO> selectAll() {
		List<ReplyArticleVO> list = null;
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(SELECT_ALL);
				ResultSet rs = stmt.executeQuery();) {
			list = new ArrayList<ReplyArticleVO>();
			while (rs.next()) {
				ReplyArticleVO replyArticle = new ReplyArticleVO();
				replyArticle.setReplyArticleId(rs.getInt("replyArticleId"));
				replyArticle.setMemberId(rs.getInt("memberId"));
				replyArticle.setArticleId(rs.getInt("articleId"));
				replyArticle.setReplyContent(rs.getString("replyContent"));
				replyArticle.setPublishTime(ConvertType.convertToLocalTime(rs.getTimestamp("publishTime")));
				replyArticle.setModifyTime(ConvertType.convertToLocalTime(rs.getTimestamp("modifyTime")));
				MemberVO bean = new MemberVO();
				bean.setMemberAccount(rs.getString("memberAccount"));
				Blob b = rs.getBlob("memberPhoto");
				bean.setMemberPhoto(b.getBytes(1, (int)b.length()));
				replyArticle.setMember(bean);
				list.add(replyArticle);
			}
		} catch (SQLException e) {
			System.out.println(e.getErrorCode() + " : " + e.getMessage());
			e.printStackTrace();
		}
		return list;
	}

	private static final String SELECT_BY_ARTICLEID = "SELECT replyArticleId, r.memberId, articleId, replyContent, publishTime, modifyTime, memberAccount, memberPhoto FROM ReplyArticle r JOIN Member m ON r.memberId = m.memberId WHERE articleId = ?";

	@Override
	public List<ReplyArticleVO> selectByArticleId(int articleId) {
		List<ReplyArticleVO> list = null;
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(SELECT_BY_ARTICLEID);) {
			stmt.setInt(1, articleId);
			ResultSet rs = stmt.executeQuery();
			list = new ArrayList<ReplyArticleVO>();
			while (rs.next()) {
				ReplyArticleVO replyArticle = new ReplyArticleVO();
				replyArticle.setReplyArticleId(rs.getInt("replyArticleId"));
				replyArticle.setMemberId(rs.getInt("memberId"));
				replyArticle.setArticleId(rs.getInt("articleId"));
				replyArticle.setReplyContent(rs.getString("replyContent"));
				replyArticle.setPublishTime(ConvertType.convertToLocalTime(rs.getTimestamp("publishTime")));
				replyArticle.setModifyTime(ConvertType.convertToLocalTime(rs.getTimestamp("modifyTime")));
				MemberVO bean = new MemberVO();
				bean.setMemberAccount(rs.getString("memberAccount"));
				Blob b = rs.getBlob("memberPhoto");
				bean.setMemberPhoto(b.getBytes(1, (int)b.length()));
				replyArticle.setMember(bean);
				list.add(replyArticle);
			}
		} catch (SQLException e) {
			System.out.println(e.getErrorCode() + " : " + e.getMessage());
			e.printStackTrace();
		}

		return list;
	}

	private static final String INSERT = "INSERT INTO ReplyArticle(memberId, articleId, replyContent) VALUES (?, ?, ?)";

	@Override
	public int insert(int memberId, int articleId, String replyContent) {
		int result = -1;
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(INSERT);) {
			stmt.setInt(1, memberId);
			stmt.setInt(2, articleId);
			stmt.setString(3, replyContent);
			result = stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getErrorCode() + " : " + e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	private static final String UPDATE = "UPDATE ReplyArticle SET replyContent = ?, modifyTime = GETUTCDATE() WHERE replyArticleId = ?";

	@Override
	public int update(String replyContent, int replyArticleId) {
		int result = -1;
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(UPDATE);) {
			stmt.setString(1, replyContent);
			stmt.setInt(2, replyArticleId);
			result = stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getErrorCode() + " : " + e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	private static final String DELETE = "UPDATE ReplyArticle SET replyContent = N'文章已被刪除', modifyTime = GETUTCDATE() WHERE replyArticleId = ?";

	@Override
	public int delete(int replyArticleId) {
		int result = -1;
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(DELETE);) {
			stmt.setInt(1, replyArticleId);
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

