<%@ page import="affichageData.ViewAjoutMeuble" %>
<%@ page import="affichageData.ViewAjoutEmploye" %>
<%@ page import="affichageData.ViewAchatClient" %>
<%@ page import="entity.*" %>
<%@ page import="entityView.ClientComplet" %>
<%
    ViewAchatClient viewAchatClient = new ViewAchatClient();
%>
<section id="features" class="features">
    <div class="container aos-init aos-animate" data-aos="fade-up">
        <div class="section-title-col-2 aos-init aos-animate" data-aos="fade-up">
            <a href="index.jsp?pagePath=page/gestion/client/MenuClient.jsp">
                <div class="icon-box"
                     style="width: 75px; height: 40px; background-color: rgb(245,245,245); display: flex;
                     justify-content: center; align-items: center;">
                    <i class="bi bi-arrow-left-short" style="font-size: 33px;"></i>
                </div>
            </a>
            <h2>Achat de produit</h2>
        </div>
        <div class="row">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">Nom</th>
                    <th scope="col">Genre</th>
                    <th scope="col"></th>
                </tr>
                </thead>
                <tbody>
                <%
                    for(ClientComplet client : viewAchatClient.clients){
                %>
                        <tr>
                            <td>
                                <div class="my-2"><%= client.getNom() %></div>
                            </td>
                            <td>
                                <div class="my-2"><%= client.getGenre() %></div>
                            </td>
                            <td>
                                <a href="FaireAchat.ClientController?idClient=<%= client.getId() %>" class="btn btn-info">
                                    achat
                                </a>
                            </td>
                        </tr>
                <%
                    }
                %>
                </tbody>
            </table>
        </div>
    </div>
</section>
