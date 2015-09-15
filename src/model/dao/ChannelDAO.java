package model.dao;

import java.util.List;

import model.vo.ChannelVO;

public interface ChannelDAO {

	public ChannelVO selectByChannelNo(int memberId, int channelNo);

	public List<ChannelVO> selectByMemberId(int memberId);

	public int insert(ChannelVO bean);

	public int update(String broadcastWebsite, int memberId, int channelNo);

	public int update(ChannelVO bean);

	public boolean delete(int memberId, int channelNo);

	public boolean deleteAll(int memberId);

	List<ChannelVO> selectAll();

}