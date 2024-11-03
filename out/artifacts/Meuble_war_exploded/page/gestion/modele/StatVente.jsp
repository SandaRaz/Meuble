<%--
    Document   : RechercheModele
    Created on : 9 janv. 2024, 14:31:37
    Author     : allan
--%>
<%@ page import="entity.Modele" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="entityView.ModeleComplet" %>
<%@ page import="affichageData.ViewListeEmploye" %>
<%@ page import="entityView.InfosEmploye" %>
<%@ page import="affichageData.ViewStatVente" %>
<%@ page import="entityView.StatistiqueGenre" %>
<%@ page import="java.io.Console" %>

<%
    ViewStatVente viewStatVente = new ViewStatVente();

    List<StatistiqueGenre> statistiqueGenres = new ArrayList<>();
    if(request.getAttribute("statsGenres") != null){
        statistiqueGenres = (List<StatistiqueGenre>) request.getAttribute("statsGenres");
        System.out.println("statistiqueGenre count :>>> "+statistiqueGenres.size());
    }

    double nombreHomme = 0;
    double nombreFemme = 0;
%>

<section id="blog" class="blog">
    <div class="container aos-init aos-animate" data-aos="fade-up">
        <div class="section-title-col-2 aos-init aos-animate" data-aos="fade-up">
            <a href="index.jsp?pagePath=page/gestion/modele/MenuModele.jsp">
                <div class="icon-box"
                     style="width: 75px; height: 40px; background-color: rgb(245,245,245); display: flex;
                     justify-content: center; align-items: center;">
                    <i class="bi bi-arrow-left-short" style="font-size: 33px;"></i>
                </div>
            </a>
            <h2>Statistique des ventes
            </h2>
        </div>
        <form action="StatGenreModele.MetierController" method="post" role="form">
            <div class="form-floating my-3">
                <select name="idModele" class="form-select" id="selectedIdModele" aria-label="Floating label select example">
<%--                    <option value="tout">Tout les produits</option>--%>
                        <%
                            for(Modele modele : viewStatVente.modeles){
                        %>
                            <option value="<%= modele.getId() %>"><%= modele.getNomModele() %></option>
                        <%
                            }
                        %>
                </select>
                <label for="selectedIdModele">Produit</label>
            </div>

            <div class="text-center">
                <button type="submit" onmouseleave="afficheDonut()" class="btn btn-primary validebouton">Afficher</button>
            </div>
        </form>

        <div class="row">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">Produit</th>
                    <th scope="col">Genre</th>
                    <th scope="col">Quantite</th>
                </tr>
                </thead>
                <tbody>
                    <%
                        for(StatistiqueGenre stats : statistiqueGenres){
                    %>
                    <tr>
                        <td><%= stats.getNomModele() %></td>
                        <td><%= stats.getType() %></td>
                        <td><%= stats.getQuantite() %></td>
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
        </div>
        <h3 class="text-center">Diagramme</h3>
        <hr>
        <div id="charte"></div>

    </div>
</section>
