package connections;
import java.sql.*;

public class ConnectionDB {
    public static final String URL = "jdbc:sqlserver://localhost;databaseName=conex_java_sql";
    public static final String USERNAME = "javaSql";
    public static final String PASSWORD = "javaSql";

    public static Connection getConexion()
    {
        Connection connection = null;
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println(":)");
        }
        catch(Exception e)
        {
            System.out.println(e);

        }
        return connection;
    }

}
