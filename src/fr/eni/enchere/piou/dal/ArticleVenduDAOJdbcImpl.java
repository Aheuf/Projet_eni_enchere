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
import fr.eni.enchere.piou.bo.ArticleVendu;

public class ArticleVenduDAOJdbcImpl implements DAO<ArticleVendu> {
	private static String INSERT = "INSERT INTO ARTICLES_VENDUS (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, no_utilisateur, no_categorie) VALUES (?, ?, ?, ?, ?, ?, ?)";
	private static String DELETE_ID = "DELETE FROM ARTICLES_VENDUS WHERE no_article = ?";
	private static String SELECT_ALL = "SELECT * FROM ARTICLES_VENDUS";
	private static String SELECT_BY_ID = "SELECT * FROM ARTICLES_VENDUS WHERE no_article = ?";
	private static String UPDATE = "UPDATE ARTICLES_VENDUS SET nom_article = ?, description = ?, date_debut_encheres = ?, date_fin_encheres = ?, prix_initial = ?, no_utilisateur = ?, no_categorie = ?";
	private static String SELECT_BY_MC = "SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, no_utilisateur, no_categorie, etat_vente FROM ARTICLES_VENDUS WHERE nom_article LIKE '%?%' OR description LIKE '%?%'";

	@Override
	public void insert(ArticleVendu article) throws BusinessException {

		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement rqt = null;

			rqt = cnx.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			rqt.setString(1, article.getNomArticle());
			rqt.setString(2, article.getDescription());
			rqt.setDate(3, article.getDateDebutEncheres());
			rqt.setDate(4, article.getDateFinEncheres());
			rqt.setInt(5, article.getPrixInitial());
			rqt.setInt(6, article.getNoUtilisateur());
			rqt.setInt(7, article.getNoCategorie());

			int nbRows = rqt.executeUpdate();

			if (nbRows == 1) {
				ResultSet rs = rqt.getGeneratedKeys();
				if (rs.next()) {
					article.setNoArticle(rs.getInt(1));
				}
			}

		} catch (SQLException e) {
			System.out.println("L'insert de l'article n'a pas marché :(");
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_ECHEC);
		}
	}

	@Override
	public void delete(int id) throws BusinessException {
		PreparedStatement rqt = null;

		try (Connection cnx = ConnectionProvider.getConnection()) {
			rqt = cnx.prepareStatement(DELETE_ID);
			rqt.setInt(1, id);
			rqt.executeUpdate();
		} catch (Exception e) {
			System.out.println("La supression de l'article n'a pas marché :(");
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.DELETE_OBJET_NULL);
		}
	}

	@Override
	public List<ArticleVendu> selectAll() throws BusinessException {
		List<ArticleVendu> articlesVendus = new ArrayList<>();
		PreparedStatement rqt = null;
		ResultSet rs = null;

		try (Connection cnx = ConnectionProvider.getConnection()) {
			rqt = cnx.prepareStatement(SELECT_ALL);
			rs = rqt.executeQuery();
			ArticleVendu article = null;

			while (rs.next()) {
				
				
				article = new ArticleVendu(rs.getInt("no_utilisateur"), rs.getString("nom_article"), rs.getString("description"),
						rs.getDate("date_debut_encheres"), rs.getDate("date_fin_encheres"), rs.getInt("prix_initial"),
						rs.getInt("prix_vente"), rs.getInt("no_utilisateur"), rs.getInt("no_categorie"));

				articlesVendus.add(article);
			}

		} catch (SQLException e) {
			System.out.println("La selection de tous les articles n'a pas marché :(");
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.READ_OBJECT_ECHEC);
		}
		return articlesVendus;
	}

	@Override
	public List<ArticleVendu> selectByMotCle(String motCle) throws BusinessException {
		List<ArticleVendu> articlesVendus = new ArrayList<>();
		PreparedStatement rqt = null;
		ResultSet rs = null;
		ArticleVendu article = null;

		try (Connection cnx = ConnectionProvider.getConnection()) {
			rqt = cnx.prepareStatement(SELECT_BY_MC);
			rqt.setString(1, motCle);
			rs = rqt.executeQuery();

			while (rs.next()) {
				article = new ArticleVendu(article.getNoArticle(), article.getNomArticle(), article.getDescription(),
						article.getDateDebutEncheres(), article.getDateFinEncheres(), article.getPrixInitial(),
						article.getPrixVente(), article.getNoUtilisateur(), article.getNoCategorie());

				articlesVendus.add(article);
			}

		} catch (SQLException e) {
			System.out.println("La selection de tous les articles n'a pas marché :(");
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.READ_OBJECT_ECHEC);
		}

		return articlesVendus;
	}

	@Override
	public List<ArticleVendu> selectById(int id) throws BusinessException {
		List<ArticleVendu> articlesVendus = new ArrayList<>();
		PreparedStatement rqt = null;
		ResultSet rs = null;

		try (Connection cnx = ConnectionProvider.getConnection()) {
			rqt = cnx.prepareStatement(SELECT_BY_ID);
			rs = rqt.executeQuery();
			ArticleVendu article = null;

			while (rs.next()) {
				article = new ArticleVendu(article.getNoArticle(), article.getNomArticle(), article.getDescription(),
						article.getDateDebutEncheres(), article.getDateFinEncheres(), article.getPrixInitial(),
						article.getPrixVente(), article.getNoUtilisateur(), article.getNoCategorie());

				articlesVendus.add(article);
			}

		} catch (SQLException e) {
			System.out.println("La selection des articles via no_article n'a pas marché :(");
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.READ_OBJECT_ECHEC);
		}
		return articlesVendus;
	}

	@Override
	public void update(ArticleVendu article) throws BusinessException {
		PreparedStatement rqt = null;

		try (Connection cnx = ConnectionProvider.getConnection()) {
			rqt = cnx.prepareStatement(UPDATE);

			rqt.setString(1, article.getNomArticle());
			rqt.setString(2, article.getDescription());
			rqt.setDate(3, article.getDateDebutEncheres());
			rqt.setDate(4, article.getDateFinEncheres());
			rqt.setInt(5, article.getPrixInitial());
			rqt.setInt(6, article.getNoUtilisateur());
			rqt.setInt(7, article.getNoCategorie());

			rqt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("L'update de l'article n'a pas marché :(");
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.UPDATE_OBJET_ECHEC);

		}
	}

}
