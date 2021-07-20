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
//import javax.servlet.http.HttpSession;

import fr.eni.enchere.piou.BusinessException;
import fr.eni.enchere.piou.bll.EnchereManager;
import fr.eni.enchere.piou.bo.ArticleVendu;
import fr.eni.enchere.piou.bo.Utilisateur;

/**
 * Servlet implementation class ServletVente
 */
@WebServlet("/encheres/ServletVente")
public class ServletVente extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	//	HttpSession session = request.getSession(#);
		
		
		//je recupere l'idUtilisateur
		int idUtilisateur = 0;
		EnchereManager em = new EnchereManager();
		
		Cookie[] cookies = request.getCookies();
		Utilisateur utilisateur = null;
		
		for (Cookie c : cookies) {
			
			if (c.getName().equals("idUtilisateur")) {
				idUtilisateur = Integer.parseInt(c.getValue());
			}
		}
		
		try {
			List<Utilisateur> utilisateurs = em.selectUtilisateurById(idUtilisateur);
			utilisateur = utilisateurs.get(0);
		} catch (BusinessException e) {
			e.printStackTrace();
		}

		//je lis les parametres Retrait de l'adresse utilisateur par défault
		//request.setCharacterEncoding("UTF-8");
		
		request.setAttribute("Rue", utilisateur.getRue());
		request.setAttribute("CodePostal", utilisateur.getCodePostal());
		request.setAttribute("Ville", utilisateur.getVille());
		
		
		/**
		List<Integer> listeCodesErreur = new ArrayList<>();
		
		
		if (listeCodesErreur.size()>0) {
			
			//je renvoie les codes d'erreurs
			request.setAttribute("listeCodeErreur", listeCodesErreur);
		*/	
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/nouvellevente.jsp");
			rd.forward(request, response);
		/**	
		} else {

			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/nouvellevente.jsp");
			rd.forward(request, response);
		*/	
		}

}
