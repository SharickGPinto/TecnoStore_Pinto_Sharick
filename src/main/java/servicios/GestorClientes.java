package servicios;



import java.util.ArrayList;
import modelo.Cliente;
import persistencia.ClienteDAO;
import persistencia.implementar.ClienteDAOImple;

public class GestorClientes {
    
    private final ClienteDAO clienteDAO = new ClienteDAOImple();
    
    //Aca se hace para la validacion del correo 
    private boolean correoValido(String correo){
        return correo != null && correo.matches(".+@.+\\..+");
    }
    
    //Verifica en la base de datos si existe con esa identificacion, si no existe y el correo es valido se registra
    public void registrar(Cliente c) {
        Cliente existe = clienteDAO.buscar(c.getIdentificacion());
        if (existe != null) {
            System.out.println("ERROR: YA EXISTE ESTE CLIENTE CON ESTE ID: " + c.getIdentificacion()+ c.getNombre());
            return;
        }
         if(!correoValido(c.getCorreo())){
             System.out.println("ERROR CORREO INVALIDO " + c.getCorreo());
             return;
         }
        clienteDAO.registrar(c);
        System.out.println("Cliente registrado");
    }
    
    //mira internamente dentro de la base de datos si hay clientes, si existen la muestra, si no muestra mensaje
    public void listar(){
        ArrayList<Cliente> listar = clienteDAO.listar();
        if(listar.isEmpty()){
            System.out.println("NO HAY CLIENTES");
            return;
        }
        listar.forEach(System.out::println );
    }

//busca dentro de la base de datos si existe la identificacion del cliente    
    public Cliente buscarPorIdentificacion(String identificacion){
        return clienteDAO.buscar(identificacion);
       
    }
    
    //busca al cliente en la base de datos y si existe lo elimina, si no existe envia mensaje
    public void eliminar(String identificacion){
        
        Cliente existe = clienteDAO.buscar(identificacion);
        
        if(existe != null){
            clienteDAO.eliminar(identificacion);
            System.out.println("Cliente eliminado");
        }else {
            System.out.println("No existe cliente con ese ID");
        }
    }
    
    //Valida la existencia del cliente y si el correo esta bien para poder actualizar
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
        
        clienteDAO.actualizar(c, c.getIdentificacion());
        System.out.println("Cliente Actualizado");
    }

   
}
