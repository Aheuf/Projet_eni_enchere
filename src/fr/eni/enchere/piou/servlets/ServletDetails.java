package fr.eni.enchere.piou.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;

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
		Utilisateur vendeur = null;
		Retrait retrait = null;
		
// gestion de l'affichage de l'article		
		try {
			article = em.selectArticleVenduById(idArticle).get(0);
			categorie = em.selectCategorieById(article.getNoCategorie()).get(0);
			vendeur = em.selectUtilisateurById(article.getNoUtilisateur()).get(0);
			retrait = em.selectRetraitById(idArticle).get(0);
			
		} catch (BusinessException e) {
			System.out.println("GET ServletDetails déconne sur l'article");
			e.printStackTrace();
		}
		request.setAttribute("article", article);
		request.setAttribute("Nom", article.getNomArticle());
		request.setAttribute("Description", article.getDescription());
		request.setAttribute("Categorie", categorie.getLibelle());
		request.setAttribute("MeilleureOffre", article.getPrixVente());
		request.setAttribute("MiseAPrix", article.getPrixInitial());
		request.setAttribute("Retrait", retrait.toString()); // adresse de retrait
		request.setAttribute("Vendeur", vendeur.getNom());
// fin de gestion de l'affichage de l'article

// gestion de l'affichage suivant l'utilisateur (vendeur ou non)	
		HttpSession session = request.getSession();
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("session");
		int idUtilisateur = utilisateur.getNoUtilisateur();

		request.setAttribute("idUtilisateur", idUtilisateur);
		request.setAttribute("idVendeur", vendeur.getNoUtilisateur());
		
		String vainqueur = null;
		try {
			vainqueur = em.selectUtilisateurById(article.getNoUtilisateur()).get(0).getPseudo();
		} catch (BusinessException e) {
			System.out.println("GET ServletDetails déconne sur l'user");
			e.printStackTrace();
		}
		
		request.setAttribute("vainqueur", vainqueur);
//fin de gestion de l'affichage suivant l'utilisateur
		
// création d'un cookie IdArticle
		Cookie[] cookies = request.getCookies();
		Cookie cookieIdArticle = null;
		int i = 0;
		for (Cookie c : cookies) {
			i++;
			if(c.getName().equals("IdArticle")) {
				c.setValue(String.valueOf(idArticle));
				break;
			} else if (i == cookies.length-1) {
				cookieIdArticle = new Cookie("IdArticle", String.valueOf(idArticle));
				response.addCookie(cookieIdArticle);
			}
		}
// fin de la gestion du cookie IdArticle
		
		rd = request.getRequestDispatcher("/WEB-INF/AffichageDetailsEncheres.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idVendeur = Integer.parseInt(request.getParameter("idVendeur"));
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/AffichageProfilAutreUtilisateur.jsp");
		EnchereManager em = new EnchereManager();	
		Cookie[] cookies = request.getCookies();

// recupération de l'id utilisateur		
		HttpSession session = request.getSession();
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("session");
		int idUtilisateur = utilisateur.getNoUtilisateur();
		
// recupération du vendeur
		Utilisateur vendeur = null;
		try {
			vendeur = em.selectUtilisateurById(idVendeur).get(0);
		} catch (BusinessException e) {
			System.out.println("POST ServletDetails déconne sur la récupération du vendeur");
			e.printStackTrace();
		}
		
//redirection en fonction de l'user
		if (idVendeur == idUtilisateur) {
			rd = request.getRequestDispatcher("/encheres/modifierprofil");
		} else {
			request.setAttribute("pseudo", vendeur.getPseudo());
			request.setAttribute("nom", vendeur.getNom());
			request.setAttribute("prenom", vendeur.getPrenom());
			request.setAttribute("email", vendeur.getEmail());
			request.setAttribute("telephone", vendeur.getTelephone());
			request.setAttribute("rue", vendeur.getRue());
			request.setAttribute("codePostal", vendeur.getCodePostal());
			request.setAttribute("ville", vendeur.getVille());
		}
		rd.forward(request, response);
	}
}