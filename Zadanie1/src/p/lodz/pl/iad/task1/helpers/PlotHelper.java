package p.lodz.pl.iad.task1.helpers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.panayotis.gnuplot.JavaPlot;

public class PlotHelper {

    public static void drawHistogram(List<Double> data323, Double precision){
        JavaPlot p = new JavaPlot();
//        StatisticalDispertionImpl statistics = new StatisticalDispertionImpl();
//        statistics.calculateRange(data)
        List<Double> data = new ArrayList<Double>();
        data.add(1.2);
        data.add(2.3);
        data.add(1.2);
        Map<Double, Integer> amountOfOccurences = new HashMap<Double, Integer>();
        for(Double elem : data){
//            amountOfOccurences.get(key)
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("\""+"/home/andrzej/Pulpit/a"+"\"u 2, \"\" u 3");//TUTAJ SĄ KOLEJNE KOLUMNY
        p.addPlot(sb.toString());//
        p.set("key","left");
        p.set("style", "style line 1 lc rgb 'black' lt 1 lw 1");
        p.set("grid","y");
        p.set("style","histogram rowstacked");
        p.set("boxwidth", "10");//GRUBOŚĆ LINII, MA MIEĆ SZEROKOŚĆ Z PARAMETRU
        p.set("style","fill pattern border -1");
        p.set("ytics", "10 nomirror");//SZEROKOSC W PIONIE
        p.set("yrange", "[-5:5]");
        p.getAxis("y").setLabel("Number of Referrals");
        p.set("ytics", "10");
        
        p.setTitle("Histogram");
        p.set("style", "data histograms");
        p.plot();
    }
}
