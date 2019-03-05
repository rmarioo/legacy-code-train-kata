package com.rmarioo.sample.trainlegacy.externalServices;

import com.rmarioo.sample.trainlegacy.Seat;

import java.util.List;

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
  public String toString() {
    return "Reservation{" +
        "trainId='" + trainId + '\'' +
        ", bookingRef='" + bookingRef + '\'' +
        ", availableSeats=" + availableSeats +
        '}';
  }



}
