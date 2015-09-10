package model.dao;

import java.util.List;

import model.vo.FollowVO;

public interface FollowDAO {

	List<FollowVO> select(int memberId);

	List<FollowVO> selectAll();

	FollowVO insert(FollowVO bean);

	boolean delete(int followId, int memberId);

}