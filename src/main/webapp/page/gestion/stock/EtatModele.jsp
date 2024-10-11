<%--
    Document   : RechercheModele
    Created on : 9 janv. 2024, 14:31:37
    Author     : allan
--%>
<%@ page import="entity.Modele" %>
<%@ page import="java.util.List" %>
<%@ page import="affichageData.ViewEtatStock" %>
<%@ page import="entityView.StockProduitDetail" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="object.Pagination" %>

<%
    ViewEtatStock viewEtatStock = new ViewEtatStock();

    String idModele = "";
    List<StockProduitDetail> stockProduitDetails = new ArrayList<>();
    double resteEnStock = 0;
    double valeurStock = 0;
    Pagination pagination = new Pagination(0,5,5,1,1);

    Object objIdModele = request.getAttribute("idModele");
    Object objStockProduits = request.getAttribute("stockProduits");
    Object objResteEnStock = request.getAttribute("resteEnStock");
    Object objValeurStock = request.getAttribute("valeurStock");
    Object objPagination = request.getAttribute("pagination");

    if(objIdModele != null){
        idModele = (String) objIdModele;
    }
    if (objStockProduits != null){
        if(objStockProduits instanceof List<?>){
            stockProduitDetails = (List<StockProduitDetail>) objStockProduits;
        }else{
            System.out.println("Instance: "+objStockProduits);
        }
    }
    if(objResteEnStock != null){
        resteEnStock = (double) objResteEnStock;
    }
    if(objValeurStock != null){
        valeurStock = (double) objValeurStock;
    }
    if(objPagination != null){
        pagination = (Pagination) objPagination;
    }
%>

<section id="blog" class="blog">
    <div class="container aos-init aos-animate" data-aos="fade-up">
        <div class="section-title-col-2 aos-init aos-animate" data-aos="fade-up">
            <a href="index.jsp?pagePath=page/gestion/stock/MenuStock.jsp">
                <div class="icon-box"
                     style="width: 75px; height: 40px; background-color: rgb(245,245,245); display: flex;
                     justify-content: center; align-items: center;">
                    <i class="bi bi-arrow-left-short" style="font-size: 33px;"></i>
                </div>
            </a>
            <h2>Etat par modele</h2>
        </div>
        <form action="MvtDeStockModele.ListController" method="post" role="form">
            <div class="row">
                <div class="col-lg-4 col-md-4">
                    <div class="form-floating my-3">
                        <select name="idModele" class="form-select" id="floatingSelect" aria-label="Floating label select example">
                            <%
                                for(Modele modele : viewEtatStock.modeles){
                            %>
                                <option value="<%= modele.getId() %>"><%= modele.getNomModele() %></option>
                            <%
                                }
                            %>
                        </select>
                        <label for="floatingSelect">Produit</label>
                    </div>
                </div>
                <div class="col-lg-2 col-md-2">
                    <div class="text-center my-3">
                        <button type="submit" class="btn btn-primary validebouton">Afficher</button>
                    </div>
                </div>
            </div>
        </form>
        <div class="row">
            <div class="col-lg-12 col-md-12">
                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col">Modele</th>
                        <th scope="col">Entrer</th>
                        <th scope="col">Sortie</th>
                        <th scope="col">Prix</th>
                        <th scope="col">Date Mouvement</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        for(StockProduitDetail spd : stockProduitDetails){
                    %>
                            <tr style="background-color: <%= spd.getCouleur() %>">
                                <td style="background-color: <%= spd.getCouleur() %>"><%= spd.getNomModele() %></td>
                                <td style="background-color: <%= spd.getCouleur() %>"><%= spd.getEntrer() %></td>
                                <td style="background-color: <%= spd.getCouleur() %>"><%= spd.getSortie() %></td>
                                <td style="background-color: <%= spd.getCouleur() %>"><%= spd.getPrixVente() %></td>
                                <td style="background-color: <%= spd.getCouleur() %>"><%= spd.getDateMvt() %></td>
                            </tr>
                    <%
                        }
                    %>
                    </tbody>
                </table>
            </div>
        <%
            if(objPagination != null){
        %>
                <div class="col-lg-12 col-md-12 text-center">
                    <nav aria-label="...">
                        <ul class="pagination pagination-sm">
                            <%
                                for(int i = 0; i<pagination.getNombrePage(); i++) {
                                    int offset = (i* pagination.getLimit());
                                    int limit = ((i+1)* pagination.getLimit());
                                    String href = "StockModeleNextPage.ListController?idModele="+idModele+"&pageActuel="+(i+1)+"&offset="+offset+"&limit="+limit;

                                    if ((i+1) == pagination.getNumPageActuel()) {
                            %>
                                        <li class="page-item active" aria-current="page">
                                            <a class="page-link" href="<%= href %>"><%= (i+1) %></a>
                                        </li>
                            <%
                                    } else {
                            %>
                                        <li class="page-item">
                                            <a class="page-link" href="<%= href %>"><%= (i+1) %></a>
                                        </li>
                            <%
                                    }
                                }
                            %>
                        </ul>
                    </nav>
                </div>
        <%
            }
        %>
        </div>
        <div class="row">
            <div class="col-lg-3 col-md-3">
                <div class="card">
                    <div class="card-body">
                        <p>Reste en stock: <b><%= resteEnStock %></b></p>
                    </div>
                </div>
            </div>
            <div class="col-lg-3 col-md-3">
                <div class="card">
                    <div class="card-body">
                        <p>Valeur en ariary: <b><%= valeurStock %> Ar</b></p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<script>

</script>
