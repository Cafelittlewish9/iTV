package model.dao;

import java.util.List;

import model.vo.LoginVO;

public interface LoginDAO {

	List<LoginVO> select(String memberAccount);

	LoginVO insert(LoginVO bean);

}