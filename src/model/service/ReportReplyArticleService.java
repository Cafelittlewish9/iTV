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

	public ReportReplyArticleService(){
		this.dao = new ReportReplyArticleDAOjdbc();
		this.dao2 = new ReplyArticleDAOjdbc();
	}
	public boolean addReportReplyArticle(int reportedReplyArticleId,java.util.Date reportTime,String reportReason){
		ReportReplyArticleVO bean = new ReportReplyArticleVO();
		bean.setReportedReplyArticleId(reportedReplyArticleId);
		bean.setReportTime(reportTime);
		bean.setReportReason(reportReason);
		return dao.insert(bean);
	}
	public Collection<ReportReplyArticleVO> selectAllList(){
		return dao.selectAll();
	}
	public boolean deleteReplyArticle(ReportReplyArticleVO bean){
		dao.delete(bean.getReportedReplyArticleId());
		dao.delete(bean.getOrderId());
		
		return false;
	}
}
