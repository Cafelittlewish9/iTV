package model.dao;

import java.util.List;

import model.vo.ArticleVO;

public interface ArticleDAO {

	public List<ArticleVO> selectAll();

	public List<ArticleVO> selectBySubclassNo(String subclassNo);

	public List<ArticleVO> selectByMemberAccountAndSubclassNo(String memberacc, String subclassNo);
	
	public List<ArticleVO> selectByArticleTitle(String articleTitle);

	public List<ArticleVO> selectByMemberAccount(String memberAccount);
	public List<ArticleVO> selectByMemberAccountAndArticleTitle(String memberAccount, String articleTitle);
	public List<ArticleVO> selectByMemberAccountOrArticleTitleAndSubclassNo(String subclassNo, String memberAccount,
			String articleTitle);
	
	public List<ArticleVO> selectByMemberId(int memberId);	

	public boolean insert(ArticleVO article);

	public boolean update(ArticleVO article);

	public boolean delete(int articleId);

}