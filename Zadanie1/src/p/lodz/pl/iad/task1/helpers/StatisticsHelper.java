package p.lodz.pl.iad.task1.helpers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import p.lodz.pl.iad.task1.characteristics.assymetry.AssymetryImpl;
import p.lodz.pl.iad.task1.characteristics.assymetry.IAssymetry;
import p.lodz.pl.iad.task1.characteristics.average.AverageImpl;
import p.lodz.pl.iad.task1.characteristics.average.IAverage;
import p.lodz.pl.iad.task1.characteristics.dispertion.IStatisticalDispertion;
import p.lodz.pl.iad.task1.characteristics.dispertion.StatisticalDispertionImpl;

public class StatisticsHelper {

    public Map<Integer, Map<String, Map<String, Double>>> getAllStatiscticsFromDataSets(Map<Integer, Map<String, List<Double>>> dataMaps){
        Map<Integer, Map<String, Map<String, Double>>> statistics = new HashMap<Integer, Map<String, Map<String, Double>>>();
        
        for(Integer key : dataMaps.keySet()){
            Map<String, Map<String, Double>> statisticsFromDataSet = new HashMap<String, Map<String, Double>>();
            for(String nominalAttribute : dataMaps.get(key).keySet()){
                statisticsFromDataSet.put(nominalAttribute, getAllStatiscticsFromDataSet(dataMaps.get(key).get(nominalAttribute)));
            }
            statistics.put(key, statisticsFromDataSet);
        }
        
        return statistics;
    }
    
    public Map<String, Double> getAllStatiscticsFromDataSet(List<Double> data) {
        Map<String, Double> statistics = new HashMap<String, Double>();
        
        statistics.putAll(getAverageStatistics(data));
        statistics.putAll(getAssymetryStatistics(data));
        statistics.putAll(getStatisticalDispertionStatistics(data));
        
        return statistics;
    }

    public Map<String, Double> getAverageStatistics(List<Double> data){
        IAverage averageStatistics = new AverageImpl();
        Map<String, Double> statistics = new HashMap<String, Double>();

        putStatistics(statistics, Constants.ARITHMETIC_MEAN, averageStatistics.calculateArithmeticMean(data));
        putStatistics(statistics, Constants.GEOMETRIC_MEAN, averageStatistics.calculateGeometicMean(data));
        putStatistics(statistics, Constants.HARMONIC_MEAN, averageStatistics.calculateHarmonicMean(data));
        putStatistics(statistics, Constants.QUANTILE + " 1/4", averageStatistics.calculateQuantileOneFourth(data));
        putStatistics(statistics, Constants.MEDIAN, averageStatistics.calculateMedian(data));
        putStatistics(statistics, Constants.QUANTILE + " 3/4", averageStatistics.calculateQuantileThreeFourth(data));
    
        return statistics;
    }
    
    public Map<String, Double> getAssymetryStatistics(List<Double> data){
        IAssymetry assymetryStatistics = new AssymetryImpl();
        Map<String, Double> statistics = new HashMap<String, Double>();

        putStatistics(statistics, Constants.THIRD_CENTRAL_MOMENT, assymetryStatistics.calculateThirdCentralMoment(data));
        putStatistics(statistics, Constants.FOURTH_CENTRAL_MOMENT, assymetryStatistics.calculateFourthCentralMoment(data));
        putStatistics(statistics, Constants.ASSYMETRY_COEFFICIENT, assymetryStatistics.calculateAssymetryCoefficient(data));
        putStatistics(statistics, Constants.SKEWNESS_COEFFICIENT, assymetryStatistics.calculateSkewnessCoefficient(data));
        
        return statistics;
    }
    
    public Map<String, Double> getStatisticalDispertionStatistics(List<Double> data){
        IStatisticalDispertion statisticalDispertionStatistics = new StatisticalDispertionImpl();
        Map<String, Double> statistics = new HashMap<String, Double>();

        putStatistics(statistics, Constants.RANGE, statisticalDispertionStatistics.calculateRange(data));
        putStatistics(statistics, Constants.STANDARD_DEVIATION, statisticalDispertionStatistics.calculateStandardDeviation(data));
        putStatistics(statistics, Constants.VARIANCE, statisticalDispertionStatistics.calculateVariance(data));
        putStatistics(statistics, Constants.VARIATION_COEFFICIENT, statisticalDispertionStatistics.calculateVariantionCoefficient(data));
        
        return statistics;
    }

    private Map<String, Double> putStatistics(Map<String, Double> map, String key, Double value) {
        map.put(key, value);
        return map;
    }
}
