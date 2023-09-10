package data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLHelper {
    @SneakyThrows
    public static Connection getConnection() {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "pass");
    }

    @SneakyThrows
    public static String getDebitPaymentStatus() {
        QueryRunner runner = new QueryRunner();
        String SqlStatus = "SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1";
        try (var connection = getConnection()) {
            String result = runner.query(connection, SqlStatus, new ScalarHandler<>());
            return result;
        }
    }
}
