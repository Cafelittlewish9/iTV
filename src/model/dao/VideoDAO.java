package model.dao;

import java.util.List;

import model.vo.VideoVO;

public interface VideoDAO {

	public List<VideoVO> select(String videoTitle);

	public List<VideoVO> selectAll();

	public boolean insert(VideoVO bean);

	public boolean update(String videoDescription, java.util.Date videoDescriptionModifyTime, int videoId);

	public void update(long videoWatchTimes, int videoId);

	public boolean delete(int videoId);

}