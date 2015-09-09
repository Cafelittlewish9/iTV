package model.dao;

import java.util.List;

import model.vo.CloudVO;

public interface CloudDAO {

	public List<CloudVO> selectAll();

	public List<CloudVO> select(String fileName);

	public List<CloudVO> select(java.util.Date fromTime, java.util.Date toTime);

	public int insert(CloudVO file);

	public int update(CloudVO file);

	public int delete(int fileId);

}