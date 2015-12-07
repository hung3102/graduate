package data;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jdbc.ConnectionUtils;

public class Name {

	public int id;
	public String familyName, middleName, lastName, fullName;
	public Date created_time, update_time;	
	
	public Name searchNameByID(int id) throws ClassNotFoundException, SQLException {
		Connection connection = ConnectionUtils.getMyConnection();
		Statement statement = connection.createStatement();
		
		String sql="select * from tbl_name where id=" + id;
		ResultSet rs = statement.executeQuery(sql);
		
		Name n = new Name();
		if(rs.next()) {
			String familyName = rs.getString(2);
			String middleName = rs.getString(3);
			String lastName = rs.getString(4);
			String fullName = rs.getString(5);
			Date created_time = rs.getDate(6);
			Date update_time = rs.getDate(7);
			
			n = this.setName(id, familyName, middleName, lastName, fullName, created_time, update_time);
		} else {
			n = null;
		}
		
		return n;
	}
	
	public Name setName(int id, String familyName, String middleName, String lastName, String fullName,
			Date created_time, Date update_time) {
		Name n = new Name();
		n.id = id;	
		n.familyName = familyName;
		n.middleName = middleName;
		n.lastName = lastName;
		n.fullName = fullName;
		n.created_time = created_time;
		n.update_time = update_time;
		
		return n;
	}
	
	public List<Name> search(int id, String fullName) throws ClassNotFoundException, SQLException {
		Connection connection = ConnectionUtils.getMyConnection();
		Statement statement = connection.createStatement();
		
		String sql = "select * from tbl_name where ";
		int flag = 0;
		if(id > 0) {
			flag = 1;
			sql += "id = " + id;
		}
		if(fullName != null) {
			if(flag == 1) {
				sql += " AND LOWER(fullName) = '" + fullName.toLowerCase() + "'";
			} else {
				sql += "LOWER(fullName) = '" + fullName.toLowerCase() + "'";
			}
			flag = 1;
		}
		
		if(flag == 1) {
			ResultSet rs = statement.executeQuery(sql);
			List<Name> nameArray = new ArrayList<Name>();
			while(rs.next()) {
				Name n = new Name();
				n.setVariables(rs);
				nameArray.add(n);
			}
			connection.close();
			return nameArray;
		} else {
			connection.close();
			return null;
		}
	}
	
	private void setVariables(ResultSet rs) throws SQLException {
		this.id = rs.getInt(1);
		this.familyName = rs.getString(2);
		this.middleName = rs.getString(3);
		this.lastName = rs.getString(4);
		this.fullName = rs.getString(5);
		this.created_time = rs.getDate(6);
		this.update_time = rs.getDate(7);
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
//		Name name = new Name();
//		List<Name> nameArray = name.search(-1, "đào Văn Hùng");
//		for(Name n:nameArray) {
//			System.out.println(n.fullName);
//		}
	}

}
