package model.service;

import java.util.Date;
import java.util.List;

import model.dao.VideoCommentsDAO;
import model.dao.jdbc.VideoCommentsDAOjdbc;
import model.vo.VideoCommentsVO;

public class VideoCommentsService {
	private VideoCommentsDAO dao;
	
	public VideoCommentsService(){
		this.dao = new VideoCommentsDAOjdbc();
	}
	
	public List<VideoCommentsVO> selectAllComments(){
		return dao.selectAll();
	}
	
	public boolean insertVideoComments(int memberId , int videoId , String commentContent){
		VideoCommentsVO bean = new VideoCommentsVO();
		bean.setMemberId(memberId);
		bean.setVideoId(videoId);
		bean.setCommentContent(commentContent);
		boolean result = dao.insert(bean);
		if(result == true){
			return true;
		}
		return false;
	}
	
	public boolean updateVideoComments(String commentsContent , java.sql.Timestamp commentTime , int commentId){
		VideoCommentsVO bean = new VideoCommentsVO();
		bean.setCommentContent(commentsContent);
		bean.setCommentTime(commentTime);
		bean.setCommentId(commentId);
		boolean result = dao.update(bean);
		if(result == true){
			return true;
		}
		return false;
	}
	
	public boolean deleteVideoComments(int commentId){
		return dao.delete(commentId);
	}
	
	
	public static void main(String[] args){
		VideoCommentsService service = new VideoCommentsService();
//		boolean bool = service.insertVideoComments(1, 4, "安安");
//		boolean bool = service.updateVideoComments("修改安安", java.sql.Timestamp.valueOf("1911-01-01 10:10:10.000"), 17);
		boolean bool = service.deleteVideoComments(17);
		
		System.out.println(bool);
		
	}
	
	
	
}
