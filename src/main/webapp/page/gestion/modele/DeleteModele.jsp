<%@ page import="affichageData.ViewModeleAvecMatiere" %>
<%@ page import="affichageData.ViewModele" %>
<%@ page import="entityView.ModeleComplet" %>
<%@ page import="entityView.QuantiteMatiereDetail" %>
<%@ page import="affichageData.ViewDeleteModele" %>

<%
    ViewDeleteModele viewDeleteModele = new ViewDeleteModele();
%>

<section id="features" class="features">
    <div class="container aos-init aos-animate" data-aos="fade-up">
        <div class="section-title-col-2 aos-init aos-animate" data-aos="fade-up">
            <a href="index.jsp?pagePath=page/gestion/modele/MenuModele.jsp">
                <div class="icon-box"
                     style="width: 75px; height: 40px; background-color: rgb(245,245,245); display: flex;
                     justify-content: center; align-items: center;">
                    <i class="bi bi-arrow-left-short" style="font-size: 33px;"></i>
                </div>
            </a>
            <h2>Effacer un modele</h2>
        </div>

        <div class="row">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col"></th>
                    <th scope="col">Modele</th>
                    <th scope="col">Categorie</th>
                    <th scope="col">Style</th>
                    <th scope="col">Volume</th>
                    <th scope="col"></th>
                </tr>
                </thead>
                <tbody>
                <%
                    int numero = 1;
                    for(ModeleComplet modele : viewDeleteModele.modeleCompletList){
                %>
                        <tr>
                            <th scope="row"><%= numero %></th>
                            <td><%= modele.getNomModele() %></td>
                            <td><%= modele.getNomcategorie() %></td>
                            <td><%= modele.getNomstyle() %></td>
                            <td><%= modele.getNomvolume() %></td>
                            <td>
                                <a onclick="confirmDeleteModele('<%= modele.getId() %>')">
                                    <i class="ri-close-line deleteModeleIcone"></i>
                                </a>
                            </td>
                        </tr>
                        <div class="card deleteDialog col-lg-6 col-md-6" id="confirmDelete<%= modele.getId() %>" style="display: none">
                            <div class="card-body">
                                <p>Voulez-vous vraiment supprimer <b><%= modele.getNomModele() %></b> ?</p>
                                <button type="button" class="btn btn-danger" onclick="deletingModele('<%= modele.getId() %>')">Oui</button>
                                <button type="button" class="btn btn-light" onclick="cancelDeleteModele('<%= modele.getId() %>')">Non</button>
                            </div>
                        </div>
                <%
                        numero++;
                    }
                %>
                </tbody>
            </table>
        </div>

        <div class="row aos-init aos-animate" data-aos="fade-up" data-aos-delay="300">
            <div class="col-lg-3 col-md-4 mt-4">
                <a href="index.jsp?pagePath=page/gestion/modele/CorbeilleModele.jsp">
                    <div class="icon-box">
                        <i class="ri-delete-bin-6-line" style="color: #626161;"></i>
                        <h3>Corbeille</h3>
                    </div>
                </a>
            </div>
        </div>
    </div>
</section>