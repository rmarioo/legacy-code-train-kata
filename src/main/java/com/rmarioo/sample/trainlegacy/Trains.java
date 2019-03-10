package com.rmarioo.sample.trainlegacy;

import static java.util.Arrays.asList;

import java.util.HashMap;
import java.util.Map;

public class Trains
{
  private static Trains instance;

  public static Trains getInstance() {
    if (instance == null) {
      instance = initializeTrains();
    }
    return instance;
  }

  private static Trains initializeTrains() {

    Trains trains = new Trains();
    String name = "first";
    trains.addTrain(name,
        new Train(name,
            asList(
                new Seat("A",1),new Seat("A",2),new Seat("A",3),new Seat("A", 4),
                new Seat("B",1),new Seat("B",2),new Seat("B",3),new Seat("B", 4)
            )));
    return trains;
  }

  private Map<String, Train> trainsMap = new HashMap<>();

  public Train find(String trainId) {
     return trainsMap.get(trainId);
  }

  public void addTrain(String trainId, Train train) {
    trainsMap.put(trainId,train);
  }

  public void deleteAllTrainReservations() {
    trainsMap.values().stream().forEach(train -> resetReservations(train));
  }

  private void resetReservations(Train train) {

    train.allSeats().stream().forEach(seat -> seat.setBookingRef(""));
  }

  public Map<String, Train> getTrainsMap() {
    return trainsMap;
  }
}
