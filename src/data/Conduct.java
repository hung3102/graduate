package data;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jdbc.ConnectionUtils;

public class Conduct {
	public int id, evaluationID, termID;
	public String content;
	public Date created_time, update_time;
	
	public Conduct searchConductByID(int id) throws ClassNotFoundException, SQLException {
		Connection connection = ConnectionUtils.getMyConnection();
		Statement statement = connection.createStatement();
		
		String sql="select * from tbl_conduct where id=" + id;
		ResultSet rs = statement.executeQuery(sql);
		
		Conduct con = new Conduct();
		if(rs.next()) {
			int evaluationID = rs.getInt(2);
			int termID = rs.getInt(3);
			String content = rs.getString(4);
			Date created_time = rs.getDate(5);
			Date update_time = rs.getDate(6);
			con = this.setConduct(id, evaluationID, termID, content, created_time, update_time);
		} else {
			con = null;
		}
		
		return con;
	}
	
	public Conduct setConduct(int id, int evaluationID, int termID, String content, Date created_time, 
			Date update_time) {
		Conduct con = new Conduct();
		con.id = id;	
		con.evaluationID = evaluationID;
		con.termID = termID;
		con.content = content;
		con.created_time = created_time;
		con.update_time = update_time;
		
		return con;
	}
	
	private int flag;
	private String sql;
	public List<Conduct> search(int evaluationID, int termID, String content) 
			throws ClassNotFoundException, SQLException {
		Connection connection = ConnectionUtils.getMyConnection();
		Statement statement = connection.createStatement();
		
		this.sql = "select * from tbl_conduct where ";
		this.flag = 0;
		
		this.checkIntSearch("evaluationID", evaluationID);
		this.checkIntSearch("termID", termID);
		this.checkStringSearch("content", content);
		
		if(flag == 1) {
			ResultSet rs = statement.executeQuery(this.sql);
			List<Conduct> conductArray = new ArrayList<Conduct>();
			while(rs.next()) {
				Conduct con = new Conduct();
				con = setConduct(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4),
						rs.getDate(6), rs.getDate(7));
				conductArray.add(con);
			}
			connection.close();
			return conductArray;
		} else {
			connection.close();
			return null;
		}
	}
	
	private void checkStringSearch(String varName, String value) {
		if(value != null) {
			if(flag == 1) {
				this.sql += " AND LOWER(" + varName + ") = '" + value.toLowerCase() + "'";
			} else {
				this.sql += "LOWER(" + varName + ") = '" + value.toLowerCase() + "'";
			}
			this.flag = 1;
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
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Conduct conduct = new Conduct();
		List<Conduct> conductArray = conduct.search(1, 1, null);
		for(Conduct con:conductArray) {
			System.out.println(con.content);
		}
	}
}
