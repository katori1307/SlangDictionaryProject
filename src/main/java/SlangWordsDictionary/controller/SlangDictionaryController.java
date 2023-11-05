package SlangWordsDictionary.controller;

import SlangWordsDictionary.models.SlangDictionary;
import SlangWordsDictionary.view.SlangDictionaryScreen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class SlangDictionaryController {
    private SlangDictionaryScreen screen;
    private SlangDictionary dictionaryModel;
    public SlangDictionaryController(SlangDictionaryScreen screen, SlangDictionary dictionaryModel) {
        this.screen = screen;
        this.dictionaryModel = dictionaryModel;
        // handle button event for tasks.
        screen.getSearchBtnActionListener(new searchBtnListener());

    }

    class searchBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            HashMap<String, String> dictionaryRes = dictionaryModel.getSlangDictionary();

//            screen.showDictionary(dictionaryRes);
        }
    }

    public static void main(String[] args) {
        SlangDictionaryScreen screen = new SlangDictionaryScreen();
        SlangDictionary dictionaryModel = new SlangDictionary();
        SlangDictionaryController controller = new SlangDictionaryController(screen, dictionaryModel);
    }

}
