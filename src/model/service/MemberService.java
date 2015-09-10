package model.service;

import java.sql.SQLException;
import java.util.Arrays;

import model.dao.MemberDAO;
import model.dao.jdbc.MemberDAOjdbc;
import model.vo.MemberVO;

//註冊後轉update頁面要由controller處理
//查詢、修改個人資料、搜尋會員
public class MemberService {
	private MemberDAO memberDao = new MemberDAOjdbc();
	
	//registry1採帳號密碼信箱註冊；registry2是採信箱密碼註冊
	public String registry1(String username,String password,String usermail)throws SQLException{
		String result=null;		
		MemberVO mvo=new MemberVO();
		if (username.length()==0){
			result="Please keyin a username.";
		}else{
			if (memberDao.getId(username)!=0)  {		
				result="Please change another username.";
			}else{
				mvo.setMemberAccount(username);
				mvo.setMemberPassword(password.getBytes());
				mvo.setMemberEmail(usermail);
//				mvo.setMemberRegisterTime(new java.util.Date());	
				memberDao.insert(mvo);
				result="success";
			}				
		}
		return result;
	}	
	
	//registry1採帳號密碼信箱註冊；registry2是採信箱密碼註冊
	public String registry2(String password,String usermail)throws SQLException{
		String result=null;		
		MemberVO mvo=new MemberVO();
		if (usermail.length()==0){
			result="Please keyin a your mail.";
		}else{
			if (memberDao.getId(usermail.substring(0,usermail.indexOf("@")))!=0)  {		
				result="Please change another mail address to login.";
			}else{
				mvo.setMemberAccount(usermail.substring(0,usermail.indexOf("@")));
				mvo.setMemberPassword(password.getBytes());
//				mvo.setMemberRegisterTime(new java.util.Date());	
				memberDao.insert(mvo);
				result="success";
			}				
		}
		return result;
	}
	//login1是採用帳號登入；login2是採信箱登入
	public MemberVO login1(String username, String password) {		
		int memberId = memberDao.getId(username);
		MemberVO mvo=memberDao.findByPK(memberId);
		if(mvo!=null) {
			if(password!=null && password.length()!=0) {
				byte[] memberPwd=mvo.getMemberPassword();
				byte[] temp = password.getBytes();
				if(Arrays.equals(memberPwd, temp)) {
					mvo=memberDao.findByPK(memberId);
					return mvo;					
				}
			}
		}
		return mvo;
	}
	//login1是採用帳號登入；login2是採信箱登入
	public MemberVO login2(String usermail, String password) {		
		int memberId = memberDao.getId(usermail.substring(0,usermail.indexOf("@")));
		MemberVO mvo=memberDao.findByPK(memberId);
		if(mvo!=null) {
			if(password!=null && password.length()!=0) {
				byte[] memberPwd=mvo.getMemberPassword();
				byte[] temp = password.getBytes();
				if(Arrays.equals(memberPwd, temp)) {
					mvo=memberDao.findByPK(memberId);
					return mvo;				
				}
			}
		}
		return mvo;
	}
	
	
	public String changePassword(String username, String oldPassword, String newPassword) {
		MemberVO mvo = this.login1(username, oldPassword);//透過帳密拿到該會員的資料
		String result="Error, please contact the administrator!";
		if(mvo!=null) {
			byte[] temp = newPassword.getBytes();
			mvo.setMemberPassword(temp);
			if (memberDao.update(mvo)==1) {
				result="You've successfully changed your password!";
			}								
		}	
		return result;
	}
	
	public void update(){
		
	}
	
	
	public static void main(String[] args) throws SQLException{
		MemberService service = new MemberService();
//		String result=service.registry1("niceguy","madclown@gmail.com", "E");
//		System.out.println("registry result="+result);
		MemberVO mvo = service.login1("niceguy", "madclown@gmail.com");
		System.out.println("VO info="+mvo);	
		
//		MemberVO mvo = service.login2("Squirtle@gmail.com", "C");
//		System.out.println("VO info="+mvo);
		
		String result = service.changePassword("niceguy", "madclown@gmail.com", "E");
		System.out.println("result="+result);	
		
	}
}
/*mvo.setMemberPassword(temp);
mvo.setMemberEmail(mvo.getMemberEmail());
mvo.setMemberFB(mvo.getMemberFB());
mvo.setMemberGoogle(mvo.getMemberGoogle());
mvo.setMemberTwitter(mvo.getMemberTwitter());
mvo.setMemberName(mvo.getMemberName());
mvo.setMemberNickname(mvo.getMemberNickname());
mvo.setMemberBirthday(mvo.getMemberBirthday());
mvo.setMemberPhoto(mvo.getMemberPhoto());
mvo.setMemberRegisterTime(mvo.getMemberRegisterTime());
mvo.setMemberSelfIntroduction(mvo.getMemberSelfIntroduction());
mvo.setBroadcastWebsite(mvo.getBroadcastWebsite());
mvo.setBroadcastTitle(mvo.getBroadcastTitle());
mvo.setBroadcastClassName(mvo.getBroadcastClassName());
mvo.setBroadcastTime(mvo.getBroadcastTime());
mvo.setBroadcastDescription(mvo.getBroadcastDescription());
mvo.setBroadcastWatchTimes(mvo.getBroadcastWatchTimes());*/


