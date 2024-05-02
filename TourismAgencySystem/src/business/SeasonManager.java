package business;

import core.Helper;
import dao.SeasonDao;
import entity.Hotel;
import entity.Pension;
import entity.Season;
import entity.User;

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

    public boolean save(Season season) {
        if(this.getById(season.getId()) != null) {
            Helper.showMessage("error");
            return false;
        }
        return this.seasonDao.save(season);
    }

    public boolean update(Season season) {
        if(this.getById(season.getId()) == null) {
            Helper.showMessage(season.getId() + " ID - season not found!");
            return false;
        }
        return this.seasonDao.update(season);
    }

    public boolean delete(int id) {
        if(this.getById(id) == null) {
            Helper.showMessage(id + " ID - season not found!");
            return false;
        }
        return this.seasonDao.delete(id);
    }
}
