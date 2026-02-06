
package modelo;


public class Celular {
    private int id;
    private String marca, modelo, sistemaOS;
    private CategoriaGama gama;
    private int  stock;
    private double precio;

    public Celular(int id, String marca, String modelo, String sistemaOS, CategoriaGama gama, int stock, double precio) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.sistemaOS = sistemaOS;
        this.gama = gama;
        this.stock = stock;
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
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

    @Override
    public String toString() {
      return """
               Marca  %s
               Modelo %s
               SistemaOS   %s
               Categoria   %
               """.formatted(marca,modelo,sistemaOS,gama);   
    }
    
  
}
