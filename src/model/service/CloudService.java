package model.service;

import java.util.Collection;
import model.dao.CloudDAO;
import model.dao.jdbc.CloudDAOjdbc;
import model.vo.CloudVO;

public class CloudService {
	private CloudDAO dao;

	public CloudService() {
		this.dao = new CloudDAOjdbc();
	}

	public Collection<CloudVO> allFile(int memberId) {
		return dao.selectAll(memberId);
	}

	public Collection<CloudVO> searchFile(String fileName) {
		Collection<CloudVO> result = null;
		if (fileName != null && fileName.trim().length() != 0) {
			result = dao.select(fileName);
		}
		return result;
	}

	public Collection<CloudVO> searchFile(java.util.Date fromTime, java.util.Date toTime) {
		Collection<CloudVO> result = null;
		if (fromTime != null && toTime != null) {
			result = dao.select(fromTime, toTime);
		}
		return result;
	}

	public Collection<CloudVO> searchFile(String fileName, java.util.Date fromTime, java.util.Date toTime) {
		Collection<CloudVO> result = null;
		if (fileName != null && fileName.trim().length() != 0) {
			if (fromTime != null && toTime != null) {
				result = dao.select(fileName, fromTime, toTime);
			}
		}
		return result;
	}

	// public boolean changeFileName(String fileName) {
	// if (fileName != null && fileName.trim().length() != 0) {
	// dao.update(null);
	// return true;
	// } else {
	// return false;
	// }
	// }
}
