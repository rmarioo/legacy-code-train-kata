package com.rmarioo.sample.trainlegacy;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

public class WebTicketManagerTest
{

  @Test
  public void when_requested_seats_exceeds_70_availability_return_empty_registration() throws IOException {

    WebTicketManager webTicketManager = new TestableWebTicketManager();
    Reservation reservation = webTicketManager.reserve("first", 6);

    Assert.assertThat(reservation,is(new Reservation("first","",Arrays.asList())));
  }

  @Test
  public void bbb() throws IOException {

    WebTicketManager webTicketManager = new TestableWebTicketManager();
    Reservation reservation = webTicketManager.reserve("first", 2);

    Assert.assertThat(reservation,is(new Reservation("first","",Arrays.asList())));
  }

  private class TestableWebTicketManager extends WebTicketManager {
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