package business;

import core.Helper;
import dao.ReservationDao;
import dao.RoomDao;
import entity.Reservation;
import entity.Room;
import entity.Season;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class RoomManager {
    private final RoomDao roomDao;
    private final ReservationDao reservationDao;

    public RoomManager() {
        this.roomDao = new RoomDao();
        this.reservationDao = new ReservationDao();
    }

    public Room getById(int id) {
        return this.roomDao.getById(id);
    }

    public ArrayList<Room> findAll() {
        return this.roomDao.findAll();
    }

    public boolean save(Room room) {
        if(this.getById(room.getId()) != null) {
            Helper.showMessage("error");
            return false;
        }
        return this.roomDao.save(room);
    }

    public boolean update(Room room) {
        if(this.getById(room.getId()) != null) {
            Helper.showMessage(room.getId() + " ID - room not found!");
            return false;
        }
        return this.roomDao.update(room);
    }

    public boolean delete(int id) {
        if(this.getById(id) == null) {
            Helper.showMessage(id + " ID - room not found!");
            return false;
        }
        return this.roomDao.delete(id);
    }

    public ArrayList<Object[]> getForTable(int size, ArrayList<Room> rooms) {
        ArrayList<Object[]> roomList = new ArrayList<>();
        for(Room obj : rooms) {
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = obj.getId();
            rowObject[i++] = obj.getHotel_id();
            rowObject[i++] = obj.getPension_id();
            rowObject[i++] = obj.getSeason_id();
            rowObject[i++] = obj.getType();
            rowObject[i++] = obj.getStock();
            rowObject[i++] = obj.getAdultPrice();
            rowObject[i++] = obj.getChildrenPrice();
            rowObject[i++] = obj.getBedCapacity();
            rowObject[i++] = obj.getArea();
            rowObject[i++] = obj.isTv();
            rowObject[i++] = obj.isMinibar();
            rowObject[i++] = obj.isGameConsole();
            rowObject[i++] = obj.isVault();
            rowObject[i++] = obj.isProjector();
            roomList.add(rowObject);
        }
        return roomList;
    }

    public ArrayList<Object[]> getForReservation(int size, ArrayList<Room> rooms) {
        ArrayList<Object[]> roomList = new ArrayList<>();
        for(Room obj : rooms) {
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = obj.getId();
            rowObject[i++] = obj.getHotel_id();
            rowObject[i++] = obj.getPension_id();
            rowObject[i++] = obj.getSeason_id();
            rowObject[i++] = obj.getType();
            roomList.add(rowObject);
        }
        return roomList;
    }

    public ArrayList<Room> getForFilter(String selectedHotelName, String selectedHotelCity, LocalDate selectedCheckIn, LocalDate selectedCheckOut, int totalBedCapacity) {
        return this.roomDao.getForFilter(selectedHotelName, selectedHotelCity, selectedCheckIn, selectedCheckOut, totalBedCapacity);
    }

    public ArrayList<Room> selectByQuery(String query){
        return this.roomDao.selectByQuery(query);
    }

}
