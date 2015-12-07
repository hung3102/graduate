package data;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jdbc.ConnectionUtils;

public class School {

	public int id;
	public String name;
	public int communeID;
	public int districtID;
	public int provinceID;
	public Date created_time;
	public Date update_time;
	
	public List<School> getSchoolByCommuneID(int communeID) throws ClassNotFoundException, SQLException {
		Connection connection = ConnectionUtils.getMyConnection();
		Statement statement = connection.createStatement();
		
		String sql="select * from tbl_school where communeID=" + communeID;
		ResultSet rs = statement.executeQuery(sql);
		
		List<School> SchoolArray = new ArrayList<School>();
		while(rs.next()) {
			int id = rs.getInt(1);
			String name = rs.getString(2);
			int districtID = rs.getInt(4);
			int provinceID = rs.getInt(5);
			Date create_time = rs.getDate(6);
			Date update_time = rs.getDate(7);
			School sch = this.setSchool(id, name, communeID, districtID, provinceID, create_time, update_time);
			SchoolArray.add(sch);
		}
		
		return SchoolArray;
	}
	
	public School setSchool(int id, String name, int communeID, int districtID, int provinceID, Date created_time, Date update_time) {
		School sch = new School();
		sch.id = id;
		sch.name = name;
		sch.communeID = communeID;
		sch.districtID = districtID;
		sch.provinceID = provinceID;
		sch.created_time = created_time;
		sch.update_time = update_time;
		
		return sch;
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
//		School school = new School();
//		List<School> schoolArray = school.getSchoolByCommuneID(1);
//		for(School sch:schoolArray) {
//			System.out.println(sch.name);
//		}
	}

}
