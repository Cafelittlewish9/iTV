package model.dao.jdbc;

import java.util.List;

import model.vo.ReportMemberVO;

public interface ReportMemberDAO {

	List<ReportMemberVO> select(int reportedMemberId);

	List<ReportMemberVO> selectAll();

	ReportMemberVO insert(ReportMemberVO bean);

	boolean delete(int orderId);

}