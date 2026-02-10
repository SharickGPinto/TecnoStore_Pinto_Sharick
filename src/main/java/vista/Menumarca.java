
package vista;

import java.util.Scanner;
import modelo.Marca;
import servicios.GestorMarca;


public class Menumarca {
    
     GestorMarca gm = new GestorMarca();

    private void registrar() {
        Marca m = new Marca(0, "");
        System.out.println("Ingrese el nombre de la marca");
        m.setNombre(new Scanner(System.in).nextLine());
        gm.registrar(m);
    }

    private void actualizar() {
        System.out.println("Ingrese el id de la marca a actualizar");
        int id = new Scanner(System.in).nextInt();
        Marca m = gm.buscarPorId(id);
        if (m != null && m.getId() != 0) {
            System.out.println("MARCA ENCONTRADA: " + m.getNombre());
            System.out.println("Ingrese el nuevo nombre");
            m.setNombre(new Scanner(System.in).nextLine());
            gm.actualizar(m);
        } else {
            System.out.println("No existe una marca con ese ID");
        }
    }

    private void eliminar() {
        System.out.println("Ingrese el id de la marca a eliminar");
        int id = new Scanner(System.in).nextInt();
        gm.eliminar(id);
    }

    private void listar() {
        gm.listar();
    }

    private void buscar() {
        System.out.println("Ingrese el id de la marca a buscar");
        int id = new Scanner(System.in).nextInt();
        Marca m = gm.buscarPorId(id);
        if (m != null && m.getId() != 0) {
            System.out.println("ID: " + m.getId() + " | Nombre: " + m.getNombre());
        } else {
            System.out.println("No existe una marca con ese ID");
        }
    }

    public void menu() {
        int op = 0;
        do {
            System.out.println("""
                               ******************************
                                        Marcas
                               1.   Registrar
                               2.   Actualizar
                               3.   Eliminar
                               4.   Listar
                               5.   Buscar
                               6.   Regresar
                               """);
            op = new Scanner(System.in).nextInt();
            while (op < 1 || op > 6) {
                System.out.println("Error, opcion no valida");
                op = new Scanner(System.in).nextInt();
            }
            switch (op) {
                case 1:
                    registrar();
                    break;
                case 2:
                    actualizar();
                    break;
                case 3:
                    eliminar();
                    break;
                case 4:
                    listar();
                    break;
                case 5:
                    buscar();
                    break;
            }
        } while (op != 6);
    }
}
