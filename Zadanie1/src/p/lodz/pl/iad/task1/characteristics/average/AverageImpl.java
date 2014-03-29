package p.lodz.pl.iad.task1.characteristics.average;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AverageImpl implements IAverage{

    //TODO: MODALNA (WARTOŚĆ WYSTĘPUJĄCA NAJCZĘŚCIEJ)
    
    @Override
    public double calculateArithmeticMean(List<Double> data) {
        double sum = 0.0;
        for (double elem : data) {
            sum += elem;
        }
        return sum / data.size();
    }

    @Override
    public double calculateGeometicMean(List<Double> data) {
        int rootPower = data.size();
        double rootNum = 1.0;
        for (double elem : data) {
            rootNum *= elem;
        }
        return Math.pow(rootNum, 1.0 / rootPower);
    }

    @Override
    public double calculateHarmonicMean(List<Double> data) {
        double sum = 0.0;
        for (Double elem : data) {
            sum += 1.0 / elem;
        }
        return data.size() / sum;
    }
    
    @Override
    public double calculateMedian(List<Double> data){
        return calculateQuantile(data, 0.5);
    }

    @Override
    public double calculateQuantileOneFourth(List<Double> data){
        return calculateQuantile(data, 0.25);
    }

    @Override
    public double calculateQuantileThreeFourth(List<Double> data){
        return calculateQuantile(data, 0.75);
    }
    
    /**
     * Formula for quantile: x=floor(n*p)+1
     */
    private double calculateQuantile(List<Double> data, double q) {
        Collections.sort(data, new DataSorter());
        int size = data.size();
        if(q==0.5 && size%2==0){
            return (data.get(size/2)+data.get(size/2)+1)/2;
        }
        double d = Math.floor(size*q);
        return data.get((int) (d+1));
    }
    
    private class DataSorter implements Comparator<Double> {
        @Override
        public int compare(Double o1, Double o2) {
            return o1.compareTo(o2);
        }
    }

}
