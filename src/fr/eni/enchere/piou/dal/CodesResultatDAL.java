package fr.eni.enchere.piou.dal;

public abstract class CodesResultatDAL {
	
	/**
	 * Echec général quand tentative d'ajouter un objet null
	 */
	public static final int INSERT_OBJET_NULL=10000;
	
	/**
	 * Echec général quand erreur non gérée à l'insertion 
	 */
	public static final int INSERT_OBJET_ECHEC=10001;
	
	
	/**
	 * Echec général quand tentative d'ajouter un objet null
	 */
	public static final int UPDATE_OBJET_NULL=10002;
	
	/**
	 * Echec général quand erreur non gérée à l'insertion 
	 */
	public static final int UPDATE_OBJET_ECHEC=10003;
	/**
	 * Echec général quand tentative de supprimer un élément inexistant
	 */
	public static final int DELETE_OBJET_NULL=10004;
	
	
	
	
	
	
}
