package SlangWordsDictionary.controller;

import SlangWordsDictionary.models.SlangDictionary;
import SlangWordsDictionary.view.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class SlangDictionaryController {
    private final SlangDictionaryScreen screen;
    private final SearchSlangScreen searchScreen;
    private final SearchSlangScreen searchDefinitionScreen;
    private final SlangDictionary dictionaryModel;
    private final HistoryScreen historyScreen;
    private final ModifySlangScreen addSlangScreen;
    private final ModifySlangScreen editSlangScreen;
    private final ModifySlangScreen deleteSlangScreen;
    private final AlertScreen delConfirmScreen;
    private final AlertScreen resetConfirmScreen;
    private final AlertScreen duplicateScreen;
    private final AlertScreen randomSlangScreen;
    private final GameScreen guessSlangScreen;
    private final GameScreen guessDefScreen;
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
                                     AlertScreen randomSlangScreen,
                                     GameScreen guessSlangScreen,
                                     GameScreen guessDefScreen) {
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
        this.guessSlangScreen = guessSlangScreen;
        this.guessDefScreen = guessDefScreen;

        // handle button event for tasks.
        screen.addSearchSlangScreenBtnListener(new searchSlangScreenBtnListener());
        screen.addSearchDefinitionScreenBtnListener(new searchDefinitionScreenBtnListener());
        screen.addShowHistoryBtnListener(new showHistoryBtnListener());
        screen.addAddNewSlangBtnListener(new newSlangScreenBtnListener());
        screen.addEditSlangBtnListener(new editSlangScreenBtnListener());
        screen.addDeleteSlangBtnListener(new deleteSlangScreenBtnListener());
        screen.addResetSlangDictionaryBtnListener(new resetSlangScreenBtnListener());
        screen.addRandomSlangBtnListener(new randomSlangScreenBtnListener());
        screen.addMini1GameBtnListener(new guessSlangScreenBtnListener());
        screen.addMini2GameBtnListener(new guessDefScreenBtnListener());

        searchScreen.addSearchBtnListener(new searchSlangBtnListener());
        searchDefinitionScreen.addSearchBtnListener(new searchDefinitionBtnListener());
        addSlangScreen.addAddSlangBtnListener(new addNewSlangBtnListener());
        editSlangScreen.addEditBtnListener(new editBtnListener());
        deleteSlangScreen.addDeleteBtnListener(new deleteSlangBtnListener());
        delConfirmScreen.addConfirmBtnListener(new confirmDelBtnListener());
        duplicateScreen.addDuplicateBtnListener(new duplicateSlangBtnListener());
        duplicateScreen.addOverwriteBtnListener(new overwriteSlangBtnListener());

        guessSlangScreen.addOption1BtnListener(new optionBtnGuessSlangListener(1));
        guessSlangScreen.addOption2BtnListener(new optionBtnGuessSlangListener(2));
        guessSlangScreen.addOption3BtnListener(new optionBtnGuessSlangListener(3));
        guessSlangScreen.addOption4BtnListener(new optionBtnGuessSlangListener(4));

        guessDefScreen.addOption1BtnListener(new optionBtnGuessDefListener(1));
        guessDefScreen.addOption2BtnListener(new optionBtnGuessDefListener(2));
        guessDefScreen.addOption3BtnListener(new optionBtnGuessDefListener(3));
        guessDefScreen.addOption4BtnListener(new optionBtnGuessDefListener(4));


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
            guessSlangScreen.dispose();
            guessDefScreen.dispose();
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
    class guessSlangScreenBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            guessSlangScreen.setVisible(true);
            guessSlangScreen.setResult("");
            guessSlangScreen.addGoBackBtnListener(new goBackBtnListener());
            HashMap<String, String> currentDict = dictionaryModel.getSlangDictionary();
            List<String> keyList = new ArrayList<>(currentDict.keySet());
            Random random = new Random();
            String keySlang = keyList.get(random.nextInt(keyList.size()));
            guessSlangScreen.setKey(keySlang);
            String keyDef = currentDict.get(keySlang);
            keyList.remove(keySlang);
            String optA = keyList.get(random.nextInt(keyList.size()));
            String optB = keyList.get(random.nextInt(keyList.size()));
            String optC = keyList.get(random.nextInt(keyList.size()));


            guessSlangScreen.setOptionsContent(keySlang, optA, optB, optC);
            guessSlangScreen.setTarget(keyDef);
            screen.dispose();

        }
    }
    class guessDefScreenBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            guessDefScreen.setVisible(true);
            guessDefScreen.setResult("");
            guessDefScreen.addGoBackBtnListener(new goBackBtnListener());
            HashMap<String, String> currentDict = dictionaryModel.getSlangDictionary();
            List<String> keyList = new ArrayList<>(currentDict.keySet());
            List<String> defList = new ArrayList<>(currentDict.values());
            Random random = new Random();
            String keySlang = keyList.get(random.nextInt(keyList.size()));
            String keyDef = currentDict.get(keySlang);
            guessDefScreen.setKey(keyDef);
            defList.remove(keyDef);
            String optA = defList.get(random.nextInt(defList.size()));
            String optB = defList.get(random.nextInt(defList.size()));
            String optC = defList.get(random.nextInt(defList.size()));

            guessDefScreen.setTarget(keySlang);
            guessDefScreen.setOptionsContent(keyDef, optA, optB, optC);
            screen.dispose();
        }
    }
    class optionBtnGuessSlangListener implements ActionListener {
        private final int choice;
        public optionBtnGuessSlangListener(int choice) {
            this.choice = choice;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            String opt = "";
            switch (choice) {
                case 1:
                    opt = guessSlangScreen.getOption_1_Text();
                    break;
                case 2:
                    opt = guessSlangScreen.getOption_2_Text();
                    break;
                case 3:
                    opt = guessSlangScreen.getOption_3_Text();
                    break;
                case 4:
                    opt = guessSlangScreen.getOption_4_Text();
                    break;
                default:
                    break;
            }
            if(Objects.equals(opt, guessSlangScreen.getKey())) {
                guessSlangScreen.setResult("Correct!");
            } else {
                guessSlangScreen.setResult("Incorrect...");
            }
        }
    }
    class optionBtnGuessDefListener implements ActionListener {
        private final int choice;
        public optionBtnGuessDefListener(int choice) {
            this.choice = choice;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            String opt = "";
            switch (choice) {
                case 1:
                    opt = guessDefScreen.getOption_1_Text();
                    break;
                case 2:
                    opt = guessDefScreen.getOption_2_Text();
                    break;
                case 3:
                    opt = guessDefScreen.getOption_3_Text();
                    break;
                case 4:
                    opt = guessDefScreen.getOption_4_Text();
                    break;
                default:
                    break;
            }
            if(Objects.equals(opt, guessDefScreen.getKey())) {
                guessDefScreen.setResult("Correct!");
            } else {
                guessDefScreen.setResult("Incorrect...");
            }
        }
    }
