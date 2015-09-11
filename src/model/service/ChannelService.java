package model.service;

import java.util.Collection;
import model.dao.ChannelDAO;
import model.dao.jdbc.ChannelDAOjdbc;
import model.vo.ChannelVO;

public class ChannelService {
	private ChannelDAO dao;

	public ChannelService() {
		this.dao = new ChannelDAOjdbc();
	}

	public Collection<ChannelVO> allChannel(int memberId) {
		return dao.selectAll(memberId);
	}

	public boolean addChannel(int memberId, byte channelNo, String broadcastWebsite) {
		ChannelVO bean = new ChannelVO();
		bean.setMemberId(memberId);
		bean.setChannelNo(channelNo);
		bean.setBroadcastWebsite(broadcastWebsite);
		int result = dao.insert(bean);
		if (result == 1) {
			return true;
		}
		return false;
	}

	public boolean changeChannel(String broadcastWebsite, int memberId, byte channelNo) {
		int result = dao.update(broadcastWebsite, memberId, channelNo);
		if (result == 1) {
			return true;
		}
		return false;
	}

	public boolean removeChannel(int memberId, byte channelNo) {
		return dao.delete(memberId, channelNo);
	}
}
