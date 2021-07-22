package fr.eni.enchere.piou.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.enchere.piou.BusinessException;
import fr.eni.enchere.piou.bo.Enchere;

public class EnchereDAOJdbcImpl implements DAO<Enchere>{

	@Override
	public void insert(Enchere enchere) throws BusinessException {
		PreparedStatement stmt = null;
		
		if (enchere == null) {

			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
			throw businessException;

		}

		try (Connection con = ConnectionProvider.getConnection()) {
			stmt = con.prepareStatement("INSERT INTO ENCHERES(no_utilisateur,no_article,date_enchere,montant_enchere) VALUES (?,?,?,?)");
			stmt.setInt(1, enchere.getNoUtilisateur());
			stmt.setInt(2, enchere.getNoArticle());
			stmt.setDate(3, enchere.getDateEnchere());
			stmt.setInt(4, enchere.getMontantEnchere());
			stmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("INSERT ENCHERE pas content");
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
			stmt = con.prepareStatement("DELETE FROM ENCHERES WHERE no_article =" + id);
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("DELETE ENCHERE pas content");
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.DELETE_OBJET_NULL);
			throw businessException;
		}	
	}

	@Override
	public List<Enchere> selectAll() throws BusinessException {
		return null;
	}

	@Override
	public List<Enchere> selectByMotCle(String montCle) throws BusinessException {
		return null;
	}

	@Override
	public List<Enchere> selectById(int id) throws BusinessException {
		Statement stmt;
		List<Enchere> encheres = new ArrayList<Enchere>();

		try (Connection con = ConnectionProvider.getConnection()) {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM ENCHERE WHERE no_article =" + id);
			
			while (rs.next()) {
				Enchere enchere = null;
				int noUtilisateur = rs.getInt(1);
				int noArticle = rs.getInt(2);
				Date dateEnchere = rs.getDate(3);
				int montantEnchere = rs.getInt(4);
				enchere = new Enchere(noUtilisateur, noArticle, dateEnchere, montantEnchere);
				encheres.add(enchere);
			}
			
		} catch (SQLException e) {
			System.out.println("SELECT by id ENCHERE pas content");
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.READ_OBJECT_ECHEC);
			throw businessException;
		}
		return encheres;
	}

	@Override
	public void update(Enchere enchere) throws BusinessException {
		PreparedStatement stmt = null;
		int id = enchere.getNoArticle();
		
		try (Connection con = ConnectionProvider.getConnection()) {
			stmt = con.prepareStatement("UPDATE ENCHERES SET no_utilisateur=?, date_enchere=?, montant_enchere=? WHERE no_article =" + id);
			stmt.setInt(1, enchere.getNoUtilisateur());
			stmt.setDate(2, enchere.getDateEnchere());
			stmt.setInt(3, enchere.getMontantEnchere());
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("UPDATE ENCHERE pas content");
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.UPDATE_OBJET_ECHEC);
			throw businessException;
		}	
	}
	
}