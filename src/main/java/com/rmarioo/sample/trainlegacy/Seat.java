package com.rmarioo.sample.trainlegacy;

import java.util.Objects;

public class Seat {
    private String coachName;
    private int seatNumber;
    private String bookingRef;

    public Seat(String coach, int seatNumber) {
        this(coach, seatNumber, "");
    }

    public Seat(String coachName, int seatNumber, String bookingRef) {
        this.coachName = coachName;
        this.seatNumber = seatNumber;
        this.bookingRef = bookingRef;
    }

    public String getCoachName() {
        return coachName;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getBookingRef() {
        return bookingRef;
    }

    public void setBookingRef(String bookingRef) {
        this.bookingRef = bookingRef;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seat seat = (Seat) o;
        return seatNumber == seat.seatNumber &&
            Objects.equals(coachName, seat.coachName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coachName, seatNumber);
    }

    @Override
    public String toString() {
        return coachName + seatNumber + (hasReservation() ? bookingRef : "");
    }

    boolean hasReservation() {
        return getBookingRef() != null && !getBookingRef().isEmpty();
    }
}
