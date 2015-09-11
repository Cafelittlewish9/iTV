package model.dao;

import java.util.List;

import model.vo.ArticleVO;

public interface ArticleDAO {

	public List<ArticleVO> selectAll();

	public List<ArticleVO> selectAll(String subclassNo);

	public List<ArticleVO> selectAll(String memberacc, String subclassNo);
	
	public List<ArticleVO> select(String... articleTitle);

	public List<ArticleVO> select(String memberacc);
	
	public List<ArticleVO> select(int memberId);	

	public boolean insert(ArticleVO article);

	public boolean update(ArticleVO article);

	public boolean delete(int articleId);

}