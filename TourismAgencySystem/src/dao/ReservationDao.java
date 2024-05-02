package dao;

import core.Db;
import entity.Hotel;
import entity.Reservation;
import entity.User;

import java.sql.*;
import java.util.ArrayList;

public class ReservationDao {
    private final Connection connection;

    public ReservationDao() {
        this.connection = Db.getInstance();
    }

    public ArrayList<Reservation> findAll() {
        return this.selectByQuery("SELECT * FROM public.reservation ORDER BY reservation_id ASC");
    }

    public Reservation getById(int id)  {
        Reservation obj = null;
        String query = "SELECT * FROM public.reservation WHERE reservation_id = ?";
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

    public boolean save(Reservation reservation) {
        String query = "INSERT INTO public.reservation " +
                "(" +
                "room_id," +
                "check_in," +
                "check_out," +
                "guest_count," +
                "guest_identification," +
                "guest_mail," +
                "guest_phone," +
                "guest_name," +
                "guest_count_adult," +
                "guest_count_children" +
                ")" +
                " VALUES (?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pr = connection.prepareStatement(query);
            pr.setInt(1, reservation.getRoom_id());
            pr.setDate(2, Date.valueOf(reservation.getCheckIn()));
            pr.setDate(3, Date.valueOf(reservation.getCheckOut()));
            pr.setInt(4, reservation.getGuestCount());
            pr.setString(5, reservation.getGuestIdentification());
            pr.setString(6, reservation.getGuestMail());
            pr.setString(7, reservation.getGuestPhone());
            pr.setString(8, reservation.getGuestName());
            pr.setInt(9, reservation.getGuestCountAdult());
            pr.setInt(10, reservation.getGuestCountChildren());
            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    public boolean update(Reservation reservation) {
        String query = "UPDATE public.reservation SET " +
                "room_id = ?, " +
                "check_in = ?, " +
                "check_out = ?, " +
                "guest_count = ?, " +
                "guest_identification = ?, " +
                "guest_mail = ?, " +
                "guest_phone = ?, " +
                "guest_name = ?, " +
                "guest_count_adult = ?, " +
                "guest_count_children = ? " +
                "WHERE reservation_id = ?";
        try {
            PreparedStatement pr = connection.prepareStatement(query);
            pr.setInt(1, reservation.getRoom_id());
            pr.setDate(2, Date.valueOf(reservation.getCheckIn()));
            pr.setDate(3, Date.valueOf(reservation.getCheckOut()));
            pr.setInt(4, reservation.getGuestCount());
            pr.setString(5, reservation.getGuestIdentification());
            pr.setString(6, reservation.getGuestMail());
            pr.setString(7, reservation.getGuestPhone());
            pr.setString(8, reservation.getGuestName());
            pr.setInt(9, reservation.getGuestCountAdult());
            pr.setInt(10, reservation.getGuestCountChildren());
            pr.setInt(11, reservation.getId());
            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    public boolean delete(int reservation_id) {
        String query = "DELETE FROM public.reservation WHERE reservation_id = ?";
        try {
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setInt(1, reservation_id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public Reservation match(ResultSet resultSet) throws SQLException {
        Reservation reservation = new Reservation();
        reservation.setId(resultSet.getInt("reservation_id"));
        reservation.setRoom_id(resultSet.getInt("room_id"));
        reservation.setCheckIn(resultSet.getDate("check_in").toLocalDate());
        reservation.setCheckOut(resultSet.getDate("check_out").toLocalDate());
        reservation.setGuestCount(resultSet.getInt("guest_count"));
        reservation.setGuestIdentification(resultSet.getString("guest_identification"));
        reservation.setGuestMail(resultSet.getString("guest_mail"));
        reservation.setGuestPhone(resultSet.getString("guest_phone"));
        reservation.setGuestName(resultSet.getString("guest_name"));
        reservation.setGuestCountAdult(resultSet.getInt("guest_count_adult"));
        reservation.setGuestCountChildren(resultSet.getInt("guest_count_children"));
        return reservation;
    }

    public ArrayList<Reservation> selectByQuery(String query) {
        ArrayList<Reservation> reservationList = new ArrayList<>();
        try {
            ResultSet rs = this.connection.createStatement().executeQuery(query);
            while (rs.next()) {
                reservationList.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservationList;
    }
}
