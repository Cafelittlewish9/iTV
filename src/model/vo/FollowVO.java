package model.vo;

public class FollowVO {
	private int memberId;
	private int followId;
	private MemberVO member;
	
	@Override
	public String toString() {
		return memberId + ": " + followId + " (被追隨的)";
	}
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public int getFollowId() {
		return followId;
	}
	public void setFollowId(int followId) {
		this.followId = followId;
	}
	public MemberVO getMember() {
		return member;
	}
	public void setMember(MemberVO member) {
		this.member = member;
	}
}
