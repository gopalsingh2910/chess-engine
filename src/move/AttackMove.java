package move;

import coordinate.Coordinate;
import pieces.Piece;
import player.Alliance;
import player.MoveStatus;
import player.MoveTransition;
import tile.Board;
import tile.Builder;

public class AttackMove extends Move
{
    public AttackMove(final Board board,
                      final Piece piece,
                      final Coordinate destinationPosition,
                      final Piece attackedPiece)
    {
        super(board, piece, destinationPosition, attackedPiece);
    }

    @Override
    public Piece getRook()
    {
        return null;
    }

    @Override
    public Piece pieceToMove()
    {
        return this.piece;
    }

    @Override
    public Piece attackedPiece()
    {
        return this.attackedPiece;
    }

    @Override
    public Coordinate destinationCoordinate()
    {
        return this.destinationPosition;
    }

    @Override
    public boolean isPawnPromoting()
    {
        return (this.pieceToMove().pieceType().isPawn()
                && this.destinationCoordinate().getY() == (this.pieceToMove().getAlliance().isWhite() ? 0:7));
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
        builder.setPlayerType(this.board.getPlayer(Alliance.WHITE).playerType(),
                this.board.getPlayer(Alliance.BLACK).playerType());
        builder.setMoveMaker(this.pieceToMove().getAlliance().opponentAlliance());
        if (builder.getBoard().currentPlayer().isInCheck())
            return new MoveTransition(this.board, null, MoveStatus.ILLEGAL);
        return new MoveTransition(builder.getBoard(), this, MoveStatus.LEGAL);
    }
}
