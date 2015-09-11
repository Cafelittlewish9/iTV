package model.dao;

import java.util.List;

import model.vo.ArticleVO;

public interface ArticleDAO {

	public List<ArticleVO> selectAll();

	public boolean insert(ArticleVO article);

	public boolean update(ArticleVO article);

	public boolean delete(int articleId, int memberId);

}