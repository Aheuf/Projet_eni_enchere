package fr.eni.enchere.piou.bo;

import java.sql.Date;

public class Enchere {

	//VARIABLES
	private int noUtilisateur;   
 	private int noArticle;       
    private Date dateEnchere;     
    private int montantEnchere;
    
    //CONSTRUCTEURS
    	//Permet d'appeler les GETTERS & SETTERS dans les servlets (à éviter!)
	public Enchere() {
	}
		//Pas utilisé pour l'instant
	public Enchere(Date dateEnchere, int montantEnchere) {
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
	}
    	//Permet le dialogue entre la BDD et l'appli quelque soit le dialogue
		//CREATE/SELECT/DELETE/UPDATE
    public Enchere(int noUtilisateur, int noArticle, Date dateEnchere, int montantEnchere) {
		this.noUtilisateur = noUtilisateur;
		this.noArticle = noArticle;
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
    }
    
    //GETTER & SETTER
	public int getNoUtilisateur() {
		return noUtilisateur;
	}
	public void setNoUtilisateur(int noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
	}
	public int getNoArticle() {
		return noArticle;
	}
	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}
	public Date getDateEnchere() {
		return dateEnchere;
	}
	public void setDateEnchere(Date dateEnchere) {
		this.dateEnchere = dateEnchere;
	}
	public int getMontantEnchere() {
		return montantEnchere;
	}
	public void setMontantEnchere(int montantEnchere) {
		this.montantEnchere = montantEnchere;
	}
	    
}
