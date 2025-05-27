package tile;

import coordinate.Coordinate;
import move.Move;
import pieces.Piece;
import player.Alliance;
import player.PlayerType;

import java.util.Map;

public class Builder
{
    public final Map<Integer, Piece> boardConfig;
    public Alliance nextMoveMaker;
    public PlayerType blackPlayer = null;
    public PlayerType whitePlayer = null;
    public final Move lastMove;

    public Builder(final Move move)
    {
        this.boardConfig = Board.generateBoard();
        this.lastMove = move;
    }

    public void setPiece(final Coordinate piecePosition,
                         final Piece piece)
    {
        if(piece == null || piecePosition == null) return;
        this.boardConfig.put(piecePosition.position(), piece);
    }

    public void setMoveMaker(final Alliance alliance)
    {
        this.nextMoveMaker = alliance;
    }

    public Board getBoard()
    {
        return new Board(this);
    }

    public void setPlayerType(final PlayerType whitePlayerType,
                              final PlayerType blackPlayerType)
    {
        this.whitePlayer = whitePlayerType;
        this.blackPlayer = blackPlayerType;
    }

    public Move getLastMove()
    {
        return this.lastMove;
    }
}
