package model.dao;

import java.util.List;

import model.vo.BlackVO;

public interface BlackDAO {

	public boolean markBlack(int memberId, int blackedId);

	public List<BlackVO> getList(int memberId);

	public boolean removeBlack(int memberId, int blackedId);

	public boolean removeAll(int memberId);

}