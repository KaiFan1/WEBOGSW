package webo.dao;

import java.sql.*;

import webo.bean.UserBean;
import webo.util.ConnectionManager;

public class UserInforDAO {
	static Connection currentCon = null;
	static ResultSet rs = null;
	
	public static UserBean[] userInfor(UserBean[] bean){
		Statement stmt = null;
		String searchQueryForUsers = "select * from CUSTOMER";
		
		try{
			currentCon = ConnectionManager.getConnection();
			stmt = currentCon.createStatement();
			rs = stmt.executeQuery(searchQueryForUsers);
		}
		catch(Exception ex){
			ex.getMessage();
		}
		return bean;
	}

}
