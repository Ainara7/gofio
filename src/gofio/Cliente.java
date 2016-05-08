package gofio;
public class Cliente extends Thread{
    
    int id;
    private Tienda tienda;
    private int cantidad = 0;
    
    public Cliente(int id, Tienda t){
        this.id=id;
        this.tienda = t;
    }
    
    public int comprado(){
        return cantidad;
    }
    
    public void run(){
        
        while(true){
    
            int cantidad_comprada = ValoresSimulacion.cantidadAComprar();
            int tiempo = ValoresSimulacion.tiempoConsumoKilo();
            
            boolean intento = tienda.comprar(this.id, cantidad_comprada);
            
            if( intento == true ){
            
                try{
                    Thread.sleep(tiempo);
                }catch(Exception e){}
                
                this.cantidad = cantidad + cantidad_comprada;
                
            }else{
                return;
            }
            
        }
        
    }
}
