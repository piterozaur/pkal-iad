package p.lodz.pl.iad.task1.helpers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileHelper {
    
    private static final String NO_NOMINAL_ATTRIBUTE = "none";
    
    /**
     * 
     * @param path
     * @param separator
     * @return map containing of n maps of "attribute value" - "list of values"
     */
    public static Map<Integer, Map<String, List<Double>>> readDataFromFile(String path, String separator){
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
                    String nominalAttribute="";
                    for(int i=0; data.length>i; i++){
                        if(data[i].matches(".*[a-zA-Z].*")){
                            nominalAttribute=data[i];
                        }
                    }
                    if(nominalAttribute.equals("")){
                        nominalAttribute=NO_NOMINAL_ATTRIBUTE;
                    }
                    for(int i=0; data.length>i; i++){
                        if(!data[i].matches(".*[a-zA-Z].*")){
                            if(result.containsKey(i)){
                                if (result.get(i).containsKey(nominalAttribute)){
                                    result.get(i).get(nominalAttribute).add(Double.parseDouble(data[i]));
                                } else {
                                    List<Double> newList = new ArrayList<Double>();
                                    newList.add(Double.parseDouble(data[i]));
                                    result.get(i).put(nominalAttribute, newList);
                                }
                            } else {
                                Map<String, List<Double>> newMap = new HashMap<String, List<Double>>();
                                List<Double> newList = new ArrayList<Double>();
                                newList.add(Double.parseDouble(data[i]));
                                newMap.put(nominalAttribute, newList);
                                result.put(i, newMap);
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
    
    public static void saveStatisticsToFile(Map<String, String> statistics){
    }

}
