package p.lodz.pl.iad.task1.helpers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileHelper {

    private static final String ALL_DATA_CHARACTERISTICS = "Characteristics for whole data set";
    private static final String NEW_LINE = "\n";

    /**
     * 
     * @param path
     * @param separator
     * @return map containing of n maps of "attribute value" - "list of values"
     */
    public static Map<Integer, Map<String, List<Double>>> readDataFromFile(String path, String separator, boolean isHypothesisTestingActive) {
        Map<Integer, Map<String, List<Double>>> result = new HashMap<Integer, Map<String, List<Double>>>();
        try {
            boolean continueReading = true;
            @SuppressWarnings("resource")
            BufferedReader br = new BufferedReader(new FileReader(path));
            while (continueReading) {
                String line = br.readLine();
                if (line == null || "".equals(line)) {
                    continueReading = false;
                } else {
                    String[] data = line.split(separator);
                    String nominalAttribute = "";
                    for (int i = 0; data.length > i; i++) {
                        if (data[i].matches(".*[a-zA-Z].*")) {
                            nominalAttribute = data[i];
                        } else if (isHypothesisTestingActive){
                            int lastElem = data.length-1;
                            nominalAttribute = data[lastElem];
                        }
                    }
                    if (nominalAttribute.equals("")) {
                        nominalAttribute = ALL_DATA_CHARACTERISTICS;
                    }
                    for (int i = 0; data.length > i; i++) {
                        if (!data[i].matches(".*[a-zA-Z].*")) {
                            if (result.containsKey(i)) {
                                result.get(i).get(ALL_DATA_CHARACTERISTICS).add(Double.parseDouble(data[i]));
                            } else {
                                Map<String, List<Double>> newMap = new HashMap<String, List<Double>>();
                                List<Double> newList = new ArrayList<Double>();
                                newList.add(Double.parseDouble(data[i]));
                                newMap.put(ALL_DATA_CHARACTERISTICS, newList);
                                result.put(i, newMap);
                            }
                            if (!nominalAttribute.equals(ALL_DATA_CHARACTERISTICS)) {
                                if (result.get(i).containsKey(nominalAttribute)) {
                                    result.get(i).get(nominalAttribute).add(Double.parseDouble(data[i]));
                                } else {
                                    List<Double> newList = new ArrayList<Double>();
                                    newList.add(Double.parseDouble(data[i]));
                                    result.get(i).put(nominalAttribute, newList);
                                }
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error while reading the file:" + e);
        }

        return result;
    }

    public static void saveStatisticsToFile(Map<String, String> statistics) {

    }

    public static String saveStatisticsFromDataSets(Map<Integer, Map<String, Map<String, Double>>> statistics, String path) {
        StringBuffer sb = new StringBuffer();
        String statisticsToSave = "";
        for (Integer dataSetNum : statistics.keySet()) {
            sb.append("Data set ");
            sb.append(dataSetNum + 1);
            sb.append(":");
            sb.append(NEW_LINE);
            sb.append("\tCharacteristics for whole data set:");
            sb.append(NEW_LINE);
            for (String charactertic : statistics.get(dataSetNum).get(ALL_DATA_CHARACTERISTICS).keySet()) {
                sb.append("\t\t");
                sb.append(charactertic);
                sb.append(": ");
                sb.append(statistics.get(dataSetNum).get(ALL_DATA_CHARACTERISTICS).get(charactertic));
                sb.append(NEW_LINE);
            }
            for (String nominalValue : statistics.get(dataSetNum).keySet()) {
                if(!nominalValue.equals(ALL_DATA_CHARACTERISTICS)){
                    sb.append("\t\tNominal value: ");
                    sb.append(nominalValue);
                    sb.append(NEW_LINE);
                    for (String charactertic : statistics.get(dataSetNum).get(nominalValue).keySet()) {
                        sb.append("\t\t\t");
                        sb.append(charactertic);
                        sb.append(": ");
                        sb.append(statistics.get(dataSetNum).get(nominalValue).get(charactertic));
                        sb.append(NEW_LINE);
                    }
                }
            }
            statisticsToSave = sb.toString();
            BufferedWriter writer = null;
            try {
                writer = new BufferedWriter(new FileWriter(path));
                writer.write(statisticsToSave);
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return statisticsToSave;
    }

}
