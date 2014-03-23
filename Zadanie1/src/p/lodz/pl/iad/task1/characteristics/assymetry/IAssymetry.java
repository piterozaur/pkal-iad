package p.lodz.pl.iad.task1.characteristics.assymetry;

import java.util.List;

public interface IAssymetry {

    double calculateAssymetryCoefficient(List<Double> data);

    double calculateSkewnessCoefficient(List<Double> data);
    
    double calculateThirdCentralMoment(List<Double> data);
    
    double calculateFourthCentralMoment(List<Double> data);

}
