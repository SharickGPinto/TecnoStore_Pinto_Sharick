
package modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Venta {

  private int id;
  private Cliente cliente;
  private  LocalDateTime fecha;
  private List<ItemVenta> items;
  private double totalSinIva;
  private double iva;
  private double totalConIva;

    public Venta(int id, Cliente cliente, LocalDateTime fecha, List<ItemVenta> items, double totalSinIva, double iva, double totalConIva) {
        this.id = id;
        this.cliente = cliente;
        this.fecha = fecha;
        this.totalSinIva = totalSinIva;
        this.iva = iva;
        this.totalConIva = totalConIva;
        
        if(items == null){
            this.items = new ArrayList<>();
        }else{
            this.items = items;
        }
            calcularTotal();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public List<ItemVenta> getItems() {
        return items;
    }

    public void setItems(List<ItemVenta> items) {
        if(items == null){
            this.items = new ArrayList<>();
        }else{
         this.items = items;   
        }
        calcularTotal();
    }

    public double getTotalSinIva() {
        return totalSinIva;
    }

    public void setTotalSinIva(double totalSinIva) {
        this.totalSinIva = totalSinIva;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public double getTotalConIva() {
        return totalConIva;
    }

    public void setTotalConIva(double totalConIva) {
        this.totalConIva = totalConIva;
    }


       public void calcularTotal (){
       double suma = 0;
           for (int i = 0; i < items.size(); i++) {
               suma += items.get(i).getSubtotal();
                              
           }
           
           this.totalSinIva = suma;
           this.iva = suma * 0.19;
           this.totalConIva = this.totalSinIva + this.iva;
      
    }
       
       public void agregarItem(ItemVenta item){
           this.items.add(item);
           calcularTotal();
       }
  
}
