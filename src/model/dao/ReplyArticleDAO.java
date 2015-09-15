package model.dao;

import java.util.List;

import model.vo.ReplyArticleVO;

public interface ReplyArticleDAO {

	public List<ReplyArticleVO> selectAll();

	public List<ReplyArticleVO> selectByArticleId(int articleId);

	public int insert(int memberId, int articleId, String replyContent);

	public int update(String replyContent, int replyArticleId);

	public int delete(int replyArticleId);

}