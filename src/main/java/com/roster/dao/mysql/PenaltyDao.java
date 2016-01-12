package com.roster.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.roster.model.*;


public class PenaltyDao {

	private Connection connection = null;
	
	public PenaltyDao() {
		super();
	}
	
	public PenaltyDao(Connection connection){
		super();
		this.connection = connection;
	}

	
	private Penalty getPenaltyFromResultSet(ResultSet rs) throws SQLException{
		Penalty penalty = new Penalty();
		penalty.setType(rs.getString("type"));
		penalty.setPeriod(rs.getString("period"));
		//penalty.setTimeInPeriod(rs.getString("timeInPeriod"));
		penalty.setDurationInSeconds(rs.getLong("duration_in_seconds"));
		return penalty;
	}
	
	public void getPenaltys(Player player, long playerid){
		try {
			String sql = "Select * from penalty where playerid=?";
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ps.setLong(1, playerid);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Penalty penalty = this.getPenaltyFromResultSet(rs);
				player.getPenalty().add(penalty);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
}
