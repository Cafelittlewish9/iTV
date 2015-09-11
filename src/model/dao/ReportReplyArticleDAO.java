package model.dao;

import java.util.List;

import model.vo.ReportReplyArticleVO;

public interface ReportReplyArticleDAO {

	List<ReportReplyArticleVO> selectAll();

	boolean insert(ReportReplyArticleVO reportReplyArticle);

	boolean delete(int orderId);

}