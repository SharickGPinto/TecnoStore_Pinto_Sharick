
package modelo;


public class Celular {
    private int id;
    private String  modelo, sistemaOS;
    private CategoriaGama gama;
    private int  stock;
    private double precio;
    private Marca id_marca;

    public Celular(int id, String modelo, String sistemaOS, CategoriaGama gama, int stock, double precio, Marca id_marca) {
        this.id = id;
        this.modelo = modelo;
        this.sistemaOS = sistemaOS;
        this.gama = gama;
        this.stock = stock;
        this.precio = precio;
        this.id_marca = id_marca;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getSistemaOS() {
        return sistemaOS;
    }

    public void setSistemaOS(String sistemaOS) {
        this.sistemaOS = sistemaOS;
    }

    public CategoriaGama getGama() {
        return gama;
    }

    public void setGama(CategoriaGama gama) {
        this.gama = gama;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Marca getId_marca() {
        return id_marca;
    }

    public void setId_marca(Marca id_marca) {
        this.id_marca = id_marca;
    }


    @Override
    public String toString() {
      return """
                 %s ID DEL CELULAR  
               Marca  %s
               Modelo %s
               SistemaOS   %s
               Categoria   %s
               Precio %s
               stock %s
               """.formatted(id,id_marca,modelo,sistemaOS,gama, precio, stock);   
    }
    

}
