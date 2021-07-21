package fr.eni.enchere.piou.servlets;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.enchere.piou.BusinessException;
import fr.eni.enchere.piou.bll.EnchereManager;
import fr.eni.enchere.piou.bo.ArticleVendu;
//import fr.eni.enchere.piou.bo.Categorie;
//import fr.eni.enchere.piou.bo.Retrait;
import fr.eni.enchere.piou.bo.Utilisateur;

/**
 * Servlet implementation class ServletNouvelArticle
 */
@WebServlet("/encheres/ServletNouvelArticle")
public class ServletNouvelArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	*/
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			/** COOKIES
			Cookie[] cookies = request.getCookies();
			Utilisateur utilisateur = null;		/// ???
			
			for (Cookie c : cookies) {
				if (c.getName().equals("idUtilisateur")) {
					idUtilisateur = Integer.parseInt(c.getValue());
				}
			}
			
			try {
				List<Utilisateur> utilisateurs = em.selectUtilisateurById(idUtilisateur);
				utilisateur = utilisateurs.get(0);
			} catch (BusinessException e) {
				e.printStackTrace();
			}
			*/
		
		request.setCharacterEncoding("UTF-8");
		int idUtilisateur = 0;
		HttpSession session = request.getSession();
		idUtilisateur=(int)session.getAttribute("session");
		
		//int idArticle =Integer.parseInt(request.getParameter("idarticle"));
		//RequestDispatcher rd = null;
		
		EnchereManager em = new EnchereManager();
		ArticleVendu article = null;
		
		String nomArticle = null;
		String description = null;
		Date dateDebutEncheres = null;
		Date dateFinEncheres = null;
		int prixInitial = 0;
		int prixVente = 0;
		int noUtilisateur = 0;
		int noCategorie = 0;
		
		//String photo;
		
		String rue = null;
		String codePostal = null;
		String ville = null;

		nomArticle = request.getParameter("article");
		description = request.getParameter("description");
		dateDebutEncheres = Date.valueOf(request.getParameter("debut"));
		dateFinEncheres = Date.valueOf(request.getParameter("fin"));
		prixInitial = Integer.parseInt(request.getParameter("prix"));
		prixVente = prixInitial;
		noUtilisateur = idUtilisateur;
/**/	noCategorie = Integer.parseInt(request.getParameter("categorie"));
		//photo = request.getParameter("photo");
		rue = request.getParameter("inputRue");
		codePostal = request.getParameter("inputCodePostal");
		ville = request.getParameter("inputVille");
				
		if (nomArticle == null || description == null || noCategorie == 0 || dateDebutEncheres == null || dateFinEncheres == null || prixInitial == 0 || rue == null || codePostal == null || ville == null ) {
			this.getServletContext().setAttribute("ErreurSaisi", "Tous les champs doivent être remplis !");
			RequestDispatcher rd = request.getRequestDispatcher("/encheres/ServletVente");
			rd.forward(request, response);
		}
		
		article = new ArticleVendu(nomArticle, description, dateDebutEncheres, dateFinEncheres, prixInitial, prixVente, noUtilisateur, noCategorie);
		
		try {
			em.insertArticle(article);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/encheres/accueil");
		rd.forward(request, response);
	
	}

}
