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
}
