package model.vo;

import java.text.SimpleDateFormat;

public class VideoVO {
	private int videoId;
	private int memberId;
	private String videoWebsite;
	private String videoClassName;
	private String videoTitle;
	private String videoName;
	private String videoPath;
	private java.util.Date videoUploadTime;
	private long videoWatchTimes;
	private String videoDescription;
	private java.util.Date videoDescriptionModifyTime;
	
	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = sdf.format(videoUploadTime);
		return videoId + ": " + videoName + " (" + date + ")";
	}
	public int getVideoId() {
		return videoId;
	}
	public void setVideoId(int videoId) {
		this.videoId = videoId;
	}
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public String getVideoWebsite() {
		return videoWebsite;
	}
	public void setVideoWebsite(String videoWebsite) {
		this.videoWebsite = videoWebsite;
	}
	public String getVideoClassName() {
		return videoClassName;
	}
	public void setVideoClassName(String videoClassName) {
		this.videoClassName = videoClassName;
	}
	public String getVideoTitle() {
		return videoTitle;
	}
	public void setVideoTitle(String videoTitle) {
		this.videoTitle = videoTitle;
	}
	public String getVideoName() {
		return videoName;
	}
	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}
	public String getVideoPath() {
		return videoPath;
	}
	public void setVideoPath(String videoPath) {
		this.videoPath = videoPath;
	}
	public java.util.Date getVideoUploadTime() {
		return videoUploadTime;
	}
	public void setVideoUploadTime(java.util.Date videoUploadTime) {
		this.videoUploadTime = videoUploadTime;
	}
	public long getVideoWatchTimes() {
		return videoWatchTimes;
	}
	public void setVideoWatchTimes(long videoWatchTimes) {
		this.videoWatchTimes = videoWatchTimes;
	}
	public String getVideoDescription() {
		return videoDescription;
	}
	public void setVideoDescription(String videoDescription) {
		this.videoDescription = videoDescription;
	}
	public java.util.Date getVideoDescriptionModifyTime() {
		return videoDescriptionModifyTime;
	}
	public void setVideoDescriptionModifyTime(java.util.Date videoDescriptionModifyTime) {
		this.videoDescriptionModifyTime = videoDescriptionModifyTime;
	}
}
