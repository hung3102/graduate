package data;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jdbc.ConnectionUtils;

public class Achievement {
	public int id, evaluationID;
	public String name, level, sort;
	public Date created_time, update_time;
	
	public Achievement searchAchievementByID(int id) throws ClassNotFoundException, SQLException {
		Connection connection = ConnectionUtils.getMyConnection();
		Statement statement = connection.createStatement();
		
		String sql="select * from tbl_achievement where id=" + id;
		ResultSet rs = statement.executeQuery(sql);
		
		Achievement ach = new Achievement();
		if(rs.next()) {
			String name = rs.getString(2);
			String level = rs.getString(3);
			String sort = rs.getString(4);
			int evaluationID = rs.getInt(5);
			Date created_time = rs.getDate(6);
			Date update_time = rs.getDate(7);
			ach = this.setAchievement(id, name, level, sort, evaluationID, created_time, update_time);
		} else {
			ach = null;
		}
		
		return ach;
	}
	
	public Achievement setAchievement(int id, String name, String level, String sort, int evaluationID,
			Date created_time, Date update_time) {
		Achievement ach = new Achievement();
		ach.id = id;	
		ach.name = name;
		ach.level = level;
		ach.sort = sort;
		ach.evaluationID = evaluationID;
		ach.created_time = created_time;
		ach.update_time = update_time;
		
		return ach;
	}
	
	private int flag;
	private String sql;
	public List<Achievement> search(String name, String level, String sort, int evaluationID) 
			throws ClassNotFoundException, SQLException {
		Connection connection = ConnectionUtils.getMyConnection();
		Statement statement = connection.createStatement();
		
		this.sql = "select * from tbl_achievement where ";
		this.flag = 0;
		
		this.checkStringSearch("name", name);
		this.checkStringSearch("level", level);
		this.checkStringSearch("sort", sort);
		this.checkIntSearch("evaluationID", evaluationID);
		
		if(flag == 1) {
			ResultSet rs = statement.executeQuery(this.sql);
			List<Achievement> achievementArray = new ArrayList<Achievement>();
			while(rs.next()) {
				Achievement ach = new Achievement();
				ach = setAchievement(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5),
						rs.getDate(6), rs.getDate(7));
				achievementArray.add(ach);
			}
			connection.close();
			return achievementArray;
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
//		Achievement achievement = new Achievement();
//		List<Achievement> achievementArray = achievement.search(null, "cấp Huyện", "NHất", 1);
//		for(Achievement ach:achievementArray) {
//			System.out.println(ach.name);
//		}
	}

}
