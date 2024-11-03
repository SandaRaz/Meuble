<%@ page import="affichageData.ViewModeleAvecMatiere" %>
<%@ page import="affichageData.ViewModele" %>
<%@ page import="entityView.ModeleComplet" %>
<%@ page import="entityView.QuantiteMatiereDetail" %>

<% 
    ViewModele viewModele = new ViewModele();
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
            <h2>Liste de modele</h2>
        </div>
        
        <div class="row">
            <%
                for(ViewModeleAvecMatiere mam : viewModele.modeleAvecMatiere){
            %>
                    <div class="col-lg-3 col-md-3">
                        <div class="sidebar">
                            <h3 class="sidebar-title"><%= mam.modele.getNomModele() %></h3>
                            <hr>
                            <ul>
                                <li>Categorie: <span><%= mam.modele.getNomcategorie() %></span></li>
                                <li>Style: <span><%= mam.modele.getNomstyle() %></span></li>
                                <li>Volume: <span><%= mam.modele.getNomvolume() %></span></li>
                            </ul>
                            <hr>
                            <div class="sidebar-item categories">
                                <ul>
                                    <%
                                        for(QuantiteMatiereDetail qmd : mam.matieres){
                                    %>
                                            <li><a href="#"><%= qmd.getNommatiere() %><span>(<%= qmd.getQuantite() %>) - <%= qmd.getPrix() %> Ar</span></a></li>  
                                    <%
                                        }
                                    %>
                                </ul>
                            </div>
                            <div class="modal-footer">
                                <div  class="btn btn-secondary"><%= mam.modele.getPrix() %> Ar</div>
                            </div>
                        </div>
                    </div>
            <%
                }  
            %>
        </div>
    </div>
</section>