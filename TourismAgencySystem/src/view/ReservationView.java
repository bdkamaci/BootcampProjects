package view;

import business.HotelManager;
import business.PensionManager;
import business.ReservationManager;
import business.RoomManager;
import core.Helper;
import entity.Reservation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.util.ArrayList;

public class ReservationView extends Layout{
    private JPanel container;
    private JLabel fld_reservation_welcome_hotel;
    private JLabel lbl_reservation_welcome_room;
    private JLabel lbl_reservation_welcome_pension;
    private JCheckBox chck_autopark;
    private JCheckBox chck_wifi;
    private JCheckBox chck_pool;
    private JCheckBox chck_roomservice;
    private JCheckBox chck_gym;
    private JCheckBox chck_concierge;
    private JCheckBox chck_spa;
    private JCheckBox chck_tv;
    private JCheckBox chck_minibar;
    private JCheckBox chck_gameconsole;
    private JCheckBox chck_vault;
    private JCheckBox chck_projector;
    private JPanel w_room;
    private JPanel w_hotel;
    private JPanel w_pension;
    private JTable tbl_reservation_hotel;
    private JTable tbl_reservation_room;
    private JTable tbl_reservation_pension;
    private JLabel lbl_reservation_welcome_guest;
    private JLabel lbl_reservaation_guestName;
    private JLabel lbl_reservation_guestId;
    private JLabel lbl_reservation_guestPhone;
    private JLabel lbl_reservation_guestMail;
    private JLabel lbl_reservation_checkIn;
    private JLabel lbl_reservation_checkOut;
    private JLabel lbl_reservation_adultCount;
    private JLabel lbl_reservation_childrenCount;
    private JButton btn_reserve;
    private JTextField fld_reservation_guestName;
    private JTextField fld_reservation_guestID;
    private JTextField fld_reservation_guestPhone;
    private JTextField fld_reservation_guestMail;
    private JTextField fld_reservation_checkIn;
    private JTextField fld_reservation_checkOut;
    private JTextField fld_reservation_adultCount;
    private JTextField fld_reservation_childrenCount;
    private JPanel w_guest;
    private JLabel lbl_reservation_roomId;
    private JTextField fld_reservation_roomId;
    private Reservation reservation;
    private ReservationManager reservationManager;
    private HotelManager hotelManager;
    private PensionManager pensionManager;
    private RoomManager roomManager;
    private DefaultTableModel tbmdl_hotel = new DefaultTableModel();
    private DefaultTableModel tbmdl_pension = new DefaultTableModel();
    private DefaultTableModel tbmdl_room = new DefaultTableModel();

    public ReservationView(Reservation reservation) {
        this.add(container);
        guiInitialise(800,1000);

        this.reservation = reservation;
        this.reservationManager = new ReservationManager();
        this.hotelManager = new HotelManager();
        this.pensionManager = new PensionManager();
        this.roomManager = new RoomManager();

        this.fld_reservation_checkIn.setText("yyyy-mm-dd");
        this.fld_reservation_checkOut.setText("yyyy-mm-dd");

        // Load Hotel Table
        loadHotelTable();

        // Load Pension Table
        loadPensionTable();

        // Load Room Table
        loadRoomTable();

        // Save Reservation
        saveAfterCheckStock();

    }

    public void loadHotelTable(){
        Object[] col_hotel_list = new Object[]{"ID", "Name"};
        ArrayList<Object[]> hotelList = this.hotelManager.getForReservation(col_hotel_list.length, this.hotelManager.findAll());
        generateTable(this.tbmdl_hotel, this.tbl_reservation_hotel, col_hotel_list, hotelList);
    }

    public void loadPensionTable(){
        Object[] col_pension_list = new Object[]{"ID", "Pension Type"};
        ArrayList<Object[]> pensionList = this.pensionManager.getForReservation(col_pension_list.length, this.pensionManager.findAll());
        generateTable(this.tbmdl_pension, this.tbl_reservation_pension, col_pension_list, pensionList);
    }

    public void loadRoomTable(){
        Object[] col_room_list = new Object[]{"ID", "Hotel ID", "Pension ID", "Season ID", "Room Type"};
        ArrayList<Object[]> roomList = this.roomManager.getForReservation(col_room_list.length, this.roomManager.findAll());
        generateTable(this.tbmdl_room, this.tbl_reservation_room, col_room_list, roomList);
    }

    public void saveAfterCheckStock() {
        btn_reserve.addActionListener(e -> {
            if(this.roomManager.getById(Integer.parseInt(this.fld_reservation_roomId.getText())).getStock() < 1) {
                Helper.showMessage("insufficient stock");
            } else {
                boolean result;
                this.reservation.setRoom_id(Integer.parseInt(this.fld_reservation_roomId.getText()));
                this.reservation.setCheckIn(LocalDate.parse(this.fld_reservation_checkIn.getText()));
                this.reservation.setCheckOut(LocalDate.parse(this.fld_reservation_checkOut.getText()));
                this.reservation.setGuestIdentification(this.fld_reservation_guestID.getText());
                this.reservation.setGuestMail(this.fld_reservation_guestMail.getText());
                this.reservation.setGuestPhone(this.fld_reservation_guestPhone.getText());
                this.reservation.setGuestName(this.fld_reservation_guestName.getText());
                this.reservation.setGuestCountAdult(Integer.parseInt(this.fld_reservation_adultCount.getText()));
                this.reservation.setGuestCountChildren(Integer.parseInt(this.fld_reservation_childrenCount.getText()));
                this.reservation.setGuestCount(Integer.parseInt(this.fld_reservation_adultCount.getText()) + Integer.parseInt(this.fld_reservation_childrenCount.getText()));
                result = this.reservationManager.save(this.reservation);
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
