package model.vo;

public class ArticleClassVO {
	private String subclassNo;
	private String subclassName;
	private String className;
	
	@Override
	public String toString() {
		return subclassNo + ": " + subclassName + " (" + className + ")";
	}
	public String getSubclassNo() {
		return subclassNo;
	}
	public void setSubclassNo(String subclassNo) {
		this.subclassNo = subclassNo;
	}
	public String getSubclassName() {
		return subclassName;
	}
	public void setSubclassName(String subclassName) {
		this.subclassName = subclassName;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
}
