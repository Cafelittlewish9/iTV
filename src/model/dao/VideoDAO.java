package model.dao;

import java.util.List;

import model.vo.VideoVO;

public interface VideoDAO {
	
	public abstract VideoVO select(String videoName);

	public abstract List<VideoVO> selectAll();

	public abstract VideoVO insert(VideoVO bean);

	public abstract VideoVO update(int memberId, String videoWebsite,String videoClassName,String videoName,String videoPath,
			java.util.Date videoUploadTime, long videoWatchTimes,String videoDescription, int videoId);

	public abstract boolean delete(int videoId);
}
