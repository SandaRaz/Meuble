function confirmDeleteModele(idModele){
    const idElement = 'confirmDelete' + idModele;
    const confirmDeleteDialog = document.getElementById(idElement);
    if(confirmDeleteDialog != null){
        console.log('Delete '+idModele+' ?');
        const deleteDialogs = document.getElementsByClassName('deleteDialog');
        for(var i = 0 ; i < deleteDialogs.length ; i++){
            console.log(deleteDialogs[i]);
            deleteDialogs[i].style.display = 'none';
        }
        confirmDeleteDialog.style.display = 'block';
    }
}

function deletingModele(idModele){
    window.location.href = "DeleteModele.FormController?idModele="+idModele;
}

function cancelDeleteModele(idModele){
    const idElement = 'confirmDelete' + idModele;
    const confirmDeleteDialog = document.getElementById(idElement);
    if(confirmDeleteDialog != null){
        console.log('Cancel Deleteting '+idModele+' ?');

        confirmDeleteDialog.style.display = 'none';
    }
}

// --------------------------------- RESTAURER -----------------------------------

function confirmRestoreModele(idModele){
    const idElement = 'confirmRestore' + idModele;
    const confirmRestoreDialog = document.getElementById(idElement);
    if(confirmRestoreDialog != null){
        console.log('Restore '+idModele+' ?');
        const restoreDialogs = document.getElementsByClassName('restoreDialog');
        for(let i = 0 ; i < restoreDialogs.length ; i++){
            console.log(restoreDialogs[i]);
            restoreDialogs[i].style.display = 'none';
        }
        confirmRestoreDialog.style.display = 'block';
    }
}

function restoringModele(idModele){
    window.location.href = "RestoreModele.FormController?idModele="+idModele;
}

function cancelRestoreModele(idModele){
    const idElement = 'confirmRestore' + idModele;
    const confirmRestoreDialog = document.getElementById(idElement);
    if(confirmRestoreDialog != null){
        console.log('Cancel Restoring '+idModele+' ?');

        confirmRestoreDialog.style.display = 'none';
    }
}

function effacerPanierProduit(idModele){
    const idProduit = '#panierProduit'+idModele;

    $.ajax({
        type: 'POST',
        url: 'EffacerPanierProduit.ClientController',
        data: {
            idModele: idModele
        },
        success: function(response){
            $('#panier '+idProduit).remove()
            console.log(response);
        },
        error: function(error){
            console.log('Erreur lors de la requete Ajax: ', error);
        }
    });
}