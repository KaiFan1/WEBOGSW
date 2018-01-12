package webo.dao;

import java.sql.*;

import webo.bean.PaymentBean;
import webo.util.ConnectionManager;

public class PaymentDao {
	static Connection currentCon = null;
	static ResultSet rs = null;
	
	public static void createNewRecord(PaymentBean paymentBean) {
		Statement statement = null;
		String searchQuery = "INSERT INTO PAYMENT_ADDRESS (CUS_ID,PAY_ADR_STREET,PAY_ADR_APT,PAY_ADR_ZIP,PAY_ADR_PHONE) VALUES ('"
				+ paymentBean.getUserID() +"','" + paymentBean.getPaymentAddress() + "','"
				+ paymentBean.getPaymentApartment() + "','" + paymentBean.getPaymentZipCode() + "','"
				+ paymentBean.getPaymentPhone() + "');";
		try {
			System.out.println(searchQuery);
			currentCon = ConnectionManager.getConnection();
			statement=currentCon.createStatement();
			statement.executeUpdate(searchQuery);
			System.out.println("<<" + paymentBean.getUserID() + "'s payment record has been created!");
			currentCon.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}
}
