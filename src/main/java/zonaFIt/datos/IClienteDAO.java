package zonaFIt.datos;

import zonaFIt.dominio.Cliente;

import java.util.List;

public interface IClienteDAO
{
    List<Cliente> listarClientes();
    Cliente buscarClientePorId(int id);
    boolean agregarCliente(Cliente cliente);
    boolean modificarCliente(Cliente cliente);
    boolean eliminarCliente(Cliente cliente);

}
