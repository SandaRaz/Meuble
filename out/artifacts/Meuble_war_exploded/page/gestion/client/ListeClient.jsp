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
<%@ page import="affichageData.ViewAchatClient" %>
<%@ page import="entityView.ClientComplet" %>

<%
    ViewAchatClient viewAchatClient = new ViewAchatClient();
%>

<section id="blog" class="blog">
    <div class="container aos-init aos-animate" data-aos="fade-up">
        <div class="section-title-col-2 aos-init aos-animate" data-aos="fade-up">
            <a href="index.jsp?pagePath=page/gestion/client/MenuClient.jsp">
                <div class="icon-box"
                     style="width: 75px; height: 40px; background-color: rgb(245,245,245); display: flex;
                     justify-content: center; align-items: center;">
                    <i class="bi bi-arrow-left-short" style="font-size: 33px;"></i>
                </div>
            </a>
            <h2>Liste des Clients
            </h2>
        </div>
        <div class="row">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">Nom</th>
                    <th scope="col">Genre</th>
                    <th scope="col">Date de naissance</th>
                </tr>
                </thead>
                <tbody>
                <%
                    for(ClientComplet cc : viewAchatClient.clients){
                %>
                <tr>
                    <td><%= cc.getNom() %></td>
                    <td><%= cc.getGenre() %></td>
                    <td><%= cc.getDateNaissance() %></td>
                </tr>
                <%
                    }
                %>
                </tbody>
            </table>
        </div>
    </div>
</section>
