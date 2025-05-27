package gui;

import coordinate.Coordinate;
import move.Move;
import pieces.*;
import player.Alliance;
import player.MoveStatus;
import player.MoveTransition;
import tile.Board;
import tile.Builder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static gui.Frame.createFrame;

public class PawnPromotion
{
    private final Piece sourceTile;
    private Board chessBoard;
    private static final Dimension PROMOTION_PANE_DIMENSION = new Dimension(150, 150);

    PawnPromotion(final Board chessBoard,
                  final Piece sourceTile)
    {
        this.chessBoard = chessBoard;
        this.sourceTile = sourceTile;
    }

    public void promotePawn(final Move move)
    {
        Builder builder = new Builder(move);
        for(Coordinate coordinate : Coordinate.MAX_TILES)
            if(chessBoard.getPiece(coordinate) != null)
                if (!chessBoard.getPiece(coordinate).equals(sourceTile))
                    builder.setPiece(coordinate, chessBoard.getPiece(coordinate));

        JFrame promotionFrame = createFrame(PROMOTION_PANE_DIMENSION);
        promotionFrame.setVisible(true);
        promotionFrame.setLayout(new BorderLayout());

        JPanel pieces = new JPanel();
        pieces.setLayout(new GridLayout(0, 1));

        JRadioButton queen = new JRadioButton("Queen");
        queen.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                builder.setPiece(sourceTile.piecePosition(),
                        new Queen(PieceType.QUEEN, sourceTile.piecePosition(), sourceTile.getAlliance()));
            }
        });
        pieces.add(queen);

        JRadioButton bishop = new JRadioButton("Bishop");
        bishop.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                builder.setPiece(sourceTile.piecePosition(),
                        new Bishop(PieceType.BISHOP, sourceTile.piecePosition(), sourceTile.getAlliance()));
            }
        });
        pieces.add(bishop);

        JRadioButton knight = new JRadioButton("Knight");
        knight.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                builder.setPiece(sourceTile.piecePosition(),
                        new Knight(PieceType.KNIGHT, sourceTile.piecePosition(), sourceTile.getAlliance()));
            }
        });
        pieces.add(knight);

        JRadioButton rook = new JRadioButton("Rook");
        rook.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                builder.setPiece(sourceTile.piecePosition(),
                        new Rook(PieceType.ROOK, sourceTile.piecePosition(), sourceTile.getAlliance()));
            }
        });
        pieces.add(rook);

        promotionFrame.add(pieces, BorderLayout.CENTER);
        JButton selected = new JButton("Selected");
        selected.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                promotionFrame.setVisible(false);
                chessBoard = builder.getBoard();
            }
        });
    }

    public static MoveTransition promotePawn(final Board board,
                                             final Move move)
    {
        Builder builder = new Builder(move);
        for (Coordinate coordinate : Coordinate.MAX_TILES)
        {
            if (board.getPiece(coordinate) != null)
                if (!(board.getPiece(coordinate).equals(move.pieceToMove())))
                    builder.setPiece(coordinate, board.getPiece(coordinate));
        }
        builder.setPiece(move.pieceToMove().piecePosition(), null);
        Piece queen = new Queen(PieceType.QUEEN, move.destinationCoordinate(), move.pieceToMove().getAlliance());
        builder.setPiece(move.destinationCoordinate(), queen);
        builder.setMoveMaker(move.pieceToMove().getAlliance().opponentAlliance());
        builder.setPlayerType(board.getPlayer(Alliance.WHITE).playerType(),
                board.getPlayer(Alliance.BLACK).playerType());
        return new MoveTransition(builder.getBoard(), move, MoveStatus.LEGAL);
    }
}
