package com.rmarioo.sample.trainlegacy;

import static java.util.Arrays.asList;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TrainDataService {

  public static Trains trains = initializeTrains();

  private static Trains initializeTrains() {

    Trains trains = new Trains();
    TrainJson trainJson = new TrainJson();
    trainJson.seats =
        asList(newSeatJson(1, "A"),newSeatJson(2, "A"),newSeatJson(3, "A"),newSeatJson(4, "A"),
               newSeatJson(2, "B"),newSeatJson(2, "B"),newSeatJson(3, "B"),newSeatJson(4, "B"));

    trains.addTrain("first", trainJson);
    return trains;
  }

  private static SeatJson newSeatJson(int seatNumber, String coach) {
    SeatJson seatJson = new SeatJson();
    seatJson.booking_reference="";
    seatJson.coach= coach;
    seatJson.seat_number=""+ seatNumber;
    return seatJson;
  }

  public String invoke(String train)
    {

      TrainJson trainJson = trains.find(train);

      System.out.println("*********");
      System.out.println("*********");
      System.out.println("*********");
      System.out.println("*********");
      System.out.println("*********");
      System.out.println("*********");

      String s = toString(trainJson);


      System.out.println(s);


      System.out.println("*********");
      System.out.println("*********");
      System.out.println("*********");
      System.out.println("*********");
      System.out.println("*********");
      System.out.println("*********");
      return s;
/*
      return "{\"seats\": {\"1A\": {\"booking_reference\": \"\", \"seat_number\": \"1\", " + "\"coach\": \"A\"}," +
                          " \"2A\": {\"booking_reference\": \"\", \"seat_number\": \"2\", " + "\"coach\": \"A\"}}}";
*/


    }

  private String toString(TrainJson trainJson) {
    ObjectMapper objectMapper = new ObjectMapper();
    String s;
    try {
      s = objectMapper.writeValueAsString(trainJson);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
    return s;
  }

}
