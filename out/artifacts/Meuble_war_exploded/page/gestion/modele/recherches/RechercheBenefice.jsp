<%--
    Document   : RechercheModele
    Created on : 9 janv. 2024, 14:31:37
    Author     : allan
--%>
<%@ page import="java.util.List" %>
<%@ page import="entityView.ModeleComplet" %>

<section id="blog" class="blog">
    <div class="container aos-init aos-animate" data-aos="fade-up">
        <form action="RechercheModeleParBenefice.SearchController" method="post">
            <div class="section-title-col-2 aos-init aos-animate" data-aos="fade-up">
                <a href="index.jsp?pagePath=page/gestion/modele/MenuRecherche.jsp">
                    <div class="icon-box"
                         style="width: 75px; height: 40px; background-color: rgb(245,245,245); display: flex;
                     justify-content: center; align-items: center;">
                        <i class="bi bi-arrow-left-short" style="font-size: 33px;"></i>
                    </div>
                </a>
                <h2>Recherche par benefice</h2>
            </div>
            <div class="row">
                <div class="col-lg-6 col-mg-6" data-aos="fade-right" data-aos-delay="300">
                    <div class="card">
                        <div class="card-body">
                            <div class="row">
                                <div class="col-lg-6 col-md-6">
                                    <div class="form-group my-2">
                                        <input type="text" name="beneficeMin" class="form-control" id="prixMin" placeholder="Benefice minimum" required="">
                                    </div>
                                </div>
                                <div class="col-lg-6 col-md-6">
                                    <div class="form-group my-2">
                                        <input type="text" name="beneficeMax" class="form-control" id="prixMax" placeholder="Benefice maximum" required="">
                                    </div>
                                </div>
                            </div>
                            <div class="text-center mt-2">
                                <button type="submit" class="btn btn-primary validebouton">Rechercher</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form>

        <br>

        <div class="row">
            <hr>
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">Modele</th>
                    <th scope="col">Categorie</th>
                    <th scope="col">Style</th>
                    <th scope="col">Volume</th>
                    <th scope="col">Benefice</th>
                </tr>
                </thead>
                <tbody>
                <%
                    if(request.getAttribute("modeles") != null){
                        List<ModeleComplet> modeles = (List<ModeleComplet>) request.getAttribute("modeles");
                        for(ModeleComplet modele : modeles){
                %>
                <tr>
                    <td><%= modele.getNomModele() %></td>
                    <td><%= modele.getNomcategorie() %></td>
                    <td><%= modele.getNomstyle() %></td>
                    <td><%= modele.getNomvolume() %></td>
                    <td><%= modele.getPrix() %> Ar</td>
                </tr>
                <%
                        }
                    }
                %>
                </tbody>
            </table>
        </div>
    </div>
</section>
