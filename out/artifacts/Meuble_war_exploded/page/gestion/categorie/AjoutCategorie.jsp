<section id="contact" class="contact">
    <div class="container">

        <div class="section-title-col-2 aos-init aos-animate" data-aos="fade-up">
            <a href="index.jsp?pagePath=page/gestion/categorie/MenuCategorie.jsp">
                <div class="icon-box" 
                     style="width: 75px; height: 40px; background-color: rgb(245,245,245); display: flex;
                     justify-content: center; align-items: center;">
                    <i class="bi bi-arrow-left-short" style="font-size: 33px;"></i>
                </div>
            </a>
            <h2>Nouveau catégorie de Meuble</h2>
        </div>

        <div class="row">
            <div class="col-lg-6 col-md-6 aos-init aos-animate formgroup" data-aos="fade-up" data-aos-delay="300">
                <form action="AjoutCategorie.FormController" method="post" role="form">
                    <div class="form-floating my-3">
                        <input name="nom" type="text" class="form-control" id="floatingInput" placeholder="" required="">
                        <label for="floatingInput">Nom de la nouvelle catégorie</label>
                    </div>
                    
                    <div class="text-center">
                        <button type="submit" class="btn btn-primary validebouton">Enregistrer</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</section>