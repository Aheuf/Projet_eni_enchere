package fr.eni.enchere.piou.bll;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

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
	public void insertEnchere(int noUtilisateur, int noArticle, Date dateEnchere, int montantEnchere) {
		Enchere enchere = new Enchere(noUtilisateur, noArticle, dateEnchere, montantEnchere);
		
		if (validerInsertEnchere(enchere)) {
			this.DAOEnchere.insert(enchere);			
		} else {
			System.out.println("l'enchere à inserer à été stoppé dans la BLL");
		}
	}

	public void insertRetrait(int noArticle, String rue, String codePostal, String ville) {
		Retrait retrait = new Retrait(noArticle, rue, codePostal, ville);
		
		if (validerInsertRetrait(retrait)) {
			this.DAORetrait.insert(retrait);			
		} else {
			System.out.println("le retrait à inserer à été stoppé dans la BLL");			
		}
	}

	// delete
	public void deleteEnchere(int id) {
		
		try {
			this.DAOEnchere.delete(id);
		} catch (Exception e) {
			System.out.println("l'enchere à supprimer à été stoppé dans la BLL");
			e.printStackTrace();
		}
	}

	public void deleteRetrait(int id) {
		
		try {
			this.DAORetrait.delete(id);
		} catch (Exception e) {
			System.out.println("le retrait à supprimer à été stoppé dans la BLL");
			e.printStackTrace();
		}
	}

	// selectById
	public List<Enchere> selectEnchereById(int id) {
		List<Enchere> encheres = new ArrayList<Enchere>();
		
		try {
			encheres = this.DAOEnchere.selectById(id);
		} catch (Exception e) {
			System.out.println("l'enchere à afficher par id à été stoppé dans la BLL");
			e.printStackTrace();
		}
		return encheres;
	}

	public List<Retrait> selectRetraitById(int id) {
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
	public void updateEnchere(Enchere enchere) {
		
		try {
			this.DAOEnchere.update(enchere);
		} catch (Exception e) {
			System.out.println("l'enchere à update à été stoppé dans la BLL");
			e.printStackTrace();
		}
	}

	public void updateRetrait(Retrait retrait) {
		
		try {
			this.DAORetrait.update(retrait);
		} catch (Exception e) {
			System.out.println("le retrait à update à été stoppé dans la BLL");
			e.printStackTrace();
		}
	}

	// validation
	// Vinsert
	private boolean validerInsertEnchere(Enchere enchere) {
		
		if (enchere.getDateEnchere() != null || enchere.getNoArticle() > 0 || enchere.getNoUtilisateur() > 0) {
			return true;
		}
		return false;
	}

	private boolean validerInsertRetrait(Retrait retrait) {
		
		if (retrait.getNoArticle() > 0 || retrait.getCodePostal() != null || retrait.getCodePostal() != ""
				|| retrait.getRue() != null || retrait.getRue() != "" || retrait.getVille() != null
				|| retrait.getVille() != "") {
			return true;
		}
		return false;
	}
}
