<%@page import="java.time.LocalDate"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="fr.eni.enchere.piou.bo.Utilisateur"%>
<%@page import="java.util.Date"%>
<%@page import="fr.eni.enchere.piou.bo.ArticleVendu"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="fr">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
    <title>Eni-Encheres-affichage-details-enchere</title>
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
            <img SRC="https://assets.rockpapershotgun.com/images/2020/09/Nvidia-RTX-3090-gpu-1212x682.jpg"
                 halt="Bootstrap"
                 class=" img-thumbnail img-fluid">
            <br>
        </div>
        <br>
        <div class="col-sm-12 col-lg-7 shadow">
            <br>

            <div class="text-center">
                <!--varie en fonction du resultat de la vente-->
                <%
                ArticleVendu article =(ArticleVendu) request.getAttribute("article");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date date = new Date();
                Date dateLimite = sdf.parse(sdf.format(article.getDateFinEncheres()));
                if (date.before(dateLimite)){
                %>
                <!--Cas 1: vente en cours-->
                	<h1>Détail vente:</h1>                	
               	<% } else if ((int) request.getAttribute("idUtilisateur") ==  (int) request.getAttribute("dernierEncherisseur")){%>
	            <!--Cas 2 : utilisateur gagne la vente-->
	                <h1>Vous avez remporté la vente</h1>
               	<% } else {%>
               	<!--Cas 3: vente gagnée par un autre utilisateur vous etes le vendeur-->
	                <h1><%=article.getDernierEncherisseur() %> a remporté la vente</h1>     
                <% } %>
            </div>
            <div class="row">
                <p class="col-sm-3 col-lg-3 fw-bold">Nom :</p>
                <p class="col-sm-9 col-lg-9"><%=request.getAttribute("Nom") %></p>
            </div>
            <div class="row">
                <p class="col-sm-3 col-lg-3 fw-bold">Description :</p>
                <p class="col-sm-9 col-lg-9"><%=request.getAttribute("Description") %></p>
            </div>
            <div class="row">
                <p class="col-sm-3 col-lg-3 fw-bold">Catégorie :</p>
                <p class="col-sm-9 col-lg-9"><%=request.getAttribute("Categorie") %></p>
            </div>
            <div class="row">
                <p class="col-sm-3 col-lg-3 fw-bold">Meilleure offre :</p>
                <p class="col-sm-9 col-lg-9"><%=request.getAttribute("MeilleureOffre") %></p>
            </div>
            <div class="row">
                <p class="col-sm-3 col-lg-3 fw-bold">Mise a prix :</p>
                <p class="col-sm-9 col-lg-9"><%=request.getAttribute("MiseAPrix") %></p>
            </div>
            <div class="row">
                <p class="col-sm-3 col-lg-3 fw-bold">Retrait:</p>
                <p class="col-sm-9 col-lg-9"><%=request.getAttribute("Retrait") %></p>
            </div>
            <div class="row">
                <p class="col-sm-3 col-lg-3 fw-bold">Vendeur:</p>
                <p class="col-sm-9 col-lg-9"><%=request.getAttribute("Vendeur") %></p>
            </div>
			<%if(request.getAttribute("valide") != null) {%>
				<%if ((boolean) request.getAttribute("valide")){%>
				<div class="alert alert-success" role="alert">
	  				votre proposition à été accepté, bonne chance
				</div>
				<%} else { %>
				<div class="alert alert-danger" role="alert">
	  				votre mise est inferieur à la dernière enchère, reessayer
				</div>
				<%} %>
			<%} %>
            <div class=" text-center fw-bold">
                <!--varie en fonction du resultat de la vente-->
                <%if ((int) request.getAttribute("idUtilisateur") == 0 
                || (int) request.getAttribute("idUtilisateur") == (int) request.getAttribute("dernierEncherisseur") && !article.getEtatVente().equals("retiré")
                || (int) request.getAttribute("idUtilisateur") == article.getNoUtilisateur() && date.before(dateLimite)
                || (int) request.getAttribute("idUtilisateur") != article.getNoUtilisateur() && date.after(dateLimite) && !article.getEtatVente().equals("retiré")
                ){ %>
                <a href="<%=request.getContextPath()%>/encheres/accueil">
                    <button class="btn btn-secondary btn-lg btn-block mb-5">Retour</button>
                </a>
                <% }else if (date.before(dateLimite)){ %>
                <form class="mb-5" method="get" action="<%=request.getContextPath()%>/encheres/encheres">
                    <label for="input_saisie">Ma proposition : </label>
                    <input id="input_saisie" type="number" name="proposition"/>
                    <button type="submit" class="btn btn-success" name="idarticle" value="<%=article.getNoArticle()%>">Encherir</button>
                </form>
                <% } else if (article.getEtatVente().equals("retiré")){%>
                <p class="mb-1">l'article a été retiré</p>
                <a href="<%=request.getContextPath()%>/encheres/accueil">
                    <button class="btn btn-secondary btn-lg btn-block mb-5">Retour</button>
                </a>
               <%} else { System.out.println(article.getEtatVente());%>
                <a href="<%=request.getContextPath() %>/encheres/retraits?idarticle=${article.noArticle}" class="btn btn-secondary btn-lg btn-block text-danger mb-5">retirer</a>
                <% } %>
            </div>
        </div>
    </div>
    <br>

</section>


<!--FOOTER-->
	<footer class="bg-dark fixed-bottom">
		<p class="text-light text-center">Copyright - ENI ecole</p>
	</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous">
</script>
</body>

</html>