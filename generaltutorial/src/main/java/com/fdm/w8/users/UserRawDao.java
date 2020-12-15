package com.fdm.w8.users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class UserRawDao implements iUserDao {
    private List<User> users = new ArrayList<>();
    private ConnectionPool pool;

    private static final String sql_create_table = "CREATE TABLE AppUser (id VARCHAR(50) PRIMARY KEY, username VARCHAR(50) NOT NULL UNIQUE)";
    private static final String sql_drop_table = "DROP TABLE AppUser CASCADE CONSTRAINTS";
    private static final String sql_select_all = "SELECT * FROM AppUser";
    private static final String sql_insert = "INSERT INTO AppUser VALUES (?, ?)";
    private static final String sql_select = "SELECT * FROM AppUser WHERE username = ?";
    private static final String sql_delete = "DELETE FROM AppUser WHERE username = ?";
    private static final String sql_update = "UPDATE AppUser SET username = ? WHERE id = ?";

    UserRawDao() {
        this.pool = new ConnectionPool();
    }

    void createTable() {
        Connection connection = pool.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(sql_create_table)) {
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pool.release(connection);
        }
    }

    void dropTable() {
        Connection connection = pool.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(sql_drop_table)) {
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pool.release(connection);
        }
    }

    @Override
    public void addUser(User user) {
        Connection connection = pool.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(sql_insert)) {
            ps.setString(1, user.getId());
            ps.setString(2, user.getUsername());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pool.release(connection);
        }
    }

    @Override
    public void addUser(List<User> users) {
        Connection connection = pool.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(sql_insert)) {
            connection.setAutoCommit(false);
            for (User u: users) {
                ps.setString(1, u.getId());
                ps.setString(2, u.getUsername());
                ps.addBatch();
            }
            ps.executeBatch();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pool.release(connection);
        }
    }

    @Override
    public User getUser(String username) {
        Connection connection = pool.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(sql_select)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new User(rs.getString("id"), rs.getString("username"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pool.release(connection);
        }
        return null;
    }

    @Override
    public void delUser(String username) {
        Connection connection = pool.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(sql_delete)) {
            ps.setString(1, username);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pool.release(connection);
        }
    }

    @Override
    public void modUser(User user) {
        Connection connection = pool.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(sql_update)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pool.release(connection);
        }
    }

    @Override
    public List<User> getAllUser() {
        List<User> users = new ArrayList<>();
        Connection connection = pool.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(sql_select_all)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                users.add(new User(rs.getString("id"), rs.getString("username")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pool.release(connection);
        }
        return users;
    }
}
