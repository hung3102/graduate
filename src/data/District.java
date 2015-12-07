package data;

import jdbc.ConnectionUtils;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class District {

	public int id;
	public String name;
	public int provinceID;
	public Date created_time;
	public Date update_time;
	
	public List<District> getDistrictByProvinceID(int provinceID) throws ClassNotFoundException, SQLException {
		Connection connection = ConnectionUtils.getMyConnection();
		Statement statement = connection.createStatement();
		
		String sql="select * from tbl_district where provinceID=" + provinceID;
		ResultSet rs = statement.executeQuery(sql);
		
		List<District> districtArray = new ArrayList<District>();
		while(rs.next()) {
			int id = rs.getInt(1);
			String name = rs.getString(2);
			Date create_time = rs.getDate(4);
			Date update_time = rs.getDate(5);
			District ds = this.setDistrict(id, name, provinceID, create_time, update_time);
			districtArray.add(ds);
		}
		
		return districtArray;
	}
	
	public List<District> search(String name, int provinceID) throws ClassNotFoundException, SQLException {
		Connection connection = ConnectionUtils.getMyConnection();
		Statement statement = connection.createStatement();
		
		String sql = "select * from tbl_district where ";
		int flag = 0;
		if(provinceID > 0) {
			flag = 1;
			sql += "provinceID = " + provinceID;
		}
		
		if(name != null) {
			if(flag == 1) {
				sql += " AND LOWER(name) = '" + name.toLowerCase() + "'";
			} else {
				sql += "LOWER(name) = '" + name.toLowerCase() + "'";
			}
			flag = 1;
		}
		
		
		if(flag == 1) {
			ResultSet rs = statement.executeQuery(sql);
			List<District> districtArray = new ArrayList<District>();
			while(rs.next()) {
				District d = new District();
				int id = rs.getInt(1);
				String districtName = rs.getString(2);
				Date created_time = rs.getDate(4);
				Date update_time = rs.getDate(5);
				d.setDistrict(id, districtName, provinceID, created_time, update_time);
				districtArray.add(d);
			}
			connection.close();
			return districtArray;
		} else {
			connection.close();
			return null;
		}
	}
	
	public District setDistrict(int id, String name, int provinceID, Date created_time, Date update_time) {
		District ds = new District();
		ds.id = id;
		ds.name = name;
		ds.provinceID = provinceID;
		ds.created_time = created_time;
		ds.update_time = update_time;
		
		return ds;
	}
	
	public District seachDistrictByID(int id) throws ClassNotFoundException, SQLException {
		Connection connection = ConnectionUtils.getMyConnection();
		Statement statement = connection.createStatement();
		
		String sql = "select * from tbl_district where id = " + id;
		ResultSet rs = statement.executeQuery(sql);
		District d = new District();
		if(rs.next()) {
			String name = rs.getString(2);
			int provinceID = rs.getInt(3);
			Date created_time = rs.getDate(4);
			Date update_time = rs.getDate(5);
			d = this.setDistrict(id, name, provinceID, created_time, update_time);
		} else {
			d = null;
		}
		
		return d;
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
//		District district = new District();
//		List<District> districtArray = district.getDistrictByProvinceID(1);
//		for(District ds:districtArray) {
//			System.out.println(ds.name);
//		}
	}

}
