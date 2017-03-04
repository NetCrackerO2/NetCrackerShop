package sql;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public abstract class DAOBase {
    protected String driver = null;
    protected String url = null;
    protected Properties properties = null;
    public DAOBase(String driver) {
        this.driver = driver;
    }
    /**
     * Процедура регистрации драйвера JDBC
     */
    protected void RegisterDriverManager() {
        try {
            Class.forName(driver).newInstance();
        } catch (InstantiationException e) {e.printStackTrace();
        } catch (IllegalAccessException e) {e.printStackTrace();
        } catch (ClassNotFoundException e) {e.printStackTrace();}
    }
    /**
     * Процедура определения строки подключения URL к серверу БД
     */
    public abstract void setURL (String host, String database, int port);

    public abstract Connection getConnection ();

    public void Connect (String login, String password) {
        RegisterDriverManager();

        properties = new Properties();
        properties.setProperty("password", password);
        properties.setProperty("user", login);
        properties.setProperty("useUnicode", "true");
        properties.setProperty("characterEncoding", "utf8");
    }

    public void Disconnect (Connection connection) {
        try {
            connection.close();
            connection = null;
        } catch (SQLException e) {}
    }

    /**
     * Функция выполнения SQL-запроса
     */
    public ResultSet execSQL (final String sql) {
        ResultSet result = null;
        try {
            if (getConnection() != null) {
                Statement statement = getConnection().createStatement();
                statement.execute(sql);
                result = statement.executeQuery(sql);
            }
        } catch (SQLException e) {
            System.err.println ("SQLException : code = " + String.valueOf(e.getErrorCode()) +
                    " - " + e.getMessage());
            System.err.println ("\tSQL : " + sql);

        }
        return result;
    }
}