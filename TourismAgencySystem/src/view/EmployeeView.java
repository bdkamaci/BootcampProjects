package view;

import business.*;
import core.Helper;
import entity.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
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
    private JComboBox cmb_employee_room_hotel_name;
    private JComboBox cmb_employee_room_city;
    private JButton btn_employee_remove_filter;
    private JComboBox cmb_add_choice;
    private DefaultTableModel tbmdl_hotel = new DefaultTableModel();
    private DefaultTableModel tbmdl_pension = new DefaultTableModel();
    private DefaultTableModel tbmdl_season = new DefaultTableModel();
    private DefaultTableModel tbmdl_room = new DefaultTableModel();
    private DefaultTableModel tbmdl_reservation = new DefaultTableModel();
    private JPopupMenu hotel_menu;
    private JPopupMenu pension_menu;
    private JPopupMenu season_menu;
    private JPopupMenu room_menu;
    private JPopupMenu reservation_menu;
    private Object[] col_room_list;
    private User user;
    private UserManager userManager;
    private HotelManager hotelManager;
    private PensionManager pensionManager;
    private SeasonManager seasonManager;
    private RoomManager roomManager;
    private ReservationManager reservationManager;


    public EmployeeView(User user) {
        this.reservationManager = new ReservationManager();
        this.roomManager = new RoomManager();
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

        // Room Tab Components
        loadRoomTable(null);
        loadRoomComponent();
        loadRoomFilter();

        // Reservation Tab Components
        loadReservationTable();
        loadReservationComponent();

        btn_exit.addActionListener(e -> {
            dispose();
        });
    }

    public void loadHotelTable() {
        Object[] col_hotel_list = new Object[]{"ID", "Name", "Address", "Mail", "Phone", "Star", "Autopark", "Wifi", "Pool", "Gym", "Concierge", "Spa", "Room Service"};
        ArrayList<Object[]> hotelList = this.hotelManager.getForTable(col_hotel_list.length, this.hotelManager.findAll());
        generateTable(this.tbmdl_hotel, this.tbl_employee_hotel_all, col_hotel_list, hotelList);
    }

    public void loadHotelComponent() {
        tableRowSelect(this.tbl_employee_hotel_all);

        this.hotel_menu = new JPopupMenu();
        this.hotel_menu.add("New").addActionListener(e -> {
            HotelSaveView hotelSaveView = new HotelSaveView(new Hotel());
            hotelSaveView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadHotelTable();
                }
            });

        });
        this.tbl_employee_hotel_all.setComponentPopupMenu(hotel_menu);

        this.hotel_menu.add("Update").addActionListener(e -> {
            int selectedHotelId = this.getTableSelectedRow(tbl_employee_hotel_all, 0);
            HotelSaveView hotelSaveView = new HotelSaveView(this.hotelManager.getById(selectedHotelId));
            hotelSaveView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadHotelTable();
                }
            });
        });

        this.hotel_menu.add("Delete").addActionListener(e -> {
            if(Helper.confirm("sure")) {
                int selectedHotelId = this.getTableSelectedRow(tbl_employee_hotel_all, 0);
                if(this.hotelManager.delete(selectedHotelId)) {
                    Helper.showMessage("done");
                    loadHotelTable();
                } else {
                    Helper.showMessage("error");
                }
            }
        });
    }

    public void loadPensionTable() {
        Object[] col_pension_list = new Object[]{"ID", "Hotel ID", "Pension Type"};
        ArrayList<Object[]> pensionList = this.pensionManager.getForTable(col_pension_list.length, this.pensionManager.findAll());
        generateTable(this.tbmdl_pension, this.tbl_employee_hotel_pension, col_pension_list, pensionList);
    }

    public void loadPensionComponent() {
        tableRowSelect(this.tbl_employee_hotel_pension);

        this.pension_menu = new JPopupMenu();
        this.pension_menu.add("New").addActionListener(e -> {
            PensionView pensionView = new PensionView(new Pension());
            pensionView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadPensionTable();
                }
            });

        });
        this.tbl_employee_hotel_pension.setComponentPopupMenu(pension_menu);

        this.pension_menu.add("Update").addActionListener(e -> {
            int selectedPensionId = this.getTableSelectedRow(tbl_employee_hotel_pension, 0);
            PensionView pensionView = new PensionView(this.pensionManager.getById(selectedPensionId));
            pensionView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadPensionTable();
                }
            });
        });

        this.pension_menu.add("Delete").addActionListener(e -> {
            if(Helper.confirm("sure")) {
                int selectedPensionId = this.getTableSelectedRow(tbl_employee_hotel_pension, 0);
                if(this.pensionManager.delete(selectedPensionId)) {
                    Helper.showMessage("done");
                    loadPensionTable();
                } else {
                    Helper.showMessage("error");
                }
            }
        });
    }

    public void loadSeasonTable() {
        Object[] col_season_list = new Object[]{"ID", "Hotel ID", "Start Date", "End Date"};
        ArrayList<Object[]> seasonList = this.seasonManager.getForTable(col_season_list.length, this.seasonManager.findAll());
        generateTable(this.tbmdl_season, this.tbl_employee_hotel_season, col_season_list, seasonList);
    }

    public void loadSeasonComponent() {
        tableRowSelect(this.tbl_employee_hotel_season);

        this.season_menu = new JPopupMenu();
        this.season_menu.add("New").addActionListener(e -> {
            SeasonView seasonView = new SeasonView(new Season());
            seasonView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadSeasonTable();
                }
            });

        });
        this.tbl_employee_hotel_season.setComponentPopupMenu(season_menu);

        this.season_menu.add("Update").addActionListener(e -> {
            int selectedSeasonId = this.getTableSelectedRow(tbl_employee_hotel_season, 0);
            SeasonView seasonView = new SeasonView(this.seasonManager.getById(selectedSeasonId));
            seasonView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadSeasonTable();
                }
            });
        });

        this.season_menu.add("Delete").addActionListener(e -> {
            if(Helper.confirm("sure")) {
                int selectedSeasonId = this.getTableSelectedRow(tbl_employee_hotel_season, 0);
                if(this.seasonManager.delete(selectedSeasonId)) {
                    Helper.showMessage("done");
                    loadSeasonTable();
                } else {
                    Helper.showMessage("error");
                }
            }
        });
    }

    public void loadRoomTable(ArrayList<Object[]> roomList) {
        col_room_list = new Object[]{"ID", "Hotel ID", "Pension ID", "Season ID", "Room Type", "Stock", "Adult Price", "Children Price", "Bed Capacity", "Area", "TV", "Minibar", "Game Console", "Vault", "Projector"};
        if(roomList == null) {
            roomList = this.roomManager.getForTable(this.col_room_list.length, this.roomManager.findAll());
        }
        generateTable(this.tbmdl_room, this.tbl_employee_room_result, col_room_list, roomList);
    }

    public void loadRoomComponent() {
        tableRowSelect(this.tbl_employee_room_result);

        this.room_menu = new JPopupMenu();
        this.room_menu.add("New").addActionListener(e -> {
            RoomSaveView roomSaveView = new RoomSaveView(new Room());
            roomSaveView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadRoomTable(null);
                }
            });

        });
        this.tbl_employee_room_result.setComponentPopupMenu(room_menu);

        this.room_menu.add("Update").addActionListener(e -> {
            int selectedRoomId = this.getTableSelectedRow(tbl_employee_room_result, 0);
            RoomSaveView roomSaveView = new RoomSaveView(this.roomManager.getById(selectedRoomId));
            roomSaveView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadRoomTable(null);
                }
            });
        });

        this.room_menu.add("Delete").addActionListener(e -> {
            if(Helper.confirm("sure")) {
                int selectedRoomId = this.getTableSelectedRow(tbl_employee_room_result, 0);
                if(this.roomManager.delete(selectedRoomId)) {
                    Helper.showMessage("done");
                    loadRoomTable(null);
                } else {
                    Helper.showMessage("error");
                }
            }
        });
    }

    private void loadHotelInfos() {

    }

    private void initSearchButtonActionListener() {

    }

    public void loadRoomFilter() {
        ArrayList<Hotel> hotelList = this.hotelManager.findAll();
        for(Hotel hotel : hotelList) {
            this.cmb_employee_room_hotel_name.addItem(hotel.getName());
            this.cmb_employee_room_hotel_name.setSelectedItem(null);
            this.cmb_employee_room_city.addItem(hotel.getAddress());
            this.cmb_employee_room_city.setSelectedItem(null);
        }

        btn_employee_room_filter.addActionListener(e -> {
            if(Helper.isFieldListEmpty(new JTextField[] {fld_employee_room_date_checkin, fld_employee_room_date_checkout, fld_employee_room_number_adult, fld_employee_room_number_children}) || cmb_employee_room_hotel_name.getSelectedItem() == null || cmb_employee_room_city.getSelectedItem() == null) {
                Helper.showMessage("fill");
            } else {
                String selectedHotelName = (String) this.cmb_employee_room_hotel_name.getSelectedItem();
                String selectedHotelCity = (String) this.cmb_employee_room_city.getSelectedItem();
                LocalDate selectedCheckIn = LocalDate.parse(this.fld_employee_room_date_checkin.getText());
                LocalDate selectedCheckOut = LocalDate.parse(this.fld_employee_room_date_checkout.getText());
                int totalBedCapacity = Integer.parseInt(this.fld_employee_room_number_adult.getText()) + Integer.parseInt(this.fld_employee_room_number_children.getText());


                ArrayList<Room> roomList = this.roomManager.getForFilter(selectedHotelName, selectedHotelCity, selectedCheckIn, selectedCheckOut, totalBedCapacity);
                ArrayList<Object[]> rooms = this.roomManager.getForTable(col_room_list.length, roomList);
                loadRoomTable(rooms);
            }
        });

        btn_employee_remove_filter.addActionListener(e -> {
            loadRoomFilter();
            loadRoomTable(null);
        });
    }

    public void loadReservationTable() {
        Object[] col_reservation_list = new Object[]{"ID", "Room ID", "Check In", "Check Out", "Guest Count", "Guest Identification", "Guest Mail", "Guest Phone", "Guest Name", "Adult Count", "Children Count"};
        ArrayList<Object[]> reservationList = this.reservationManager.getForTable(col_reservation_list.length, this.reservationManager.findAll());
        generateTable(this.tbmdl_reservation, this.tbl_employee_reservation, col_reservation_list, reservationList);
    }

    public void loadReservationComponent() {
        tableRowSelect(this.tbl_employee_reservation);

        this.reservation_menu = new JPopupMenu();
        this.reservation_menu.add("New").addActionListener(e -> {
            ReservationView reservationView = new ReservationView(new Reservation());

            reservationView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadReservationTable();
                }
            });

        });
        this.tbl_employee_reservation.setComponentPopupMenu(reservation_menu);

        this.reservation_menu.add("Update").addActionListener(e -> {
            int selectedReservationId = this.getTableSelectedRow(tbl_employee_reservation, 0);
            ReservationEditView reservationEditView = new ReservationEditView(this.reservationManager.getById(selectedReservationId));
            reservationEditView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadReservationTable();
                }
            });
        });

        this.reservation_menu.add("Delete").addActionListener(e -> {
            if(Helper.confirm("sure")) {
                int selectedReservationId = this.getTableSelectedRow(tbl_employee_reservation, 0);
                if(this.reservationManager.delete(selectedReservationId)) {
                    Helper.showMessage("done");
                    loadReservationTable();
                } else {
                    Helper.showMessage("error");
                }
            }
        });
    }



}
