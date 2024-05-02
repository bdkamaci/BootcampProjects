package dao;

import core.Db;
import entity.Room;
import entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class RoomDao {
    private final Connection connection;

    public RoomDao() {
        this.connection = Db.getInstance();
    }

    public ArrayList<Room> findAll() {
        return this.selectByQuery("SELECT * FROM public.room ORDER BY room_id ASC");
    }

    public Room getById(int id)  {
        Room obj = null;
        String query = "SELECT * FROM public.room WHERE hotel_id = ?";
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

    public boolean update(Room room) {
        String query = "UPDATE public.room SET " +
                "hotel_id = ?, " +
                "pension_id = ?, " +
                "season_id = ?, " +
                "room_type = ?, " +
                "stock = ?, " +
                "adult_price = ?, " +
                "children_price = ?, " +
                "bed_capacity = ?, " +
                "area = ?, " +
                "tv = ?, " +
                "minibar = ?, " +
                "game_console = ?, " +
                "vault = ?, " +
                "projector = ? " +
                "WHERE room_id = ?";
        try {
            PreparedStatement pr = connection.prepareStatement(query);
            pr.setInt(1, room.getHotel_id());
            pr.setInt(2, room.getPension_id());
            pr.setInt(3, room.getSeason_id());
            pr.setString(4, room.getType());
            pr.setInt(5, room.getStock());
            pr.setDouble(6, room.getAdultPrice());
            pr.setDouble(7, room.getChildrenPrice());
            pr.setInt(8, room.getBedCapacity());
            pr.setInt(9, room.getArea());
            pr.setBoolean(10, room.isTv());
            pr.setBoolean(11, room.isMinibar());
            pr.setBoolean(12, room.isGameConsole());
            pr.setBoolean(13, room.isVault());
            pr.setBoolean(14, room.isProjector());
            pr.setInt(15, room.getId());
            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }


    public boolean save(Room room) {
        String query = "INSERT INTO public.room " +
                "(" +
                "hotel_id," +
                "pension_id," +
                "season_id," +
                "room_type, " +
                "stock, " +
                "adult_price, " +
                "children_price, " +
                "bed_capacity, " +
                "area, " +
                "tv, " +
                "minibar, " +
                "game_console, " +
                "vault, " +
                "projector" +
                ")" +
                " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pr = connection.prepareStatement(query);
            pr.setInt(1, room.getHotel_id());
            pr.setInt(2, room.getPension_id());
            pr.setInt(3, room.getSeason_id());
            pr.setString(4, room.getType());
            pr.setInt(5, room.getStock());
            pr.setDouble(6, room.getAdultPrice());
            pr.setDouble(7, room.getChildrenPrice());
            pr.setInt(8, room.getBedCapacity());
            pr.setInt(9, room.getArea());
            pr.setBoolean(10, room.isTv());
            pr.setBoolean(11, room.isMinibar());
            pr.setBoolean(12, room.isGameConsole());
            pr.setBoolean(13, room.isVault());
            pr.setBoolean(14, room.isProjector());
            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    public boolean delete(int room_id) {
        String query = "DELETE FROM public.room WHERE room_id = ?";
        try {
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setInt(1, room_id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public Room match(ResultSet resultSet) throws SQLException {
        Room room = new Room();
        room.setId(resultSet.getInt("room_id"));
        room.setHotel_id(resultSet.getInt("hotel_id"));
        room.setPension_id(resultSet.getInt("pension_id"));
        room.setSeason_id(resultSet.getInt("season_id"));
        room.setType(resultSet.getString("room_type"));
        room.setStock(resultSet.getInt("stock"));
        room.setAdultPrice(resultSet.getDouble("adult_price"));
        room.setChildrenPrice(resultSet.getDouble("children_price"));
        room.setBedCapacity(resultSet.getInt("bed_capacity"));
        room.setArea(resultSet.getInt("area"));
        room.setTv(resultSet.getBoolean("tv"));
        room.setMinibar(resultSet.getBoolean("minibar"));
        room.setGameConsole(resultSet.getBoolean("game_console"));
        room.setVault(resultSet.getBoolean("vault"));
        room.setProjector(resultSet.getBoolean("projector"));
        return room;
    }

    public ArrayList<Room> selectByQuery(String query) {
        ArrayList<Room> roomList = new ArrayList<>();
        try {
            ResultSet rs = this.connection.createStatement().executeQuery(query);
            while (rs.next()) {
                roomList.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roomList;
    }

    public ArrayList<Room> getForFilter(String selectedHotelName, String selectedHotelCity, LocalDate selectedCheckIn, LocalDate selectedCheckOut, int totalBedCapacity) {
        String query = "SELECT * FROM public.room AS r\n" +
                "LEFT JOIN public.season AS s\n" +
                "ON r.season_id = s.season_id\n" +
                "LEFT JOIN public.hotel AS h\n" +
                "ON r.hotel_id = h.hotel_id\n" +
                "WHERE hotel_name = '" + selectedHotelName + "' " +
                "AND hotel_address = '" + selectedHotelCity + "' " +
                "AND start_date <= '" + selectedCheckIn + "' " +
                "AND end_date >= '" + selectedCheckOut + "' " +
                "AND bed_capacity >= " + totalBedCapacity + ";";
        return this.selectByQuery(query);
    }


}
