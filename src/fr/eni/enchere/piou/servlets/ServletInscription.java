package fr.eni.enchere.piou.servlets;

import java.io.IOException;
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
import fr.eni.enchere.piou.bo.Utilisateur;

/**
 * Servlet implementation class ServletInscription
 */
@WebServlet("/encheres/inscription")
public class ServletInscription extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServletInscription() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		EnchereManager manager = new EnchereManager();
		int idUtilisateur = 0;

		List<Utilisateur> verification = new ArrayList<>();

		// Cookie[] cookies = request.getCookies();

		String pseudo = request.getParameter("Pseudo");
		String nom = request.getParameter("Nom");
		String prenom = request.getParameter("Prenom");
		String email = request.getParameter("Email");
		String telephone = request.getParameter("Telephone");
		String rue = request.getParameter("Rue");
		String codePostal = request.getParameter("CdP");
		String ville = request.getParameter("Ville");
		String mdp = request.getParameter("MdP");
		String verifMdp = request.getParameter("MdP2");
		int credit = Integer.parseInt(request.getParameter("Credit"));

		try {

			if (mdp.equals(verifMdp)) {
				System.out.println(mdp);
				Utilisateur newUtilisateur = manager.insertUtilisateur(pseudo, nom, prenom, email, telephone, rue,
						codePostal, ville, mdp, credit, false);
				request.setAttribute("newUtilisateur", newUtilisateur);
				verification = manager.selectUtilisateurByMotCle(pseudo);

				for (Utilisateur u : verification) {
					idUtilisateur = u.getNoUtilisateur();
				}

				HttpSession session = request.getSession();
				session.setAttribute("session", idUtilisateur);

				/*
				 * for (Utilisateur u : verification) { String id =
				 * String.valueOf(u.getNoUtilisateur()); System.out.println(id);
				 * 
				 * Cookie unCookie = new Cookie("CookieIDUtilisateur", id);
				 * unCookie.setMaxAge(60); response.addCookie(unCookie);
				 * System.out.println(unCookie.getMaxAge()); }
				 */

				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/Accueil.jsp");
				requestDispatcher.forward(request, response);
			} else {

				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/inscription.jsp");
				requestDispatcher.forward(request, response);

			}
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
