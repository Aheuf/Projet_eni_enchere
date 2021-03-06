package fr.eni.enchere.piou.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.enchere.piou.BusinessException;
import fr.eni.enchere.piou.bo.Retrait;

public class RetraitDAOJdbcImpl implements DAO<Retrait>{

	@Override
	public void insert(Retrait retrait) throws BusinessException {
		PreparedStatement stmt = null;
		
		if (retrait == null) {

			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
			throw businessException;

		}

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
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_ECHEC);
			throw businessException;
		}
	}

	@Override
	public void delete(int id) throws BusinessException {
		PreparedStatement stmt = null;
		try (Connection con = ConnectionProvider.getConnection()) {
			stmt = con.prepareStatement("DELETE FROM RETRAITS WHERE no_article =" + id);
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("DELETE RETRAITS pas content");
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.DELETE_OBJET_NULL);
			throw businessException;
		}	
	}

	@Override
	public List<Retrait> selectAll() throws BusinessException {
		return null;
	}

	@Override
	public List<Retrait> selectByMotCle(String montCle) throws BusinessException {
		return null;
	}

	@Override
	public List<Retrait> selectById(int id) throws BusinessException {
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
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.READ_OBJECT_ECHEC);
			throw businessException;
		}
		return retraits;
	}

	@Override
	public void update(Retrait retrait) throws BusinessException {
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
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.UPDATE_OBJET_ECHEC);
			throw businessException;
		}	
	}
}
