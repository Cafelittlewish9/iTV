package model.vo;

public class MemberVO {
	private int memberId;
	private String memberAccount;
	private byte[] memberPassword;
	private String memberEmail;
	private String memberFB;
	private String memberGoogle;
	private String memberTwitter;
	private String memberName;
	private String memberNickname;
	private java.util.Date memberBirthday;
	private byte[] memberPhoto;
	private java.util.Date memberRegisterTime;
	private String memberSelfIntroduction;
	private String broadcastWebsite;
	private String broadcastTitle;
	private String broadcastClassName;
	private java.util.Date broadcastTime;
	private String broadcastDescription;
	private long broadcastWatchTimes;
	
	@Override
	public String toString() {
		return memberId + ": " + memberAccount + " " + memberName + " (" + memberNickname + ")";
	}
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public String getMemberAccount() {
		return memberAccount;
	}
	public void setMemberAccount(String memberAccount) {
		this.memberAccount = memberAccount;
	}
	public byte[] getMemberPassword() {
		return memberPassword;
	}
	public void setMemberPassword(byte[] memberPassword) {
		this.memberPassword = memberPassword;
	}
	public String getMemberEmail() {
		return memberEmail;
	}
	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}
	public String getMemberFB() {
		return memberFB;
	}
	public void setMemberFB(String memberFB) {
		this.memberFB = memberFB;
	}
	public String getMemberGoogle() {
		return memberGoogle;
	}
	public void setMemberGoogle(String memberGoogle) {
		this.memberGoogle = memberGoogle;
	}
	public String getMemberTwitter() {
		return memberTwitter;
	}
	public void setMemberTwitter(String memberTwitter) {
		this.memberTwitter = memberTwitter;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberNickname() {
		return memberNickname;
	}
	public void setMemberNickname(String memberNickname) {
		this.memberNickname = memberNickname;
	}
	public java.util.Date getMemberBirthday() {
		return memberBirthday;
	}
	public void setMemberBirthday(java.util.Date memberBirthday) {
		this.memberBirthday = memberBirthday;
	}
	public byte[] getMemberPhoto() {
		return memberPhoto;
	}
	public void setMemberPhoto(byte[] memberPhoto) {
		this.memberPhoto = memberPhoto;
	}
	public java.util.Date getMemberRegisterTime() {
		return memberRegisterTime;
	}
	public void setMemberRegisterTime(java.util.Date memberRegisterTime) {
		this.memberRegisterTime = memberRegisterTime;
	}
	public String getMemberSelfIntroduction() {
		return memberSelfIntroduction;
	}
	public void setMemberSelfIntroduction(String memberSelfIntroduction) {
		this.memberSelfIntroduction = memberSelfIntroduction;
	}
	public String getBroadcastWebsite() {
		return broadcastWebsite;
	}
	public void setBroadcastWebsite(String broadcastWebsite) {
		this.broadcastWebsite = broadcastWebsite;
	}
	public String getBroadcastTitle() {
		return broadcastTitle;
	}
	public void setBroadcastTitle(String broadcastTitle) {
		this.broadcastTitle = broadcastTitle;
	}
	public String getBroadcastClassName() {
		return broadcastClassName;
	}
	public void setBroadcastClassName(String broadcastClassName) {
		this.broadcastClassName = broadcastClassName;
	}
	public java.util.Date getBroadcastTime() {
		return broadcastTime;
	}
	public void setBroadcastTime(java.util.Date broadcastTime) {
		this.broadcastTime = broadcastTime;
	}
	public String getBroadcastDescription() {
		return broadcastDescription;
	}
	public void setBroadcastDescription(String broadcastDescription) {
		this.broadcastDescription = broadcastDescription;
	}
	public long getBroadcastWatchTimes() {
		return broadcastWatchTimes;
	}
	public void setBroadcastWatchTimes(long broadcastWatchTimes) {
		this.broadcastWatchTimes = broadcastWatchTimes;
	}

}
