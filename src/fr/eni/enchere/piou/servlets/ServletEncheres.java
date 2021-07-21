package fr.eni.enchere.piou.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.enchere.piou.BusinessException;
import fr.eni.enchere.piou.bll.EnchereManager;
import fr.eni.enchere.piou.bo.ArticleVendu;
import fr.eni.enchere.piou.bo.Utilisateur;

@WebServlet("/encheres/encheres")
public class ServletEncheres extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int proposition = Integer.parseInt(request.getParameter("proposition"));
		HttpSession session = request.getSession();
		EnchereManager em = new EnchereManager();
		RequestDispatcher rd = request.getRequestDispatcher("/encheres/details");

// recupération de l'id de l'article concerné
		int idArticle = (int) session.getAttribute("idArticle");
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
			try {
				Utilisateur utilisateur =  em.selectUtilisateurById((int) session.getAttribute("session")).get(0);
				utilisateur.setCredit(utilisateur.getCredit() - proposition);
				em.updateUtilisateur(utilisateur);
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
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
