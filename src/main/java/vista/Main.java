package vista;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
       int op = 0;
        do {
            System.out.println("""
             ==========================================
                     Bienvenido a TecnoStore             
             ==========================================
                               
              1.   Gestionar Marcas
              2.   Gestionar Celulares
              3.   Gestionar Clientes
              4.   Gestionar Ventas
              5.   Salir
             ==========================================
             """);
            op = new Scanner(System.in).nextInt();
            while (op < 1 || op > 5) {
                System.out.println("Error, opcion no valida");
                op = new Scanner(System.in).nextInt();
            }
            switch (op) {
                case 1:
                    Menumarca mm = new Menumarca();
                    mm.menu();
                    break;
                case 2:
                    Menucelular mc = new Menucelular();
                    mc.menu();
                    break;
                case 3:
                    Menucliente mcl = new Menucliente();
                    mcl.menu();
                    break;
                case 4:
                    Menuventa mv = new Menuventa();
                    mv.menu();
                    break;
                case 5:
                    System.out.println("Gracias por usar TecnoStore!");
                    break;
            }
        } while (op != 5);
    }
}
