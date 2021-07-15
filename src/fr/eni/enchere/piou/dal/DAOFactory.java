package fr.eni.enchere.piou.dal;

import fr.eni.enchere.piou.bo.ArticleVendu;
import fr.eni.enchere.piou.bo.Categorie;
import fr.eni.enchere.piou.bo.Enchere;
import fr.eni.enchere.piou.bo.Retrait;
import fr.eni.enchere.piou.bo.Utilisateur;

public abstract class DAOFactory {
	
	public static DAO<Utilisateur>getUtilisateurDAO(){
		return new UtilisateurDAOJdbcImpl();}
	
	public static DAO<ArticleVendu>getArticleVenduDAO(){
		return new ArticleVenduDAOJdbcImpl();}
	
	public static DAO<Categorie>getCategoriesDAO(){
		return new CategorieDAOJdbcImpl();}
	
	public static DAO<Enchere>getEnchereDAO(){
		return new EnchereDAOJdbcImpl();}
	
	public static DAO<Retrait>getRetraitDAO(){
		return new RetraitDAOJdbcImpl();}
	
	
	
	
	
	
	
}
