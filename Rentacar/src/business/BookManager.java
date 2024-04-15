package business;

import core.Helper;
import dao.BookDao;
import entity.Book;
import entity.Car;
import entity.Model;

import java.util.ArrayList;

public class BookManager {
    private final BookDao bookDao;

    public BookManager() {
        this.bookDao = new BookDao();
    }

    public Book getById(int id) {
        return this.bookDao.getById(id);
    }

    public ArrayList<Book> findAll() {
        return this.bookDao.findAll();
    }

    public ArrayList<Book> searchForTable(int carId) {
        String select = "SELECT * FROM public.book";
        ArrayList<String> whereList = new ArrayList<>();

        if (carId != 0) {
            whereList.add("book_car_id = " + carId);
        }

        String whereStr = String.join(" AND ", whereList);
        String query = select;
        if (whereStr.length() > 0) {
            query += " WHERE " + whereStr;
        }
        return this.bookDao.selectByQuery(query);
    }

    public ArrayList<Object[]> getForTable(int size, ArrayList<Book> bookings) {
        ArrayList<Object[]> bookingList = new ArrayList<>();
        for(Book obj : bookings) {
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = obj.getId();
            rowObject[i++] = obj.getCar_id();
            rowObject[i++] = obj.getName();
            rowObject[i++] = obj.getIdno();
            rowObject[i++] = obj.getMpno();
            rowObject[i++] = obj.getMail();
            rowObject[i++] = obj.getStrt_date();
            rowObject[i++] = obj.getFnsh_date();
            rowObject[i++] = obj.getPrc();
            rowObject[i++] = obj.getNote();
            rowObject[i++] = obj.getbCase();
            bookingList.add(rowObject);
        }
        return bookingList;
    }

    public boolean save(Book book) {
        if(this.getById(book.getId()) != null) {
            Helper.showMessage("error");
            return false;
        }
        return this.bookDao.save(book);
    }

    public boolean update(Book book) {
        if(this.getById(book.getId()) != null) {
            Helper.showMessage(book.getId() + " ID - car not found!");
            return false;
        }
        return this.bookDao.update(book);
    }

    public boolean delete(int id) {
        if(this.getById(id) == null) {
            Helper.showMessage(id + " ID - car not found!");
            return false;
        }
        return this.bookDao.delete(id);
    }
}
