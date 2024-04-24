package entity;

import java.time.LocalDate;

public class Season {
    private int id;
    private int hotel_id;
    private LocalDate start_date;
    private LocalDate end_date;

    public Season(int hotel_id, LocalDate start_date, LocalDate end_date) {
        this.hotel_id = hotel_id;
        this.start_date = start_date;
        this.end_date = end_date;
    }

    public Season() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    public LocalDate getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDate start_date) {
        this.start_date = start_date;
    }

    public LocalDate getEnd_date() {
        return end_date;
    }

    public void setEnd_date(LocalDate end_date) {
        this.end_date = end_date;
    }
}
