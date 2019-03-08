package com.rmarioo.sample.trainlegacy;

import static org.hamcrest.CoreMatchers.is;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class WebTicketManagerTest
{

  @Test
  public void when_requested_seats_exceeds_70_availability_return_empty_reservation() throws IOException {

    WebTicketManager webTicketManager = new TestableWebTicketManager();
    Reservation reservation = webTicketManager.reserve("first", 6);

    Assert.assertThat(reservation,is(new Reservation("first","",Arrays.asList())));
  }

  @Test
  public void when_requested_seats_below_70_availability_return_a_reservation() throws IOException {

    WebTicketManager webTicketManager = new TestableWebTicketManager();
    Reservation reservation = webTicketManager.reserve("first", 2);

    Assert.assertThat(reservation,is(new Reservation("first","ref1",
        Arrays.asList(
            new Seat("A",1),
        new Seat("A",2)))));
  }

  @Test
  public void when_reserve_fails_return_empty_reservation() throws IOException {

    WebTicketManager webTicketManager = new TestableWebTicketManager(false);
    Reservation reservation = webTicketManager.reserve("first", 2);

    Assert.assertThat(reservation,is(new Reservation("first","",Arrays.asList())));

  }

  @Test
  public void sameReservasion_goes_across_different_coaches() throws IOException {

    WebTicketManager webTicketManager = new TestableWebTicketManager();
    Reservation reservation = webTicketManager.reserve("first", 5);

    Assert.assertThat(reservation,is(new Reservation("first","ref1",
        Arrays.asList(
            new Seat("A",1),
            new Seat("A",2),
            new Seat("A",3),
            new Seat("A",4),
            new Seat("B",1)
        ))));
  }


  private class TestableWebTicketManager extends WebTicketManager
  {
    private final boolean reservationSuccessfull;

    public TestableWebTicketManager(boolean reservationSuccessfull) {

      this.reservationSuccessfull = reservationSuccessfull;
    }

    public TestableWebTicketManager() {
      this(true);
    }

    @Override
    protected String createBookingReference() {
      return "ref1";
    }

    @Override
    protected Boolean makeReservation(String trainId, List<Seat> availableSeats, String bookingRef) {
      return reservationSuccessfull;
    }

    @Override
    protected Train findTrain(String trainId) {


      return new Train("first", Arrays.asList(
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
}