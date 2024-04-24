package dao;

import core.Db;
import entity.Season;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SeasonDao {
    private final Connection connection;

    public SeasonDao() {
        this.connection = Db.getInstance();
    }

    public ArrayList<Season> findSeasonByHotelId(int selectedHotelId) {
        ArrayList<Season> seasonList = new ArrayList<>();
        String query = "SELECT * FROM public.season WHERE hotel_id = ";
        try {
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setInt(1, selectedHotelId);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                seasonList.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return seasonList;
    }

    public Season match(ResultSet resultSet) throws SQLException {
        Season season = new Season();
        season.setId(resultSet.getInt("season_id"));
        season.setHotel_id(resultSet.getInt("hotel_id"));
        season.setStart_date(resultSet.getDate("start_date").toLocalDate());
        season.setEnd_date(resultSet.getDate("end_date").toLocalDate());
        return season;
    }
}
