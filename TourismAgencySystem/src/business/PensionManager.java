package business;

import core.Helper;
import dao.PensionDao;
import entity.Hotel;
import entity.Pension;
import entity.User;

import java.sql.SQLException;
import java.util.ArrayList;

public class PensionManager {
    private final PensionDao pensionDao;

    public PensionManager() {
        this.pensionDao = new PensionDao();
    }

    public Pension getById(int id) {
        return this.pensionDao.getById(id);
    }

    public ArrayList<Pension> findPensionByHotelId(int selectedHotelId) {
        return this.pensionDao.findPensionByHotelId(selectedHotelId);
    }
    public ArrayList<Pension> findAll() {
        return this.pensionDao.findAll();
    }

    public ArrayList<Object[]> getForTable(int size, ArrayList<Pension> pensions) {
        ArrayList<Object[]> pensionList = new ArrayList<>();
        for(Pension obj : pensions) {
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = obj.getId();
            rowObject[i++] = obj.getHotel_id();
            rowObject[i++] = obj.getPension_type();
            pensionList.add(rowObject);
        }
        return pensionList;
    }

    public ArrayList<Object[]> getForReservation(int size, ArrayList<Pension> pensions) {
        ArrayList<Object[]> pensionList = new ArrayList<>();
        for(Pension obj : pensions) {
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = obj.getId();
            rowObject[i++] = obj.getPension_type();
            pensionList.add(rowObject);
        }
        return pensionList;
    }

    public boolean save(Pension pension) {
        if(this.getById(pension.getId()) != null) {
            Helper.showMessage("error");
            return false;
        }
        return this.pensionDao.save(pension);
    }

    public boolean update(Pension pension) {
        if(this.getById(pension.getId()) == null) {
            Helper.showMessage(pension.getId() + " ID - pension not found!");
            return false;
        }
        return this.pensionDao.update(pension);
    }

    public boolean delete(int id) {
        if(this.getById(id) == null) {
            Helper.showMessage(id + " ID - pension not found!");
            return false;
        }
        return this.pensionDao.delete(id);
    }

    public ArrayList<Object> pensionDetailsByHotelId(int hotelId) {
        return this.pensionDao.pensionDetailsByHotelId(hotelId);
    }

}
