package fr.eni.enchere.piou.bll;

import java.util.List;

import fr.eni.enchere.piou.bo.ArticleVendu;
import fr.eni.enchere.piou.dal.DAO;
import fr.eni.enchere.piou.dal.DAOFactory;

public class EnchereManagerArticleVendu {
	private DAO<ArticleVendu> articleVendu;
	
	public EnchereManagerArticleVendu() {
		this.articleVendu = DAOFactory.getArticleVenduDAO();
	}

	public void addArticle(ArticleVendu article) {
		try {
			ValiderArticle(article);
			articleVendu.insert(article);
		} catch (Exception e) {
			System.out.println("Erreur addArticle :(");
			e.printStackTrace();
		}
	}
	
	public void updateArticle(ArticleVendu article) {
		try {
			ValiderArticle(article);
			articleVendu.update(article);
		} catch (Exception e) {
			System.out.println("Erreur updateArticle :(");
			e.printStackTrace();
		}
	}
	
	public void deleteArticle(ArticleVendu article) {
		try {
			articleVendu.delete(article.getNoArticle());
		} catch (Exception e) {
			System.out.println("Erreur deteleArticle :(");
			e.printStackTrace();
		}
	}
	
	public List<ArticleVendu> getAllArticleVendu() {
		List<ArticleVendu> articlesVendus = null;

		try {
			articlesVendus = articleVendu.selectAll();
		} catch (Exception e) {
			System.out.println("Erreur getAllArticleVendu :(");
			e.printStackTrace();
		}
		
		return articlesVendus;
	}
	
	public List<ArticleVendu> getArticleVenduByID(int id) {
		List<ArticleVendu> articlesVendus = null;
		
		try {
			articlesVendus = articleVendu.selectById(id);
		} catch (Exception e) {
			System.out.println("Erreur getArticlevenduByID :(");
			e.printStackTrace();
		}
		
		return articlesVendus;
	}

	public List<ArticleVendu> getArticleVenduByMotCle(String motCle) {
		List<ArticleVendu> articlesVendus = null;
		
		try {
			articlesVendus = articleVendu.selectByMotCle(motCle);
		} catch (Exception e) {
			System.out.println("Erreur getArticleVenduByMotCle :(");
			e.printStackTrace();
		}
		
		return articlesVendus;
	}
	
	public void ValiderArticle(ArticleVendu article) {
		boolean valide = true;
		
		if (article == null) {
			System.out.println("Article null");
		}
		if (article.getNomArticle() == null || article.getNomArticle().trim().length() == 0) {
			System.out.println("Le nom d'article est obligatoire :(");
			valide = false;
		}
		if (article.getDescription() == null || article.getDescription().trim().length() == 0) {
			System.out.println("La description d'article est obligatoire :(");
			valide = false;
		}
		if (article.getDateDebutEncheres() == null) {
			System.out.println("La date de début d'enchère est obligatoire :(");
			valide = false;
		}
		if (article.getDateFinEncheres() == null) {
			System.out.println("La date de fin d'enchère est obligatoire :(");
			valide = false;		
		}
		if (article.getPrixInitial() < 0) {
			System.out.println("Le prix initial doit etre supérieur à 0 :(");
			valide = false;
		}
		if (article.getNoCategorie() < 1 || article.getNoCategorie() > 14) {
			System.out.println("Choisissez une catégorie valide :(");
			valide = false;
		}
		
		if (!valide) {
			System.out.println("Article non valide :(");
		}
		
	}
}
