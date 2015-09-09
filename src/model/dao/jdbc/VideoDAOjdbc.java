package model.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import model.dao.VideoDAO;
import model.vo.VideoVO;

public class VideoDAOjdbc implements VideoDAO {
//	private final String URL = "jdbc:sqlserver://localhost:1433;database=iTV";
//	private final String USERNAME = "sa";
//	private final String PASSWORD = "passw0rd";
	
	private DataSource source = null;
	
	public VideoDAOjdbc(){
		try {
			Context context = new InitialContext();
			source = (DataSource) context.lookup("java:comp/env/jdbc/DB");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static final String SELECT_BY_ID = "select * from video where videoName like ?";
	
	@Override
	public VideoVO select(String videoName) {
		VideoVO result = null;
		ResultSet rset = null;
		
		try(
//				Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				Connection conn = source.getConnection();
				PreparedStatement stmt = conn.prepareStatement(SELECT_BY_ID);) {
			
			videoName = "%"+videoName +"%";
			stmt.setString(1, videoName);
			
			rset = stmt.executeQuery();
			if(rset.next()) {
				result = new VideoVO();
				result.setVideoId(rset.getInt("videoId"));
				result.setMemberId(rset.getInt("memberId"));
				result.setVideoWebsite(rset.getString("videoWebsite"));
				result.setVideoClassName(rset.getString("videoClassName"));
				result.setVideoName(rset.getString("videoName"));
				result.setVideoPath(rset.getString("videoPath"));
				result.setVideoUploadTime(rset.getTimestamp("videoUploadTime"));
				result.setVideoWatchTimes(rset.getLong("videoWatchTimes"));
				result.setVideoDescription(rset.getString("videoDescription"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	private static final String SELECT_ALL = "select * from video";
	
	@Override
	public List<VideoVO> selectAll() {
		List<VideoVO> list = null;
		
		try(
//				Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				Connection conn = source.getConnection();
				PreparedStatement stmt = conn.prepareStatement(SELECT_ALL);
				ResultSet rset = stmt.executeQuery();) {
			
			list = new ArrayList<VideoVO>();
			while(rset.next()) {
				VideoVO bean = new VideoVO();
				bean.setVideoId(rset.getInt("videoId"));
				bean.setMemberId(rset.getInt("memberId"));
				bean.setVideoWebsite(rset.getString("videoWebsite"));
				bean.setVideoClassName(rset.getString("videoClassName"));
				bean.setVideoName(rset.getString("videoName"));
				bean.setVideoPath(rset.getString("videoPath"));
				bean.setVideoUploadTime(rset.getTimestamp("videoUploadTime"));
				bean.setVideoWatchTimes(rset.getLong("videoWatchTimes"));
				bean.setVideoDescription(rset.getString("videoDescription"));
				
				list.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	private static final String INSERT =
			"insert into video(memberId,videoWebsite,videoClassName,videoName,videoPath,videoWatchTimes,videoDescription) values (?,?,?,?,?,?,?)";
	@Override
	public VideoVO insert(VideoVO bean) {
		VideoVO result = null;
		try(
//				Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				Connection conn = source.getConnection();
				PreparedStatement stmt = conn.prepareStatement(INSERT);) {
			if(bean!=null) {
				stmt.setInt(1, bean.getMemberId());
				stmt.setString(2, bean.getVideoWebsite());
				stmt.setString(3, bean.getVideoClassName());
				stmt.setString(4, bean.getVideoName());
//				java.util.Date make = bean.getVideoName();
//				if(make!=null) {
//					long time = make.getTime();
//					stmt.setDate(4, new java.sql.Date(time));
//				} else {
//					stmt.setDate(4, null);				
//				}
				stmt.setString(5, bean.getVideoPath());
				stmt.setLong(6, bean.getVideoWatchTimes());
				stmt.setString(7, bean.getVideoDescription());
				int i = stmt.executeUpdate();
				if(i==1) {
					result = this.select(bean.getVideoName());
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	private static final String UPDATE = 
			"update video set memberId =?, videoWebsite=?, videoClassName=?, videoName=? ,"
			+ " videoPath=?, videoUploadTime=?, videoWatchTimes=?, videoDescription=? where videoId=?";

	@Override
	public VideoVO update(int memberId, String videoWebsite, String videoClassName, String videoName, String videoPath,
			Date videoUploadTime, long videoWatchTimes, String videoDescription, int videoId) {
		
		VideoVO result = null;
		try(
//				Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				Connection conn = source.getConnection();
				PreparedStatement stmt = conn.prepareStatement(UPDATE);) {
			stmt.setInt(1, memberId);
			stmt.setString(2, videoWebsite);
			stmt.setString(3, videoClassName);
			stmt.setString(4, videoName);
			stmt.setString(5, videoPath);
			if(videoUploadTime!=null) {
				long time = videoUploadTime.getTime();
				stmt.setTimestamp(6, new java.sql.Timestamp(time));
			} else {
				stmt.setTimestamp(6, null);				
			}
			stmt.setLong(7, videoWatchTimes);
			stmt.setString(8, videoDescription);
			stmt.setInt(9, videoId);

			int i = stmt.executeUpdate();
			if(i==1) {
				result = this.select(videoName);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	private static final String DELETE =
			"delete from video where videoId=?";
	
	@Override
	public boolean delete(int videoId) {
		try(
//				Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				Connection conn = source.getConnection();
				PreparedStatement stmt = conn.prepareStatement(DELETE);) {			
			stmt.setInt(1, videoId);
			int i = stmt.executeUpdate();
			if(i==1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static void main(String[] args) {
		//SelectALL
//		VideoDAO temp = new VideoDAOjdbc();
//		List<VideoVO> list = temp.selectAll();
//		System.out.println(list);
		
		
		//Insert
//		String url = "http://nextinnovation.cloudapp.net/ITV/PlayVideo.jsp?filename=";
//		String path = "../mp4/";
//		String videoname = "Mamamoo - Um Oh Ah Yeh";
//		VideoVO tempinsert = new VideoVO();
//		tempinsert.setMemberId(2);
//		tempinsert.setVideoWebsite(url+videoname);
//		tempinsert.setVideoClassName("mv");
//		tempinsert.setVideoName(videoname);
//		tempinsert.setVideoPath(path+videoname+".mp4");
//		tempinsert.setVideoWatchTimes(1000);
//		
//		VideoDAO dao = new VideoDAOjdbc();
//		VideoVO insertlist = dao.insert(tempinsert);
//		System.out.println("Insert : "+ insertlist);
		
		//Update
//		String url = "http://nextinnovation.cloudapp.net/ITV/PlayVideo.jsp?filename=";
//		String path = "../mp4/";
//		String videoname = "Mamamoo";
//		
//		VideoVO tempupdate = new VideoVO();
//		tempupdate.setMemberId(3);
//		tempupdate.setVideoWebsite(url+videoname);
//		tempupdate.setVideoClassName("mv");
//		tempupdate.setVideoName(videoname);
//		tempupdate.setVideoPath(path+videoname+".mp4");
//		tempupdate.setVideoUploadTime(new java.sql.Date(System.currentTimeMillis()));
//		tempupdate.setVideoWatchTimes(2000);
//		tempupdate.setVideoDescription("");
//		tempupdate.setVideoId(14);
//
//		VideoDAO dao = new VideoDAOjdbc();
//		VideoVO updatelist = dao.update(tempupdate.getMemberId(),tempupdate.getVideoWebsite(),tempupdate.getVideoClassName(),
//				tempupdate.getVideoName(),tempupdate.getVideoPath(),tempupdate.getVideoUploadTime(),tempupdate.getVideoWatchTimes(),tempupdate.getVideoDescription(),tempupdate.getVideoId());
//		System.out.println("Update : "+ updatelist);
		
		//Delete
//		VideoDAO dao = new VideoDAOjdbc();
//		boolean d = dao.delete(13);
//		if(d==true){
//			System.out.println("Delete : Success!!!");
//		}else{
//			System.out.println("Delete : Fail!!!");
//		}
	}

}
