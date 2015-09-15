package model.dao;

import java.util.List;

import model.vo.MemberVO;

public interface MemberDAO {

	int insert(MemberVO member) ;

	int insert2(MemberVO member);

	List<MemberVO> getMemberList();

	int getId(String memberAccount);

	int update(MemberVO member);	

	MemberVO findByPK(int memberId);

	int switchSuspend(String memberAccount, boolean suspendRight);
	
	

}