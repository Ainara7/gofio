/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gofio;

public class Agricultor extends Thread{
    
    int id;
    private Tienda tienda;
    private int cantidad_vendida = 0;
    
    public Agricultor(int id, Tienda t){
        this.id=id;
        this.tienda = t;
    }
    
    public int vendido(){ 
        // RETORNAR nº de Kg
        return cantidad_vendida; 
    }
    
    public void run(){
        
        for(int i=0; i<5; i++){
            
            int tiempo = ValoresSimulacion.tiempoCosecha();
            
            try{
                Thread.sleep(tiempo);
            }catch(Exception e){}
            
            //devuelve el numero de sacos que vamos a vender
            int cantidad_cosechada = ValoresSimulacion.cantidadCosechada();
            
            boolean intento = tienda.vender(id, cantidad_cosechada);
            
            if( intento == true ){
                
                this.cantidad_vendida = this.cantidad_vendida + cantidad_cosechada * 20;
                
            }
            
        } 
        
    }
    
}
