package model.service;

import java.util.Collection;
import model.dao.ArticleDAO;
import model.dao.jdbc.ArticleDAOjdbc;
import model.vo.ArticleVO;

public class ArticleService {
	private ArticleDAO dao;

	public ArticleService() {
		this.dao = new ArticleDAOjdbc();
	}

	public Collection<ArticleVO> article() {
		Collection<ArticleVO> list = null;
		return list;
	}
	
	public boolean addArticle(){
		boolean result=false;
		
		
		
		
		return result;
	}
	
	/*public boolean modifyArticle(ArticleVO avo) {
		boolean temp = dao.update(avo);
		if (temp == 1) {
			return true;
		} else {
			return false;
		}
	}*/

	public boolean deleteArticle(int memberId, byte channelNo) {
		return dao.delete(memberId, channelNo);
	}
	
	
}
