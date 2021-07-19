package fr.eni.enchere.piou.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
     * @see HttpServlet#HttpServlet()
     */
    public ServletNouvelArticle() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
