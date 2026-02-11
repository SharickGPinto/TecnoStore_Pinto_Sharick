
package modelo;
// ItemVenta esta vinculado con Venta, es de un celular, se mira la cantidad que se vaya a comprar dando el subtotal
public class ItemVenta {
    private int id;
    private Celular celular;
    private int cantidad;
    private double subtotal;
    private Venta venta;

    public ItemVenta(int id, Celular celular, int cantidad, double subtotal, Venta venta) {
        this.id = id;
        this.celular = celular;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
        this.venta = venta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Celular getCelular() {
        return celular;
    }

    public void setCelular(Celular celular) {
        this.celular = celular;
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

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

}
