package view;

import business.HotelManager;
import business.PensionManager;
import business.RoomManager;
import business.SeasonManager;
import core.Helper;
import entity.Room;

import javax.swing.*;

public class RoomSaveView extends Layout{
    private JPanel container;
    private JLabel lbl_roomsave_welcome;
    private JPanel w_left;
    private JPanel w_right;
    private JLabel lbl_roomsave_hotel;
    private JLabel lbl_roomsave_pension;
    private JLabel lbl_roomsave_season;
    private JLabel lbl_roomsave_roomtype;
    private JLabel lbl_roomsave_stock;
    private JLabel lbl_roomsave_price_adult;
    private JLabel lbl_roomsave_price_children;
    private JComboBox cmb_roomsave_roomtype;
    private JTextField fld_roomsave_stock;
    private JTextField fld_roomsave_price_adult;
    private JTextField fld_roomsave_price_children;
    private JLabel lbl_roomsave_room_capacity;
    private JLabel lbl_roomsave_room_area;
    private JLabel lbl_roomsave_tv;
    private JLabel lbl_roomsave_minibar;
    private JLabel lbl_roomsave_game_console;
    private JLabel lbl_roomsave_vault;
    private JLabel lbl_roomsave_projector;
    private JTextField fld_roomsave_room_area;
    private JPanel w_bottom;
    private JButton btn_roomsave_save;
    private JComboBox cmb_roomsave_tv;
    private JComboBox cmb_roomsave_minibar;
    private JComboBox cmb_roomsave_game_console;
    private JComboBox cmb_roomsave_vault;
    private JComboBox cmb_roomsave_projector;
    private JTextField fld_roomsave_hotel_id;
    private JTextField fld_roomsave_pension_id;
    private JTextField fld_roomsave_season_id;
    private JComboBox cmb_roomsave_bed_capacity;
    private JTextField fld_roomsave_pension;
    private JTextField fld_roomsave_season;
    private Room room;
    private RoomManager roomManager;
    private SeasonManager seasonManager;
    private PensionManager pensionManager;
    private HotelManager hotelManager;

    public RoomSaveView(Room selectedRoom) {
        this.add(container);
        guiInitialise(700,500);

        this.room = selectedRoom;
        this.roomManager = new RoomManager();
        this.seasonManager = new SeasonManager();
        this.pensionManager = new PensionManager();
        this.hotelManager = new HotelManager();

        loadRoomTypeFilter();

        int room_id = this.room.getId();

        if(room_id != 0) {
            this.fld_roomsave_hotel_id.setText(String.valueOf(room.getHotel_id()));
            this.fld_roomsave_pension_id.setText(String.valueOf(room.getPension_id()));
            this.fld_roomsave_season_id.setText(String.valueOf(room.getSeason_id()));
            this.cmb_roomsave_roomtype.setSelectedItem(room.getType());
            this.fld_roomsave_stock.setText(String.valueOf(room.getStock()));
            this.fld_roomsave_price_adult.setText(String.valueOf(room.getAdultPrice()));
            this.fld_roomsave_price_children.setText(String.valueOf(room.getChildrenPrice()));
            this.cmb_roomsave_bed_capacity.setSelectedItem(room.getBedCapacity());
            this.fld_roomsave_room_area.setText(String.valueOf(room.getArea()));
            this.cmb_roomsave_tv.setSelectedItem(room.isTv());
            this.cmb_roomsave_minibar.setSelectedItem(room.isMinibar());
            this.cmb_roomsave_game_console.setSelectedItem(room.isGameConsole());
            this.cmb_roomsave_vault.setSelectedItem(room.isVault());
            this.cmb_roomsave_projector.setSelectedItem(room.isProjector());
        }

        btn_roomsave_save.addActionListener(e -> {

            if(Helper.isFieldListEmpty(new JTextField[]{fld_roomsave_hotel_id, fld_roomsave_pension_id,
                    fld_roomsave_season_id, fld_roomsave_stock,
                    fld_roomsave_price_adult, fld_roomsave_price_children, fld_roomsave_room_area})) {
                Helper.showMessage("fill");
            } else {
                boolean result;
                this.room.setHotel_id(Integer.parseInt(fld_roomsave_hotel_id.getText()));
                this.room.setPension_id(Integer.parseInt(fld_roomsave_pension_id.getText()));
                this.room.setSeason_id(Integer.parseInt(fld_roomsave_season_id.getText()));
                this.room.setType((String) cmb_roomsave_roomtype.getSelectedItem());
                this.room.setStock(Integer.parseInt(fld_roomsave_stock.getText()));
                this.room.setAdultPrice(Double.parseDouble(fld_roomsave_price_adult.getText()));
                this.room.setChildrenPrice(Double.parseDouble(fld_roomsave_price_children.getText()));
                this.room.setBedCapacity(Integer.parseInt(cmb_roomsave_bed_capacity.getSelectedItem().toString()));
                this.room.setArea(Integer.parseInt(fld_roomsave_room_area.getText()));
                this.room.setTv((Boolean) cmb_roomsave_tv.getSelectedItem());
                this.room.setMinibar((Boolean) cmb_roomsave_minibar.getSelectedItem());
                this.room.setGameConsole((Boolean) cmb_roomsave_game_console.getSelectedItem());
                this.room.setVault((Boolean) cmb_roomsave_vault.getSelectedItem());
                this.room.setProjector((Boolean) cmb_roomsave_projector.getSelectedItem());
                if(room_id != 0) {
                    result = this.roomManager.update(this.room);
                } else {
                    result = this.roomManager.save(this.room);
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

    public void loadRoomTypeFilter() {
        String[] filter_roomType = {"SINGLE", "DOUBLE", "JUNIOR_SUITE", "SUITE"};
        this.cmb_roomsave_roomtype.setModel(new DefaultComboBoxModel<>(filter_roomType));
        this.cmb_roomsave_roomtype.setSelectedItem(null);

        Boolean[] filter = {true, false};
        this.cmb_roomsave_tv.setModel(new DefaultComboBoxModel<>(filter));
        this.cmb_roomsave_tv.setSelectedItem(null);
        this.cmb_roomsave_minibar.setModel(new DefaultComboBoxModel<>(filter));
        this.cmb_roomsave_minibar.setSelectedItem(null);
        this.cmb_roomsave_game_console.setModel(new DefaultComboBoxModel<>(filter));
        this.cmb_roomsave_game_console.setSelectedItem(null);
        this.cmb_roomsave_vault.setModel(new DefaultComboBoxModel<>(filter));
        this.cmb_roomsave_vault.setSelectedItem(null);
        this.cmb_roomsave_projector.setModel(new DefaultComboBoxModel<>(filter));
        this.cmb_roomsave_projector.setSelectedItem(null);

        Integer[] bedCapacityFilter = {1,2,3,4};
        this.cmb_roomsave_bed_capacity.setModel(new DefaultComboBoxModel(bedCapacityFilter));
        this.cmb_roomsave_bed_capacity.setSelectedItem(null);
    }
}
