package model.vo;

import java.text.SimpleDateFormat;

public class ReportMemberVO {
	private int orderId;
	private int reportedMemberId;
	private java.util.Date reportTime;
	private String reportReason;
	private MemberVO member;
	
	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = sdf.format(reportTime);
		return orderId + "被檢舉的會員ID為: " + reportedMemberId + " (" + date + ")";
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getReportedMemberId() {
		return reportedMemberId;
	}
	public void setReportedMemberId(int reportedMemberId) {
		this.reportedMemberId = reportedMemberId;
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
	public MemberVO getMember() {
		return member;
	}
	public void setMember(MemberVO member) {
		this.member = member;
	}
}
