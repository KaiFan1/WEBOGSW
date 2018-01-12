package webo.bean;

public class ShipBean {
	private int userID;
	private String shippingAddress;
	private int shippingZipCode;
	private String shippingApartment;
	private int shippingNum;
	
	public int getUserID(){
		return this.userID;
	}
	public void setUserID(int userid){
		this.userID=userid;
	}
	public String getShippingAddress(){
		return this.shippingAddress;
	}
	public void setShippingAddress(String shippingaddress){
		this.shippingAddress=shippingaddress;
	}
	public int getShippingZipCode(){
		return this.shippingZipCode;
	}
	public void setShippingZipCode(int szp){
		this.shippingZipCode=szp;
	}
	public String getShippingApartment(){
		return this.shippingApartment;
	}
	public void setShippingApartment(String shippingApt){
		this.shippingApartment=shippingApt;
	}
	public int getShippingNum(){
		return this.shippingNum;
	}
	public void setShippingNum(int shippingnum){
		this.shippingNum = shippingnum;
	}
}
