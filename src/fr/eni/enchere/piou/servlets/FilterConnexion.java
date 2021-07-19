package fr.eni.enchere.piou.servlets;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class FilterConnexion
 */
@WebFilter("encheres/filterConnexion")
public class FilterConnexion implements Filter {

	public FilterConnexion() {}

	public void destroy() {}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		if (httpRequest.getParameter("accepte") != null || httpRequest.getServletPath().startsWith("/css")
				|| httpRequest.getServletPath().startsWith("/vendor")
				|| httpRequest.getSession().getAttribute("ok") != null) {
			httpRequest.getSession().setAttribute("ok", true);
			chain.doFilter(request, response);

		} else {
			httpRequest.setAttribute("urlCible", httpRequest.getContextPath() + httpRequest.getServletPath());
			RequestDispatcher rd = httpRequest
					.getRequestDispatcher("encheres/connexion");
			rd.forward(httpRequest, httpResponse);
		}
	}

	

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
