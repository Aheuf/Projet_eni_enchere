package fr.eni.enchere.piou.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.enchere.piou.BusinessException;
import fr.eni.enchere.piou.bll.EnchereManager;
import fr.eni.enchere.piou.bo.Utilisateur;

@WebServlet("/encheres/VerificationConnexion")
public class ServletVerificationConnexion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServletVerificationConnexion() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// recuperation et validation d'un lien parametré pour envoyer sur la jsp
		// inscription
		if (request.getParameter("inscription") != null) {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/inscription.jsp");
			requestDispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		EnchereManager manager = new EnchereManager();

		List<Utilisateur> verification = new ArrayList<>();
		Cookie[] cookies = request.getCookies();

		try {
			String pseudo = request.getParameter("Identifiant");
			String mdp = request.getParameter("MdP");
			String testmdp = null;
			int idUtilisateur = 0;
			verification = manager.selectUtilisateurByMotCle(pseudo);

			// Verifie si le pseudo est existant dans la bdd
			if (verification.isEmpty()) {
				// le pseudo ne renvoie aucune information donc creation d'une erreur
				request.setAttribute("ErreurLoginPseudo",
						"Pseudo inconnu veuillez saisir le bon identifiant ou creer un nouveau profil");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/encheres/connexionPage");
				requestDispatcher.forward(request, response);
				System.out.println("rien dedans");

			} else {
				// recuperation des inforamtion de la bdd pour verifier la saisie
				for (Utilisateur u : verification) {
					testmdp = u.getMotDePasse();
					idUtilisateur = u.getNoUtilisateur();
				}
				// comparaison mdp et mdp de la bdd
				if (mdp.equals(testmdp)) {
					HttpSession session = request.getSession();
					session.setAttribute("session", idUtilisateur);

					RequestDispatcher requestDispatcher = request.getRequestDispatcher("/encheres/accueil");
					requestDispatcher.forward(request, response);

				} else {
					// creation d'une erreur car saisie erroné
					request.setAttribute("ErreurLoginMDP", "Mot de passe erroné");
					RequestDispatcher rd = request.getRequestDispatcher("/encheres/connexionPage");
					rd.forward(request, response);

				}
			}

		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		;
	}

}
