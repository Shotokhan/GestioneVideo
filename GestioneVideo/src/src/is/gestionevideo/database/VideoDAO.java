package src.is.gestionevideo.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/*
import java.time.LocalDate;
import java.time.ZoneId;
*/
import src.is.gestionevideo.entity.Video;

import src.is.gestionevideo.database.DBManager;

public class VideoDAO {
	
	public static Video create(Video V){
		try {
			Connection conn = DBManager.getConnection();
			
			String query = new String("INSERT INTO Video VALUES (NULL, ?, ?, ?, NULL)");
			
			PreparedStatement stmt = conn.prepareStatement(query);
			
			stmt.setString(1, V.getNome());
			stmt.setDate(2, Date.valueOf(V.getData()));
			stmt.setString(3, V.getSport().toString());
			
			stmt.executeUpdate();
			
			ResultSet r = stmt.getGeneratedKeys();
			int id = 0;
			if(r.next())
				id = r.getInt(1);
			/*
			String selectQuery = new String("SELECT Nome, Data, Sport FROM Video WHERE (Video.Nome = ? AND Video.Data = ? AND Video.Sport = ?)");
			stmt = conn.prepareStatement(selectQuery);
			r = stmt.executeQuery();
			String nome = r.getString("NOME");
			LocalDate data = r.getDate("DATA").toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			Sport sport = new Sport(r.getString("SPORT"));
			*/
			V.setId(id);
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return V;
	}
	// TODO: read, update, delete
	
}
