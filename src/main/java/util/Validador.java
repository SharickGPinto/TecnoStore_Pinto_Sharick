
package util;


public class Validador {
        private boolean correoValido(String correo){
        return correo != null && correo.matches(".+@.+\\..+");
    }
    
}
