package model.service;

import java.util.List;

import model.dao.ReportMemberDAO;
import model.dao.jdbc.ReportMemberDAOjdbc;
import model.vo.ReportMemberVO;

public class ReportMemberService {
	private ReportMemberDAO dao;
	
	public ReportMemberService(){
		this.dao = new ReportMemberDAOjdbc();
	}
	public boolean addReportMember(int reportedMemberId,java.util.Date reportTime,String reportReason){
		ReportMemberVO bean = new ReportMemberVO();	
			bean.setReportedMemberId(reportedMemberId);
			bean.setReportTime(reportTime);
			bean.setReportReason(reportReason);
			ReportMemberVO demo = dao.insert(bean);
			if(demo != null){
				return true;
			}else{
				return false;
			}		
	}
	
	public List<ReportMemberVO> selectAll(){
		return dao.selectAll();
	}
	
	public void deleteReportMember(int orderId){
		dao.delete(orderId);
	}
	
}
