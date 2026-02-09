
package modelo;

public class Venta {

  private int id;
  private Cliente cliente;
  private  String fecha;
  private double totalSinIva;
  private double totalConIva;

    public Venta(int id, Cliente cliente, String fecha, double totalSinIva, double totalConIva) {
        this.id = id;
        this.cliente = cliente;
        this.fecha = fecha;
        this.totalSinIva = totalSinIva;
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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getTotalSinIva() {
        return totalSinIva;
    }

    public void setTotalSinIva(double totalSinIva) {
        this.totalSinIva = totalSinIva;
    }

    public double getTotalConIva() {
        return totalConIva;
    }

    public void setTotalConIva(double totalConIva) {
        this.totalConIva = totalConIva;
    }

}
