package zonaFIt.main;

import zonaFIt.controlador.Controlador;
import zonaFIt.datos.ClienteDAO;
import zonaFIt.datos.IClienteDAO;
import zonaFIt.vista.Ivista;
import zonaFIt.vista.VistaConsola;

public class Main
{
    public static void main(String[] args)
    {
        Ivista vista=new VistaConsola();
        IClienteDAO clienteDao =new ClienteDAO();
        Controlador controlador=new Controlador(vista,clienteDao);
        controlador.iniciar();
    }
}
