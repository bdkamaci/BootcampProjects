package business;

import core.Helper;
import dao.ReservationDao;
import entity.Hotel;
import entity.Reservation;
import entity.Room;
import entity.User;

import java.util.ArrayList;

public class ReservationManager {
    private final ReservationDao reservationDao;

    public ReservationManager() {
        this.reservationDao = new ReservationDao();
    }

    public Reservation getById(int id) {
        return this.reservationDao.getById(id);
    }

    public ArrayList<Reservation> findAll() {
        return this.reservationDao.findAll();
    }

    public boolean save(Reservation reservation) {
        if(this.getById(reservation.getId()) != null) {
            Helper.showMessage("error");
            return false;
        }
        return this.reservationDao.save(reservation);
    }

    public boolean update(Reservation reservation) {
        if(this.getById(reservation.getId()) == null) {
            Helper.showMessage(reservation.getId() + " ID - reservation not found!");
            return false;
        }
        return this.reservationDao.update(reservation);
    }

    public boolean delete(int id) {
        if(this.getById(id) == null) {
            Helper.showMessage(id + " ID - reservation not found!");
            return false;
        }
        return this.reservationDao.delete(id);
    }

    public ArrayList<Object[]> getForTable(int size, ArrayList<Reservation> reservations) {
        ArrayList<Object[]> reservationList = new ArrayList<>();
        for(Reservation obj : reservations) {
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = obj.getId();
            rowObject[i++] = obj.getRoom_id();
            rowObject[i++] = obj.getCheckIn();
            rowObject[i++] = obj.getCheckOut();
            rowObject[i++] = obj.getGuestCount();
            rowObject[i++] = obj.getGuestIdentification();
            rowObject[i++] = obj.getGuestMail();
            rowObject[i++] = obj.getGuestPhone();
            rowObject[i++] = obj.getGuestName();
            rowObject[i++] = obj.getGuestCountAdult();
            rowObject[i++] = obj.getGuestCountChildren();
            reservationList.add(rowObject);
        }
        return reservationList;
    }
}
