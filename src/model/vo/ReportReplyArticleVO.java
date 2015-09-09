package model.vo;

import java.text.SimpleDateFormat;

public class ReportReplyArticleVO {
	private int orderId;
	private int reportedReplyArticleId;
	private java.util.Date reportTime;
	private String reportReason;
	
	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = sdf.format(reportTime);
		return orderId + "被檢舉的回覆文章ID為: " + reportedReplyArticleId + " (" + date + ")";
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getReportedReplyArticleId() {
		return reportedReplyArticleId;
	}
	public void setReportedReplyArticleId(int reportedReplyArticleId) {
		this.reportedReplyArticleId = reportedReplyArticleId;
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
}
