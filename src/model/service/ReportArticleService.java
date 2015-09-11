package model.service;

import java.util.Collection;

import model.dao.ArticleDAO;
import model.dao.ReportArticleDAO;
import model.dao.jdbc.ArticleDAOjdbc;
import model.dao.jdbc.ReportArticleDAOjdbc;
import model.vo.ReportArticleVO;

public class ReportArticleService {
	private ReportArticleDAO dao;
	private ArticleDAO dao2;

	public ReportArticleService() {
		this.dao = new ReportArticleDAOjdbc();
		this.dao2 = new ArticleDAOjdbc();
	}

	public Collection<ReportArticleVO> reportArticleList() {
		return dao.selectAll();
	}

	public boolean reportArticle(int reportedArticleId, String reportReason) {
		ReportArticleVO bean = new ReportArticleVO();
		bean.setReportedArticleId(reportedArticleId);
		bean.setReportReason(reportReason);
		return dao.insert(bean);
	}

	public boolean reportArticle(ReportArticleVO bean) {
		return dao.insert(bean);
	}

	public boolean deleteArticle(ReportArticleVO bean) {
		boolean result1 = dao2.delete(bean.getReportedArticleId());
		boolean result2 = dao.delete(bean.getOrderId());
		if (result1 && result2) {
			return true;
		} else {
			return false;
		}
	}
}
