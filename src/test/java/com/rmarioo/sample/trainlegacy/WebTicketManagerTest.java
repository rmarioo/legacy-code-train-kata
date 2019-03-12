package com.rmarioo.sample.trainlegacy;

import static org.hamcrest.CoreMatchers.is;

import com.rmarioo.sample.trainlegacy.externalServices.BookingService;
import com.rmarioo.sample.trainlegacy.externalServices.TrainDataRepository;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class WebTicketManagerTest
{

  @Test
  public void when_requested_seats_exceeds_70_availability_return_empty_reservation() throws IOException {

    WebTicketManager webTicketManager = new WebTicketManager(new StubTrainDataRepository(),
        new StubBookingReserveService(true));
    Reservation reservation = webTicketManager.reserve("first", 6);

    Assert.assertThat(reservation,is(new Reservation("first","",Arrays.asList())));
  }

  @Test
  public void when_requested_seats_below_70_availability_return_a_reservation() throws IOException {

    WebTicketManager webTicketManager = new WebTicketManager(new StubTrainDataRepository(),
        new StubBookingReserveService(true));
    Reservation reservation = webTicketManager.reserve("first", 2);

    Assert.assertThat(reservation,is(new Reservation("first","ref1",
        Arrays.asList(
            new Seat("A",1),
        new Seat("A",2)))));
  }

  @Test
  public void when_reserve_fails_return_empty_reservation() throws IOException {

    WebTicketManager webTicketManager = new WebTicketManager(new StubTrainDataRepository(),
        new StubBookingReserveService(false));
    Reservation reservation = webTicketManager.reserve("first", 2);

    Assert.assertThat(reservation,is(new Reservation("first","",Arrays.asList())));

  }

  @Test
  public void sameReservasion_doesNotgo_across_different_coaches() throws IOException {

    WebTicketManager webTicketManager = new WebTicketManager(new StubTrainDataRepository(),
        new StubBookingReserveService(true));
    Reservation reservation = webTicketManager.reserve("first", 5);

    Assert.assertThat(reservation,is(new Reservation("first","",Arrays.asList())));
  }


  @Test
  public void sameReservasion_goes_across_different_coaches_2() throws IOException {

    List<Seat> seats = Arrays.asList(
        new Seat("A", 1, "ref0"),
        new Seat("A", 2, "ref0"),
        new Seat("A", 3, "ref0"),
        new Seat("A", 4),
        new Seat("B", 1),
        new Seat("B", 2),
        new Seat("B", 3),
        new Seat("B", 4));
    WebTicketManager webTicketManager = new WebTicketManager(new StubTrainDataRepository(seats),
        new StubBookingReserveService(true));
    Reservation reservation = webTicketManager.reserve("first", 2);

    Assert.assertThat(reservation,is(new Reservation("first","ref1",Arrays.asList(
        new Seat("B", 1, "ref1"),
        new Seat("B", 2, "ref1"))
    )));
  }


  private class StubTrainDataRepository implements TrainDataRepository {

    private  List<Seat> seats;

    public StubTrainDataRepository() {
    }

    public StubTrainDataRepository(List<Seat> seats) {
      this.seats = seats;
    }

    @Override
    public Train findTrain(String trainId) {
      return seats != null ? new Train("first",seats)
          :
          new Train("first", Arrays.asList(
          new Seat("A",1),
          new Seat("A",2),
          new Seat("A",3),
          new Seat("A",4),
          new Seat("B",1),
          new Seat("B",2),
          new Seat("B",3),
          new Seat("B",4)
      ));
    }
  }

  private class StubBookingReserveService implements BookingService {

    private final boolean reservationSuccessfull;

    public StubBookingReserveService(boolean reservationSuccessfull) {
      this.reservationSuccessfull = reservationSuccessfull;
    }

    @Override
    public String createbookingRef() {
      return "ref1";
    }

    @Override
    public Boolean reserve(String trainId, List<Seat> availableSeats, String bookingRef) {
      for (Seat availableSeat : availableSeats) {
        availableSeat.setBookingRef(bookingRef);
      }
      return reservationSuccessfull;
    }
  }
}