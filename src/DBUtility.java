import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBUtility {
    private static final String URL = "jdbc:mysql://localhost:3307/CurrencyExchange";;
    private static final String USER = "root";
    private static final String PASSWORD = "Gokdeniz0?";

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static ResultSet getExchangeRates() throws Exception {
        Connection conn = getConnection();
        Statement stmt = conn.createStatement();
        return stmt.executeQuery("SELECT * FROM ExchangeRates");
    }
}
