package view;

import business.BrandManager;
import business.CarManager;
import business.ModelManager;
import core.ComboItem;
import core.Helper;
import entity.Brand;
import entity.Car;
import entity.Model;
import entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import java.awt.event.*;
import java.text.ParseException;
import java.util.ArrayList;

public class AdminView extends Layout{
    private JPanel container;
    private JLabel lbl_welcome;
    private JPanel pnl_top;
    private JTabbedPane pnl_model;
    private JPanel tab_menu;
    private JButton btn_logout;
    private JScrollPane scrl_brand;
    private JTable tbl_brand;
    private JComboBox<ComboItem> cmb_s_model_brand;
    private JComboBox<Model.Type> cmb_s_model_type;
    private JComboBox<Model.Fuel> cmb_s_model_fuel;
    private JComboBox cmb_s_model_gear;
    private JTable tbl_model;
    private JButton btn_model_search;
    private JLabel lbl_s_brand;
    private JLabel lbl_s_gear;
    private JLabel lbl_s_fuel;
    private JLabel lbl_s_type;
    private JButton btn_cncl_fltr;
    private JTable tbl_car;
    private JScrollPane scrl_cars;
    private JPanel pnl_booking;
    private JLabel lbl_strt_date;
    private JLabel lbl_fnsh_date;
    private JLabel lbl_book_gear;
    private JLabel lbl_book_fuel;
    private JLabel lbl_book_type;
    private JFormattedTextField fld_strt_date;
    private JFormattedTextField fld_fnsh_date;
    private JComboBox cmb_book_gear;
    private JComboBox cmb_book_fuel;
    private JComboBox cmb_book_type;
    private JButton btn_book_search;
    private JScrollPane scrl_book;
    private JTable tbl_book;
    private JButton btn_book_delete_filter;
    private User user;
    private DefaultTableModel tmdl_brand = new DefaultTableModel();
    private DefaultTableModel tmdl_model = new DefaultTableModel();
    private DefaultTableModel tmdl_car = new DefaultTableModel();
    private DefaultTableModel tmdl_book = new DefaultTableModel();
    private BrandManager brandManager;
    private ModelManager modelManager;
    private JPopupMenu brand_menu;
    private JPopupMenu model_menu;
    private JPopupMenu car_menu;
    private JPopupMenu book_menu;
    private Object[] col_mtbl_model;
    private CarManager carManager;
    private Object[] col_booking_list;

    public AdminView(User user) {
        this.carManager = new CarManager();
        this.brandManager = new BrandManager();
        this.modelManager = new ModelManager();
        this.add(container);
        this.guiInitialise(1000,500);
        this.user = user;
        if(this.user == null) {
            dispose();
        }

        this.lbl_welcome.setText("Welcome: " + this.user.getUsername());

        // Brand Tab Menu
        loadBrandTable();
        loadBrandComponent();

        // Model Tab Menu
        loadModelTable(null);
        loadModelComponent();
        loadModelFilter();

        // Car Tab Menu
        loadCarTable();
        loadCarComponent();

        // Booking Tab Menu
        loadBookingTable(null);
        loadBookingComponent();
        loadBookingFilter();

        this.tbl_brand.setComponentPopupMenu(brand_menu);
        this.tbl_model.setComponentPopupMenu(model_menu);

    }

    public void loadBookingTable(ArrayList<Object[]> carList) {
        col_booking_list = new Object[]{"ID", "Brand", "Model", "Plate", "Color", "KM", "Year", "Type", "Fuel", "Gear"};
        generateTable(this.tmdl_book, this.tbl_book, col_booking_list, carList);
    }

