package model.vo;

public class BlackVO {
	private int memberId;
	private int blackedId;
	private MemberVO member;
	
	public MemberVO getMember() {
		return member;
	}
	public void setMember(MemberVO member) {
		this.member = member;
	}
	@Override
	public String toString() {
		return memberId + ": " + blackedId + " (被黑的)";
	}
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public int getBlackedId() {
		return blackedId;
	}
	public void setBlackedId(int blackedId) {
		this.blackedId = blackedId;
	}
}
