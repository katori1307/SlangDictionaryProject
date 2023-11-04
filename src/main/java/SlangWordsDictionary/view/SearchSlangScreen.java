package SlangWordsDictionary.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchSlangScreen extends JFrame {
    public SearchSlangScreen() {
        createSearchSlangScreen();
    }
    private void createSearchSlangScreen() {
        setTitle("Search Slang");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setDefaultLookAndFeelDecorated(true);
        setPreferredSize(new Dimension(600, 400));
        add(createMainPanel());
        setCursorCenter();
        pack();
        setVisible(true);
    }
    private JPanel createMainPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(20, 0));

        mainPanel.add(createHeadingPanel(), BorderLayout.NORTH);
        mainPanel.add(createFormControl(), BorderLayout.CENTER);
        mainPanel.add(createResTable(), BorderLayout.SOUTH);
        return mainPanel;
    }
    private JPanel createHeadingPanel() {
        JPanel headingPanel = new JPanel();
        headingPanel.add(new JLabel("Input a slang to search for definitions"));
        return headingPanel;
    }
    private JPanel createFormControl() {
        JPanel formControlPanel = new JPanel();
        JButton searchBtn = new JButton("Search for definitions");
        JButton goBackBtn = new JButton("Go back");
        handleGoBackBtn(goBackBtn);
        formControlPanel.add(new JTextField(20));
        formControlPanel.add(searchBtn);
        formControlPanel.add(goBackBtn);

        return formControlPanel;
    }
    private JPanel createResTable() {
        JPanel tablePanel = new JPanel();
        DefaultTableModel model = new DefaultTableModel();
        JTable resTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(resTable);
        scrollPane.setPreferredSize(new Dimension(500, 275));
        tablePanel.add(scrollPane);
        return tablePanel;
    }
    private void handleGoBackBtn(JButton goBackBtn) {
        goBackBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SlangDictionaryScreen slangDictionaryScreen = new SlangDictionaryScreen();
                slangDictionaryScreen.setVisible(true);
                dispose();
            }
        });
    }
    private void setCursorCenter() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - 700) / 2;
        int y = (screenSize.height - 500) / 2;
        setLocation(x, y);
    }

//    public static void main(String[] args) {
//        javax.swing.SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                SearchSlangScreen screen = new SearchSlangScreen();
//            }
//        });
//    }
}
