package data;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import jdbc.ConnectionUtils;

public class Province {
	public int id;
	public String name;
	public Date created_time;
	public Date update_time;
	
	public List<Province> getProvince() throws ClassNotFoundException, SQLException {
		Connection connection = ConnectionUtils.getMyConnection();
		Statement statement = (Statement) connection.createStatement();
		
		String sql = "Select * from tbl_province";
		ResultSet rs = statement.executeQuery(sql);
		List<Province> provinceArray = new ArrayList<Province>();

		while(rs.next()) {
			int id = rs.getInt(1);
			String name = rs.getString(2);
			Date created_time = rs.getDate(3);
			Date update_time = rs.getDate(4);
			Province pr = this.setProvince(id, name, created_time, update_time);
			provinceArray.add(pr);
		}
		
		connection.close();
		return provinceArray;
	}
	
	public String[] getProvinceName() throws ClassNotFoundException, SQLException {
		Connection connection = ConnectionUtils.getMyConnection();
		Statement statement = (Statement) connection.createStatement();
		
		String sql = "Select * from tbl_province";
		ResultSet rs = statement.executeQuery(sql);
		String[] provinceNameArray = new String[100];
		int i = 0;
		while(rs.next()) {
			String provinceName = rs.getString(2);
			provinceNameArray[i++] = provinceName;
		}
		
		connection.close();
		return provinceNameArray;
		
	}
	
	public Province setProvince(int id, String name, Date created_time, Date update_time) {
		Province pr = new Province();
		pr.id = id;
		pr.name = name;
		pr.created_time = created_time;
		pr.update_time = update_time;
		
		return pr;
	}
	
	public Province seachProvinceByID(int id) throws ClassNotFoundException, SQLException {
		Connection connection = ConnectionUtils.getMyConnection();
		Statement statement = (Statement) connection.createStatement();
		
		String sql = "select * from tbl_province where id = " + id;
		ResultSet rs = statement.executeQuery(sql);
		Province pr = new Province();
		if(rs.next()) {
			String name = rs.getString(2);
			Date created_time = rs.getDate(3);
			Date update_time = rs.getDate(4);
			pr = this.setProvince(id, name, created_time, update_time);
		} else {
			pr = null;
		}
		
		return pr;
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
//		Province province = new Province();
//		List<Province> provinceArray = province.getProvince();
//		for(Province pr:provinceArray) {
//			System.out.println(pr.name);
//		}
	}
}
