package model.dao;

import java.sql.SQLException;
import java.util.List;

import model.vo.ArticleClassVO;

public interface ArticleClassDAO {

	List<ArticleClassVO> selectAll() throws SQLException;

	ArticleClassVO select(String subclassNo) throws SQLException;

	boolean insert(ArticleClassVO articleClass) throws SQLException;

	boolean update(ArticleClassVO articleClass) throws SQLException;

	boolean delete(String subclassNo) throws SQLException;

}