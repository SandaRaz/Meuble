/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exception;

/**
 *
 * @author Sanda
 */
public class StockManquante extends Exception{
    private double stockRequis;
    private double stockRestante;

    public double getStockRequis() {
        return stockRequis;
    }

    public void setStockRequis(double stockRequis) {
        this.stockRequis = stockRequis;
    }

    public double getStockRestante() {
        return stockRestante;
    }

    public void setStockRestante(double stockRestante) {
        this.stockRestante = stockRestante;
    }

    public StockManquante(String message, double stockRequis, double stockRestante) {
        super(message);
        this.stockRequis = stockRequis;
        this.stockRestante = stockRestante;
    }
    
    
}
