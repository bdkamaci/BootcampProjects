package view;

import business.UserManager;
import core.Helper;
import entity.User;

import javax.swing.*;

public class LoginView extends Layout {
    private JPanel container;
    private JPanel w_top;
    private JPanel w_bottom;
    private JLabel lbl_welcome;
    private JLabel fld_welcome;
    private JLabel lbl_welcome2;
    private JLabel fld_welcome2;
    private JLabel lbl_username;
    private JTextField fld_username;
    private JLabel lbl_password;
    private JTextField fld_password;
    private JButton btn_login;
    private UserManager userManager;

    public LoginView() {
        this.userManager = new UserManager();
        this.add(container);
        this.guiInitialise(400,400);

        btn_login.addActionListener(e -> {
            JTextField[] checkFieldList = {this.fld_username, this.fld_password};
            if(Helper.isFieldListEmpty(checkFieldList)) {
                Helper.showMessage("fill");
            } else {
                User loginUser = this.userManager.findByLogin(this.fld_username.getText(), this.fld_password.getText());
                if(loginUser == null) {
                    Helper.showMessage("notFound");
                } else {
                    if(String.valueOf(loginUser.getUser_role()).equals("ADMIN")) {
                        AdminView adminView = new AdminView(loginUser);
                        dispose();
                    } else {
                        EmployeeView employeeView = new EmployeeView(loginUser);
                        dispose();
                    }

                }
            }
        });
    }
}
