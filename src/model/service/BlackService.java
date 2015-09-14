package model.service;

import java.util.Collection;
import model.dao.BlackDAO;
import model.dao.jdbc.BlackDAOjdbc;
import model.vo.BlackVO;

/**
 * @author iTV小組成員
 *
 */
public class BlackService {
	private BlackDAO dao;

	/**
	 * 初始化BlackDAO
	 */
	public BlackService() {
		this.dao = new BlackDAOjdbc();
	}

	/**
	 * 增加黑名單
	 * @param memberId 黑別人的人
	 * @param blackedId 被黑的人
	 * @return Collection<BlackVO>
	 */	
	public boolean insertBlackList(int memberId, int blackedId) {
		return dao.markBlack(memberId, blackedId);
	}

	/**
	 * 查詢所有黑名單
	 * @param memberId 黑別人的人 
	 * @return Collection<BlackVO>
	 */
	public Collection<BlackVO> searchBlackAccount(int memberId) {
		return dao.getList(memberId);
	}

	/**
	 * 刪除單一黑名單
	 * @param memberId 黑別人的人
	 * @param blackedId 被黑的人
	 * @return boolean
	 */
	public boolean removeBlackAccount(int memberId, int blackedId) {
		return dao.removeBlack(memberId, blackedId);
	}
	
	/**
	 * 刪除全部黑名單
	 * @param memberId 黑別人的人 
	 * @return boolean
	 */	
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
