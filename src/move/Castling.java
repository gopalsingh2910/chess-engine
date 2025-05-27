package move;

import coordinate.Coordinate;
import pieces.Piece;
import player.Alliance;
import player.MoveStatus;
import player.MoveTransition;
import tile.Board;
import tile.Builder;

public class Castling extends Move
{
    final Integer castlingSide;
    final Piece rook;

    public Castling(final Board board,
                    final Piece piece,
                    final Piece rook,
                    final Integer castlingSide,
                    final Coordinate destinationPosition)
    {
        super(board, piece, destinationPosition, null);
        this.castlingSide = castlingSide;
        this.rook = rook;
    }

    @Override
    public Piece pieceToMove()
    {
        return this.piece;
    }

    @Override
    public Piece getRook()
    {
        return this.rook;
    }

    @Override
    public Piece attackedPiece()
    {
        return null;
    }

    @Override
    public Coordinate destinationCoordinate()
    {
        return this.destinationPosition;
    }


    @Override
    public boolean isPawnPromoting()
    {
        return false;
    }

    @Override
    public MoveTransition execute()
    {
        if (!(this.board.currentPlayer().isLegalMove(this)))
            return new MoveTransition(this.board, null, MoveStatus.INVALID);
        Builder builder = new Builder(this);
        for (Coordinate coordinate : Coordinate.MAX_TILES)
            if (this.board.getPiece(coordinate) != null
                    && !this.board.getPiece(coordinate).equals(this.pieceToMove())
                    && !this.board.getPiece(coordinate).equals(this.getRook()))
                builder.setPiece(coordinate, this.board.getPiece(coordinate));
        builder.setPiece(this.pieceToMove().piecePosition(), null);
        builder.setPiece(new Coordinate(this.castlingSide == Move.KING_SIDE ? 0:7,
                        this.piece.getAlliance().isBlack() ? 0:7), null);
        builder.setPiece(this.destinationCoordinate(),
                this.pieceToMove().movePiece(this.destinationCoordinate()));
        builder.setPiece(this.getRook().piecePosition(), this.getRook());
        builder.setMoveMaker(this.pieceToMove().getAlliance().opponentAlliance());
        builder.setPlayerType(this.board.getPlayer(Alliance.WHITE).playerType(),
                this.board.getPlayer(Alliance.BLACK).playerType());
        if (builder.getBoard().currentPlayer().isInCheck())
            return new MoveTransition(this.board, null, MoveStatus.ILLEGAL);
        return new MoveTransition(builder.getBoard(), this, MoveStatus.LEGAL);
    }
}
