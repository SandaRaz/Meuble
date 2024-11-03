const advancedSearchForm = document.getElementById('rechercheAvanceForm');
if(advancedSearchForm !== null){
    $(document).ready(function(){
       $('#rechercheAvanceForm').submit(function(e){
           e.preventDefault();

           let formData = $(this).serialize();

           $.ajax({
               type: 'POST',
               url: 'RechercheAvance.SearchController',
               data: formData,
               success: function(response){
                    $('#searchResultTable tbody').empty();

                    $.each(response, function(index, modele){
                        console.log(modele);
                        let row = '<tr>' +
                            '<td>' + modele.nomModele + '</td>' +
                            '<td>' + modele.nomcategorie + '</td>' +
                            '<td>' + modele.nomstyle + '</td>' +
                            '<td>' + modele.nomvolume + '</td>' +
                            '<td>' + modele.prix + '</td>' +
                            '<td>' + modele.benefice + '</td>' +
                            '</tr>';
                        $('#searchResultTable tbody').append(row);
                    });
               },
               error: function(error){
                   console.log('Erreur lors de la requete Ajax: ', error);
               }
           });
       });
    });
}