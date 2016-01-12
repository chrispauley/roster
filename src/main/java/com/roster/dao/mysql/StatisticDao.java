package com.roster.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.roster.model.*;


public class StatisticDao {

	private Connection connection = null;
	
	public StatisticDao() {
		super();
	}
	
	public StatisticDao(Connection connection){
		super();
		this.connection = connection;
	}

	
	private Statistic getStatisticFromResultSet(ResultSet rs) throws SQLException{
		Statistic statistic = new Statistic();
		statistic.setType(rs.getString("type"));
		statistic.setPeriod(rs.getString("period"));
		//statistic.setTimeInPeriod(rs.getString("time_in_period"));
		return statistic;
	}
	
	public void getStatistices(Player player, long playerid){
		try {
			String sql = "Select * from statistic where playerid=?";
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ps.setLong(1, playerid);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Statistic statistic = this.getStatisticFromResultSet(rs);
				player.getStatistic().add(statistic);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
}
