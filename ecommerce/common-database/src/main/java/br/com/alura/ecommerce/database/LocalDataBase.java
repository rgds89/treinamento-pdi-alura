package br.com.alura.ecommerce.database;

import java.sql.*;

public class LocalDataBase {
    private final Connection connection;

    public LocalDataBase(String dataBaseName) {
        try {
            String url = "jdbc:sqlite:target/" + dataBaseName + ".db";
            connection = DriverManager.getConnection(url);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void createIfNotExists(String query) {
        try {
            connection.createStatement().execute(query);
        } catch (SQLException ex) {
            // be careful, the sql could be wrong, be reallllly careful
            ex.printStackTrace();
        }
    }

    public void update(String statement, String... params) {
        try {
            prepare(statement, params).execute();
        } catch (SQLException ex) {
            // be careful, the sql could be wrong, be reallllly careful
            ex.printStackTrace();
        }
    }

    public ResultSet query(String query, String... params) {
        try {
            return prepare(query).executeQuery();
        } catch (SQLException ex) {
            // be careful, the sql could be wrong, be reallllly careful
            ex.printStackTrace();
            return null;
        }
    }

    private PreparedStatement prepare(String statement, String... params) throws SQLException {
        var prepareStatement = connection.prepareStatement(statement);
        for (int i = 0; i < params.length; i++) {
            prepareStatement.setString(i + 1, params[i]);
        }
        return prepareStatement;
    }

    public void close() throws SQLException {
        connection.close();
    }
}
