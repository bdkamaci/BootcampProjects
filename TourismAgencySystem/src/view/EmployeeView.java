package view;

import business.UserManager;
import entity.User;

import javax.swing.*;

public class EmployeeView extends Layout{
    private JPanel container;
    private JPanel w_top;
    private JLabel lbl_employee_welcome;
    private JTabbedPane tabbedPane1;
    private JButton btn_exit;
    private JTable tbl_employee_reservation;
    private JPanel pnl_room_top;
    private JLabel lbl_employee_room_hotel_name;
    private JLabel lbl_employee_room_city;
    private JLabel lbl_employee_room_date_checkin;
    private JLabel lbl_employee_room_date_checkout;
    private JLabel lbl_employee_room_number_adult;
    private JLabel lbl_employee_room_number_children;
    private JTextField fld_employee_room_hotel_name;
    private JTextField fld_employee_room_city;
    private JTextField fld_employee_room_date_checkin;
    private JTextField fld_employee_room_date_checkout;
    private JTextField fld_employee_room_number_adult;
    private JTextField fld_employee_room_number_children;
    private JButton btn_employee_room_filter;
    private JTable tbl_employee_room_result;
    private JButton btn_employee_hotel_hoteladd;
    private JTable tbl_employee_hotel_all;
    private JTable tbl_employee_hotel_peension;
    private JTable tbl_employee_hotel_season;
    private User user;
    private UserManager userManager;


    public EmployeeView(User user) {
        this.userManager = new UserManager();
        this.user = user;
        this.add(container);
        guiInitialise(1100,700);

        this.user = user;
        if(this.user == null) {
            dispose();
        }

        this.lbl_employee_welcome.setText("Welcome: " + this.user.getUser_name());
    }
}
