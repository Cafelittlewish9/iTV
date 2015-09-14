package model.service;

import java.util.Collection;
import model.dao.ArticleDAO;
import model.dao.jdbc.ArticleDAOjdbc;
import model.vo.ArticleVO;

/**
 * @author iTV小組成員
 *
 */
public class ArticleService {
	private ArticleDAO dao;

	/**
	 * 初始化ArticleDAO
	 */
	public ArticleService() {
		this.dao = new ArticleDAOjdbc();
	}

	/**
	 * 查詢所有文章
	 * 
	 * @return Collection<ArticleVO>
	 */
	public Collection<ArticleVO> allArticle() {
		return dao.selectAll();
	}

	/**
	 * 依照文章的類別來查詢文章
	 * 
	 * @param subclassNo
	 *            文章的類別代碼
	 * @return Collection<ArticleVO>
	 */
	public Collection<ArticleVO> allSubArticle(String subclassNo) {
		Collection<ArticleVO> list = null;
		return list;
	}

	/**
	 * 用單一關鍵字搜尋文章
	 * 
	 * @param keyword
	 *            關鍵字，可能是文章標題，帳號或暱稱
	 * @return Collection<ArticleVO>
	 */
	public Collection<ArticleVO> searchArticle(String keyword) {
		Collection<ArticleVO> list = null;
		return list;
	}

	/**
	 * 用多個關鍵字搜尋文章
	 * 
	 * @param keywords
	 *            關鍵字們，可能是文章標題和作者
	 * @return Collection<ArticleVO>
	 */
	public Collection<ArticleVO> searchArticle(String... keywords) {
		Collection<ArticleVO> list = null;
		return list;
	}

	/**
	 * 增加一篇文章
	 * 
	 * @param bean
	 *            必須包含 <b>memberId</b>、<b>subclassNoarticleTitle</b> 以及 <b>articleContent</b>
	 * @return true 新增成功; false 新增失敗
	 * @see #addArticle(int, String, String, String)
	 */
	public boolean addArticle(ArticleVO bean) {
		boolean result = false;
		if (bean != null) {
		}
		return result;
	}

	/**
	 * 增加一篇文章
	 * 
	 * @param memberId
	 *            會員ID
	 * @param subclassNo
	 *            文章的類別代碼
	 * @param articleTitle
	 *            文章的標題
	 * @param articleContent
	 *            文章的內容
	 * @return true 新增成功; false 新增失敗
	 * @see #addArticle(ArticleVO)
	 */
	public boolean addArticle(int memberId, String subclassNo, String articleTitle, String articleContent) {
		boolean result = false;
		return result;
	}

	/**
	 * 修改文章內容
	 * 
	 * @param articleContent
	 *            修改後的內容
	 * @param articleId
	 *            要修改的文章ID
	 * @return true 新增成功; false 新增失敗
	 */
	public boolean modifyArticle(String articleContent, int articleId) {
		boolean result = false;
		return result;
	}

	/**
	 * 修改文章內容
	 * 
	 * @param bean
	 *            必須包含 <b>articleContent</b> 與 <b>articleId</b>
	 * @return true 新增成功; false 新增失敗
	 * @see #modifyArticle(String, int)
	 */
	public boolean modifyArticle(ArticleVO bean) {
		boolean result = false;
		if (bean != null) {
		}
		return result;
	}

	/**
	 * 刪除文章
	 * 
	 * @param articleId
	 *            要刪除的文章ID
	 * @return true 新增成功; false 新增失敗
	 * @see #addArticle(ArticleVO)
	 */
	public boolean deleteArticle(int articleId) {
		boolean result = false;
		return result;
	}

	/**
	 * 刪除文章
	 * 
	 * @param bean
	 *            必須包含 <b>articleId</b>
	 * @return true 新增成功; false 新增失敗
	 * @see #deleteArticle(int)
	 */
	public boolean deleteArticle(ArticleVO bean) {
		boolean result = false;
		return result;
	}

	// 測試程式
	public static void main(String[] args) {
		ArticleService service = new ArticleService();
		// System.out.println(service.allArticle());
		// System.out.println(service.allSubArticle("M"));
		System.out.println(service.searchArticle("a"));
		// System.out.println(service.addArticle());
		// System.out.println(service.deleteArticle(4, 10));

	}
}
