
package modelo;

public class ItemVenta {
    private Celular celular;
    private int cantidad;

    public ItemVenta(Celular celular, int cantidad) {
        this.celular = celular;
        this.cantidad = cantidad;
    }
    
     public Celular getCelular(){
         return celular;}
     
     public void setCelular(Celular celular){
         this.celular = celular;
     }
     public int getCantidad(){
         return cantidad;
     }
     public void setCantidad(int cantidad){
         this.cantidad = cantidad;
     }
     
     public double getSubtotal(){
         return celular.getPrecio() * cantidad;
     }
}
