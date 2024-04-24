package view;

import business.UserManager;
import core.ComboItem;
import core.Helper;
import entity.Role;
import entity.User;

import javax.swing.*;

public class UserView extends Layout{
    private JPanel container;
    private JLabel lbl_user_username;
    private JTextField fld_user_username;
    private JLabel lbl_user_userpassword;
    private JLabel lbl_user_welcome;
    private JTextField fld_user_userpassword;
    private JLabel lbl_user_userrole;
    private JComboBox cmb_user_userrole;
    private JButton btn_user_usersave;
    private User user;
    private UserManager userManager;

    public UserView(User user) {
        this.userManager = new UserManager();
        this.add(container);
        this.user = user;
        guiInitialise(300,300);

        this.cmb_user_userrole.setModel(new DefaultComboBoxModel<>(Role.values()));
        for(Role role : Role.values()) {
            this.cmb_user_userrole.addItem(role);
        }

        int userId = this.user.getUser_id();

        if(userId != 0) {
            this.fld_user_username.setText(user.getUser_name());
            this.fld_user_userpassword.setText(user.getUser_password());
            this.cmb_user_userrole.setSelectedItem(user.getUser_role());
        }

        this.btn_user_usersave.addActionListener(e -> {
            if(Helper.isFieldListEmpty(new JTextField[]{fld_user_username, fld_user_userpassword})) {
                Helper.showMessage("fill");
            } else {
                boolean result;
                this.user.setUser_name(fld_user_username.getText());
                this.user.setUser_password(fld_user_userpassword.getText());
                this.user.setUser_role(((Role) this.cmb_user_userrole.getSelectedItem()));
                if(userId != 0) {
                    result = this.userManager.update(this.user);
                } else {
                    result = this.userManager.save(this.user);
                }
                if(result) {
                    Helper.showMessage("done");
                    dispose();
                } else {
                    Helper.showMessage("error");
                }
            }

        });

    }
}
