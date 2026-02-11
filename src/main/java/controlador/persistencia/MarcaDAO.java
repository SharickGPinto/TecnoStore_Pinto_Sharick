package controlador.persistencia;

import java.util.ArrayList;
import modelo.Marca;

//Se diseña esta interfaz para centralizar todas las acciones que podemos hacer en marca en la base de datos, para matener codigo organizado

public interface MarcaDAO {

    void registrar(Marca m);

    void actualizar(Marca m, int id);

    void eliminar(int id);

    ArrayList<Marca> listar();

    Marca buscar(int id);
}
