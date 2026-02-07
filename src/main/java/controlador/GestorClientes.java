package controlador;

import java.util.ArrayList;
import modelo.Cliente;

public class GestorClientes {

    private ArrayList<Cliente> clientes = new ArrayList<>();
    
    //Aca se hace para la validacion del correo 
    private boolean correoValido(String correo){
        return correo != null && correo.matches(".+@.+\\..+");
    }

    /*
    Aca se empieza a utilizar Stream API
    para registrar, lambda para listar, buscar por id Stream API 
    eliminar, actualizar y descontar stock
     */
    
    // Si cumple la condicion de registrar tiene que verificar si la identificacion existe, si existe genera error, si no existe se registra dentro del ArrayList de clientes.
    public void registrar(Cliente c) {
        boolean existe = clientes.stream().anyMatch(x -> x.getIdentificacion().equals(c.getIdentificacion()));
        if (existe) {
            System.out.println("ERROR: YA EXISTE ESTE CLIENTE CON ESTE ID: " + c.getIdentificacion()+ c.getNombre());
            return;
        }
         if(!correoValido(c.getCorreo())){
             System.out.println("ERRO CORREO INVALIDO " + c.getCorreo());
             return;
         }
        clientes.add(c);
        System.out.println("Cliente registrado");
    }
    
    //Se verifica si hay clientes, si no hay se muestra un mensaje, pero si existe muestra los clientes que existen.
    public void listar(){
        if(clientes.isEmpty()){
            System.out.println("NO HAY CLIENTES");
            return;
        }
        clientes.forEach(System.out::println );
    }
    
   // Se filtra por la ident y debe devolver el cliente que tenga esa identif, si no existe un cliente que le pertenezca ese identif, retorna en nulo.
    public Cliente buscarPorIdentificacion(String identificacion){
        return clientes.stream()
                .filter(c -> c.getIdentificacion().equals(identificacion))
                .findFirst()
                .orElse(null);
       
    }
    
    
    //Se busca la identificacion del cliente, si existe se elimina, si no existe un cliente con esa identificacion no hace nada.
    public void eliminar(String identificacion){
        boolean eliminado = clientes.removeIf(c -> c.getIdentificacion().equals(identificacion));
        
        if(eliminado){
            System.out.println("Cliente eliminado");
        }else {
            System.out.println("No existe cliente con ese ID");
        }
    }
    
 //Aca se busca por la identificacion del cliente, si no existe se termina el proceso de buscar, pero si existe se empieza a actualizar el cliente obteniendo sus referencias
    public void actualizar(Cliente c){
        Cliente existe = buscarPorIdentificacion(c.getIdentificacion());
        if (existe == null){
            System.out.println("No existe");
            return;
        }
        if(!correoValido(c.getCorreo())){
            System.out.println("ERROR: CORREO INVALIDO " + c.getCorreo());
            return;
        }
        
        existe.setCorreo(c.getCorreo());
        existe.setNombre(c.getNombre());
        existe.setTelefono(c.getTelefono());
        System.out.println("Cliente Actualizado");
    }

   
}
