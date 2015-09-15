package model.dao;

import java.util.List;

import model.vo.ArticleVO;

public interface ArticleDAO {

	public List<ArticleVO> selectAll();

	public List<ArticleVO> selectByInput(String subclassNo,String articleTitle,String memberAccount,String memberNickName);

	public boolean insert(ArticleVO article);

	public boolean update(ArticleVO article);

	public boolean delete(int articleId);

}