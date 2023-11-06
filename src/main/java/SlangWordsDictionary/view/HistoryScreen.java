package SlangWordsDictionary.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HistoryScreen extends JFrame {
    private DefaultTableModel slangTableModel;
    private DefaultTableModel defTableModel;
    private JButton goBackBtn;
    public HistoryScreen() {
        createHistoryScreen();
    }
    private void createHistoryScreen() {
        setTitle("Slang Dictionary");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setDefaultLookAndFeelDecorated(true);
        setPreferredSize(new Dimension(700, 500));
        add(createMainPanel());
        setCursorCenter();
        pack();
//        setVisible(true); // remember to delete this
    }
    private void setCursorCenter() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - 700) / 2;
        int y = (screenSize.height - 500) / 2;
        setLocation(x, y);
    }
    private JPanel createMainPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(20, 0));
        mainPanel.add(createHeadingPanel(), BorderLayout.NORTH);
        mainPanel.add(createHistoryTable(), BorderLayout.CENTER);
        return mainPanel;
    }
    private JPanel createHeadingPanel() {
        JPanel headingPanel = new JPanel();
        headingPanel.add(new JLabel("SEARCHING HISTORY"));
        goBackBtn = new JButton("GO BACK");
        headingPanel.add(goBackBtn);
        return headingPanel;
    }
    private JPanel createHistoryTable() {
        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new GridLayout(1, 2, 20,0));

        slangTableModel = new DefaultTableModel();
        slangTableModel.addColumn("Slangs searched");
        JTable slangTable = new JTable(slangTableModel);
        slangTable.getTableHeader().setReorderingAllowed(false);
        slangTable.getTableHeader().setResizingAllowed(false);
        JScrollPane slangScrollPane = new JScrollPane(slangTable);
//        slangScrollPane.setPreferredSize(new Dimension(200, 200));
        tablePanel.add(slangScrollPane);

        defTableModel = new DefaultTableModel();
        defTableModel.addColumn("Definitions searched");
        JTable defTable = new JTable(defTableModel);
        defTable.getTableHeader().setReorderingAllowed(false);
        defTable.getTableHeader().setResizingAllowed(false);
        JScrollPane defScrollPane = new JScrollPane(defTable);
//        defScrollPane.setPreferredSize(new Dimension(200, 200));
        tablePanel.add(defScrollPane);
        tablePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        return tablePanel;
    }
    public void printHistory(ArrayList<String> slangHistory, ArrayList<String> defHistory) {
        slangTableModel.setRowCount(0);
        defTableModel.setRowCount(0);
        for(int i = 0; i < slangHistory.size(); i++) {
            slangTableModel.addRow(new Object[]{slangHistory.get(i)});
        }
        for(int i = 0; i < defHistory.size(); i++) {
            defTableModel.addRow(new Object[]{defHistory.get(i)});
        }
    }
    public void addGoBackBtnListener(ActionListener listener) {
        goBackBtn.addActionListener(listener);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                HistoryScreen screen = new HistoryScreen();
            }
        });
    }

}
