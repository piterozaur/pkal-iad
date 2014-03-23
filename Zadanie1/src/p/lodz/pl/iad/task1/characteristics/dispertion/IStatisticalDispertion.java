package p.lodz.pl.iad.task1.characteristics.dispertion;

import java.util.List;

public interface IStatisticalDispertion {

    //Rozstęp
    double calculateRange(List<Double> data);

    double calculateStandardDeviation(List<Double> data);

    double calculateVariance(List<Double> data);

    double calculateVariantionCoefficient(List<Double> data);

}
