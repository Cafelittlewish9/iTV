package model.dao;

import java.util.List;

import model.vo.ReplyArticleVO;

public interface ReplyArticleDAO {

	public List<ReplyArticleVO> selectAll();

	public List<ReplyArticleVO> select(int articleId);

	public int insert(ReplyArticleVO replyArticle);

	public int update(ReplyArticleVO replyArticle);

	public int delete(ReplyArticleVO replyArticle);

}