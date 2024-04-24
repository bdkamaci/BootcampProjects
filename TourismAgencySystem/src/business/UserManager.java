package business;

import core.Helper;
import dao.UserDao;
import entity.Role;
import entity.User;

import java.util.ArrayList;

public class UserManager {
    private final UserDao userDao;

    public UserManager() {
        this.userDao = new UserDao();
    }

    public User findByLogin(String username, String password) {
        return this.userDao.findByLogin(username, password);
    }

    public User getById(int id) {
        return this.userDao.getById(id);
    }

    public ArrayList<User> findAll() {
        return this.userDao.findAll();
    }

    public ArrayList<Object[]> getForTable(int size, ArrayList<User> users) {
        ArrayList<Object[]> userList = new ArrayList<>();
        for(User obj : users) {
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = obj.getUser_id();
            rowObject[i++] = obj.getUser_name();
            rowObject[i++] = obj.getUser_password();
            rowObject[i++] = obj.getUser_role();
            userList.add(rowObject);
        }
        return userList;
    }

    public boolean save(User user) {
        if(this.getById(user.getUser_id()) != null) {
            Helper.showMessage("error");
            return false;
        }
        return this.userDao.save(user);
    }

    public boolean update(User user) {
        if(this.getById(user.getUser_id()) == null) {
            Helper.showMessage(user.getUser_id() + " ID - user not found!");
            return false;
        }
        return this.userDao.update(user);
    }

    public boolean delete(int id) {
        if(this.getById(id) == null) {
            Helper.showMessage(id + " ID - user not found!");
            return false;
        }
        return this.userDao.delete(id);
    }

    public ArrayList<User> filterForTable(int userId, String userName, String userPassword, Role role) {
        String select = "SELECT * FROM public.user";
        ArrayList<String> whereList = new ArrayList<>();

        if(userId != 0) {
            whereList.add("user_id = " + userId);
        }

        if(userName != null) {
            whereList.add("user_name = " + userName);
        }

        if(userPassword != null) {
            whereList.add("user_password = " + userPassword);
        }

        if(role != null) {
            whereList.add("user_role ='" + role.toString() + "'");
        }

        String whereStr = String.join(" AND ", whereList);
        String query = select;
        if(whereStr.length() > 0) {
            query += " WHERE " + whereStr;
        }
        return this.userDao.selectByQuery(query);
    }
}
