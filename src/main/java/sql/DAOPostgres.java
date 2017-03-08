package sql;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOPostgres extends DAOBase
{
    private  Connection  connection = null;

    public DAOPostgres() {
        super ("org.postgresql.Driver");
    }
    @Override
    public void setURL (String host, String database, int port) {
        if (database.length() > 0)
            this.url = "jdbc:postgresql://" + host + ":" + port + "/" + database;
        else
            this.url = "jdbc:postgresql://" + host + ":" + port;
    }
    @Override
    public Connection getConnection () {
        return connection;
    }
    @Override
    public void Connect (String login, String password) {
        super.Connect(login, password);
        try {
            connection = DriverManager.getConnection(url, properties);
        } catch (SQLException e) {
            connection = null;
        }
    }

    public ResultSet selectTable(String table){
        return execSQL("select * from " + table);
    }
}