<%@ page import="affichageData.ViewFabrication" %>
<%@ page import="exception.StockManquante" %>
<%@ page import="entity.Modele" %>
<%
    ViewFabrication viewFabrication = new ViewFabrication();
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
            <h2>Fabrication de modele</h2>
        </div>

        <form action="Fabrication.MetierController" method="post" role="form">
            <div class="row">
                <div class="col-lg-6 col-mg-6">
                    <div class="card">
                        <div class="card-body">
                            <div class="form-group my-2">
                                <label for="modele" class="my-1">Modele</label>
                                <select name="idmodele" class="form-select" id="modele" aria-label="Floating label select example">
                                    <%
                                        for(Modele modele : viewFabrication.modeles){
                                    %>
                                            <option value="<%= modele.getId() %>"><%= modele.getNomModele() %></option>
                                    <%
                                        }
                                    %>
                                </select>
                            </div>
                            <div class="form-group my-2">
                                <label for="quantiteInput" class="my-1">Quantite a fabriquer</label>
                                <input name="quantite" type="number" step="0.5" class="form-control" id="quantiteInput" placeholder="quantite" required="">
                            </div>
                            <div class="text-center form-group mt-2">
                                <button type="submit" class="btn btn-primary validebouton">Fabriquer</button>
                            </div>
                        </div>
                    </div>
                </div>

                <%
                    if(request.getAttribute("StockManquante") != null){
                        StockManquante sm = (StockManquante) request.getAttribute("StockManquante");
                %>
                        <div class="col-lg-6 col-md-6" data-aos="fade-down" data-aos-delay="300">
                            <div style="color: rgba(205,10,10,0.6)" class="mt-3">
                                <h5><%= sm.getMessage()  %></h5>
                            </div>
                            <table class="table mt-3">
                                <thead>
                                <tr>
                                    <th scope="col">Quantite requis</th>
                                    <th scope="col">Quantite restante</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td><%= sm.getStockRequis() %></td>
                                    <td><%= sm.getStockRestante() %></td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                <%
                    }
                %>
            </div>
        </form>
    </div>
</section>