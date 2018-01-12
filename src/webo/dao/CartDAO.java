package webo.dao;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

import webo.bean.CartBean;
import webo.util.ConnectionManager;

public class CartDAO {
	static Connection currentCon = null;
	static ResultSet rs = null;
	
	public static void CreateRecord(CartBean bean){
		Statement statement = null;
		String searchQuery = "INSERT INTO CART (CUS_ID, BOOK_ISBN,BOOK_QUANTITY, PURCHASE_FLAG ) VALUES ('" 
				+ bean.getUserID()+"','" + bean.getBookISBN() + "','" + bean.getBookQuantity() +"','" +bean.getPurchaseFlag()+"')";
		try {
			System.out.println(searchQuery);
			currentCon = ConnectionManager.getConnection();
			statement=currentCon.createStatement();
			statement.executeUpdate(searchQuery);
			System.out.println("<<" + bean.getUserID() + "'s record has been created!");
			currentCon.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}
	
	public static List<CartBean> GetRecord(int userid,int purchaseflag){
		Statement statement = null;
		String searchQuery = "SELECT * FROM CART WHERE CUS_ID= '" + userid + "' AND PURCHASE_FLAG = '"
				+ purchaseflag +"'";
		List<CartBean> list = new LinkedList<CartBean>();
		try{
			currentCon = ConnectionManager.getConnection();
			statement = currentCon.createStatement();
			rs = statement.executeQuery(searchQuery);
			while (rs.next()) {
				CartBean bean = new CartBean();
				bean.setBookISBN(rs.getString("BOOK_ISBN"));
				bean.setUserID(rs.getInt("CUS_ID"));
				bean.setBookQuantity(rs.getInt("BOOK_QUANTITY"));
				list.add(bean);
			}
			System.out.println(list.size());
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return list;
	}
	
	public static void UpdateRecord(CartBean bean){
		Statement statement = null;
		String searchQuery = "UPDATE CART SET BOOK_QUANTITY = '" 
				+ bean.getBookQuantity() +"' WHERE BOOK_ISBN = '" 
				+ bean.getBookISBN() + "'";
		try {
			currentCon = ConnectionManager.getConnection();
			statement = currentCon.createStatement();
			statement.executeUpdate(searchQuery);
			System.out.println("<<Update book sucessfully!");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}
	
	public static int QueryRecord(CartBean bean){
		int quantity = 0;
		Statement statement = null;
		String searchQuery = "SELECT * FROM CART "
				+ "WHERE CUS_ID ='" + bean.getUserID()+"'AND BOOK_ISBN = '"
				+ bean.getBookISBN() +"'AND PURCHASE_FLAG = '"
				+ bean.getPurchaseFlag()+"'";
		try {
			currentCon = ConnectionManager.getConnection();
			statement = currentCon.createStatement();
			rs = statement.executeQuery(searchQuery);
			System.out.println(searchQuery);
			if (rs.next()) {
				quantity = rs.getInt("BOOK_QUANTITY");
			}	
			else {
				quantity = 0 ;
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return quantity;
	}
	
	public static void DeleteRecord(CartBean bean){
		Statement statement = null;
		String searchQuery = "DELETE FROM CART WHERE BOOK_ISBN = '" 
				+ bean.getBookISBN() + "' AND CUS_ID = '"
				+ bean.getUserID() + "' AND PURCHASE_FLAG ='" 
				+ bean.getPurchaseFlag() +"'";
		System.out.println(searchQuery);
		try {
			currentCon = ConnectionManager.getConnection();
			statement = currentCon.createStatement();
			statement.executeUpdate(searchQuery);
			System.out.println("<<Your record has been deleted.");
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}
	
	public static List<CartBean> QueryInvoiceInfor(int userid,int purchaseflag){
		Statement statement = null;
		String searchQuery = "SELECT BOOK.BOOK_PRICE,CART.BOOK_QUANTITY,CART.BOOK_ISBN,BOOK.BOOK_NAME FROM CART"
				+ " INNER JOIN BOOK ON CART.BOOK_ISBN = BOOK.BOOK_ISBN WHERE CART.CUS_ID ='" 
				+ userid + "'";
		List<CartBean> list = new LinkedList<CartBean>();
		try{
			currentCon = ConnectionManager.getConnection();
			statement = currentCon.createStatement();
			rs = statement.executeQuery(searchQuery);
			while (rs.next()) {
				CartBean bean = new CartBean();
				bean.setBookISBN(rs.getString("BOOK_ISBN"));
				bean.setBookName(rs.getString("BOOK_NAME"));
				bean.setBookPrice(rs.getDouble("BOOK_PRICE"));
				bean.setBookQuantity(rs.getInt("BOOK_QUANTITY"));
				bean.setUserID(userid);
				bean.setSubtotalPrice(rs.getDouble("BOOK_PRICE")*rs.getInt("BOOK_QUANTITY"));
				list.add(bean);
			}
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return list;
	}
}
