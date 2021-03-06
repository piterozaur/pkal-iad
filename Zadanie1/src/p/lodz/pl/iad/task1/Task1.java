package p.lodz.pl.iad.task1;

import java.awt.EventQueue;
import java.util.List;
import java.util.Map;

import javax.swing.UIManager;

import com.sun.org.apache.xalan.internal.xsltc.compiler.Constants;

import p.lodz.pl.iad.task1.helpers.FileHelper;
import p.lodz.pl.iad.task1.helpers.PlotHelper;
import p.lodz.pl.iad.task1.helpers.StatisticsHelper;

import p.lodz.pl.iad.task1.gui.*;

/**
 * 
 * @author Andrzej Lisowski 171131
 * @author Piotr Kluch 165436
 *
 */
public class Task1 {

	/*
	private static final String USER_DIR = System.getProperty("user.dir");
	
    private static final String SAMPLE_PATH = USER_DIR + "/data/sample.dat";
    private static final String SEPARATOR = ",";
    private static final String STATISTICS_PATH = USER_DIR + "/data/stats.txt";
    private static final String HISTOGRAM_PATH = USER_DIR + "/data";
    */
	
    public static void main(String[] args) {
        
    	/*
        Map<Integer, Map<String, List<Double>>> dataMaps = FileHelper.readDataFromFile(SAMPLE_PATH, SEPARATOR);
        StatisticsHelper statisticsHelper = new StatisticsHelper();
        
        Map<Integer, Map<String, Map<String, Double>>> statistics = statisticsHelper.getAllStatiscticsFromDataSets(dataMaps);
        String statisticsString = FileHelper.saveStatisticsFromDataSets(statistics, STATISTICS_PATH);
        */
    	
        //System.out.print(statisticsString);
        
        //System.out.println(statistics.get(0).get("Iris-virginica").get("Median"));
        //System.out.print(Constants.);

        
		EventQueue.invokeLater(new Runnable() {
			
		    private InitialWindow initialWindow;

			public void run() {
				try {
					
					//Turn off irritating boldness of swing metal theme
					UIManager.put("swing.boldMetal", Boolean.FALSE); 
	                //UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
					
					//Run the main application controller
					initialWindow = new InitialWindow();
					initialWindow.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			
		});

    }

}
