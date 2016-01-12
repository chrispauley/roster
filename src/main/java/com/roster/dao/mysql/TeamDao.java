package com.roster.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.roster.model.*;


public class TeamDao {

	private Connection connection = null;
	
	public TeamDao() {
		super();
	}
	
	public TeamDao(Connection connection){
		super();
		this.connection = connection;
	}

	public Team findById(long id) {
		Team team = null;
		try {
			String sql = "Select * from team where teamid=?";
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				team = new Team();
				//team.setValueObjectId(id);
				team.setName(rs.getString("name"));
			}
			rs.close();
			ps.close();
			if (team == null) {
				return null;
			}

			PlayerDao playerDao = new PlayerDao(connection);
			playerDao.getPlayers(team, id);
			CoachDao coachDao = new CoachDao(connection);
			coachDao.getCoaches(team, id);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return team;
	}
	
	
	
}
