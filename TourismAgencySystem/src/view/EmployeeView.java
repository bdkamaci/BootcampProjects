package view;

import business.HotelManager;
import business.PensionManager;
import business.SeasonManager;
import business.UserManager;
import entity.Pension;
import entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

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
    private JButton btn_employee_hotel_hotel_add;
    private JTable tbl_employee_hotel_pension;
    private JTable tbl_employee_hotel_season;
    private JTable tbl_employee_hotel_all;
    private JComboBox cmb_add_choice;
    private DefaultTableModel tbmdl_hotel = new DefaultTableModel();
    private DefaultTableModel tbmdl_pension = new DefaultTableModel();
    private DefaultTableModel tbmdl_season = new DefaultTableModel();
    private Object[] col_hotel_list;
    private Object[] col_pension_list;
    private Object[] col_season_list;
    private JPopupMenu hotel_menu;
    private JPopupMenu pension_menu;
    private JPopupMenu season_menu;
    private User user;
    private UserManager userManager;
    private HotelManager hotelManager;
    private PensionManager pensionManager;
    private SeasonManager seasonManager;


    public EmployeeView(User user) {
        this.seasonManager = new SeasonManager();
        this.pensionManager = new PensionManager();
        this.hotelManager = new HotelManager();
        this.userManager = new UserManager();
        this.user = user;
        this.add(container);
        guiInitialise(1100,700);

        this.user = user;
        if(this.user == null) {
            dispose();
        }

        this.lbl_employee_welcome.setText("Welcome: " + this.user.getUser_name());

        // Hotel Tab Components
        loadHotelTable();
        loadHotelComponent();

        loadPensionTable();
        loadPensionComponent();

        loadSeasonTable();
        loadSeasonComponent();

        loadAddFilter();
        btn_employee_hotel_hotel_add.addActionListener(e -> {
            String selectedFilter = (String) this.cmb_add_choice.getSelectedItem();
            switch (selectedFilter) {
                case "HOTEL":
                    HotelSaveView hotelSaveView = new HotelSaveView(null);
                    break;
                case "PENSION":
                    PensionView pensionView = new PensionView(null);
                    break;
                case "SEASON":
                    SeasonView seasonView = new SeasonView(null);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "You need to choose what feature you intend to add.", "Missing Feature", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Room Tab Components

        // Reservation Tab Components


        btn_exit.addActionListener(e -> {
            dispose();
        });
    }

    public void loadHotelTable() {
        this.col_hotel_list = new Object[]{"ID", "Name", "Address", "Mail", "Phone", "Star", "Autopark", "Wifi", "Pool", "Gym", "Concierge", "Spa", "Room Service"};
        ArrayList<Object[]> hotelList = this.hotelManager.getForTable(col_hotel_list.length, this.hotelManager.findAll());
        generateTable(this.tbmdl_hotel, this.tbl_employee_hotel_all, col_hotel_list, hotelList);
    }

    public void loadHotelComponent() {
        tableRowSelect(this.tbl_employee_hotel_all);
    }

    public void loadPensionTable() {
        this.col_pension_list = new Object[]{"ID", "Hotel ID", "Pension Type"};
        ArrayList<Object[]> pensionList = this.pensionManager.getForTable(col_pension_list.length, this.pensionManager.findAll());
        generateTable(this.tbmdl_pension, this.tbl_employee_hotel_pension, col_pension_list, pensionList);
    }

    public void loadPensionComponent() {
        tableRowSelect(this.tbl_employee_hotel_pension);
    }

    public void loadSeasonTable() {
        this.col_season_list = new Object[]{"ID", "Hotel ID", "Start Date", "End Date"};
        ArrayList<Object[]> seasonList = this.seasonManager.getForTable(col_season_list.length, this.seasonManager.findAll());
        generateTable(this.tbmdl_season, this.tbl_employee_hotel_season, col_season_list, seasonList);
    }

    public void loadSeasonComponent() {
        tableRowSelect(this.tbl_employee_hotel_season);
    }

    public void loadAddFilter() {
        String[] filter = {"HOTEL", "PENSION", "SEASON"};
        this.cmb_add_choice.setModel(new DefaultComboBoxModel<>(filter));
        this.cmb_add_choice.setSelectedItem(null);
    }



}
