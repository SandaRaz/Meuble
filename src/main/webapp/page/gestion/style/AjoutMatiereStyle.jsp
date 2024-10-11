<%@ page import="entity.Style" %>
<%@ page import="entity.Matiere" %>
<%@ page import="affichageData.ViewMatiereStyle" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>

<%
    ViewMatiereStyle viewMatiereStyle = new ViewMatiereStyle();
    List<Style> styles = viewMatiereStyle.styles;
    List<Matiere> matieres = viewMatiereStyle.matieres;
%>
<section id="contact" class="contact">
    <div class="container">

        <div class="section-title-col-2 aos-init aos-animate" data-aos="fade-up">
            <a href="index.jsp?pagePath=page/gestion/style/MenuStyle.jsp">
                <div class="icon-box" 
                     style="width: 75px; height: 40px; background-color: rgb(245,245,245); display: flex;
                     justify-content: center; align-items: center;">
                    <i class="bi bi-arrow-left-short" style="font-size: 33px;"></i>
                </div>
            </a>
            <h2>Matière première pour un Style</h2>
        </div>

        <div class="row">
            <div class="col-lg-6 col-md-6 aos-init aos-animate formgroup" data-aos="fade-up" data-aos-delay="300">
                <form action="MatieresStyle.FormController" method="post" role="form">
                    <div class="form-floating my-3">
                        <select name="idstyle" class="form-select" id="floatingSelect" aria-label="Floating label select example">
                            <%
                                for(Style style : styles){
                            %>
                                    <option value="<%= style.getId() %>"><%= style.getNomstyle() %></option>
                            <%
                                }
                            %>
                        </select>
                        <label for="floatingSelect">Choix de style</label>
                    </div>
                        
                    <h4>Matière première pour ce style</h4>
                    <div class="form-floating my-3">
                        <%
                            for(Matiere matiere : matieres){
                        %>
                                <p><input name="idmatieres" type="checkbox" value="<%= matiere.getId() %>"> <%= matiere.getNommatiere() %></P>
                        <%
                            }
                        %>
<!--                        
                        <select name="idmatiere" class="form-select" id="floatingSelect" aria-label="Floating label select example">
                            
                        </select>
-->
                    </div>
                    
                    <div class="text-center">
                        <button type="submit" class="btn btn-primary validebouton">Enregistrer</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</section>