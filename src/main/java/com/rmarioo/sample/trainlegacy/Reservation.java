package com.rmarioo.sample.trainlegacy;

import java.util.List;
import java.util.Objects;

public class Reservation {
  public String trainId;
  public String bookingRef;
  public List<Seat> availableSeats;

  public Reservation() {
  }

  public Reservation(String trainId, String bookingRef, List<Seat> availableSeats) {
    this.trainId = trainId;
    this.bookingRef = bookingRef;
    this.availableSeats = availableSeats;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Reservation that = (Reservation) o;
    return Objects.equals(trainId, that.trainId) &&
        Objects.equals(bookingRef, that.bookingRef) &&
        Objects.equals(availableSeats, that.availableSeats);
  }

  @Override
  public int hashCode() {
    return Objects.hash(trainId, bookingRef, availableSeats);
  }

  @Override
  public String toString() {
    return "Reservation{" +
        "trainId='" + trainId + '\'' +
        ", bookingRef='" + bookingRef + '\'' +
        ", availableSeats=" + availableSeats +
        '}';
  }



}
