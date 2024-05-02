package view;

import business.HotelManager;
import core.Helper;
import entity.Hotel;
import entity.Role;
import entity.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

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
    private JButton btn_hotel_save_save;
    private JLabel lbl_hotel_save_autopark;
    private JLabel lbl_hotel_save_wifi;
    private JLabel lbl_hotel_save_pool;
    private JLabel lbl_hotel_save_gym;
    private JLabel lbl_hotel_save_concierge;
    private JLabel lbl_hotel_save_spa;
    private JLabel lbl_hotel_save_room_service;
    private JComboBox<Boolean> cmb_hotel_save_autopark;
    private JComboBox<Boolean> cmb_hotel_save_wifi;
    private JComboBox<Boolean> cmb_hotel_save_pool;
    private JComboBox<Boolean> cmb_hotel_save_gym;
    private JComboBox<Boolean> cmb_hotel_save_concierge;
    private JComboBox<Boolean> cmb_hotel_save_spa;
    private JComboBox<Boolean> cmb_hotel_save_room_service;
    private Hotel hotel;
    private HotelManager hotelManager;

    public HotelSaveView(Hotel hotel) {
        this.hotelManager = new HotelManager();
        this.add(container);
        guiInitialise(400,600);
        loadBooleanFilter();

        this.hotel = hotel;
        int hotelId = this.hotel.getId();

        if(hotelId != 0) {
            this.fld_hotel_save_hotel_name.setText(hotel.getName());
            this.fld_hotel_save_mail.setText(hotel.getMail());
            this.fld_hotel_save_phone.setText(hotel.getPhone());
            this.fld_hotel_save_adress.setText(hotel.getAddress());
            this.cmb_hotel_save_star.setSelectedItem(hotel.getStar());
            this.cmb_hotel_save_autopark.setSelectedItem(hotel.isAutopark());
            this.cmb_hotel_save_wifi.setSelectedItem(hotel.isWifi());
            this.cmb_hotel_save_pool.setSelectedItem(hotel.isPool());
            this.cmb_hotel_save_gym.setSelectedItem(hotel.isGym());
            this.cmb_hotel_save_concierge.setSelectedItem(hotel.isConcierge());
            this.cmb_hotel_save_spa.setSelectedItem(hotel.isSpa());
            this.cmb_hotel_save_room_service.setSelectedItem(hotel.isRoomService());
        }


        btn_hotel_save_save.addActionListener(e -> {

            if(Helper.isFieldListEmpty(new JTextField[]{fld_hotel_save_hotel_name, fld_hotel_save_mail, fld_hotel_save_phone, fld_hotel_save_adress})) {
                Helper.showMessage("fill");
            } else {
                boolean result;
                this.hotel.setName(fld_hotel_save_hotel_name.getText());
                this.hotel.setAddress(fld_hotel_save_adress.getText());
                this.hotel.setMail(fld_hotel_save_mail.getText());
                this.hotel.setPhone(fld_hotel_save_phone.getText());
                this.hotel.setStar((String)cmb_hotel_save_star.getSelectedItem());
                this.hotel.setAutopark((Boolean) cmb_hotel_save_autopark.getSelectedItem());
                this.hotel.setWifi((Boolean) cmb_hotel_save_wifi.getSelectedItem());
                this.hotel.setPool((Boolean) cmb_hotel_save_pool.getSelectedItem());
                this.hotel.setGym((Boolean) cmb_hotel_save_gym.getSelectedItem());
                this.hotel.setConcierge((Boolean) cmb_hotel_save_concierge.getSelectedItem());
                this.hotel.setSpa((Boolean) cmb_hotel_save_spa.getSelectedItem());
                this.hotel.setRoomService((Boolean) cmb_hotel_save_room_service.getSelectedItem());
                if(hotelId != 0) {
                    result = this.hotelManager.update(this.hotel);
                } else {
                    result = this.hotelManager.save(this.hotel);
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

    public void loadBooleanFilter() {
        Boolean[] filter = {true, false};
        String[] star_filter = {"*", "**", "***", "****", "*****"};
        this.cmb_hotel_save_autopark.setModel(new DefaultComboBoxModel<>(filter));
        this.cmb_hotel_save_autopark.setSelectedItem(null);
        this.cmb_hotel_save_wifi.setModel(new DefaultComboBoxModel<>(filter));
        this.cmb_hotel_save_wifi.setSelectedItem(null);
        this.cmb_hotel_save_pool.setModel(new DefaultComboBoxModel<>(filter));
        this.cmb_hotel_save_pool.setSelectedItem(null);
        this.cmb_hotel_save_gym.setModel(new DefaultComboBoxModel<>(filter));
        this.cmb_hotel_save_gym.setSelectedItem(null);
        this.cmb_hotel_save_concierge.setModel(new DefaultComboBoxModel<>(filter));
        this.cmb_hotel_save_concierge.setSelectedItem(null);
        this.cmb_hotel_save_spa.setModel(new DefaultComboBoxModel<>(filter));
        this.cmb_hotel_save_spa.setSelectedItem(null);
        this.cmb_hotel_save_room_service.setModel(new DefaultComboBoxModel<>(filter));
        this.cmb_hotel_save_room_service.setSelectedItem(null);
        this.cmb_hotel_save_star.setModel(new DefaultComboBoxModel<>(star_filter));
        this.cmb_hotel_save_star.setSelectedItem(null);
    }
}
