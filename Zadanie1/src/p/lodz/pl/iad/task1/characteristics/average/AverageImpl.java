package p.lodz.pl.iad.task1.characteristics.average;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public List<Double> calculateMode(List<Double> data){
        Map<Double, Integer> occurences = new HashMap<Double, Integer>();
        for(Double elem : data){
            if(!occurences.containsKey(elem)){
                occurences.put(elem, 1);
            } else {
                Integer curAmount = occurences.get(elem);
                occurences.put(elem, curAmount+1);
            }
        }
        
        Integer maxOccurences = 0;
        for(Double key : occurences.keySet()){
            if(occurences.get(key)>maxOccurences){
                maxOccurences = occurences.get(key);
            }
        }
        
        List<Double> modes = new ArrayList<Double>();
        for(Double key : occurences.keySet()){
            if(occurences.get(key).equals(maxOccurences)){
                modes.add(key);
            }
        }
        
        return modes;
    }

    @Override
    public Double calculateQuantile(List<Double> data, Double q) {
        Collections.sort(data, new DataSorter());
        return data.get((int) (data.size()*q));//TODO poprawić
    }
    
    private class DataSorter implements Comparator<Double> {
        @Override
        public int compare(Double o1, Double o2) {
            return o1.compareTo(o2);
        }
    }

}
