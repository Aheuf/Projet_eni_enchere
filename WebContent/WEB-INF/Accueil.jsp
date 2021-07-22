<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="fr">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
<title>Eni-Encheres</title>
</head>

<body>
	<!--HEADER-->
	<header>
		<nav
			class="navbar navbar-expand-lg navbar-light bg-dark container-fluid">

			<a class="navbar-brand text-light"
				href="${pageContext.request.contextPath}/encheres/accueil">Eni-Encheres</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarNav"
				aria-controls="navbarNav" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>


			<div class="collapse navbar-collapse justify-content-end"
				id="navbarNav">

				<!--<c:out value="${ CookieIDUtilisateur }" />-->
				<c:if test="${!empty session}">
					<a class="nav-link text-light text-end" href="#">Enchères</a>
					<a class="nav-link text-light text-end"
						href="${pageContext.request.contextPath}/encheres/ServletVente">Vendre
						un article</a>

					<a class="nav-link text-light text-end"
						href="${pageContext.request.contextPath}/encheres/profil"><i
						class="bi bi-person text-primary">Mon profil</i></a>
					<a class="nav-link text-light text-end"
						href="${pageContext.request.contextPath}/encheres/accueil?deconnexion"><i
						class="bi bi-box-arrow-left text-danger">Déconnexion</i></a>
				</c:if>
				<c:if test="${empty session}">
					<a class="nav-link text-light text-end"
						href="<%=request.getContextPath()%>/encheres/connexionPage"> <i
						class="bi bi-person"> S'inscrire - Se connecter</i>
					</a>
				</c:if>
			</div>

		</nav>
	</header>

	<!--TITRES-->
	<h1 class="text-center mt-2">ENI-Encheres</h1>
	<h2 class="text-center">Liste des enchères</h2>

	<!--CHAMPS-->
	<section class="container">
		<form method="post" action="../encheres/recherche">
			<label for="champ_accueil">Filtres :</label> <input
				id="champ_accueil" class="form-control col-lg-6" type="text"
				name="Filtre"
				placeholder="Le nom de l'article contient (veuillez mettre UN mot clef)"
				aria-label="default input example">
			<div class="row mt-2">
				<label class="pt-1" for="categorie_accueil">Catégories :</label> <select
					id="categorie_accueil" name="ChoixCategorie" class="form-select"
					aria-label="Default select example">
					<option selected value=0>Toutes</option>
					<c:forEach var="c" items="${listeCategorie}">
						<option value="${c.noCategorie}">${c.libelle}</option>
					</c:forEach>

				</select>
			</div>

			<c:if test="${!empty session}">
				<!--RADIO DE SELECTION-->
				<div class="d-flex justify-content-evenly mt-3">
					<div class="form-check-toggle">
						<input class="form-check-input" type="radio"
							onclick="bloqueVente()" name="radiocheck" value="radioAchats"
							id="flexRadioDefault1"> <label class="form-check-label"
							for="flexRadioDefault1"> Achats </label>
					</div>

					<div class="form-check-toggle">
						<input class="form-check-input" type="radio"
							onclick="bloqueAchat()" name="radiocheck" value="radioVentes"
							id="flexRadioDefault2"> <label class="form-check-label"
							for="flexRadioDefault2"> Mes ventes </label>
					</div>
				</div>

				<div class="d-flex justify-content-evenly">
					<!--CHECKBOX ACHAT-->
					<div>
						<div class="form-check">
							<input class="form-check-input" id="myCheckAchat1"
								name="myCheckAchat1" type="checkbox" value="Encheres_ouverte">
							<label class="form-check-label" for="flexCheckDefault">
								Enchères ouverte</label>
						</div>
						<div class="form-check">
							<input class="form-check-input" id="myCheckAchat2"
								name="myCheckAchat2" type="checkbox" value="encheres_en_cours">
							<label class="form-check-label" for="flexCheckDefault">
								Mes enchères en cours</label>
						</div>
						<div class="form-check">
							<input class="form-check-input" id="myCheckAchat3"
								name="myCheckAchat3" type="checkbox" value="encheres_remportees">
							<label class="form-check-label" for="flexCheckDefault">
								Mes enchères remportées</label>
						</div>
					</div>

					<!--CHECKBOX VENTE-->
					<div>
						<div class="form-check">
							<input class="form-check-input" id="myCheckVente1"
								name="myCheckVente1" type="checkbox" value="ventes_en_cours">
							<label class="form-check-label" for="flexCheckDefault">
								Mes ventes en cours</label>
						</div>
						<div class="form-check">
							<input class="form-check-input" id="myCheckVente2"
								name="myCheckVente2" type="checkbox" value="ventes_non_debutees">
							<label class="form-check-label" for="flexCheckDefault">
								Mes ventes non débutées</label>
						</div>
						<div class="form-check">
							<input class="form-check-input" id="myCheckVente3"
								name="myCheckVente3" type="checkbox" value="Ventes_terminees">
							<label class="form-check-label" for="flexCheckDefault">
								Ventes terminées</label>
						</div>
					</div>
				</div>

			</c:if>
			<!--SUBMIT-->
			<div class="d-flex justify-content-center mt-2">
				<button type="submit" class="btn btn-secondary">Rechercher</button>
			</div>
		</form>
	</section>

	<!--CARDS-->
	<article
		class="row col-sm-12 col-lg-10 mx-auto mt-5 justify-content-center">
		<c:if test="${empty listeArticleFiltre}">
			<c:forEach var="a" items="${listeArticleActuelle}">

				<div class="card shadow col-11 col-lg-2 m-1">
					<img class="card-img-top" src=".../100px180/" alt="image produit">
					<div class="card-body">
						<h5 class="card-title">
							<a
								href="<%=request.getContextPath()%>/encheres/details?idarticle=${a.noArticle}"
								class="link-dark">${a.nomArticle}</a>
						</h5>
						<p class="card-text">Prix : ${a.prixVente} points</p>
						<p class="card-text">Fin de l'enchère : ${a.dateFinEncheres}</p>
						<br>

						<c:forEach var="b" items="${listeVendeurArticleActuelle}">
							<c:if test="${b.noUtilisateur==a.noUtilisateur}">
								<c:if test="${!empty session}">
									<form
										action="<%=request.getContextPath()%>/encheres/details?idVendeur=${a.noUtilisateur}"
										method="post">
										<input type="submit" class="btn btn-link" value="${b.pseudo}">
									</form>

								</c:if>
								<c:if test="${empty session}">
									<p class="card-text">Vendeur :${b.pseudo}</p>
								</c:if>
							</c:if>
						</c:forEach>
					</div>
				</div>
			</c:forEach>
		</c:if>
		<c:if test="${!empty listeArticleFiltre}">
			<c:forEach var="a" items="${listeArticleFiltre}">

				<div class="card shadow col-11 col-lg-2 m-1">
					<img class="card-img-top" src=".../100px180/" alt="image produit">
					<div class="card-body">
						<h5 class="card-title">
							<a
								href="<%=request.getContextPath()%>/encheres/details?idarticle=${a.noArticle}"
								class="link-dark">${a.nomArticle}</a>
						</h5>
						<p class="card-text">Prix : ${a.prixVente} points</p>
						<p class="card-text">Fin de l'enchère : ${a.dateFinEncheres}</p>
						<br>

						<c:forEach var="b" items="${listeVendeurArticleActuelle}">
							<c:if test="${b.noUtilisateur==a.noUtilisateur}">
								<c:if test="${!empty session}">
									<form
										action="<%=request.getContextPath()%>/encheres/details?idVendeur=${a.noUtilisateur}"
										method="post">
										<input type="submit" class="btn btn-link" value="${b.pseudo}">
									</form>
									<!--<p class="card-text">
									Vendeur : 
									<a href="${pageContext.request.contextPath}/encheres/profil?idvendeur="${b.noUtilisateur}>${b.pseudo}</a>
								</p>-->
								</c:if>
								<c:if test="${empty session}">
									<p class="card-text">Vendeur :${b.pseudo}</p>
								</c:if>
							</c:if>
						</c:forEach>
					</div>
				</div>
			</c:forEach>
		</c:if>

	</article>

	<!--FOOTER-->
	<footer class="bg-dark fixed-bottom">
		<p class="text-light text-center">Copyright - ENI ecole</p>
	</footer>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous">
		
	</script>
	<script>
		function bloqueVente() {
			document.getElementById("myCheckAchat1").disabled = false;
			document.getElementById("myCheckAchat2").disabled = false;
			document.getElementById("myCheckAchat3").disabled = false;
			document.getElementById("myCheckVente1").disabled = true;
			document.getElementById("myCheckVente2").disabled = true;
			document.getElementById("myCheckVente3").disabled = true;
		}

		function bloqueAchat() {
			document.getElementById("myCheckAchat1").disabled = true;
			document.getElementById("myCheckAchat2").disabled = true;
			document.getElementById("myCheckAchat3").disabled = true;
			document.getElementById("myCheckVente1").disabled = false;
			document.getElementById("myCheckVente2").disabled = false;
			document.getElementById("myCheckVente3").disabled = false;

		}
	</script>

</body>

</html>