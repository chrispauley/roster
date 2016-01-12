package com.roster.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.roster.model.*;


public class GameDao {

	private Connection connection = null;
	
	public GameDao() {
		super();
	}
	
	public GameDao(Connection connection){
		super();
		this.connection = connection;
	}

	public Game findById(long gid) {
		Game game = null;
		TeamDao teamDao = null;
		try {
			String sql = "Select * from game where gameid=?";
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ps.setLong(1, gid);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				game = this.getGameFromResultSet(rs);
				teamDao = new TeamDao(connection);
				long hometeamId = rs.getLong("home_team_id");
				Team home = teamDao.findById(hometeamId);
				game.getTeam().add(home);
				long visitorTeamId = rs.getLong("visitor_team_id");
				Team visitor = teamDao.findById(visitorTeamId);
				game.getTeam().add(visitor);				
			}
			teamDao = null;
			rs.close();
			ps.close();
			// GetStatistics
			// GetPenalties
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return game;
	}
	
	private Game getGameFromResultSet(ResultSet rs) throws SQLException{
		Game game = new Game();
		game.setName(rs.getString("name"));
		//game.setDatePlayed(rs.getDate("date_played"));
		return game;
	}
	
	
}
