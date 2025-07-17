package zonaFIt.datos;

import zonaFIt.conexion.Conexion;
import zonaFIt.dominio.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static zonaFIt.conexion.Conexion.getConnection;

public class ClienteDAO implements IClienteDAO
{
    @Override
    public List<Cliente> listarClientes()
    {
        List<Cliente> clientes = new ArrayList<>();
        var sql = "SELECT id, nombre, apellido, membresia FROM cliente ORDER BY id";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery())
        {

            while (rs.next())
            {
                var cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setMembresia(rs.getInt("membresia"));
                clientes.add(cliente);
            }

        } catch (SQLException e)
        {
            System.out.println("Error al listar clientes: " + e.getMessage());
        }

        return clientes;
    }


    @Override
    public Cliente buscarClientePorId(int id)
    {
        var sql = "SELECT id, nombre, apellido, membresia FROM cliente WHERE id=?";
        Cliente cliente = null;
        try (Connection conn = getConnection(); PreparedStatement st = conn.prepareStatement(sql);)
        {
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next())
            {
                cliente = new Cliente(rs.getInt("id"), rs.getString("nombre"), rs.getString("apellido"), rs.getInt("membresia"));

            }

        } catch (SQLException e)
        {
            System.out.println("ocurrio un error al buscar el cliente: " + e.getMessage());
        }
        return cliente;
    }

    @Override
    public boolean agregarCliente(Cliente cliente)
    {
        var sql = "INSERT INTO cliente(nombre,apellido, membresia) VALUES (?,?,?)";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql))
        {
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setInt(3, cliente.getMembresia());
            ps.execute();
            return true;

        } catch (SQLException e)
        {
            System.out.println("Ocurrio un error al agregar el cliente" + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean modificarCliente(Cliente cliente)
    {
        var sql = "UPDATE cliente SET nombre=?,apellido=?,membresia=? WHERE id=?";
        try (Connection conn = getConnection(); PreparedStatement st = conn.prepareStatement(sql);)
        {
            st.setString(1, cliente.getNombre());
            st.setString(2, cliente.getApellido());
            st.setInt(3, cliente.getMembresia());
            st.setInt(4, cliente.getId());
            st.execute();
            return true;
        } catch (SQLException e)
        {
            System.out.println("Ocurrio un error al modificar el cliente: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean eliminarCliente(Cliente cliente)
    {
        var sql = "DELETE FROM cliente WHERE id=?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql))
        {
            ps.setInt(1, cliente.getId());
            ps.execute();
            return true;
        } catch (SQLException e)
        {
            System.out.println("Ocurrio un error al borrar el cliente: " + e.getMessage());
        }
        return false;
    }

}
