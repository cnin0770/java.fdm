package com.fdm.w8.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GameDao {
    private static final String sql_create_table = "CREATE TABLE GAMES (id INTEGER(5) PRIMARY KEY, name VARCHAR(50))";
    private static String sql_insert = "INSERT INTO GAMES VALUES (%d, %s)";
    private static String sql_insert_safe = "INSERT INTO GAMES VALUES (?, ?)";
    private static String sql_select_all = "SELECT * FROM GAMES";
    private static String sql_select_name = "SELECT * FROM GAMES WHERE name = '%s'";
    private static String sql_drop_table = "DROP TABLE GAMES";

    private ConnectionPool connectionPool;

    GameDao(ConnectionPool connectionPool) {
        super();
        this.connectionPool = connectionPool;
    }

    void createTable() {
        Connection connection = connectionPool.getConnection();
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql_create_table);
            statement.execute("SHUTDOWN");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connectionPool.release(connection);
    }

    void dropTable() {
        Connection connection = connectionPool.getConnection();
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql_drop_table);
            statement.execute("SHUTDOWN ");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void insert(Game game) {
        Connection connection = connectionPool.getConnection();
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(String.format(sql_insert, game.getId(), game.getName()));
            statement.execute("SHUTDOWN");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connectionPool.release(connection);
    }

    void safeInsert(Game game) {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(sql_insert_safe)) {
            ps.setInt(1, game.getId());
            ps.setString(2, game.getName());
            ps.executeUpdate();
//            ps.execute("SHUTDOWN ");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connectionPool.release(connection);
    }

    void batchInsert(List<Game> games) {
        Connection connection = connectionPool.getConnection();
        try {
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(sql_insert_safe);
            for (Game g: games) {
                ps.setInt(1, g.getId());
                ps.setString(2, g.getName());
                ps.addBatch();
            }
            ps.executeBatch();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    List<Game> selectAll() {
        List<Game> found = new ArrayList<>();
        Connection connection = connectionPool.getConnection();
        try (Statement statement = connection.createStatement())  {
            ResultSet rs = statement.executeQuery(sql_select_all);
            while (rs.next()) {
                // safer than getInt() in sense of 0/false confusion
                int foundId = rs.getBigDecimal("id").intValue();
                String foundName = rs.getString("name");
                found.add(new Game(foundId, foundName));
            }
            statement.execute("SHUTDOWN");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connectionPool.release(connection);
        return found;
    }

    List<Game> selectName(String name) {
        List<Game> found = new ArrayList<>();
        Connection connection = connectionPool.getConnection();
        try (Statement statement = connection.createStatement())  {
            ResultSet rs = statement.executeQuery(String.format(sql_select_name, name));
            while (rs.next()) {
                // safer than getInt() in sense of 0/false confusion
                int foundId = rs.getBigDecimal("id").intValue();
                String foundName = rs.getString("name");
                found.add(new Game(foundId, foundName));
            }
            statement.execute("SHUTDOWN");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connectionPool.release(connection);
        return found;
    }

    List<Game> safeSelect(String name) {
        List<Game> found = new ArrayList<>();
        String query = "SELECT * FROM GAMES WHERE name = ?";
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(query))  {
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int foundId = rs.getBigDecimal("id").intValue();
                String foundName = rs.getString("name");
                found.add(new Game(foundId, foundName));
            }
//            ps.execute("SHUTDOWN");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connectionPool.release(connection);
        return found;
    }
}
