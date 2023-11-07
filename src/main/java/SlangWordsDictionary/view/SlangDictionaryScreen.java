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
    private JButton miniGameButton_2;
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
        JLabel mainLabel = new JLabel("Slang Dictionary");
        mainLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headingPn.add(mainLabel);
        return headingPn;
    }
    private JPanel createMenuPanel() {
        JPanel menuPanel = new JPanel();
        GridLayout layout = new GridLayout(0,3);
        layout.setHgap(10);
        layout.setVgap(10);
        menuPanel.setLayout(layout);
        menuPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        Font textFont = new Font("Arial", Font.BOLD, 16);
        searchSlangButton = new JButton("Search slang");
        searchSlangButton.setFont(textFont);

        searchDefinitionButton = new JButton("Search definition");
        searchDefinitionButton.setFont(textFont);

        showHistoryButton = new JButton("Show history");
        showHistoryButton.setFont(textFont);

        addNewSlangButton = new JButton("Add new slang");
        addNewSlangButton.setFont(textFont);

        editSlangButton = new JButton("Edit slang");
        editSlangButton.setFont(textFont);

        deleteSlangButton = new JButton("Delete slang");
        deleteSlangButton.setFont(textFont);

        resetSlangDictionaryButton = new JButton("Reset slang dictionary");
        resetSlangDictionaryButton.setFont(textFont);

        randomSlangButton = new JButton("Random slang");
        randomSlangButton.setFont(textFont);

        miniGameButton = new JButton("Guess slang game");
        miniGameButton.setFont(new Font("Arial", Font.BOLD, 14));

        miniGameButton_2 = new JButton("Guess definition game");
        miniGameButton_2.setFont(new Font("Arial", Font.BOLD, 16));

        menuPanel.add(searchSlangButton);
        menuPanel.add(searchDefinitionButton);
        menuPanel.add(showHistoryButton);
        menuPanel.add(addNewSlangButton);
        menuPanel.add(editSlangButton);
        menuPanel.add(deleteSlangButton);
        menuPanel.add(resetSlangDictionaryButton);
        menuPanel.add(randomSlangButton);
        menuPanel.add(miniGameButton);
        menuPanel.add(miniGameButton_2);

        return menuPanel;
    }
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
    public void addSearchDefinitionScreenBtnListener(ActionListener listener) {
        searchDefinitionButton.addActionListener(listener);
    }
    public void addShowHistoryBtnListener(ActionListener listener) {
        showHistoryButton.addActionListener(listener);
    }
    public void addAddNewSlangBtnListener(ActionListener listener) {
        addNewSlangButton.addActionListener(listener);
    }
    public void addEditSlangBtnListener(ActionListener listener) {
        editSlangButton.addActionListener(listener);
    }
    public void addDeleteSlangBtnListener(ActionListener listener) {
        deleteSlangButton.addActionListener(listener);
    }
    public void addResetSlangDictionaryBtnListener(ActionListener listener) {
        resetSlangDictionaryButton.addActionListener(listener);
    }
    public void addRandomSlangBtnListener(ActionListener listener) {
        randomSlangButton.addActionListener(listener);
    }
    public void addMini1GameBtnListener(ActionListener listener) {
        miniGameButton.addActionListener(listener);
    }
    public void addMini2GameBtnListener(ActionListener listener) {
        miniGameButton_2.addActionListener(listener);
    }


//    public static void main(String[] args) {
//        javax.swing.SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                SlangDictionaryScreen mainScreen = new SlangDictionaryScreen();
//            }
//        });
//    }

}
