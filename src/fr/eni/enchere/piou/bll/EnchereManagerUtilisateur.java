package fr.eni.enchere.piou.bll;

import java.util.List;

import fr.eni.enchere.piou.BusinessException;
import fr.eni.enchere.piou.bo.Utilisateur;
import fr.eni.enchere.piou.dal.DAO;
import fr.eni.enchere.piou.dal.DAOFactory;

public class EnchereManagerUtilisateur {
	private DAO<Utilisateur> utilisateurDAO;

	public EnchereManagerUtilisateur() {
		this.utilisateurDAO = DAOFactory.getUtilisateurDAO();
	}

	public Utilisateur insert(String pseudo, String nom, String prenom, String email, String telephone, String rue,
			String codePostal, String ville, String motDePasse, int credit, boolean administrateur)
			throws BusinessException {

		BusinessException exception = new BusinessException();

		Utilisateur utilisateur = new Utilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal, ville,
				motDePasse, credit, administrateur);

		this.validerInfoUtilisateur(utilisateur, exception);

		if (!exception.hasErreurs()) {
			this.utilisateurDAO.insert(utilisateur);
		}

		if (exception.hasErreurs()) {
			throw exception;
		}
		return utilisateur;
	}

	public void deleteUtilisateur(int index) throws BusinessException {

		try {
			utilisateurDAO.delete(index);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Utilisateur> getUtilisateur(int id) throws BusinessException {
		List<Utilisateur> listeUtilisateur = null;
		try {
			
			listeUtilisateur = utilisateurDAO.selectById(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listeUtilisateur;
	}

	public void updateUtilisateur(Utilisateur utilisateur) throws BusinessException {

		try {
			utilisateurDAO.update(utilisateur);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void validerInfoUtilisateur(Utilisateur utilisateur, BusinessException businessException) {

		if (utilisateur.getPseudo().equals("") || utilisateur.getPseudo() == null) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_CREATION_UTILISATEUR_PSEUDO_ERREUR);
		}
		if (utilisateur.getPrenom().equals("") || utilisateur.getPrenom() == null) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_CREATION_UTILISATEUR_PRENOM_ERREUR);
		}
		if (utilisateur.getNom().equals("") || utilisateur.getNom() == null) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_CREATION_UTILISATEUR_NOM_ERREUR);
		}
		if (utilisateur.getEmail().equals("") || utilisateur.getEmail() == null) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_CREATION_UTILISATEUR_EMAIL_ERREUR);
		}
		if (utilisateur.getRue().equals("") || utilisateur.getRue() == null) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_CREATION_UTILISATEUR_RUE_ERREUR);
		}
		if (utilisateur.getCodePostal().equals("") || utilisateur.getCodePostal() == null) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_CREATION_UTILISATEUR_CDP_ERREUR);
		}
		if (utilisateur.getVille().equals("") || utilisateur.getVille() == null) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_CREATION_UTILISATEUR_VILLE_ERREUR);
		}
		if (utilisateur.getMotDePasse().equals("") || utilisateur.getMotDePasse() == null) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_CREATION_UTILISATEUR_MDP_ERREUR);
		}
		if (utilisateur.getCredit() < 0) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_CREATION_UTILISATEUR_CREDIT_ERREUR);
		}

	}

}
