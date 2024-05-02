package dao;

import core.Db;
import entity.Hotel;
import entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HotelDao {
    private final Connection connection;

    public HotelDao() {
        this.connection = Db.getInstance();
    }

    public ArrayList<Hotel> findAll() {
        return this.selectByQuery("SELECT * FROM public.hotel ORDER BY hotel_id ASC");
    }

    public Hotel getById(int id)  {
        Hotel obj = null;
        String query = "SELECT * FROM public.hotel WHERE hotel_id = ?";
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

    public boolean save(Hotel hotel) {
        String query = "INSERT INTO public.hotel " +
                "(" +
                "hotel_name, " +
                "hotel_address, " +
                "hotel_mail, " +
                "hotel_phone, " +
                "hotel_star, " +
                "hotel_autopark, " +
                "hotel_wifi, " +
                "hotel_pool, " +
                "hotel_gym, " +
                "hotel_concierge, " +
                "hotel_spa, " +
                "hotel_room_service" +
                ")" +
                " VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pr = connection.prepareStatement(query);
            pr.setString(1, hotel.getName());
            pr.setString(2, hotel.getAddress());
            pr.setString(3, hotel.getMail());
            pr.setString(4, hotel.getPhone());
            pr.setString(5, hotel.getStar());
            pr.setBoolean(6, hotel.isAutopark());
            pr.setBoolean(7, hotel.isWifi());
            pr.setBoolean(8, hotel.isPool());
            pr.setBoolean(9, hotel.isGym());
            pr.setBoolean(10, hotel.isConcierge());
            pr.setBoolean(11, hotel.isSpa());
            pr.setBoolean(12, hotel.isRoomService());


            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    public boolean delete(int hotel_id) {
        String query = "DELETE FROM public.hotel WHERE hotel_id = ?";
        try {
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setInt(1, hotel_id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean update(Hotel hotel) {
        String query = "UPDATE public.hotel SET " +
                "hotel_name = ?, " +
                "hotel_address = ?, " +
                "hotel_mail = ?, " +
                "hotel_phone = ?, " +
                "hotel_star = ?, " +
                "hotel_autopark = ?, " +
                "hotel_wifi = ?, " +
                "hotel_pool = ?, " +
                "hotel_gym = ?, " +
                "hotel_concierge = ?, " +
                "hotel_spa = ?, " +
                "hotel_room_service = ? " +
                "WHERE hotel_id = ?";
        try {
            PreparedStatement pr = connection.prepareStatement(query);
            pr.setString(1, hotel.getName());
            pr.setString(2, hotel.getAddress());
            pr.setString(3, hotel.getMail());
            pr.setString(4, hotel.getPhone());
            pr.setString(5, hotel.getStar());
            pr.setBoolean(6, hotel.isAutopark());
            pr.setBoolean(7, hotel.isWifi());
            pr.setBoolean(8, hotel.isPool());
            pr.setBoolean(9, hotel.isGym());
            pr.setBoolean(10, hotel.isConcierge());
            pr.setBoolean(11, hotel.isSpa());
            pr.setBoolean(12, hotel.isRoomService());
            pr.setInt(13, hotel.getId());
            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    public Hotel match(ResultSet resultSet) throws SQLException {
        Hotel hotel = new Hotel();
        hotel.setId(resultSet.getInt("hotel_id"));
        hotel.setName(resultSet.getString("hotel_name"));
        hotel.setAddress(resultSet.getString("hotel_address"));
        hotel.setMail(resultSet.getString("hotel_mail"));
        hotel.setPhone(resultSet.getString("hotel_phone"));
        hotel.setStar(resultSet.getString("hotel_star"));
        hotel.setAutopark(resultSet.getBoolean("hotel_autopark"));
        hotel.setWifi(resultSet.getBoolean("hotel_wifi"));
        hotel.setPool(resultSet.getBoolean("hotel_pool"));
        hotel.setGym(resultSet.getBoolean("hotel_gym"));
        hotel.setConcierge(resultSet.getBoolean("hotel_concierge"));
        hotel.setSpa(resultSet.getBoolean("hotel_spa"));
        hotel.setRoomService(resultSet.getBoolean("hotel_room_service"));
        return hotel;
    }

    public ArrayList<Hotel> selectByQuery(String query) {
        ArrayList<Hotel> hotelList = new ArrayList<>();
        try {
            ResultSet rs = this.connection.createStatement().executeQuery(query);
            while (rs.next()) {
                hotelList.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hotelList;
    }
}
