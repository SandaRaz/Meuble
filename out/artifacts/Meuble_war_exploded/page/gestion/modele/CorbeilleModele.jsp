<%@ page import="affichageData.ViewModeleAvecMatiere" %>
<%@ page import="affichageData.ViewModele" %>
<%@ page import="entityView.ModeleComplet" %>
<%@ page import="entityView.QuantiteMatiereDetail" %>
<%@ page import="affichageData.ViewDeleteModele" %>
<%@ page import="entity.Modele" %>

<%
    ViewDeleteModele viewDeleteModele = new ViewDeleteModele();
%>

<section id="features" class="features">
    <div class="container aos-init aos-animate" data-aos="fade-up">
        <div class="section-title-col-2 aos-init aos-animate" data-aos="fade-up">
            <a href="index.jsp?pagePath=page/gestion/modele/DeleteModele.jsp">
                <div class="icon-box"
                     style="width: 75px; height: 40px; background-color: rgb(245,245,245); display: flex;
                     justify-content: center; align-items: center;">
                    <i class="bi bi-arrow-left-short" style="font-size: 33px;"></i>
                </div>
            </a>
            <h2>Corbeille (modele)</h2>
        </div>

        <div class="row">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col"></th>
                    <th scope="col">Modele</th>
                    <th scope="col"></th>
                </tr>
                </thead>
                <tbody>
                <%
                    int numero = 1;
                    for(Modele modele : viewDeleteModele.modeleSupprimes){
                %>
                <tr>
                    <th scope="row"><%= numero %></th>
                    <td><%= modele.getNomModele() %></td>
                    <td>
                        <a onclick="confirmRestoreModele('<%= modele.getId() %>')">
                            <i class="ri-restart-line restoreModeleIcone"></i>
                        </a>
                    </td>
                </tr>
                <div class="card restoreDialog col-lg-6 col-md-6" id="confirmRestore<%= modele.getId() %>" style="display: none">
                    <div class="card-body">
                        <p>Confirmer la restauration <b><%= modele.getNomModele() %></b> ?</p>
                        <button type="button" class="btn btn-primary" onclick="restoringModele('<%= modele.getId() %>')">Restaurer</button>
                        <button type="button" class="btn btn-light" onclick="cancelRestoreModele('<%= modele.getId() %>')">Annuler</button>
                    </div>
                </div>
                <%
                        numero++;
                    }
                %>
                </tbody>
            </table>
        </div>
    </div>
</section>