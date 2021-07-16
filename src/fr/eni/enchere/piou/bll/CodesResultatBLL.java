package fr.eni.enchere.piou.bll;

public abstract class CodesResultatBLL {
	

	/**
	 * Echec quand la fiche utilisateur  ne respecte pas les règles définies
	 */
	public static final int REGLE_CREATION_UTILISATEUR_PSEUDO_ERREUR=20000;	
	public static final int REGLE_CREATION_UTILISATEUR_PRENOM_ERREUR=20001;
	public static final int REGLE_CREATION_UTILISATEUR_NOM_ERREUR=20002;
	public static final int REGLE_CREATION_UTILISATEUR_EMAIL_ERREUR=20003;
	public static final int REGLE_CREATION_UTILISATEUR_RUE_ERREUR=20004;
	public static final int REGLE_CREATION_UTILISATEUR_CDP_ERREUR=20005;
	public static final int REGLE_CREATION_UTILISATEUR_VILLE_ERREUR=20006;
	public static final int REGLE_CREATION_UTILISATEUR_MDP_ERREUR=20007;
	public static final int REGLE_CREATION_UTILISATEUR_CREDIT_ERREUR=20008;
	
}
