<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Connexion</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>

<body>
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

    <section>
        <h1 class="text-center mt-5 mb-5">Nouvelle Vente</h1>
        <div class="container">
            <div class="d-flex justify-content-around">
                <form class="row col-md-6 col-sm-12 col-10 g-3">
                    <div class="col-md-6 col-sm-6 col-6">
                        <label for="article">Article :</label>
                    </div>
                    <div class="col-md-6 col-sm-6 col-6">
                        <input type="text" id="article" name="article"  class="form-control">
                    </div>
                    <div class="col-md-6 col-sm-6 col-6">
                        <label for="description">Description :</label>
                    </div>
                    <div class="col-md-6 col-sm-6 col-6">
                        <input type="text" id="description" name="description" style="height: 100px;"
                            class="form-control">
                    </div>
                    <div class="col-md-6 col-sm-6 col-6">
                        <label for="categorie">Categorie :</label>
                    </div>
                    <div class="col-md-2 col-sm-6 col-6">
                        <select class="form-select">
                            <option selected>Choisir...</option>
                            <option value="1">One</option>
                            <option value="2">Two</option>
                            <option value="3">Three</option>
                        </select>
                    </div>
                    <div class="col-md-6 col-sm-6 col-6">
                        <label for="photo">Photo de l'article :</label>
                    </div>
                    <div class="col-md-6 col-sm-6 col-6">
                        <input type="file" id="photo" name="photo" class="form-control btn btn-outline-dark">
                    </div>
                    <div class="col-md-6 col-sm-6 col-6">
                        <label for="prix">Mise à prix :</label>
                    </div>
                    <div class="col-md-6 col-sm-6 col-6">
                        <input type="number" id="quantity" name="quantity" min="1" max="9999" class="form-control">
                    </div>
                    <div class="col-md-6 col-sm-6 col-6">
                        <label for="debut">Début de l'enchère :</label>
                    </div>
                    <div class="col-md-6 col-sm-6 col-6">
                        <input type="date" id="debut" name="debut" class="form-control">
                    </div>
                    <div class="col-md-6 col-sm-6 col-6">
                        <label for="fin">Fin de l'enchère :</label>
                    </div>
                    <div class="col-md-6 col-sm-6 col-6">
                        <input type="date" id="fin" name="fin" class="form-control">
                    </div>
                    <fieldset id="retrait" class="border border-dark col-12 pb-4 mt-3">
                        <legend>Retrait</legend>
                        <div class="col-md-12 col-sm-12 col-12 d-flex justify-content-between">
                            <label for="rue" class="mt-2 col-3">Rue :</label>
                            <input type="text" id="rue" name="rue" class="form-control">
                        </div>
                        <div class="col-md-12 col-sm-12 col-12 d-flex justify-content-between mt-3">
                            <label for="postal" class="mt-2  col-3">Code postal :</label>
                            <input type="text" id="postal" name="postal" class="form-control">
                        </div>
                        <div class="col-md-12 col-sm-12 col-12 d-flex justify-content-between mt-3">
                            <label for="ville" class="mt-2  col-3">Ville :</label>
                            <input type="text" id="ville" name="ville" class="form-control">
                        </div>
                    </fieldset>

                    <div class="row justify-content-around mt-3 mb-5">
                        <input type="submit" class="btn btn-dark col-5 col-sm-3 col-md-3" id="enregistrer" name="enregistrer"
                            value="Enregistrer">
                        <a href="" class="btn btn-outline-dark col-sm-3 col-md-3 col-5">Annuler</a>
                        <input type="button" class="btn btn-outline-dark col-sm-3 col-md-3 col-5" value="Annuler Vente"/>
                    </div>
                </form>
            </div>
        </div>

    </section>

    <footer class="fixed-bottom bg-dark">
        <p class="text-light text-center">Copyright - ENI ecole</p>
    </footer>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous">
    </script>
</body>

</html>