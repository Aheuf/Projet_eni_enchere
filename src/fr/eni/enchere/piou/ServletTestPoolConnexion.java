package fr.eni.enchere.piou;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.sql.DataSource;

/**
 * Servlet implementation class ServletTestPoolConnexion
 */
@WebServlet("/encheres/ServletTestPoolConnexion")
public class ServletTestPoolConnexion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		try {
			Context context = new InitialContext();
			
			//recherche de la data source
			DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc/pool_cnx");
			
			//demande une connexion. la methode getConnexion met la demande en attente
			//tant qu'il n'y a pas de connexion dispo dans le pool
			Connection cnx = dataSource.getConnection();
			out.print("la connexion est " + (cnx.isClosed()?"fermée":"ouverte")+".");
			
			//libérer la connexion lorsqu'on en a plus besoin
			cnx.close(); //la connexion n'est pas fermée mais remise dans le pool
			
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			out.println("Une erreur est survenue lors de l'utilisation de la BDD" + e.getMessage());
																				//+ e.getMessage() que en prod
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
