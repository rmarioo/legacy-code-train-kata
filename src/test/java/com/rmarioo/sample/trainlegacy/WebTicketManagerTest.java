package com.rmarioo.sample.trainlegacy;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class WebTicketManagerTest
{

  @Test
  public void aaa() throws IOException {

    WebTicketManager webTicketManager = new WebTicketManager();
    Reservation reservation = webTicketManager.reserve("first", 6);

    Assert.assertThat(reservation,is(new Reservation()));
  }

}