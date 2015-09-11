package model.dao;

import java.util.List;

import model.vo.LoginVO;

public interface LoginDAO {

	public List<LoginVO> selectAll(String memberAccount);
	
	public List<LoginVO> selectAll();
	
	public LoginVO select(String memberAccount);

	public LoginVO insert(LoginVO bean);

}