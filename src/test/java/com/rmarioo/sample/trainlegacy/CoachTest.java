package com.rmarioo.sample.trainlegacy;

import static org.hamcrest.CoreMatchers.is;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

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
}