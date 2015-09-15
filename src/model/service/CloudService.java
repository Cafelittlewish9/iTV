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
		return dao.selectByMemberId(memberId);
	}

	public Collection<CloudVO> searchFile(int memberId, String fileName) {
		Collection<CloudVO> result = null;
		if (fileName != null && fileName.trim().length() != 0) {
			result = dao.selectByFileName(memberId, fileName);
		}
		return result;
	}

	public Collection<CloudVO> searchFile(int memberId, java.util.Date fromTime, java.util.Date toTime) {
		Collection<CloudVO> result = null;
		if (fromTime != null && toTime != null) {
			result = dao.selectByTime(memberId, fromTime, toTime);
		}
		return result;
	}

	public Collection<CloudVO> searchFile(String fileType, int memberId) {
		Collection<CloudVO> result = null;
		if (fileType != null && fileType != null) {
			result = dao.selectByFileType(memberId, fileType);
		}
		return result;
	}

	public Collection<CloudVO> searchFile(int memberId, String fileName, java.util.Date fromTime,
			java.util.Date toTime) {
		Collection<CloudVO> result = null;
		if (fileName != null && fileName.trim().length() != 0) {
			if (fromTime != null && toTime != null) {
				result = dao.selectByFileNameAndTime(memberId, fileName, fromTime, toTime);
			}
		}
		return result;
	}

	public Collection<CloudVO> searchFile(int memberId, String fileName, String fileType) {
		Collection<CloudVO> result = null;
		if (fileName != null && fileName.trim().length() != 0) {
			if (fileType != null && fileType.trim().length() != 0) {
				result = dao.selectByFileNameAndFileType(memberId, fileName, fileType);
			}
		}
		return result;
	}

	public Collection<CloudVO> searchFile(int memberId, java.util.Date fromTime, java.util.Date toTime,
			String fileType) {
		Collection<CloudVO> result = null;
		if (fileType != null && fileType.trim().length() != 0) {
			if (fromTime != null && toTime != null) {
				result = dao.selectByFileTypeAndTime(memberId, fromTime, toTime, fileType);
			}
		}
		return result;
	}

	public Collection<CloudVO> searchFile(int memberId, String fileName, java.util.Date fromTime, java.util.Date toTime,
			String fileType) {
		Collection<CloudVO> result = null;
		if (fileName != null && fileName.trim().length() != 0) {
			if (fileType != null && fileType.trim().length() != 0) {
				if (fromTime != null && toTime != null) {
					result = dao.selectByFileNameFileTypeAndTime(memberId, fileName, fromTime, toTime, fileType);
				}
			}
		}
		return result;
	}

	public boolean addFile(int memberId, String fileName, String fileType, String filePath, long fileSize) {
		boolean result = false;
		return result;
	}

	public boolean modifyFile(int fileId, String filePath, long fileSize) {
		boolean result = false;
		return result;
	}

	public boolean modifyFileName(int fileId, String fileName, String filePath) {
		boolean result = false;
		return result;
	}

	public boolean deleteFile(int fileId) {
		int temp = dao.delete(fileId);
		if (temp == 1) {
			return true;
		} else {
			return false;
		}
	}
}
