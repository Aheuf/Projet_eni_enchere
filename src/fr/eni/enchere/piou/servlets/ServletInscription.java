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
		List<Utilisateur> listUtilisateur = new ArrayList<>();
		int idUtilisateur = 0;
		List<Utilisateur> verification = new ArrayList<>();
		boolean validationError = false;

		// Recuperation des informations de la jsp inscription
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
			listUtilisateur = manager.selectAllUtilisateur();
			// Vérifie que tous les champs sont remplis
			if (pseudo.trim().equals("") || nom.trim().equals("") || prenom.trim().equals("") || email.trim().equals("")
					|| telephone.trim().equals("") || rue.trim().equals("") || codePostal.trim().equals("")
					|| ville.trim().equals("") || mdp.trim().equals("") || verifMdp.trim().equals("")) {
				request.setAttribute("ErreurSaisiInscrition", "Tous les champs doivent être remplis !");
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/inscription.jsp");
				rd.forward(request, response);
			} else {

				// Vérifie si le pseudo et l'email n'est pas deja existant dans la BDD
				for (Utilisateur u : listUtilisateur) {

					// pseudo identique dans la bdd
					if (u.getPseudo().equals(pseudo)) {
						validationError = true;
						request.setAttribute("ErreurPseudo", "Pseudo deja existant");
						RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/inscription.jsp");
						rd.forward(request, response);
					}
					// email identique dans la bdd
					if (u.getEmail().equals(email)) {
						validationError = true;
						request.setAttribute("ErreurEmail", "Email deja existant");
						RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/inscription.jsp");
						rd.forward(request, response);
					}
				}
				if (validationError == false) {
					// Vérifie si les deux mdp sont similaire
					if (mdp.equals(verifMdp)) {
						System.out.println(mdp);
						Utilisateur newUtilisateur = manager.insertUtilisateur(pseudo, nom, prenom, email, telephone,
								rue, codePostal, ville, mdp, credit);
						request.setAttribute("newUtilisateur", newUtilisateur);
						verification = manager.selectUtilisateurByMotCle(pseudo);

						// recuperation de id créé pour realiser le context de session
						for (Utilisateur u : verification) {
							idUtilisateur = u.getNoUtilisateur();
						}

						// creation du context de session
						HttpSession session = request.getSession();
						session.setAttribute("session", idUtilisateur);

						RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/Accueil.jsp");
						requestDispatcher.forward(request, response);
					} else {
						// mdps ne sont pas similaire
						request.setAttribute("ErreurConfirmMDPInscription",
								"Erreur lors de la confirmation de votre nouveau mot de passe !");
						RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/inscription.jsp");
						rd.forward(request, response);

					}
				}
			}
		} catch (

		BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
