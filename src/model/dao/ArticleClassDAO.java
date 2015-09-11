package model.dao;

import java.util.List;

import model.vo.ArticleClassVO;

public interface ArticleClassDAO {

	public List<ArticleClassVO> selectAll();

	public ArticleClassVO select(String subclassNo);

	public boolean insert(ArticleClassVO articleClass);

	public boolean update(ArticleClassVO articleClass);

	public boolean delete(String subclassNo);

}