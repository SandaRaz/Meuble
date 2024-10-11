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

<%
    ViewListeEmploye listEmp = new ViewListeEmploye();
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
            <h2>Liste des employes
            </h2>
        </div>
        <div class="row">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">Nom</th>
                    <th scope="col">Poste</th>
                    <th scope="col">TauxHoraire</th>
                    <th scope="col">Debut d' embauche</th>
                    <th scope="col">Exerce ce poste depuis</th>
                </tr>
                </thead>
                <tbody>
                <%
                    for(InfosEmploye emp : listEmp.employes){
                %>
                        <tr>
                            <td><%= emp.getNom() %></td>
                            <td><%= emp.getTitre() %></td>
                            <td><%= emp.getTauxHoraire() %></td>
                            <td><%= emp.getDateEmbauche() %></td>
                            <td><%= emp.getDateDebutPoste() %></td>
                        </tr>
                <%
                    }
                %>
                </tbody>
            </table>
        </div>
    </div>
</section>
