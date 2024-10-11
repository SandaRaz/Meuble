<%@ page import="affichageData.ViewAjoutMeuble" %>
<%@ page import="entity.Categorie" %>
<%@ page import="entity.Style" %>
<%@ page import="entity.Volume" %>
<%
    ViewAjoutMeuble viewAjoutMeuble = new ViewAjoutMeuble();
        
    Object tempModele = request.getSession().getAttribute("tempModele");
    if(tempModele != null){
        request.getSession().removeAttribute("tempModele");
        System.out.println("PAS NULL SESSION");
    }
%>

<section id="contact" class="contact">
    <div class="container">

        <div class="section-title-col-2 aos-init aos-animate" data-aos="fade-up">
            <a href="index.jsp?pagePath=page/gestion/modele/MenuModele.jsp">
                <div class="icon-box" 
                     style="width: 75px; height: 40px; background-color: rgb(245,245,245); display: flex;
                     justify-content: center; align-items: center;">
                    <i class="bi bi-arrow-left-short" style="font-size: 33px;"></i>
                </div>
            </a>
            <h2>Ajout de nouveau choix de modele</h2>
        </div>

        <form action="AjoutModeleVersPage2.FormController" method="post" role="form">
            <div class="row">
                <div class="col-lg-6 col-mg-6">
                    <div class="card">
                        <div class="card-body">
                            <div class="form-group my-4">
                                <input name="nomModele" type="text" class="form-control" placeholder="Nom du modele" required="">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-6 col-mg-6">
                    <div class="card">
                        <div class="card-body">
                            <div class="form-group my-2">
                                <label for="categorieSelect" class="mb-2">Categorie du modele</label>
                                <select name="idcategorie" class="form-select" id="categorieSelect" aria-label="Floating label select example">
                                    <%
                                        for(Categorie categorie : viewAjoutMeuble.categories){
                                    %>
                                            <option value="<%= categorie.getId() %>"><%= categorie.getNom() %></option>
                                    <%
                                        }
                                    %>
                                </select>
                            </div>
                            <div class="form-group my-2">
                                <label for="styleSelect" class="mb-2">Style du modele</label>
                                <select name="idstyle" class="form-select" id="styleSelect" aria-label="Floating label select example">
                                    <%
                                        for(Style style : viewAjoutMeuble.styles){
                                    %>
                                            <option value="<%= style.getId() %>"><%= style.getNomstyle() %></option>
                                    <%
                                        }
                                    %>
                                </select>
                            </div>
                            <div class="form-group my-2">
                                <label for="volumeSelect" class="mb-2">Volume du modele</label>
                                <select name="idvolume" class="form-select" id="volumeSelect" aria-label="Floating label select example">
                                    <%
                                        for(Volume volume : viewAjoutMeuble.volumes){
                                    %>
                                            <option value="<%= volume.getId() %>"><%= volume.getNomVolume() %></option>
                                    <%
                                        }
                                    %>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>
                <hr class="mt-4">
                <div class="text-center my-2">
                    <button type="submit" class="btn btn-primary validebouton">Suivant</button>
                </div>
            </div>
        </form>
    </div>
</section>