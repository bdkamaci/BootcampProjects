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
}
