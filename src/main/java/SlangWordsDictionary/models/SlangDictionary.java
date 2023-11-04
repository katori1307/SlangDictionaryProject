package SlangWordsDictionary.models;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class SlangDictionary {
    private final HashMap<String, String> slangDictionary;

    public SlangDictionary() {
        slangDictionary = new HashMap<>();
        // load data from slang.txt
    }

    private void loadDataFromTextFile(String filePath) {
        try(BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            // handle uploading data from slang.txt

        } catch (IOException e) {
            e.getMessage();
        }
    }



}
