package model.dao;

import java.util.List;

import model.vo.ReportVideoVO;

public interface ReportVideoDAO {

	List<ReportVideoVO> selectAll();

	boolean insert(ReportVideoVO reportVideo);

	boolean delete(int orderId);

}