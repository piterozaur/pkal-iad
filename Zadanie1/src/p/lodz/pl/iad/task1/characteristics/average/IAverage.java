package p.lodz.pl.iad.task1.characteristics.average;

import java.util.List;

public interface IAverage {

    Double calculateArithmeticMean(List<Double> data);

    Double calculateGeometicMean(List<Double> data);

    Double calculateHarmonicMean(List<Double> data);
    
    Double calculateMedian(List<Double> data);
    
    Double calculateQuantileOneFourth(List<Double> data);
    
    Double calculateQuantileThreeFourth(List<Double> data);

}
