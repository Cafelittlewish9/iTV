package model.dao;

import java.util.List;

import model.vo.MemberVO;

public interface MemberDAO {

	public int insert(MemberVO member) ;

	public int insert2(MemberVO member);

	public List<MemberVO> getMemberList();

	public int getId(String memberAccount);

	public int update(MemberVO member);

	public List<MemberVO> selectAll();

	public MemberVO findByPK(int memberId);

	public int switchSuspend(int memberId, boolean suspendRight);
	
	

}