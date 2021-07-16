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
	
	/**
	 * Echec quand la fiche article vendu ne respecte pas les règles définies
	 */
	public static final int REGLE_CREATION_ARTICLEVENDU_NOM_ARTICLE_ERREUR=20010;
	public static final int REGLE_CREATION_ARTICLEVENDU_DESCRIPTION_ERREUR=20011;
	public static final int REGLE_CREATION_ARTICLEVENDU_DATE_DEBUT_ERREUR=20012;
	public static final int REGLE_CREATION_ARTICLEVENDU_DATE_FIN_ERREUR=20013;
	public static final int REGLE_CREATION_ARTICLEVENDU_PRIX_INITIAL_ERREUR=20014;
	public static final int REGLE_CREATION_ARTICLEVENDU_NO_CATEGORIE_ERREUR=20015;
	/**
	 * Echec quand la fiche enchere vendu ne respecte pas les règles définies
	 */
	public static final int REGLE_CREATION_ENCHERE_DATE_ERREUR = 20020;
	public static final int REGLE_CREATION_ENCHERE_NO_ARTICLE_ERREUR = 20021;
	public static final int REGLE_CREATION_ENCHERE_NO_UTILISATEUR_ERREUR = 20022;
	/**
	 * Echec quand la fiche retrait vendu ne respecte pas les règles définies
	 */
	public static final int REGLE_CREATION_RETRAIT_DATE_ERREUR = 20030;
	public static final int REGLE_CREATION_RETRAIT_CODE_POSTAL_ERREUR = 20031;
	public static final int REGLE_CREATION_RETRAIT_RUE_ERREUR = 20032;
	public static final int REGLE_CREATION_RETRAIT_VILLE_ERREUR = 20033;
}
