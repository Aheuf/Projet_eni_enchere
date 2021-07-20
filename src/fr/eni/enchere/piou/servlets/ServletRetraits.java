package fr.eni.enchere.piou.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.enchere.piou.BusinessException;
import fr.eni.enchere.piou.bll.EnchereManager;
import fr.eni.enchere.piou.bo.ArticleVendu;

@WebServlet("/encheres/retraits")
public class ServletRetraits extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EnchereManager em = new EnchereManager();
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Accueil.jsp");
		int idArticle = Integer.parseInt(request.getParameter("retrait"));
		ArticleVendu article = null;
		
		try {
			List<ArticleVendu> articles =em.selectArticleVenduById(idArticle);
			article = articles.get(0);
		} catch (BusinessException e) {
			System.out.println("GET servlet retrait qui déconne lors de la récupèration de l'article");
			e.printStackTrace();
		}
		
		article.setEtatVente("retiré");
		
		try {
			em.updateArticle(article);
		} catch (BusinessException e) {
			System.out.println("GET servlet retrait qui déconne lors de l'update de l'article");
			e.printStackTrace();
		}
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
