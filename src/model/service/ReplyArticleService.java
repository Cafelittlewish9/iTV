package model.service;

import java.util.Collection;

import model.dao.ReplyArticleDAO;
import model.dao.jdbc.ReplyArticleDAOjdbc;
import model.vo.ReplyArticleVO;

public class ReplyArticleService {
	private ReplyArticleDAO dao;

	public ReplyArticleService() {
		this.dao = new ReplyArticleDAOjdbc();
	}

	public Collection<ReplyArticleVO> listReplyArticle(int articleId) {
		return dao.selectByArticleId(articleId);
	}

	public boolean addReplyArticle(int memberId, int articleId, String replyContent) {
		int temp = dao.insert(memberId, articleId, replyContent);
		if (temp == 1) {
			return true;
		} else {
			return false;
		}
	}

	public boolean modifyReplyArticle(String replyContent, int replyArticleId) {
		if (replyContent != null && replyContent.trim().length() != 0) {
			int temp = dao.update(replyContent, replyArticleId);
			if (temp == 1) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public boolean deleteReplyArticle(int replyArticleId) {
		int temp = dao.delete(replyArticleId);
		if (temp == 1) {
			return true;
		} else {
			return false;
		}
	}
}
