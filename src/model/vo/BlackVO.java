package model.vo;

public class BlackVO {
	private int memberId;
	private int blackedId;
	
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
