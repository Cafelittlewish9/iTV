package model.service;

import model.dao.ArticleClassDAO;
import model.dao.jdbc.ArticleClassDAOjdbc;
import model.vo.ArticleClassVO;

public class ArticleClassService {
	private ArticleClassDAO dao;
	
	public ArticleClassService(){
		this.dao = new ArticleClassDAOjdbc();
	}
	public boolean addArticleClass(String subclassName,String className){
		ArticleClassVO bean = new ArticleClassVO();
			bean.setSubclassName(subclassName);
			bean.setClassName(className);
			boolean demo = dao.insert(bean);
			if(demo != false){
				return true;
			}else{
				return false;
			}		
	}
	public boolean updateArticleClass(String subclassName,String className){
		ArticleClassVO bean = new ArticleClassVO();
			bean.setSubclassName(subclassName);
			bean.setClassName(className);
			boolean demo = dao.update(bean);
			if(demo != false){
				return true;
			}else{
				return false;
			}	
	}
	public void deleteArticleClass(String subclassNo){
		dao.delete(subclassNo);
	}
}
