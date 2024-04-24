package business;

import dao.PensionDao;
import entity.Hotel;
import entity.Pension;

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
}
