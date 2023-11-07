package SlangWordsDictionary.controller;

import SlangWordsDictionary.models.SlangDictionary;
import SlangWordsDictionary.view.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class SlangDictionaryController {
    private SlangDictionaryScreen screen;
    private SearchSlangScreen searchScreen;
    private SearchSlangScreen searchDefinitionScreen;
    private SlangDictionary dictionaryModel;
    private HistoryScreen historyScreen;
    private ModifySlangScreen addSlangScreen;
    private ModifySlangScreen editSlangScreen;
    private ModifySlangScreen deleteSlangScreen;
    private AlertScreen delConfirmScreen;
    private AlertScreen resetConfirmScreen;
    private AlertScreen duplicateScreen;
    private AlertScreen randomSlangScreen;
    public SlangDictionaryController(SlangDictionaryScreen screen,
                                     SlangDictionary dictionaryModel,
                                     SearchSlangScreen searchScreen,
                                     SearchSlangScreen searchDefinitionScreen,
                                     HistoryScreen historyScreen,
                                     ModifySlangScreen addSlangScreen,
                                     ModifySlangScreen editSlangScreen,
                                     ModifySlangScreen deleteSlangScreen,
                                     AlertScreen delConfirmScreen,
                                     AlertScreen duplicateScreen,
                                     AlertScreen resetConfirmScreen,
                                     AlertScreen randomSlangScreen) {
        this.screen = screen;
        this.dictionaryModel = dictionaryModel;
        this.searchScreen = searchScreen;
        this.searchDefinitionScreen = searchDefinitionScreen;
        this.historyScreen = historyScreen;
        this.addSlangScreen = addSlangScreen;
        this.editSlangScreen = editSlangScreen;
        this.delConfirmScreen = delConfirmScreen;
        this.duplicateScreen = duplicateScreen;
        this.deleteSlangScreen = deleteSlangScreen;
        this.resetConfirmScreen = resetConfirmScreen;
        this.randomSlangScreen = randomSlangScreen;
        // handle button event for tasks.
        screen.addSearchSlangScreenBtnListener(new searchSlangScreenBtnListener());
        screen.addSearchDefinitionScreenBtnListener(new searchDefinitionScreenBtnListener());
        screen.addShowHistoryBtnListener(new showHistoryBtnListener());
        screen.addAddNewSlangBtnListener(new newSlangScreenBtnListener());
        screen.addEditSlangBtnListener(new editSlangScreenBtnListener());
        screen.addDeleteSlangBtnListener(new deleteSlangScreenBtnListener());
        screen.addResetSlangDictionaryBtnListener(new resetSlangScreenBtnListener());
        screen.addRandomSlangBtnListener(new randomSlangScreenBtnListener());

        searchScreen.addSearchBtnListener(new searchSlangBtnListener());
        searchDefinitionScreen.addSearchBtnListener(new searchDefinitionBtnListener());
        addSlangScreen.addAddSlangBtnListener(new addNewSlangBtnListener());
        editSlangScreen.addEditBtnListener(new editBtnListener());
        deleteSlangScreen.addDeleteBtnListener(new deleteSlangBtnListener());
        delConfirmScreen.addConfirmBtnListener(new confirmDelBtnListener());
        duplicateScreen.addDuplicateBtnListener(new duplicateSlangBtnListener());
        duplicateScreen.addOverwriteBtnListener(new overwriteSlangBtnListener());
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
            deleteSlangScreen.dispose();
            delConfirmScreen.dispose();
            resetConfirmScreen.dispose();
            randomSlangScreen.dispose();
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
            screen.dispose();
        }
    }
    class searchSlangBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            HashMap<String, String> currentDictionary = dictionaryModel.getSlangDictionary();
            HashMap<String, String> res = new HashMap<>();
            System.out.println("Is in search event");
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
                dictionaryModel.setTempSlang(inputSlang);
                dictionaryModel.setTempDef(inputDef);
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
        @Override
        public void actionPerformed(ActionEvent e) {
            String slang = dictionaryModel.getTempSlang();
            String def = dictionaryModel.getTempDef();
            addSlangScreen.addSlangToTable(slang, def);
            dictionaryModel.updateSlangDictionary(slang, def);
            duplicateScreen.dispose();
        }
    }
    class overwriteSlangBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String slang = dictionaryModel.getTempSlang();
            String def = dictionaryModel.getTempDef();
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
                        if(currentDict.containsKey(newSlang)) {
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
    class deleteSlangScreenBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            deleteSlangScreen.setVisible(true);
            deleteSlangScreen.addGoBackBtnListener(new goBackBtnListener());
            screen.dispose();
        }
    }
    class deleteSlangBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            HashMap<String, String> currentDict = dictionaryModel.getSlangDictionary();
            String slang = deleteSlangScreen.getInputSlang();
            delConfirmScreen.setVisible(true);
            delConfirmScreen.addGoBackBtnListener(new goBackBtnListener());
            dictionaryModel.setTempSlang(slang);
            dictionaryModel.setTempDef(currentDict.get(slang));
        }
    }
    class confirmDelBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String slang = dictionaryModel.getTempSlang();
            String def = dictionaryModel.getTempDef();
            deleteSlangScreen.addSlangToTable(slang, def);
            dictionaryModel.deleteSlang(slang);
            delConfirmScreen.dispose();
        }
    }
    class resetSlangScreenBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            resetConfirmScreen.setVisible(true);
            resetConfirmScreen.addGoBackBtnListener(new goBackBtnListener());
            resetConfirmScreen.addConfirmBtnListener(new resetDictionaryBtnListener());
//            resetConfirmScreen.dispose();
        }
    }
    class resetDictionaryBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dictionaryModel.setSlangDictionary(dictionaryModel.getOldDictionary());
            resetConfirmScreen.dispose();
        }
    }
    class randomSlangScreenBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            randomSlangScreen.addGoBackBtnListener(new goBackBtnListener());
            HashMap<String, String> currentDict = dictionaryModel.getSlangDictionary();
            List<String> keyList = new ArrayList<>(currentDict.keySet());
            Random random = new Random();
            String rdSlang = keyList.get(random.nextInt(keyList.size()));
            String rdDef = currentDict.get(rdSlang);
            randomSlangScreen.setTextContent(rdSlang, rdDef);
            randomSlangScreen.setVisible(true);
        }
    }
    public static void main(String[] args) {
        SlangDictionaryScreen screen = new SlangDictionaryScreen();
        SlangDictionary dictionaryModel = new SlangDictionary();
        SearchSlangScreen searchSlangScreen = new SearchSlangScreen();
        SearchSlangScreen searchDefinitionScreen = new SearchSlangScreen();
        HistoryScreen historyScreen = new HistoryScreen();
        ModifySlangScreen addSlangScreen = new ModifySlangScreen("add");
        ModifySlangScreen editSlangScreen = new ModifySlangScreen("edit");
        ModifySlangScreen deleteSlangScreen = new ModifySlangScreen("delete");
        AlertScreen delConfirmScreen = new AlertScreen("confirmDel");
        AlertScreen duplicateScreen = new AlertScreen("duplicateSlang");
        AlertScreen resetConfirmScreen = new AlertScreen("confirmReset");
        AlertScreen randomSlangScreen = new AlertScreen("randomSlang");
        SlangDictionaryController controller = new SlangDictionaryController(screen, dictionaryModel,
                                                    searchSlangScreen, searchDefinitionScreen,
                                                    historyScreen, addSlangScreen, editSlangScreen,
                                                    deleteSlangScreen, delConfirmScreen, duplicateScreen,
                                                    resetConfirmScreen, randomSlangScreen);
    }
}
