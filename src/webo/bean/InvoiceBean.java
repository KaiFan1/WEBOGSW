package webo.bean;

public class InvoiceBean {
	private int invoiceNum;
	private int customerId;
	private int shippingNum;
	private int quantity;
	private double price;
	private String bookISBN;
	private int transID;
	
	public int getInvoiceNum(){
		return this.invoiceNum;
	}
	public void setInvoiceNum(int invoicenum){
		this.invoiceNum=invoicenum;
	}
	public int getCustomerID(){
		return this.customerId;
	}
	public void setCustomerID(int customerid){
		this.customerId=customerid;
	}
	public int getShippingNum(){
		return this.shippingNum;
	}
	public void setShippingNUM(int shippingnum){
		this.shippingNum=shippingnum;
	}
	public int getQuantity(){
		return this.quantity;
	}
	public void setQuantity(int quantity){
		this.quantity=quantity;
	}
	public double getPrice(){
		return this.price;
	}
	public void setPrice(double price){
		this.price=price;
	}
	public String getBookISBN() {
		return bookISBN;
	}
	public void setBookISBN(String bookISBN) {
		this.bookISBN = bookISBN;
	}
	public int getTransID() {
		return transID;
	}
	public void setTransID(int transID) {
		this.transID = transID;
	}

	
}
