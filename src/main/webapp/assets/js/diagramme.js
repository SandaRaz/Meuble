function afficheDonut(){
    let diag = document.getElementById("charte");
    let selectIdModele = document.getElementById("selectedIdModele");
    let idModele = selectIdModele.value;

    console.log('IdModele: '+idModele);

    if(diag != null){
        $.ajax({
            type:'GET',
            url: 'afficheDonut.MetierController',
            data: {
                idModele: idModele
            },
            success: function(response){
                let columns = [];
                $.each(response, function(key,value){
                    let row = [key,value];
                    columns.push(row);
                });
                console.log(columns);

                var chart = c3.generate({
                    bindto: "#charte",
                    data: {
                        columns: columns,
                        type: 'donut',
                        onclick: function(d, i) { console.log("onclick", d, i); },
                        onmouseover: function(d, i) { console.log("onmouseover", d, i); },
                        onmouseout: function(d, i) { console.log("onmouseout", d, i); },

                        colors: {
                            data1: '#5969ff',
                            data2: '#ff407b'
                        }
                    },
                    donut: {
                        title: ""
                    }
                });
            },
            error: function(error){
                console.log('Erreur lors de la requete Ajax: ', error);
            }
        })
    }
}