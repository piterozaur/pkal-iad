package p.lodz.pl.iad.task1.helpers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import p.lodz.pl.iad.task1.characteristics.dispertion.IStatisticalDispertion;
import p.lodz.pl.iad.task1.characteristics.dispertion.StatisticalDispertionImpl;

import com.panayotis.gnuplot.JavaPlot;

public class PlotHelper {
    private static final String ALL_DATA_CHARACTERISTICS = "Characteristics for whole data set";
    public static void drawHistogram(Map<String, List<Double>> data, String path){
        JavaPlot p = new JavaPlot();
//        StatisticalDispertionImpl statistics = new StatisticalDispertionImpl();
//        statistics.calculateRange(data)
        List<Double> data2 = new ArrayList<Double>();
        data2.add(1.2);
        data2.add(2.3);
        data2.add(1.2);
        Map<Double, Integer> amountOfOccurences = new HashMap<Double, Integer>();
        for(String elem : data.keySet()){
//            amountOfOccurences.get(key)
        }
        double[][] data3 = convertDataInto2DArray(data.get(ALL_DATA_CHARACTERISTICS));
        StringBuilder sb = new StringBuilder();
//        sb.append("\""+"/home/andrzej/Pulpit/a"+"\"u 2, \"\" u 3");//TUTAJ SĄ KOLEJNE KOLUMNY
//        p.addPlot(sb.toString());//
        sb.append("\""+data3+"\"u 2, \"\" u 3");//TUTAJ SĄ KOLEJNE KOLUMNY
        p.addPlot(data3);
        p.set("key","left");
        p.set("style", "style line 1 lc rgb 'black' lt 1 lw 1");
        p.set("grid","y");
        p.set("style","histogram rowstacked");
        p.set("boxwidth", "10");//GRUBOŚĆ LINII, MA MIEĆ SZEROKOŚĆ Z PARAMETRU
        p.set("style","fill pattern border -1");
        p.set("ytics", "10 nomirror");//SZEROKOSC W PIONIE
        p.set("yrange", "[0:15]");
        p.getAxis("y").setLabel("Number of Referrals");
        p.set("ytics", "1");//POMOCNICZE LINIE Y
//        
        p.setTitle("Histogram");
        p.set("style", "data histograms");
        p.plot();
    }
    
    private static double[][] convertDataInto2DArray(List<Double> data){
        IStatisticalDispertion staticalDispertionCalculations = new StatisticalDispertionImpl();
        double range = staticalDispertionCalculations.calculateRange(data);
        double max=data.get(0);
        for(double elem : data){
            if(elem>max){
                max=elem;
            }
        }
        double min = max-range;
        double histogramBoxWidth = range/20;
        double histogram[][] = new double[20][2];
        for(double elem : data){
            for(int i=0;i<20;i++){
                if((elem>histogramBoxWidth*i+min)&&(elem<histogramBoxWidth*(i+1)+min)){
                    System.out.println(i + " y " + elem + "\n");
                    histogram[i][1]++;
                    histogram[i][0]=i*histogramBoxWidth+min;
                }
            }
        }
        return histogram;
    }
    
}
