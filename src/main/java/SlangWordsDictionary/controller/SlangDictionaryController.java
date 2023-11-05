package SlangWordsDictionary.controller;

import SlangWordsDictionary.models.SlangDictionary;
import SlangWordsDictionary.view.HistoryScreen;
import SlangWordsDictionary.view.SearchSlangScreen;
import SlangWordsDictionary.view.SlangDictionaryScreen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class SlangDictionaryController {
    private SlangDictionaryScreen screen;
    private SearchSlangScreen searchScreen;
    private SearchSlangScreen searchDefinitionScreen;
    private SlangDictionary dictionaryModel;
    private HistoryScreen historyScreen;
    public SlangDictionaryController(SlangDictionaryScreen screen,
                                     SlangDictionary dictionaryModel,
                                     SearchSlangScreen searchScreen,
                                     SearchSlangScreen searchDefinitionScreen,
                                     HistoryScreen historyScreen) {
        this.screen = screen;
        this.dictionaryModel = dictionaryModel;
        this.searchScreen = searchScreen;
        this.searchDefinitionScreen = searchDefinitionScreen;
        this.historyScreen = historyScreen;
        // handle button event for tasks.
        screen.addSearchSlangScreenBtnListener(new searchSlangScreenBtnListener());
        screen.addSearchDefinitionScreenBtnListener(new searchDefinitionScreenBtnListener());
        screen.addShowHistoryBtnListener(new showHistoryBtnListener());
        searchScreen.addGoBackBtnListener(new goBackBtnListener());
        historyScreen.addGoBackBtnListener(new goBackBtnListener());

        searchScreen.addSearchBtnListener(new searchSlangBtnListener());
        searchDefinitionScreen.addGoBackBtnListener(new goBackBtnListener());
        searchDefinitionScreen.addSearchBtnListener(new searchDefinitionBtnListener());

    }

    class searchSlangScreenBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            searchScreen.setLabelContent("INPUT A SLANG TO SEARCH FOR ITS DEFINITIONS");
            searchScreen.resetTableData();
            searchScreen.setVisible(true);
            screen.dispose();
        }
    }
    class searchDefinitionScreenBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            searchDefinitionScreen.setLabelContent("INPUT DEFINITION TO SEARCH FOR THE SLANG");
            searchDefinitionScreen.resetTableData();
            searchDefinitionScreen.setVisible(true);
            screen.dispose();
        }
    }
    class goBackBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            screen.pack();
            screen.setVisible(true);
            searchScreen.dispose();
            searchDefinitionScreen.dispose();
            historyScreen.dispose();
        }
    }
    class searchSlangBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            HashMap<String, String> currentDictionary = dictionaryModel.getSlangDictionary();
            HashMap<String, String> res = new HashMap<>();
            String inputSlang = searchScreen.getInputFromTextField();
            if(currentDictionary.containsKey(inputSlang)) {
                res.put(inputSlang, currentDictionary.get(inputSlang));
                dictionaryModel.updateSlangHistory(inputSlang);
            } else {
                res.put("Invalid input slang!","Slang does not exist!");
            }
            searchScreen.printDictionary(res);
        }
    }

    class searchDefinitionBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            HashMap<String, String> currentDictionary = dictionaryModel.getSlangDictionary();
            HashMap<String, String> res = new HashMap<>();
            String inputDef = searchDefinitionScreen.getInputFromTextField();
            dictionaryModel.updateDefHistory(inputDef);
            inputDef = inputDef.toLowerCase();
            for(Map.Entry<String, String> entry: currentDictionary.entrySet()) {
                String definition = entry.getValue().toLowerCase();
                if(definition.contains(inputDef)) {
                    res.put(entry.getKey(), entry.getValue());
                }
            }
            if(res.isEmpty()) {
                res.put("Invalid definition input", "Your definition input does not match any slang's definitions");
            }
            searchDefinitionScreen.printDictionary(res);
        }
    }

    class showHistoryBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            historyScreen.setVisible(true);
            screen.dispose();
            historyScreen.printHistory(dictionaryModel.getSlangHistory(), dictionaryModel.getDefHistory());
        }
    }

    public static void main(String[] args) {
        SlangDictionaryScreen screen = new SlangDictionaryScreen();
        SlangDictionary dictionaryModel = new SlangDictionary();
        SearchSlangScreen searchSlangScreen = new SearchSlangScreen();
        SearchSlangScreen searchDefinitionScreen = new SearchSlangScreen();
        HistoryScreen historyScreen = new HistoryScreen();
        SlangDictionaryController controller = new SlangDictionaryController(screen, dictionaryModel,
                                                    searchSlangScreen, searchDefinitionScreen,
                                                    historyScreen);
    }
}
