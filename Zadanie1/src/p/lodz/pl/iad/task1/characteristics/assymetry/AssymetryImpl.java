package p.lodz.pl.iad.task1.characteristics.assymetry;

import java.util.List;

public class AssymetryImpl implements IAssymetry{
    
    @Override
    public Double calculateAssymetryCoefficient(List<Double> data){
        //TODO
       Double standardDeviation = calculateStandardDeviation(data);
       return null; 
    }
    
    @Override
    public Double calculateSkewnessCoefficient(List<Double> data){
        //TODO
        return null;
    }
    
    private Double calculateStandardDeviation(List<Double> data){
        Double variance = calculateVariance(data);
        return Math.sqrt(variance);
    }
    
    private Double calculateVariance(List<Double> data){
        Double mean = calculateArithmeticMean(data);
        Double temp = 0.0;
        for(Double elem : data){
            temp += (mean-elem)*(mean-elem);
        }
        return temp/data.size();
    }
    
    private Double calculateArithmeticMean(List<Double> data){
        Double sum = 0.0;
        for (Double elem : data) {
            sum += elem;
        }
        return sum / data.size();
    }

}
