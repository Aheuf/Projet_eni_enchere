<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="fr.eni.enchere.piou.bo.Utilisateur" %>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>

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
				<a class="nav-link text-light text-end" href="#"><i
					class="bi bi-person text-primary">Mon profil</i></a> <a
					class="nav-link text-light text-end" href="#"><i
					class="bi bi-box-arrow-left text-danger"> Déconnexion</i></a>
			</div>
		</nav>
	</header>

	<section>
		<!-- Ajout Titres "mon profil"-->
		<h1 class="text-center mt-5 mb-5">Mon Profil</h1>

		<!-- formulaire "mon profil" -->
		<div class="container">
			<div class="d-flex justify-content-around">

				<form method="POST" action="<%=request.getContextPath()%>/modifierprofil" class="row col-md-12 col-sm-12 ">

					<div class="row col-sm-12 col-lg-12 ">
						<div class="d-flex mb-3 col-lg-5 col-sm-12">
							<label for="inputPseudo" class="col-3 form-label mt-1 ">Pseudo
								:</label> <input type="text" class="form-control" id="inputPseudo"
								aria-describedby="pseudoHelp" value="<%= user.getPseudo()%>">
						</div>
						<div class="d-flex mb-3 offset-lg-2 col-lg-5 col-sm-12">
							<label for="inputNom" class="col-3 form-label mt-1">Nom :</label>
							<input type="text" class="form-control" id="inputNom"
								aria-describedby="nomHelp">
						</div>
					</div>

					<div class="row col-sm-12 col-lg-12 ">
						<div class="d-flex mb-3 col-lg-5 col-sm-12">
							<label for="inputPrenom" class="col-3 form-label mt-1 ">Prenom
								:</label> <input type="text" class="form-control" id="inputPrenom"
								aria-describedby="prenomHelp" value="<%= user.getPrenom()%>">
						</div>
						<div class="d-flex mb-3 offset-lg-2 col-lg-5 col-sm-12">
							<label for="inputEmail" class="col-3 form-label mt-1 ">Email
								:</label> <input type="text" class="form-control" id="inputEmail"
								aria-describedby="emailHelp" value="<%= user.getEmail()%>">
						</div>
					</div>

					<div class="row col-sm-12 col-lg-12 ">
						<div class="d-flex mb-3 col-lg-5 col-sm-12">
							<label for="inputTelephone" class="col-3 form-label mt-1 ">Telephone
								:</label> <input type="text" class="form-control" id="inputTelephone"
								aria-describedby="telephoneHelp" value="<%= user.getTelephone()%>">
						</div>
						<div class="d-flex mb-3 offset-lg-2 col-lg-5 col-sm-12">
							<label for="inputRue" class="col-3 form-label mt-1">Rue :</label>
							<input type="text" class="form-control" id="inputRue"
								aria-describedby="rueHelp" value="<%= user.getRue()%>">
						</div>
					</div>

					<div class="row col-sm-12 col-lg-12 ">
						<div class="d-flex mb-3 col-lg-5 col-sm-12">
							<label for="inputCodePostal" class="col-3 form-label mt-1 ">Code
								Postal :</label> <input type="text" class="form-control"
								id="inputCodePostal" aria-describedby="codePostalHelp"
								value="<%= .getCodePostal()%>">
						</div>
						<div class="d-flex mb-3 offset-lg-2 col-lg-5 col-sm-12">
							<label for="inputVille" class="col-3 form-label mt-1 ">Ville
								:</label> <input type="text" class="form-control" id="inputVille"
								aria-describedby="villeHelp" value="<%= user.getVille()%>">
						</div>
					</div>

					<!-- formulaire "Mot de passe actuel " -->
					<div class="row col-sm-12 col-lg-12">
						<div class="d-flex mb-3 col-lg-5 col-sm-12">
							<label for="inputMotDePasse" class="col-3 form-label mt-1 ">Mot
								de passe actuel :</label> <input type="password" class="form-control"
								id="inputMotDePasse" aria-describedby="motDePasseHelp">
						</div>
						<% if (inputMotDePasse != user.getMotDePasse) {%>
							<p style="color: red;">Le mot de passe est erroné !</p>
						<%}%>
					</div>


					<!-- formulaire "Nouveau mot de passe & Confirmation" -->
					<div class="row col-sm-12 col-lg-12">
						<div class="d-flex mb-3 col-lg-5 col-sm-12">
							<label for="inputNouveauMotDePasse"
								class="col-3 form-label mt-1 ">Nouveau mot de passe :</label> <input
								type="password" class="form-control" id="inputNouveauMotDePasse"
								aria-describedby="motDePasseHelp">
						</div>
						<div class="d-flex mb-3 offset-lg-2 col-lg-5 col-sm-12">
							<label for="inputConfirmation" class="col-3 form-label mt-1 ">Confirmation
								:</label> <input type="password" class="form-control"
								id="inputConfirmation" aria-describedby="confirmationHelp">	
						</div>
						<% if (inputNouveauMotDePasse != inputConfirmation) {%>
							<p style="color: red;">Le mot de passe doit être indentique !</p>
						<%}%>						
					</div>

					<div class="row col-sm-12 col-lg-12 mt-3 mb-3">
						<div class="d-flex mb-3 col-lg-5 col-sm-12">
							<p>
								Credit : <strong><%= user.getCredit() %></strong>
							</p>
						</div>
					</div>

					<!-- bouton "Enregistrer / Supprimer mon compte" -->
					<div class="d-flex justify-content-around mb-5">
						<!-- bouton "Enregistrer" -->
						<input type="submit" name="btEnregistrer" value="Enregistrer"
							class="col-md-2 btn btn-lg btn-dark" title="Enregistrer" />
						<!-- bouton "Supprimer mon compte -->
						<a href="<%=request.getContextPath()%>/modifierprofil" class="col-md-2 btn btn-lg btn-outline-dark">Supprimer
							profil</a>
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