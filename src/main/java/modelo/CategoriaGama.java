
package modelo;

/*
Se crea categoriagama como enum para evitar que el usuario inserte datos incorrectos
*/
public enum CategoriaGama {
    
    ALTA, MEDIA, BAJA;
    
    public static CategoriaGama getALTA() {
        return ALTA;
    }

    public static CategoriaGama getMEDIA() {
        return MEDIA;
    }

    public static CategoriaGama getBAJA() {
        return BAJA;
    }
}
