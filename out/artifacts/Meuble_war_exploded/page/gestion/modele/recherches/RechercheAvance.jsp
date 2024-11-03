<%@ page import="affichageData.ViewRechercheAvance" %>
<%@ page import="entity.Categorie" %>
<%@ page import="entity.Style" %>
<%@ page import="entity.Volume" %>
<%
    ViewRechercheAvance vra = new ViewRechercheAvance();
%>

<section id="features" class="features">
    <div class="container">
        <div class="section-title-col-2">
            <a href="index.jsp?pagePath=page/gestion/modele/MenuRecherche.jsp">
                <div class="icon-box"
                     style="width: 75px; height: 40px; background-color: rgb(245,245,245); display: flex;
                     justify-content: center; align-items: center;">
                    <i class="bi bi-arrow-left-short" style="font-size: 33px;"></i>
                </div>
            </a>
            <h2>Recherche avanc&eacute;</h2>
        </div>
        <form id="rechercheAvanceForm" action="RechercheAvance.SearchController" method="post">
            <div class="row">
                <div class="col-lg-4 col-md-4 mb-4">
                    <div class="card" data-aos="fade-right" data-aos-delay="300">
                        <div class="card-body">
                            <div class="row form-group">
                                <div class="col-lg-1 col-md-1">
                                    <div class="my-1">
                                        <input name="checkCategorie" type="checkbox" class="form-check-input">
                                    </div>
                                </div>
                                <div class="col-lg-4 col-md-4">
                                    <div class="my-1">Categorie</div>
                                </div>
                                <div class="col-lg-7 col-md-7">
                                    <select name="idcategorie" class="form-select">
                                        <%
                                            for(Categorie categorie : vra.categories){
                                        %>
                                                <option value="<%= categorie.getId() %>"><%= categorie.getNom() %></option>
                                        <%
                                            }
                                        %>
                                    </select>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
                <div class="col-lg-4 col-md-4 mb-4">
                    <div class="card" data-aos="fade-down" data-aos-delay="300">
                        <div class="card-body">
                            <div class="row form-group">
                                <div class="col-lg-1 col-md-1">
                                    <div class="my-1">
                                        <input name="checkStyle" type="checkbox" class="form-check-input">
                                    </div>
                                </div>
                                <div class="col-lg-4 col-md-4">
                                    <div class="my-1">Style</div>
                                </div>
                                <div class="col-lg-7 col-md-7">
                                    <select name="idstyle" class="form-select">
                                        <%
                                            for(Style style : vra.styles){
                                        %>
                                                <option value="<%= style.getId() %>"><%= style.getNomstyle() %></option>
                                        <%
                                            }
                                        %>
                                    </select>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
                <div class="col-lg-4 col-md-4 mb-4">
                    <div class="card" data-aos="fade-left" data-aos-delay="300">
                        <div class="card-body">
                            <div class="row form-group">
                                <div class="col-lg-1 col-md-1">
                                    <div class="my-1">
                                        <input name="checkVolume" type="checkbox" class="form-check-input">
                                    </div>
                                </div>
                                <div class="col-lg-3 col-md-3">
                                    <div class="my-1">Volume</div>
                                </div>
                                <div class="col-lg-4 col-md-4">
                                    <select name="volumeComparaison" class="form-select">
                                        <option value="=">est</option>
                                        <option value=">=">au moin</option>
                                        <option value="<=">au plus</option>
                                    </select>
                                </div>
                                <div class="col-lg-4 col-md-4">
                                    <select name="echelleVolume" class="form-select">
                                        <%
                                            for(Volume volume : vra.volumes){
                                        %>
                                                <option value="<%= volume.getEchelle() %>"><%= volume.getNomVolume() %></option>
                                        <%
                                            }
                                        %>
                                    </select>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>


                <div class="col-lg-6 col-md-6 mb-4">
                    <div class="card" data-aos="fade-right" data-aos-delay="300">
                        <div class="card-body">
                            <div class="row form-group">
                                <div class="col-lg-1 col-md-1">
                                    <div class="my-1">
                                        <input name="checkPrix" type="checkbox" class="form-check-input">
                                    </div>
                                </div>
                                <div class="col-lg-2 col-md-2">
                                    <div class="my-1">Prix</div>
                                </div>
                                <div class="col-lg-4 col-md-4">
                                    <select name="prixComparaison" class="form-select">
                                        <option value=">=">au moin</option>
                                        <option value="<=">au plus</option>
                                        <option value="=">est</option>
                                    </select>
                                </div>
                                <div class="col-lg-5 col-md-5">
                                    <input name="prix" type="number" step="0.1" class="form-control" placeholder="Ariary">
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
                <div class="col-lg-6 col-md-6 mb-4">
                    <div class="card" data-aos="fade-left" data-aos-delay="300">
                        <div class="card-body">
                            <div class="row form-group">
                                <div class="col-lg-1 col-md-1">
                                    <div class="my-1">
                                        <input name="checkBenefice" type="checkbox" class="form-check-input">
                                    </div>
                                </div>
                                <div class="col-lg-2 col-md-2">
                                    <div class="my-1">Benefice</div>
                                </div>
                                <div class="col-lg-4 col-md-4">
                                    <select name="beneficeComparaison" class="form-select">
                                        <option value=">=">au moin</option>
                                        <option value="<=">au plus</option>
                                        <option value="=">est</option>
                                    </select>
                                </div>
                                <div class="col-lg-5 col-md-5">
                                    <input name="benefice" type="number" step="0.1" class="form-control" placeholder="Ariary">
                                </div>
                            </div>

                        </div>
                    </div>
                </div>

                <div class="col-lg-4 col-md-4 mb-4 offset-4" data-aos="fade-up" data-aos-delay="300">
                    <div class="text-center">
                        <button type="submit" class="btn btn-primary validebouton">
                            <i class="ri-search-line mx-1" style="color: #ffffff;"></i>
                            <span>Rechercher</span>
                        </button>
                    </div>
                </div>
            </div>
        </form>

        <div class="row" data-aos="fade-up" data-aos-delay="500">
            <hr>
            <table class="table" id="searchResultTable">
                <thead>
                    <tr>
                        <th scope="col">Modele</th>
                        <th scope="col">Categorie</th>
                        <th scope="col">Style</th>
                        <th scope="col">Volume</th>
                        <th scope="col">Prix</th>
                        <th scope="col">Benefice</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>...</td>
                        <td>...</td>
                        <td>...</td>
                        <td>...</td>
                        <td>...</td>
                        <td>...</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</section>