//    public static void main(String[] args) {
//        SlangDictionaryScreen screen = new SlangDictionaryScreen();
//        SlangDictionary dictionaryModel = new SlangDictionary();
//        SearchSlangScreen searchSlangScreen = new SearchSlangScreen();
//        SearchSlangScreen searchDefinitionScreen = new SearchSlangScreen();
//        HistoryScreen historyScreen = new HistoryScreen();
//        ModifySlangScreen addSlangScreen = new ModifySlangScreen("add");
//        ModifySlangScreen editSlangScreen = new ModifySlangScreen("edit");
//        ModifySlangScreen deleteSlangScreen = new ModifySlangScreen("delete");
//        AlertScreen delConfirmScreen = new AlertScreen("confirmDel");
//        AlertScreen duplicateScreen = new AlertScreen("duplicateSlang");
//        AlertScreen resetConfirmScreen = new AlertScreen("confirmReset");
//        AlertScreen randomSlangScreen = new AlertScreen("randomSlang");
//        GameScreen guessSlangScreen = new GameScreen("guessSlang");
//        GameScreen guessDefScreen = new GameScreen("guessDef");
//        SlangDictionaryController controller = new SlangDictionaryController(screen, dictionaryModel,
//                                                    searchSlangScreen, searchDefinitionScreen,
//                                                    historyScreen, addSlangScreen, editSlangScreen,
//                                                    deleteSlangScreen, delConfirmScreen, duplicateScreen,
//                                                    resetConfirmScreen, randomSlangScreen,
//                                                    guessSlangScreen, guessDefScreen);
//    }
}
