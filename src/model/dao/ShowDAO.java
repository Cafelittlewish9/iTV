package model.dao;

import java.util.List;

import model.vo.ShowVO;


public interface ShowDAO {
	public abstract ShowVO select(int memberId);

	public abstract List<ShowVO> selectAll();

	public abstract ShowVO insert(ShowVO bean);

	public abstract ShowVO update(java.util.Date showTime, String website, int memberId);

	public abstract boolean delete(int memberId);
}
