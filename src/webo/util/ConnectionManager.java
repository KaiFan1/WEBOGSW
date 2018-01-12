package webo.util;

import java.sql.*;

public class ConnectionManager {
	
	static Connection con;
	static String url;
	
	public static Connection getConnection(){
		try{
			 //Database located at my laptop
			
			String url = "jdbc:mysql://localhost:3306/mysql?autoReconnect=true&useSSL=false";
			String username="root";
			String password="123456";
			
			/*
			String url = "jdbc:mysql://dbms.gswcm.net:3306/Group 1?autoReconnect=true&useSSL=false";
			String username = "kfan";
			String password = "230863";
			*/
			Class.forName("com.mysql.jdbc.Driver");
			
			try{
				con = DriverManager.getConnection(url,username,password);
			}
			catch(SQLException ex){
				ex.printStackTrace();
			}
		}
		catch(ClassNotFoundException e){
			System.out.println(e);
		}
		return con;
	}
}
