package p.lodz.pl.iad.task1;

import java.awt.EventQueue;
import java.util.List;
import java.util.Map;

import p.lodz.pl.iad.task1.gui.GraphicalUserInterface;
import p.lodz.pl.iad.task1.helpers.FileHelper;
import p.lodz.pl.iad.task1.helpers.PlotHelper;

/**
 * 
 * @author Andrzej Lisowski 171131
 * @author Piotr Kluch
 *
 */
public class Task1 {

    private static final String SEPARATOR = ",";
    private static final String PATH = "/home/andrzej/Pulpit/sample";
    
    public static void main(String[] args) {
        
        Map<Integer, Map<String, List<Double>>> dataList= FileHelper.readDataFromFile(PATH, SEPARATOR);
        
        PlotHelper.drawHistogram(null, null);
        
        
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
