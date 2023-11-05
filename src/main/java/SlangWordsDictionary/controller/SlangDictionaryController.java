package SlangWordsDictionary.controller;

import SlangWordsDictionary.models.SlangDictionary;
import SlangWordsDictionary.view.SearchSlangScreen;
import SlangWordsDictionary.view.SlangDictionaryScreen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class SlangDictionaryController {
    private SlangDictionaryScreen screen;
    private SearchSlangScreen searchScreen;
    private SlangDictionary dictionaryModel;
    public SlangDictionaryController(SlangDictionaryScreen screen, SlangDictionary dictionaryModel, SearchSlangScreen searchScreen) {
        this.screen = screen;
        this.dictionaryModel = dictionaryModel;
        this.searchScreen = searchScreen;
        // handle button event for tasks.
//        screen.getSearchBtnActionListener(new searchBtnListener());
        screen.addSearchSlangScreenBtnListener(new searchSlangScreenBtnListener());
        searchScreen.addGoBackBtnListener(new goBackBtnListener());
        searchScreen.addSearchBtnListener(new searchBtnListener());

    }

    class searchBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            HashMap<String, String> dictionaryRes = dictionaryModel.getSlangDictionary();
            searchScreen.printDictionary(dictionaryRes);
//            screen.showDictionary(dictionaryRes);
        }
    }

    class searchSlangScreenBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            searchScreen.setVisible(true);
            screen.dispose();
        }
    }
    class goBackBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            screen.pack();
            screen.setVisible(true);
            searchScreen.dispose();
        }
    }

    public static void main(String[] args) {
        SlangDictionaryScreen screen = new SlangDictionaryScreen();
        SlangDictionary dictionaryModel = new SlangDictionary();
        SearchSlangScreen searchSlangScreen = new SearchSlangScreen();
        SlangDictionaryController controller = new SlangDictionaryController(screen, dictionaryModel, searchSlangScreen);
    }

}
