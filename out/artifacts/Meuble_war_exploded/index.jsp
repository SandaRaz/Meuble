<% 
    String pagePath = request.getParameter("pagePath");
    if(pagePath == null){
        pagePath = "page/GetStarted.jsp";
    }
    
    String rootPath = request.getContextPath();
%>
    
<!DOCTYPE html>
<html lang="fr">
    <head>
        <meta http-equiv="Content-Type" content="text/html" charset="utf-8">
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <title>Gestion et Fabrication de Meuble</title>
        
        <link href="assets/img/favicon.png" rel="icon">
        <link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">
        
        <!-- Vendor CSS Files -->
        <link href="assets/vendor/aos/aos.css" rel="stylesheet">
        <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
        <link href="assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
        <link href="assets/vendor/glightbox/css/glightbox.min.css" rel="stylesheet">
        <link href="assets/vendor/remixicon/remixicon.css" rel="stylesheet">
        <link href="assets/vendor/swiper/swiper-bundle.min.css" rel="stylesheet">

        <!-- Template Main CSS File -->
        <link href="assets/css/styleFlexStart.css" rel="stylesheet">
        <link href="assets/css/styleVesperr.css" rel="stylesheet">
        <link href="assets/css/form.css" rel="stylesheet">
        <link href="assets/css/modele.css" rel="stylesheet">

    </head>
    <body>
        <header id="header" class="fixed-top d-flex align-items-center">
            <div class="container d-flex align-items-center justify-content-between">

              <div class="logo">
                <h1><a href="index.jsp">Meuble</a></h1>
                <!-- Uncomment below if you prefer to use an image logo -->
                <!-- <a href="index.html"><img src="assets/img/logo.png" alt="" class="img-fluid"></a>-->
              </div>

              <nav id="navbar" class="navbar">
                <ul>
                  <li><a class="nav-link scrollto active" href="index.jsp?pagePath=page/GetStarted.jsp">Home</a></li>
                  <li><a class="nav-link scrollto" href="index.jsp?pagePath=page/gestion/MenuGestion.jsp">Gestion</a></li>
                  <li class="dropdown"><a href="#"><span>Menu Rapide</span> <i class="bi bi-chevron-down"></i></a>
                    <ul>
                        <li class="dropdown"><a href="index.jsp?pagePath=page/gestion/matierePremiere/MenuMatiere.jsp"><span>Matiere premiere</span> <i class="bi bi-chevron-right"></i></a>
                            <ul>
                                <li><a href="index.jsp?pagePath=page/gestion/matierePremiere/AjoutMatiere.jsp">Ajout</a></li>
                                <li><a href="index.jsp?pagePath=page/gestion/matierePremiere/ListMatiere.jsp">Liste</a></li>
                                <li><a href="index.jsp?pagePath=page/gestion/matierePremiere/StockageMatiere.jsp">Stockage</a></li>
                            </ul>
                        </li>
                        <li class="dropdown"><a href="index.jsp?pagePath=page/gestion/categorie/MenuCategorie.jsp"><span>Categorie</span> <i class="bi bi-chevron-right"></i></a>
                            <ul>
                                <li><a href="index.jsp?pagePath=page/gestion/categorie/AjoutCategorie.jsp">Ajout</a></li>
                            </ul>
                        </li>
                        <li class="dropdown"><a href="index.jsp?pagePath=page/gestion/style/MenuStyle.jsp"><span>Style</span> <i class="bi bi-chevron-right"></i></a>
                            <ul>
                                <li><a href="index.jsp?pagePath=page/gestion/style/AjoutStyle.jsp">Ajout</a></li>
                                <li><a href="index.jsp?pagePath=page/gestion/style/AjoutMatiereStyle.jsp">Config</a></li>
                            </ul>
                        </li>
                        <li class="dropdown"><a href="index.jsp?pagePath=page/gestion/volume/MenuVolume.jsp"><span>Volume</span> <i class="bi bi-chevron-right"></i></a>
                            <ul>
                                <li><a href="index.jsp?pagePath=page/gestion/volume/AjoutVolume.jsp">Ajout</a></li>
                            </ul>
                        </li>
                        <li class="dropdown">
                            <a href="index.jsp?pagePath=page/gestion/modele/MenuModele.jsp"><span>Modele</span> <i class="bi bi-chevron-right"></i></a>
                            <ul>
                                <li><a href="index.jsp?pagePath=page/gestion/modele/AjoutModele.jsp">Ajout</a></li>
                                <li><a href="index.jsp?pagePath=page/gestion/modele/DeleteModele.jsp">Suppression</a></li>
                                <li><a href="index.jsp?pagePath=page/gestion/modele/ListModele.jsp">Liste</a></li>
                                <li class="dropdown">
                                    <a href="index.jsp?pagePath=page/gestion/modele/MenuRecherche.jsp"><span>Recherche</span> <i class="bi bi-chevron-right"></i></a>
                                    <ul>
                                        <li><a href="index.jsp?pagePath=page/gestion/modele/recherches/RecherchePrix.jsp">Par prix</a></li>
                                        <li><a href="index.jsp?pagePath=page/gestion/modele/recherches/RecherchePrix.jsp">Par benefice</a></li>
                                        <li><a href="index.jsp?pagePath=page/gestion/modele/recherches/RecherchePrix.jsp">Avanc&eacute;</a></li>
                                    </ul>
                                </li>
                                <li><a href="index.jsp?pagePath=page/gestion/modele/Fabrication.jsp">Fabrication</a></li>
                                <li><a href="index.jsp?pagePath=page/gestion/modele/StatVente.jsp">Statistique</a></li>
                            </ul>
                        </li>
                        <li class="dropdown"><a href="index.jsp?pagePath=page/gestion/employe/MenuEmploye.jsp"><span>Employe</span> <i class="bi bi-chevron-right"></i></a>
                            <ul>
                                <li><a href="index.jsp?pagePath=page/gestion/employe/AjoutEmploye.jsp">Ajout</a></li>
                                <li><a href="index.jsp?pagePath=page/gestion/employe/ListeEmploye.jsp">Liste</a></li>
                                <li><a href="index.jsp?pagePath=page/gestion/employe/AjoutPoste.jsp">Nouveau poste</a></li>
                                <li><a href="index.jsp?pagePath=page/gestion/employe/ListePoste.jsp">Liste poste</a></li>
                            </ul>
                        </li>
                        <li class="dropdown"><a href="index.jsp?pagePath=page/gestion/client/MenuClient.jsp"><span>Client</span> <i class="bi bi-chevron-right"></i></a>
                            <ul>
                                <li><a href="index.jsp?pagePath=page/gestion/client/AjoutClient.jsp">Ajout</a></li>
                                <li><a href="index.jsp?pagePath=page/gestion/client/ListeClient.jsp">List</a></li>
                                <li><a href="index.jsp?pagePath=page/gestion/client/AchatClient.jsp">Achat</a></li>
                            </ul>
                        </li>
                        <li class="dropdown"><a href="index.jsp?pagePath=page/gestion/stock/MenuStock.jsp"><span>Stock</span> <i class="bi bi-chevron-right"></i></a>
                            <ul>
                                <li><a href="MvtDeStockGlobale.ListController">Mouvement globale</a></li>
                                <li><a href="index.jsp?pagePath=page/gestion/stock/EtatModele.jsp">Etat</a></li>
                            </ul>
                        </li>
                    </ul>
                  </li>
                </ul>
                <i class="bi bi-list mobile-nav-toggle"></i>
              </nav><!-- .navbar -->

            </div>
        </header><!-- End Header -->
        
    <!-------------------- INCLUDE BODY CONTENT -------------------->
        <%
            RequestDispatcher dispatcher = request.getRequestDispatcher(pagePath);
            dispatcher.include(request, response);
        %>
    <!-------------------------------------------------------------->
    
       
        <footer id="footer">
            <div class="container">
              <div class="row d-flex align-items-center">
                <div class="col-lg-6 text-lg-left text-center">
                  <div class="copyright">
                    &copy; Copyright <strong>Meuble</strong>. All Rights Reserved
                  </div>
                  <div class="credits">
                    <!-- All the links in the footer should remain intact. -->
                    <!-- You can delete the links only if you purchased the pro version. -->
                    <!-- Licensing information: https://bootstrapmade.com/license/ -->
                    <!-- Purchase the pro version with working PHP/AJAX contact form: https://bootstrapmade.com/vesperr-free-bootstrap-template/ -->
                    Designed by <a href="https://bootstrapmade.com/">BootstrapMade</a>
                  </div>
                </div>
                <div class="col-lg-6">
                  <nav class="footer-links text-lg-right text-center pt-2 pt-lg-0">
                    <a href="#">Privacy Policy</a>
                    <a href="#">Terms of Use</a>
                  </nav>
                </div>
              </div>
            </div>
        </footer>

        <canvas id="camemberChart" width="400" height="400"></canvas>
    
        <div class="section-title aos-init aos-animate" data-aos="fade-up">
            <a href="#" class="back-to-top d-flex align-items-center justify-content-center active">
                <i class="bi bi-arrow-up-short"></i>
            </a>
        </div>
        
        <!-- Vendor JS Files -->
        <script src="assets/vendor/purecounter/purecounter_vanilla.js"></script>
        <script src="assets/vendor/aos/aos.js"></script>
        <script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <script src="assets/vendor/glightbox/js/glightbox.min.js"></script>
        <script src="assets/vendor/isotope-layout/isotope.pkgd.min.js"></script>
        <script src="assets/vendor/swiper/swiper-bundle.min.js"></script>
        <script src="assets/vendor/php-email-form/validate.js"></script>

        <!-- Template Main JS File -->
        <script src="assets/jquery/jquery.min.js"></script>
        <script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <script src="assets/js/main.js"></script>
        <script src="assets/js/delete.js"></script>
        <script src="assets/js/request/search.js"></script>

        <script src="assets/js/c3charts/c3.min.js" type="text/javascript"></script>
        <script src="assets/js/c3charts/d3-5.4.0.min.js" type="text/javascript"></script>
        <script src="assets/js/diagramme.js" type="text/javascript"></script>

    </body>
</html>
