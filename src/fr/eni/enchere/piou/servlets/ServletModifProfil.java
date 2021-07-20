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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idUtilisateur = 0;
		EnchereManager en = new EnchereManager();
		Cookie[] cookies = request.getCookies();
		Utilisateur user = null;
		
		for (Cookie c : cookies) {
			if (c.getName().equals("idUtilisateur")) {
				idUtilisateur = Integer.parseInt(c.getValue());
			}
		}
		
		try {
			List<Utilisateur> users = en.selectUtilisateurById(idUtilisateur);
			user = users.get(0);
			en.deleteUtilisateur(user.getNoUtilisateur());
			//session.invalidate();
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/FilterConnexion");
		rd.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idUtilisateur = 0;
		EnchereManager en = new EnchereManager();
		Cookie[] cookies = request.getCookies();
		Utilisateur user = null;
		
		for (Cookie c : cookies) {
			if (c.getName().equals("idUtilisateur")) {
				idUtilisateur = Integer.parseInt(c.getValue());
			}
		}
		
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
		boolean administrateur;
		
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
		administrateur = false;
		
		if (motDePasse.equals(user.getMotDePasse()) && nouveauMDP == confirmationMDP) {
			Utilisateur userUpdate = new Utilisateur(pseudo, nom, prenom, email, telephone,rue, codePostal, ville, nouveauMDP, credit, administrateur);
			try {
				en.updateUtilisateur(userUpdate);
			} catch (BusinessException e) {
				e.printStackTrace();
			}
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/modifierprofil.jsp");
			rd.forward(request, response);
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/modifierprofil.jsp");
			rd.forward(request, response);
		}
		

	}

}
