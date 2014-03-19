package p.lodz.pl.iad.task1.helpers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileHelper {
    
    public static Map<Integer, List<Object>> readDataFromFile(String path, String separator){
        Map<Integer, List<Object>> result = new HashMap<Integer, List<Object>>();
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
                    for(int i=0; data.length>i; i++){
                        if(result.containsKey(i)){
                            result.get(i).add(data[i]);
                        } else {
                            List<Object> newList = new ArrayList<Object>();
                            newList.add(data[i]);
                            result.put(i, newList);
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
