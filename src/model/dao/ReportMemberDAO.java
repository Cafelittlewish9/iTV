package model.dao;

import java.util.List;

import model.vo.ReportMemberVO;

public interface ReportMemberDAO {

	public List<ReportMemberVO> select(int reportedMemberId);

	public List<ReportMemberVO> selectAll();

	public ReportMemberVO insert(ReportMemberVO bean);

	public boolean delete(int orderId);

}