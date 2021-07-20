package fr.eni.enchere.piou.bll;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import fr.eni.enchere.piou.BusinessException;
import fr.eni.enchere.piou.bo.ArticleVendu;
import fr.eni.enchere.piou.bo.Categorie;
import fr.eni.enchere.piou.bo.Enchere;
import fr.eni.enchere.piou.bo.Retrait;
import fr.eni.enchere.piou.bo.Utilisateur;
import fr.eni.enchere.piou.dal.DAO;
import fr.eni.enchere.piou.dal.DAOFactory;

public class EnchereManager {
	private DAO<Utilisateur> DAOUtilisateur;
	private DAO<Enchere> DAOEnchere;
	private DAO<Retrait> DAORetrait;
	private DAO<Categorie> DAOCategorie;
	private DAO<ArticleVendu> articleVendu;

	public EnchereManager() {
		this.DAOUtilisateur = DAOFactory.getUtilisateurDAO();
		this.DAOEnchere = DAOFactory.getEnchereDAO();
		this.DAORetrait = DAOFactory.getRetraitDAO();
		this.DAOCategorie = DAOFactory.getCategoriesDAO();
		this.articleVendu = DAOFactory.getArticleVenduDAO();
	}

	// ---------------------------------------------------------
	// Insert
	// Insert utilisateur
	public Utilisateur insertUtilisateur(String pseudo, String nom, String prenom, String email, String telephone,
			String rue, String codePostal, String ville, String motDePasse, int credit, boolean administrateur)
			throws BusinessException {

		BusinessException exception = new BusinessException();

		Utilisateur utilisateur = new Utilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal, ville,
				motDePasse, credit, administrateur);

		this.validerInfoUtilisateur(utilisateur, exception);

		if (!exception.hasErreurs()) {
			this.DAOUtilisateur.insert(utilisateur);
		}

