package business;

import core.Helper;
import dao.ReservationDao;
import entity.Hotel;
import entity.Reservation;

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
}
