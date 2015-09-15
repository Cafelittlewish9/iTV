package model.service;

import java.util.Collection;
import model.dao.ReplyArticleDAO;
import model.dao.ReportReplyArticleDAO;
import model.dao.jdbc.ReplyArticleDAOjdbc;
import model.dao.jdbc.ReportReplyArticleDAOjdbc;
import model.vo.ReportReplyArticleVO;

public class ReportReplyArticleService {
	private ReportReplyArticleDAO dao;
	private ReplyArticleDAO dao2;

	public ReportReplyArticleService() {
		this.dao = new ReportReplyArticleDAOjdbc();
		this.dao2 = new ReplyArticleDAOjdbc();
	}

	public boolean addReportReplyArticle(int reportedReplyArticleId, String reportReason) {
		ReportReplyArticleVO bean = new ReportReplyArticleVO();
		bean.setReportedReplyArticleId(reportedReplyArticleId);
		bean.setReportReason(reportReason);
		return dao.insert(bean);
	}

	public Collection<ReportReplyArticleVO> selectAllList() {
		return dao.selectAll();
	}

	public boolean deleteReplyArticle(ReportReplyArticleVO bean) {
		int result1 = dao2.delete(bean.getReportedReplyArticleId());
		boolean result2 = dao.delete(bean.getOrderId());
		if (result1 == 1 && result2) {
			return true;
		} else {
			return false;
		}
	}
}
