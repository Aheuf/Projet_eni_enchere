package fr.eni.enchere.piou.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.enchere.piou.BusinessException;
import fr.eni.enchere.piou.bll.EnchereManager;

/**
 * Servlet implementation class ServletConnexion
 */
@WebServlet("encheres/connexion")
public class ServletConnexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ServletConnexion() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EnchereManager manager = new EnchereManager();
		
		String pseudo;
		String mdp;
		
		try {
			manager.selectAllUtilisateur();
			
			for (Utilisateur u : iterable) {
				
			}
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	
		doGet(request, response);
	}

}
