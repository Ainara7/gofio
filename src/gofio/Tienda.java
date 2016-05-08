package gofio;
public class Tienda{
    
    //en kilos
    private int capacidad;
    private int actual = 0;
    
    
    public Tienda(int capacidad){
        this.capacidad = capacidad;
    }
    
    public synchronized boolean vender(int idAgricultor, int sacos){ //El agricultor intenta vender N sacos de 20kg
        
        long inicio = System.currentTimeMillis();
        long end = inicio + ValoresSimulacion.esperaVenta();
    
        Log.intentandoVender(idAgricultor, actual, sacos, end);
        while( actual > (capacidad - 20) && System.currentTimeMillis() < end){
            
            try{
                wait(end-inicio);
            }catch(Exception e){}
            
        }
        
        if( actual < (capacidad - 20) ){
            
            Log.vendido(idAgricultor, actual, sacos);
            actual = actual + sacos * 20;
            notify();
            return true;
        }
    
        Log.noPudoVender(idAgricultor, actual, sacos);
        return false;
    }
    public synchronized boolean comprar(int idCliente, int kilos){//El cliente intenta comprar N kilos
    
        long inicio = System.currentTimeMillis();
        long end = inicio + ValoresSimulacion.esperaCompra();
    
        Log.intentandoComprar(idCliente, actual, kilos, end);
        while( actual < kilos && System.currentTimeMillis() < end){
            
            try{
                wait(end-inicio);
            }catch(Exception e){}
            
        }
        
        if( actual >= kilos ){
            Log.comprado(idCliente, actual, kilos);
            actual = actual - kilos;
            notify();
            return true;
        }
    
        Log.noPudoComprar(idCliente, actual, kilos);
        return false;
    }
    
    public int stock(){ //kilos en stock
        return actual;
    }
}
