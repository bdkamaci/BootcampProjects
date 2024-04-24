package view;

import entity.Season;

import javax.swing.*;

public class SeasonView extends Layout{
    private JPanel container;
    private JLabel lbl_season_welcome;
    private JLabel lbl_season_date_start;
    private JTextField fld_season_date_start;
    private JLabel lbl_season_date_end;
    private JTextField fld_season_date_end;
    private JButton btn_season_save;
    private Season season;

    public SeasonView(Season season) {
        this.season = season;
        this.add(container);
        guiInitialise(400,300);
    }
}
