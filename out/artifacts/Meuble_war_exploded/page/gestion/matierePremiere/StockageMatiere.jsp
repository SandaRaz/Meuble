<%@ page import="affichageData.ViewMatiere" %>
<%@ page import="entity.Matiere" %>
<%@ page import="entityView.MvtStockMatiereDetail" %>
<%
    ViewMatiere viewMatiere = new ViewMatiere();
%>

<section id="contact" class="contact">
    <div class="container">

        <div class="section-title-col-2 aos-init aos-animate" data-aos="fade-up">
            <a href="index.jsp?pagePath=page/gestion/matierePremiere/MenuMatiere.jsp">
                <div class="icon-box" 
                     style="width: 75px; height: 40px; background-color: rgb(245,245,245); display: flex;
                     justify-content: center; align-items: center;">
                    <i class="bi bi-arrow-left-short" style="font-size: 33px;"></i>
                </div>
            </a>
            <h2>Stockage de nouveau Matiere</h2>
        </div>
        
        <div class="row">
            <div class="col-lg-5 col-md-5 aos-init aos-animate formgroup" data-aos="fade-up" data-aos-delay="300">
                <form action="StockageMatiere.MetierController" method="post" role="form">
                    <div class="form-floating my-3">
                        <select name="idmatiere" class="form-select" id="floatingSelect" aria-label="Floating label select example">
                            <%
                                for(Matiere matiere : viewMatiere.matieres){
                            %>
                                    <option value="<%= matiere.getId() %>"><%= matiere.getNommatiere() %></option>
                            <%
                                }
                            %>
                        </select>
                        <label for="floatingSelect">Modele</label>
                    </div>
                    
                    <div class="form-floating my-3">
                        <input name="quantite" type="number" step="0.5" class="form-control" id="floatingInput" placeholder="" required="">
                        <label for="floatingInput">Quantite</label>
                    </div>
                        
                    <div class="text-center">
                        <button type="submit" class="btn btn-primary validebouton">Stocker</button>
                    </div>
                </form>
            </div>
            <div style="overflow-y: scroll; overflow-x: none;" class="col-lg-6 col-md-6 offset-1 aos-init aos-animate formgroup" data-aos="fade-up" data-aos-delay="300">
                <table class="table mt-3">
                <thead>
                  <tr>
                    <th scope="col">Matiere</th>
                    <th scope="col">Entrer</th>
                    <th scope="col">Sortie</th>
                    <th scope="col">Date</th>
                  </tr>
                </thead>
                <tbody>
                    <% 
                        for(MvtStockMatiereDetail mvt : viewMatiere.mvtMatieres){
                    %>
                            <tr>
                                <td><%= mvt.getNommatiere() %></td>
                                <td><%= mvt.getEntrer() %></td>
                                <td><%= mvt.getSortie() %></td>
                                <td><%= mvt.getDateMvt() %></td>
                            </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
            </div>
        </div>
    </div>
</section>
