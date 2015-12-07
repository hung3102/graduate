package data;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jdbc.ConnectionUtils;

public class Commune {

	public int id;
	public String name;
	public int districtID;
	public int provinceID;
	public Date created_time;
	public Date update_time;
	
	public List<Commune> getCommune(int districtID) throws ClassNotFoundException, SQLException {
		Connection connection = ConnectionUtils.getMyConnection();
		Statement statement = connection.createStatement();
		
		String sql="select * from tbl_commune where districtID=" + districtID;
		ResultSet rs = statement.executeQuery(sql);
		
		List<Commune> communeArray = new ArrayList<Commune>();
		while(rs.next()) {
			int id = rs.getInt(1);
			String name = rs.getString(2);
			int provinceID = rs.getInt(4);
			Date create_time = rs.getDate(5);
			Date update_time = rs.getDate(6);
			Commune cm = this.setCommune(id, name, districtID, provinceID, create_time, update_time);
			communeArray.add(cm);
		}
		
		return communeArray;
	}
	
	public Commune setCommune(int id, String name, int districtID, int provinceID, Date created_time, Date update_time) {
		Commune cm = new Commune();
		cm.id = id;
		cm.name = name;
		cm.districtID = districtID;
		cm.provinceID = provinceID;
		cm.created_time = created_time;
		cm.update_time = update_time;
		
		return cm;
	}
	
	public Commune seachCommuneByID(int id) throws ClassNotFoundException, SQLException {
		Connection connection = ConnectionUtils.getMyConnection();
		Statement statement = connection.createStatement();
		
		String sql = "select * from tbl_commune where id = " + id;
		ResultSet rs = statement.executeQuery(sql);
		Commune com = new Commune();
		if(rs.next()) {
			String name = rs.getString(2);
			int districtID = rs.getInt(3);
			int provinceID = rs.getInt(4);
			Date created_time = rs.getDate(5);
			Date update_time = rs.getDate(5);
			com = this.setCommune(id, name, districtID, provinceID, created_time, update_time);
		} else {
			com = null;
		}
		
		return com;
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
//		Commune commune = new Commune();
//		List<Commune> communeArray = commune.getCommune(1);
//		for(Commune cm:communeArray) {
//			System.out.println(cm.name);
//		}
	}

}
