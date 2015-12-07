package data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jdbc.ConnectionUtils;

public class Student {

	public int id, nameID, gender, addressID, nativeID, ethnicID, religionID, fatherID, motherID;
	public String imageURL;
	public Date birthday, created_time, update_time;
	public String tutorName;
	
	public Student getStudentByID(int id) throws ClassNotFoundException, SQLException {
		Connection connection = ConnectionUtils.getMyConnection();
		Statement statement = connection.createStatement();
		
		String sql="select * from tbl_student where id=" + id;
		ResultSet rs = statement.executeQuery(sql);
		
		Student std = new Student();
		while(rs.next()) {
			int nameID = rs.getInt(2);
			String imageURL = rs.getString(3);
			int gender = rs.getInt(4); 
			Date birthday = rs.getDate(5);
			int addressID = rs.getInt(6);
			int nativeID = rs.getInt(7);
			int ethnicID = rs.getInt(8);
			int religionID = rs.getInt(9);
			int fatherID = rs.getInt(10);
			int motherID = rs.getInt(11);
			String tutorName = rs.getString(12);
			Date create_time = rs.getDate(13);
			Date update_time = rs.getDate(14);
			std = this.setStudent(id, nameID, imageURL, gender, birthday, addressID, nativeID, ethnicID, 
					religionID, fatherID, motherID, tutorName, create_time, update_time);
		}
		
		return std;
	}
	
	public Student setStudent(int id, int nameID, String imageURL, int gender, Date birthday, int addressID, 
			int nativeID, int ethnicID, int religionID, int fatherID, int motherID, String tutorName, 
			Date created_time, Date update_time) {
		Student std = new Student();
		std.id = id;
		std.nameID = nameID;
		std.imageURL = imageURL;
		std.gender = gender;
		std.birthday = birthday;
		std.addressID = addressID;
		std.nativeID = nativeID;
		std.ethnicID = ethnicID;
		std.religionID = religionID;
		std.fatherID = fatherID;
		std.motherID = motherID;
		std.tutorName = tutorName;
		std.created_time = created_time;
		std.update_time = update_time;
		
		return std;
	}
	
	public List<Student> getStudentByName(String name) throws ClassNotFoundException, SQLException {
		Connection connection = ConnectionUtils.getMyConnection();
		Statement statement = connection.createStatement();
		String sql = "select * from tbl_student where nameID IN (select id from tbl_name "
				+ "where LOWER(fullName) = '"+name.toLowerCase()+"')";
		ResultSet rs = statement.executeQuery(sql);
		
		List<Student> studentArray = new ArrayList<Student>();
		while(rs.next()) {
			Student st = new Student();
			st.setVariables(rs);
			studentArray.add(st);
		}
		connection.close();
		return studentArray;
	}
	
	private String sql;
	private int flag;
	public List<Student> search(int id, int nameID, int gender, int addressID, int nativeID, int ethnicID, 
			int religionID, Date birthday, int schoolID) throws ClassNotFoundException, SQLException {
		Connection connection = ConnectionUtils.getMyConnection();
		Statement statement = connection.createStatement();
		
		this.sql = "select * from tbl_student tbl1 where ";
		this.flag = 0;
		this.checkIntSearch("id", id);
		this.checkIntSearch("nameID", nameID);
		this.checkIntSearch("gender", gender);
		this.checkIntSearch("addressID", addressID);
		this.checkIntSearch("nativeID", nativeID);
		this.checkIntSearch("ethnicID", ethnicID);
		this.checkIntSearch("religionID", religionID);
		if(birthday != null) {
			String birthdayString = new SimpleDateFormat("yyyy-MM-dd").format(birthday);
//			JOptionPane.showMessageDialog(new JFrame(), birthdayString);
			if(this.flag == 1) {
				sql += " AND DATE(birthday) = '" + birthdayString + "'";
			} else {
				sql += " DATE(birthday) = '" + birthdayString + "'";
			}
			this.flag = 1;
		}
		if(schoolID > 0) {
			if(this.flag == 1) {
				sql += " AND (tbl1.id IN (select tbl2.studentID from tbl_relation_school_student tbl2 where schoolID = " 
						+ schoolID + "))";
			} else {
				sql += " (tbl1.id IN (select tbl2.studentID from tbl_relation_school_student tbl2 where schoolID = " 
						+ schoolID + "))";
			}
			this.flag = 1;
		}
		
		if(this.flag == 1) {
			ResultSet rs = statement.executeQuery(sql);
			List<Student> studentArray = new ArrayList<Student>();
			while(rs.next()) {
				Student std = new Student();
				std.setVariables(rs);
				studentArray.add(std);
			}
			connection.close();
			return studentArray;
		} else {
			connection.close();
			return null;
		}
	}
	
	private void checkIntSearch(String varName, int var) {
		if(var > 0) {
			if(this.flag == 1) {
				this.sql += " AND " + varName + " = " + var;
			} else {
				this.sql += varName + " = " + var;
			}
			this.flag = 1;
		}
	}
	
	private void setVariables(ResultSet rs) throws SQLException {
		this.id = rs.getInt(1);
		this.nameID = rs.getInt(2);
		this.imageURL = rs.getString(3);
		this.gender = rs.getInt(4);
		this.birthday = rs.getDate(5);
		this.addressID = rs.getInt(6);
		this.nativeID = rs.getInt(7);
		this.ethnicID = rs.getInt(8);
		this.religionID = rs.getInt(9);
		this.fatherID = rs.getInt(10);
		this.motherID = rs.getInt(11);
		this.tutorName = rs.getString(12);
		this.created_time = rs.getDate(13);
		this.update_time = rs.getDate(14);
	}
	public static void main(String[] args) throws ClassNotFoundException, SQLException, ParseException {
//		Student student = new Student();
//		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
//		Date birthday = formatter.parse("1993-07-02");
//		List<Student> studentArray = student.search(-1, -1, -1, -1, -1, -1, -1, null, 1);
//		for(Student std:studentArray) {
//			System.out.println(std.nameID);
//		}
	}

}
