package SlangWordsDictionary.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Objects;

public class AddAndEditSlangScreen extends JFrame {
    private JButton addBtn;
    private JButton editBtn;
    private JButton goBackBtn;
    private JTextField inputSlang;
    private JTextField inputDef;
    private JTextField inputNewSlang;
    private DefaultTableModel tableModel;
    private String mode;
    public AddAndEditSlangScreen(String mode) {
        this.mode = mode;
        setTitle("Add Slang");
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
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(createHeadingPanel(), BorderLayout.NORTH);
        mainPanel.add(createFormControl(), BorderLayout.CENTER);

        return mainPanel;
    }
    private void setCursorCenter() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - 600) / 2;
        int y = (screenSize.height - 400) / 2;
        setLocation(x, y);
    }
    private JPanel createHeadingPanel() {
        JPanel headingPanel = new JPanel();
        JLabel label = null;
        if(Objects.equals(this.mode, "add")) {
            label = new JLabel("Input a slang and its definitions to add");
        } else if(Objects.equals(this.mode, "edit")) {
            label = new JLabel("Input a slang to edit");
        }
        label.setFont(new Font("Arial", Font.BOLD, 18));
        headingPanel.add(label);
        return headingPanel;
    }
    private JPanel createFormControl() {
        JPanel formPanel = new JPanel();
        formPanel.setLayout(null);
        inputSlang = new JTextField(20);
        inputDef = new JTextField(20);
        inputNewSlang = new JTextField(20);
        if(Objects.equals(this.mode, "add")) {
            JPanel inputSlangPn = createInput("New Slang:", inputSlang);
            inputSlangPn.setSize(400, 30);
            inputSlangPn.setLocation(100, 10);
            JPanel inputDefPn = createInput("New Definitions:", inputDef);
            inputDefPn.setSize(400, 30);
            inputDefPn.setLocation(81, 35);
            formPanel.add(inputSlangPn);
            formPanel.add(inputDefPn);
            addBtn = new JButton("ADD SLANG");
            addBtn.setLocation(150, 80);
            addBtn.setSize(125,30);
            formPanel.add(addBtn);
        } else if(Objects.equals(this.mode, "edit")) {
            JPanel inputSlangPn = createInput("Slang to edit:", inputSlang);
            inputSlangPn.setSize(400, 25);
            inputSlangPn.setLocation(100, 0);
            JPanel editedSlangPn = createInput("Edit Slang:", inputNewSlang);
            editedSlangPn.setSize(400, 25);
            editedSlangPn.setLocation(110,25);
            JPanel editedDefPn = createInput("Edit Definitions:", inputDef);
            editedDefPn.setSize(400, 25);
            editedDefPn.setLocation(90, 50);
            formPanel.add(editedSlangPn);
            formPanel.add(inputSlangPn);
            formPanel.add(editedDefPn);
            editBtn = new JButton("EDIT SLANG");
            editBtn.setLocation(150, 80);
            editBtn.setSize(125,30);
            formPanel.add(editBtn);
        }

        goBackBtn = new JButton("GO BACK");
        goBackBtn.setLocation(300, 80);
        goBackBtn.setSize(125, 30);

        formPanel.add(goBackBtn);
        JPanel tablePanel = createResTable();
        tablePanel.setLocation(0, 110);
        tablePanel.setSize(600, 300);
        formPanel.add(tablePanel);

        return formPanel;
    }
    private JPanel createInput(String labelContent, JTextField textField) {
        JPanel inputPanel = new JPanel();
        JLabel label = new JLabel(labelContent);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        inputPanel.add(label);
        inputPanel.add(textField);
        return inputPanel;
    }

    private JPanel createResTable() {
        JPanel tablePanel = new JPanel();
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Slang added");
        tableModel.addColumn("Definition added");
        JTable resTable = new JTable(tableModel);
        resTable.getTableHeader().setResizingAllowed(false);
        resTable.getTableHeader().setReorderingAllowed(false);
        resTable.getColumnModel().getColumn(0).setPreferredWidth(200);
        resTable.getColumnModel().getColumn(1).setPreferredWidth(400);
        JScrollPane scrollPane = new JScrollPane(resTable);
        scrollPane.setPreferredSize(new Dimension(500, 200));
        tablePanel.add(scrollPane);

        return tablePanel;
    }
    public void addGoBackBtnListener(ActionListener listener) {
        goBackBtn.addActionListener(listener);
    }
    public void addAddSlangBtnListener(ActionListener listener) {
        addBtn.addActionListener(listener);
    }
    public String getInputSlang() {
        return inputSlang.getText();
    }
    public String getInputDefinition() {
        return inputDef.getText();
    }
    public void clearTableData() {
        tableModel.setRowCount(0);
    }
    public void addSlangToTable(String slang, String def) {
        tableModel.addRow(new Object[]{slang, def});
    }
//    public static void main(String[] args) {
//        javax.swing.SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                AddSlangScreen screen = new AddSlangScreen();
//            }
//        });
//    }
}
