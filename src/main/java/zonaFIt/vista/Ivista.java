package zonaFIt.vista;

import zonaFIt.dominio.Cliente;

import java.util.List;
import java.util.Optional;

public interface Ivista
{
    public void mostrarMenu();

    public void mostrarClientes(List<Cliente> clientes);

    public void mostrarCliente(Cliente cliente);

    public Optional<Cliente> leerDatosDelCliente();

    public int leerId();

    public void mostrarMensaje(String mensaje);

    public void mostrarError(String mensaje);

    public int leerOpcion();


}
