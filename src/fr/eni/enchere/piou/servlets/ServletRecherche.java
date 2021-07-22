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
		String[] categories = request.getParameterValues("ChoixCategorie");
		int categorie = Integer.parseInt(categories[0]);
		String radio = request.getParameter("radiocheck");
		String[] premierCheckBox = null;
		String[] secondeCheckBox = null;
		String[] troisiemeCheckBox = null;
		String pseudo = null;
		request.setAttribute("switchOn", null);
		int idUtilisateur = 0;

		if (session.getAttribute("session") != null) {
			if (radio != null) {
				switch (radio) {
				case "radioAchats":
					premierCheckBox = request.getParameterValues("myCheckAchat1");
					secondeCheckBox = request.getParameterValues("myCheckAchat2");
					troisiemeCheckBox = request.getParameterValues("myCheckAchat3");
					request.setAttribute("switchOn", true);
					System.out.println("test1:je suis passé dans radio achat");
					break;
				case "radioVentes":
					premierCheckBox = request.getParameterValues("myCheckVente1");
					secondeCheckBox = request.getParameterValues("myCheckVente2");
					troisiemeCheckBox = request.getParameterValues("myCheckVente3");
					request.setAttribute("switchOn", "ok");
					System.out.println("test2:je suis passé dans radio vente");

					break;

				default:
					request.setAttribute("switchOn", null);
					break;
				}
			}
		}

		String test = (String) request.getAttribute("switchOn");
		System.out.println("test2-1:" + test);
		System.out.println("test3:" + premierCheckBox);
		System.out.println("test4:" + secondeCheckBox);
		System.out.println("test5:" + troisiemeCheckBox);
		System.out.println("test5+1:" + listeArticleFiltre);
		// MISE EN PLACE DE LA FILTRATION-----------------------------------------

		try {
			// appelle des listes-----------------------------------------
			listeArticle = manager.selectAllArticleVendu();

			if (session.getAttribute("session") != null) {

				idUtilisateur = (int) session.getAttribute("session");

				List<Utilisateur> listPseudo = manager.selectUtilisateurById(idUtilisateur);
				for (Utilisateur u : listPseudo) {
					pseudo = u.getPseudo();
				}
				for (ArticleVendu av : listeArticle) {
					if (av.getNoUtilisateur() == idUtilisateur)
						listeArticleVente.add(av);
				}
			}
			System.out.println("test6:" + idUtilisateur);
			System.out.println("test7:" + pseudo);
			System.out.println("test8:" + listeArticleVente);
			System.out.println("test8-1:" + listeArticleFiltre);
			
			// filtre un mot clef-----------------------------------------
			if (filtre.equals(null)|| filtre != "") {
				listeArticleFiltre = manager.selectArticleVenduByMotCle(filtre);
			}
			System.out.println("test8-2:" + listeArticleFiltre);
			System.out.println("test8-3:" + categorie);
			// filtre categorie-----------------------------------------
			if (categorie > 0) {
				System.out.println("test8-3-1:je suis dans le fitltre categori");

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
			System.out.println("test8-4:" + listeArticleFiltre);
			// filtre sur switch achat/vente-----------------------------------------
			if (request.getAttribute("switchOn") != null) {
				System.out.println("test9:je suis dans le switch de tete");
				switch (radio) {

				// filtre radio achats-----------------------------------------
				case "radioAchats":
					System.out.println("test10:je suis passé dans filtre radio achat");

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
					System.out.println("test10:je suis passé dans filtre radio vente");
					if (premierCheckBox != null) {
						System.out.println("test10-1:dans le premier if");
						if (listeArticleFiltre != null) {
							listeArticleDepartFiltre = listeArticleFiltre;
							listeArticleFiltre = new ArrayList<>();

							for (ArticleVendu av : listeArticleDepartFiltre) {
								String testav= av.getEtatVente();
								System.out.println("test11:"+testav);
								if (av.getEtatVente().equals("en cours")) {
									listeArticleFiltre.add(av);
								}
							}

						} 
						if (listeArticleFiltre== null) {

							listeArticleFiltre = new ArrayList<>();
							for (ArticleVendu av : listeArticleVente) {
								String testav= av.getEtatVente();
								System.out.println("test11:"+testav);
								if (av.getEtatVente().equals("en cours")) {
									listeArticleFiltre.add(av);
								}
							}
						}
					}
					System.out.println("test12:"+listeArticleFiltre);

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

			if (filtre==null && categorie == 0 && premierCheckBox.equals(null) && secondeCheckBox.equals(null)
					&& troisiemeCheckBox.equals(null)) {
				
				
				request.setAttribute("listeArticleFiltre", null);
				
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/encheres/accueil");
				requestDispatcher.forward(request, response);

			} else {
				request.setAttribute("listeArticleFiltre", listeArticleFiltre);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/encheres/accueil");
				requestDispatcher.forward(request, response);
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
