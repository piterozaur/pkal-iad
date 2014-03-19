package p.lodz.pl.iad.task1.characteristics.assymetry;

import java.util.List;

import p.lodz.pl.iad.task1.characteristics.dispertion.StatisticalDispertionImpl;

public class AssymetryImpl implements IAssymetry{
    
    @Override
    public Double calculateAssymetryCoefficient(List<Double> data){
       StatisticalDispertionImpl statisticCalculations = new StatisticalDispertionImpl();
       Double standardDeviation = statisticCalculations.calculateStandardDeviation(data);
       return null; 
    }
    
    @Override
    public Double calculateSkewnessCoefficient(List<Double> data){
        //TODO
        return null;
    }
    
    

}
