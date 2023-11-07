package SlangWordsDictionary;

import SlangWordsDictionary.controller.SlangDictionaryController;
import SlangWordsDictionary.models.SlangDictionary;
import SlangWordsDictionary.view.*;

public class Main {
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
        GameScreen guessSlangScreen = new GameScreen("guessSlang");
        GameScreen guessDefScreen = new GameScreen("guessDef");
        SlangDictionaryController controller = new SlangDictionaryController(screen, dictionaryModel,
                searchSlangScreen, searchDefinitionScreen,
                historyScreen, addSlangScreen, editSlangScreen,
                deleteSlangScreen, delConfirmScreen, duplicateScreen,
                resetConfirmScreen, randomSlangScreen,
                guessSlangScreen, guessDefScreen);
    }
}
