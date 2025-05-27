package gui;

import player.PlayerType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Setup extends JPanel
{
    public int level = 2;
    public JFrame frame = new JFrame("Game Set-up");
    public PlayerType whitePlayerType = null;
    public PlayerType blackPlayerType = null;
    public static final Dimension FRAME_DIMENSION = new Dimension(300, 400);

    public Setup()
    {
        super(new GridLayout(0, 1));

        this.frame.setSize(FRAME_DIMENSION);
        this.frame.setLocationRelativeTo(null);
        this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.frame.setVisible(true);
        this.frame.setVisible(true);

        ButtonGroup group1 = new ButtonGroup();
        ButtonGroup group2 = new ButtonGroup();

        JLabel w = new JLabel("White:");
        w.setBackground(Color.WHITE);
        w.setFont(new Font("", Font.BOLD, 15));
        w.setBackground(Color.WHITE);
        this.add(w);
        JRadioButton whiteHuman = new JRadioButton("Human");
        whiteHuman.setBackground(Color.WHITE);
        JRadioButton whiteComputer = new JRadioButton("Computer");
        whiteComputer.setBackground(Color.WHITE);
        this.add(whiteHuman);
        group1.add(whiteHuman);
        this.add(whiteComputer);
        group1.add(whiteComputer);

        JLabel b = new JLabel("Black:");
        b.setBackground(Color.WHITE);
        b.setFont(new Font("", Font.BOLD, 15));
        b.setBackground(Color.WHITE);
        this.add(b);
        JRadioButton blackHuman = new JRadioButton("Human");
        blackHuman.setBackground(Color.WHITE);
        JRadioButton blackComputer = new JRadioButton("Computer");
        blackComputer.setBackground(Color.WHITE);
        this.add(blackHuman);
        group2.add(blackHuman);
        this.add(blackComputer);
        group2.add(blackComputer);

        ButtonGroup group3 = new ButtonGroup();

        JPanel levelPanel = new JPanel();
        levelPanel.setBackground(Color.WHITE);
        JRadioButton level1 = new JRadioButton("1");
        level1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                level = 1;
            }
        });
        levelPanel.add(level1);
        group3.add(level1);
        JRadioButton level2 = new JRadioButton("2");
        level2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                level = 2;
            }
        });
        levelPanel.add(level2);
        group3.add(level2);
        JRadioButton level3 = new JRadioButton("3");
        level3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                level = 3;
            }
        });
        levelPanel.add(level3);
        group3.add(level3);
        JRadioButton level4 = new JRadioButton("4");
        level4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                level = 4;
            }
        });
        levelPanel.add(level4);
        group3.add(level4);
        this.add(levelPanel);

        level1.setSelected(true);
        whiteHuman.setSelected(true);
        blackComputer.setSelected(true);

        JPanel panel = getjPanel(whiteHuman, blackHuman);
        this.add(panel);
        this.frame.add(this);
    }

    private JPanel getjPanel(JRadioButton whiteHuman, JRadioButton blackHuman) {
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(new FlowLayout());
        JButton OK = getjButton(whiteHuman, blackHuman);
        JButton defaultSetting = new JButton("default");
        defaultSetting.setBackground(Color.WHITE);
        defaultSetting.addActionListener(e -> {
            frame.setVisible(false);
            new JChess("JChess!", PlayerType.HUMAN, PlayerType.COMPUTER, 2);
        });
        JButton cancel = new JButton("Cancel");
        cancel.setBackground(Color.WHITE);
        cancel.addActionListener(e -> frame.setVisible(false));
        panel.add(OK);
        panel.add(defaultSetting);
        panel.add(cancel);
        return panel;
    }

    private JButton getjButton(JRadioButton whiteHuman, JRadioButton blackHuman) {
        JButton OK = new JButton("Ok");
        OK.setBackground(Color.WHITE);
        OK.addActionListener(e -> {
            whitePlayerType = whiteHuman.isSelected() ? PlayerType.HUMAN : PlayerType.COMPUTER;
            blackPlayerType = blackHuman.isSelected() ? PlayerType.HUMAN : PlayerType.COMPUTER;
            if (whiteHuman.isSelected() && blackHuman.isSelected()) level = 1;
            if (level > 0) {
                frame.setVisible(false);
                new JChess("JChess!", whitePlayerType, blackPlayerType, level + 1);
            } else JOptionPane.showMessageDialog(null, "Level not selected!");
        });
        return OK;
    }
}
