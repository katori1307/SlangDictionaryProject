package SlangWordsDictionary.models;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SlangDictionary {
    private HashMap<String, String> slangDictionary;
    private HashMap<String, String> oldDictionary;
    private HashMap<String, String> history;
    private ArrayList<String> slangHistory;
    private ArrayList<String> defHistory;
    private String tempSlang;
    private String tempDef;

    public void setTempSlang(String tempSlang) {
        this.tempSlang = tempSlang;
    }

    public String getTempSlang() {
        return tempSlang;
    }

    public String getTempDef() {
        return tempDef;
    }

    public void setTempDef(String tempDef) {
        this.tempDef = tempDef;
    }

    public ArrayList<String> getDefHistory() {
        return defHistory;
    }

    public ArrayList<String> getSlangHistory() {
        return slangHistory;
    }

    public HashMap<String, String> getHistory() {
        return history;
    }
    public HashMap<String, String> getSlangDictionary() {
        return slangDictionary;
    }
    public HashMap<String, String> getOldDictionary() {
        return oldDictionary;
    }
    public void updateSlangDictionary(String slang, String def) {
        slangDictionary.put(slang, def);
    }
    public void overwriteSlang(String slang, String def) {
        for(Map.Entry<String, String> entry: slangDictionary.entrySet()) {
            if(Objects.equals(entry.getKey(), slang)) {
                entry.setValue(def);
            }
        }
    }
    public void deleteSlang(String slang) {
        slangDictionary.remove(slang);
    }

    public void setSlangDictionary(HashMap<String, String> slangDictionary) {
        this.slangDictionary = slangDictionary;
    }

    public void updateSlangHistory(String data) {
        slangHistory.add(data);
    }
    public void updateDefHistory(String data) {
        defHistory.add(data);
    }

    public SlangDictionary() {
        slangDictionary = new HashMap<>();
        slangHistory = new ArrayList<>();
        defHistory = new ArrayList<>();
        // load data from slang.txt
        loadDataFromTextFile("./src/main/java/data/slang.txt");
    }

    private void loadDataFromTextFile(String filePath) {
        try(BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            // handle uploading data from slang.txt
            String line = "";
            // skip the first line.
            line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("`");
                if(parts.length == 2) {
                    String slang = parts[0].trim();
                    String definitions = parts[1].replace("|", ",").trim();
                    slangDictionary.put(slang, definitions);
                }
            }
            oldDictionary = new HashMap<>(slangDictionary);
//            System.out.println("Read file successfully");
        } catch (IOException e) {
            System.out.println("Cannot read file. Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SlangDictionary slangDictionary = new SlangDictionary();
//        System.out.println(System.getProperty("user.dir"));
    }



}
