package SlangWordsDictionary.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SlangDictionaryScreen extends JFrame {

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
        JButton searchSlangButton = new JButton("Search slang");
        JButton searchDefinitionButton = new JButton("Search definition");
        JButton showHistoryButton = new JButton("Show history");
        JButton addNewSlangButton = new JButton("Add new slang");
        JButton editSlangButton = new JButton("Edit slang");
        JButton deleteSlangButton = new JButton("Delete slang");
        JButton resetSlangDictionaryButton = new JButton("Reset slang dictionary");
        JButton randomSlangButton = new JButton("Random slang");
        JButton miniGameButton = new JButton("Mini game with slang");
        menuPanel.add(searchSlangButton);
        menuPanel.add(searchDefinitionButton);
        menuPanel.add(showHistoryButton);
        menuPanel.add(addNewSlangButton);
        menuPanel.add(editSlangButton);
        menuPanel.add(deleteSlangButton);
        menuPanel.add(resetSlangDictionaryButton);
        menuPanel.add(randomSlangButton);
        menuPanel.add(miniGameButton);

        handleButtonEvent(searchSlangButton);

        return menuPanel;
    }

    private void handleButtonEvent(JButton btn) {
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SearchSlangScreen searchSlangScreen = new SearchSlangScreen();
                searchSlangScreen.setVisible(true);
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
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                SlangDictionaryScreen mainScreen = new SlangDictionaryScreen();
            }
        });
    }



}
