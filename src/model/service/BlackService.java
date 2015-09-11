package model.service;

import java.util.Collection;
import model.dao.BlackDAO;
import model.dao.jdbc.BlackDAOjdbc;
import model.vo.BlackVO;

public class BlackService {
	private BlackDAO dao;

	public BlackService() {
		this.dao = new BlackDAOjdbc();
	}

	public boolean insertBlackList(int memberId, int blackedId) {
		return dao.markBlack(memberId, blackedId);
	}

	public Collection<BlackVO> searchBlackAccount(int memberId) {
		return dao.getList(memberId);
	}

	public boolean removeBlackAccount(int memberId, int blackedId) {
		return dao.removeBlack(memberId, blackedId);
	}

	public boolean removeAll(int memberId) {
		return dao.removeAll(memberId);
	}

	public static void main(String[] args) {
		BlackService service = new BlackService();
		boolean bool = service.insertBlackList(1, 3);
		System.out.println(bool);
		System.out.println(service.searchBlackAccount(1));
		
	}
}
