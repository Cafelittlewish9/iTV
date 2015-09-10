package model.dao;

import java.util.List;

import model.vo.BlackVO;

public interface BlackDAO {

	boolean markBlack(int memberId, int blackedId);

	List<BlackVO> getList(int memberId);

	boolean removeBlack(int memberId, int blackedId);

	boolean removeAll(int memberId);

}