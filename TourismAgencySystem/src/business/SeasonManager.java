package business;

import dao.SeasonDao;
import entity.Hotel;
import entity.Pension;
import entity.Season;

import java.util.ArrayList;

public class SeasonManager {
    private final SeasonDao seasonDao;

    public SeasonManager() {
        this.seasonDao = new SeasonDao();
    }

    public ArrayList<Season> findSeasonByHotelId(int selectedHotelId) {
        return this.seasonDao.findSeasonByHotelId(selectedHotelId);
    }

    public ArrayList<Season> findAll() {
        return this.seasonDao.findAll();
    }

    public ArrayList<Object[]> getForTable(int size, ArrayList<Season> seasons) {
        ArrayList<Object[]> seasonList = new ArrayList<>();
        for(Season obj : seasons) {
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = obj.getId();
            rowObject[i++] = obj.getHotel_id();
            rowObject[i++] = obj.getStart_date();
            rowObject[i++] = obj.getEnd_date();
            seasonList.add(rowObject);
        }
        return seasonList;
    }

    public Season getById(int id) {
        return this.seasonDao.getById(id);
    }
}
