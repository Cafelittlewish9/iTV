package model.dao;

import java.util.List;

import model.vo.ReplyArticleVO;

public interface ReplyArticleDAO {

	List<ReplyArticleVO> selectAll();

	List<ReplyArticleVO> select(int articleId);

	int insert(ReplyArticleVO replyArticle);

	int update(ReplyArticleVO replyArticle);

	int delete(ReplyArticleVO replyArticle);

}