package view;

import business.ReservationManager;
import core.Helper;
import entity.Reservation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class ReservationEditView extends Layout{
    private JPanel container;
    private JLabel lbl_reservation_edit_welcome;
    private JLabel lbl_reservation_edit_guest_name;
    private JTextField fld_reservation_edit_guest_name;
    private JLabel lbl_reservation_edit_guest_id;
    private JTextField fld_reservation_edit_guest_id;
    private JLabel lbl_reservation_edit_guest_phone;
    private JLabel lbl_reservation_edit_guest_mail;
    private JTextField fld_reservation_edir_guest_phone;
    private JTextField fld_reservation_edit_guess_mail;
    private JLabel lbl_reservation_edit_checkin;
    private JLabel lbl_reservation_edit_checkout;
    private JTextField fld_room_edit_checkin;
    private JTextField fld_room_edit_checkout;
    private JLabel lbl_reservation_edit_num_adult;
    private JTextField fld_reservation_edit_num_adult;
    private JLabel lbl_reservation_edit_num_children;
    private JTextField fld_reservation_edit_num_children;
    private JButton btn_save;
    private Reservation reservation;
    private ReservationManager reservationManager;

    public ReservationEditView(Reservation reservation) {
        this.add(container);
        this.reservation = reservation;
        this.reservationManager = new ReservationManager();
        guiInitialise(400, 400);

        int reservation_id = reservation.getId();
        if(reservation_id != 0) {
            loadCustomerDetails();
        }

        btn_save.addActionListener(e -> {
            if(Helper.isFieldListEmpty(new JTextField[]{fld_reservation_edit_guest_name, fld_reservation_edit_guest_id, fld_reservation_edir_guest_phone,
                    fld_reservation_edit_guess_mail, fld_reservation_edit_num_adult, fld_reservation_edit_num_children, fld_room_edit_checkin, fld_room_edit_checkout})) {
                Helper.showMessage("fill");
            } else {
                updateCustomerDetails();
                boolean result;
                result = this.reservationManager.update(this.reservation);
                if(result) {
                    Helper.showMessage("done");
                    dispose();
                } else {
                    Helper.showMessage("error");
                }
            }
        });
    }

    public void loadCustomerDetails() {
        this.fld_reservation_edit_guest_name.setText(reservation.getGuestName());
        this.fld_reservation_edit_guess_mail.setText(reservation.getGuestMail());
        this.fld_reservation_edir_guest_phone.setText(reservation.getGuestPhone());
        this.fld_reservation_edit_guest_id.setText(reservation.getGuestIdentification());
        this.fld_room_edit_checkin.setText(String.valueOf(reservation.getCheckIn()));
        this.fld_room_edit_checkout.setText(String.valueOf(reservation.getCheckOut()));
        this.fld_reservation_edit_num_adult.setText(String.valueOf(reservation.getGuestCountAdult()));
        this.fld_reservation_edit_num_children.setText(String.valueOf(reservation.getGuestCountChildren()));
    }

    public void updateCustomerDetails() {
        this.reservation.setGuestName(this.fld_reservation_edit_guest_name.getText());
        this.reservation.setGuestIdentification(this.fld_reservation_edit_guest_id.getText());
        this.reservation.setGuestMail(this.fld_reservation_edit_guess_mail.getText());
        this.reservation.setGuestPhone(this.fld_reservation_edir_guest_phone.getText());
        this.reservation.setCheckIn(LocalDate.parse(this.fld_room_edit_checkin.getText()));
        this.reservation.setCheckOut(LocalDate.parse(this.fld_room_edit_checkout.getText()));
        this.reservation.setGuestCountAdult(Integer.parseInt(this.fld_reservation_edit_num_adult.getText()));
        this.reservation.setGuestCountChildren(Integer.parseInt(this.fld_reservation_edit_num_children.getText()));
        this.reservation.setGuestCount(Integer.parseInt(this.fld_reservation_edit_num_adult.getText()) + Integer.parseInt(this.fld_reservation_edit_num_children.getText()));
    }


}
