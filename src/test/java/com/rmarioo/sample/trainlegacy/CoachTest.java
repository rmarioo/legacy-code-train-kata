package com.rmarioo.sample.trainlegacy;

import static org.hamcrest.CoreMatchers.is;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class CoachTest {


  @Test
  public void enoughSeats() {

    Coach coach = new Coach("A", Arrays.asList(
        new Seat("A", 1, "ref1"),
        new Seat("A", 2, "ref1"),
        new Seat("A", 3)
    ));


    boolean enoughSearsFor = coach.hasEnoughSeatsFor(1);
    Assert.assertThat(enoughSearsFor,is(true));
  }

  @Test
  public void moreThanRequested() {

    Coach coach = new Coach("A", Arrays.asList(
        new Seat("A", 1, "ref1"),
        new Seat("A", 2, "ref1"),
        new Seat("A", 3),
        new Seat("A", 4)
    ));


    boolean enoughSearsFor = coach.hasEnoughSeatsFor(1);
    Assert.assertThat(enoughSearsFor,is(true));
  }

  @Test
  public void lessThanRequested() {

    Coach coach = new Coach("A", Arrays.asList(
        new Seat("A", 1, "ref1"),
        new Seat("A", 2, "ref1"),
        new Seat("A", 3),
        new Seat("A", 4)
    ));


    boolean enoughSearsFor = coach.hasEnoughSeatsFor(3);
    Assert.assertThat(enoughSearsFor,is(false));
  }

  @Test
  public void availableSeatsForRequest() {
    Coach coach = new Coach("A", Arrays.asList(
        new Seat("A", 1, "ref1"),
        new Seat("A", 2, "ref1"),
        new Seat("A", 3),
        new Seat("A", 4),
        new Seat("A", 5)
    ));


    List<Seat> seats = coach.firstAvailableSeats(2);
    Assert.assertThat(seats,is(Arrays.asList(
        new Seat("A", 3),
        new Seat("A", 4)
    )));

  }


  @Test
  public void not_enough_seats_available() {
    Coach coach = new Coach("A", Arrays.asList(
        new Seat("A", 1, "ref1"),
        new Seat("A", 2, "ref1"),
        new Seat("A", 3),
        new Seat("A", 4),
        new Seat("A", 5)
    ));


    List<Seat> seats = coach.firstAvailableSeats(4);
    Assert.assertThat(seats,is(Arrays.asList()));

  }
}