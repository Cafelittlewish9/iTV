package model.service;

import java.util.Collection;

import model.dao.FollowDAO;
import model.dao.jdbc.FollowDAOjdbc;
import model.vo.FollowVO;

public class FollowService {
	private FollowDAO dao;

	public FollowService() {
		this.dao = new FollowDAOjdbc();
	}

	public boolean follow(int memberId, int followId) {
		FollowVO bean = new FollowVO();
		bean.setMemberId(memberId);
		bean.setFollowId(followId);
		int result = dao.insert(bean);
		if (result == 1) {
			return true;
		} else {
			return false;
		}
	}

	public boolean unfollow(int memberId, int followId) {
		return dao.delete(memberId, followId);
	}
	
	public Collection<FollowVO> followList(int memberId) {
		Collection<FollowVO> list = dao.selectAll(memberId);
		return list;
	}
}
