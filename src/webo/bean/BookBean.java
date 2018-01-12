package webo.bean;

public class BookBean {
	private String bookISBN;
	private String publishName;
	private String bookName;
	private String bookAuthFName;
	private String bookAuthMName;
	private String bookAuthLName;
	private String bookAuthName;
	private String bookYear;
	private int bookStock;
	private String bookPicRef;
	private String bookCat;
	private double bookPrice;
	private int bookAuthNum;
	private int publishNum;
	
	public String getBookISBN() {
		return this.bookISBN;
	}
	public void setBookISBN(String bookISBN){
		this.bookISBN=bookISBN;
	}
	public String getPublishName(){
		return this.publishName;
	}
	public void setPublishName(String publishname){
		this.publishName=publishname;
	}
	public String getBookName(){
		return this.bookName;
	}
	public void setBookName(String bookname){
		this.bookName=bookname;
	}
	public String getBookAuthFName(){
		return this.bookAuthFName;
	}
	public void setBookAuthFName(String bookAuthFName){
		this.bookAuthFName=bookAuthFName;
	}
	public String getBookAuthMName(){
		return this.bookAuthMName;
	}
	public void setBookAuthMName(String bookauthmname){
		this.bookAuthMName=bookauthmname;
	}
	public String getBookAuthLName(){
		return this.bookAuthLName;
	}
	public void setBookAuthLName(String bookauthlname){
		this.bookAuthLName=bookauthlname;
	}
	public String getBookYear(){
		return this.bookYear;
	}
	public void setBookYear(String bookyear){
		this.bookYear=bookyear;
	}
	public int getBookStock(){
		return this.bookStock;
	}
	public void setBookStock(int bookstock){
		this.bookStock=bookstock;
	}
	public String getBookPicRef(){
		return this.bookPicRef;
	}
	public void setBookPicRef(String bookpicref){
		this.bookPicRef=bookpicref;
	}
	public String getBookCat(){
		return this.bookCat;
	}
	public void setBookCat(String bookcat){
		this.bookCat=bookcat;
	}
	public double getBookPrice(){
		return this.bookPrice;
	}
	public void setBookPrice(double bookprice){
		this.bookPrice=bookprice;
	}
	public int getBookAuthNum(){
		return this.bookAuthNum;
	}
	public void setBookAuthNum(int bookauthnum){
		this.bookAuthNum=bookauthnum;
	}
	public int getPulisherNum(){
		return this.publishNum;
	}
	public void setPublishNum(int publishnum){
		this.publishNum = publishnum;
	}
	public String getBookAuthName(){
		return this.bookAuthName;
	}
	public void setBookAuthName(String fname,String mname,String lname){
		if(this.bookAuthMName != null){
			this.bookAuthName = fname + " " + mname + " " + lname;
		}
		this.bookAuthName = fname + " " + lname;
	}

	

}
