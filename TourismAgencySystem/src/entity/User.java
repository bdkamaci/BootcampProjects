package entity;

public class User {
    private int user_id;
    private String user_name;
    private String user_password;
    private Role user_role;

    public User() {
    }

    public User(String user_name, String user_password, Role user_role) {
        this.user_name = user_name;
        this.user_password = user_password;
        this.user_role = user_role;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public Role getUser_role() {
        return user_role;
    }

    public void setUser_role(Role user_role) {
        this.user_role = user_role;
    }
}
