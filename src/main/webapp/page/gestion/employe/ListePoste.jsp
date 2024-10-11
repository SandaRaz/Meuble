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
<%@ page import="affichageData.ViewEtatStock" %>
<%@ page import="entityView.StockProduitDetail" %>
<%@ page import="affichageData.ViewListePoste" %>
<%@ page import="entity.Poste" %>

<%
    ViewListePoste listePoste = new ViewListePoste();
%>

<section id="blog" class="blog">
    <div class="container aos-init aos-animate" data-aos="fade-up">
        <div class="section-title-col-2 aos-init aos-animate" data-aos="fade-up">
            <a href="index.jsp?pagePath=page/gestion/employe/MenuEmploye.jsp">
                <div class="icon-box"
                     style="width: 75px; height: 40px; background-color: rgb(245,245,245); display: flex;
                     justify-content: center; align-items: center;">
                    <i class="bi bi-arrow-left-short" style="font-size: 33px;"></i>
                </div>
            </a>
            <h2>Liste Poste
            </h2>
        </div>
        <div class="row">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">Titre</th>
                    <th scope="col">Salaire par heure</th>
                    <th scope="col">NiveauTauxHoraire</th>
                    <th scope="col">Hierarchie</th>
                </tr>
                </thead>
                <tbody>
                <%
                    for(Poste p : listePoste.postes){
                %>
                <tr>
                    <td><%= p.getTitre() %></td>
                    <td><%= p.getSalaireHeure() %></td>
                    <td><%= p.getNiveauTauxHoraire() %></td>
                    <td><%= p.getHierarchie() %></td>
                </tr>
                <%
                    }
                %>
                </tbody>
            </table>
        </div>
    </div>
</section>
