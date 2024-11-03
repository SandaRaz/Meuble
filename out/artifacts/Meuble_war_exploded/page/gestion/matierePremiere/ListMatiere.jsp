<%@ page import="affichageData.ViewMatiere" %>
<%@ page import="entity.Matiere" %>
<%
    ViewMatiere viewMatiere = new ViewMatiere();
%>

<section id="blog" class="blog">
    <div class="container aos-init aos-animate" data-aos="fade-up">

        <div class="section-title-col-2 aos-init aos-animate" data-aos="fade-up">
            <a href="index.jsp?pagePath=page/gestion/matierePremiere/MenuMatiere.jsp">
                <div class="icon-box"
                     style="width: 75px; height: 40px; background-color: rgb(245,245,245); display: flex;
                     justify-content: center; align-items: center;">
                    <i class="bi bi-arrow-left-short" style="font-size: 33px;"></i>
                </div>
            </a>
            <h2>Liste de matiere</h2>
        </div>

        <div class="row">
            <%
                for(Matiere matiere : viewMatiere.matieres){
            %>
                    <div class="col-lg-3 col-md-3">
                        <div class="sidebar">
                            <a href="ListModelesMatiere.ListController?idmatiere=<%= matiere.getId() %>">
                                <h3 class="sidebar-title"><%= matiere.getNommatiere() %></h3>
                            </a>
                        </div>
                    </div>
            <%
                }
            %>

        </div>
    </div>
</section>