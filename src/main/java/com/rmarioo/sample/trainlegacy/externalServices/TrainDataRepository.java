package com.rmarioo.sample.trainlegacy.externalServices;

import com.rmarioo.sample.trainlegacy.Train;

public interface TrainDataRepository {
  Train findTrain(String trainId);
}
