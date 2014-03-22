package p.lodz.pl.iad.task1.characteristics.dispertion;

import java.util.List;

public interface IStatisticalDispertion {

    Double calculateRange(List<Double> data);

    Double calculateStandardDeviation(List<Double> data);

    Double calculateVariance(List<Double> data);

    Double calculateVariantionCoefficient(List<Double> data);

}
