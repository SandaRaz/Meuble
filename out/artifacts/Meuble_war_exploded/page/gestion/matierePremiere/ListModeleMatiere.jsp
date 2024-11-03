<%@ page import="affichageData.ViewMatiere" %>
<%@ page import="entity.Matiere" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="entityView.ModeleComplet" %>
<%@ page import="java.util.List" %>
<%
    List<ModeleComplet> modeles = new ArrayList<>();
    if(request.getAttribute("modeles") != null){
        modeles = (List<ModeleComplet>) request.getAttribute("modeles");
    }
%>

<section id="blog" class="blog">
    <div class="container aos-init aos-animate" data-aos="fade-up">

        <div class="section-title-col-2 aos-init aos-animate" data-aos="fade-up">
            <a href="index.jsp?pagePath=page/gestion/matierePremiere/ListMatiere.jsp">
                <div class="icon-box"
                     style="width: 75px; height: 40px; background-color: rgb(245,245,245); display: flex;
                     justify-content: center; align-items: center;">
                    <i class="bi bi-arrow-left-short" style="font-size: 33px;"></i>
                </div>
            </a>
            <h2>Liste de modele utilisant cet matiere</h2>
        </div>

        <div class="row">
            <table class="table">
                <thead>
                  <tr>
                    <th scope="col">#</th>
                    <th scope="col">Modele</th>
                  </tr>
                </thead>
                <tbody>
                    <%
                        int numero = 1;
                        for(ModeleComplet modele : modeles){
                    %>
                            <tr>
                                <th scope="row"><%= numero %></th>
                                <td><%= modele.getNomModele() %></td>
                            </tr> 
                    <%
                            numero++;
                        }
                    %>
                </tbody>
            </table>
        </div>
    </div>
</section>