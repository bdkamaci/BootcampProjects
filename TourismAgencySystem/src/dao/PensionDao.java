package dao;

import core.Db;
import entity.Hotel;
import entity.Pension;

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

}
