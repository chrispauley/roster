package com.roster.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.roster.model.*;


public class CoachDao {

	private Connection connection = null;
	
	public CoachDao() {
		super();
	}
	
	public CoachDao(Connection connection){
		super();
		this.connection = connection;
	}

	public Coach findById(long id) {
		Coach coach = null;
		try {
			String sql = "Select * from coach where coachid=?";
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				coach = this.getCoachFromResultSet(rs);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return coach;
	}
	
	private Coach getCoachFromResultSet(ResultSet rs) throws SQLException{
		Coach coach = new Coach();
		coach.setFirstName(rs.getString("first_name"));
		coach.setLastName(rs.getString("last_name"));
		coach.setRole(rs.getString("role"));
		return coach;
	}
	
	public void getCoaches(Team team, long teamid){
		try {
			String sql = "Select * from coach where teamid=?";
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ps.setLong(1, teamid);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Coach coach = this.getCoachFromResultSet(rs);
				team.getCoach().add(coach);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
}
