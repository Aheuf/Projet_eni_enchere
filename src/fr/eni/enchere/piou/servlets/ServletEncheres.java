package fr.eni.enchere.piou.servlets;

import java.io.IOException;

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

@WebServlet("/encheres/encheres")
public class ServletEncheres extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int proposition = Integer.parseInt(request.getParameter("proposition"));
		EnchereManager em = new EnchereManager();
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/AffichageDetailsEncheres.jsp");
		Cookie[] cookies = request.getCookies();
		int idArticle = 0;
		int idUtilisateur = 0;
// recupération de l'id de l'article concerné
		for (Cookie c : cookies) {
			if (c.getName().equals("idArticle")) {
				idArticle = Integer.parseInt(c.getValue());
			}
		}
// recupération de l'id utilisateur
		for (Cookie c : cookies) {
			if (c.getName().equals("CookieIDUtilisateur")) {
			idUtilisateur = Integer.parseInt(c.getValue());	
			}
		}
// récupération de l'article
		ArticleVendu article = null;

		try {
			article = em.selectArticleVenduById(idArticle).get(0);
		} catch (BusinessException e) {
			System.out.println("POST servletEnchere déconne à la récupération de l'article");
			e.printStackTrace();
		}
// test de validité de l'enchère
		if (article.getPrixVente() < proposition) {
			article.setPrixVente(proposition);
			article.setNoUtilisateur(idUtilisateur);
			try {
				em.updateArticle(article);
			} catch (BusinessException e) {
				System.out.println("POST servletEnchere déconne à l'update de l'article");
				e.printStackTrace();
			}
		} else {
			System.out.println("l'enchère doit être superieure wesh, tu sais pas compter ou bien ?");
		}
		rd.forward(request, response);
	}
}
