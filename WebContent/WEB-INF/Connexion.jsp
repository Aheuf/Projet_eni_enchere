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
<title>Eni-Encheres-Connexion</title>
</head>

<body>
	<!--HEADER-->
	<header>
		<nav
			class="navbar navbar-expand-lg navbar-light bg-dark container-fluid">

			<a class="navbar-brand text-light" href="${pageContext.request.contextPath}/encheres/accueil">Eni-Encheres</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarNav"
				aria-controls="navbarNav" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>



			<div class="collapse navbar-collapse justify-content-end"
				id="navbarNav">
				<c:if test="${empty ok}">
					<a class="nav-link text-light text-end"
						href="<%=request.getContextPath()%>/encheres/connexionPage"> <i
						class="bi bi-person"> S'inscrire - Se connecter</i>
					</a>
				</c:if>
			</div>

		</nav>
	</header>

	<section>
		<h1 class="text-center mt-5 mb-5">Connexion</h1>
		<div class="container">
			<div class="d-flex justify-content-around">
				<form method="post" action="../encheres/VerificationConnexion"
					class="row col-md-4 col-sm-6 col-7 g-3">
					<div class="col-md-6 col-sm-12">
						<label for="identifiant">Identifiant(pseudo) :</label>
					</div>
					<div class="col-md-6 col-sm-12">
						<input type="text" id="identifiant" name="Identifiant">
					</div>
					<div class="col-md-6 col-sm-12">
						<label for="mdp">Mot de passe :</label>
					</div>
					<div class="col-md-6 col-sm-12">
						<input type="password" id="mdp" name="MdP">
					</div>
					<div
						class="mt-2 row justify-content-md-end justify-content-sm-center">
						<a href="" class="text-md-end text-sm-center">Mot de passe
							oublié</a>
					</div>
					<div class="col-md-6 col-sm-12">
						<input class="btn btn-outline-dark" type="submit" id="connexion"
							name="connexion" value="Connexion">
					</div>
					<div class="form-check col-md-6 col-sm-12 mt-4">
						<input class="form-check-input" type="checkbox" id="souvenir">
						<label class="form-check-label" for="souvenir"> Se
							souvenir de moi </label>
					</div>

					<div class="col-md-12 col-sm-12 mt-5">
						<a
							href="${pageContext.request.contextPath}/encheres/VerificationConnexion?inscription"
							class="btn btn-dark col-12">Inscrivez-vous</a>
					</div>
				</form>
			</div>
		</div>
	</section>

	<footer class="fixed-bottom bg-dark">
		<p class="text-light text-center">Copyright - ENI ecole</p>
	</footer>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous">
		
	</script>
</body>

</html>