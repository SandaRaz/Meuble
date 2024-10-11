<%@ page import="affichageData.ViewAjoutMeuble" %>
<%@ page import="entity.Categorie" %>
<%@ page import="entity.Style" %>
<%@ page import="entity.Volume" %>
<%@ page import="affichageData.ViewAjoutEmploye" %>
<%@ page import="entity.Poste" %>
<%
    ViewAjoutEmploye ajoutEmp = new ViewAjoutEmploye();
%>

<section id="contact" class="contact">
    <div class="container">

        <div class="section-title-col-2 aos-init aos-animate" data-aos="fade-up">
            <a href="index.jsp?pagePath=page/gestion/employe/MenuEmploye.jsp">
                <div class="icon-box"
                     style="width: 75px; height: 40px; background-color: rgb(245,245,245); display: flex;
                     justify-content: center; align-items: center;">
                    <i class="bi bi-arrow-left-short" style="font-size: 33px;"></i>
                </div>
            </a>
            <h2>Ajout de nouveau choix de modele</h2>
        </div>

        <div class="row">
            <div class="col-lg-6 col-md-6 aos-init aos-animate formgroup" data-aos="fade-up" data-aos-delay="300">
                <form action="InsertEmployer.EmpController" method="post" role="form">
                    <div class="form-floating my-3">
                        <input name="nomEmploye" type="text" class="form-control" id="floatingInput" placeholder="quantite" required="">
                        <label for="floatingInput">Nom de l'employer</label>
                    </div>

                    <div class="form-floating my-3">
                        <select name="idPoste" class="form-select" id="floatingSelect" aria-label="Floating label select example">
                            <%
                                for(Poste poste : ajoutEmp.postes){
                            %>
                            <option value="<%= poste.getId() %>"><%= poste.getTitre() %></option>
                            <%
                                }
                            %>
                        </select>
                        <label for="floatingSelect">Poste</label>
                    </div>

                    <div class="form-floating my-3">
                        <input name="dateNaissance" type="date" class="form-control" id="floatingInput" placeholder="quantite" required="">
                        <label for="floatingInput">Date de naissance</label>
                    </div>

                    <div class="form-floating my-3">
                        <input name="tauxHoraire" type="number" class="form-control" id="floatingInput" placeholder="quantite" required="">
                        <label for="floatingInput">Taux horaire</label>
                    </div>

                    <div class="text-center">
                        <button type="submit" class="btn btn-primary validebouton">Ajouter</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</section>
