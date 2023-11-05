package SlangWordsDictionary.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class SearchSlangScreen extends JFrame {
    private JButton searchBtn;
    private JButton goBackBtn;
    private DefaultTableModel model;
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
//        setVisible(true);
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
        searchBtn = new JButton("Search for definitions");
        goBackBtn = new JButton("Go back");
//        handleGoBackBtn(goBackBtn);
        formControlPanel.add(new JTextField(20));
        formControlPanel.add(searchBtn);
        formControlPanel.add(goBackBtn);

        return formControlPanel;
    }
    private JPanel createResTable() {
        JPanel tablePanel = new JPanel();
        model = new DefaultTableModel();
        model.addColumn("Slang");
        model.addColumn("Definition");
//        model.addRow(new Object[]{"#1", "Number one"});
        JTable resTable = new JTable(model);
        resTable.getTableHeader().setResizingAllowed(false);
        resTable.getTableHeader().setReorderingAllowed(false);
        resTable.getColumnModel().getColumn(0).setPreferredWidth(200);
        resTable.getColumnModel().getColumn(1).setPreferredWidth(400);
        JScrollPane scrollPane = new JScrollPane(resTable);
        scrollPane.setPreferredSize(new Dimension(500, 275));
        tablePanel.add(scrollPane);

        return tablePanel;
    }
//    private void handleGoBackBtn(JButton goBackBtn) {
//        goBackBtn.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                SlangDictionaryScreen slangDictionaryScreen = new SlangDictionaryScreen();
//                slangDictionaryScreen.setVisible(true);
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
    public void addSearchBtnListener(ActionListener listener) {
        searchBtn.addActionListener(listener);
    }

    public void addGoBackBtnListener(ActionListener listener) {
        goBackBtn.addActionListener(listener);
    }

    public void printDictionary(HashMap<String, String> dictionary) {
        for(Map.Entry<String, String> entry: dictionary.entrySet()) {
            String slang = entry.getKey();
            String definitions = entry.getValue();
            model.addRow(new Object[]{slang, definitions});
        }
    }

//    public static void main(String[] args) {
//        javax.swing.SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                SearchSlangScreen screen = new SearchSlangScreen();
//            }
//        });
//    }
}
