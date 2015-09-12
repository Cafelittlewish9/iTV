package model.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.dao.ArticleClassDAO;
import model.vo.ArticleClassVO;
import util.GC;

public class ArticleClassDAOjdbc implements ArticleClassDAO {
	
	private static final String SELECT_ALL =
			"SELECT subclassNo,subclassName,className FROM articleclass";
	@Override
	public List<ArticleClassVO> selectAll(){
		ArticleClassVO acvo;
		List<ArticleClassVO> acvos=new ArrayList<ArticleClassVO>();
		try(Connection conn=DriverManager.getConnection(GC.URL, GC.USERNAME, GC.PASSWORD);
			PreparedStatement pstmt = conn.prepareStatement(SELECT_ALL);) {
			ResultSet rs=pstmt.executeQuery();		
			while (rs.next()) {
				acvo=new ArticleClassVO(); 
				acvo.setClassName(rs.getString("subclassNo"));
				acvo.setSubclassName(rs.getString("subclassName"));
				acvo.setSubclassNo(rs.getString("className"));			
				acvos.add(acvo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return acvos;
	}

	private static final String SELECT =
			"SELECT subclassNo,subclassName,className FROM articleclass WHERE subclassNo=?";
	@Override
	public ArticleClassVO select(String subclassNo){
		ArticleClassVO acvo=null;
		try (Connection conn=DriverManager.getConnection(GC.URL, GC.USERNAME, GC.PASSWORD);
				PreparedStatement pstmt = conn.prepareStatement(SELECT);){			
			pstmt.setString(1, subclassNo);
			ResultSet rs=pstmt.executeQuery();		
			while (rs.next()) {
				acvo=new ArticleClassVO(); 
				acvo.setSubclassNo(rs.getString("subclassNo"));			
				acvo.setSubclassName(rs.getString("subclassName"));
				acvo.setClassName(rs.getString("className"));			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return acvo;
	}
	
	private static final String INSERT = "INSERT INTO ArticleClass VALUES (?,?,?)";
	@Override
	public boolean insert(ArticleClassVO articleClass){
		boolean result=false;		
		try (Connection conn=DriverManager.getConnection(GC.URL, GC.USERNAME, GC.PASSWORD);
				PreparedStatement pstmt = conn.prepareStatement(INSERT);) {
			pstmt.setString(1,articleClass.getSubclassNo());
			pstmt.setString(2,articleClass.getSubclassName());
			pstmt.setString(3,articleClass.getClassName());
			int updateCount=pstmt.executeUpdate();		
			if(updateCount==1){
				result=true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return result;		
	}

	private static final String UPDATE="UPDATE ArticleClass SET SubclassName=?,ClassName=? WHERE subclassNo=?";
	@Override
	public boolean update(ArticleClassVO articleClass) {
		boolean result=false;
		try (Connection conn=DriverManager.getConnection(GC.URL, GC.USERNAME, GC.PASSWORD);
				PreparedStatement pstmt = conn.prepareStatement(UPDATE);){
			if(articleClass!=null){
				pstmt.setString(1,articleClass.getSubclassName());
				pstmt.setString(2,articleClass.getClassName());
				pstmt.setString(3,articleClass.getSubclassNo());
				int updateCount=pstmt.executeUpdate();
				if (updateCount==1) {
					result = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	private static final String DELETE = "DELETE FROM ArticleClass WHERE subclassNo=?";
	@Override
	public boolean delete(String subclassNo) {
		boolean result=false;
		try(Connection conn=DriverManager.getConnection(GC.URL, GC.USERNAME, GC.PASSWORD);
				PreparedStatement pstmt = conn.prepareStatement(DELETE);) {
			pstmt.setString(1, subclassNo);
			int updateCount = pstmt.executeUpdate();
			if (updateCount >= 1) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return result;
	}
	
	//測試程式
	public static void main(String[] args) throws SQLException{
		ArticleClassDAO temp=new ArticleClassDAOjdbc();
		ArticleClassVO acvo=new ArticleClassVO();
		acvo.setSubclassNo("o");
		acvo.setSubclassName("聯航");
		acvo.setClassName("交通");
//		System.out.println(temp.selectAll());
//		System.out.println(temp.select("o"));		
//		System.out.println(temp.insert(acvo));
		System.out.println(temp.update(acvo));
//		System.out.println(temp.delete("o"));
		
		
	}
	
	
}
