package webo.dao;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

import webo.bean.UserBean;
import webo.util.ConnectionManager;

public class UserDAO {
	static Connection currentCon = null;
	static ResultSet rs = null;
	
	public static UserBean login(UserBean bean){
		Statement stmt = null;
		String username = bean.getUsername();
		String password = bean.getPassword();
		String searchQuery = "SELECT * FROM CUSTOMER where CUS_USERN ='" + username 
					+ "' AND CUS_PASS ='" + password + "'";
		try{
			//connect to Database
			currentCon = ConnectionManager.getConnection();
			stmt = currentCon.createStatement();
			rs = stmt.executeQuery(searchQuery);
			System.out.println("rs = " + rs);
			boolean more = rs.next();
			//if user doesn't exist set the isValid variable to false
			if(!more){
				System.out.println("Sorry, you are not a registered user!Please sign up first");
				bean.setValid(false);
			}
			//if user exists set the isValid variable to true
			else if(more){
				String firstName = rs.getString("CUS_FNAME");
				String lastName = rs.getString("CUS_LNAME");
				System.out.println("Weclome" + firstName);
				bean.setFirstName(firstName);
				bean.setLastName(lastName);
				bean.setId(rs.getInt("CUS_ID"));
				bean.setValid(true);
			}
		}
		
		catch(Exception ex){
			System.out.println("Log In failed:An Exception has occurred!" + ex);
		}
		
		//some exception handling
		finally{
			if(rs!=null){
				try{
					rs.close();
				}
				catch(Exception e){
					
				}
				rs = null;
			}
			
			if(stmt!=null){
				try{
					stmt.close();
				}
				catch(Exception e){
					
				}
				stmt=null;
			}
			
			if(currentCon!=null){
				try{
					currentCon.close();
				}
				catch(Exception e){
					
				}
				currentCon=null;
			}
		}
		return bean;
	}
	
	public static void Register(UserBean bean){
		Statement stmt = null;
		String searchQuery = "INSERT INTO CUSTOMER (CUS_ID, CUS_FNAME ,"
				+ "CUS_MNAME, CUS_LNAME , CUS_DOB , CUS_EMAIL , CUS_USERN ,"
				+ " CUS_PASS ) VALUES ('"
				+ bean.getId() + "','"+ bean.getFirstName() + "','" 
				+ bean.getMiddleName() + "','" + bean.getLastName() 
				+ "','" + bean.getBirthday() + "','" + bean.getEmail() 
				+ "','" + bean.getUsername()
				+ "','" + bean.getPassword() + "')";
		System.out.println(searchQuery);
		try{
			currentCon = ConnectionManager.getConnection();
			stmt = currentCon.createStatement();
			stmt.executeUpdate(searchQuery);
			System.out.println("success");
		}
		catch(Exception ex){
			System.out.println(ex.getMessage() + "error");
		}
		
	}
	public static UserBean QueryUserRecord(int userid){
		Statement statement = null;
		String searchQuery = "SELECT * FROM CUSTOMER WHERE CUS_ID ='"
				+ userid + "';";
		UserBean userBean = new UserBean();
		try{
			currentCon = ConnectionManager.getConnection();
			statement = currentCon.createStatement();
			rs = statement.executeQuery(searchQuery);
			if(rs.next()){
				userBean.setId(rs.getInt("CUS_ID"));
			}
			else {
				userBean = null;
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return userBean;
	}
	public static List<UserBean> RetrieveUsers(){
		Statement stmt = null;
		String searchQuery = "SELECT * FROM CUSTOMER";
		List<UserBean> list = new LinkedList<UserBean>();
		
		try{
			currentCon = ConnectionManager.getConnection();
			stmt = currentCon.createStatement();
			rs = stmt.executeQuery(searchQuery);
			while(rs.next()){
				UserBean bean = new UserBean();
				bean.setId(rs.getInt("CUS_ID"));
				bean.setFirstName(rs.getString("CUS_FNAME"));
				bean.setMiddleName(rs.getString("CUS_MNAME"));
				bean.setLastName(rs.getString("CUS_LNAME"));
				bean.setBirthday(rs.getString("CUS_DOB"));
				bean.setEmail(rs.getString("CUS_EMAIL"));
				bean.setUsername(rs.getString("CUS_USERN"));
				bean.setPassword(rs.getString("CUS_PASS"));
				list.add(bean);
			}
		
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		return list;
	}
	
	public static void Update(UserBean bean){
		Statement stmt = null;
		int id = bean.getId();
		//String password = bean.getPassword();
		String searchQuery = "UPDATE CUSTOMER SET CUS_ID ='" +
					bean.getId() +"',CUS_FNAME='" +
					bean.getFirstName() +"',CUS_MNAME='" +
					bean.getMiddleName() + "',CUS_LNAME='" + 
					bean.getLastName()+"',CUS_DOB= '" + 
					bean.getBirthday()+"',CUS_EMAIL='" +
					bean.getEmail()+"',CUS_USERN= '"+
					bean.getUsername()+"',CUS_PASS='" +
					bean.getPassword() +"' WHERE CUS_ID ='" + id +"'";
		try{
			currentCon = ConnectionManager.getConnection();
			stmt = currentCon.createStatement();
			stmt.executeUpdate(searchQuery);
			System.out.println("success");
		}
		catch(Exception ex){
			System.out.println(ex.getMessage() + "error");
		}
	}

    public static void Delete(String bean) {
    	Statement stmt = null;
		int id = Integer.valueOf(bean).intValue();
		//String password = bean.getPassword();
		String searchQuery="DELETE FROM CUSTOMER WHERE CUS_ID = '" + id +"';";
		try{
			currentCon = ConnectionManager.getConnection();
			stmt = currentCon.createStatement();
			stmt.executeUpdate(searchQuery);
			System.out.println("success");
		}
		catch(Exception ex){
			System.out.println(ex.getMessage() + "error");
		}
	}
}
