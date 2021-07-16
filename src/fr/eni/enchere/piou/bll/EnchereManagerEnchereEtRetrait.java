package fr.eni.enchere.piou.bll;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import fr.eni.enchere.piou.BusinessException;
import fr.eni.enchere.piou.bo.Enchere;
import fr.eni.enchere.piou.bo.Retrait;
import fr.eni.enchere.piou.dal.DAO;
import fr.eni.enchere.piou.dal.DAOFactory;

public class EnchereManagerEnchereEtRetrait {
	private DAO<Enchere> DAOEnchere;
	private DAO<Retrait> DAORetrait;

	public EnchereManagerEnchereEtRetrait() {
		this.DAOEnchere = DAOFactory.getEnchereDAO();
		this.DAORetrait = DAOFactory.getRetraitDAO();
	}

	// insert
	public Enchere insertEnchere(int noUtilisateur, int noArticle, Date dateEnchere, int montantEnchere) throws BusinessException {
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

	public Retrait insertRetrait(int noArticle, String rue, String codePostal, String ville) throws BusinessException{
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

	// delete
	public void deleteEnchere(int id) throws BusinessException{
		
		try {
			this.DAOEnchere.delete(id);
		} catch (Exception e) {
			System.out.println("l'enchere à supprimer à été stoppé dans la BLL");
			e.printStackTrace();
		}
	}

	public void deleteRetrait(int id) throws BusinessException{
		
		try {
			this.DAORetrait.delete(id);
		} catch (Exception e) {
			System.out.println("le retrait à supprimer à été stoppé dans la BLL");
			e.printStackTrace();
		}
	}

	// selectById
	public List<Enchere> selectEnchereById(int id) throws BusinessException{
		List<Enchere> encheres = new ArrayList<Enchere>();
		
		try {
			encheres = this.DAOEnchere.selectById(id);
		} catch (Exception e) {
			System.out.println("l'enchere à afficher par id à été stoppé dans la BLL");
			e.printStackTrace();
		}
		return encheres;
	}

	public List<Retrait> selectRetraitById(int id) throws BusinessException{
		List<Retrait> retraits = new ArrayList<Retrait>();
		
		try {
			retraits = this.DAORetrait.selectById(id);
		} catch (Exception e) {
			System.out.println("le retrait à afficher par id à été stoppé dans la BLL");
			e.printStackTrace();
		}
		return retraits;
	}

	// update
	public void updateEnchere(Enchere enchere) throws BusinessException{
		
		try {
			this.DAOEnchere.update(enchere);
		} catch (Exception e) {
			System.out.println("l'enchere à update à été stoppé dans la BLL");
			e.printStackTrace();
		}
	}

	public void updateRetrait(Retrait retrait) throws BusinessException{
		
		try {
			this.DAORetrait.update(retrait);
		} catch (Exception e) {
			System.out.println("le retrait à update à été stoppé dans la BLL");
			e.printStackTrace();
		}
	}

	// validation
	// Vinsert
	private void validerInsertEnchere(Enchere enchere, BusinessException businessException) throws BusinessException{
		
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
}
