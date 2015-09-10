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

public class ArticleClassDAOjdbc implements ArticleClassDAO {
	private final String URL = "jdbc:sqlserver://y56pcc16br.database.windows.net:1433;database=iTV";
	private final String USERNAME = "iTVSoCool";//iTVSoCool@y56pcc16br
	private final String PASSWORD = "iTVisgood911";	
	
	
	private static final String SELECT_ALL =
			"SELECT subclassNo,subclassName,className FROM articleclass";	
	/* (non-Javadoc)
	 * @see model.dao.jdbc.ArticleClassDAO#selectAll()
	 */
	@Override
	public List<ArticleClassVO> selectAll() throws SQLException{
		ArticleClassVO acvo;
		List<ArticleClassVO> acvos=new ArrayList<ArticleClassVO>();
		Connection conn=DriverManager.getConnection(URL, USERNAME, PASSWORD);
		PreparedStatement pstmt = conn.prepareStatement(SELECT_ALL);
		ResultSet rs=pstmt.executeQuery();		
		while (rs.next()) {
			acvo=new ArticleClassVO(); 
			acvo.setClassName(rs.getString("subclassNo"));
			acvo.setSubclassName(rs.getString("subclassName"));
			acvo.setSubclassNo(rs.getString("className"));			
			acvos.add(acvo);
		}
		return acvos;
	}

	private static final String SELECT =
			"SELECT subclassNo,subclassName,className FROM articleclass WHERE subclassNo=?";	
	/* (non-Javadoc)
	 * @see model.dao.jdbc.ArticleClassDAO#select(java.lang.String)
	 */
	@Override
	public ArticleClassVO select(String subclassNo) throws SQLException{
		ArticleClassVO acvo=null;
		Connection conn=DriverManager.getConnection(URL, USERNAME, PASSWORD);
		PreparedStatement pstmt = conn.prepareStatement(SELECT);
		pstmt.setString(1, subclassNo);
		ResultSet rs=pstmt.executeQuery();		
		while (rs.next()) {
			acvo=new ArticleClassVO(); 
			acvo.setSubclassNo(rs.getString("subclassNo"));			
			acvo.setSubclassName(rs.getString("subclassName"));
			acvo.setClassName(rs.getString("className"));			
		}
		return acvo;
	}
	
	private static final String INSERT = "INSERT INTO ArticleClass VALUES (?,?,?)";
	/* (non-Javadoc)
	 * @see model.dao.jdbc.ArticleClassDAO#insert(model.vo.ArticleClassVO)
	 */
	@Override
	public boolean insert(ArticleClassVO articleClass) throws SQLException{
		boolean result=false;		
		Connection conn=DriverManager.getConnection(URL, USERNAME, PASSWORD);
		PreparedStatement pstmt = conn.prepareStatement(INSERT);
		pstmt.setString(1,articleClass.getSubclassNo());
		pstmt.setString(2,articleClass.getSubclassName());
		pstmt.setString(3,articleClass.getClassName());
		int updateCount=pstmt.executeUpdate();		
		if(updateCount==1){
			result=true;
		}		
		return result;		
	}

	private static final String UPDATE="UPDATE ArticleClass SET SubclassName=?,ClassName=? WHERE subclassNo=?";
	/* (non-Javadoc)
	 * @see model.dao.jdbc.ArticleClassDAO#update(model.vo.ArticleClassVO)
	 */
	@Override
	public boolean update(ArticleClassVO articleClass) throws SQLException {
		boolean result=false;
		if(articleClass!=null){
			Connection conn=DriverManager.getConnection(URL, USERNAME, PASSWORD);
			PreparedStatement pstmt = conn.prepareStatement(UPDATE);
			pstmt.setString(1,articleClass.getSubclassName());
			pstmt.setString(2,articleClass.getClassName());
			pstmt.setString(3,articleClass.getSubclassNo());
			int updateCount=pstmt.executeUpdate();
			if (updateCount==1) {
				result = true;
			}
		}
		return result;
	}

	private static final String DELETE = "DELETE FROM ArticleClass WHERE subclassNo=?";
	/* (non-Javadoc)
	 * @see model.dao.jdbc.ArticleClassDAO#delete(java.lang.String)
	 */
	@Override
	public boolean delete(String subclassNo) throws SQLException {
		boolean result=false;
		Connection conn=DriverManager.getConnection(URL, USERNAME, PASSWORD);
		PreparedStatement pstmt = conn.prepareStatement(DELETE);
		pstmt.setString(1, subclassNo);
		int updateCount = pstmt.executeUpdate();
		if (updateCount >= 1) {
			result = true;
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
