package fr.eni.enchere.piou.bll;

import java.util.List;

import fr.eni.enchere.piou.BusinessException;
import fr.eni.enchere.piou.bo.ArticleVendu;
import fr.eni.enchere.piou.dal.DAO;
import fr.eni.enchere.piou.dal.DAOFactory;

public class EnchereManagerArticleVendu {

	private DAO<ArticleVendu> articleVendu;

	public EnchereManagerArticleVendu() {
		this.articleVendu = DAOFactory.getArticleVenduDAO();
	}

	public void addArticle(ArticleVendu article) throws BusinessException {
		
		BusinessException exception = new BusinessException();
		
		try {
			ValiderArticle(article, exception);
			articleVendu.insert(article);
		} catch (BusinessException e) {
			System.out.println("Erreur addArticle :(");
			e.printStackTrace();
		}
	}

	public void updateArticle(ArticleVendu article) throws BusinessException{
		
		BusinessException exception = new BusinessException();
		
		try {
			ValiderArticle(article, exception);
			articleVendu.update(article);
		} catch (BusinessException e) {
			System.out.println("Erreur updateArticle :(");
			e.printStackTrace();
		}
	}

	public void deleteArticle(ArticleVendu article) throws BusinessException {
		try {
			articleVendu.delete(article.getNoArticle());
		} catch (BusinessException e) {
			System.out.println("Erreur deteleArticle :(");
			e.printStackTrace();
		}
	}

	public List<ArticleVendu> getAllArticleVendu() throws BusinessException {
		List<ArticleVendu> articlesVendus = null;

		try {
			articlesVendus = articleVendu.selectAll();
		} catch (BusinessException e) {
			System.out.println("Erreur getAllArticleVendu :(");
			e.printStackTrace();
		}

		return articlesVendus;
	}

	public List<ArticleVendu> getArticleVenduByID(int id) throws BusinessException {
		List<ArticleVendu> articlesVendus = null;

		try {
			articlesVendus = articleVendu.selectById(id);
		} catch (BusinessException e) {
			System.out.println("Erreur getArticlevenduByID :(");
			e.printStackTrace();
		}

		return articlesVendus;
	}

	public List<ArticleVendu> getArticleVenduByMotCle(String motCle) throws BusinessException {
		List<ArticleVendu> articlesVendus = null;

		try {
			articlesVendus = articleVendu.selectByMotCle(motCle);
		} catch (BusinessException e) {
			System.out.println("Erreur getArticleVenduByMotCle :(");
			e.printStackTrace();
		}

		return articlesVendus;
	}

	public void ValiderArticle(ArticleVendu article, BusinessException businessException) throws BusinessException {
		boolean valide = true;

		if (article == null) {
			System.out.println("Article null");
		}
		if (article.getNomArticle() == null || article.getNomArticle().trim().length() == 0) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_CREATION_ARTICLEVENDU_NOM_ARTICLE_ERREUR);
			valide = false;
		}
		if (article.getDescription() == null || article.getDescription().trim().length() == 0) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_CREATION_ARTICLEVENDU_DESCRIPTION_ERREUR);
			valide = false;
		}
		if (article.getDateDebutEncheres() == null) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_CREATION_ARTICLEVENDU_DATE_DEBUT_ERREUR);
			valide = false;
		}
		if (article.getDateFinEncheres() == null) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_CREATION_ARTICLEVENDU_DATE_FIN_ERREUR);
			valide = false;
		}
		if (article.getPrixInitial() < 0) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_CREATION_ARTICLEVENDU_PRIX_INITIAL_ERREUR);
			valide = false;
		}
		if (article.getNoCategorie() < 1 || article.getNoCategorie() > 14) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_CREATION_ARTICLEVENDU_NO_CATEGORIE_ERREUR);
			valide = false;
		}

		if (!valide) {
			System.out.println("Article non valide :(");
		}

	}
}
