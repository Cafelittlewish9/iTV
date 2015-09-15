package model.vo;

import java.text.SimpleDateFormat;

public class ReportVideoVO {
	private int orderId;
	private int reportedVideoId;
	private java.util.Date reportTime;
	private String reportReason;
	private VideoVO video;
	
	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = sdf.format(reportTime);
		return orderId + "被檢舉的影片ID為: " + reportedVideoId + " (" + date + ")";
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getReportedVideoId() {
		return reportedVideoId;
	}
	public void setReportedVideoId(int reportedVideoId) {
		this.reportedVideoId = reportedVideoId;
	}
	public java.util.Date getReportTime() {
		return reportTime;
	}
	public void setReportTime(java.util.Date reportTime) {
		this.reportTime = reportTime;
	}
	public String getReportReason() {
		return reportReason;
	}
	public void setReportReason(String reportReason) {
		this.reportReason = reportReason;
	}
	public VideoVO getVideo() {
		return video;
	}
	public void setVideo(VideoVO video) {
		this.video = video;
	}
}
