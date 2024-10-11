/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package association;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DIVA
 */
public class ModeleEmployes {
    private int id;
    private String idModele;
    private int idPoste;
    private int nombre;
    private double minHeureTravail;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdModele() {
        return idModele;
    }

    public void setIdModele(String idModele) {
        this.idModele = idModele;
    }


    public int getIdPoste() {
        return idPoste;
    }

    public void setIdPoste(int idPoste) {
        this.idPoste = idPoste;
    }

    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) {
        if(nombre < 0){
            try {
                throw new Exception("Nombre negatif en parametre");
            } catch (Exception ex) {
                Logger.getLogger(ModeleEmployes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            this.nombre = nombre;
        }
    }

    public double getMinHeureTravail() {
        return minHeureTravail;
    }

    public void setMinHeureTravail(double minHeureTravail) {
        if(minHeureTravail < 0){
            try {
                throw new Exception("Nombre negatif en parametre");
            } catch (Exception ex) {
                Logger.getLogger(ModeleEmployes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            this.minHeureTravail = minHeureTravail;
        }
    }
    
    public ModeleEmployes() {
    }
    
    public ModeleEmployes(int id, String idModele, int idPoste, int nombre, double minHeureTravail) {
        this.id = id;
        this.idModele = idModele;
        this.idPoste = idPoste;
        this.nombre = nombre;
        this.minHeureTravail = minHeureTravail;
    }
    
    public ModeleEmployes(String idModele, int idPoste, int nombre, double minHeureTravail) {
        this.idModele = idModele;
        this.idPoste = idPoste;
        this.nombre = nombre;
        this.minHeureTravail = minHeureTravail;
    }
}
