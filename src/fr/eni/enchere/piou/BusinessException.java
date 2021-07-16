package fr.eni.enchere.piou;

import java.util.ArrayList;
import java.util.List;


public class BusinessException extends Exception {
	private List<Integer> listeCodesErreur;

	public BusinessException() {
		super();
		this.listeCodesErreur = new ArrayList<>();
	}

	/**
	 * 
	 * @param code
	 *            Code de l'erreur. Doit avoir un message associé dans un fichier
	 *            properties.
	 */
	public void ajouterErreur(int code) {
		if (!this.listeCodesErreur.contains(code)) {
			this.listeCodesErreur.add(code);
		}
	}

	public boolean hasErreurs() {
		return this.listeCodesErreur.size() > 0;
	}

	public List<Integer> getListeCodesErreur() {
		return this.listeCodesErreur;
	}

}
