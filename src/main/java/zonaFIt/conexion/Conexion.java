package zonaFIt.conexion;
import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    public static Connection getConnection(){
        Connection conexion=null;
        var baseDatos="zonafit";
        var url="jdbc:mysql://localhost:3306/"+baseDatos;
        var usuario="root";
        var password="120512";
        try {
            //Class.forName("com.mysql.cj.jdbc.Driver");
            conexion=DriverManager.getConnection(url,usuario,password);

        }catch (Exception e){
            System.out.println("error al conectarnos a la base de datos: "+e.getMessage());
        }
        return conexion;
    }


}
