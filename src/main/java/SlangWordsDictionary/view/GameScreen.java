package SlangWordsDictionary.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Objects;
import java.util.Random;

public class GameScreen extends JFrame {
    private JTextArea res;
    private JTextArea target;
    private JButton option_1;
    private JButton option_2;
    private JButton option_3;
    private JButton option_4;
    private JButton goBackBtn;
    private final Font textFont;
    private final String mode;
    private String key;

    public void setResult(String _res) {
        res.setText(_res);
    }
    public void setTarget(String _target) {
        target.setText(_target);
    }
    public String getTarget() {
        return target.getText();
    }
    public String getOption_1_Text() {
        return option_1.getText();
    }
    public String getOption_2_Text() {
        return option_2.getText();
    }
    public String getOption_3_Text() {
        return option_3.getText();
    }
    public String getOption_4_Text() {
        return option_4.getText();
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setOptionsContent(String optKey, String optA, String optB, String optC) {
        Random random = new Random();
        int keyPos = random.nextInt(4) + 1;
        switch (keyPos) {
            case 1:
                option_1.setText(optKey);
                option_2.setText(optA);
                option_3.setText(optB);
                option_4.setText(optC);
                break;
            case 2:
                option_1.setText(optA);
                option_2.setText(optKey);
                option_3.setText(optB);
                option_4.setText(optC);
                break;
            case 3:
                option_1.setText(optA);
                option_2.setText(optB);
                option_3.setText(optKey);
                option_4.setText(optC);
                break;
            case 4:
                option_1.setText(optA);
                option_2.setText(optB);
                option_3.setText(optC);
                option_4.setText(optKey);
                break;
            default:
                break;
        }
    }
    public GameScreen(String mode) {
        this.mode = mode;
        this.textFont = new Font("Arial", Font.BOLD, 18);
        setTitle("Guessing game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setDefaultLookAndFeelDecorated(true);
        setPreferredSize(new Dimension(700, 500));
        add(createMainPanel());
        setCursorCenter();
        pack();
//        setVisible(true);
    }
    private JPanel createMainPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);

        JPanel headingPn = createHeadingPanel();
        headingPn.setSize(700,30);
        headingPn.setLocation(0, 30);
        mainPanel.add(headingPn);

        JPanel targetPn = createTargetPanel();
        targetPn.setSize(700, 30);
        targetPn.setLocation(0, 70);
        mainPanel.add(targetPn);

        JPanel btnPn = createBtnPanel();
        btnPn.setSize(500,150);
        btnPn.setLocation(200,120);
        mainPanel.add(btnPn);

        JPanel resPn = createResPanel();
        resPn.setSize(700, 30);
        resPn.setLocation(0, 300);
        mainPanel.add(resPn);

        goBackBtn = new JButton("GO BACK");
        goBackBtn.setSize(300,30);
        goBackBtn.setLocation(200,350);
        mainPanel.add(goBackBtn);

        return mainPanel;
    }
    private JPanel createHeadingPanel() {
        JPanel headingPn = new JPanel();
        Font textFont = new Font("Arial", Font.BOLD, 20);
        if(Objects.equals(this.mode, "guessSlang")) {
            JLabel label = new JLabel("Guess slang matching the definitions");
            label.setFont(textFont);
            headingPn.add(label);
        } else if (Objects.equals(this.mode, "guessDef")) {
            JLabel label = new JLabel("Guess definitions matching the slang");
            label.setFont(textFont);
            headingPn.add(label);
        }
        return headingPn;
    }
    private JPanel createTargetPanel() {
        JPanel targetPn = new JPanel();
        target = new JTextArea();
//        target.setText("Something");
        target.setFont(textFont);
        targetPn.add(target);
        return targetPn;
    }
    private JPanel createBtnPanel() {
        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(null);
        option_1 = new JButton("Slang 1");
        option_2 = new JButton("Slang 2");
        option_3 = new JButton("Slang 3");
        option_4 = new JButton("Slang 4");

        option_1.setSize(300,30);
        option_2.setSize(300,30);
        option_3.setSize(300,30);
        option_4.setSize(300,30);

        option_1.setLocation(0,0);
        option_2.setLocation(0,40);
        option_3.setLocation(0,80);
        option_4.setLocation(0,120);

        btnPanel.add(option_1);
        btnPanel.add(option_2);
        btnPanel.add(option_3);
        btnPanel.add(option_4);
        return btnPanel;
    }
    private JPanel createResPanel() {
        JPanel resPn = new JPanel();
        res = new JTextArea();
//        res.setText("Something");
        res.setFont(textFont);
        res.setPreferredSize(new Dimension(300,70));
        resPn.add(res);
        return resPn;
    }
    private void setCursorCenter() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - 700) / 2;
        int y = (screenSize.height - 500) / 2;
        setLocation(x, y);
    }
    public void addOption1BtnListener(ActionListener listener) {
        option_1.addActionListener(listener);
    }
    public void addOption2BtnListener(ActionListener listener) {
        option_2.addActionListener(listener);
    }
    public void addOption3BtnListener(ActionListener listener) {
        option_3.addActionListener(listener);
    }
    public void addOption4BtnListener(ActionListener listener) {
        option_4.addActionListener(listener);
    }
    public void addGoBackBtnListener(ActionListener listener) {
        goBackBtn.addActionListener(listener);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                GameScreen mainScreen = new GameScreen("guessDef");
            }
        });
    }


}
