package p.lodz.pl.iad.task1.helpers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import p.lodz.pl.iad.task1.characteristics.dispertion.IStatisticalDispertion;
import p.lodz.pl.iad.task1.characteristics.dispertion.StatisticalDispertionImpl;

import com.panayotis.gnuplot.JavaPlot;
import com.panayotis.gnuplot.terminal.ImageTerminal;

public class PlotHelper {

    private static final String ALL_DATA_CHARACTERISTICS = "Characteristics for whole data set";

    private static final String USER_DIR = System.getProperty("user.dir");

    public static void drawHistogram(String prefix, Map<String, List<Double>> data, String path) {

        Map<String, String> tempData = new HashMap<String, String>();
        for (String key : data.keySet()) {
            tempData.put(key, saveToTempFile(data.get(key), key));
        }
        IStatisticalDispertion staticalDispertionCalculations = new StatisticalDispertionImpl();
        double range = staticalDispertionCalculations.calculateRange(data.get(ALL_DATA_CHARACTERISTICS));
        double maxX = data.get(ALL_DATA_CHARACTERISTICS).get(0);
        for (double elem : data.get(ALL_DATA_CHARACTERISTICS)) {
            if (elem > maxX) {
                maxX = elem;
            }
        }
        double minX = maxX - range;
        for (String key : tempData.keySet()) {
            saveGraph(tempData.get(key), prefix+" "+key, minX, maxX, range);
        }
    }

    private static String saveToTempFile(List<Double> data, String fileName) {
        IStatisticalDispertion staticalDispertionCalculations = new StatisticalDispertionImpl();
        double range = staticalDispertionCalculations.calculateRange(data);
        double max = data.get(0);
        for (double elem : data) {
            if (elem > max) {
                max = elem;
            }
        }

        double histogramBoxWidth = 0.25;
        double minBox = ((int) ((max - range) / histogramBoxWidth)) * histogramBoxWidth;
        double maxBox = ((int) (max / histogramBoxWidth)) * histogramBoxWidth;
        int size = (int) ((maxBox - minBox) / histogramBoxWidth) + 3;

        double histogram[][] = new double[size][2];
        for (double elem : data) {
            for (int i = 0; i < size; i++) {
                if ((elem > histogramBoxWidth * i + minBox) && (elem < histogramBoxWidth * (i + 1) + minBox)) {
                    histogram[i][1]++;
                    histogram[i][0] = i * histogramBoxWidth + minBox;
                }
            }
        }

        File file = new File(USER_DIR + "/data/temp/" + fileName + ".dat");
        try {
            file.createNewFile();
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < histogram.length; i++) {
                sb.append(histogram[i][0] + 0.125);
                sb.append(" ");
                sb.append(histogram[i][1]);
                sb.append("\n");
            }
            bw.write(sb.toString());
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return file.getPath();
    }

    private static void saveGraph(String tempDataPath, String graphName, double minX, double maxX, double range) {
        ImageTerminal png = new ImageTerminal();
        String fullPath = USER_DIR + "/data/graphs/" + graphName;

        File file = new File(fullPath);
        try {
            file.createNewFile();
            png.processOutput(new FileInputStream(file));
        } catch (FileNotFoundException ex) {
            System.err.print(ex);
        } catch (IOException ex) {
            System.err.print(ex);
        }

        JavaPlot p = new JavaPlot();

        StringBuilder sb = new StringBuilder();
        sb.append("\"" + tempDataPath + "\"u 1:2 w boxes");
        p.addPlot(sb.toString());
        p.set("key", "left");
        p.set("style", "fill transparent solid 2 noborder");
        p.set("grid", "y");
        p.set("style", "histogram rowstacked");
        p.set("boxwidth", "0.25");
        p.set("style", "fill pattern border -1");
        p.set("ytics", "10 nomirror");
        p.set("yrange", "[0:100]");
        p.set("xrange", "[" + (minX - 1) + ":" + (maxX + 1) + "]");
        p.getAxis("y").setLabel("Number of occurences");
        p.getAxis("x").setLabel("Values");
        p.set("ytics", "5");
        if(range>3.0){
            p.set("xtics", "0.5");
        } else {
            p.set("xtics", "0.25");
        }
        p.setTitle("Histogram of dataset " + graphName);
        p.set("style", "data histograms");
        p.setTerminal(png);
        p.plot();

        try {
            ImageIO.write(png.getImage(), "png", file);
        } catch (IOException ex) {
            System.err.print(ex);
        }
    }

}
