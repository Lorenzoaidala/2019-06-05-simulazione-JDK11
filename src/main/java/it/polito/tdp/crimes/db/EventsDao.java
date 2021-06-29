package it.polito.tdp.crimes.db;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.crimes.model.Event;



public class EventsDao {

	public List<Event> listAllEvents(){
		String sql = "SELECT * FROM events" ;
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;

			List<Event> list = new ArrayList<>() ;

			ResultSet res = st.executeQuery() ;

			while(res.next()) {
				try {
					list.add(new Event(res.getLong("incident_id"),
							res.getInt("offense_code"),
							res.getInt("offense_code_extension"), 
							res.getString("offense_type_id"), 
							res.getString("offense_category_id"),
							res.getTimestamp("reported_date").toLocalDateTime(),
							res.getString("incident_address"),
							res.getDouble("geo_lon"),
							res.getDouble("geo_lat"),
							res.getInt("district_id"),
							res.getInt("precinct_id"), 
							res.getString("neighborhood_id"),
							res.getInt("is_crime"),
							res.getInt("is_traffic")));
				} catch (Throwable t) {
					t.printStackTrace();
					System.out.println(res.getInt("id"));
				}
			}

			conn.close();
			return list ;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}

	public List<Integer> getAnni(){
		String sql ="SELECT DISTINCT YEAR(reported_date) AS anno "
				+ "FROM events "
				+ "ORDER BY YEAR(reported_date)";
		List<Integer> result = new ArrayList<Integer>();
		Connection conn = DBConnect.getConnection();
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while(res.next()) {
				result.add(res.getInt("anno"));
			}
			conn.close();
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		return result;
	}

	public List<Integer> getVertici(){
		String sql="SELECT distinct district_id as id "
				+ "FROM events "
				+ "ORDER BY district_id";
		List<Integer> result = new ArrayList<Integer>();
		Connection conn = DBConnect.getConnection();
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while(res.next()) {
				result.add(res.getInt("id"));
			}
			conn.close();
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		return result;
	}

	public double getLonMedia(int anno, int distretto) {
		String sql ="SELECT AVG(geo_lon) AS lon "
				+ "FROM events "
				+ "WHERE YEAR(reported_date) =? "
				+ "AND district_id=?";

		double result = 0.0;
		Connection conn = DBConnect.getConnection();
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, anno);
			st.setInt(2, distretto);
			ResultSet res = st.executeQuery();
			res.next();
			result = res.getDouble("lon");
			conn.close();
			return result;
		}catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	public double getLatMedia(int anno, int distretto) {
		String sql ="SELECT AVG(geo_lat) AS lat "
				+ "FROM events "
				+ "WHERE YEAR(reported_date) =? "
				+ "AND district_id=?";

		double result = 0.0;
		Connection conn = DBConnect.getConnection();
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, anno);
			st.setInt(2, distretto);
			ResultSet res = st.executeQuery();
			res.next();
			result = res.getDouble("lat");
			conn.close();
			return result;
		}catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}


}
