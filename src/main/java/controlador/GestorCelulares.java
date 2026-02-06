
package controlador;


public class GestorCelulares {
    
  int op;
do{
    System.out.println("""
                       ==================================
                       Bienvenido al Gestor de Celulares
                       
                       1-.Registrar Celular
                       2-.Listar
                       3-.Buscar por ID
                       3-.Actualizar
                       4-.Eliminar
                       5-.Salir
                       """);
    op = sc.nextInt();
    //validacion de opcion
    while(op < 1|| op <5){
        System.out.println("Error ingrese una opcion valida");
        op = sc.nextInt();
    }
    if (op == 1 || op || 2){
        Celular.stream()
        .map(c ->c.get)        
    }
}  
    
}
