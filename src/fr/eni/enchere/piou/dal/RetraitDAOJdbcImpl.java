package fr.eni.enchere.piou.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.enchere.piou.bo.Retrait;

public class RetraitDAOJdbcImpl implements DAO<Retrait>{

	@Override
	public void insert(Retrait retrait) {
		PreparedStatement stmt = null;

		try (Connection con = ConnectionProvider.getConnection()) {
			stmt = con.prepareStatement("INSERT INTO RETRAITS(no_article,rue,code_postal,ville) VALUES (?,?,?,?)");
			stmt.setInt(1, retrait.getNoArticle());
			stmt.setString(2, retrait.getRue());
			stmt.setString(3, retrait.getCodePostal());
			stmt.setString(4, retrait.getVille());
			stmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("INSERT RETRAITS pas content");
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int id) {
		PreparedStatement stmt = null;
		try (Connection con = ConnectionProvider.getConnection()) {
			stmt = con.prepareStatement("DELETE FROM RETRAITS WHERE no_article =" + id);
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("DELETE RETRAITS pas content");
			e.printStackTrace();
		}	
	}

	@Override
	public List<Retrait> selectAll() {
		return null;
	}

	@Override
	public List<Retrait> selectByMotCle(String montCle) {
		return null;
	}

	@Override
	public List<Retrait> selectById(int id) {
		Statement stmt;
		List<Retrait> retraits = new ArrayList<Retrait>();

		try (Connection con = ConnectionProvider.getConnection()) {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM RETRAITS WHERE no_article =" + id);
			
			while (rs.next()) {
				Retrait retrait = null;
				int noArticle = rs.getInt(1);
				String rue = rs.getString(2);
				String codePostal = rs.getString(3);
				String ville = rs.getString(4);
				retrait = new Retrait(noArticle, rue, codePostal, ville);
				retraits.add(retrait);
			}
			
		} catch (SQLException e) {
			System.out.println("SELECT by id ENCHERE pas content");
			e.printStackTrace();
		}
		return retraits;
	}

	@Override
	public void update(Retrait retrait) {
		PreparedStatement stmt = null;
		int id = retrait.getNoArticle();
		
		try (Connection con = ConnectionProvider.getConnection()) {
			stmt = con.prepareStatement("UPDATE RETRAITS rue=?, code_postal=?, ville=? WHERE no_article =" + id);
			stmt.setString(1, retrait.getRue());
			stmt.setString(2, retrait.getCodePostal());
			stmt.setString(3, retrait.getVille());
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("UPDATE RETRAITS pas content");
			e.printStackTrace();
		}	
	}
}
