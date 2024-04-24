package business;

import dao.SeasonDao;
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
}
