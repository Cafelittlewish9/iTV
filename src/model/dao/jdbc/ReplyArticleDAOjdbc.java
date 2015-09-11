package model.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.dao.ReplyArticleDAO;
import model.vo.ReplyArticleVO;
	
public class ReplyArticleDAOjdbc implements ReplyArticleDAO {
	private static final String URL = "jdbc:sqlserver://y56pcc16br.database.windows.net:1433;database=iTV";
	private static final String USERNAME = "iTVSoCool@y56pcc16br";
	private static final String PASSWORD = "iTVisgood911";

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
				replyArticle.setPublishTime(rs.getTimestamp("publishTime"));
				replyArticle.setModifyTime(rs.getTimestamp("modifyTime"));
				list.add(replyArticle);
			}
		} catch (SQLException e) {
			System.out.println(e.getErrorCode() + " : " + e.getMessage());
			e.printStackTrace();
		}
		return list;
	}

	private static final String SELECT_BY_ARTICLEID = "SELECT * FROM ReplyArticle WHERE articleId = ?";

	@Override
	public List<ReplyArticleVO> select(int articleId) {
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
				replyArticle.setPublishTime(rs.getTimestamp("publishTime"));
				replyArticle.setModifyTime(rs.getTimestamp("modifyTime"));
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
	public int insert(ReplyArticleVO replyArticle) {
		int result = -1;
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(INSERT);) {
			stmt.setInt(1, replyArticle.getMemberId());
			stmt.setInt(2, replyArticle.getArticleId());
			stmt.setString(3, replyArticle.getReplyContent());
			result = stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getErrorCode() + " : " + e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	private static final String UPDATE = "UPDATE ReplyArticle SET replyContent = ?, modifyTime = GETUTCDATE() WHERE replyArticleId = ?";

	@Override
	public int update(ReplyArticleVO replyArticle) {
		int result = -1;
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(UPDATE);) {
			stmt.setString(1, replyArticle.getReplyContent());
			stmt.setInt(2, replyArticle.getReplyArticleId());
			result = stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getErrorCode() + " : " + e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	private static final String DELETE = "UPDATE ReplyArticle SET replyContent = N'文章已被刪除', modifyTime = GETUTCDATE() WHERE replyArticleId = ?";

	@Override
	public int delete(ReplyArticleVO replyArticle) {
		int result = -1;
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(DELETE);) {
			stmt.setInt(1, replyArticle.getReplyArticleId());
			result = stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getErrorCode() + " : " + e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	public static void main(String[] args) {
		ReplyArticleDAO temp = new ReplyArticleDAOjdbc();

		List<ReplyArticleVO> all = temp.selectAll();
		System.out.println("SELECT_ALL result = " + all);

		System.out.println("select = " + temp.select(3));
		
		// ReplyArticleVO replyArticle1 = new ReplyArticleVO();
		// replyArticle1.setMemberId(2);
		// replyArticle1.setArticleId(6);
		// replyArticle1.setReplyContent("XDDDDDD");
		// replyArticle1.setPublishTime(new java.util.Date(22));
		// replyArticle1.setModifyTime(new java.util.Date(59));
		// int i = temp.insert(replyArticle1);
		// System.out.println("INSERT result = " + i);
		//
		// ReplyArticleVO replyArticle2 = new ReplyArticleVO();
		// replyArticle2.setReplyContent("ODDDDD");
		// replyArticle2.setModifyTime(new java.util.Date());
		// replyArticle2.setReplyArticleId(49);
		// int j = temp.update(replyArticle2);
		// System.out.println("UPDATE result = " + j);
		//
		// ReplyArticleVO replyArticle3 = new ReplyArticleVO();
		// replyArticle3.setReplyArticleId(49);
		// replyArticle3.setModifyTime(new java.util.Date());
		// int k = temp.delete(replyArticle3);
		// System.out.println("DELETE result = " + k);
	}
}

