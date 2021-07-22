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
<title>Eni-Encheres-Inscription</title>
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
		<h1 class="text-center mt-5 mb-5">Inscription</h1>
		<div class="container">
			<%
				if (request.getAttribute("ErreurSaisiInscrition") != null) {
			%>
			<div class="alert alert-dark d-flex align-items-center" role="alert">
				<div>
					<i class="bi bi-x-circle"></i> Tous les champs doivent être remplis
				</div>
			</div>
			<%
				}
			%>
			<%
				if (request.getAttribute("ErreurPseudo") != null) {
			%>
			<div class="alert alert-dark d-flex align-items-center" role="alert">
				<div>
					<i class="bi bi-x-circle"></i> Pseudo deja existant
				</div>
			</div>
			<%
				}
			%>
			<%
				if (request.getAttribute("ErreurEmail") != null) {
			%>
			<div class="alert alert-dark d-flex align-items-center" role="alert">
				<div>
					<i class="bi bi-x-circle"></i> Email deja existant.

				</div>
			</div>
			<%
				}
			%>
			<%
				if (request.getAttribute("ErreurConfirmMDPInscription") != null) {
			%>
			<div class="alert alert-dark d-flex align-items-center" role="alert">
				<div>
					<i class="bi bi-x-circle"></i> Les mots de passes doivent être
					identique.
				</div>
			</div>
			<%
				}
			%>
			<form method="post" action="../encheres/inscription"
				class="row col-md-12 col-sm-12 ">

				<div class="row col-sm-12 col-lg-12 ">
					<div class="d-flex mb-3 col-lg-5 col-sm-12">
						<label for="inputPseudo" class="col-3 form-label mt-1 ">Pseudo
							:</label> <input type="text" class="form-control" id="inputPseudo"
							aria-describedby="pseudoHelp" name="Pseudo">
					</div>

					<div class="d-flex mb-3 offset-lg-2 col-lg-5 col-sm-12">
						<label for="inputNom" class="col-3 form-label mt-1">Nom :</label>
						<input type="text" class="form-control" id="inputNom"
							aria-describedby="nomHelp" name="Nom">
					</div>
				</div>

				<div class="row col-sm-12 col-lg-12 ">
					<div class="d-flex mb-3 col-lg-5 col-sm-12">
						<label for="inputPrenom" class="col-3 form-label mt-1 ">Prenom
							:</label> <input type="text" class="form-control" id="inputPrenom"
							aria-describedby="prenomHelp" name="Prenom">
					</div>
					<div class="d-flex mb-3 offset-lg-2 col-lg-5 col-sm-12">
						<label for="inputEmail" class="col-3 form-label mt-1 ">Email
							:</label> <input type="text" class="form-control" id="inputEmail"
							aria-describedby="emailHelp" name="Email">
					</div>
				</div>


				<div class="row col-sm-12 col-lg-12 ">
					<div class="d-flex mb-3 col-lg-5 col-sm-12">
						<label for="inputTelephone" class="col-3 form-label mt-1 ">Telephone
							:</label> <input type="text" class="form-control" id="inputTelephone"
							aria-describedby="telephoneHelp" name="Telephone">
					</div>
					<div class="d-flex mb-3 offset-lg-2 col-lg-5 col-sm-12">
						<label for="inputRue" class="col-3 form-label mt-1">Rue :</label>
						<input type="text" class="form-control" id="inputRue"
							aria-describedby="rueHelp" name="Rue">
					</div>
				</div>

				<div class="row col-sm-12 col-lg-12 ">
					<div class="d-flex mb-3 col-lg-5 col-sm-12">
						<label for="inputCodePostal" class="col-3 form-label mt-1 ">Code
							Postal :</label> <input type="text" class="form-control"
							id="inputCodePostal" aria-describedby="codePostalHelp" name="CdP">
					</div>
					<div class="d-flex mb-3 offset-lg-2 col-lg-5 col-sm-12">
						<label for="inputVille" class="col-3 form-label mt-1 ">Ville
							:</label> <input type="text" class="form-control" id="inputVille"
							aria-describedby="villeHelp" name="Ville">
					</div>
				</div>

				<div class="row col-sm-12 col-lg-12">
					<div class="d-flex mb-3 col-lg-5 col-sm-12">
						<label for="inputMotDePasse" class="col-3 form-label mt-1 ">Mot
							de passe :</label> <input type="password" class="form-control"
							id="inputMotDePasse" aria-describedby="motDePasseHelp" name="MdP">
					</div>
					<div class="d-flex mb-3 offset-lg-2 col-lg-5 col-sm-12">
						<label for="inputConfirmation" class="col-3 form-label mt-1 ">Confirmation
							:</label> <input type="password" class="form-control"
							id="inputConfirmation" aria-describedby="confirmationHelp"
							name="MdP2">
					</div>
				</div>

				<div class="row col-sm-12 col-lg-12">
					<div class="d-flex mb-3 col-lg-5 col-sm-12">
						<label for="inputCredit" class="col-3 form-label mt-1 ">Credit
							:</label> <input type="number" class="form-control" id="inputCredit"
							aria-describedby="creditHelp" value="1" min="1" max="9999"
							name="Credit">
					</div>
				</div>

				<div class="d-flex justify-content-around mb-5">
					<!-- bouton "Enregistrer" -->
					<input type="submit" name="btEnregistrer" value="Enregistrer"
						class="col-md-2 btn btn-lg btn-dark" title="Créer" />
					<!-- bouton "Retour en arriere -->
					<a
						href="${pageContext.request.contextPath}/encheres/accueil?deconnexion"
						class="col-md-2 btn btn-lg btn-outline-dark" title="Annuler">Annuler</a>
				</div>

			</form>
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