package model.service;

import model.dao.VideoDAO;
import model.dao.jdbc.VideoDAOjdbc;

public class VideoService {
	private VideoDAO dao;
	public VideoService() {
		this.dao = new VideoDAOjdbc();
	}
	public boolean uploadVideo() {
		return false;
	}
}
