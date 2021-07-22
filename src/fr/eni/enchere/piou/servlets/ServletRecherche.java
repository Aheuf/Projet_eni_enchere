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

		// recuperation des catégories dans la bdd
		CreationListCategorie(request, response);

		// renvoie sur la jsp accueil
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/Accueil.jsp");
		requestDispatcher.forward(request, response);

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		EnchereManager manager = new EnchereManager();
		request.setAttribute("listeArticleFiltre", null);

		List<ArticleVendu> listeArticle = new ArrayList<ArticleVendu>();
		List<ArticleVendu> listeArticleDepartFiltre = new ArrayList<ArticleVendu>();
		List<ArticleVendu> listeArticleFiltre = new ArrayList<ArticleVendu>();
		List<ArticleVendu> listeArticleVente = new ArrayList<ArticleVendu>();

		// RECUPERATION DES INFORMATION JSP---------------------------------------
		String filtre = request.getParameter("Filtre");
		System.out.println("filtre: " + filtre);
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
					request.setAttribute("switchOn", "ok");
					break;
				case "radioVentes":
					premierCheckBox = request.getParameterValues("myCheckVente1");
					secondeCheckBox = request.getParameterValues("myCheckVente2");
					troisiemeCheckBox = request.getParameterValues("myCheckVente3");
					request.setAttribute("switchOn", "ok");

					break;

				default:
					request.setAttribute("switchOn", null);
					break;
				}
			}
		}

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
					if (av.getNoUtilisateur() == idUtilisateur) {
						listeArticleVente.add(av);
					}
				}
			}
			System.out.println(listeArticleFiltre);
			// filtre un mot clef-----------------------------------------

			if (filtre.length() > 1 || filtre != "") {
				listeArticleFiltre = new ArrayList<ArticleVendu>();
				listeArticleFiltre = manager.selectArticleVenduByMotCle(filtre);
				System.out.println("valeur filtre " + listeArticleFiltre);
				System.out.println("je suis passé dans le filtre");
			}

			// filtre categorie-----------------------------------------
			if (categorie != 0) {
				System.out.println("je suis passé dans categorie"+categorie);
				System.out.println(listeArticleFiltre);

				if (listeArticleFiltre != null) {
					listeArticleDepartFiltre = new ArrayList<ArticleVendu>();
					listeArticleDepartFiltre = listeArticleFiltre;
					listeArticleFiltre = new ArrayList<ArticleVendu>();

					for (ArticleVendu article : listeArticleDepartFiltre) {
						if (article.getNoCategorie() == categorie) {
							listeArticleFiltre.add(article);
						}
					}
				}
				if(listeArticleFiltre.isEmpty()){
					listeArticleFiltre = new ArrayList<ArticleVendu>();
					System.out.println("je suis la");
					for (ArticleVendu article : listeArticle) {
						int testav = article.getNoCategorie();
						System.out.println("test8-3-3:" + testav);
						if (article.getNoCategorie() == categorie) {
							listeArticleFiltre.add(article);
						}
					}

				}
			}

			// filtre sur switch achat/vente-----------------------------------------
			if (request.getAttribute("switchOn") != null) {
				switch (radio) {

				// filtre radio achats-----------------------------------------
				case "radioAchats":

					if ((premierCheckBox != null && secondeCheckBox != null && troisiemeCheckBox != null)
							|| (premierCheckBox != null && troisiemeCheckBox != null)) {

						if (listeArticleFiltre != null) {
							listeArticleDepartFiltre = new ArrayList<ArticleVendu>();
							listeArticleDepartFiltre = listeArticleFiltre;
							listeArticleFiltre = new ArrayList<ArticleVendu>();

							for (ArticleVendu av : listeArticleDepartFiltre) {
								LocalDate dateFinVente = Instant.ofEpochMilli(av.getDateFinEncheres().getTime())
										.atZone(ZoneId.systemDefault()).toLocalDate();
								if (LocalDate.now().isBefore(dateFinVente)
										|| av.getDernierEncherisseur().equals(pseudo)
												&& av.getEtatVente().equals("retiré")
										|| av.getEtatVente().equals("fini")
												&& av.getDernierEncherisseur().equals(pseudo)) {
									listeArticleFiltre.add(av);
								}
							}
						} else {
							listeArticleFiltre = new ArrayList<>();

							for (ArticleVendu av : listeArticle) {
								LocalDate dateFinVente = Instant.ofEpochMilli(av.getDateFinEncheres().getTime())
										.atZone(ZoneId.systemDefault()).toLocalDate();
								if (LocalDate.now().isBefore(dateFinVente)
										|| av.getDernierEncherisseur().equals(pseudo)
												&& av.getEtatVente().equals("retiré")
										|| av.getEtatVente().equals("fini")
												&& av.getDernierEncherisseur().equals(pseudo)) {
									listeArticleFiltre.add(av);

								}
							}
						}

					} else {
						if (premierCheckBox != null && secondeCheckBox != null) {

							if (listeArticleFiltre != null) {
								listeArticleDepartFiltre = new ArrayList<ArticleVendu>();
								listeArticleDepartFiltre = listeArticleFiltre;
								listeArticleFiltre = new ArrayList<ArticleVendu>();

								for (ArticleVendu av : listeArticleDepartFiltre) {
									LocalDate dateFinVente = Instant.ofEpochMilli(av.getDateFinEncheres().getTime())
											.atZone(ZoneId.systemDefault()).toLocalDate();
									if (LocalDate.now().isBefore(dateFinVente)
											|| av.getDernierEncherisseur().equals(pseudo)
													&& av.getEtatVente().equals("en cours")) {
										listeArticleFiltre.add(av);
									}
								}
							} else {
								listeArticleFiltre = new ArrayList<>();

								for (ArticleVendu av : listeArticle) {
									LocalDate dateFinVente = Instant.ofEpochMilli(av.getDateFinEncheres().getTime())
											.atZone(ZoneId.systemDefault()).toLocalDate();
									if (LocalDate.now().isBefore(dateFinVente)
											|| av.getDernierEncherisseur().equals(pseudo)
													&& av.getEtatVente().equals("en cours")) {
										listeArticleFiltre.add(av);
									}
								}
							}

						} else {
							if (secondeCheckBox != null && troisiemeCheckBox != null) {

								if (listeArticleFiltre != null) {
									listeArticleDepartFiltre = new ArrayList<ArticleVendu>();
									listeArticleDepartFiltre = listeArticleFiltre;
									listeArticleFiltre = new ArrayList<>();

									for (ArticleVendu av : listeArticleDepartFiltre) {
										if (av.getDernierEncherisseur().equals(pseudo)
												&& av.getEtatVente().equals("en cours")
												|| av.getDernierEncherisseur().equals(pseudo)
														&& av.getEtatVente().equals("retiré")
												|| av.getEtatVente().equals("fini")
														&& av.getDernierEncherisseur().equals(pseudo)) {
											listeArticleFiltre.add(av);
										}
									}

								} else {

									listeArticleFiltre = new ArrayList<>();
									for (ArticleVendu av : listeArticle) {
										if (av.getDernierEncherisseur().equals(pseudo)
												&& av.getEtatVente().equals("en cours")
												|| av.getDernierEncherisseur().equals(pseudo)
														&& av.getEtatVente().equals("retiré")
												|| av.getEtatVente().equals("fini")
														&& av.getDernierEncherisseur().equals(pseudo)) {
											listeArticleFiltre.add(av);
										}
									}
								}

							} else {
								if (premierCheckBox != null || (premierCheckBox != null && secondeCheckBox != null)) {
									if (listeArticleFiltre != null) {
										listeArticleDepartFiltre = new ArrayList<ArticleVendu>();
										listeArticleDepartFiltre = listeArticleFiltre;
										listeArticleFiltre = new ArrayList<ArticleVendu>();

										for (ArticleVendu av : listeArticleDepartFiltre) {
											LocalDate dateFinVente = Instant
													.ofEpochMilli(av.getDateFinEncheres().getTime())
													.atZone(ZoneId.systemDefault()).toLocalDate();
											if (LocalDate.now().isBefore(dateFinVente)) {
												listeArticleFiltre.add(av);
											}
										}
									}
									if (listeArticleFiltre.isEmpty()) {
										listeArticleFiltre = new ArrayList<>();

										for (ArticleVendu av : listeArticle) {
											LocalDate dateFinVente = Instant
													.ofEpochMilli(av.getDateFinEncheres().getTime())
													.atZone(ZoneId.systemDefault()).toLocalDate();
											if (LocalDate.now().isBefore(dateFinVente)) {
												listeArticleFiltre.add(av);

											}
										}
									}
								}
								if (secondeCheckBox != null) {

									if (listeArticleFiltre != null) {
										listeArticleDepartFiltre = new ArrayList<ArticleVendu>();
										listeArticleDepartFiltre = listeArticleFiltre;
										listeArticleFiltre = new ArrayList<>();

										for (ArticleVendu av : listeArticleDepartFiltre) {
											if (av.getDernierEncherisseur().equals(pseudo)
													&& av.getEtatVente().equals("en cours")) {
												listeArticleFiltre.add(av);

											}
										}

									} else {

										listeArticleFiltre = new ArrayList<>();
										for (ArticleVendu av : listeArticle) {
											if (av.getDernierEncherisseur().equals(pseudo)
													&& av.getEtatVente().equals("en cours")) {
												listeArticleFiltre.add(av);

											}
										}
									}

								}
								if (troisiemeCheckBox != null) {
									if (listeArticleFiltre != null) {
										listeArticleDepartFiltre = new ArrayList<ArticleVendu>();
										listeArticleDepartFiltre = listeArticleFiltre;
										listeArticleFiltre = new ArrayList<>();

										for (ArticleVendu av : listeArticleDepartFiltre) {
											if (av.getDernierEncherisseur().equals(pseudo)
													&& av.getEtatVente().equals("retiré")
													|| av.getEtatVente().equals("fini")
															&& av.getDernierEncherisseur().equals(pseudo)) {
												listeArticleFiltre.add(av);

											}

										}
									}

								} else {

									listeArticleFiltre = new ArrayList<>();
									for (ArticleVendu av : listeArticle) {
										if (av.getDernierEncherisseur().equals(pseudo)
												&& av.getEtatVente().equals("retiré")
												|| av.getEtatVente().equals("fini")
														&& av.getDernierEncherisseur().equals(pseudo)) {
											listeArticleFiltre.add(av);
										}
									}

								}
							}
						}
					}

					break;
				// filtre radio vente-----------------------------------------
				case "radioVentes":
					if ((troisiemeCheckBox != null && secondeCheckBox != null && premierCheckBox != null)
							|| (troisiemeCheckBox != null && premierCheckBox != null)) {
						if (listeArticleFiltre != null) {
							listeArticleDepartFiltre = new ArrayList<ArticleVendu>();
							listeArticleDepartFiltre = listeArticleFiltre;
							listeArticleFiltre = new ArrayList<>();

							for (ArticleVendu av : listeArticleDepartFiltre) {
								if (av.getNoUtilisateur() == idUtilisateur) {
									listeArticleFiltre.add(av);
								}
							}
						} else {
							listeArticleFiltre = new ArrayList<>();
							for (ArticleVendu av : listeArticleVente) {
								if (av.getNoUtilisateur() == idUtilisateur) {
									listeArticleFiltre.add(av);
								}
							}
						}
					} else {
						if (troisiemeCheckBox != null && secondeCheckBox != null) {
							if (listeArticleFiltre != null) {
								listeArticleDepartFiltre = new ArrayList<ArticleVendu>();
								listeArticleDepartFiltre = listeArticleFiltre;
								listeArticleFiltre = new ArrayList<>();

								for (ArticleVendu av : listeArticleDepartFiltre) {
									int valeurDeDepart = av.getPrixVente();
									int valeurInitiale = av.getPrixInitial();
									if (valeurDeDepart == valeurInitiale) {
										if (av.getNoUtilisateur() == idUtilisateur) {
											listeArticleFiltre.add(av);
										}
										if (av.getEtatVente().equals("retiré") || av.getEtatVente().equals("fini")) {
											if (av.getNoUtilisateur() == idUtilisateur) {
												listeArticleFiltre.add(av);
											}
										}
									}
								}

							} else {
								listeArticleFiltre = new ArrayList<>();
								for (ArticleVendu av : listeArticleVente) {
									int valeurDeDepart = av.getPrixVente();
									int valeurInitiale = av.getPrixInitial();
									if (valeurDeDepart == valeurInitiale) {
										if (av.getNoUtilisateur() == idUtilisateur) {
											listeArticleFiltre.add(av);
										}
										if (av.getEtatVente().equals("retiré") || av.getEtatVente().equals("fini")) {
											if (av.getNoUtilisateur() == idUtilisateur) {
												listeArticleFiltre.add(av);
											}
										}

									}
								}
							}

						} else {

							System.out.println("test10:je suis passé dans filtre radio vente");
							if (premierCheckBox != null || (secondeCheckBox != null && premierCheckBox != null)) {
								System.out.println("test10-1:dans le premier if");
								if (listeArticleFiltre != null) {
									listeArticleDepartFiltre = new ArrayList<ArticleVendu>();
									listeArticleDepartFiltre = listeArticleFiltre;
									listeArticleFiltre = new ArrayList<>();

									for (ArticleVendu av : listeArticleDepartFiltre) {
										if (av.getEtatVente().equals("en cours")) {
											if (av.getNoUtilisateur() == idUtilisateur) {
												listeArticleFiltre.add(av);
											}
										}
									}

								} else {

									listeArticleFiltre = new ArrayList<>();
									for (ArticleVendu av : listeArticleVente) {

										if (av.getEtatVente().equals("en cours")) {
											if (av.getNoUtilisateur() == idUtilisateur) {
												listeArticleFiltre.add(av);
											}
										}
									}
								}
							}
							System.out.println("test12:" + listeArticleFiltre);

							if (secondeCheckBox != null) {

								if (listeArticleFiltre != null) {
									listeArticleDepartFiltre = new ArrayList<ArticleVendu>();
									listeArticleDepartFiltre = listeArticleFiltre;
									listeArticleFiltre = new ArrayList<>();

									for (ArticleVendu av : listeArticleDepartFiltre) {
										int valeurDeDepart = av.getPrixVente();
										int valeurInitiale = av.getPrixInitial();
										if (valeurDeDepart == valeurInitiale) {
											if (av.getNoUtilisateur() == idUtilisateur) {
												listeArticleFiltre.add(av);
											}
										}
									}

								} else {

									listeArticleFiltre = new ArrayList<>();
									for (ArticleVendu av : listeArticleVente) {
										int valeurDeDepart = av.getPrixVente();
										int valeurInitiale = av.getPrixInitial();
										if (valeurDeDepart == valeurInitiale) {
											if (av.getNoUtilisateur() == idUtilisateur) {
												listeArticleFiltre.add(av);
											}
										}
									}
								}
							}
							if (troisiemeCheckBox != null) {
								if (listeArticleFiltre != null) {
									listeArticleDepartFiltre = new ArrayList<ArticleVendu>();
									listeArticleDepartFiltre = listeArticleFiltre;
									listeArticleFiltre = new ArrayList<>();

									for (ArticleVendu av : listeArticleDepartFiltre) {
										if (av.getEtatVente().equals("retiré") || av.getEtatVente().equals("fini")) {
											if (av.getNoUtilisateur() == idUtilisateur) {
												listeArticleFiltre.add(av);
											}
										}
									}

								} else {

									listeArticleFiltre = new ArrayList<>();
									for (ArticleVendu av : listeArticleVente) {
										if (av.getEtatVente().equals("retiré") || av.getEtatVente().equals("fini")) {
											if (av.getNoUtilisateur() == idUtilisateur) {
												listeArticleFiltre.add(av);
											}
										}
									}
								}
							}
						}
					}

					break;

				default:
					break;
				}
			}
			System.out.println(filtre);
			System.out.println(categorie);
			System.out.println(premierCheckBox);
			System.out.println(secondeCheckBox);
			System.out.println(troisiemeCheckBox);

			if (filtre.equals(null) && categorie == 0 && premierCheckBox.equals(null) && secondeCheckBox.equals(null)
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
