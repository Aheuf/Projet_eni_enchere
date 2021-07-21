package fr.eni.enchere.piou.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.enchere.piou.BusinessException;
import fr.eni.enchere.piou.bll.EnchereManager;
//import fr.eni.enchere.piou.bo.ArticleVendu;
//import fr.eni.enchere.piou.bo.Categorie;
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
		
		int idUtilisateur = 0;
		EnchereManager em = new EnchereManager();
		Utilisateur utilisateur = null;
		HttpSession session = request.getSession();
		idUtilisateur = 1; //(int) session.getAttribute("session");
		
		
		try {
			List<Utilisateur> utilisateurs = em.selectUtilisateurById(idUtilisateur);
			request.setAttribute("adresseUtilisateur", utilisateurs);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/nouvellevente.jsp");
			rd.forward(request, response);
			
			//utilisateur = utilisateurs.get(0);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			/** COOKIES
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
			*/

		//request.setCharacterEncoding("UTF-8");
		
		//je recupere l'utilisateur
		
		
		
		//request.setAttribute("idVendeur", vendeur.getNoUtilisateur());
		
		//je lis les parametres Retrait de l'adresse utilisateur par défault


				
		/**
		List<Integer> listeCodesErreur = new ArrayList<>();
		
		
		if (listeCodesErreur.size()>0) {
			
			//je renvoie les codes d'erreurs
			request.setAttribute("listeCodeErreur", listeCodesErreur);
		*/	
			
			
		/**	
		} else {

			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/nouvellevente.jsp");
			rd.forward(request, response);
		*/	
		}

}
