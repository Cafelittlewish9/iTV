package model.dao;

import java.util.List;

import model.vo.VideoVO;

public interface VideoDAO {

	List<VideoVO> select(String videoTitle);

	List<VideoVO> selectAll();

	boolean insert(VideoVO bean);

	boolean update(String videoDescription, java.util.Date videoDescriptionModifyTime, int videoId);

	void update(long videoWatchTimes, int videoId);

	boolean delete(int videoId);

}