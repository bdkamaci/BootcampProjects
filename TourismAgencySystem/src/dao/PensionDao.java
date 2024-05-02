package dao;

import core.Db;
import entity.Hotel;
import entity.Pension;
import entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PensionDao {
    private final Connection connection;

    public PensionDao() {
        this.connection = Db.getInstance();
    }

    public ArrayList<Pension> findPensionByHotelId(int selectedHotelId) {
        ArrayList<Pension> pensionList = new ArrayList<>();
        String query = "SELECT * FROM public.pension WHERE hotel_id = ";
        try {
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setInt(1, selectedHotelId);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                pensionList.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pensionList;
    }

    public Pension getById(int id)  {
        Pension obj = null;
        String query = "SELECT * FROM public.pension WHERE pension_id = ?";
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

    public Pension match(ResultSet resultSet) throws SQLException {
        Pension pension = new Pension();
        pension.setId(resultSet.getInt("pension_id"));
        pension.setHotel_id(resultSet.getInt("hotel_id"));
        pension.setPension_type(resultSet.getString("pension_type"));
        return pension;
    }

    public ArrayList<Pension> findAll() {
        return this.selectByQuery("SELECT * FROM public.pension ORDER BY pension_id ASC");
    }

    public ArrayList<Pension> selectByQuery(String query) {
        ArrayList<Pension> pensionList = new ArrayList<>();
        try {
            ResultSet rs = this.connection.createStatement().executeQuery(query);
            while (rs.next()) {
                pensionList.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pensionList;
    }

    public boolean update(Pension pension) {
        String query = "UPDATE public.pension SET " +
                "hotel_id = ?, " +
                "pension_type = ? " +
                "WHERE pension_id = ?";
        try {
            PreparedStatement pr = connection.prepareStatement(query);
            pr.setInt(1, pension.getHotel_id());
            pr.setString(2, pension.getPension_type());
            pr.setInt(3, pension.getId());
            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    public boolean save(Pension pension) {
        String query = "INSERT INTO public.pension " +
                "(" +
                "hotel_id, " +
                "pension_type" +
                ") " +
                " VALUES (?,?)";
        try {
            PreparedStatement pr = connection.prepareStatement(query);
            pr.setInt(1, pension.getHotel_id());
            pr.setString(2, pension.getPension_type());
            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    public boolean delete(int pension_id) {
        String query = "DELETE FROM public.pension WHERE pension_id = ?";
        try {
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setInt(1, pension_id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public ArrayList<Object> pensionDetailsByHotelId(int hotelId) {
        ArrayList<Object> pension = new ArrayList<>();
        String query = "SELECT pension_type FROM public.pension WHERE hotel_id = ?";
        try {
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setInt(1, hotelId);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                pension.add(rs.getInt("pension_id"));
                pension.add(rs.getString("pension_type"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pension;
    }

}
