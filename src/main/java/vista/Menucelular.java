package vista;

import java.util.Scanner;
import modelo.CategoriaGama;
import modelo.Celular;
import modelo.Marca;
import servicios.GestorCelulares;
import servicios.GestorMarca;

public class Menucelular {

    GestorCelulares gc = new GestorCelulares();
    GestorMarca gm = new GestorMarca(); 

    private void registrar() {
       
        System.out.println("********* MARCAS *********");
        gm.listar();
        System.out.println("Ingrese el id de la marca");
        Marca marca = gm.buscarPorId(new Scanner(System.in).nextInt());

        System.out.println("Ingrese el modelo del celular");
        String modelo = new Scanner(System.in).nextLine();

        System.out.println("Ingrese el sistema operativo");
        String sistemaOS = new Scanner(System.in).nextLine();

        System.out.println("""
                           Ingrese la gama:
                           1. ALTA  2. MEDIA  3. BAJA
                           """);
        int opGama = new Scanner(System.in).nextInt();
        while (opGama < 1 || opGama > 3) {
            System.out.println("Opcion no valida");
            opGama = new Scanner(System.in).nextInt();
        }
        CategoriaGama gama;
        switch (opGama) {
            case 1:
                gama = CategoriaGama.ALTA;
                break;
            case 2:
                gama = CategoriaGama.MEDIA;
                break;
            default:
                gama = CategoriaGama.BAJA;
                break;
        }

        System.out.println("Ingrese el stock");
        int stock = new Scanner(System.in).nextInt();

        System.out.println("Ingrese el precio");
        double precio = new Scanner(System.in).nextDouble();

        Celular cel = new Celular(0, modelo, sistemaOS, gama, stock, precio, marca);
        gc.registrar(cel);
    }

    private void actualizar() {
        System.out.println("Ingrese el id del celular a actualizar");
        int id = new Scanner(System.in).nextInt();
        Celular cel = gc.buscarPorId(id);
        if (cel != null && cel.getId() != 0) {
            System.out.println("CELULAR ENCONTRADO");
            System.out.println(cel);
            System.out.println("""
                               Ingrese lo que desea modificar:
                               1.   Modelo
                               2.   Sistema Operativo
                               3.   Gama
                               4.   Stock
                               5.   Precio
                               6.   Marca
                               """);
            int op = new Scanner(System.in).nextInt();
            while (op < 1 || op > 6) {
                System.out.println("Error, opcion no valida");
                op = new Scanner(System.in).nextInt();
            }
            switch (op) {
                case 1:
                    System.out.println("Ingrese el nuevo modelo");
                    cel.setModelo(new Scanner(System.in).nextLine());
                    break;
                case 2:
                    System.out.println("Ingrese el nuevo sistema operativo");
                    cel.setSistemaOS(new Scanner(System.in).nextLine());
                    break;
                case 3:
                    System.out.println("""
                                       1. ALTA  2. MEDIA  3. BAJA
                                       """);
                    int opGama = new Scanner(System.in).nextInt();
                    while (opGama < 1 || opGama > 3) {
                        System.out.println("Opcion no valida");
                        opGama = new Scanner(System.in).nextInt();
                    }
                    switch (opGama) {
                        case 1:
                            cel.setGama(CategoriaGama.ALTA);
                            break;
                        case 2:
                            cel.setGama(CategoriaGama.MEDIA);
                            break;
                        case 3:
                            cel.setGama(CategoriaGama.BAJA);
                            break;
                    }
                    break;
                case 4:
                    System.out.println("Ingrese el nuevo stock");
                    cel.setStock(new Scanner(System.in).nextInt());
                    break;
                case 5:
                    System.out.println("Ingrese el nuevo precio");
                    cel.setPrecio(new Scanner(System.in).nextDouble());
                    break;
                case 6:
                    
                    System.out.println("********* MARCAS *********");
                    gm.listar();
                    System.out.println("Ingrese el id de la nueva marca");
                    Marca marca = gm.buscarPorId(new Scanner(System.in).nextInt());
                    if (marca != null && marca.getId() != 0) {
                        cel.setId_marca(marca);
                    } else {
                        System.out.println("MARCA NO ENCONTRADA, NO SE ACTUALIZO");
                    }
                    break;
            }
            gc.actualizar(cel);
        } else {
            System.out.println("No existe un celular con ese ID");
        }
    }

    private void eliminar() {
        System.out.println("Ingrese el id del celular a eliminar");
        int id = new Scanner(System.in).nextInt();
        gc.eliminar(id);
    }

    private void listar() {
        gc.listar();
    }

    private void buscar() {
        System.out.println("Ingrese el id del celular a buscar");
        int id = new Scanner(System.in).nextInt();
        Celular cel = gc.buscarPorId(id);
        if (cel != null && cel.getId() != 0) {
            System.out.println(cel);
        } else {
            System.out.println("No existe un celular con ese ID");
        }
    }

    public void menu() {
        int op = 0;
        do {
            System.out.println("""
                               ******************************
                                       Celulares
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
