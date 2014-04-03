package p.lodz.pl.iad.task1.characteristics.hypothesisTesting;

import java.util.List;
import java.util.Map;

import p.lodz.pl.iad.task1.characteristics.average.AverageImpl;
import p.lodz.pl.iad.task1.characteristics.average.IAverage;

public class TestingHypothesisImpl implements ITestingHypothesis {

    @Override
    public void testHypothesis(Map<Integer, Map<String, List<Double>>> dataMaps){
        IAverage averageCalculations = new AverageImpl();
        
        try{
            double m1 = averageCalculations.calculateArithmeticMean(dataMaps.get(0).get("1.000000"));
            double m2 = averageCalculations.calculateArithmeticMean(dataMaps.get(0).get("2.000000"));
            System.out.println("Ok");
        } catch(Exception e){
            System.err.println("Error!");
        }
        
    }
    
}
