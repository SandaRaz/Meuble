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
            <h4>Ajout de nouveau poste</h4>
        </div>
        
        <div class="row">
            <div class="col-lg-6 col-md-6 aos-init aos-animate formgroup" data-aos="fade-up" data-aos-delay="300">
                <form action="AjoutPoste.FormController" method="post" role="form">
                    <div class="form-floating my-3">
                        <input name="titre" type="text" class="form-control" id="floatingInput1" placeholder="" required="">
                        <label for="floatingInput1">Nom du nouveau poste</label>
                    </div>
                    <div class="form-floating my-3">
                        <input name="salaireHeure" type="number" step="0.5" class="form-control" id="floatingInput2" placeholder="" required="">
                        <label for="floatingInput2">Salaire par heure</label>
                    </div>
                    <div class="form-floating my-3">
                        <input name="niveauTH" type="number" class="form-control" id="floatingInput3" placeholder="" required="">
                        <label for="floatingInput3">Niveau de taux horaire</label>
                    </div>
                    <div class="form-floating my-3">
                        <input name="hierarchie" type="text" class="form-control" id="floatingInput4" placeholder="" required="">
                        <label for="floatingInput4">Niveau hierarchique</label>
                    </div>
                    
                    <div class="text-center">
                        <button type="submit" class="btn btn-primary validebouton">Ajouter</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</section>