package model.service;

import java.util.Collection;

import model.dao.VideoDAO;
import model.dao.jdbc.VideoDAOjdbc;
import model.vo.BlackVO;
import model.vo.MemberVO;
import model.vo.VideoVO;

public class VideoService {
	private VideoDAO dao;
	
	public VideoService() {
		this.dao = new VideoDAOjdbc();
	}	
	
	//另一個可以考慮的名稱為uploadVideo
	public boolean addVideo(VideoVO bean) {
		return dao.insert(bean);
	}

	//只能改videoDescription，另外兩個參數是應資料庫而要求的
	public boolean updateVideo(String videoDescription, java.util.Date videoDescriptionModifyTime, int videoId){
		return dao.update(videoDescription, videoDescriptionModifyTime, videoId);
	}
	
	public Collection<VideoVO> searchVideo(String videoTitle) {
		return dao.select(videoTitle);
	}

	public boolean removeVideo(int videoId) {
		return dao.delete(videoId);
	}	
	//不知道是否應該要有一個全刪的方法
	
	
	
	public static void main(String[] args) {
		VideoService service = new VideoService();
		VideoVO bean=new VideoVO();
		//是說不是應該要會員登入之後才准上傳嗎？還是我們很大方，他願意上傳就上傳，與會員的差異就只在能不能針對實況留言？！
		MemberVO member=new MemberVO();
		
		bean.setMemberId(5);
		bean.setVideoWebsite(member.getBroadcastWebsite());
		bean.setVideoClassName("㊣↙煞氣a↗㊣☆");
		bean.setVideoDescription("㊣↙煞氣a正牌哥吉拉之澎湖海報你把我一世英名都毀惹↗㊣☆ ");
		bean.setVideoName("㊣↙煞氣a哥吉拉↗㊣☆");
		//按目前資料庫的結果看來應該是上傳路徑，那不就應該要有個IO？！
		bean.setVideoPath("G:\\godzillaRocks.avi");
		boolean result = service.addVideo(bean);
//		System.out.println(result);
//		System.out.println(service.searchBlackAccount(1));
		
	}
	
	
}
