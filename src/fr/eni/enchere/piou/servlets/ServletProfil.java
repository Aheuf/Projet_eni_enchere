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

import fr.eni.enchere.piou.BusinessException;
import fr.eni.enchere.piou.bll.EnchereManager;
import fr.eni.enchere.piou.bo.Utilisateur;

/**
 * Servlet implementation class ServletProfil
 */
@WebServlet("/encheres/profil")
public class ServletProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EnchereManager en = new EnchereManager();
		
		//recupere l'utilisateur connecté
		HttpSession session = request.getSession();
		int idUtilisateur = (int) session.getAttribute("session");

		try {
			//cherche dans la liste d'utilisateur 
			List<Utilisateur> users = en.selectUtilisateurById(idUtilisateur);
			Utilisateur user = users.get(0);
			request.setAttribute("user", user);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		System.out.println(idUtilisateur);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/modifierprofil.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
