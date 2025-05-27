package pieces;

import coordinate.Coordinate;
import move.AttackMove;
import move.Move;
import move.NonAttackMove;
import player.Alliance;
import tile.Board;

import java.util.ArrayList;

public class Pawn extends Piece
{
	public Pawn(final PieceType pieceType,
			final Coordinate piecePosition,
			final Alliance alliance)
	{
		super(pieceType, piecePosition, alliance);
	}

	@Override
	public String toString() {
		return "";
	}

	@Override
	public ArrayList<Move> legalMoves(final Board board)
	{
		final ArrayList<Move> LegalMoves = new ArrayList<>();
		Coordinate destinationSquare;
		final int y = this.getAlliance().getDirection();

		//forward
		destinationSquare = new Coordinate(this.piecePosition.X, this.piecePosition.Y+y);
		if(destinationSquare.inRange())
			if(board.isEmptyTile(destinationSquare))
				LegalMoves.add(new NonAttackMove(board, this, destinationSquare));
		
		//attack-left
		destinationSquare = new Coordinate(this.piecePosition.X-1, this.piecePosition.Y+y);
		if(destinationSquare.inRange()
				&& !board.isEmptyTile(destinationSquare))
			if(board.getPiece(destinationSquare).getAlliance()!=this.alliance)
				LegalMoves.add(new AttackMove(board, this, destinationSquare, board.getPiece(destinationSquare)));
			
		//Attack-right
		destinationSquare = new Coordinate(this.piecePosition.X+1, this.piecePosition.Y+y);
		if(destinationSquare.inRange()
				&& !board.isEmptyTile(destinationSquare))
			if(board.getPiece(destinationSquare).getAlliance()!=this.alliance)
				LegalMoves.add(new AttackMove(board, this, destinationSquare, board.getPiece(destinationSquare)));
			
		//initial-move
		destinationSquare = new Coordinate(this.piecePosition.X, this.piecePosition.Y+y+y);
		if(destinationSquare.inRange())
			if(board.isEmptyTile(destinationSquare)
						&& board.isEmptyTile(new Coordinate(piecePosition.X, piecePosition.Y+y))
						&& this.isInitialMove())
			{
				LegalMoves.add(new NonAttackMove(board, this, destinationSquare));
			}
		return LegalMoves;
	}

	@Override
	public Piece movePiece(final Coordinate destinationCoordinate)
	{
		/* TODO Auto-generated method stub */
		Piece piece = new Pawn(PieceType.PAWN, destinationCoordinate, this.alliance);
		piece.initialMoveMade();
		return piece;
	}
}
