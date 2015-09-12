package model.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.dao.VideoCommentsDAO;
import model.vo.VideoCommentsVO;

public class VideoCommentsDAOjdbc implements VideoCommentsDAO {
	private final String URL = "jdbc:sqlserver://y56pcc16br.database.windows.net:1433;database=iTV";
	private final String USERNAME = "iTVSoCool@y56pcc16br";
	private final String PASSWORD = "iTVisgood911";

	private static final String SELECT_ALL = 
			"SELECT commentId,memberId,videoId,commentContent,commentTime FROM videoComments";
	/* (non-Javadoc)
	 * @see model.dao.jdbc.VideoCommentsDAO#selectAll()
	 */
	@Override
	public List<VideoCommentsVO> selectAll() {
		VideoCommentsVO vcvo = new VideoCommentsVO();
		List<VideoCommentsVO> vcvos = new ArrayList<VideoCommentsVO>();
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement pstmt = conn.prepareStatement(SELECT_ALL);
				ResultSet rs = pstmt.executeQuery();) {
			while (rs.next()) {
				vcvo = new VideoCommentsVO();
				vcvo.setCommentId(rs.getInt("commentId"));
				vcvo.setMemberId(rs.getInt("memberId"));
				vcvo.setVideoId(rs.getInt("videoId"));
				vcvo.setCommentContent(rs.getString("commentContent"));
				vcvo.setCommentTime(rs.getTimestamp("commentTime"));
				vcvos.add(vcvo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vcvos;
	}

	private static final String INSERT = 
			"INSERT INTO videoComments (memberId,videoId,commentContent) VALUES (?,?,?)";
	/* (non-Javadoc)
	 * @see model.dao.jdbc.VideoCommentsDAO#insert(model.vo.VideoCommentsVO)
	 */
	@Override
	public boolean insert(VideoCommentsVO videoComments) {
		boolean result = false;
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement pstmt = conn.prepareStatement(INSERT);) {
			
			pstmt.setInt(1, videoComments.getMemberId());
			pstmt.setInt(2, videoComments.getVideoId());
			pstmt.setString(3, videoComments.getCommentContent());
			int updateCount = pstmt.executeUpdate();
			if (updateCount == 1) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	private static final String UPDATE =
			"UPDATE videoComments SET commentContent=?,commentTime=? WHERE commentId=?";	
	/* (non-Javadoc)
	 * @see model.dao.jdbc.VideoCommentsDAO#update(model.vo.VideoCommentsVO)
	 */
	@Override
	public boolean update(VideoCommentsVO videoComments) {
		boolean result = false;
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement pstmt = conn.prepareStatement(UPDATE);) {
			pstmt.setString(1, videoComments.getCommentContent());
			long comment = videoComments.getCommentTime().getTime();
			pstmt.setTimestamp(2, new java.sql.Timestamp(comment));
			pstmt.setInt(3, videoComments.getCommentId());
			int updateCount = pstmt.executeUpdate();
			if (updateCount == 1) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;		
	}

	private static final String DELETE = "DELETE FROM videoComments WHERE commentId=?";
	/* (non-Javadoc)
	 * @see model.dao.jdbc.VideoCommentsDAO#delete(int)
	 */
	@Override
	public boolean delete(int commentId) {
		boolean removeResult = false;
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement pstmt = conn.prepareStatement(DELETE);) {
			pstmt.setInt(1, commentId);
			int updateCount = pstmt.executeUpdate();
			if (updateCount >= 1) {
				removeResult = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return removeResult;		
	}
	
	//測試程式
	public static void main(String[] args){
		VideoCommentsDAO vcdao=new VideoCommentsDAOjdbc();
		VideoCommentsVO vcvo=new VideoCommentsVO();
		vcvo.setCommentId(18);
//		vcvo.setMemberId(4);
//		vcvo.setVideoId(5);
		vcvo.setCommentContent("我超越了");		
		vcvo.setCommentTime(new java.util.Date());
//		System.out.println(vcdao.insert(vcvo));
		System.out.println(vcdao.update(vcvo));
//		System.out.println(vcdao.selectAll());
//		System.out.println(vcdao.delete(17));
	
//		insert時，DB抓的是GMT時間，update時則是傳本地端時間進DB
		
	}
	
}
