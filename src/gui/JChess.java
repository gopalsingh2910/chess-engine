package gui;

import computer.MoveEvaluator;
import coordinate.Coordinate;
import move.Move;
import pieces.Piece;
import player.MoveStatus;
import player.MoveTransition;
import player.PlayerType;
import tile.Board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import static gui.Frame.createFrame;

public class JChess
{
	private final int level;
	public Board chessBoard;
	public JFrame frame = createFrame(BOARD_DIMENSION);
	public final JPanel tilePanel = new JPanel();
	public JMenuBar menuBar = new MenuBar(this);
	public boolean flipMode = false;
	private boolean hasToFlip = false;

	public ArrayList<Move> moves;
	public Piece sourceTile;
	public static final Dimension BOARD_DIMENSION = new Dimension(450, 500);
	public static final Dimension TILE_DIMENSION = new Dimension(50, 50);
	private static final int RUNTIME_SPEED = 500;
    private Move move = null;
	private boolean save = true;

	public JChess(final String title,
				  final PlayerType whitePlayer,
				  final PlayerType blackPlayer,
				  final int level)
	{
		this.level = level;
		this.chessBoard = Board.initializeBoard(whitePlayer, blackPlayer);
		this.frame.setVisible(true);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setTitle(title);
		this.frame.getContentPane().setLayout(new BorderLayout());
		this.frame.getContentPane().add(this.menuBar, BorderLayout.NORTH);
		this.frame.getContentPane().add(this.tilePanel, BorderLayout.CENTER);
		drawBoard();

		AutoPlay autoPlay = new AutoPlay();
		Timer timer = new Timer(RUNTIME_SPEED, autoPlay);
		timer.start();
	}

	private void drawBoard()
	{
		this.tilePanel.setLayout(new GridLayout(8, 8));
		for(Coordinate tileCoordinate : Coordinate.MAX_TILES)
			this.tilePanel.add(new TilePanel(tileCoordinate));
	}

	public void reDraw()
	{
		this.tilePanel.removeAll();
		for(Coordinate tileCoordinate : Coordinate.MAX_TILES)
			if (flipMode && hasToFlip) this.tilePanel.add(new TilePanel(tileCoordinate.flip()));
			else this.tilePanel.add(new TilePanel(tileCoordinate));
		this.tilePanel.validate();
		this.tilePanel.repaint();
		this.frame.repaint();
	}

	public class TilePanel extends JPanel
	{
		private static final long serialVersionUID = 1L;

		private final Coordinate tileCoordinate;

		public TilePanel(final Coordinate tileCoordinate)
		{
			super(new GridBagLayout());
			this.tileCoordinate = tileCoordinate;

			setPreferredSize(TILE_DIMENSION);
			setColor();
			setPiece();

			addMouseListener(new MouseAdapter()
			{
				@Override
				public void mouseClicked(MouseEvent e)
				{
					if (chessBoard.currentPlayer().isInCheckMate())
					    showMessage(chessBoard.currentPlayer().toString() + " is in Checkmate!");
					else if (chessBoard.currentPlayer().isInStaleMate())
					    showMessage(chessBoard.currentPlayer().toString() + " is in Stalemate!");
					if(sourceTile == null && chessBoard.getPiece(tileCoordinate) != null)
					{
						if(chessBoard.getPiece(tileCoordinate).getAlliance() == chessBoard.currentPlayer().getAlliance()
								&& chessBoard.currentPlayer().playerType().isHuman())
						{
							save = true;
							sourceTile = chessBoard.getPiece(tileCoordinate);
							moves = sourceTile.legalMoves(chessBoard);
						}
					}else if(sourceTile != null
							&& !Board.isEndGame(chessBoard.currentPlayer())
							&& (chessBoard.getPiece(tileCoordinate) == null
								|| chessBoard.getPiece(tileCoordinate).getAlliance()
									.isOpponent(chessBoard.currentPlayer().getAlliance())))
					{
						save = true;
						move = Move.createMove(sourceTile, tileCoordinate, moves);
						MoveTransition transition = new MoveTransition(null, null, MoveStatus.INVALID);
						if(move != null) transition = move.execute();

						if (transition.isLegalMove())
						{
							chessBoard = transition.transitionBoard();
							sourceTile = null;
							moves.clear();
							if (flipMode) hasToFlip = !hasToFlip;
							else hasToFlip = false;
                            assert move != null;
                            if (move.isPawnPromoting())
							{
								PawnPromotion pawnPromotion = new PawnPromotion(chessBoard, sourceTile);
								pawnPromotion.promotePawn(move);
							}
						} else
						{
							moves.clear();
							sourceTile = null;
							if (transition.moveStatus().isIlLegal()) showMessage("ILLegal move!");
							else if (transition.moveStatus().isInvalid()) showMessage("Invalid move!");
						}
					}else if (chessBoard.getPiece(tileCoordinate) != null)
					{
						moves.clear();
						sourceTile = chessBoard.getPiece(tileCoordinate);
						moves = sourceTile.legalMoves(chessBoard);
					}
				}
			});
			validate();
		}

