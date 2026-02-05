
package MODELO;


public class detalle_ventas {
    private int id;
    private int id_venta, id_celular;
    private int cantidad;
    private double subtotal;

    public detalle_ventas(int id, int id_venta, int id_celular, int cantidad, double subtotal) {
        this.id = id;
        this.id_venta = id_venta;
        this.id_celular = id_celular;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_venta() {
        return id_venta;
    }

    public void setId_venta(int id_venta) {
        this.id_venta = id_venta;
    }

    public int getId_celular() {
        return id_celular;
    }

    public void setId_celular(int id_celular) {
        this.id_celular = id_celular;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
    
}
