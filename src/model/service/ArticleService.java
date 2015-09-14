package model.service;

import java.util.Collection;
import model.dao.ArticleDAO;
import model.dao.jdbc.ArticleDAOjdbc;
import model.vo.ArticleVO;

public class ArticleService {
	private ArticleDAO dao;

	public ArticleService() {
		this.dao = new ArticleDAOjdbc();
	}

	//顯示文章列表
	public Collection<ArticleVO> allArticle() {		
		return dao.selectAll();
	}
	
	//依分類顯示該類別文章列表
	//（是否需要要能從subclassNo抓出subclassName？←不用，前端select的option可以處理）
	public Collection<ArticleVO> allSubArticle(String subclassNo){
		return dao.selectAll(subclassNo);
	}	
	
	//依文章名稱與發文帳號搜尋文章
	//我本來以為會送keyword去兩個DAO的方法看哪個有結果再傳回來給我，卻感覺是我想太多了
	public Collection<ArticleVO> searchArticle(String articleTitle){		
		return dao.selectByArticleTitle(articleTitle);
	}
	
	//增加文章
	public boolean addArticle(){
		boolean result=false;
		
		
		
		
		return result;
	}
	
	//修改文章
	public boolean modifyArticle(ArticleVO avo) {
		boolean temp = dao.update(avo);
		if (temp == true) {
			return true;
		} else {
			return false;
		}
	}

	//刪除文章
	public boolean deleteArticle(int memberId, int articleId) {
		
		dao.select(memberId);
		
		return dao.delete(articleId);	
	}	

	//測試程式
	public static void main(String[] args) {
		ArticleService service = new ArticleService();
//		System.out.println(service.allArticle());
//		System.out.println(service.allSubArticle("M"));
		System.out.println(service.searchArticle("a"));
//		System.out.println(service.addArticle());
//		System.out.println(service.deleteArticle(4, 10));
		
	}	
}
	
	

