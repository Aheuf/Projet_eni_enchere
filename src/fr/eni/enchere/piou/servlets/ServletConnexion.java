package fr.eni.enchere.piou.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.enchere.piou.BusinessException;
import fr.eni.enchere.piou.bll.EnchereManager;
import fr.eni.enchere.piou.bo.Utilisateur;

/**
 * Servlet implementation class ServletConnexion
 */
@WebServlet("/encheres/connexion")
public class ServletConnexion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServletConnexion() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		EnchereManager manager = new EnchereManager();

		List<Utilisateur> verification = new ArrayList<>();
		Cookie[] cookies = request.getCookies();

		try {
			String pseudo = request.getParameter("identifiant");
			String mdp = request.getParameter("mdp");
			String testmdp = null;
			verification = manager.selectUtilisateurByMotCle(pseudo);

			if (verification.isEmpty()) {
				System.out.println("rien dedans");
			} else {
				for (Utilisateur u : verification) {
					testmdp = u.getMotDePasse();
				}
				if (mdp.equals(testmdp)) {

					for (Utilisateur u : verification) {
						String id = String.valueOf(u.getNoUtilisateur());
						Cookie unCookie = new Cookie("CookieIDUtilisateur", id);
						unCookie.setMaxAge(300);
						response.addCookie(unCookie);
					}
				} else {
					System.out.println("probleme de mdp");
				}
			}

		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		doGet(request, response);
	}

}
