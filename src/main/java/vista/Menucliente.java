
package vista;

import java.util.Scanner;
import modelo.Cliente;
import servicios.GestorClientes;


public class Menucliente {
    GestorClientes gc = new GestorClientes();
    Scanner sc = new Scanner(System.in);
    
    private void registrar() {
        System.out.println("\n--- REGISTRAR CLIENTE ---");
        
        System.out.print("Ingrese el nombre del cliente: ");
        String nombre = sc.nextLine();
        
        System.out.print("Ingrese la identificacion: ");
        String identificacion = sc.nextLine();
        
        System.out.print("Ingrese el correo electronico: ");
        String correo = sc.nextLine();
        
        System.out.print("Ingrese el telefono: ");
        String telefono = sc.nextLine();
        
        Cliente cliente = new Cliente(0, nombre, identificacion, correo, telefono);
        gc.registrar(cliente);
    }
    
    private void listar() {
        System.out.println("\n--- LISTADO DE CLIENTES ---");
        gc.listar();
    }
    
    private void buscar() {
        System.out.print("\nIngrese la identificacion del cliente a buscar: ");
        String identificacion = sc.nextLine();
        
        Cliente cli = gc.buscarPorIdentificacion(identificacion);
        if (cli != null && cli.getId() != 0) {
            System.out.println("\nCLIENTE ENCONTRADO:");
            System.out.println(cli);
        } else {
            System.out.println("No existe un cliente con esa identificacion");
        }
    }
    
    private void actualizar() {
        System.out.print("\nIngrese la identificacion del cliente a actualizar: ");
        String identificacion = sc.nextLine();
        
        Cliente cli = gc.buscarPorIdentificacion(identificacion);
        if (cli != null && cli.getId() != 0) {
            System.out.println("\nCLIENTE ENCONTRADO:");
            System.out.println(cli);
            
            System.out.println("""
            Que desea modificar?
            1. Nombre
            2. Identificacion
            3. Correo
            4. Telefono
            """);
            System.out.print("Opcion: ");
            int opcion = sc.nextInt();
            sc.nextLine(); 
            
            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el nuevo nombre: ");
                    cli.setNombre(sc.nextLine());
                    break;
                case 2:
                    System.out.print("Ingrese la nueva identificacion: ");
                    cli.setIdentificacion(sc.nextLine());
                    break;
                case 3:
                    System.out.print("Ingrese el nuevo correo: ");
                    cli.setCorreo(sc.nextLine());
                    break;
                case 4:
                    System.out.print("Ingrese el nuevo telefono: ");
                    cli.setTelefono(sc.nextLine());
                    break;
            }
            
            gc.actualizar(cli);
        } else {
            System.out.println("No existe un cliente con esa identificacion");
        }
    }
    
    private void eliminar() {
        System.out.print("\nIngrese la identificacion del cliente a eliminar: ");
        String identificacion = sc.nextLine();
        
        gc.eliminar(identificacion);
    }
    
    public void menu() {
        int op;
        do {
            System.out.println("""
            ==========================================
                    GESTION DE CLIENTES
            ==========================================
            1. Registrar Cliente
            2. Listar Clientes
            3. Buscar por Identificacion
            4. Actualizar Cliente
            5. Eliminar Cliente
            6. Regresar
            ==========================================
            """);
            System.out.print("Seleccione una opcion: ");
            op = sc.nextInt();
            sc.nextLine(); 
            
            while (op < 1 || op > 6) {
                System.out.println("Error, opcion no valida");
                System.out.print("Seleccione una opcion: ");
                op = sc.nextInt();
                sc.nextLine();
            }
            
            switch (op) {
                case 1:
                    registrar();
                    break;
                case 2:
                    listar();
                    break;
                case 3:
                    buscar();
                    break;
                case 4:
                    actualizar();
                    break;
                case 5:
                    eliminar();
                    break;
            }
        } while (op != 6);
    }
    
}
