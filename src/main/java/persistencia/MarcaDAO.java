package persistencia;

import java.util.ArrayList;
import modelo.Marca;

public interface MarcaDAO {

    void registrar(Marca m);

    void actualizar(Marca m, int id);

    void eliminar(int id);

    ArrayList<Marca> listar();

    Marca buscar(int id);
}
