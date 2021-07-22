package fr.eni.enchere.piou.servlets;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
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
import fr.eni.enchere.piou.bo.Retrait;

/**
 * Servlet implementation class ServletNouvelArticle
 */
@WebServlet("/encheres/ServletNouvelArticle")
public class ServletNouvelArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/**
		 * COOKIES Cookie[] cookies = request.getCookies(); Utilisateur utilisateur =
		 * null; /// ???
		 * 
		 * for (Cookie c : cookies) { if (c.getName().equals("idUtilisateur")) {
		 * idUtilisateur = Integer.parseInt(c.getValue()); } }
		 * 
		 * try { List<Utilisateur> utilisateurs =
		 * em.selectUtilisateurById(idUtilisateur); utilisateur = utilisateurs.get(0); }
		 * catch (BusinessException e) { e.printStackTrace(); }
		 */

		request.setCharacterEncoding("UTF-8");
		int idUtilisateur = 0;
		HttpSession session = request.getSession();
		idUtilisateur = (int) session.getAttribute("session");

//		System.out.println(idUtilisateur);

		EnchereManager em = new EnchereManager();
		ArticleVendu article = null;
		Retrait retrait = null;

		String nomArticle = null;
		String description = null;
		Date dateDebutEncheres = null;
		Date dateFinEncheres = null;
		int prixInitial = 0;
		int prixVente = 0;
		int noUtilisateur = 0;
		int noCategorie = 0;
		String potentielAcheteur = null;
		String etatVente = null;
		// String photo;

		String rue = null;
		String codePostal = null;
		String ville = null;

		List<ArticleVendu> recuperationInfoArticle = new ArrayList<>();
		int idArticleCree = 0;

		try {

			nomArticle = request.getParameter("article");
			description = request.getParameter("description");
			dateDebutEncheres = Date.valueOf(request.getParameter("debut"));
			dateFinEncheres = Date.valueOf(request.getParameter("fin"));
			prixInitial = Integer.parseInt(request.getParameter("prix"));
			prixVente = prixInitial;
			noUtilisateur = idUtilisateur;
			noCategorie = Integer.parseInt(request.getParameter("categorie"));
			// photo = request.getParameter("photo");
			rue = request.getParameter("rue");
			codePostal = request.getParameter("postal");
			ville = request.getParameter("ville");
//			System.out.println(nomArticle);
//			System.out.println(description);
//			System.out.println(dateDebutEncheres);
//			System.out.println(dateFinEncheres);
//			System.out.println(prixInitial);
//			System.out.println(prixVente);
//			System.out.println(noUtilisateur);
//			System.out.println(noCategorie);
//			System.out.println(rue);
//			System.out.println(codePostal);
//			System.out.println(ville);
			if (nomArticle == null || description == null || noCategorie == 0 || dateDebutEncheres == null
					|| dateFinEncheres == null || prixInitial == 0 || rue == null || codePostal == null
					|| ville == null) {

//				System.out.println("probleme dans ici");
				this.getServletContext().setAttribute("ErreurSaisi", "Tous les champs doivent être remplis !");
				RequestDispatcher rd = request.getRequestDispatcher("/encheres/ServletVente");
				rd.forward(request, response);

			} else {

				article = em.insertArticle(nomArticle, description, dateDebutEncheres, dateFinEncheres, prixInitial,
						prixInitial, noUtilisateur, noCategorie,etatVente, potentielAcheteur);

				recuperationInfoArticle = em.selectArticleVenduByMotCle(nomArticle);

				for (ArticleVendu av : recuperationInfoArticle) {
					idArticleCree = av.getNoArticle();
//					System.out.println(idArticleCree);
				}

				retrait = em.insertRetrait(idArticleCree, rue, codePostal, ville);

				RequestDispatcher rd = request.getRequestDispatcher("/encheres/accueil");
				rd.forward(request, response);
			}

		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}

}
