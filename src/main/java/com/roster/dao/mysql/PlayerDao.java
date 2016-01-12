package com.roster.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.roster.model.*;


public class PlayerDao {

	private Connection connection = null;
	
	public PlayerDao() {
		super();
	}
	
	public PlayerDao(Connection connection){
		super();
		this.connection = connection;
	}

	public Player findById(long id) {
		Player player = null;
		try {
			String sql = "Select * from player where playerid=?";
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				player = getPlayerFromResultSet(rs);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return player;
	}
	
	public void getPlayers(Team team, long teamid){
		
		try {
			String sql = "Select * from player where teamid=? order by number";
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ps.setLong(1, teamid);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				team.getPlayer().add(getPlayerFromResultSet(rs));
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	private Player getPlayerFromResultSet(ResultSet rs) throws SQLException{
		Player player = new Player();
		player.setFirstName(rs.getString("first_name"));
		player.setLastName(rs.getString("last_name"));
		player.setPosition(rs.getString("position"));
		player.setNumber(rs.getLong("number"));
		player.setActive(rs.getString("active"));
		return player;
	}
	
}
