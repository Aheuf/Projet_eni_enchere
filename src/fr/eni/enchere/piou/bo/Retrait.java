package fr.eni.enchere.piou.bo;

public class Retrait {
	
	//VARIABLES
	private int noArticle; 
	private String rue;              
	private String codePostal;     
	private String ville;
	
	//CONSTRUCTEURS
		//Permet d'appeler les GETTERS & SETTERS dans les servlets (à éviter!)
	public Retrait() {
	}
		//Permet la création d'un nouveau retrait(CREATE)
	public Retrait(String rue, String codePostal, String ville) {
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
	}
		//Permet la recherche d'un retrait précis dans la BDD (SELECT/DELETE/UPDATE)
	public Retrait(int noArticle, String rue, String codePostal, String ville) {
		this.noArticle = noArticle;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
	}
	
	//GETTER & SETTER
	public int getNoArticle() {
		return noArticle;
	}
	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}
	public String getRue() {
		return rue;
	}
	public void setRue(String rue) {
		this.rue = rue;
	}
	public String getCodePostal() {
		return codePostal;
	}
	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	
}
