package business;

import core.Helper;
import dao.HotelDao;
import entity.Hotel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class HotelManager {
    private final HotelDao hotelDao;

    public HotelManager() {
        this.hotelDao = new HotelDao();
    }

    public Hotel getById(int id) {
        return this.hotelDao.getById(id);
    }

    public ArrayList<Hotel> findAll() {
        return this.hotelDao.findAll();
    }

    public boolean save(Hotel hotel) {
        if(this.getById(hotel.getId()) != null) {
            Helper.showMessage("error");
            return false;
        }
        return this.hotelDao.save(hotel);
    }

    /*public ArrayList<Hotel> searchForBooking(String start_date, String end_date, Model.Gear gear, Model.Fuel fuel, Model.Type type) {
        String query = "SELECT * FROM public.car AS c LEFT JOIN public.model AS m";
        ArrayList<String> where = new ArrayList<>();
        ArrayList<String> joinWhere = new ArrayList<>();
        ArrayList<String> bookOrWhere = new ArrayList<>();

        joinWhere.add("c.car_model_id = m.model_id");

        // In Date Format -> dd/MM/yyyy -> 01/01/2024
        // Out Day Format -> Y-m-d -> 2024-01-01
        start_date = LocalDate.parse(start_date, DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString();
        end_date = LocalDate.parse(end_date, DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString();

        if(fuel != null) { where.add("m.model_fuel = '" + fuel.toString() + "'"); }
        if(gear != null) { where.add("m.model_gear = '" + gear.toString() + "'"); }
        if(type != null) { where.add("m.model_type = '" + type.toString() + "'"); }

        String whereStr = String.join(" AND ", where);
        String joinStr = String.join(" AND ", joinWhere);

        if(joinStr.length() > 0) {
            query += " ON " + joinStr;
        }

        if(whereStr.length() > 0) {
            query += " WHERE " + whereStr;
        }

        ArrayList<Car> searchedCarList = this.carDao.selectByQuery(query);

        bookOrWhere.add("('" + start_date + "' BETWEEN book_strt_date AND book_fnsh_date)");
        bookOrWhere.add("('" + end_date + "' BETWEEN book_strt_date AND book_fnsh_date)");
        bookOrWhere.add("(book_strt_date BETWEEN '" + start_date + "' AND '" + end_date + "')");
        bookOrWhere.add("(book_fnsh_date BETWEEN '" + start_date + "' AND '" + end_date + "')");

        String bookOrWhereStr = String.join(" OR ", bookOrWhere);
        String bookQuery = "SELECT * FROM public.book WHERE " + bookOrWhereStr;

        ArrayList<Book> bookListByDate = this.bookDao.selectByQuery(bookQuery);
        ArrayList<Integer> busyCarId = new ArrayList<>();
        for(Book book : bookListByDate) {
            busyCarId.add(book.getCar_id());
        }

        searchedCarList.removeIf(car -> busyCarId.contains(car.getId()));

        return searchedCarList;
    }*/
}
