package p.lodz.pl.iad.task1.characteristics.dispertion;

import java.util.List;

public class StatisticalDispertionImpl implements IStatisticalDispertion{

    @Override
    public Double calculateRange(List<Double> data){
        Double maxValue=data.get(0);
        Double minValue=data.get(0);
        for(Double elem : data){
            if(maxValue < elem){
                maxValue = elem;
            }
            if(minValue > elem){
                minValue = elem;
            }
        }
        Double range = maxValue - minValue;
        return range;
    }
    
    @Override
    public Double calculateStandardDeviation(List<Double> data){
        Double variance = calculateVariance(data);
        return Math.sqrt(variance);
    }
    
    @Override
    public Double calculateVariance(List<Double> data){
        Double mean = calculateArithmeticMean(data);
        Double temp = 0.0;
        for(Double elem : data){
            temp += (mean-elem)*(mean-elem);
        }
        return temp/data.size();
    }
    
    @Override
    public Double calculateCoefficientOfVariantion(List<Double> data){
        Double standardDeviation = calculateStandardDeviation(data);
        Double mean = calculateArithmeticMean(data);
        return standardDeviation/mean;
    }
    
    private Double calculateArithmeticMean(List<Double> data){
        Double sum = 0.0;
        for (Double elem : data) {
            sum += elem;
        }
        return sum / data.size();
    }
}
