<%--
    Document   : RechercheModele
    Created on : 9 janv. 2024, 14:31:37
    Author     : allan
--%>
<%@ page import="entity.Modele" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="entityView.StockProduitDetail" %>
<%@ page import="object.Pagination" %>

<%
    List<StockProduitDetail> stockProduitDetails = new ArrayList<>();
    Pagination pagination = new Pagination(0,5,5,1,1);

    Object objStockProduits = request.getAttribute("stockProduits");
    Object objPagination = request.getAttribute("pagination");

    if (objStockProduits != null){
        if(objStockProduits instanceof List<?>){
            stockProduitDetails = (List<StockProduitDetail>) objStockProduits;
        }else{
            System.out.println("Instance: "+objStockProduits);
        }
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
            <h2>Mouvement de tous les modeles</h2>
        </div>
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
                            <tr>
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
                                        String href = "StockGlobaleNextPage.ListController?pageActuel="+(i+1)+"&offset="+offset+"&limit="+limit;

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
    </div>
</section>
