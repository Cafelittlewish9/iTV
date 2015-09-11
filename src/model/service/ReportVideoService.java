package model.service;

import java.util.Collection;

import model.dao.ReportVideoDAO;
import model.dao.VideoDAO;
import model.dao.jdbc.ReportVideoDAOjdbc;
import model.dao.jdbc.VideoDAOjdbc;
import model.vo.ReportVideoVO;

public class ReportVideoService {
	private ReportVideoDAO dao;
	private VideoDAO dao2;
	
	public ReportVideoService(){
		this.dao = new ReportVideoDAOjdbc();
		this.dao2 = new VideoDAOjdbc();
	}
	public Collection<ReportVideoVO> selectAllList(){
		return dao.selectAll();
	}
	public boolean addReportVideo(int reportedVideoId,java.util.Date reportTime,String reportReason){
		ReportVideoVO bean = new ReportVideoVO();
		bean.setReportedVideoId(reportedVideoId);
		bean.setReportTime(reportTime);
		bean.setReportReason(reportReason);
		return dao.insert(bean);
	}
	public boolean deleteVideo(ReportVideoVO bean){
						// 拿到要舉報的VideoId，所以才能讓Video刪掉自己的資料
		boolean result1 = dao2.delete(bean.getReportedVideoId());
		boolean result2 = dao.delete(bean.getOrderId());
		
		return false;
	}	
}
