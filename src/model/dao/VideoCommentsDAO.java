package model.dao;

import java.util.List;

import model.vo.VideoCommentsVO;

public interface VideoCommentsDAO {

	List<VideoCommentsVO> selectAll();

	boolean insert(VideoCommentsVO videoComments);

	boolean update(VideoCommentsVO videoComments);

	boolean delete(int commentId);

}