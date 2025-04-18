package groups.venixteam.laas.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQL {

    private final String host, database, username, password;
    private final int port;
    private Connection connection;

    public MySQL(String host, int port, String database, String username, String password) {
        this.host = host;
        this.port = port;
        this.database = database;
        this.username = username;
        this.password = password;
    }

    public void connect() throws SQLException {
        if (connection != null && !connection.isClosed()) return;

        String url = "jdbc:mysql://" + host + ":" + port + "/?useSSL=false";
        connection = DriverManager.getConnection(url, username, password);

        try (Statement stmt = connection.createStatement()) {
            String createDbQuery = "CREATE DATABASE IF NOT EXISTS `" + database + "`";
            stmt.executeUpdate(createDbQuery);
        }

        url = "jdbc:mysql://" + host + ":" + port + "/" + database + "?useSSL=false";
        connection = DriverManager.getConnection(url, username, password);
    }

    public void disconnect() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
