package model.service;

import model.dao.ReplyArticleDAO;
import model.dao.jdbc.ReplyArticleDAOjdbc;

public class ReplyArticleService {
	private ReplyArticleDAO dao;

	public ReplyArticleService() {
		this.dao = new ReplyArticleDAOjdbc();
	}
	
}
