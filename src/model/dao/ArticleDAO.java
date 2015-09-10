package model.dao;

import java.util.List;

import model.vo.ArticleVO;

public interface ArticleDAO {

	List<ArticleVO> selectAll();

	boolean insert(ArticleVO article);

	boolean update(ArticleVO article);

	boolean delete(int articleId, int memberId);

}