package model.dao;

import java.util.List;

import model.vo.ReportArticleVO;

public interface ReportArticleDAO {

	public List<ReportArticleVO> selectAll();

	public boolean insert(ReportArticleVO reportArticle);

	public boolean delete(int orderId);

}