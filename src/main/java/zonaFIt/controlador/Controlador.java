package zonaFIt.controlador;

import zonaFIt.datos.IClienteDAO;
import zonaFIt.dominio.Cliente;
import zonaFIt.vista.Ivista;

import java.util.Optional;

public class Controlador
{
    private IClienteDAO daoCliente;
    private Ivista vista;

    public Controlador(Ivista vista, IClienteDAO daoCliente)
    {
        this.vista = vista;
        this.daoCliente = daoCliente;
    }

    public void iniciar()
    {
        var finalizado = false;
        do
        {
            vista.mostrarMenu();
            var opc = vista.leerOpcion();
            switch (opc)
            {
                case 1:

                    vista.mostrarMensaje("Opcion 1 seleccionada...");
                    var clientes = daoCliente.listarClientes();
                    vista.mostrarClientes(clientes);

                    break;
                case 2:
                    vista.mostrarMensaje("Opcion 2 seleccionada...");
                    Optional<Cliente> clienteOptional = vista.leerDatosDelCliente();
                    clienteOptional.ifPresent(cliente -> daoCliente.agregarCliente(cliente));
                    break;
                case 3:
                    vista.mostrarMensaje("Opcion 3 seleccionada...");
                    vista.mostrarMensaje("Ingrese el id del cliente a modificar");
                    var id = vista.leerId();
                    var cliente = daoCliente.buscarClientePorId(id);
                    vista.mostrarMensaje("Ingrese los nuevos datos");
                    Optional<Cliente> clienteOptional2 = vista.leerDatosDelCliente();
                    if (clienteOptional2.isPresent())
                    {daoCliente.modificarCliente(clienteOptional2.get());
                    } else
                    {
                        System.out.println("No se pudo modificar el cliente");
                    }



                    break;
                case 4:
                    vista.mostrarMensaje("Opcion 4 seleccionada...");
                    vista.mostrarMensaje("Ingrese el id del cliente a eliminar");
                    var idElimar = vista.leerId();
                    if (daoCliente.eliminarCliente(new Cliente(idElimar)))
                    {
                        System.out.println("Cliente eliminado");
                    }
                    break;
                case 5:

                    finalizado = true;
                    break;
                default:
            }

        } while (!finalizado);
        System.out.println("Programa finalizado...");

    }
}
