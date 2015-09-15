package model.service;

import java.util.List;
import model.dao.MemberDAO;
import model.dao.ReportMemberDAO;
import model.dao.jdbc.MemberDAOjdbc;
import model.dao.jdbc.ReportMemberDAOjdbc;
import model.vo.ReportMemberVO;

public class ReportMemberService {
	private ReportMemberDAO dao;
	private MemberDAO dao2;

	public ReportMemberService() {
		this.dao = new ReportMemberDAOjdbc();
		this.dao2 = new MemberDAOjdbc();
	}

	public boolean addReportMember(int reportedMemberId, String reportReason) {
		ReportMemberVO bean = new ReportMemberVO();
		bean.setReportedMemberId(reportedMemberId);
		bean.setReportReason(reportReason);
		ReportMemberVO demo = dao.insert(bean);
		if (demo != null) {
			return true;
		} else {
			return false;
		}
	}

	public List<ReportMemberVO> selectAll() {
		return dao.selectAll();
	}

	public boolean deleteReportMember(ReportMemberVO bean) {
		int result1 = dao2.switchSuspend(bean.getReportedMemberId(), true);
		boolean result2 = dao.delete(bean.getOrderId());
		if (result1 == 1 && result2) {
			return true;
		} else {
			return false;
		}
	}

}
