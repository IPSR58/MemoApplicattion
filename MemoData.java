package controller;

public class MemoData {
	
	private String id;
	private String title;
	private String content;
	
	MemoData(String id, String title, String content){
		this.id = id;
		this.title = title;
		this.content = content;
	}//constractor
	
	public String getId() {
		return this.id;
	}//getId
	
	public String getTitle() {
		return this.title;
	}//getTitle

	public String getContent() {
		return this.content;
	}//getContent
}
