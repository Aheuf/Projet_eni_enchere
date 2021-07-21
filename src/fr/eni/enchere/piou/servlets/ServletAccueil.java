package fr.eni.enchere.piou.servlets;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

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
import fr.eni.enchere.piou.bo.Categorie;
import fr.eni.enchere.piou.bo.Utilisateur;

@WebServlet("/encheres/accueil")
public class ServletAccueil extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		CreationListArticle(request, response);
		CreationListCategorie(request, response);

		if (request.getParameter("deconnexion") != null) {
			HttpSession session = request.getSession();
			session.setAttribute("session", null);
		}

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/Accueil.jsp");
		requestDispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private void CreationListArticle(HttpServletRequest request, HttpServletResponse response) {
		EnchereManager manager = new EnchereManager();
		List<ArticleVendu> listeArticleBrut = null;
		List<ArticleVendu> listeArticleActuelle = new ArrayList<ArticleVendu>();
		List<Utilisateur> listeVendeurArticleActuelle = new ArrayList<Utilisateur>();

		try {
			listeArticleBrut = manager.selectAllArticleVendu();
			listeVendeurArticleActuelle = manager.selectAllUtilisateur();
			for (ArticleVendu av : listeArticleBrut) {
				LocalDate dateFinVente = Instant.ofEpochMilli(av.getDateFinEncheres().getTime())
						.atZone(ZoneId.systemDefault()).toLocalDate();
				if (LocalDate.now().isBefore(dateFinVente)) {
					listeArticleActuelle.add(av);

				}
			}
			request.setAttribute("listeArticleActuelle", listeArticleActuelle);
			request.setAttribute("listeVendeurArticleActuelle", listeVendeurArticleActuelle);

		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void CreationListCategorie(HttpServletRequest request, HttpServletResponse response) {
		EnchereManager manager = new EnchereManager();
		List<Categorie> listeCategorie = new ArrayList<>();

		try {
			listeCategorie = manager.selectAllCategorie();
			request.setAttribute("listeCategorie", listeCategorie);

		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
