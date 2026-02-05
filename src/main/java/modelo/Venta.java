
package modelo;

import java.time.LocalDateTime;
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
        this.items = items;
        this.totalSinIva = totalSinIva;
        this.iva = iva;
        this.totalConIva = totalConIva;
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
        this.items = items;
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
  
}
