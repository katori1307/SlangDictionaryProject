package SlangWordsDictionary.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class SlangDictionaryScreen extends JFrame {
    private SearchSlangScreen searchScreen;
    private JButton searchSlangButton;
    private JButton searchDefinitionButton;
    private JButton showHistoryButton;
    private JButton addNewSlangButton;
    private JButton editSlangButton;
    private JButton deleteSlangButton;
    private JButton resetSlangDictionaryButton;
    private JButton randomSlangButton;
    private JButton miniGameButton;
    public SlangDictionaryScreen() {
        CreateMainScreen();
    }
    private void CreateMainScreen() {
        setTitle("Slang Dictionary");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setDefaultLookAndFeelDecorated(true);
        setPreferredSize(new Dimension(700, 500));
        add(createMainPanel());
        setCursorCenter();
        pack();
        setVisible(true);
    }
    private JPanel createMainPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(20, 0));
        mainPanel.add(createHeadingPanel(), BorderLayout.NORTH);
        mainPanel.add(createMenuPanel(), BorderLayout.CENTER);
        return mainPanel;
    }
    private JPanel createHeadingPanel() {
        JPanel headingPn = new JPanel();
        headingPn.add(new JLabel("Slang Dictionary"));
        return headingPn;
    }
    private JPanel createMenuPanel() {
        JPanel menuPanel = new JPanel();
        GridLayout layout = new GridLayout(0,3);
        layout.setHgap(10);
        layout.setVgap(10);
        menuPanel.setLayout(layout);
        menuPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        searchSlangButton = new JButton("Search slang");
        searchDefinitionButton = new JButton("Search definition");
        showHistoryButton = new JButton("Show history");
        addNewSlangButton = new JButton("Add new slang");
        editSlangButton = new JButton("Edit slang");
        deleteSlangButton = new JButton("Delete slang");
        resetSlangDictionaryButton = new JButton("Reset slang dictionary");
        randomSlangButton = new JButton("Random slang");
        miniGameButton = new JButton("Mini game with slang");
        menuPanel.add(searchSlangButton);
        menuPanel.add(searchDefinitionButton);
        menuPanel.add(showHistoryButton);
        menuPanel.add(addNewSlangButton);
        menuPanel.add(editSlangButton);
        menuPanel.add(deleteSlangButton);
        menuPanel.add(resetSlangDictionaryButton);
        menuPanel.add(randomSlangButton);
        menuPanel.add(miniGameButton);

        // initialize other screens
//        searchScreen = new SearchSlangScreen();

//        handleButtonEvent(searchSlangButton);

        return menuPanel;
    }
//    private void handleButtonEvent(JButton btn) {
//        btn.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
////                searchScreen = new SearchSlangScreen();
//                // set visible for other screens and dispose the current screen.
//                searchScreen.setVisible(true);
//                dispose();
//            }
//        });
//    }
    private void setCursorCenter() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - 700) / 2;
        int y = (screenSize.height - 500) / 2;
        setLocation(x, y);
    }
    public void addSearchSlangScreenBtnListener(ActionListener listener) {
        searchSlangButton.addActionListener(listener);
    }
    public void getSearchBtnActionListener(ActionListener listener) {
        searchScreen.addSearchBtnListener(listener);
    }
//    public void showDictionary(HashMap<String, String> dictionary) {
//        searchScreen.printDictionary(dictionary);
//    }

//    public static void main(String[] args) {
//        javax.swing.SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                SlangDictionaryScreen mainScreen = new SlangDictionaryScreen();
//            }
//        });
//    }

}
