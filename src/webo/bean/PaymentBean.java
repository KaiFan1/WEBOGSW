package webo.bean;

public class PaymentBean {
	private int userID;
	private String paymentAddress;
	private int paymentZipCode;
	private String paymentApartment;
	private int paymentPhone;
	
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getPaymentAddress() {
		return paymentAddress;
	}
	public void setPaymentAddress(String paymentAddress) {
		this.paymentAddress = paymentAddress;
	}
	public int getPaymentZipCode() {
		return paymentZipCode;
	}
	public void setPaymentZipCode(int paymentZipCode) {
		this.paymentZipCode = paymentZipCode;
	}
	public String getPaymentApartment() {
		return paymentApartment;
	}
	public void setPaymentApartment(String paymentApartment) {
		this.paymentApartment = paymentApartment;
	}
	public int getPaymentPhone() {
		return paymentPhone;
	}
	public void setPaymentPhone(int paymentPhone) {
		this.paymentPhone = paymentPhone;
	}
	
	
}
