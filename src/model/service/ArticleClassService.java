package model.service;

import model.dao.ArticleClassDAO;
import model.dao.jdbc.ArticleClassDAOjdbc;
import model.vo.ArticleClassVO;

public class ArticleClassService {
	private ArticleClassDAO dao;

	public ArticleClassService() {
		this.dao = new ArticleClassDAOjdbc();
	}
	public boolean addArticleClass(String subclassNo, String subclassName, String className) {
		ArticleClassVO bean = new ArticleClassVO();
		bean.setSubclassNo(subclassNo);
		bean.setSubclassName(subclassName);
		bean.setClassName(className);
		return dao.insert(bean);
	}

	public boolean addArticleClass(ArticleClassVO bean) {
		boolean result = false;
		if (bean != null) {
			result = dao.insert(bean);
		}
		return result;
	}

	public boolean updateArticleClass(String subclassNo, String subclassName, String className) {
		ArticleClassVO bean = new ArticleClassVO();
		bean.setSubclassNo(subclassNo);
		bean.setSubclassName(subclassName);
		bean.setClassName(className);
		return dao.update(bean);
	}

	public boolean updateArticleClass(ArticleClassVO bean) {
		boolean result = false;
		if (bean != null) {
			result = dao.update(bean);
		}
		return result;
	}
	
	public boolean deleteArticleClass(String subclassNo) {
		return dao.delete(subclassNo);
	}
}
