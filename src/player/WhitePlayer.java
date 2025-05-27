package player;

import move.Move;
import pieces.Piece;
import tile.Board;

import java.util.ArrayList;

public class WhitePlayer extends Player
{
    public WhitePlayer(final PlayerType playerType,
                       final Board board,
                       final ArrayList<Move> whiteLegalMoves,
                       final ArrayList<Move> blackLegalMoves)
    {
        super(playerType, board, whiteLegalMoves, blackLegalMoves);
    }

    @Override
    public PlayerType playerType()
    {
        return this.playerType;
    }

    @Override
    public Alliance getAlliance()
    {
        return Alliance.WHITE;
    }

    @Override
    public boolean isInCheck()
    {
        for (Move move :this.opponentPlayer().legalMoves())
            if (!(move.attackedPiece() == null)
                    && move.attackedPiece().pieceType().isKing())
                return true;
        return false;
    }

    @Override
    public boolean isInCheckMate()
    {
        if (!isInCheck()) return false;
        for (Move move : this.legalMoves()) {
            MoveTransition transition = move.execute();
            if (transition.isLegalMove())
                if (!(transition.transitionBoard().currentPlayer().isInCheck()))
                    return false;
        }
        return true;
    }

    @Override
    public boolean isInStaleMate()
    {
        if (isInCheck()) return false;
        for (Move move : this.legalMoves())
        {
            MoveTransition transition = move.execute();
            if (transition.isLegalMove())
                if (!(transition.transitionBoard().currentPlayer().isInCheck()))
                    return false;
        }
        return true;
    }

    @Override
    public ArrayList<Move> legalMoves()
    {
        return this.legalMoves;
    }

    @Override
    public Player opponentPlayer()
    {
        return new BlackPlayer(this.playerType.opponentPlayerType(), this.board, this.opponentMoves, this.legalMoves());
    }

    @Override
    public Piece getKing()
    {
        for(Piece piece : this.whiteActivePieces)
            if(piece!=null)
                if(piece.pieceType().isKing())
                    return piece;
        System.out.println("Not valid board!");
        return null;
    }

    @Override
    public boolean isLegalMove(final Move move)
    {
        return this.legalMoves.contains(move);
    }

    @Override
    public ArrayList<Piece> activePieces()
    {
        return this.whiteActivePieces;
    }
}
