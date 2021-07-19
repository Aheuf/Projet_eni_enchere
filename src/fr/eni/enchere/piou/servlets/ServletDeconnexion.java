package fr.eni.enchere.piou.servlets;

import java.io.IOException;

//import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ServletDeconnexion
 */
@WebServlet("/encheres/ServletDeconnexion")
public class ServletDeconnexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     
    public ServletDeconnexion() {
        super();
        // TODO Auto-generated constructor stub
    }
	*/
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		
		// Obtenir un HttpSession concernant cette requète.
		// Si pas de session existante, n'en créé pas une nouvelle
		// (juste un test pour voir si après l'invalidation la session devient null)
		if (session != null) {
		
			session = request.getSession(false);
			//ne doit pas arriver
			response.getWriter().println("Session : " + session);
			break;
			
		} else {
			
			// Invalide la session et enlève tous les attributs la concernant
			session.invalidate();
		}
		
		// Redirection page d'accueil
		response.sendRedirect("/WEB-INF/Accueil.jsp");
		
		/**
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Accueil.jsp");
		rd.forward(request, response);
		*/
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
