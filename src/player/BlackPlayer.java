package player;

import move.Move;
import pieces.Piece;
import tile.Board;

import java.util.ArrayList;

public class BlackPlayer extends Player
{
    public BlackPlayer(final PlayerType playerType,
                       final Board board,
                       final ArrayList<Move> whiteLegalMoves,
                       final ArrayList<Move> blackLegalMoves)
    {
        super(playerType, board, blackLegalMoves, whiteLegalMoves);
    }

    @Override
    public Alliance getAlliance()
    {
        return Alliance.BLACK;
    }

    @Override
    public boolean isInCheck()
    {
        for (Move move : this.opponentPlayer().legalMoves())
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
        for (Move move : this.legalMoves()) {
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
    public PlayerType playerType()
    {
        return this.playerType;
    }

    @Override
    public Player opponentPlayer()
    {
        return new player.BlackPlayer(this.playerType.opponentPlayerType(), this.board, this.opponentMoves, this.legalMoves);
    }

    @Override
    public Piece getKing()
    {
        for(Piece piece : this.blackActivePieces)
            if(piece!=null)
                if(piece.pieceType().isKing())
                    return piece;
        System.out.println("Not valid Board to be played!");
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
        // TODO Auto-generated method stub
        return this.blackActivePieces;
    }
}
