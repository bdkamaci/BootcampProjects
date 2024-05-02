package dao;

import core.Db;
import entity.Hotel;
import entity.Season;
import entity.User;

import java.sql.*;
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

    public ArrayList<Season> findAll() {
        return this.selectByQuery("SELECT * FROM public.season ORDER BY season_id ASC");
    }

    public ArrayList<Season> selectByQuery(String query) {
        ArrayList<Season> seasonList = new ArrayList<>();
        try {
            ResultSet rs = this.connection.createStatement().executeQuery(query);
            while (rs.next()) {
                seasonList.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return seasonList;
    }

    public Season getById(int id)  {
        Season obj = null;
        String query = "SELECT * FROM public.season WHERE season_id = ?";
        try {
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if(rs.next()) {
                obj = this.match(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public boolean update(Season season) {
        String query = "UPDATE public.season SET " +
                "hotel_id = ?, " +
                "start_date = ?, " +
                "end_date = ? " +
                "WHERE season_id = ?";
        try {
            PreparedStatement pr = connection.prepareStatement(query);
            pr.setInt(1, season.getHotel_id());
            pr.setDate(2, Date.valueOf(season.getStart_date()));
            pr.setDate(3, Date.valueOf(season.getEnd_date()));
            pr.setInt(4, season.getId());
            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    public boolean save(Season season) {
        String query = "INSERT INTO public.season " +
                "(" +
                "hotel_id, " +
                "start_date, " +
                "end_date" +
                ") " +
                " VALUES (?,?,?)";
        try {
            PreparedStatement pr = connection.prepareStatement(query);
            pr.setInt(1, season.getHotel_id());
            pr.setDate(2, Date.valueOf(season.getStart_date()));
            pr.setDate(3, Date.valueOf(season.getEnd_date()));
            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    public boolean delete(int season_id) {
        String query = "DELETE FROM public.season WHERE season_id = ?";
        try {
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setInt(1, season_id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

}

