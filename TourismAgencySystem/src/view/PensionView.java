package view;

import entity.Pension;

import javax.swing.*;

public class PensionView extends Layout{
    private JPanel container;
    private JLabel lbl_pension_welcome;
    private JComboBox comboBox1;
    private JButton btn_pension_save;
    private JLabel lbl_pension_hotel_id;
    private JLabel lbl_pension_type;
    private JComboBox cmb_pension_type;
    private Pension pension;

    public PensionView(Pension pension) {
        this.pension = pension;
        this.add(container);
        guiInitialise(400,400);
        loadPensionFilter();
    }

    public void loadPensionFilter() {
        String[] filter = {"ULTRA ALL INCLUSIVE",
                "ALL INCLUSIVE", "ROOM BREAKFAST",
                "FULL BOARD", "HALF BOARD",
                "ROOM ONLY", "FULL CREDIT NON ALCOHOL"};
        this.cmb_pension_type.setModel(new DefaultComboBoxModel<>(filter));
        this.cmb_pension_type.setSelectedItem(null);
    }
}
