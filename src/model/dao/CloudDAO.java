package model.dao;

import java.util.List;
import model.vo.CloudVO;

public interface CloudDAO {

	List<CloudVO> selectAll();

	List<CloudVO> selectByMemberId(int memberId);

	List<CloudVO> selectByFileName(int memberId, String fileName);

	List<CloudVO> selectByTime(int memberId, java.util.Date fromTime, java.util.Date toTime);

	List<CloudVO> selectByFileType(int memberId, String fileType);

	List<CloudVO> selectByFileNameAndTime(int memberId, String fileName, java.util.Date fromTime,
			java.util.Date toTime);

	List<CloudVO> selectByFileNameAndFileType(int memberId, String fileName, String fileType);

	List<CloudVO> selectByFileTypeAndTime(int memberId, java.util.Date fromTime, java.util.Date toTime,
			String fileType);

	List<CloudVO> selectByFileNameFileTypeAndTime(int memberId, String fileName, java.util.Date fromTime,
			java.util.Date toTime, String fileType);

	int insert(CloudVO file);

	int updateFile(String filePath, long fileSize, int fileId);

	public int updateFileName(int fileId, String fileName, String filePath);

	int delete(int fileId);

}