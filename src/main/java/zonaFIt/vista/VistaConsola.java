package zonaFIt.vista;

import zonaFIt.dominio.Cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLOutput;
import java.util.List;
import java.util.Optional;

public class VistaConsola implements Ivista
{
    @Override
    public void mostrarMenu()
    {
        System.out.println("***** MENU ****");
        System.out.println("1.Listar clientes");
        System.out.println("2.Agregar cliente");
        System.out.println("3.Modificar cliente");
        System.out.println("4. Eliminar cliente");
    }

    @Override
    public void mostrarClientes(List<Cliente> clientes)
    {
        System.out.println("***** LISTA DE CLIENTES ****");
        clientes.forEach(System.out::println);
    }

    @Override
    public void mostrarCliente(Cliente cliente)
    {
        if (cliente != null)
        {
            System.out.println(cliente.toString());
        } else
        {
            mostrarError("No se pudo mostrar el cliente por que no existe...");
        }
    }

    @Override
    public Optional<Cliente> leerDatosDelCliente()
    {
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        Cliente cliente = null;
        try
        {
            System.out.print("Ingrese el nombre: ");
            var nombre = lector.readLine();
            System.out.print("Ingrese el apellido: ");
            String apellido = lector.readLine();
            System.out.print("Ingrese la membresia: ");
            int membresia = Integer.parseInt(lector.readLine());
            cliente = new Cliente(nombre, apellido, membresia);

        } catch (IOException | NumberFormatException e)
        {
            System.out.println("Ocurrio un error al leer los datos.." + e.getMessage());
        }
        return Optional.ofNullable(cliente);
    }

    @Override
    public int leerId()
    {
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        int id = -1;
        try
        {
            System.out.println("Digite el id: ");
            id = Integer.parseInt(lector.readLine());

        } catch (IOException | NumberFormatException e)
        {
            System.out.println("Ocurrio un error al leer los datos.." + e.getMessage());
        }
        return id;
    }

    @Override
    public void mostrarMensaje(String mensaje)
    {
        System.out.println(mensaje);

    }

    @Override
    public void mostrarError(String mensaje)
    {
        System.out.println("Error: " + mensaje);
    }

}
