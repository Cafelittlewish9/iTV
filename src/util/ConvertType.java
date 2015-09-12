package util;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

import model.dao.LoginDAO;
import model.dao.jdbc.LoginDAOjdbc;
import model.vo.LoginVO;

public class ConvertType {

	public static int convertToInt(String whichYouWantToConvert) {
		int result = Integer.MIN_VALUE;
		try {
			result = Integer.parseInt(whichYouWantToConvert);
		} catch (NumberFormatException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	public static long convertToLong(String whichYouWantToConvert) {
		long result = Long.MIN_VALUE;
		try {
			result = Long.parseLong(whichYouWantToConvert);
		} catch (NumberFormatException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	public static byte convertToByte(String whichYouWantToConvert) {
		byte result = Byte.MIN_VALUE;
		try {
			result = Byte.parseByte(whichYouWantToConvert);
		} catch (NumberFormatException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	public static String convertToLocalTime(java.util.Date whichYouWantToConvert, String whereIsUser) {
		String result = null;
		if (whichYouWantToConvert != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			sdf.setTimeZone(TimeZone.getTimeZone(whereIsUser));
			result = sdf.format(whichYouWantToConvert);
		}
		return result;
	}

	public static void main(String[] args) {
//		 System.out.println(TimeZone.getDefault());
//		 String[] temp = TimeZone.getAvailableIDs();
//		 for (String a : temp) {
//		 System.out.println(a);
//		 }
//		Set<String> temp2 = ZoneId.getAvailableZoneIds();
//		for (String b : temp2) {
//			if (b.contains("Asia")) {
//				b = b.substring(4);
//				System.out.println(b);
//			}
//		}
//		String time = ConvertType.convertToLocalTime(new java.util.Date(System.currentTimeMillis()), "Asia/Taipei");
//		System.out.println(time);
		
		LoginDAO dao = new LoginDAOjdbc();
		LoginVO bean = dao.select("Pikachu");
		String time2 = ConvertType.convertToLocalTime(bean.getLoginTime(), "Asia/Taipei");
		System.out.println(time2);
	}
}
