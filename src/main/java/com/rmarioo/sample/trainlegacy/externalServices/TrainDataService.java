package com.rmarioo.sample.trainlegacy.externalServices;

import static java.util.Arrays.asList;

import com.rmarioo.sample.trainlegacy.Seat;
import com.rmarioo.sample.trainlegacy.Train;
import com.rmarioo.sample.trainlegacy.Trains;

public class TrainDataService {

  public static Trains trains = initializeTrains();

  private static Trains initializeTrains() {

    Trains trains = new Trains();
    trains.addTrain("first",
        new Train(
        asList(
            new Seat("A",1),new Seat("A",2),new Seat("A",3),new Seat("A", 4),
            new Seat("B",1),new Seat("B",2),new Seat("B",3),new Seat("B", 4)
            )));
    return trains;
  }

  public Train findTrain(String trainId)
    {
      return trains.find(trainId);

    }

}
