<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="fr.eni.enchere.piou.bo.Utilisateur"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Mon profil</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">

<title>Modifier un profil</title>
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
				<a class="nav-link text-light text-end" href="#">Enchères</a> <a
					class="nav-link text-light text-end"
					href="${pageContext.request.contextPath}/encheres/ServletVente">Vendre
					un article</a> <a class="nav-link text-light text-end"
					href="${pageContext.request.contextPath}/encheres/profil"><i
					class="bi bi-person text-primary">Mon profil</i></a> <a
					class="nav-link text-light text-end"
					href="${pageContext.request.contextPath}/encheres/accueil?deconnexion"><i
					class="bi bi-box-arrow-left text-danger">Déconnexion</i></a>

			</div>
		</nav>
	</header>

	<section>
		<!-- Ajout Titres "mon profil"-->
		<h1 class="text-center mt-5 mb-5">Mon Profil</h1>
		<%
			Utilisateur user = (Utilisateur) request.getAttribute("user");
		%>
		<!-- formulaire "mon profil" -->
		<div class="container">
			<%
				if (request.getAttribute("success") != null) {
			%>
			<div class="alert alert-dark d-flex align-items-center" role="alert">
				<div>
					<i class="bi bi-check-circle"></i> Modification enregistrée
					!
				</div>
			</div>
			<%
				}
			%>
			<%
				if (request.getAttribute("ErreurSaisi") != null) {
			%>
			<div class="alert alert-dark d-flex align-items-center" role="alert">
				<div>
					<i class="bi bi-x-circle"></i> Tous les champs doivent être remplis
					!
				</div>
			</div>
			<%
				}
			%>
			<div class="d-flex justify-content-around">
				<form method="POST"
					action="<%=request.getContextPath()%>/encheres/modifierprofil"
					class="row col-md-12 col-sm-12 ">
					<div class="row col-sm-12 col-lg-12 ">
						<div class="d-flex mb-3 col-lg-5 col-sm-12">
							<label for="inputPseudo" class="col-3 form-label mt-1 ">Pseudo
								:</label> <input type="text" class="form-control" name="inputPseudo"
								aria-describedby="pseudoHelp" value="<%=user.getPseudo()%>">
						</div>
						<div class="d-flex mb-3 offset-lg-2 col-lg-5 col-sm-12">
							<label for="inputNom" class="col-3 form-label mt-1">Nom :</label>
							<input type="text" class="form-control" name="inputNom"
								aria-describedby="nomHelp" value="<%=user.getNom()%>">
						</div>
					</div>


					<div class="row col-sm-12 col-lg-12 ">
						<div class="d-flex mb-3 col-lg-5 col-sm-12">
							<label for="inputPrenom" class="col-3 form-label mt-1 ">Prenom
								:</label> <input type="text" class="form-control" name="inputPrenom"
								aria-describedby="prenomHelp" value="<%=user.getPrenom()%>">
						</div>
						<div class="d-flex mb-3 offset-lg-2 col-lg-5 col-sm-12">
							<label for="inputEmail" class="col-3 form-label mt-1 ">Email
								:</label> <input type="text" class="form-control" name="inputEmail"
								aria-describedby="emailHelp" value="<%=user.getEmail()%>">
						</div>
					</div>

					<div class="row col-sm-12 col-lg-12 ">
						<div class="d-flex mb-3 col-lg-5 col-sm-12">
							<label for="inputTelephone" class="col-3 form-label mt-1 ">Telephone
								:</label> <input type="text" class="form-control" name="inputTelephone"
								aria-describedby="telephoneHelp"
								value="<%=user.getTelephone()%>">
						</div>
						<div class="d-flex mb-3 offset-lg-2 col-lg-5 col-sm-12">
							<label for="inputRue" class="col-3 form-label mt-1">Rue :</label>
							<input type="text" class="form-control" name="inputRue"
								aria-describedby="rueHelp" value="<%=user.getRue()%>">
						</div>
					</div>

					<div class="row col-sm-12 col-lg-12 ">
						<div class="d-flex mb-3 col-lg-5 col-sm-12">
							<label for="inputCodePostal" class="col-3 form-label mt-1 ">Code
								Postal :</label> <input type="text" class="form-control"
								name="inputCodePostal" aria-describedby="codePostalHelp"
								value="<%=user.getCodePostal()%>">
						</div>
						<div class="d-flex mb-3 offset-lg-2 col-lg-5 col-sm-12">
							<label for="inputVille" class="col-3 form-label mt-1 ">Ville
								:</label> <input type="text" class="form-control" name="inputVille"
								aria-describedby="villeHelp" value="<%=user.getVille()%>">
						</div>
					</div>

					<!-- formulaire "Mot de passe actuel " -->
					<div class="row col-sm-12 col-lg-12">
						<div class="d-flex mb-3 col-lg-5 col-sm-12">
							<label for="inputMotDePasse" class="col-3 form-label mt-1 ">Mot
								de passe actuel :</label> <input type="password" class="form-control"
								name="inputMotDePasse" aria-describedby="motDePasseHelp">
						</div>
					</div>

					<!-- formulaire "Nouveau mot de passe & Confirmation" -->
					<div class="row col-sm-12 col-lg-12">
						<div class="d-flex mb-3 col-lg-5 col-sm-12">
							<label for="inputNouveauMotDePasse"
								class="col-3 form-label mt-1 ">Nouveau mot de passe :</label> <input
								type="password" class="form-control"
								name="inputNouveauMotDePasse" aria-describedby="motDePasseHelp">
						</div>
						<div class="d-flex mb-3 offset-lg-2 col-lg-5 col-sm-12">
							<label for="inputConfirmation" class="col-3 form-label mt-1 ">Confirmation
								:</label> <input type="password" class="form-control"
								name="inputConfirmation" aria-describedby="confirmationHelp">
						</div>
					</div>

					<div class="row col-sm-12 col-lg-12 mt-3 mb-3">
						<div class="d-flex mb-3 col-lg-5 col-sm-12">
							<p>
								Credit : <strong><%=user.getCredit()%></strong>
							</p>
						</div>
					</div>

					<%
						if (request.getAttribute("ErreurMDP") != null) {
					%>
					<div class="alert alert-dark d-flex align-items-center"
						role="alert">
						<div>
							<i class="bi bi-x-circle"></i> Entrez votre mot de passe actuel !
						</div>
					</div>
					<%
						}
					%>
					<%
						if (request.getAttribute("ErreurConfirmMDP") != null) {
					%>
					<div class="alert alert-dark d-flex align-items-center"
						role="alert">
						<div>
							<i class="bi bi-x-circle"></i> Le nouveau mot de passe et la confirmation doivent être identique ! !
						</div>
					</div>
					<%
						}
					%>
					<!-- bouton "Enregistrer / Supprimer mon compte" -->
					<div class="d-flex justify-content-around mb-5">
						<!-- bouton "Enregistrer" -->
						<input type="submit" name="btEnregistrer" value="Enregistrer"
							class="col-md-2 btn btn-lg btn-dark" title="Enregistrer" />
						<!-- bouton "Supprimer mon compte -->
						<a href="<%=request.getContextPath()%>/encheres/modifierprofil"
							class="col-md-2 btn btn-lg btn-outline-dark">Supprimer profil</a>
					</div>

				</form>
			</div>
		</div>
	</section>

	<!--FOOTER-->
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