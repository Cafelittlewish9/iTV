package model.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.dao.VideoDAO;
import model.vo.VideoVO;

public class VideoDAOjdbc implements VideoDAO {
	private final String URL = "jdbc:sqlserver://y56pcc16br.database.windows.net:1433;database=iTV";
	private final String USERNAME = "iTVSoCool@y56pcc16br";
	private final String PASSWORD = "iTVisgood911";

	private static final String SELECT_BY_ID = "select * from video where videoTitle like ?";

	@Override
	public List<VideoVO> select(String videoTitle) {
		VideoVO result = null;
		List<VideoVO> list = null;
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(SELECT_BY_ID);) {
			stmt.setString(1, "%" + videoTitle + "%");
			ResultSet rset = stmt.executeQuery();
			list = new ArrayList<VideoVO>();
			while (rset.next()) {
				result = new VideoVO();
				result.setVideoId(rset.getInt("videoId"));
				result.setMemberId(rset.getInt("memberId"));
				result.setVideoWebsite(rset.getString("videoWebsite"));
				result.setVideoClassName(rset.getString("videoClassName"));
				result.setVideoTitle(rset.getString("videoTitle"));
				result.setVideoName(rset.getString("videoName"));
				result.setVideoPath(rset.getString("videoPath"));
				result.setVideoUploadTime(rset.getTimestamp("videoUploadTime"));
				result.setVideoWatchTimes(rset.getLong("videoWatchTimes"));
				result.setVideoDescription(rset.getString("videoDescription"));
				result.setVideoDescriptionModifyTime(rset.getTimestamp("videoDescriptionModifyTime"));
				list.add(result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	private static final String SELECT_ALL = "select * from video";

	@Override
	public List<VideoVO> selectAll() {
		List<VideoVO> list = null;
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(SELECT_ALL);
				ResultSet rset = stmt.executeQuery();) {
			list = new ArrayList<VideoVO>();
			while (rset.next()) {
				VideoVO bean = new VideoVO();
				bean.setVideoId(rset.getInt("videoId"));
				bean.setMemberId(rset.getInt("memberId"));
				bean.setVideoWebsite(rset.getString("videoWebsite"));
				bean.setVideoClassName(rset.getString("videoClassName"));
				bean.setVideoTitle(rset.getString("videoTitle"));
				bean.setVideoName(rset.getString("videoName"));
				bean.setVideoPath(rset.getString("videoPath"));
				bean.setVideoUploadTime(rset.getTimestamp("videoUploadTime"));
				bean.setVideoWatchTimes(rset.getLong("videoWatchTimes"));
				bean.setVideoDescription(rset.getString("videoDescription"));
				bean.setVideoDescriptionModifyTime(rset.getTimestamp("videoDescriptionModifyTime"));
				list.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	private static final String INSERT = "insert into video(memberId, videoWebsite, videoClassName, videoTitle, videoName, videoPath, videoWatchTimes, videoDescription) values (?, ?, ?, ?, ?, ?, ?, ?)";

	@Override
	public boolean insert(VideoVO bean) {
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(INSERT);) {
			if (bean != null) {
				stmt.setInt(1, bean.getMemberId());
				stmt.setString(2, bean.getVideoWebsite());
				stmt.setString(3, bean.getVideoClassName());
				stmt.setString(4, bean.getVideoTitle());
				stmt.setString(5, bean.getVideoName());
				stmt.setString(6, bean.getVideoPath());
				stmt.setLong(7, bean.getVideoWatchTimes());
				stmt.setString(8, bean.getVideoDescription());
				int i = stmt.executeUpdate();
				if (i == 1) {
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	private static final String UPDATE_DESCRIPTION = "update video set videoDescription = ?, videoDescriptionModifyTime = GETUTCDATE() where videoId = ?";

	@Override
	public boolean update(String videoDescription, java.util.Date videoDescriptionModifyTime, int videoId) {
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(UPDATE_DESCRIPTION);) {
			stmt.setString(1, videoDescription);
			stmt.setInt(2, videoId);
			int i = stmt.executeUpdate();
			if (i == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	private static final String UPDATE_WATCHTIMES = "update video set videoWatchTimes = ? where videoId = ?";

	@Override
	public void update(long videoWatchTimes, int videoId) {
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(UPDATE_WATCHTIMES);) {
			stmt.setLong(1, videoWatchTimes);
			stmt.setInt(2, videoId);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static final String DELETE = "delete from video where videoId = ?";

	@Override
	public boolean delete(int videoId) {
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(DELETE);) {
			stmt.setInt(1, videoId);
			int i = stmt.executeUpdate();
			if (i == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static void main(String[] args) {
		// SelectALL
		VideoDAO temp = new VideoDAOjdbc();
		List<VideoVO> list = temp.selectAll();
		System.out.println(list);

		// Insert
		// String url =
		// "http://nextinnovation.cloudapp.net/ITV/PlayVideo.jsp?filename=";
		// String path = "../mp4/";
		// String videoname = "Mamamoo - Um Oh Ah Yeh";
		// VideoVO tempinsert = new VideoVO();
		// tempinsert.setMemberId(2);
		// tempinsert.setVideoWebsite(url+videoname);
		// tempinsert.setVideoClassName("mv");
		// tempinsert.setVideoName(videoname);
		// tempinsert.setVideoPath(path+videoname+".mp4");
		// tempinsert.setVideoWatchTimes(1000);
		//
		// VideoDAO dao = new VideoDAOjdbc();
		// VideoVO insertlist = dao.insert(tempinsert);
		// System.out.println("Insert : "+ insertlist);

		// Update
		// String url =
		// "http://nextinnovation.cloudapp.net/ITV/PlayVideo.jsp?filename=";
		// String path = "../mp4/";
		// String videoname = "Mamamoo";
		//
		// VideoVO tempupdate = new VideoVO();
		// tempupdate.setMemberId(3);
		// tempupdate.setVideoWebsite(url+videoname);
		// tempupdate.setVideoClassName("mv");
		// tempupdate.setVideoName(videoname);
		// tempupdate.setVideoPath(path+videoname+".mp4");
		// tempupdate.setVideoUploadTime(new
		// java.sql.Date(System.currentTimeMillis()));
		// tempupdate.setVideoWatchTimes(2000);
		// tempupdate.setVideoDescription("");
		// tempupdate.setVideoId(14);
		//
		// VideoDAO dao = new VideoDAOjdbc();
		// VideoVO updatelist =
		// dao.update(tempupdate.getMemberId(),tempupdate.getVideoWebsite(),tempupdate.getVideoClassName(),
		// tempupdate.getVideoName(),tempupdate.getVideoPath(),tempupdate.getVideoUploadTime(),tempupdate.getVideoWatchTimes(),tempupdate.getVideoDescription(),tempupdate.getVideoId());
		// System.out.println("Update : "+ updatelist);

		// Delete
		// VideoDAO dao = new VideoDAOjdbc();
		// boolean d = dao.delete(13);
		// if(d==true){
		// System.out.println("Delete : Success!!!");
		// }else{
		// System.out.println("Delete : Fail!!!");
		// }
	}
}
