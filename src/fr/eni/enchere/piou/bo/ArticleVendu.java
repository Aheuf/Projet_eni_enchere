package fr.eni.enchere.piou.bo;

import java.sql.Date;



public class ArticleVendu {

	// Attributs
	private int noArticle;
	private String nomArticle;
	private String description;
	private Date dateDebutEncheres;
	private Date dateFinEncheres;
	private int prixInitial;
	private int prixVente;
	private int noUtilisateur;
	private int noCategorie;
	private String etatVente;
	private String dernierEncherisseur;

	// Constructor
	public ArticleVendu(int noArticle, String nomArticle, String description, Date dateDebutEncheres,
			Date dateFinEncheres, int prixInitial, int prixVente, int noUtilisateur, int noCategorie, String dernierEncherisseur) {
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.prixInitial = prixInitial;
		this.prixVente = prixVente;
		this.noUtilisateur = noUtilisateur;
		this.noCategorie = noCategorie;
		this.dernierEncherisseur = dernierEncherisseur;
	}

	public ArticleVendu(String nomArticle, String description, Date dateDebutEncheres, Date dateFinEncheres,
			int prixInitial, int prixVente, int noUtilisateur, int noCategorie, String dernierEncherisseur) {
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.prixInitial = prixInitial;
		this.prixVente = prixVente;
		this.noUtilisateur = noUtilisateur;
		this.noCategorie = noCategorie;
		this.dernierEncherisseur = dernierEncherisseur;
	}

	public ArticleVendu() {
	}

	// Getter Setter
	public int getNoArticle() {
		return noArticle;
	}

	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}

	public String getNomArticle() {
		return nomArticle;
	}

	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDateDebutEncheres() {
		return dateDebutEncheres;
	}

	public void setDateDebutEncheres(Date dateDebutEncheres) {
		this.dateDebutEncheres = dateDebutEncheres;
	}

	public Date getDateFinEncheres() {
		return dateFinEncheres;
	}

	public void setDateFinEncheres(Date dateFinEncheres) {
		this.dateFinEncheres = dateFinEncheres;
	}

	public int getPrixInitial() {
		return prixInitial;
	}

	public void setPrixInitial(int prixInitial) {
		this.prixInitial = prixInitial;
	}

	public int getPrixVente() {
		return prixVente;
	}

	public void setPrixVente(int prixVente) {
		this.prixVente = prixVente;
	}

	public int getNoUtilisateur() {
		return noUtilisateur;
	}

	public void setNoUtilisateur(int noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
	}

	public int getNoCategorie() {
		return noCategorie;
	}

	public void setNoCategorie(int noCategorie) {
		this.noCategorie = noCategorie;
	}

	public String getEtatVente() {
		return etatVente;
	}

	public void setEtatVente(String etatVente) {
		this.etatVente = etatVente;
	}

	public String getDernierEncherisseur() {
		return dernierEncherisseur;
	}

	public void setDernierEncherisseur(String dernierEncherisseur) {
		this.dernierEncherisseur = dernierEncherisseur;
	}

}
