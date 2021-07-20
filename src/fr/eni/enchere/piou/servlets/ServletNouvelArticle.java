package fr.eni.enchere.piou.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.enchere.piou.BusinessException;
import fr.eni.enchere.piou.bll.EnchereManager;
import fr.eni.enchere.piou.bo.ArticleVendu;
import fr.eni.enchere.piou.bo.Retrait;
import fr.eni.enchere.piou.bo.Utilisateur;

/**
 * Servlet implementation class ServletNouvelArticle
 */
@WebServlet("/encheres/ServletNouvelArticle")
public class ServletNouvelArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletNouvelArticle() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	//
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int idUtilisateur = 0;
		EnchereManager em = new EnchereManager();
		Cookie[] cookies = request.getCookies();
		Utilisateur utilisateur = null;
		
		for (Cookie c : cookies) {
			if (c.getName().equals("idUtilisateur")) {
				idUtilisateur = Integer.parseInt(c.getValue());
			}
		}
		
		try {
			
			
			
			
			
			/**
			List<Utilisateur> utilisateurs = em.selectUtilisateurById(idUtilisateur);
			utilisateur = utilisateurs.get(0);
			
			em.deleteUtilisateur(utilisateur.getNoUtilisateur());
			//
			
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
		this.getServletContext().setAttribute("utilisateur", utilisateur);
		RequestDispatcher rd = request.getRequestDispatcher("/FilterConnexion");
		rd.forward(request, response);
		
	}
		
		//j'ajoute l'article
		
		//request.setAttribute("Articles", articles);

		ArticleVendu article = null; //////////
		Retrait retrait = null;
		
		request.setAttribute("Nom", article.getNomArticle());
		request.setAttribute("Description", article.getDescription());
		request.setAttribute("Categorie", article.getNoCategorie());
		
		request.setAttribute("MiseAPrix", article.getPrixInitial());
		request.setAttribute("DateDebutEncheres", article.getDateDebutEncheres());
		request.setAttribute("DateFinEnchere", article.getDateFinEncheres());
		
		request.setAttribute("Rue", retrait.getRue());
		request.setAttribute("CodePostal", retrait.getCodePostal());
		request.setAttribute("Ville", retrait.getVille());

	}
*/
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int idUtilisateur = 0;
		EnchereManager em = new EnchereManager();
		Cookie[] cookies = request.getCookies();
		Utilisateur utilisateur = null;
		
		for (Cookie c : cookies) {
			if (c.getName().equals("idUtilisateur")) {
				idUtilisateur = Integer.parseInt(c.getValue());
			}
		}
		
		try {
			List<Utilisateur> users = em.selectUtilisateurById(idUtilisateur);
			utilisateur = users.get(0);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
		String nomArticle;
		String description;
		
		String noCategorie;
		//String photo;
		
		String dateDebutEncheres;
		String dateFinEncheres;
		String prixInitial;
				
		String rue;
		String codePostal;
		String ville;

		
		
		nomArticle = request.getParameter("article");
		description = request.getParameter("description");
		
/**/	noCategorie = request.getParameter("categorie");
		//photo = request.getParameter("photo");
		
		dateDebutEncheres = request.getParameter("debut");
		dateFinEncheres = request.getParameter("fin");
		prixInitial = request.getParameter("prix");
				
		rue = request.getParameter("inputRue");
		codePostal = request.getParameter("inputCodePostal");
		ville = request.getParameter("inputVille");
				
		if (nomArticle == null || description == null || noCategorie == null || dateDebutEncheres == null || dateFinEncheres == null || prixInitial == null || rue == null || codePostal == null || ville == null ) {
			this.getServletContext().setAttribute("ErreurSaisi", "Tous les champs doivent être remplis !");
			RequestDispatcher rd = request.getRequestDispatcher("/FilterConnexion");
			rd.forward(request, response);
		} else {
			
			// >>> WRITE 2 BDD <<<
			
			RequestDispatcher rd = request.getRequestDispatcher("/FilterConnexion");
			rd.forward(request, response);
		}
		
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