		private void setPiece()
		{
			ImageIcon image = null;

			if(!(chessBoard.getPiece(this.tileCoordinate) == null))
			{
				String alliance = (chessBoard.getPiece(this.tileCoordinate).getAlliance().isWhite()) ? "w":"b";
				image = new ImageIcon(new ImageIcon("images\\"+alliance+chessBoard.getPiece(this.tileCoordinate)
						.pieceType().toString()+".png").getImage().getScaledInstance(40, 40, 40));
			}

			add(new JLabel(image));
		}

		private void setColor()
		{
			if(moves != null && !moves.isEmpty())
			{
				for(Move move : moves)
					if(move.destinationCoordinate().equals(this.tileCoordinate))
					{
						if(!chessBoard.isEmptyTile(move.destinationCoordinate()))
						{
							setBackground(new Color(0, 0, 150, 50));
							return;
						}
						setBackground(new Color(0, 0, 150, 50));
						return;
					}
			}
			else if (move != null) {
                if (this.tileCoordinate.equals(move.pieceToMove().piecePosition())) {
                    setBackground(new Color(0, 0, 150, 50));
                    return;
                }
                else if (this.tileCoordinate.equals(move.destinationCoordinate()))
                {
                    setBackground(new Color(0, 0, 150, 30));
                    return;
                }
            }

			if((this.tileCoordinate.getX()%2 == 0
                    && this.tileCoordinate.getY()%2 == 0)
					|| (this.tileCoordinate.getX()%2 == 1
                    && this.tileCoordinate.getY()%2 == 1))
			    setBackground(Color.WHITE);
			else setBackground(new Color(0xFFA220));
		}
	}

	public class AutoPlay implements ActionListener
	{
		@Override
		public void actionPerformed(final ActionEvent e)
		{
			if (save) {
				save = false;
				reDraw();
				if (Board.isEndGame(chessBoard.currentPlayer())) {
					JFrame _frame = Frame.createFrame(new Dimension(300, 200));
					_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					_frame.setVisible(true);
					_frame.setLayout(new BorderLayout(0, 1));
					_frame.add(new JLabel(chessBoard.currentPlayer().getAlliance() +
							" is in " + (chessBoard.currentPlayer().isInCheckMate() ? "Checkmate!" : "Stalemate!")),
                            BorderLayout.CENTER);
					JPanel panel = new JPanel(new FlowLayout());
					JButton OK = new JButton("Ok");
					OK.addActionListener(e1 -> System.exit(0));
					JButton newGame = new JButton("New Game");
					newGame.addActionListener(e12 -> new Setup());
					JButton cancel = new JButton("Cancel");
					cancel.addActionListener(e13 -> _frame.setVisible(false));
					panel.add(OK);
					panel.add(newGame);
					panel.add(cancel);
					_frame.add(panel, BorderLayout.SOUTH);
				}
			}
			if (chessBoard.currentPlayer().playerType().isComputer()
                    && !Board.isEndGame(chessBoard.currentPlayer()))
			{
				save = true;
				MoveEvaluator moveEvaluator = new MoveEvaluator();
				move = moveEvaluator.calculateMove(chessBoard, level);
				MoveTransition moveTransition = move.execute();
				if (moveTransition.isLegalMove()) {
					if (move.isPawnPromoting()) PawnPromotion.promotePawn(chessBoard, move);
					else chessBoard = moveTransition.transitionBoard();
					if (flipMode) hasToFlip = !hasToFlip;
					else hasToFlip = false;
				}
			}
		}
	}

	private void showMessage(final String message)
	{
		JOptionPane.showMessageDialog(this.frame, message);
	}

	public static void main(String[] args)
	{
		new Setup();
	}
}
