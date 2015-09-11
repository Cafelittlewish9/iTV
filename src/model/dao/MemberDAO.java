package model.dao;

import java.sql.SQLException;
import java.util.List;

import model.vo.MemberVO;

public interface MemberDAO {

	int insert(MemberVO member) throws SQLException;

	int insert2(MemberVO member);

	List<MemberVO> getMemberList();

	int getId(String memberAccount);

	int update(MemberVO member);

	List<MemberVO> selectAll();

	MemberVO findByPK(int memberId);

}