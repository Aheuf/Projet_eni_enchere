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



			<a class="navbar-brand text-light" href="#">Eni-Encheres</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarNav"
				aria-controls="navbarNav" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>



			<div class="collapse navbar-collapse justify-content-end"
				id="navbarNav">
				<a class="nav-link text-light text-end" href="#">Enchères</a> <a
					class="nav-link text-light text-end" href="#">Vendre un article</a>
				<c:if test="${!empty ok}">
					<a class="nav-link text-light text-end" href="<%=request.getContextPath()%>/encheres/profil"><i
						class="bi bi-person text-primary">Mon profil</i></a>
					<a class="nav-link text-light text-end" href="#"><i
						class="bi bi-box-arrow-left text-danger">Déconnexion</i></a>
				</c:if>
				<c:if test="${empty ok}">
					<a class="nav-link text-light text-end"
						href="${pageContext.request.contextPath}/encheres/connexion"> <i
						class="bi bi-person"> S'inscrire - Se connecter</i></a>
				</c:if>
			</div>
			
		</nav>
	</header>

	<!--TITRES-->
	<h1 class="text-center mt-2">ENI-Encheres</h1>
	<h2 class="text-center">Liste des enchères</h2>

	<!--CHAMPS-->
	<section>
		<form action="#" class="container">
			<label for="champ_accueil">Filtres :</label> <input
				id="champ_accueil" class="form-control col-lg-6" type="text"
				placeholder="Le nom de l'article contient"
				aria-label="default input example">
			<div class="row mt-2">
				<label class="pt-1" for="categorie_accueil">Catégories :</label> <select
					id="categorie_accueil" class="form-select"
					aria-label="Default select example">
					<option selected>Toutes</option>
					<option value="1">One</option>
					<option value="2">Two</option>
					<option value="3">Three</option>
				</select>
			</div>

			<!--RADIO DE SELECTION-->
			<div class="d-flex justify-content-evenly mt-3">
				<div class="form-check-toggle">
					<input class="form-check-input" type="radio"
						name="flexRadioDefault" id="flexRadioDefault1"> <label
						class="form-check-label" for="flexRadioDefault1"> Achats </label>
				</div>

				<div class="form-check-toggle">
					<input class="form-check-input" type="radio"
						name="flexRadioDefault" id="flexRadioDefault2"> <label
						class="form-check-label" for="flexRadioDefault2"> Mes
						ventes </label>
				</div>
			</div>

			<div class="d-flex justify-content-evenly">
				<!--CHECKBOX ACHAT-->
				<div>
					<div class="form-check">
						<input class="form-check-input" id="achat" type="checkbox"
							value="Encheres_ouverte"> <label class="form-check-label"
							for="flexCheckDefault"> Enchères ouverte</label>
					</div>
					<div class="form-check">
						<input class="form-check-input" type="checkbox"
							value="encheres_en_cours"> <label
							class="form-check-label" for="flexCheckDefault"> Mes
							enchères en cours</label>
					</div>
					<div class="form-check">
						<input class="form-check-input" type="checkbox"
							value="encheres_remportees"> <label
							class="form-check-label" for="flexCheckDefault"> Mes
							enchères remportées</label>
					</div>
				</div>

				<!--CHECKBOX VENTE-->
				<div>
					<div class="form-check">
						<input class="form-check-input" type="checkbox"
							value="ventes_en_cours"> <label class="form-check-label"
							for="flexCheckDefault"> Mes ventes en cours</label>
					</div>
					<div class="form-check">
						<input class="form-check-input" type="checkbox"
							value="ventes_non_debutees"> <label
							class="form-check-label" for="flexCheckDefault"> Mes
							ventes non débutées</label>
					</div>
					<div class="form-check">
						<input class="form-check-input" type="checkbox"
							value="Ventes_terminees"> <label class="form-check-label"
							for="flexCheckDefault"> Ventes terminées</label>
					</div>
				</div>
			</div>


			<!--SUBMIT-->
			<div class="d-flex justify-content-center mt-2">
				<button type="submit" class="btn btn-secondary">Rechercher</button>
			</div>
		</form>
	</section>

	<!--CARDS-->
	<article
		class="row col-sm-12 col-lg-10 mx-auto mt-5 justify-content-center">

		<c:forEach var="a" items="${listeArticleActuelle}">

			<div class="card shadow col-11 col-lg-2 m-1">
				<img class="card-img-top" src=".../100px180/" alt="image produit">
				<div class="card-body">
					<h5 class="card-title">
						<a href="#" class="link-dark">${a.nomArticle}</a>
					</h5>
					<p class="card-text">Prix : ${a.prixVente} points</p>
					<p class="card-text">Fin de l'enchère : ${a.dateFinEncheres}</p>
					<br>

					<c:forEach var="b" items="${listeVendeurArticleActuelle}">
						<c:if test="${b.noUtilisateur==a.noUtilisateur}">
							<p class="card-text">
								Vendeur : <a
									href="${pageContext.request.contextPath}/encheres/profil">${b.pseudo}</a>
							</p>
						</c:if>
					</c:forEach>
				</div>
			</div>
		</c:forEach>


	</article>

	<!--FOOTER-->
	<footer class="bg-dark">
		<p class="text-light text-center">Copyright - ENI ecole</p>
	</footer>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous">
		
	</script>
</body>

</html>