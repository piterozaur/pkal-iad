package p.lodz.pl.iad.task1.characteristics.concentration;

import java.util.List;

import p.lodz.pl.iad.task1.characteristics.assymetry.AssymetryImpl;
import p.lodz.pl.iad.task1.characteristics.assymetry.IAssymetry;
import p.lodz.pl.iad.task1.characteristics.dispertion.IStatisticalDispertion;
import p.lodz.pl.iad.task1.characteristics.dispertion.StatisticalDispertionImpl;

public class ConcentrationDistributionImpl implements IConcentrationDistribution {

    @Override
    public double calculateCurtosis(List<Double> data) {
        IAssymetry assymetryCalculations = new AssymetryImpl();
        IStatisticalDispertion statisticalDispertionCalculations = new StatisticalDispertionImpl();
        double fourthCentralMoment = assymetryCalculations.calculateFourthCentralMoment(data);
        double standardDeviation = statisticalDispertionCalculations.calculateStandardDeviation(data);
     
        return (fourthCentralMoment-standardDeviation)/3;
    }

}
