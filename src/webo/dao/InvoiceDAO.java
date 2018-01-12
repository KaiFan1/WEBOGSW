package webo.dao;
import java.sql.*;

import webo.bean.InvoiceBean;
import webo.util.ConnectionManager;
public class InvoiceDAO {
	static Connection currentCon = null;
	static ResultSet rs = null;
	
	public static void CreateInvoice(InvoiceBean bean){
		Statement statement = null;
		String searchQuery = "INSERT INTO INVOICE (CUS_ID,SHIP_ADR_NUM,INV_QUANTITY,INV_TRANS,INV_TOTAL,BOOK_ISBN) VALUES ('"
		+ bean.getCustomerID() + "','" + bean.getShippingNum() + "','" + bean.getQuantity() + "','"
		+ bean.getTransID()+ "','" + bean.getPrice() + "','" + bean.getBookISBN() +"')";
		
		try {
			currentCon = ConnectionManager.getConnection();
			statement = currentCon.createStatement();
			statement.executeUpdate(searchQuery);
			System.out.println("<<the invoice has been created!");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}
	
	public static int QueryInvoiceID(int invoicid){
		Statement statement = null;
		String searchQuery = "SELECT INV_TRANS FROM INVOICE WHERE INV_TRANS ='"+invoicid+"'";
		int inv_id = 0;
		try {
			currentCon = ConnectionManager.getConnection();
			statement = currentCon.createStatement();
			rs=statement.executeQuery(searchQuery);
			if(rs.next()){
				inv_id = rs.getInt("INV_ID");
			}else {
				inv_id = 0;
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return inv_id;
	}
}
