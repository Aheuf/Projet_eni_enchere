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
import fr.eni.enchere.piou.bo.Categorie;
import fr.eni.enchere.piou.bo.Retrait;
import fr.eni.enchere.piou.bo.Utilisateur;

@WebServlet("/encheres/details")
public class ServletDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idArticle = Integer.parseInt(request.getParameter("idArticle"));
		RequestDispatcher rd = null;
		EnchereManager em = new EnchereManager();
		ArticleVendu article = null;
		Categorie categorie = null;
		Utilisateur utilisateur = null;
		Retrait retrait = null;
		
		try {
			List<ArticleVendu> articles = em.selectArticleVenduById(idArticle);
			article = articles.get(0);
			
			List <Categorie> categories = em.selectCategorieById(article.getNoCategorie());
			categorie = categories.get(0);
			
			List<Utilisateur> utilisateurs = em.selectUtilisateurById(article.getNoUtilisateur());
			utilisateur = utilisateurs.get(0);
			
			List<Retrait> retraits = em.selectRetraitById(idArticle);
			retrait = retraits.get(0);
			
		} catch (BusinessException e) {
			System.out.println("GET ServletDetails déconne");
			e.printStackTrace();
		}
		
// définition du cookies transportant l'id de l'article
			Cookie[] cookies = request.getCookies();
			Cookie cookieIdArticle = null;
			if (cookies.length == 0) {
				cookieIdArticle = new Cookie("idArticle",String.valueOf(idArticle));
				cookieIdArticle.setMaxAge(Integer.MAX_VALUE);	
			} else {
				int i = 0;
				for (Cookie c : cookies) {
					i++;
					if (c.getName().equals("idArticle")) {
						c.setValue(String.valueOf(idArticle));
						break;
					} else if (i == cookies.length-1) {
						cookieIdArticle = new Cookie("idArticle",String.valueOf(idArticle));
						cookieIdArticle.setMaxAge(Integer.MAX_VALUE);	
					}
				}
			}
			response.addCookie(cookieIdArticle);
// fin de la gestion du cookie IdArticle
			
			request.setAttribute("Nom", article.getNomArticle());
			request.setAttribute("Description", article.getDescription());
			request.setAttribute("Categorie", categorie.getLibelle());
			request.setAttribute("MeilleureOffre", article.getPrixVente());
			request.setAttribute("MiseAPrix", article.getPrixInitial());
			request.setAttribute("Retrait", retrait.toString()); // adresse de retrait
			request.setAttribute("Vendeur", utilisateur.getNom());

		rd = request.getRequestDispatcher("/WEB-INF/AffichageDetailsEncheres.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idVendeur = Integer.parseInt(request.getParameter("idVendeur"));
		RequestDispatcher rd = null;
		EnchereManager em = new EnchereManager();	
		Cookie[] cookies = request.getCookies();
		int idUtilisateur = 0;
		
		for (Cookie c : cookies) {
			if(c.getName().equals("idUtilisateur")) {
				idUtilisateur = Integer.parseInt(c.getValue());
				break;
			}
		}
		
		if (idVendeur == idUtilisateur) {
			try {
				List<Utilisateur> vendeurs = em.selectUtilisateurById(idVendeur);
				request.setAttribute("utilisateurs", vendeurs);
			} catch (BusinessException e) {
				e.printStackTrace();
				System.out.println("POST ServletDetails déconne");
			}
			rd = request.getRequestDispatcher("/WEB-INF/AffichageProfilAutreUtilisateur.jsp");
		} else {
			rd = request.getRequestDispatcher("/encheres/ModifierProfil");
		}
		rd.forward(request, response);
	}
}