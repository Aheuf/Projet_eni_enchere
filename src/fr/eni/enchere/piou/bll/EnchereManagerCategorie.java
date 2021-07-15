package fr.eni.enchere.piou.bll;

import java.util.List;

import fr.eni.enchere.piou.bo.Categorie;
import fr.eni.enchere.piou.dal.DAO;
import fr.eni.enchere.piou.dal.DAOFactory;

public class EnchereManagerCategorie {

	private DAO<Categorie> DAOCategorie;
	
	public EnchereManagerCategorie() {
		this.DAOCategorie = DAOFactory.getCategoriesDAO();
	}	
	
	public List<Categorie> getAllCategorie() {
		List<Categorie> categories = null;
		
		try {
			categories = DAOCategorie.selectAll();
		} catch (Exception e) {
			System.out.println("Erreur getAllCategorie");
			e.printStackTrace();
		}
		return categories;
	}
		
	public List<Categorie> getByIdCategorie(int id) {
		List<Categorie> categories = null;
		
		try {
			categories = DAOCategorie.selectById(id);
		} catch (Exception e) {
			System.out.println("Erreur getByIdCategorie");
			e.printStackTrace();
		}
		return categories;
	}
	
}
