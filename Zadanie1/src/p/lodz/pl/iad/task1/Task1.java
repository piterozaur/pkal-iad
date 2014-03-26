package p.lodz.pl.iad.task1;

import java.awt.EventQueue;
import java.util.List;
import java.util.Map;

import p.lodz.pl.iad.task1.gui.GraphicalUserInterface;
import p.lodz.pl.iad.task1.helpers.FileHelper;
import p.lodz.pl.iad.task1.helpers.PlotHelper;
import p.lodz.pl.iad.task1.helpers.StatisticsHelper;

/**
 * 
 * @author Andrzej Lisowski 171131
 * @author Piotr Kluch 165436
 *
 */
public class Task1 {

    private static final String SEPARATOR = ",";
    private static final String PATH = "/home/andrzej/Pulpit/sample";
    private static final String STATISTICS_PATH = "/home/andrzej/Pulpit/stats.txt";
    private static final String HISTOGRAM_PATH = "/home/andrzej/Pulpit/IAD";
    
    public static void main(String[] args) {
        
        Map<Integer, Map<String, List<Double>>> dataMaps= FileHelper.readDataFromFile(PATH, SEPARATOR);
        StatisticsHelper statisticsHelper = new StatisticsHelper();
        
        Map<Integer, Map<String, Map<String, Double>>> statistics = statisticsHelper.getAllStatiscticsFromDataSets(dataMaps);
        String statisticsString = FileHelper.saveStatisticsFromDataSets(statistics, STATISTICS_PATH);
        
        System.out.println(statisticsString);
        
        //Draws histograms
//        for(int key : dataMaps.keySet()){
            PlotHelper.drawHistogram(dataMaps.get(1), HISTOGRAM_PATH);
//        }
        
        
//        EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                try {
//                    GraphicalUserInterface window = new GraphicalUserInterface();
//                    window.frame.setVisible(true);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });

    }

}
