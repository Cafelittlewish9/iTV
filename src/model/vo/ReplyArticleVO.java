package model.vo;

import java.text.SimpleDateFormat;

public class ReplyArticleVO {
	private int replyArticleId;
	private int memberId;
	private int articleId;
	private String replyContent;
	private java.util.Date publishTime;
	private java.util.Date modifyTime;
	
	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = sdf.format(publishTime);
		return replyArticleId + "回覆的文章ID為: " + articleId + " (" + date + ")";
	}
	public int getReplyArticleId() {
		return replyArticleId;
	}
	public void setReplyArticleId(int replyArticleId) {
		this.replyArticleId = replyArticleId;
	}
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public int getArticleId() {
		return articleId;
	}
	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	public java.util.Date getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(java.util.Date publishTime) {
		this.publishTime = publishTime;
	}
	public java.util.Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(java.util.Date modifyTime) {
		this.modifyTime = modifyTime;
	}
}
