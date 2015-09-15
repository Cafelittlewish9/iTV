package model.dao;

import java.util.List;

import model.vo.ArticleClassVO;

public interface ArticleClassDAO {

	public List<ArticleClassVO> selectAll();


	public List<ArticleClassVO> select(String subclassNo);

	public ArticleClassVO selectBySubclassNo(String subclassNo);
	
	public ArticleClassVO selectBySubclassName(String subclassName);


	public boolean insert(ArticleClassVO bean);

	public boolean update(ArticleClassVO bean);

	public boolean delete(String subclassNo);

}