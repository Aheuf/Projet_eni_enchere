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
			<img SRC="https://thumbs.dreamstime.com/b/vogelavatar-45383570.jpg"
				class="img-fluid rounded-circle me-2" width="3%" height="auto">
			<br> <a class="navbar-brand text-light"
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
					<a class="nav-link text-light text-end"
						href="${pageContext.request.contextPath}/encheres/accueil">Enchères</a>
					<a class="nav-link text-light text-end"
						href="${pageContext.request.contextPath}/encheres/ServletVente">Vendre
						un article</a>

					<a class="nav-link text-light text-end"
						href="${pageContext.request.contextPath}/encheres/MonProfil"><i
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
	<br>
	<br>
	<section class="container-fluid col-sm-11 col-lg-11 ">

		<div class="row">
			<div class="col-sm-12 col-lg-4">
				<img SRC="https://avatarfiles.alphacoders.com/718/71823.jpg"
					halt="Bootstrap" class="rounded-circle img-thumbnail img-fluid">
			</div>
			<br>
			<div class="col-sm-12 col-lg-7 shadow">
				<div class="text-center">
					<h1><%=request.getAttribute("pseudo")%></h1>
				</div>
				<div class="row">
					<p class="col-sm-3 col-lg-3 fw-bold ">Nom :</p>
					<p class="col-sm-9 col-lg-9"><%=request.getAttribute("nom")%></p>
				</div>
				<div class="row">
					<p class="col-sm-3 col-lg-3 fw-bold">Prénom :</p>
					<p class="col-sm-9 col-lg-9"><%=request.getAttribute("prenom")%></p>
				</div>
				<div class="row">
					<p class="col-sm-3 col-lg-3 fw-bold">Email :</p>
					<p class="col-sm-9 col-lg-9"><%=request.getAttribute("email")%></p>
				</div>
				<div class="row">
					<p class="col-sm-3 col-lg-3 fw-bold">Telephone :</p>
					<p class="col-sm-9 col-lg-9"><%=request.getAttribute("telephone")%></p>
				</div>
				<div class="row">
					<p class="col-sm-3 col-lg-3 fw-bold">Rue :</p>
					<p class="col-sm-9 col-lg-9 "><%=request.getAttribute("rue")%></p>
				</div>
				<div class="row">
					<p class="col-sm-3 col-lg-3 fw-bold">Code postal :</p>
					<p class="col-sm-9 col-lg-9"><%=request.getAttribute("codePostal")%></p>
				</div>
				<div class="row">
					<p class="col-sm-3 col-lg-3 fw-bold">ville:</p>
					<p class="col-sm-9 col-lg-9"><%=request.getAttribute("ville")%></p>
				</div>
			</div>
		</div>


		<br>

	</section>


	<!--FOOTER-->
	<footer class="bg-dark fixed-bottom">
		<p class="text-light text-center">Copyright - ENI ecole</p>
	</footer>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous">
		
	</script>
</body>

</html>