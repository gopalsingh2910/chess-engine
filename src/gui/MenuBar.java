package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MenuBar extends JMenuBar
{
	public JChess chess;

	public MenuBar(final JChess chess)
	{
		this.chess = chess;
		this.setBackground(Color.WHITE);
		createMenuBar();
	}

	public void createMenuBar()
	{
		this.add(fileMenu());
	}

	public JMenu fileMenu()
	{
		JMenu fileMenu = new JMenu("File");
		fileMenu.setAlignmentX(Component.LEFT_ALIGNMENT);
		fileMenu.setAlignmentY(Component.TOP_ALIGNMENT);
		fileMenu.setForeground(Color.BLACK);

		JMenuItem newGame = new JMenuItem("New Game");
		newGame.addActionListener(e ->
		{
			chess.frame.setVisible(false);
			new Setup();
		});
		fileMenu.add(newGame);

		JMenuItem restart = new JMenuItem("Restart");
		restart.addActionListener(this::actionPerformed);
		fileMenu.add(restart);

		JMenuItem flipBoard = new JMenuItem("Flip");
		flipBoard.addActionListener(e -> {
			if (chess.chessBoard.currentPlayer().getAlliance().isWhite()) chess.flipMode = !chess.flipMode;
			else JOptionPane.showMessageDialog(null, "Try when it is Black to play!");
		});
		fileMenu.add(flipBoard);

		JSeparator fileSeparator = new JSeparator();
		fileMenu.add(fileSeparator);
		
		JMenuItem exit = new JMenuItem("Close");
		exit.addActionListener(e -> System.exit(0));
		fileMenu.add(exit);
		
		return fileMenu;
	}

	private void actionPerformed(ActionEvent e) {
		chess.frame.setVisible(false);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException ex) {
			System.out.println(ex.getMessage());
		}
		chess.frame.setVisible(true);
	}
}