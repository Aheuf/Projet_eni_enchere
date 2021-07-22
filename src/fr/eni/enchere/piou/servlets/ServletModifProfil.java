package fr.eni.enchere.piou.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.enchere.piou.bo.Utilisateur;
import fr.eni.enchere.piou.BusinessException;
import fr.eni.enchere.piou.bll.EnchereManager;

/**
 * Servlet implementation class ServletModifProfil
 */
@WebServlet("/encheres/modifierprofil")
public class ServletModifProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		EnchereManager en = new EnchereManager();
		Utilisateur user = null;
		HttpSession session = request.getSession();
		session.getAttribute("session");
		int idUtilisateur = (int) session.getAttribute("session");

		try {
			en.deleteUtilisateur(idUtilisateur);
			session.invalidate();
		} catch (BusinessException e) {
			e.printStackTrace();
		}

		this.getServletContext().setAttribute("user", user);
		RequestDispatcher rd = request.getRequestDispatcher("/encheres/accueil");
		rd.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		EnchereManager en = new EnchereManager();
		Utilisateur user = null;
		HttpSession session = request.getSession();
		int idUtilisateur = (int) session.getAttribute("session");

		try {
			List<Utilisateur> users = en.selectUtilisateurById(idUtilisateur);
			user = users.get(0);
		} catch (BusinessException e) {
			e.printStackTrace();
		}

		String pseudo;
		String nom;
		String prenom;
		String email;
		String telephone;
		String rue;
		String codePostal;
		String ville;
		String motDePasse;
		String nouveauMDP;
		String confirmationMDP;
		int credit;

		pseudo = request.getParameter("inputPseudo");
		nom = request.getParameter("inputNom");
		System.out.println("nom " + nom);
		prenom = request.getParameter("inputPrenom");
		System.out.println("prenom " + prenom);
		email = request.getParameter("inputEmail");
		telephone = request.getParameter("inputTelephone");
		rue = request.getParameter("inputRue");
		codePostal = request.getParameter("inputCodePostal");
		ville = request.getParameter("inputVille");
		motDePasse = request.getParameter("inputMotDePasse");
		nouveauMDP = request.getParameter("inputNouveauMotDePasse");
		confirmationMDP = request.getParameter("inputConfirmation");
		credit = user.getCredit();

		if (pseudo.trim().equals("") || nom.trim().equals("") || prenom.trim().equals("") || email.trim().equals("")
				|| telephone.trim().equals("") || rue.trim().equals("") || codePostal.trim().equals("")
				|| ville.trim().equals("") || motDePasse.trim().equals("") || nouveauMDP.trim().equals("")
				|| confirmationMDP.trim().equals("")) {
			request.setAttribute("ErreurSaisi", "Tous les champs doivent être remplis !");
			RequestDispatcher rd = request.getRequestDispatcher("/encheres/profil");
			rd.forward(request, response);
		}

		if (motDePasse.equals(user.getMotDePasse())) {
		} else {
			request.setAttribute("ErreurMDP", "Entrez votre mot de passe actuel !");
			RequestDispatcher rd = request.getRequestDispatcher("/encheres/profil");
			rd.forward(request, response);
		}

		if (nouveauMDP.equals(confirmationMDP)) {
		} else {
			request.setAttribute("ErreurConfirmMDP", "Confirmez votre nouveau mot de passe !");
			RequestDispatcher rd = request.getRequestDispatcher("/encheres/profil");
			rd.forward(request, response);
		}

		if (motDePasse.equals(user.getMotDePasse()) && nouveauMDP.equals(confirmationMDP)) {
			
			Utilisateur userUpdate = new Utilisateur(idUtilisateur, pseudo, nom, prenom, email, telephone, rue,
					codePostal, ville, nouveauMDP, credit);
			System.out.println("coucou ca a créé un utilisateur lol");

			try {
				en.updateUtilisateur(userUpdate);
				System.out.println("coucou c'est passé");
			} catch (BusinessException e) {
				e.printStackTrace();
			}
			
			request.setAttribute("success", "success");
			
			RequestDispatcher rd = request.getRequestDispatcher("/encheres/profil");
			rd.forward(request, response);
		}
	}

}
