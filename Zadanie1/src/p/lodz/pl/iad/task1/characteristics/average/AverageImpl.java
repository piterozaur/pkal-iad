package p.lodz.pl.iad.task1.characteristics.average;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AverageImpl implements IAverage{

    @Override
    public Double calculateArithmeticMean(List<Double> data) {
        Double sum = 0.0;
        for (Double elem : data) {
            sum += elem;
        }
        return sum / data.size();
    }

    @Override
    public Double calculateGeometicMean(List<Double> data) {
        Integer rootPower = data.size();
        Double rootNum = 1.0;
        for (Double elem : data) {
            rootNum *= elem;
        }
        return Math.pow(rootNum, 1 / rootPower);
    }

    @Override
    public Double calculateHarmonicMean(List<Double> data) {
        Double sum = 0.0;
        for (Double elem : data) {
            sum += 1.0 / elem;
        }
        return data.size() / sum;
    }
    
    @Override
    public Double calculateMedian(List<Double> data){
        return calculateQuantile(data, 0.5);
    }

    @Override
    public Double calculateQuantileOneFourth(List<Double> data){
        return calculateQuantile(data, 0.25);
    }

    @Override
    public Double calculateQuantileThreeFourth(List<Double> data){
        return calculateQuantile(data, 0.75);
    }
    
    /**
     * Formula for quantile: x=floor(n*p)+1
     */
    private Double calculateQuantile(List<Double> data, Double q) {
        Collections.sort(data, new DataSorter());
        int size = data.size();
        if(q==0.5 && size%2==0){
            return (data.get(size/2)+data.get(size/2)+1)/2;
        }
        Double d = Math.floor(size*q);
        return data.get(d.intValue()+1);
    }
    
    private class DataSorter implements Comparator<Double> {
        @Override
        public int compare(Double o1, Double o2) {
            return o1.compareTo(o2);
        }
    }

}
