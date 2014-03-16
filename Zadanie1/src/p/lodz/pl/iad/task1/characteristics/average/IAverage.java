package p.lodz.pl.iad.task1.characteristics.average;

import java.util.List;

public interface IAverage {

    Double calculateArithmeticMean(List<Double> data);

    Double calculateGeometicMean(List<Double> data);

    Double calculateHarmonicMean(List<Double> data);
    
    List<Double> calculateMode(List<Double> data);
    
    Double calculateQuantile(List<Double> data, Double q);

}
