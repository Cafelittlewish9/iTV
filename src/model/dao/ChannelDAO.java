package model.dao;

import java.util.List;

import model.vo.ChannelVO;

public interface ChannelDAO {
	public abstract ChannelVO select(int memberId, int channelNo);

	public abstract List<ChannelVO> selectAll();

	public abstract ChannelVO insert(ChannelVO bean);

	public abstract ChannelVO update(String broadcastWebsite, int memberId, int channelNo);

	public abstract boolean delete(int memberId, int channelNo);
}
