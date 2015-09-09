package model.vo;

public class ChannelVO {
	private int memberId;
	private byte channelNo;
	private String broadcastWebsite;
	
	@Override
	public String toString() {
		return "頻道 " + channelNo + ": " + broadcastWebsite;
	}
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public byte getChannelNo() {
		return channelNo;
	}
	public void setChannelNo(byte channelNo) {
		this.channelNo = channelNo;
	}
	public String getBroadcastWebsite() {
		return broadcastWebsite;
	}
	public void setBroadcastWebsite(String broadcastWebsite) {
		this.broadcastWebsite = broadcastWebsite;
	}
}
