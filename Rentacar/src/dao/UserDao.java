package dao;

import core.Db;
import core.Helper;
import entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDao {
    private final Connection connection;

    public UserDao() {
        this.connection = Db.getInstance();
    }

    public ArrayList<User> findAll() {
        ArrayList<User> userList = new ArrayList<>();
        String sql_query = "SELECT * FROM public.user";
        try {
            ResultSet rs = this.connection.createStatement().executeQuery(sql_query);
            while (rs.next()) {
                userList.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public User findByLogin(String username, String password) {
        User obj = null;
        String query = "SELECT * FROM public.user WHERE user_name = ? AND user_password = ?";
        try {
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setString(1, username);
            pr.setString(2, password);
            ResultSet rs = pr.executeQuery();
            if(rs.next()) {
                obj = this.match(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public User match(ResultSet resultSet) throws SQLException {
        User obj = new User();
        obj.setId(resultSet.getInt("user_id"));
        obj.setUsername(resultSet.getString("user_name"));
        obj.setPassword(resultSet.getString("user_password"));
        obj.setRole(resultSet.getString("user_role"));
        return obj;
    }

}
