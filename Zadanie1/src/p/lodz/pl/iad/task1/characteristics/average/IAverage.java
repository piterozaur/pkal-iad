package p.lodz.pl.iad.task1.characteristics.average;

import java.util.List;

public interface IAverage {

    double calculateArithmeticMean(List<Double> data);

    double calculateGeometicMean(List<Double> data);

    double calculateHarmonicMean(List<Double> data);
    
    double calculateMedian(List<Double> data);
    
    double calculateQuantileOneFourth(List<Double> data);
    
    double calculateQuantileThreeFourth(List<Double> data);

}
