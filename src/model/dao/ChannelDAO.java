package model.dao;

import java.util.List;

import model.vo.ChannelVO;

public interface ChannelDAO {

	public ChannelVO select(int memberId, int channelNo);
	
	public List<ChannelVO> selectAll(int memberId);

	public List<ChannelVO> selectAll();

	public int insert(ChannelVO bean);

	public int update(String broadcastWebsite, int memberId, int channelNo);

	public boolean delete(int memberId, int channelNo);

}