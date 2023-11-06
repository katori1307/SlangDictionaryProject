package SlangWordsDictionary.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Objects;

public class ConfirmScreen extends JFrame {
    private JButton confirmBtn;
    private JButton duplicateBtn;
    private JButton overWriteBtn;
    private String mode;
    public ConfirmScreen(String mode) {
        this.mode = mode;
        setTitle("Confirm Screen");
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

        JPanel btnPanel = createBtnPanel();
        btnPanel.setSize(300, 150);
        if(Objects.equals(this.mode, "duplicateSlang")) {
            btnPanel.setLocation(50, 50);
        } else if(Objects.equals(this.mode, "confirmDel")) {
            btnPanel.setLocation(50,70);
        }
        mainPanel.add(btnPanel);
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
        }
        return headingPn;
    }
    private JPanel createBtnPanel() {
        JPanel btnPanel = new JPanel();
        if(Objects.equals(this.mode, "duplicateSlang")) {

            duplicateBtn = new JButton("DUPLICATE SLANG");
            overWriteBtn = new JButton("OVERWRITE SLANG");
            btnPanel.add(duplicateBtn);
            btnPanel.add(overWriteBtn);
        } else if(Objects.equals(this.mode, "confirmDel")) {
            confirmBtn = new JButton("CONFIRM DELETE");
            btnPanel.add(confirmBtn);
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

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ConfirmScreen screen = new ConfirmScreen("confirmDel");
            }
        });
    }
}
