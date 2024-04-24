package view;

import javax.swing.*;

public class ReservationView extends Layout{
    private JPanel container;
    private JPanel w_hotel;
    private JPanel w_room;
    private JPanel w_customer;
    private JLabel lbl_hotel_info;
    private JLabel lbl_hotel_name;
    private JTextField fld_hotel_name;
    private JLabel lbl_hotel_city;
    private JTextField fld_hotel_city;
    private JLabel lbl_hotel_star;
    private JTextField fld_hotel_star;
    private JCheckBox chck_hotel_autopark;
    private JCheckBox chck_hotel_wifi;
    private JCheckBox chck_hotel_pool;
    private JCheckBox chck_hotel_gym;
    private JCheckBox chck_hotel_concierge;
    private JCheckBox chck_hotel_spa;
    private JCheckBox chck_hotel_roomservice;
    private JLabel lbl_room_info;
    private JLabel lbl_room_type;
    private JTextField fld_room_type;
    private JLabel lbl_room_pension_type;
    private JTextField fld_room_pension_type;
    private JLabel lbl_room_date_checkin;
    private JTextField fld_room_date_checkin;
    private JLabel lbl_room_date_checkout;
    private JTextField fld_room_date_checkout;
    private JLabel lbl_room_totalprice;
    private JTextField fld_room_totalprice;
    private JLabel lbl_room_capacity;
    private JTextField fld_room_capacity;
    private JLabel lbl_room_area;
    private JTextField fld_room_area;
    private JCheckBox chck_room_tv;
    private JCheckBox chck_room_minibar;
    private JCheckBox chck_room_gameconsole;
    private JCheckBox chck_room_vault;
    private JCheckBox chck_room_projector;
    private JLabel lbl_customer_info;
    private JLabel lbl_customer_occupancy;
    private JTextField fld_customer_occupancy;
    private JLabel lbl_customer_name;
    private JTextField fld_customer_name;
    private JLabel lbl_customer_number_identification;
    private JTextField fld_customer_number_identification;
    private JLabel lbl_customer_mail;
    private JTextField fld_customer_mail;
    private JLabel lbl_customer_number_phone;
    private JTextField fld_customer_number_phone;
    private JButton btn_reservation_create;

    public ReservationView() {
        this.add(container);
        guiInitialise(800,800);
    }
}
