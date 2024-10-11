<%@ page import="affichageData.ViewAjoutMeuble" %>
<%@ page import="affichageData.ViewAjoutEmploye" %>
<%@ page import="affichageData.ViewAjoutClient" %>
<%@ page import="entity.*" %>
<%
    ViewAjoutClient viewAjoutClient = new ViewAjoutClient();
%>

<section id="contact" class="contact">
    <div class="container">

        <div class="section-title-col-2 aos-init aos-animate" data-aos="fade-up">
            <a href="index.jsp?pagePath=page/gestion/client/MenuClient.jsp">
                <div class="icon-box"
                     style="width: 75px; height: 40px; background-color: rgb(245,245,245); display: flex;
                     justify-content: center; align-items: center;">
                    <i class="bi bi-arrow-left-short" style="font-size: 33px;"></i>
                </div>
            </a>
            <h2>Ajout de nouveau client</h2>
        </div>

        <div class="row">
            <div class="col-lg-6 col-md-6 aos-init aos-animate formgroup" data-aos="fade-up" data-aos-delay="300">
                <form action="InsertClient.ClientController" method="post" role="form">
                    <div class="form-floating my-3">
                        <input name="nomClient" type="text" class="form-control" id="floatingInput" placeholder="quantite" required="">
                        <label for="floatingInput">Nom du client</label>
                    </div>

                    <div class="form-floating my-3">
                        <select name="idGenre" class="form-select" id="floatingSelect" aria-label="Floating label select example">
                            <%
                                for(Genre genre : viewAjoutClient.genres){
                            %>
                                    <option value="<%= genre.getId() %>"><%= genre.getType() %></option>
                            <%
                                }
                            %>
                        </select>
                        <label for="floatingSelect">Genre</label>
                    </div>

                    <div class="form-floating my-3">
                        <input name="dateNaissance" type="date" class="form-control" id="floatingInput" placeholder="quantite" required="">
                        <label for="floatingInput">Date de naissance</label>
                    </div>

                    <div class="text-center">
                        <button type="submit" class="btn btn-primary validebouton">Ajouter</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</section>