		if (exception.hasErreurs()) {
			throw exception;
		}
		return utilisateur;
	}

	// Insert Enchere
	public Enchere insertEnchere(int noUtilisateur, int noArticle, Date dateEnchere, int montantEnchere)
			throws BusinessException {
		Enchere enchere = new Enchere(noUtilisateur, noArticle, dateEnchere, montantEnchere);

		BusinessException businessException = new BusinessException();

		validerInsertEnchere(enchere, businessException);

		if (!businessException.hasErreurs()) {
			this.DAOEnchere.insert(enchere);
		}

		if (businessException.hasErreurs()) {
			System.out.println("l'enchere à inserer à été stoppé dans la BLL");
			throw businessException;
		}
		return enchere;
	}

	// Insert Retrait
	public Retrait insertRetrait(int noArticle, String rue, String codePostal, String ville) throws BusinessException {
		Retrait retrait = new Retrait(noArticle, rue, codePostal, ville);

		BusinessException businessException = new BusinessException();

		validerInsertRetrait(retrait, businessException);

		if (!businessException.hasErreurs()) {
			this.DAORetrait.insert(retrait);
		}

		if (businessException.hasErreurs()) {
			System.out.println("l'enchere à inserer à été stoppé dans la BLL");
			throw businessException;
		}
		return retrait;
	}

	// Insert Article
	public void insertArticle(ArticleVendu article) throws BusinessException {

		BusinessException exception = new BusinessException();

		try {
			ValiderArticle(article, exception);
			articleVendu.insert(article);
		} catch (BusinessException e) {
			System.out.println("Erreur addArticle :(");
			e.printStackTrace();
		}
	}

	// ---------------------------------------------------------
	// Delete
	// Delete Utilisateur
	public void deleteUtilisateur(int index) throws BusinessException {

		try {
			DAOUtilisateur.delete(index);
		} catch (BusinessException e) {
			System.out.println("erreur bll utilisateur delete");
			e.printStackTrace();
		}
	}

	// Delete Enchere
	public void deleteEnchere(int id) throws BusinessException {

		try {
			this.DAOEnchere.delete(id);
		} catch (Exception e) {
			System.out.println("l'enchere à supprimer à été stoppé dans la BLL");
			e.printStackTrace();
		}
	}

	// Delete Retrait
	public void deleteRetrait(int id) throws BusinessException {

		try {
			this.DAORetrait.delete(id);
		} catch (Exception e) {
			System.out.println("le retrait à supprimer à été stoppé dans la BLL");
			e.printStackTrace();
		}
	}

	// Delete Article
	public void deleteArticle(ArticleVendu article) throws BusinessException {
		try {
			articleVendu.delete(article.getNoArticle());
		} catch (BusinessException e) {
			System.out.println("Erreur deteleArticle :(");
			e.printStackTrace();
		}
	}

	// ---------------------------------------------------------
	// Select By ID
	// Select By ID Utilisateur
	public List<Utilisateur> selectUtilisateurById(int id) throws BusinessException {
		List<Utilisateur> listeUtilisateur = null;
		try {

			listeUtilisateur = DAOUtilisateur.selectById(id);
		} catch (BusinessException e) {
			System.out.println("erreur bll utilisateur selectbyid");
			
			e.printStackTrace();
		}
		return listeUtilisateur;
	}

	// Select By ID Enchere
	public List<Enchere> selectEnchereById(int id) throws BusinessException {
		List<Enchere> encheres = new ArrayList<Enchere>();

		try {
			encheres = this.DAOEnchere.selectById(id);
		} catch (Exception e) {
			System.out.println("l'enchere à afficher par id à été stoppé dans la BLL");
			e.printStackTrace();
		}
		return encheres;
	}

	// Select By ID Retrait
	public List<Retrait> selectRetraitById(int id) throws BusinessException {
		List<Retrait> retraits = new ArrayList<Retrait>();

		try {
			retraits = this.DAORetrait.selectById(id);
		} catch (Exception e) {
			System.out.println("le retrait à afficher par id à été stoppé dans la BLL");
			e.printStackTrace();
		}
		return retraits;
	}

	// Select By ID Categorie
	public List<Categorie> selectCategorieById(int id) throws BusinessException {
		List<Categorie> categories = null;

		try {
			categories = DAOCategorie.selectById(id);
		} catch (BusinessException e) {
			System.out.println("Erreur getByIdCategorie");
			e.printStackTrace();
		}
		return categories;
	}

	// Select By ID Article
	public List<ArticleVendu> selectArticleVenduById(int id) throws BusinessException {
		List<ArticleVendu> articlesVendus = null;

		try {
			articlesVendus = articleVendu.selectById(id);
		} catch (BusinessException e) {
			System.out.println("Erreur getArticlevenduByID :(");
			e.printStackTrace();
		}

		return articlesVendus;
	}

	// ---------------------------------------------------------
	// Select All
	// Select All Utilisateur
		public List<Utilisateur> selectAllUtilisateur() throws BusinessException {
			List<Utilisateur> utilisateur = null;

			try {
				utilisateur = DAOUtilisateur.selectAll();
			} catch (BusinessException e) {
				System.out.println("Erreur getUtilisateur");
				e.printStackTrace();
			}
			return utilisateur;
		}
		
	// Select All Categorie
	public List<Categorie> selectAllCategorie() throws BusinessException {
		List<Categorie> categories = null;

		try {
			categories = DAOCategorie.selectAll();
		} catch (BusinessException e) {
			System.out.println("Erreur getAllCategorie");
			e.printStackTrace();
		}
		return categories;
	}

	// Select All Article
	public List<ArticleVendu> selectAllArticleVendu() throws BusinessException {
		List<ArticleVendu> articlesVendus = null;

		try {
			articlesVendus = articleVendu.selectAll();
		} catch (BusinessException e) {
			System.out.println("Erreur getAllArticleVendu :(");
			e.printStackTrace();
		}

		return articlesVendus;
	}

	// ---------------------------------------------------------
	// Select By Mot Clef
	
	// Update Utilisateur
		public List<Utilisateur> selectUtilisateurByMotCle(String motCle) throws BusinessException {
			List<Utilisateur> utilisateurs = null;

			try {
				utilisateurs = DAOUtilisateur.selectByMotCle(motCle);
			} catch (BusinessException e) {
				System.out.println("Erreur selectUtilisateurByMotCle :(");
				e.printStackTrace();
			}

			return utilisateurs;
		}
	// Update Article
	public List<ArticleVendu> selectArticleVenduByMotCle(String motCle) throws BusinessException {
		List<ArticleVendu> articlesVendus = null;

		try {
			articlesVendus = articleVendu.selectByMotCle(motCle);
		} catch (BusinessException e) {
			System.out.println("Erreur getArticleVenduByMotCle :(");
			e.printStackTrace();
		}

		return articlesVendus;
	}

	// ---------------------------------------------------------
	// Update
	// Update Utilisateur
	public void updateUtilisateur(Utilisateur utilisateur) throws BusinessException {

		try {
			DAOUtilisateur.update(utilisateur);
		} catch (BusinessException e) {
			System.out.println("erreur bll utilisateur update");

			e.printStackTrace();
		}
	}

	// Update Enchere
	public void updateEnchere(Enchere enchere) throws BusinessException {

		try {
			this.DAOEnchere.update(enchere);
		} catch (Exception e) {
			System.out.println("l'enchere à update à été stoppé dans la BLL");
			e.printStackTrace();
		}
	}

	// Update Retrait
	public void updateRetrait(Retrait retrait) throws BusinessException {

		try {
			this.DAORetrait.update(retrait);
		} catch (Exception e) {
			System.out.println("le retrait à update à été stoppé dans la BLL");
			e.printStackTrace();
		}
	}

	// Update Article
	public void updateArticle(ArticleVendu article) throws BusinessException {

		BusinessException exception = new BusinessException();

		try {
			ValiderArticle(article, exception);
			articleVendu.update(article);
		} catch (BusinessException e) {
			System.out.println("Erreur updateArticle :(");
			e.printStackTrace();
		}
	}

	// ---------------------------------------------------------
	// Validation
	// Validation Utilisateur
	private void validerInfoUtilisateur(Utilisateur utilisateur, BusinessException businessException) {

		if (utilisateur.getPseudo().equals("") || utilisateur.getPseudo() == null) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_CREATION_UTILISATEUR_PSEUDO_ERREUR);
			System.out.println("pseudo");
		}
		if (utilisateur.getPrenom().equals("") || utilisateur.getPrenom() == null) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_CREATION_UTILISATEUR_PRENOM_ERREUR);
			System.out.println("prenom");

		}
		if (utilisateur.getNom().equals("") || utilisateur.getNom() == null) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_CREATION_UTILISATEUR_NOM_ERREUR);
			System.out.println("nom");

		}
		if (utilisateur.getEmail().equals("") || utilisateur.getEmail() == null) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_CREATION_UTILISATEUR_EMAIL_ERREUR);
			System.out.println("email");

		}
		if (utilisateur.getRue().equals("") || utilisateur.getRue() == null) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_CREATION_UTILISATEUR_RUE_ERREUR);
			System.out.println("rue");

		}
		if (utilisateur.getCodePostal().equals("") || utilisateur.getCodePostal() == null) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_CREATION_UTILISATEUR_CDP_ERREUR);
			System.out.println("cdp");

		}
		if (utilisateur.getVille().equals("") || utilisateur.getVille() == null) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_CREATION_UTILISATEUR_VILLE_ERREUR);
			System.out.println("ville");

		}
		if (utilisateur.getMotDePasse().equals("") || utilisateur.getMotDePasse() == null) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_CREATION_UTILISATEUR_MDP_ERREUR);
			System.out.println("mdp");

		}
		if (utilisateur.getCredit() < 0) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_CREATION_UTILISATEUR_CREDIT_ERREUR);
			System.out.println("Credi");

		}

	}

	// Validation Enchere
	private void validerInsertEnchere(Enchere enchere, BusinessException businessException) throws BusinessException {

		if (enchere.getDateEnchere() == null) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_CREATION_ENCHERE_DATE_ERREUR);
		}
		if (enchere.getNoArticle() < 0) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_CREATION_ENCHERE_NO_ARTICLE_ERREUR);
		}
		if (enchere.getNoUtilisateur() < 0) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_CREATION_ENCHERE_NO_UTILISATEUR_ERREUR);
		}
	}

	// Validation Retrait
	private void validerInsertRetrait(Retrait retrait, BusinessException businessException) {

		if (retrait.getNoArticle() < 0) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_CREATION_RETRAIT_DATE_ERREUR);
		}
		if (retrait.getCodePostal() == null || retrait.getCodePostal() == "") {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_CREATION_RETRAIT_CODE_POSTAL_ERREUR);
		}
		if (retrait.getRue() == null || retrait.getRue() == "") {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_CREATION_RETRAIT_RUE_ERREUR);
		}
		if (retrait.getVille() == null || retrait.getVille() == "") {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_CREATION_RETRAIT_VILLE_ERREUR);
		}

	}

	// Validation Article
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
