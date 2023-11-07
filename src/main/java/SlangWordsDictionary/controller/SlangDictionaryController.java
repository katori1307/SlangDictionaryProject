package SlangWordsDictionary.controller;

import SlangWordsDictionary.models.SlangDictionary;
import SlangWordsDictionary.view.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SlangDictionaryController {
    private SlangDictionaryScreen screen;
    private SearchSlangScreen searchScreen;
    private SearchSlangScreen searchDefinitionScreen;
    private SlangDictionary dictionaryModel;
    private HistoryScreen historyScreen;
    private AddAndEditSlangScreen addSlangScreen;
    private AddAndEditSlangScreen editSlangScreen;
    private ConfirmScreen delConfirmScreen;
    private ConfirmScreen duplicateScreen;
    public SlangDictionaryController(SlangDictionaryScreen screen,
                                     SlangDictionary dictionaryModel,
                                     SearchSlangScreen searchScreen,
                                     SearchSlangScreen searchDefinitionScreen,
                                     HistoryScreen historyScreen,
                                     AddAndEditSlangScreen addSlangScreen,
                                     AddAndEditSlangScreen editSlangScreen,
                                     ConfirmScreen delConfirmScreen,
                                     ConfirmScreen duplicateScreen) {
        this.screen = screen;
        this.dictionaryModel = dictionaryModel;
        this.searchScreen = searchScreen;
        this.searchDefinitionScreen = searchDefinitionScreen;
        this.historyScreen = historyScreen;
        this.addSlangScreen = addSlangScreen;
        this.editSlangScreen = editSlangScreen;
        this.delConfirmScreen = delConfirmScreen;
        this.duplicateScreen = duplicateScreen;
        // handle button event for tasks.
        screen.addSearchSlangScreenBtnListener(new searchSlangScreenBtnListener());
        screen.addSearchDefinitionScreenBtnListener(new searchDefinitionScreenBtnListener());
        screen.addShowHistoryBtnListener(new showHistoryBtnListener());
        screen.addAddNewSlangBtnListener(new newSlangScreenBtnListener());
        screen.addEditSlangBtnListener(new editSlangScreenBtnListener());
        addSlangScreen.addAddSlangBtnListener(new addNewSlangBtnListener());

        searchScreen.addSearchBtnListener(new searchSlangBtnListener());
        searchDefinitionScreen.addSearchBtnListener(new searchDefinitionBtnListener());

    }

    class searchSlangScreenBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            searchScreen.setLabelContent("INPUT A SLANG TO SEARCH FOR ITS DEFINITIONS");
            searchScreen.resetTableData();
            searchScreen.addGoBackBtnListener(new goBackBtnListener());
            searchScreen.setVisible(true);
            screen.dispose();
        }
    }
    class searchDefinitionScreenBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            searchDefinitionScreen.setLabelContent("INPUT DEFINITION TO SEARCH FOR THE SLANG");
            searchDefinitionScreen.resetTableData();
            searchDefinitionScreen.addGoBackBtnListener(new goBackBtnListener());
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
            addSlangScreen.dispose();
            editSlangScreen.dispose();
        }
    }
    class newSlangScreenBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            addSlangScreen.addGoBackBtnListener(new goBackBtnListener());
            addSlangScreen.setVisible(true);
            screen.dispose();
        }
    }
    class editSlangScreenBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            editSlangScreen.setVisible(true);
            editSlangScreen.addGoBackBtnListener(new goBackBtnListener());
            editSlangScreen.addEditBtnListener(new editBtnListener());
            screen.dispose();
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
            historyScreen.addGoBackBtnListener(new goBackBtnListener());
            historyScreen.printHistory(dictionaryModel.getSlangHistory(), dictionaryModel.getDefHistory());
        }
    }
    private void addNewSlangModule(String inputSlang, String inputDef) {
        HashMap<String, String> slangDict = dictionaryModel.getSlangDictionary();
        for(Map.Entry<String, String> entry: slangDict.entrySet()) {
            if(Objects.equals(entry.getKey(), inputSlang)) {
                duplicateScreen.setVisible(true);
                duplicateScreen.addDuplicateBtnListener(new duplicateSlangBtnListener(inputSlang, inputDef));
                duplicateScreen.addOverwriteBtnListener(new overwriteSlangBtnListener(inputSlang, inputDef));
                return;
            }
        }
        if(inputSlang.contains("`")) {
            addSlangScreen.addSlangToTable("Invalid slang input", "");
        } else {
            addSlangScreen.addSlangToTable(inputSlang, inputDef);
            dictionaryModel.updateSlangDictionary(inputSlang, inputDef);
        }
    }
    class addNewSlangBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String inputSlang = addSlangScreen.getInputSlang();
            String inputDef = addSlangScreen.getInputDefinition();
            addNewSlangModule(inputSlang, inputDef);
        }
    }
    class duplicateSlangBtnListener implements ActionListener {
        private String slang;
        private String def;
        duplicateSlangBtnListener(String slang, String def) {
            this.slang = slang;
            this.def = def;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            addSlangScreen.addSlangToTable(slang, def);
            dictionaryModel.updateSlangDictionary(slang, def);
            duplicateScreen.dispose();
        }
    }
    class overwriteSlangBtnListener implements ActionListener {
        private String slang;
        private String def;
        overwriteSlangBtnListener(String slang, String def) {
            this.slang = slang;
            this.def = def;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            addSlangScreen.addSlangToTable(slang, def);
            dictionaryModel.overwriteSlang(slang, def);
            duplicateScreen.dispose();
        }
    }

    class editBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            HashMap<String, String> currentDict = dictionaryModel.getSlangDictionary();
            String inputSlang = editSlangScreen.getInputSlang();
            String newSlang = editSlangScreen.getInputNewSlang();
            String newDef = editSlangScreen.getInputDefinition();
            for(Map.Entry<String, String> entry: currentDict.entrySet()) {
                // check if inputSlang existed, then change the definition.
                if(Objects.equals(entry.getKey(), inputSlang)) {
                    // check if inputSlang = newSlang, then change the definition
                    if(Objects.equals(inputSlang, newSlang)) {
                        entry.setValue(newDef);
                        editSlangScreen.addSlangToTable(inputSlang, newDef);
                    }
                    // check if inputSlang != newSlang, then add new slang.
                    else {
                        if(isExistInDictionary(currentDict, newSlang)) {
                            editSlangScreen.addSlangToTable("Old slang and new slang are already Existed!","");
                        } else {
                            addNewSlangModule(newSlang, newDef);
                            editSlangScreen.addSlangToTable(newSlang, newDef);
                        }
                    }
                    return;
                }
            }
            editSlangScreen.addSlangToTable("Slang does not exist!","");
        }
    }
    private boolean isExistInDictionary(HashMap<String, String> dictionary, String slang) {
        for(Map.Entry<String, String> entry: dictionary.entrySet()) {
            if(Objects.equals(entry.getKey(), slang))
                return true;
        }
        return false;
    }


    public static void main(String[] args) {
        SlangDictionaryScreen screen = new SlangDictionaryScreen();
        SlangDictionary dictionaryModel = new SlangDictionary();
        SearchSlangScreen searchSlangScreen = new SearchSlangScreen();
        SearchSlangScreen searchDefinitionScreen = new SearchSlangScreen();
        HistoryScreen historyScreen = new HistoryScreen();
        AddAndEditSlangScreen addSlangScreen = new AddAndEditSlangScreen("add");
        AddAndEditSlangScreen editSlangScreen = new AddAndEditSlangScreen("edit");
        ConfirmScreen delConfirmScreen = new ConfirmScreen("confirmDel");
        ConfirmScreen duplicateScreen = new ConfirmScreen("duplicateSlang");
        SlangDictionaryController controller = new SlangDictionaryController(screen, dictionaryModel,
                                                    searchSlangScreen, searchDefinitionScreen,
                                                    historyScreen, addSlangScreen, editSlangScreen,
                                                    delConfirmScreen, duplicateScreen);
    }
}
