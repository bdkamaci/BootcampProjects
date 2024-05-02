package view;

import business.SeasonManager;
import core.Helper;
import entity.Season;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.sql.Date;
import java.text.ParseException;
import java.time.LocalDate;

public class SeasonView extends Layout{
    private JPanel container;
    private JLabel lbl_season_welcome;
    private JLabel lbl_season_date_start;
    private JTextField fld_season_date_start;
    private JLabel lbl_season_date_end;
    private JTextField fld_season_date_end;
    private JButton btn_season_save;
    private JLabel lbl_season_hotel_id;
    private JTextField fld_season_hotel_id;
    private Season season;
    private SeasonManager seasonManager;

    public SeasonView(Season season) {
        this.seasonManager = new SeasonManager();
        this.season = season;
        this.add(container);
        guiInitialise(400,300);
        try {
            createUIComponents();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        int season_id = this.season.getId();

        if(season_id != 0) {
            this.fld_season_hotel_id.setText(String.valueOf(season.getHotel_id()));
            this.fld_season_date_start.setText(String.valueOf(Date.valueOf(season.getStart_date())));
            this.fld_season_date_end.setText(String.valueOf(Date.valueOf(season.getEnd_date())));
        }

        btn_season_save.addActionListener(e -> {
            if(Helper.isFieldListEmpty(new JTextField[]{fld_season_date_start, fld_season_date_end})) {
                Helper.showMessage("fill");
            } else {
                boolean result;
                this.season.setHotel_id(Integer.parseInt(fld_season_hotel_id.getText()));
                this.season.setStart_date(LocalDate.parse(fld_season_date_start.getText()));
                this.season.setEnd_date(LocalDate.parse(fld_season_date_end.getText()));
                if(season_id != 0) {
                    result = this.seasonManager.update(this.season);
                } else {
                    result = this.seasonManager.save(this.season);
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

    private void createUIComponents() throws ParseException {
        // custom component creation code
        this.fld_season_date_start = new JFormattedTextField(new MaskFormatter("####-##-##"));
        this.fld_season_date_start.setText("2024-01-01");
        this.fld_season_date_end = new JFormattedTextField(new MaskFormatter("####-##-##"));
        this.fld_season_date_end.setText("2024-01-01");
    }
}
