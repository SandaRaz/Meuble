<%@ page import="entity.Modele" %>
<%@ page import="entity.Matiere" %>
<%@ page import="entity.Poste" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="affichageData.ViewAjoutMeuble" %>
<%
    Object tempModele = request.getSession().getAttribute("tempModele");
    List<Matiere> matieres = new ArrayList<>();
    List<Poste> postes = new ArrayList<>();
    ViewAjoutMeuble viewAjoutMeuble = null;
    if(tempModele != null){
        Modele modele = (Modele) tempModele;
        viewAjoutMeuble = new ViewAjoutMeuble(modele);
        
        matieres = viewAjoutMeuble.matieres;
        postes = viewAjoutMeuble.postes;
    }else{
        response.sendRedirect("index.jsp?pagePath=page/gestion/modele/AjoutModele.jsp");
    }
%>

<section id="contact" class="contact">
    <div class="container">
      
        <div class="section-title-col-2 aos-init aos-animate" data-aos="fade-up">
            <a href="index.jsp?pagePath=page/gestion/modele/AjoutModele.jsp">
                <div class="icon-box"
                     style="width: 75px; height: 40px; background-color: rgb(245,245,245); display: flex;
                     justify-content: center; align-items: center;">
                    <i class="bi bi-arrow-left-short" style="font-size: 33px;"></i>
                </div>
            </a>
            <h2>Ajout de nouveau choix de modele</h2>
        </div>
        
        <div class="row">
            <table class="table">
                <thead>
                  <tr>
                    <th scope="col">Champ</th>
                    <th scope="col">Valeur</th>
                  </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>categorie</td>
                        <td><%= viewAjoutMeuble.modeleComplet.getNomcategorie() %></td>
                    </tr>
                    <tr>
                        <td>style</td>
                        <td><%= viewAjoutMeuble.modeleComplet.getNomstyle() %></td>
                    </tr>
                    <tr>
                        <td>Volume</td>
                        <td><%= viewAjoutMeuble.modeleComplet.getNomvolume() %></td>
                    </tr>
                </tbody>
            </table>
        </div>
        <br />
        <form action="InsererModele.FormController" method="post" role="form">
            <div class="row">
                <div class="col-lg-6 col-mg-6">
                    <div class="card">
                        <div class="card-body">
                            <h5>Quantit&eacute; de chaque matieres premieres</h5>
                            <%
                                for(Matiere matiere : matieres){
                            %>
                                    <div class="form-group my-1">
                                        <input name="idmatiere" type="hidden" value="<%= matiere.getId() %>">
                                        <label for="matiere" class="mb-1"><%= matiere.getNommatiere() %></label>
                                        <input value="0" name="quantiteMatiere" type="number" step="0.1" class="form-control" id="matiere" placeholder="quantite">
                                    </div>
                            <%
                                }
                            %>
                        </div>
                    </div>
                </div>
                <div class="col-lg-6 col-mg-6">
                    <div class="card">
                        <div class="card-body">
                            <h5>Poste oeuvrant</h5>

                            <%
                                for(Poste poste : postes){
                            %>
                                    <div class="form-group my-1">
                                        <input name="idposte" type="hidden" value="<%= poste.getId() %>">
                                        <div class="row">
                                            <div class="col-lg-6 col-md-6">
                                                <label for="floatingInput<%= poste.getId() %>" class="mb-1"><%= poste.getTitre() %></label>
                                                <input value="0" name="nombre" type="number" class="form-control" id="floatingInput<%= poste.getId() %>" placeholder="Nombre">
                                            </div>
                                            <div class="col-lg-6 col-md-6">
                                                <label for="floatingInput<%=poste.getId()%>" class="mb-1">Heure de travail minimum</label>
                                                <input value="0" name="minHeureTravail" type="number" class="form-control" id="floatingInput<%=poste.getId()%>" placeholder="Heure de travail minimum">
                                            </div>
                                        </div>
                                    </div>
                            <%
                                }
                            %>
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