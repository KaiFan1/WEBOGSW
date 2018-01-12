package webo.bean;

public class CartBean {
	private int userid;
	private int bookQuantity;
	private String bookISBN;
	private String purchaseDate;
	private int purchaseFlag;
	private String bookName;
	private double bookPrice;
	private double subtotalPrice;
	
	public int getUserID(){
		return this.userid;
	}
	public void setUserID(int userid){
		this.userid=userid;
	}
	public int getBookQuantity(){
		return this.bookQuantity;
	}
	public void setBookQuantity(int quantity){
		this.bookQuantity=quantity;
	}
	public String getBookISBN() {
		return this.bookISBN;
	}
	public void setBookISBN(String bookisbn){
		this.bookISBN=bookisbn;
	}
	public String getPurchaseDate(){
		return this.purchaseDate;
	}
	public void setPurchaseDate(String purchasedate){
		this.purchaseDate=purchasedate;
	}
	public int getPurchaseFlag() {
		return this.purchaseFlag;
	}
	public void setPurchaseFlage(int purchaseflage){
		this.purchaseFlag=purchaseflage;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public double getBookPrice() {
		return bookPrice;
	}
	public void setBookPrice(double bookPrice) {
		this.bookPrice = bookPrice;
	}
	public double getSubtotalPrice() {
		return subtotalPrice;
	}
	public void setSubtotalPrice(double subtotalPrice) {
		this.subtotalPrice = subtotalPrice;
	}
	

}
