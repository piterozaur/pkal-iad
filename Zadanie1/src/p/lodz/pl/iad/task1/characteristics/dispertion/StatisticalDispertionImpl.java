package p.lodz.pl.iad.task1.characteristics.dispertion;

import java.util.List;

public class StatisticalDispertionImpl implements IStatisticalDispertion{

    @Override
    public double calculateRange(List<Double> data){
        double maxValue=data.get(0);
        double minValue=data.get(0);
        for(double elem : data){
            if(maxValue < elem){
                maxValue = elem;
            }
            if(minValue > elem){
                minValue = elem;
            }
        }
        double range = maxValue - minValue;
        return range;
    }
    
    @Override
    public double calculateStandardDeviation(List<Double> data){
        double variance = calculateVariance(data);
        return Math.sqrt(variance);
    }
    
    @Override
    public double calculateVariance(List<Double> data){
        double mean = calculateArithmeticMean(data);
        double temp = 0.0;
        for(double elem : data){
            temp += (mean-elem)*(mean-elem);
        }
        return temp/data.size();
    }
    
    @Override
    public double calculateVariantionCoefficient(List<Double> data){
        double standardDeviation = calculateStandardDeviation(data);
        double mean = calculateArithmeticMean(data);
        return standardDeviation/mean;
    }
    
    private double calculateArithmeticMean(List<Double> data){
        double sum = 0.0;
        for (double elem : data) {
            sum += elem;
        }
        return sum / data.size();
    }
}
