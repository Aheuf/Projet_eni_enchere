package fr.eni.enchere.piou.bo;

import java.sql.Date;

public class Enchere {
	 	private int noUtilisateur;   
	 	private int no_article;       
	    private Date dateEnchere;     
	    private int montant_enchere;
	    
		public int getNoUtilisateur() {
			return noUtilisateur;
		}
		public void setNoUtilisateur(int noUtilisateur) {
			this.noUtilisateur = noUtilisateur;
		}
		public int getNo_article() {
			return no_article;
		}
		public void setNo_article(int no_article) {
			this.no_article = no_article;
		}
		public Date getDateEnchere() {
			return dateEnchere;
		}
		public void setDateEnchere(Date dateEnchere) {
			this.dateEnchere = dateEnchere;
		}
		public int getMontant_enchere() {
			return montant_enchere;
		}
		public void setMontant_enchere(int montant_enchere) {
			this.montant_enchere = montant_enchere;
		} 
	    
}
