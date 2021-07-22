package fr.eni.enchere.piou.servlets;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
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
import fr.eni.enchere.piou.bo.ArticleVendu;
import fr.eni.enchere.piou.bo.Categorie;
import fr.eni.enchere.piou.bo.Utilisateur;

/**
 * Servlet implementation class ServletRecherche
 */
@WebServlet("/encheres/recherche")
public class ServletRecherche extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServletRecherche() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CreationListCategorie(request, response);

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/Accueil.jsp");
		requestDispatcher.forward(request, response);

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		EnchereManager manager = new EnchereManager();
		List<ArticleVendu> listeArticle = new ArrayList<>();
		List<ArticleVendu> listeArticleDepartFiltre = new ArrayList<>();
		List<ArticleVendu> listeArticleFiltre = new ArrayList<>();
		List<ArticleVendu> listeArticleVente = new ArrayList<>();

		// RECUPERATION DES INFORMATION JSP---------------------------------------
		String filtre = request.getParameter("Filtre");
		int categorie = Integer.parseInt(request.getParameter("ChoixCategorie"));
		String radio = request.getParameter("radiocheck");
		String[] premierCheckBox = null;
		String[] secondeCheckBox = null;
		String[] troisiemeCheckBox = null;
		String pseudo = null;

		System.out.println(categorie);
		int idUtilisateur = (int) session.getAttribute("session");

		if (session.getAttribute("session") != null) {
			switch (radio) {
			case "radioAchats":
				premierCheckBox = request.getParameterValues("myCheckAchat1");
				secondeCheckBox = request.getParameterValues("myCheckAchat2");
				troisiemeCheckBox = request.getParameterValues("myCheckAchat3");
				break;
			case "radioVentes":
				premierCheckBox = request.getParameterValues("myCheckVente1");
				secondeCheckBox = request.getParameterValues("myCheckVente2");
				troisiemeCheckBox = request.getParameterValues("myCheckVente3");
				break;

			default:
				break;
			}
		}
		// MISE EN PLACE DE LA FILTRATION-----------------------------------------

		try {
			// appelle des listes-----------------------------------------
			listeArticle = manager.selectAllArticleVendu();
			List<Utilisateur> listPseudo = manager.selectUtilisateurById(idUtilisateur);
			for (Utilisateur u : listPseudo) {
				pseudo = u.getPseudo();
			}
			for (ArticleVendu av : listeArticle) {
				if (av.getNoUtilisateur() == idUtilisateur)
					listeArticleVente.add(av);
			}

			// filtre un mot clef-----------------------------------------
			if (filtre != null || filtre != "") {
				listeArticleFiltre = manager.selectArticleVenduByMotCle(filtre);
			}
			// filtre categorie-----------------------------------------
			if (categorie != 0) {
				if (listeArticleFiltre != null) {
					listeArticleDepartFiltre = listeArticleFiltre;
					listeArticleFiltre = new ArrayList<>();

					for (ArticleVendu article : listeArticleDepartFiltre) {
						if (article.getNoCategorie() == categorie) {
							listeArticleFiltre.add(article);
						}
					}
				} else if (listeArticleFiltre == null) {
					listeArticleFiltre = new ArrayList<>();
					for (ArticleVendu article : listeArticle) {
						if (article.getNoCategorie() == categorie) {
							listeArticleFiltre.add(article);
						}
					}
				}
			}
			// filtre sur switch achat/vente-----------------------------------------
			if (session.getAttribute("session") != null) {
				switch (radio) {

				// filtre radio achats-----------------------------------------
				case "radioAchats":
					if (premierCheckBox != null) {
						if (listeArticleFiltre != null) {
							listeArticleDepartFiltre = listeArticleFiltre;
							listeArticleFiltre = new ArrayList<>();

							for (ArticleVendu av : listeArticleDepartFiltre) {
								LocalDate dateFinVente = Instant.ofEpochMilli(av.getDateFinEncheres().getTime())
										.atZone(ZoneId.systemDefault()).toLocalDate();
								if (LocalDate.now().isBefore(dateFinVente)) {
									listeArticleFiltre.add(av);
								}
							}
						} else if (listeArticleFiltre == null) {
							listeArticleFiltre = new ArrayList<>();

							for (ArticleVendu av : listeArticle) {
								LocalDate dateFinVente = Instant.ofEpochMilli(av.getDateFinEncheres().getTime())
										.atZone(ZoneId.systemDefault()).toLocalDate();
								if (LocalDate.now().isBefore(dateFinVente)) {
									listeArticleFiltre.add(av);

								}
							}
						}
					}
					if (secondeCheckBox != null) {

						if (listeArticleFiltre != null) {
							listeArticleDepartFiltre = listeArticleFiltre;
							listeArticleFiltre = new ArrayList<>();
							List<ArticleVendu> listeArticleDeuxiemeFiltre = new ArrayList<>();

							for (ArticleVendu av : listeArticleDepartFiltre) {
								if (av.getDernierEncherisseur().equals(pseudo)) {
									listeArticleDeuxiemeFiltre.add(av);
									for (ArticleVendu as : listeArticleDeuxiemeFiltre) {
										if (as.getEtatVente().equals("en cours")) {
											listeArticleFiltre.add(as);
										}
									}
								}
							}

						} else if (listeArticleFiltre == null) {

							listeArticleFiltre = new ArrayList<>();
							List<ArticleVendu> listeArticleDeuxiemeFiltre = new ArrayList<>();
							for (ArticleVendu av : listeArticle) {
								if (av.getDernierEncherisseur().equals(pseudo)) {
									listeArticleDeuxiemeFiltre.add(av);
									for (ArticleVendu as : listeArticleDeuxiemeFiltre) {
										if (as.getEtatVente().equals("en cours")) {
											listeArticleFiltre.add(as);
										}
									}
								}
							}
						}

					}
					if (troisiemeCheckBox != null) {
						if (listeArticleFiltre != null) {
							listeArticleDepartFiltre = listeArticleFiltre;
							listeArticleFiltre = new ArrayList<>();
							List<ArticleVendu> listeArticleDeuxiemeFiltre = new ArrayList<>();
							List<ArticleVendu> listeArticleTroisiemeFiltre = new ArrayList<>();

							for (ArticleVendu av : listeArticleDepartFiltre) {
								if (av.getDernierEncherisseur().equals(pseudo)) {
									listeArticleDeuxiemeFiltre.add(av);
									for (ArticleVendu as : listeArticleDeuxiemeFiltre) {
										if (as.getEtatVente().equals("retiré")) {
											listeArticleFiltre.add(as);
										}
										if (as.getEtatVente().equals("fini")) {
											listeArticleFiltre.add(as);
										}
									}

								}
							}

						} else if (listeArticleFiltre == null) {

							listeArticleFiltre = new ArrayList<>();
							List<ArticleVendu> listeArticleDeuxiemeFiltre = new ArrayList<>();
							for (ArticleVendu av : listeArticle) {
								if (av.getDernierEncherisseur().equals(pseudo)) {
									listeArticleDeuxiemeFiltre.add(av);
									for (ArticleVendu as : listeArticleDeuxiemeFiltre) {
										if (as.getEtatVente().equals("retiré")) {
											listeArticleFiltre.add(as);
										}
										if (as.getEtatVente().equals("fini")) {
											listeArticleFiltre.add(as);
										}
									}
								}
							}
						}
					}

					break;

				// filtre radio vente-----------------------------------------
				case "radioVentes":
					if (premierCheckBox != null) {

						if (listeArticleFiltre != null) {
							listeArticleDepartFiltre = listeArticleFiltre;
							listeArticleFiltre = new ArrayList<>();

							for (ArticleVendu av : listeArticleDepartFiltre) {
								if (av.getEtatVente().equals("en cours")) {
									listeArticleFiltre.add(av);
								}
							}

						} else if (listeArticleFiltre == null) {

							listeArticleFiltre = new ArrayList<>();
							for (ArticleVendu av : listeArticleVente) {
								if (av.getEtatVente().equals("en cours")) {
									listeArticleFiltre.add(av);
								}
							}
						}
					}

					if (secondeCheckBox != null) {

						if (listeArticleFiltre != null) {
							listeArticleDepartFiltre = listeArticleFiltre;
							listeArticleFiltre = new ArrayList<>();

							for (ArticleVendu av : listeArticleDepartFiltre) {
								if (av.getDernierEncherisseur().equals(null)) {
									listeArticleFiltre.add(av);
								}
							}

						} else if (listeArticleFiltre == null) {

							listeArticleFiltre = new ArrayList<>();
							for (ArticleVendu av : listeArticleVente) {
								if (av.getDernierEncherisseur().equals(null)) {
									listeArticleFiltre.add(av);
								}
							}
						}
					}
					if (troisiemeCheckBox != null) {
						if (listeArticleFiltre != null) {
							listeArticleDepartFiltre = listeArticleFiltre;
							listeArticleFiltre = new ArrayList<>();

							for (ArticleVendu av : listeArticleDepartFiltre) {
								if (av.getEtatVente().equals("retiré")) {
									listeArticleFiltre.add(av);
								}
								if (av.getEtatVente().equals("fini")) {
									listeArticleFiltre.add(av);
								}
							}

						} else if (listeArticleFiltre == null) {

							listeArticleFiltre = new ArrayList<>();
							for (ArticleVendu av : listeArticleVente) {
								if (av.getEtatVente().equals("retiré")) {
									listeArticleFiltre.add(av);
								}
								if (av.getEtatVente().equals("fini")) {
									listeArticleFiltre.add(av);
								}
							}
						}

					}
					break;

				default:
					break;
				}
			}

			if(filtre==null&& categorie==0&& premierCheckBox.equals(null) && secondeCheckBox.equals(null) && troisiemeCheckBox.equals(null)) {
				request.setAttribute("listeArticleFiltre", null);
				doGet(request, response);
			
			}else {
				request.setAttribute("listeArticleFiltre", listeArticleFiltre);
				doGet(request, response);
			}
			
			
		} catch (

		BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

	private void CreationListCategorie(HttpServletRequest request, HttpServletResponse response) {
		EnchereManager manager = new EnchereManager();
		List<Categorie> listeCategorie = new ArrayList<>();

		try {
			listeCategorie = manager.selectAllCategorie();
			request.setAttribute("listeCategorie", listeCategorie);

		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
