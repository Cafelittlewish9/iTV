package model.dao;

import java.util.List;

import model.vo.ShowVO;

public interface ShowDAO {

	public List<ShowVO> select(int memberId);

	public List<ShowVO> selectAll();

	public int insert(ShowVO bean);

	public int update(java.util.Date showTime, String website, int memberId, java.util.Date showTimed);

	public boolean delete(int memberId, java.util.Date showTime);

}