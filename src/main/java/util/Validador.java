
package util;


public class Validador {
       public static boolean correoValido(String correo){
        return correo != null && correo.matches(".+@.+\\..+");
    }
    
}
