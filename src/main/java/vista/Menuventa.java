
package vista;

import java.util.Scanner;
import modelo.CategoriaGama;
import modelo.Celular;
import modelo.Marca;
import servicios.GestorCelulares;

public class Menuventa {
    GestorCelulares gc = new GestorCelulares();
    Scanner sc = new Scanner(System.in);
    
    private void registrar() {
        System.out.println("\n--- REGISTRAR CELULAR ---");
        
        System.out.print("Ingrese el modelo del celular: ");
        String modelo = sc.nextLine();
        
        System.out.print("Ingrese el sistema operativo: ");
        String sistemaOS = sc.nextLine();
        
        System.out.println("""
        Seleccione la gama:
        1. ALTA
        2. MEDIA
        3. BAJA
        """);
        System.out.print("Opcion: ");
        int opGama = sc.nextInt();
        sc.nextLine(); 
        
        CategoriaGama gama;
        switch (opGama) {
            case 1:
                gama = CategoriaGama.ALTA;
                break;
            case 2:
                gama = CategoriaGama.MEDIA;
                break;
            case 3:
                gama = CategoriaGama.BAJA;
                break;
            default:
                gama = CategoriaGama.MEDIA;
                break;
        }
        
        System.out.print("Ingrese el stock: ");
        int stock = sc.nextInt();
        
        System.out.print("Ingrese el precio: ");
        double precio = sc.nextDouble();
        sc.nextLine(); 
        
       
        System.out.println("""
        Marcas disponibles (use el ID):
        1. Samsung
        2. Apple
        3. Xiaomi
        4. Motorola
        5. Huawei
        """);
        System.out.print("Ingrese el ID de la marca: ");
        int idMarca = sc.nextInt();
        sc.nextLine(); 
        
        String nombreMarca;
        switch (idMarca) {
            case 1: nombreMarca = "Samsung"; break;
            case 2: nombreMarca = "Apple"; break;
            case 3: nombreMarca = "Xiaomi"; break;
            case 4: nombreMarca = "Motorola"; break;
            case 5: nombreMarca = "Huawei"; break;
            default: nombreMarca = "Samsung"; break;
        }
        
        Marca marca = new Marca(idMarca, nombreMarca);
        Celular celular = new Celular(0, modelo, sistemaOS, gama, stock, precio, marca);
        gc.registrar(celular);
    }
    
    private void listar() {
        System.out.println("\n--- LISTADO DE CELULARES ---");
        gc.listar();
    }
    
    private void buscar() {
        System.out.print("\nIngrese el ID del celular a buscar: ");
        int id = sc.nextInt();
        sc.nextLine(); 
        
        Celular cel = gc.buscarPorId(id);
        if (cel != null && cel.getId() != 0) {
            System.out.println("\nCELULAR ENCONTRADO:");
            System.out.println(cel);
        } else {
            System.out.println("No existe un celular con ese ID");
        }
    }
    
    private void actualizar() {
        System.out.print("\nIngrese el ID del celular a actualizar: ");
        int id = sc.nextInt();
        sc.nextLine(); 
        
        Celular cel = gc.buscarPorId(id);
        if (cel != null && cel.getId() != 0) {
            System.out.println("\nCELULAR ENCONTRADO:");
            System.out.println(cel);
            
            System.out.println("""
            Que desea modificar?
            1. Modelo
            2. Sistema Operativo
            3. Gama
            4. Stock
            5. Precio
            6. Marca
            """);
            System.out.print("Opcion: ");
            int opcion = sc.nextInt();
            sc.nextLine(); 
            
            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el nuevo modelo: ");
                    cel.setModelo(sc.nextLine());
                    break;
                case 2:
                    System.out.print("Ingrese el nuevo sistema operativo: ");
                    cel.setSistemaOS(sc.nextLine());
                    break;
                case 3:
                    System.out.println("""
                    Seleccione la nueva gama:
                    1. ALTA
                    2. MEDIA
                    3. BAJA
                    """);
                    System.out.print("Opcion: ");
                    int opGama = sc.nextInt();
                    sc.nextLine();
                    switch (opGama) {
                        case 1: cel.setGama(CategoriaGama.ALTA); break;
                        case 2: cel.setGama(CategoriaGama.MEDIA); break;
                        case 3: cel.setGama(CategoriaGama.BAJA); break;
                    }
                    break;
                case 4:
                    System.out.print("Ingrese el nuevo stock: ");
                    cel.setStock(sc.nextInt());
                    sc.nextLine();
                    break;
                case 5:
                    System.out.print("Ingrese el nuevo precio: ");
                    cel.setPrecio(sc.nextDouble());
                    sc.nextLine();
                    break;
                case 6:
                    System.out.println("""
                    Marcas disponibles:
                    1. Samsung
                    2. Apple
                    3. Xiaomi
                    4. Motorola
                    5. Huawei
                    """);
                    System.out.print("Ingrese el ID de la nueva marca: ");
                    int idMarca = sc.nextInt();
                    sc.nextLine();
                    
                    String nombreMarca;
                    switch (idMarca) {
                        case 1: nombreMarca = "Samsung"; break;
                        case 2: nombreMarca = "Apple"; break;
                        case 3: nombreMarca = "Xiaomi"; break;
                        case 4: nombreMarca = "Motorola"; break;
                        case 5: nombreMarca = "Huawei"; break;
                        default: nombreMarca = "Samsung"; break;
                    }
                    cel.setId_marca(new Marca(idMarca, nombreMarca));
                    break;
            }
            
            gc.actualizar(cel);
        } else {
            System.out.println("No existe un celular con ese ID");
        }
    }
    
    private void eliminar() {
        System.out.print("\nIngrese el ID del celular a eliminar: ");
        int id = sc.nextInt();
        sc.nextLine(); 
        
        gc.eliminar(id);
    }
    
    private void descontarStock() {
        System.out.print("\nIngrese el ID del celular: ");
        int id = sc.nextInt();
        
        System.out.print("Ingrese la cantidad a descontar: ");
        int cantidad = sc.nextInt();
        sc.nextLine(); 
        
        boolean resultado = gc.descontarStock(id, cantidad);
        if (resultado) {
            System.out.println("Stock descontado exitosamente!");
        } else {
            System.out.println("No se pudo descontar el stock (stock insuficiente o celular no existe)");
        }
    }
    
    public void menu() {
        int op;
        do {
            System.out.println("""
            ==========================================
                    GESTION DE CELULARES
            ==========================================
            1. Registrar Celular
            2. Listar Celulares
            3. Buscar por ID
            4. Actualizar Celular
            5. Eliminar Celular
            6. Descontar Stock
            7. Regresar
            ==========================================
            """);
            System.out.print("Seleccione una opcion: ");
            op = sc.nextInt();
            sc.nextLine(); 
            
            while (op < 1 || op > 7) {
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
                case 6:
                    descontarStock();
                    break;
            }
        } while (op != 7);
    }
}

