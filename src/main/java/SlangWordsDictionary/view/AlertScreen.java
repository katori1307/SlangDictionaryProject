package SlangWordsDictionary.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Objects;

public class AlertScreen extends JFrame {
    private JButton confirmBtn;
    private JButton duplicateBtn;
    private JButton overWriteBtn;
    private JButton goBackBtn;
    private JTextArea slangText;
    private JTextArea defText;
    private final String mode;
    public void setTextContent(String slang, String def) {
        slangText.setText(slang);
        defText.setText(def);
    }
    public AlertScreen(String mode) {
        this.mode = mode;
        setTitle("Alert Screen");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setDefaultLookAndFeelDecorated(true);
        setPreferredSize(new Dimension(400,200));
        add(createMainPanel());
        setCursorCenter();
        pack();
//        setVisible(true);
    }
    private JPanel createMainPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);

        JPanel headingPn = createHeadingPanel();
        headingPn.setSize(300,30);
        headingPn.setLocation(50, 10);
        mainPanel.add(headingPn);
        if (!Objects.equals(this.mode, "randomSlang")) {
            JPanel btnPanel = createBtnPanel();
            btnPanel.setSize(300, 150);
            if (Objects.equals(this.mode, "duplicateSlang")) {
                btnPanel.setLocation(50, 50);
            } else if (Objects.equals(this.mode, "confirmDel") || Objects.equals(this.mode, "confirmReset")) {
                btnPanel.setLocation(50, 70);
            }
            mainPanel.add(btnPanel);
        } else {
            JPanel randomPn = createRandomSlangPanel();
            randomPn.setSize(300,80);
            randomPn.setLocation(30, 40);
            goBackBtn = new JButton("GO BACK");
            goBackBtn.setSize(100, 30);
            goBackBtn.setLocation(150, 120);
            mainPanel.add(randomPn);
            mainPanel.add(goBackBtn);
        }
        return mainPanel;
    }
    private JPanel createHeadingPanel() {
        JPanel headingPn = new JPanel();
        Font textFont = new Font("Arial", Font.BOLD, 18);
        if(Objects.equals(this.mode, "duplicateSlang")) {
            JLabel label = new JLabel("Your slang already existed !");
            label.setFont(textFont);
            headingPn.add(label);
        } else if (Objects.equals(this.mode, "confirmDel")) {
            JLabel label = new JLabel("Are your sure to delete slang ?");
            label.setFont(textFont);
            headingPn.add(label);
        } else if (Objects.equals(this.mode, "confirmReset")) {
            JLabel label = new JLabel("Confirm reset slang dictionary ?");
            label.setFont(textFont);
            headingPn.add(label);
        } else if (Objects.equals(this.mode, "randomSlang")) {
            JLabel label = new JLabel("Random slang");
            label.setFont(textFont);
            headingPn.add(label);
        }
        return headingPn;
    }
    private JPanel createRandomSlangPanel() {
        JPanel rdPanel = new JPanel();
        Font textFont = new Font("Arial", Font.BOLD, 15);
        rdPanel.setLayout(null);
        JLabel slangLb = new JLabel("Slang:");
        slangLb.setFont(textFont);
        slangLb.setSize(90, 30);
        slangLb.setLocation(0, 0);
        JLabel defLb = new JLabel("Definitions:");
        defLb.setFont(textFont);
        defLb.setSize(90, 20);
        defLb.setLocation(0, 30);
        rdPanel.add(slangLb);
        rdPanel.add(defLb);

        slangText = new JTextArea("");
        slangText.setFont(new Font("Arial", Font.ITALIC, 14));
        slangText.setLocation(100, 0);
        slangText.setSize(200, 20);
        defText = new JTextArea("");
        defText.setFont(new Font("Arial", Font.ITALIC, 14));
        defText.setLocation(100, 30);
        defText.setSize(200, 20);
        rdPanel.add(slangText);
        rdPanel.add(defText);

        return rdPanel;
    }
    private JPanel createBtnPanel() {
        JPanel btnPanel = new JPanel();
        if(Objects.equals(this.mode, "duplicateSlang")) {
            duplicateBtn = new JButton("DUPLICATE SLANG");
            overWriteBtn = new JButton("OVERWRITE SLANG");
            goBackBtn = new JButton("GO BACK");
            btnPanel.add(goBackBtn);
            btnPanel.add(duplicateBtn);
            btnPanel.add(overWriteBtn);
        } else if(Objects.equals(this.mode, "confirmDel")) {
            confirmBtn = new JButton("CONFIRM DELETE");
            btnPanel.add(confirmBtn);
            goBackBtn = new JButton("GO BACK");
            btnPanel.add(goBackBtn);
        } else if(Objects.equals(this.mode, "confirmReset")) {
            confirmBtn = new JButton("RESET DICTIONARY");
            btnPanel.add(confirmBtn);
            goBackBtn = new JButton("GO BACK");
            btnPanel.add(goBackBtn);
        }
        return btnPanel;
    }

    private void setCursorCenter() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - 400) / 2;
        int y = (screenSize.height - 200) / 2;
        setLocation(x, y);
    }
    public void addDuplicateBtnListener(ActionListener listener) {
        duplicateBtn.addActionListener(listener);
    }
    public void addOverwriteBtnListener(ActionListener listener) {
        overWriteBtn.addActionListener(listener);
    }
    public void addConfirmBtnListener(ActionListener listener) {
        confirmBtn.addActionListener(listener);
    }
    public void addGoBackBtnListener(ActionListener listener) {
        goBackBtn.addActionListener(listener);
    }


//    public static void main(String[] args) {
//        javax.swing.SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                AlertScreen screen = new AlertScreen("randomSlang");
//            }
//        });
//    }
}
