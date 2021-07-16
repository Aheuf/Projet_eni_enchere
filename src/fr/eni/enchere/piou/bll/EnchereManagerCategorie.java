package fr.eni.enchere.piou.bll;

import java.util.List;

import fr.eni.enchere.piou.BusinessException;
import fr.eni.enchere.piou.bo.Categorie;
import fr.eni.enchere.piou.dal.DAO;
import fr.eni.enchere.piou.dal.DAOFactory;

public class EnchereManagerCategorie {

	private DAO<Categorie> DAOCategorie;
	
	public EnchereManagerCategorie() {
		this.DAOCategorie = DAOFactory.getCategoriesDAO();
	}	
	
	public List<Categorie> getAllCategorie() throws BusinessException {
		List<Categorie> categories = null;
		
		try {
			categories = DAOCategorie.selectAll();
		} catch (BusinessException e) {
			System.out.println("Erreur getAllCategorie");
			e.printStackTrace();
		}
		return categories;
	}
		
	public List<Categorie> getByIdCategorie(int id) throws BusinessException {
		List<Categorie> categories = null;
		
		try {
			categories = DAOCategorie.selectById(id);
		} catch (BusinessException e) {
			System.out.println("Erreur getByIdCategorie");
			e.printStackTrace();
		}
		return categories;
	}
	
}
