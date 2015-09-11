package model.dao;

import java.sql.SQLException;
import java.util.List;

import model.vo.ArticleClassVO;

public interface ArticleClassDAO {

	public List<ArticleClassVO> selectAll() throws SQLException;

	public ArticleClassVO select(String subclassNo) throws SQLException;

	public boolean insert(ArticleClassVO articleClass) throws SQLException;

	public boolean update(ArticleClassVO articleClass) throws SQLException;

	public boolean delete(String subclassNo) throws SQLException;

}