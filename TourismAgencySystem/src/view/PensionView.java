package view;

import business.HotelManager;
import business.PensionManager;
import core.Helper;
import entity.Pension;
import entity.Role;

import javax.swing.*;

public class PensionView extends Layout{
    private JPanel container;
    private JLabel lbl_pension_welcome;
    private JButton btn_pension_save;
    private JLabel lbl_pension_hotel_id;
    private JLabel lbl_pension_type;
    private JComboBox cmb_pension_type;
    private JComboBox cmb_pension_hotel_id;
    private JTextField fld_pension_hotel_id;
    private Pension pension;
    private PensionManager pensionManager;

    public PensionView(Pension pension) {
        this.pensionManager = new PensionManager();
        this.pension = pension;
        this.add(container);
        guiInitialise(400,400);
        loadPensionFilter();

        int pension_id = this.pension.getId();

        if(pension_id != 0) {
            this.fld_pension_hotel_id.setText(String.valueOf(pension.getHotel_id()));
            this.cmb_pension_type.setSelectedItem(pension.getPension_type());
        }

        btn_pension_save.addActionListener(e -> {
            boolean result;
            this.pension.setHotel_id(Integer.parseInt(this.fld_pension_hotel_id.getText()));
            this.pension.setPension_type((String)this.cmb_pension_type.getSelectedItem());
            if(pension_id != 0) {
                result = this.pensionManager.update(this.pension);
            } else {
                result = this.pensionManager.save(this.pension);
            }
            if(result) {
                Helper.showMessage("done");
                dispose();
            } else {
                Helper.showMessage("error");
            }
        });
    }

    public void loadPensionFilter() {
        String[] filter = {"ULTRA_ALL_INCLUSIVE",
                "ALL_INCLUSIVE", "ROOM_BREAKFAST",
                "FULL_BOARD", "HALF_BOARD",
                "ROOM_ONLY", "FULL_CREDIT_NON_ALCOHOL"};
        this.cmb_pension_type.setModel(new DefaultComboBoxModel<>(filter));
        this.cmb_pension_type.setSelectedItem(null);
    }
}
