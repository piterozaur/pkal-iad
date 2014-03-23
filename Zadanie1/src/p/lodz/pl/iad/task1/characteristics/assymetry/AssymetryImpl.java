package p.lodz.pl.iad.task1.characteristics.assymetry;

import java.util.List;

import p.lodz.pl.iad.task1.characteristics.average.AverageImpl;
import p.lodz.pl.iad.task1.characteristics.average.IAverage;
import p.lodz.pl.iad.task1.characteristics.dispertion.IStatisticalDispertion;
import p.lodz.pl.iad.task1.characteristics.dispertion.StatisticalDispertionImpl;

public class AssymetryImpl implements IAssymetry{
    
    @Override
    public double calculateAssymetryCoefficient(List<Double> data){
       IStatisticalDispertion statisticalDispertionCalculations = new StatisticalDispertionImpl();
       double thirdCentralMoment = calculateThirdCentralMoment(data);
       double standardDeviation = statisticalDispertionCalculations.calculateStandardDeviation(data);
       
       return thirdCentralMoment/(Math.pow(standardDeviation, 3)); 
    }
    
    @Override
    public double calculateSkewnessCoefficient(List<Double> data){
        IStatisticalDispertion statisticalDispertionCalculations = new StatisticalDispertionImpl();
        IAverage averageCalculations = new AverageImpl();
        double arithmeticMean=averageCalculations.calculateArithmeticMean(data);
        double median=averageCalculations.calculateMedian(data);
        double standardDeviaation=statisticalDispertionCalculations.calculateStandardDeviation(data);
        
        return 3*(arithmeticMean-median)/standardDeviaation;
    }

    @Override
    public double calculateThirdCentralMoment(List<Double> data) {
        IAverage averageCalculations = new AverageImpl();
        int size = data.size();
        double sum=0;
        double arithmeticMean = averageCalculations.calculateArithmeticMean(data);
        for(double elem : data){
            sum+=Math.pow((elem-arithmeticMean), 3);
        }

        return 1/size*sum;
    }

    @Override
    public double calculateFourthCentralMoment(List<Double> data) {
        IAverage averageStatistics = new AverageImpl();
        int size = data.size();
        double sum=0;
        double arithmeticMean = averageStatistics.calculateArithmeticMean(data);
        for(double elem : data){
            sum+=Math.pow((elem-arithmeticMean), 4);
        }

        return 1/size*sum;
    }
    
    

}