    public void loadBookingComponent() {
        tableRowSelect(this.tbl_book);
        this.book_menu = new JPopupMenu();
        this.book_menu.add("Book a Car").addActionListener(e -> {
            int selectCarId = this.getTableSelectedRow(this.tbl_book, 0);
            BookingView bookingView = new BookingView(this.carManager.getById(selectCarId), this.fld_strt_date.getText(), this.fld_fnsh_date.getText());
            bookingView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadBookingTable(null);
                    loadBookingFilter();
                }
            });
        });
        this.tbl_book.setComponentPopupMenu(book_menu);

        btn_book_search.addActionListener(e -> {
            ArrayList<Car> carList = this.carManager.searchForBooking(fld_strt_date.getText(), fld_fnsh_date.getText(), (Model.Gear) cmb_book_gear.getSelectedItem(), (Model.Fuel) cmb_book_fuel.getSelectedItem(), (Model.Type) cmb_book_type.getSelectedItem());
            ArrayList<Object[]> carBookingRow = this.carManager.getForTable(this.col_booking_list.length, carList);
            loadBookingTable(carBookingRow);
        });
        btn_book_delete_filter.addActionListener(e -> {
            loadBookingFilter();
        });
    }

    public void loadBookingFilter() {
        this.cmb_book_type.setModel(new DefaultComboBoxModel<>(Model.Type.values()));
        this.cmb_book_type.setSelectedItem(null);
        this.cmb_book_fuel.setModel(new DefaultComboBoxModel<>(Model.Fuel.values()));
        this.cmb_book_fuel.setSelectedItem(null);
        this.cmb_book_gear.setModel(new DefaultComboBoxModel<>(Model.Gear.values()));
        this.cmb_book_gear.setSelectedItem(null);
    }

    public void loadCarTable() {
        Object[] col_car = {"ID", "Brand", "Model", "Plate", "Color", "KM", "Year", "Type", "Fuel", "Gear"};
        ArrayList<Object[]> carList = this.carManager.getForTable(col_car.length, this.carManager.findAll());
        generateTable(this.tmdl_car, this.tbl_car, col_car, carList);
    }

    public void loadCarComponent() {
        tableRowSelect(this.tbl_car);

        this.car_menu = new JPopupMenu();
        this.car_menu.add("New").addActionListener(e -> {
            CarView carView = new CarView(new Car());
            carView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadCarTable();
                }
            });

        });
        this.car_menu.add("Update").addActionListener(e -> {
            int selectCarId = this.getTableSelectedRow(tbl_car, 0);
            CarView carView = new CarView(this.carManager.getById(selectCarId));
            carView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadCarTable();
                }
            });
        });
        this.car_menu.add("Delete").addActionListener(e -> {
            if(Helper.confirm("sure")) {
                int selectCarId = this.getTableSelectedRow(tbl_car, 0);
                if(this.carManager.delete(selectCarId)) {
                    Helper.showMessage("done");
                    loadCarTable();
                } else {
                    Helper.showMessage("error");
                }
            }
        });
    }

    public void loadModelTable(ArrayList<Object[]> modelList) {
        this.col_mtbl_model = new Object[]{"Model ID", "Brand Name ", "Model Name", "Type", "Year", "Fuel", "Gear"};
        if(modelList == null) {
            modelList = this.modelManager.getForTable(this.col_mtbl_model.length, this.modelManager.findAll());
        }
        this.generateTable(this.tmdl_model, this.tbl_model, col_mtbl_model, modelList);
    }

    public void loadModelFilter() {
        this.cmb_s_model_type.setModel(new DefaultComboBoxModel<>(Model.Type.values()));
        this.cmb_s_model_type.setSelectedItem(null);
        this.cmb_s_model_gear.setModel(new DefaultComboBoxModel<>(Model.Gear.values()));
        this.cmb_s_model_gear.setSelectedItem(null);
        this.cmb_s_model_fuel.setModel(new DefaultComboBoxModel<>(Model.Fuel.values()));
        this.cmb_s_model_fuel.setSelectedItem(null);
        loadModelFilterBrand();
    }

    public void loadModelFilterBrand() {
        this.cmb_s_model_brand.removeAllItems();
        for (Brand obj : brandManager.findAll()) {
            this.cmb_s_model_brand.addItem(new ComboItem(obj.getId(), obj.getName()));
        }
        this.cmb_s_model_brand.setSelectedItem(null);
    }


    public void loadModelComponent() {
        tableRowSelect(this.tbl_model);

        this.model_menu = new JPopupMenu();
        this.model_menu.add("New").addActionListener(e -> {
           ModelView modelView = new ModelView(new Model());
           modelView.addWindowListener(new WindowAdapter() {
               @Override
               public void windowClosed(WindowEvent e) {
                   loadModelTable(null);
               }
           });

        });
        this.model_menu.add("Update").addActionListener(e -> {
            int selectModelId = this.getTableSelectedRow(tbl_model, 0);
            ModelView modelView = new ModelView(this.modelManager.getById(selectModelId));
            modelView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadModelTable(null);
                }
            });
        });
        this.model_menu.add("Delete").addActionListener(e -> {
            if(Helper.confirm("sure")) {
                int selectModelId = this.getTableSelectedRow(tbl_model, 0);
                if(this.modelManager.delete(selectModelId)) {
                    Helper.showMessage("done");
                    loadModelTable(null);
                } else {
                    Helper.showMessage("error");
                }
            }
        });

        this.btn_model_search.addActionListener(e -> {
            ComboItem selectedBrand = (ComboItem) this.cmb_s_model_brand.getSelectedItem();
            int brandId = 0;
            if(selectedBrand != null) {
                brandId = selectedBrand.getKey();
            }
            ArrayList<Model> modelListBySearch = this.modelManager.filterForTable(brandId,(Model.Fuel) cmb_s_model_fuel.getSelectedItem(), (Model.Gear) cmb_s_model_gear.getSelectedItem(), (Model.Type) cmb_s_model_type.getSelectedItem());
            ArrayList<Object[]> modelRowListBySearch = this.modelManager.getForTable(this.col_mtbl_model.length, modelListBySearch);
            loadModelTable(modelRowListBySearch);
        });

        this.btn_cncl_fltr.addActionListener(e -> {
            this.cmb_s_model_type.setSelectedItem(null);
            this.cmb_s_model_gear.setSelectedItem(null);
            this.cmb_s_model_fuel.setSelectedItem(null);
            this.cmb_s_model_brand.setSelectedItem(null);
            loadModelTable(null);
        });


    }

    public void loadBrandComponent() {
        tableRowSelect(this.tbl_brand);

        this.brand_menu = new JPopupMenu();
        this.brand_menu.add("New").addActionListener(e -> {
            BrandView brandView = new BrandView(null);
            brandView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadBrandTable();
                    loadModelTable(null);
                    loadModelFilterBrand();
                }
            });
        });
        this.brand_menu.add("Update").addActionListener(e -> {
            int selectBrandId = this.getTableSelectedRow(tbl_brand, 0);
            BrandView brandView = new BrandView(this.brandManager.getById(selectBrandId));
            brandView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadBrandTable();
                    loadModelTable(null);
                    loadModelFilterBrand();
                }
            });
        });
        this.brand_menu.add("Delete").addActionListener(e -> {
            if(Helper.confirm("sure")) {
                int selectBrandId = this.getTableSelectedRow(tbl_brand, 0);
                if(this.brandManager.delete(selectBrandId)) {
                    Helper.showMessage("done");
                    loadBrandTable();
                    loadModelTable(null);
                    loadModelFilterBrand();
                } else {
                    Helper.showMessage("error");
                }
            }
        });
    }

    public void loadBrandTable() {
        Object[] col_mtbl_brand = {"Brand ID", "Brand Name"};
        ArrayList<Object[]> brandList = this.brandManager.getForTable(col_mtbl_brand.length);
        this.generateTable(this.tmdl_brand, this.tbl_brand, col_mtbl_brand, brandList);
    }

    private void createUIComponents() throws ParseException {
        // custom component creation code
        this.fld_strt_date = new JFormattedTextField(new MaskFormatter("##/##/####"));
        this.fld_strt_date.setText("01/01/2024");
        this.fld_fnsh_date = new JFormattedTextField(new MaskFormatter("##/##/####"));
        this.fld_fnsh_date.setText("01/01/2024");
    }
}
