package move;

import coordinate.Coordinate;
import pieces.Piece;
import player.Alliance;
import player.MoveStatus;
import player.MoveTransition;
import tile.Board;
import tile.Builder;

public class NonAttackMove extends Move
{
    public NonAttackMove(final Board board,
                         final Piece piece,
                         final Coordinate destinationPosition)
    {
        super(board, piece, destinationPosition, null);
    }

    @Override
    public Piece pieceToMove()
    {
        return this.piece;
    }

    @Override
    public Piece getRook()
    {
        return null;
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
        return (pieceToMove().pieceType().isPawn()
                && destinationCoordinate().getY() == (pieceToMove().getAlliance().isWhite() ? 0:7));
    }

    @Override
    public MoveTransition execute()
    {
        if (!(this.board.currentPlayer().isLegalMove(this)))
            return new MoveTransition(this.board, null, MoveStatus.INVALID);
        Builder builder = new Builder(this);
        for (Coordinate coordinate : Coordinate.MAX_TILES)
            if (this.board.getPiece(coordinate) != null
                && !this.board.getPiece(coordinate).equals(this.pieceToMove()))
                builder.setPiece(coordinate, this.board.getPiece(coordinate));
        builder.setPiece(this.pieceToMove().piecePosition(), null);
        builder.setPiece(this.destinationCoordinate(),
                this.pieceToMove().movePiece(this.destinationCoordinate()));
        builder.setMoveMaker(this.pieceToMove().getAlliance().opponentAlliance());
        builder.setPlayerType(this.board.getPlayer(Alliance.WHITE).playerType(),
                this.board.getPlayer(Alliance.BLACK).playerType());
        if (builder.getBoard().currentPlayer().isInCheck())
            return new MoveTransition(this.board, null, MoveStatus.ILLEGAL);
        return new MoveTransition(builder.getBoard(), this, MoveStatus.LEGAL);
    }
}
