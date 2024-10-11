<%@ page import="affichageData.ViewAchatClient" %>
<%@ page import="entity.Modele" %>
<%@ page import="object.Panier" %>
<%@ page import="object.ProduitAchetee" %>
<%
    ViewAchatClient viewAchatClient = new ViewAchatClient();
    Panier panier = new Panier();

    if(session.getAttribute("panierClient") != null){
        panier = (Panier) session.getAttribute("panierClient");
    }

    String smException = (String) request.getAttribute("stockManquante");
    boolean stockManquante = false;
    if(smException != null){
        stockManquante = true;
    }
%>
<section id="features" class="features">
    <div class="container aos-init aos-animate" data-aos="fade-up">
        <div class="section-title-col-2 aos-init aos-animate" data-aos="fade-up">
                <a href="index.jsp?pagePath=page/gestion/client/AchatClient.jsp">
                    <div class="icon-box"
                         style="width: 75px; height: 40px; background-color: rgb(245,245,245); display: flex;
                     justify-content: center; align-items: center;">
                        <i class="bi bi-arrow-left-short" style="font-size: 33px;"></i>
                    </div>
                </a>
                <h2>Achat de produit</h2>
        </div>
        <div class="row">
            <div class="col-lg-5 col-mg-5">
                <form action="AjouterAuPanier.ClientController" method="post">
                    <div class="card">
                        <div class="card-body">
                            <div class="text-center mb-3">
                                <h5>Achat du client <b><%= panier.getProprietaire().getNom() %></b></h5>
                            </div>
                            <div class="form-group my-2">
                                <select name="idModele" class="form-select">
                                    <%
                                        for(Modele modele : viewAchatClient.modeles){
                                    %>
                                            <option value="<%= modele.getId() %>"><%= modele.getNomModele() %></option>
                                    <%
                                        }
                                    %>
                                </select>
                            </div>
                            <div class="form-group my-2">
                                <input type="number" name="quantite" class="form-control" id="quantite" placeholder="quantit&eacute;" required="">
                            </div>
                            <div class="form-group my-2">
                                <input type="date" name="dateAchat" class="form-control" id="dateAchat">
                            </div>
                            <%
                                if(stockManquante){
                            %>
                                <div class="alert alert-danger alert-dismissible fade show" role="alert">
                                    <%= smException %>
                                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                                </div>
                            <%
                                }
                            %>
                            <div class="text-center mt-3">
                                <button type="submit" class="btn btn-primary validebouton">Ajout au panier</button>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="col-lg-7 col-mg-7" id="panier">
                <div class="text-center my-3">
                    <h5>Panier</h5>
                </div>
                <hr>
                <div class="row">
                    <div class="col-lg-8 col-md-8 text-center">
                        <b>Produit</b>
                    </div>
                    <div class="col-lg-2 col-md-2">
                        <b>Quantit&eacute;</b>
                    </div>
                    <div class="col-lg-2 col-md-2">
                        <b>Effacer</b>
                    </div>
                </div>
                <hr>
                <%
                    for(ProduitAchetee pa : panier.getListeProduits()){
                %>
                        <div class="card my-2" id="panierProduit<%= pa.getModele().getId() %>">
                            <div class="card-body">
                                <div class="row">
                                    <div class="col-lg-8 col-md-8"><%= pa.getModele().getNomModele() %></div>
                                    <div class="col-lg-2 col-md-2"><%= pa.getQuantite() %></div>
                                    <div class="col-lg-2 col-md-2">
                                        <a onclick="effacerPanierProduit('<%= pa.getModele().getId() %>')">
                                            <i class="ri-close-line deleteModeleIcone"></i>
                                        </a>
<%--                                        <a href="EffacerTest.ClientController?idModele=<%= pa.getModele().getId() %>">--%>
<%--                                            <i class="ri-close-line deleteModeleIcone"></i>--%>
<%--                                        </a>--%>
                                    </div>
                                </div>
                            </div>
                        </div>
                <%
                    }
                %>
                <div class="text-center mt-3">
                    <a href="Acheter.ClientController" class="btn btn-success validebouton">Acheter</a>
                </div>
            </div>
        </div>

    </div>
</section>
