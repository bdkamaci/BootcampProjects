package view;

import entity.Hotel;

import javax.swing.*;

public class HotelSaveView extends Layout {
    private JPanel container;
    private JLabel fld_hotel_save_welcome;
    private JPanel w_left;
    private JPanel w_right;
    private JLabel lbl_hotel_save_hotel_name;
    private JTextField fld_hotel_save_hotel_name;
    private JLabel lbl_hotel_save_mail;
    private JTextField fld_hotel_save_mail;
    private JLabel lbl_hotel_save_phone;
    private JTextField fld_hotel_save_phone;
    private JLabel lbl_hotel_save_adress;
    private JTextField fld_hotel_save_adress;
    private JLabel lbl_hotel_save_star;
    private JComboBox cmb_hotel_save_star;
    private JCheckBox chck_autopark;
    private JCheckBox chck_wifi;
    private JCheckBox chck_pool;
    private JCheckBox chck_gym;
    private JCheckBox chck_concierge;
    private JCheckBox chck_spa;
    private JCheckBox chck_roomservice;
    private JButton btn_hotel_save_save;
    private Hotel hotel;

    public HotelSaveView(Hotel hotel) {
        this.add(container);
        guiInitialise(400,400);

        this.hotel = hotel;
    }
}
