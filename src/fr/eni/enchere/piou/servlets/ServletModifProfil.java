package fr.eni.enchere.piou.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
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
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("session");
		int idUtilisateur = utilisateur.getNoUtilisateur();

		try {
			List<Utilisateur> users = en.selectUtilisateurById(idUtilisateur);
			user = users.get(0);
			en.deleteUtilisateur(user.getNoUtilisateur());
			session.invalidate();
		} catch (BusinessException e) {
			e.printStackTrace();
		}

		this.getServletContext().setAttribute("user", user);
		RequestDispatcher rd = request.getRequestDispatcher("/FilterConnexion");
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
		prenom = request.getParameter("inputPrenom");
		email = request.getParameter("inputEmail");
		telephone = request.getParameter("inputTelephone");
		rue = request.getParameter("inputRue");
		codePostal = request.getParameter("inputCodePostal");
		ville = request.getParameter("inputVille");
		motDePasse = request.getParameter("inputMotDePasse");
		nouveauMDP = request.getParameter("inputNouveauMotDePasse");
		confirmationMDP = request.getParameter("inputConfirmation");
		credit = user.getCredit();

		if (pseudo == null || nom == null || prenom == null || email == null || telephone == null || rue == null
				|| codePostal == null || ville == null || motDePasse == null || nouveauMDP == null
				|| confirmationMDP == null) {
			this.getServletContext().setAttribute("ErreurSaisi", "Tous les champs doivent être remplis !");
			RequestDispatcher rd = request.getRequestDispatcher("/encheres/profil");
			rd.forward(request, response);
		}

		Utilisateur userUpdate = new Utilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal, ville,
				nouveauMDP, credit);

		try {
			en.updateUtilisateur(userUpdate);
		} catch (BusinessException e) {
			e.printStackTrace();
		}

		RequestDispatcher rd = request.getRequestDispatcher("/encheres/profil");
		rd.forward(request, response);

	}

}
