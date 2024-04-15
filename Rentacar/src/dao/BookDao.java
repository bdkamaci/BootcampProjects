package dao;

import core.Db;
import entity.Book;
import entity.Model;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class BookDao {
    private Connection connection;
    private final CarDao carDao;

    public BookDao() {
        this.connection = Db.getInstance();
        this.carDao = new CarDao();
    }

    public Book getById(int id)  {
        Book obj = null;
        String query = "SELECT * FROM public.book WHERE book_id = ?";
        try {
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if(rs.next()) {
                obj = this.match(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public ArrayList<Book> findAll() {
        return this.selectByQuery("SELECT * FROM public.book ORDER BY book_id ASC");
    }

    public ArrayList<Book> selectByQuery(String query) {
        ArrayList<Book> bookList = new ArrayList<>();
        try {
            ResultSet rs = this.connection.createStatement().executeQuery(query);
            while (rs.next()) {
                bookList.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookList;
    }

    public Book match(ResultSet resultSet) throws SQLException {
        Book book = new Book();
        book.setId(resultSet.getInt("car_id"));
        book.setCar_id(resultSet.getInt("book_car_id"));
        book.setName(resultSet.getString("book_name"));
        book.setMpno(resultSet.getString("book_mpno"));
        book.setMail(resultSet.getString("book_mail"));
        book.setStrt_date(LocalDate.parse(resultSet.getString("book_strt_date")));
        book.setFnsh_date(LocalDate.parse(resultSet.getString("book_fnsh_date")));
        book.setPrc(resultSet.getInt("book_prc"));
        book.setNote(resultSet.getString("book_note"));
        book.setbCase(resultSet.getString("book_case"));
        return book;
    }

    public boolean update(Book book) {
        String query = "UPDATE public.book SET " +
                "book_id = ?" +
                "book_car_id = ? ," +
                "book_name = ? ," +
                "book_mpno = ? ," +
                "book_mail = ? ," +
                "book_strt_date = ? ," +
                "book_fnsh_date = ? ," +
                "book_prc = ? ," +
                "book_note = ? ," +
                "book_case = ? ," +
                "WHERE book_idno = ?";
        try {
            PreparedStatement pr = connection.prepareStatement(query);
            pr.setInt(1, book.getId());
            pr.setInt(2, book.getCar_id());
            pr.setString(3, book.getName());
            pr.setString(4, book.getMpno());
            pr.setString(5, book.getMail());
            pr.setDate(6, Date.valueOf(book.getStrt_date()));
            pr.setDate(7, Date.valueOf(book.getFnsh_date()));
            pr.setInt(8, book.getPrc());
            pr.setString(9, book.getNote());
            pr.setString(10, book.getbCase());
            pr.setString(11, book.getIdno());
            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    public boolean save(Book book) {
        String query = "INSERT INTO public.car " +
                "(" +
                "book_car_id," +
                "book_name," +
                "book_idno" +
                "book_mpno," +
                "book_mail," +
                "book_strt_date," +
                "book_fnsh_date," +
                "book_prc," +
                "book_note," +
                "book_case" +
                ")" +
                " VALUES (?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pr = connection.prepareStatement(query);
            pr.setInt(1, book.getCar_id());
            pr.setString(2, book.getName());
            pr.setString(3, book.getIdno());
            pr.setString(4, book.getMpno());
            pr.setString(5, book.getMail());
            pr.setDate(6, Date.valueOf(book.getStrt_date()));
            pr.setDate(7, Date.valueOf(book.getFnsh_date()));
            pr.setInt(8, book.getPrc());
            pr.setString(9, book.getNote());
            pr.setString(10, book.getbCase());

            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    public boolean delete(int book_id) {
        String query = "DELETE FROM public.book WHERE book_id = ?";
        try {
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setInt(1, book_id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

}
