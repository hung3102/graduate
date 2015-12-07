package data;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jdbc.ConnectionUtils;

public class Address {

	public int id, communeID, districtID, provinceID;
	public String houseNumber, street, hamlet;
	public Date created_time, update_time;	
	
	public Address getAddressByID(int id) throws ClassNotFoundException, SQLException {
		Connection connection = ConnectionUtils.getMyConnection();
		Statement statement = connection.createStatement();
		
		String sql="select * from tbl_address where id=" + id;
		ResultSet rs = statement.executeQuery(sql);
		
		Address adr = new Address();
		while(rs.next()) {
			String houseNumber = rs.getString(2);
			String street = rs.getString(3);
			String hamlet = rs.getString(4);
			int communeID = rs.getInt(5);
			int districtID = rs.getInt(6);
			int provinceID = rs.getInt(7);
			Date created_time = rs.getDate(8);
			Date update_time = rs.getDate(9);
			adr = this.setAddress(id, hamlet, communeID, districtID, provinceID, houseNumber, street, created_time, 
					update_time);
		}
		
		return adr;
	}
	
	public Address setAddress(int id, String hamlet, int communeID, int districtID, int provinceID, String houseNumber,
			String street, Date created_time, Date update_time) {
		Address adr = new Address();
		adr.id = id;	
		adr.hamlet = hamlet;
		adr.communeID = communeID;
		adr.districtID = districtID;
		adr.provinceID = provinceID;
		adr.houseNumber = houseNumber;
		adr.street = street;
		adr.created_time = created_time;
		adr.update_time = update_time;
		
		return adr;
	}
	
	public List<Address> search(String hamlet, int communeID, int districtID, int provinceID, 
			String houseNumber, String street) throws ClassNotFoundException, SQLException {
		Connection connection = ConnectionUtils.getMyConnection();
		Statement statement = connection.createStatement();
		
		String sql = "select * from tbl_address where ";
		int flag = 0;
		if(provinceID > 0) {
			flag = 1;
			sql += "provinceID = " + provinceID;
		}
		if(districtID > 0) {
			if(flag == 1) {
				sql += " AND districtID = " + districtID;
			} else {
				sql += " districtID = " + districtID;
			}
			flag = 1;
		}
		if(communeID > 0) {
			if(flag == 1) {
				sql += " AND communeID = " + communeID;
			} else {
				sql += "communeID = " + communeID;
			}
			flag = 1;
		}
		if(hamlet != null) {
			if(flag == 1) {
				sql += " AND LOWER(hamlet) = '" + hamlet.toLowerCase() + "'";
			} else {
				sql += "LOWER(hamlet) = '" + hamlet.toLowerCase() + "'";
			}
			flag = 1;
		}
		if(houseNumber != null) {
			if(flag == 1) {
				sql += " AND LOWER(houseNumber) = '" + houseNumber.toLowerCase() + "'";
			} else {
				sql += "LOWER(houseNumber) = '" + houseNumber.toLowerCase() + "'";
			}
			flag = 1;
		}
		if(street != null) {
			if(flag == 1) {
				sql += " AND LOWER(street) = '" + street.toLowerCase() + "'";
			} else {
				sql += "LOWER(street) = '" + street.toLowerCase() + "'";
			}
			flag = 1;
		}
		
		if(flag == 1) {
			ResultSet rs = statement.executeQuery(sql);
			List<Address> addressArray = new ArrayList<Address>();
			while(rs.next()) {
				Address adr = new Address();
				adr.setVariables(rs);
				addressArray.add(adr);
			}
			connection.close();
			return addressArray;
		} else {
			connection.close();
			return null;
		}
	}
	
	public Address seachAddressByID(int id) throws ClassNotFoundException, SQLException {
		Connection connection = ConnectionUtils.getMyConnection();
		Statement statement = connection.createStatement();
		
		String sql = "select * from tbl_address where id = " + id;
		ResultSet rs = statement.executeQuery(sql);
		Address adr = new Address();
		if(rs.next()) {
			String houseNumber = rs.getString(2);
			String street = rs.getString(3);
			String hamlet = rs.getString(4);
			int communeID = rs.getInt(5);
			int districtID = rs.getInt(6);
			int provinceID = rs.getInt(7);
			Date created_time = rs.getDate(8);
			Date update_time = rs.getDate(9);
			adr = this.setAddress(id, hamlet, communeID, districtID, provinceID, houseNumber, street, created_time, 
					update_time);
		} else {
			adr = null;
		}
		
		return adr;
	}
	
	private void setVariables(ResultSet rs) throws SQLException {
		this.id = rs.getInt(1);
		this.houseNumber = rs.getString(2);
		this.street = rs.getString(3);
		this.hamlet = rs.getString(4);
		this.communeID = rs.getInt(5);
		this.districtID = rs.getInt(6);
		this.provinceID = rs.getInt(7);
		this.created_time = rs.getDate(8);
		this.update_time = rs.getDate(9);
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
//		Address Address = new Address();
//		List<Address> AddressArray = Address.getAddressByName("đào Văn Hùng");
//		for(Address adr:AddressArray) {
//			System.out.println(adr.nameID);
//		}
	}

}
