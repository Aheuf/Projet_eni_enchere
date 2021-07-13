<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
        <nav class="navbar navbar-expand-lg navbar-light bg-dark container-fluid">
            <a class="navbar-brand text-light" href="#">Eni-Encheres</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse justify-content-end" id="navbarNav">
                <a class="nav-link text-light text-end" href="#">Enchères</a>
                <a class="nav-link text-light text-end" href="#">Vendre un article</a>
                <a class="nav-link text-light text-end" href="#"><i class="bi bi-person text-primary">Mon profil</i></a>
                <a class="nav-link text-light text-end" href="#"><i class="bi bi-box-arrow-left text-danger"> Déconnexion</i></a>
            </div>
        </nav>
    </header>
<br>
<br>

<section class="container-fluid offset-sm-1 col-sm-11 col-lg-11 ">

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
                <!--Cas 1: vente en cours-->
                <h1>Détail vente:</h1>
                <!--Cas 2 : utilisateur gagne la vente-->
                <h1>Vous avez remporté la vente</h1>
                <!--Cas 3: vente gagnée par un autre utilisateur vous etes le vendeur-->
                <h1>**** a remporté la vente</h1>
            </div>
            <div class="row">
                <p class="col-sm-3 col-lg-3 fw-bold">Nom :</p>
                <p class="col-sm-9 col-lg-9">*********************</p>
            </div>
            <div class="row">
                <p class="col-sm-3 col-lg-3 fw-bold">Description :</p>
                <p class="col-sm-9 col-lg-9">*********************</p>
            </div>
            <div class="row">
                <p class="col-sm-3 col-lg-3 fw-bold">Catégorie :</p>
                <p class="col-sm-9 col-lg-9">*********************</p>
            </div>
            <div class="row">
                <p class="col-sm-3 col-lg-3 fw-bold">Meilleure offre :</p>
                <p class="col-sm-9 col-lg-9">*********************</p>
            </div>
            <div class="row">
                <p class="col-sm-3 col-lg-3 fw-bold">Mise a prix :</p>
                <p class="col-sm-9 col-lg-9">*********************</p>
            </div>
            <div class="row">
                <p class="col-sm-3 col-lg-3 fw-bold">Retrait:</p>
                <p class="col-sm-9 col-lg-9">*********************</p>
            </div>
            <div class="row">
                <p class="col-sm-3 col-lg-3 fw-bold">Vendeur:</p>
                <p class="col-sm-9 col-lg-9">*********************</p>
            </div>


            <div class=" text-center fw-bold">
                <!--varie en fonction du resultat de la vente-->
                <!--Cas 1 : vente en cours-->
                <form method="post" action="../.............">
                    <label for="input_saisie">Ma proposition : </label>
                    <input id="input_saisie" type="number" name="proposition"/>
                    <button type="submit" class="btn btn-success " name="validation">Encherir</button>
                </form>
                <!--Cas 2 : utilisateur gagne la vente-->
                <a href="#">
                    <button class="btn btn-secondary btn-lg btn-block">Retour</button>
                </a>
                <br>
                <!--Cas 3: vente gagnée par un autre utilisateur vous etes le vendeur-->
                <a href="${pageContext.request.contextPath}/../...?retrait" class="badge text-danger" title="Retrait"><i
                        class="btn btn-secondary btn-lg btn-block">Retrait effectué</i></a>

            </div>
        </div>
    </div>


    <br>

</section>


<!--FOOTER-->
<footer class="bg-dark">
    <p class="text-light text-center">Copyright - ENI ecole</p>
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous">
</script>
</body>

</html>