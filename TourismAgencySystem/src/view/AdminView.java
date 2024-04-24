package view;

import business.UserManager;
import core.Helper;
import entity.Role;
import entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class AdminView extends Layout {
    private JPanel container;
    private JPanel w_top;
    private JPanel w_left;
    private JPanel w_right;
    private JLabel lbl_welcome;
    private JButton btn_exit;
    private JButton btn_search;
    private JComboBox<Role> cmb_search;
    private JTable tbl_search;
    private JPanel pnl_search;
    private JButton btn_cncl_filter;
    private JPanel pnl_empty_row;
    private User user;
    private UserManager userManager;
    private DefaultTableModel tbmdl_user = new DefaultTableModel();
    private JPopupMenu user_menu;
    private Object[] col_user_list;

    public AdminView(User user) {
        this.userManager = new UserManager();
        this.add(container);
        this.guiInitialise(700,600);

        this.user = user;
        if(this.user == null) {
            dispose();
        }

        this.lbl_welcome.setText("Welcome: " + this.user.getUser_name());

        // User Management Operations
        loadUserTable();
        loadUserFilter();
        loadUserComponent();


    }

    public void loadUserTable() {
        this.col_user_list = new Object[]{"ID", "Name", "Password", "Role"};
        ArrayList<Object[]> userList = this.userManager.getForTable(col_user_list.length, this.userManager.findAll());
        generateTable(this.tbmdl_user, this.tbl_search, col_user_list, userList);
    }

    public void loadUserComponent() {
        tableRowSelect(this.tbl_search);

        this.user_menu = new JPopupMenu();
        this.user_menu.add("New").addActionListener(e -> {
            UserView userView = new UserView(new User());
            userView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadUserTable();
                }
            });

        });
        this.tbl_search.setComponentPopupMenu(user_menu);

        this.user_menu.add("Update").addActionListener(e -> {
            int selectedUserId = this.getTableSelectedRow(tbl_search, 0);
            UserView userView = new UserView(this.userManager.getById(selectedUserId));
            userView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadUserTable();
                }
            });
        });
        this.user_menu.add("Delete").addActionListener(e -> {
            if(Helper.confirm("sure")) {
                int selectedUserId = this.getTableSelectedRow(tbl_search, 0);
                if(this.userManager.delete(selectedUserId)) {
                    Helper.showMessage("done");
                    loadUserTable();
                } else {
                    Helper.showMessage("error");
                }
            }
        });

        this.btn_search.addActionListener(e -> {
            Role selectedRole = (Role) this.cmb_search.getSelectedItem();
            ArrayList<User> userListBySearch = this.userManager.filterForTable(0,null, null, selectedRole);
            ArrayList<Object[]> userRowListBySearch = this.userManager.getForTable(this.col_user_list.length, userListBySearch);
            generateTable(this.tbmdl_user, this.tbl_search, col_user_list, userRowListBySearch);
        });

        this.btn_cncl_filter.addActionListener(e -> {
            this.cmb_search.setSelectedItem(null);
            loadUserTable();
        });

        btn_exit.addActionListener(e -> {
            dispose();
        });
    }

    public void loadUserFilter() {
        this.cmb_search.setModel(new DefaultComboBoxModel<>(Role.values()));
        this.cmb_search.setSelectedItem(null);
        loadUserFilterRole();
    }

    public void loadUserFilterRole() {
        this.cmb_search.removeAllItems();
        for (Role role : Role.values()) {
            this.cmb_search.addItem(role);
        }
        this.cmb_search.setSelectedItem(null);
    }

}
