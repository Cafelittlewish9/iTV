package model.dao;

import java.util.List;

import model.vo.ChannelVO;

public interface ChannelDAO {

	public ChannelVO select(int memberId, int channelNo);

	public List<ChannelVO> selectAll();

	public ChannelVO insert(ChannelVO bean);

	public ChannelVO update(String broadcastWebsite, int memberId, int channelNo);

	public boolean delete(int memberId, int channelNo);

}