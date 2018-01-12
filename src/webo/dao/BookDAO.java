package webo.dao;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

import webo.bean.BookBean;
import webo.util.ConnectionManager;

public class BookDAO {
	static Connection currentCon = null;
	static ResultSet rs = null;
	
	public static void NewBook(BookBean bean){
		Statement stmt = null;
		String searchQuery1 = "INSERT INTO BOOK_AUTHOR (BOOK_AUTHOR_FNAME,BOOK_AUTHOR_MNAME,BOOK_AUTHOR_LNAME) VALUES ('"
			+ bean.getBookAuthFName() +"','"+bean.getBookAuthMName() +"','" + bean.getBookAuthLName()+"')";
		String searchQuery2	= "INSERT INTO PUBLISHER (PUB_NAME) VALUES ('" + bean.getPublishName() +"')";
		String searchQuery3	= "INSERT INTO BOOK (BOOK_ISBN,BOOK_NAME,BOOK_YEAR,BOOK_PIC_REF,BOOK_STOCK,BOOK_CAT,BOOK_PRICE) VALUES ('"
				+ bean.getBookISBN() +"','"+bean.getBookName()+"','"+bean.getBookYear()+"','"+bean.getBookPicRef()+"','"
				+ bean.getBookStock() +"','"+bean.getBookCat()+"','"+bean.getBookPrice()+"')";
		
		try{
			currentCon = ConnectionManager.getConnection();
			stmt=currentCon.createStatement();
			stmt.executeUpdate(searchQuery1);
			stmt.executeUpdate(searchQuery2);
			stmt.executeUpdate(searchQuery3);
			System.out.println("New book has been imported");
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
	}
	
	public static List<BookBean> GetBookTable(){
		Statement statement = null;
		String searchQuery ="SELECT * FROM(BOOK INNER JOIN PUBLISHER ON BOOK.PUB_ID= PUBLISHER.PUB_ID) INNER JOIN BOOK_AUTHOR ON BOOK.BOOK_AUTHOR_NUM = BOOK_AUTHOR.BOOK_AUTHOR_NUM";
		List<BookBean> list = new LinkedList<BookBean>();
		
		try {
			currentCon = ConnectionManager.getConnection();
			statement = currentCon.createStatement();
			rs = statement.executeQuery(searchQuery);
			while (rs.next()) {
				BookBean bean = new BookBean();
				bean.setBookISBN(rs.getString("BOOK_ISBN"));
				bean.setBookName(rs.getString("BOOK_NAME"));
				bean.setBookAuthFName(rs.getString("BOOK_AUTHOR_FNAME"));
				bean.setBookAuthMName(rs.getString("BOOK_AUTHOR_MNAME"));
				bean.setBookAuthLName(rs.getString("BOOK_AUTHOR_LNAME"));
				bean.setBookYear(rs.getString("BOOK_YEAR"));
				bean.setPublishName(rs.getString("PUB_NAME"));
				bean.setBookStock(rs.getInt("BOOK_STOCK"));
				bean.setBookCat(rs.getString("BOOK_CAT"));
				bean.setBookPrice(rs.getDouble("BOOK_PRICE"));
				list.add(bean);
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return list;
	}
	
	public static void Delete(String bean) {
		Statement statement = null;
		int id = Integer.valueOf(bean).intValue();
		String searchQuery ="DELETE FROM BOOK WHERE BOOK_ISBN = '" + id +"';";
		try{
			currentCon = ConnectionManager.getConnection();
			statement = currentCon.createStatement();
			statement.executeUpdate(searchQuery);
			System.out.println(">>Book has been deleted sucessfully");
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
	}
	
	public static void UpdateForAdm(BookBean bean,String oldibsn){
		/*
		Statement statement =null;
		int pubid=0,authid=0;
		String searchQuery1 = "UPDATE BOOK SET BOOK_ISBN ='" +
		bean.getBookISBN() + "', BOOK_NAME = '" +
		bean.getBookName() + "', BOOK_YEAR = '" +
		bean.getBookYear() + "', BOOK_PIC_REF = '" +
		bean.getBookPicRef() + "', BOOK_STOCK = '" + 
		bean.getBookStock() + "', BOOK_CAT = '" + 
		bean.getBookCat() + "', BOOK_PRICE = '" + 
		bean.getBookPrice() + "' WHERE BOOK_ISBN = '" + oldibsn +"'";
		String searchPubID = "SELECT PUB_ID FROM BOOK WHERE BOOK_ISBN = '" + oldibsn +"'";
		String searchAuthID= "SELECT BOOK_AUTHOR_NUM FROM BOOK WHERE BOOK_ISBN = '" + authid +"'";
		String updatePublisher= "UPDATE PUBLISHER SET PUB_NAME = '"
		+ bean.getPublishName()+"' WHERE PUB_ID = '" + pubid + "'";
		String updateAuthor="UPDATE BOOK_AUTHOR SET BOOK_AUTHOR_FNAME='"
				+ bean.getBookAuthFName() + "', BOOK_AUTHOR_MNAME = '"
				+ bean.getBookAuthMName() + "', BOOK_AUTHOR_LNAME = '"
				+ bean.getBookAuthLName() + "'";
		try{
			currentCon = ConnectionManager.getConnection();
			statement = currentCon.createStatement();
			statement.executeUpdate(searchQuery1);
			rs = statement.executeQuery(searchPubID);
			pubid = rs.getInt("PUB_ID");
			statement.executeUpdate(updatePublisher);
			System.out.println("<<Update book sucessfully!");
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}*/
	}
	
	public static List<BookBean> GetBookForWeb(String type, String value){
		Statement statement = null;
		String searchQuery = null;
		if(type == "BOOK_CAT"){
			searchQuery ="select * from(BOOK inner join PUBLISHER on BOOK.PUB_ID= PUBLISHER.PUB_ID) "
					+ "INNER JOIN BOOK_AUTHOR ON BOOK.BOOK_AUTHOR_NUM = BOOK_AUTHOR.BOOK_AUTHOR_NUM  "
					+ "WHERE " + type + "='" + value + "'";
		}else if (type == "BOOK_NAME") {
			searchQuery ="SELECT * FROM BOOK WHERE " + "REPLACE (" + type + ",' ','')" + " LIKE '%" + value + "%'";
		}
		System.out.println(searchQuery);
		List<BookBean> list = new LinkedList<BookBean>();
		
		try {
			currentCon = ConnectionManager.getConnection();
			statement = currentCon.createStatement();
			rs = statement.executeQuery(searchQuery);
			while (rs.next()) {
				BookBean bean = new BookBean();
				bean.setBookISBN(rs.getString("BOOK_ISBN"));
				bean.setBookName(rs.getString("BOOK_NAME"));
				bean.setBookYear(rs.getString("BOOK_YEAR"));
				bean.setBookAuthName(rs.getString("BOOK_AUTHOR_FNAME"),rs.getString("BOOK_AUTHOR_MNAME"),rs.getString("BOOK_AUTHOR_LNAME"));
				bean.setPublishName(rs.getString("PUB_NAME"));
				bean.setBookPicRef(rs.getString("BOOK_PIC_REF"));
				bean.setBookStock(rs.getInt("BOOK_STOCK"));
				bean.setBookCat(rs.getString("BOOK_CAT"));
				bean.setBookPrice(rs.getDouble("BOOK_PRICE"));
				list.add(bean);
				System.out.println("the book is :" + bean.getBookName());
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return list;
	}
	
	public static BookBean QueryBookForWeb(String query){
		Statement statement = null;
		String searchQuery ="select * from(BOOK inner join PUBLISHER on BOOK.PUB_ID= PUBLISHER.PUB_ID) "
				+ "INNER JOIN BOOK_AUTHOR ON BOOK.BOOK_AUTHOR_NUM = BOOK_AUTHOR.BOOK_AUTHOR_NUM "
				+ "WHERE BOOK_ISBN ='" + query + "'";
		BookBean bean = new BookBean();
		System.out.println(searchQuery);
		try {
			currentCon = ConnectionManager.getConnection();
			statement = currentCon.createStatement();
			rs = statement.executeQuery(searchQuery);
			if(rs.next()){
			bean.setBookISBN(rs.getString("BOOK_ISBN"));
			bean.setBookName(rs.getString("BOOK_NAME"));				
			bean.setBookAuthName(rs.getString("BOOK_AUTHOR_FNAME"),rs.getString("BOOK_AUTHOR_MNAME"),rs.getString("BOOK_AUTHOR_LNAME"));
			bean.setPublishName(rs.getString("PUB_NAME"));
			bean.setBookYear(rs.getString("BOOK_YEAR"));
			bean.setBookPicRef(rs.getString("BOOK_PIC_REF"));
			bean.setBookStock(rs.getInt("BOOK_STOCK"));
			bean.setBookCat(rs.getString("BOOK_CAT"));
			bean.setBookPrice(rs.getDouble("BOOK_PRICE"));
			System.out.println(searchQuery);
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
		return bean;
	}	
	
	public static void UpdateBookStock(String isbn,int quantity){
		Statement statement = null;
		String searchQuery = "UPDATE BOOK SET BOOK_STOCK ='"+quantity+"' WHERE BOOK_ISBN = '" + isbn + "'";
		try {
			currentCon = ConnectionManager.getConnection();
			statement = currentCon.createStatement();
			statement.executeUpdate(searchQuery);
			System.out.println("<<Book_stock has been updated!");
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}
	
	public static double QueryBookPrice(String isbn){
		Statement statement = null;
		String searchQuery = "SELECT BOOK_PRICE FROM BOOK WHERE BOOK_ISBN = '" + isbn + "'";
		BookBean bean = new BookBean();
		try {
			currentCon = ConnectionManager.getConnection();
			statement = currentCon.createStatement();
			rs = statement.executeQuery(searchQuery);
			if(rs.next()){
				bean.setBookPrice(Double.parseDouble(rs.getString("BOOK_PRICE")));
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return bean.getBookPrice();
	}
}
