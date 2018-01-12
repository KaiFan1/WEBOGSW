package webo.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import webo.bean.ShipBean;
import webo.util.ConnectionManager;

public class ShipDAO {
	static Connection currentCon = null;
	static ResultSet rs = null;
	
	public static int CreateNewShip(ShipBean shipBean){
		Statement stmt = null;
		int num = -1;
		String searchQuery = "INSERT INTO SHIPPING_ADDRESS (CUS_ID,SHIP_ADR_STREET,SHIP_ADR_APT,SHIP_ADR_ZIP) VALUES ('"
				+ shipBean.getUserID() +"','"+shipBean.getShippingAddress()+"','"+shipBean.getShippingApartment()+"','"+shipBean.getShippingZipCode()+"')";
		String getShipNum = "SELECT SHIP_ADR_NUM FROM SHIPPING_ADDRESS WHERE CUS_ID ='"
				+ shipBean.getUserID() + "' AND SHIP_ADR_STREET = '" +shipBean.getShippingAddress() + "' AND SHIP_ADR_APT ='"
				+ shipBean.getShippingApartment() + "' AND SHIP_ADR_ZIP ='" +shipBean.getShippingZipCode()+"'";
		try {
			currentCon = ConnectionManager.getConnection();
			stmt=currentCon.createStatement();
			stmt.executeUpdate(searchQuery);
			rs = stmt.executeQuery(getShipNum);
			while (rs.next()) {
				num = rs.getInt("SHIP_ADR_NUM");
			}
			System.out.println("<<New ship infor has been added");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return num;
	}
	
	public static ShipBean QueryShipInfor(int shipnum){
		Statement statement = null;
		String searchQuery = "SELECT * FROM SHIPPING_ADDRESS WHERE SHIP_ADR_NUM ='"+ shipnum +"'";
		ShipBean shipBean = new ShipBean();
		try {
			currentCon = ConnectionManager.getConnection();
			statement = currentCon.createStatement();
			rs = statement.executeQuery(searchQuery);
			if(rs.next()){
				shipBean.setShippingAddress(rs.getString("SHIP_ADR_STREET"));
				shipBean.setShippingApartment(rs.getString("SHIP_ADR_APT"));
				shipBean.setShippingZipCode(rs.getInt("SHIP_ADR_ZIP"));
				shipBean.setShippingNum(rs.getInt("SHIP_ADR_NUM"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return shipBean;
	}
}
