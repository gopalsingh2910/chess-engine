package pieces;

import coordinate.Coordinate;
import move.Move;
import player.Alliance;
import tile.Board;

import java.util.ArrayList;

public abstract class Piece
{
	public final PieceType pieceType;
	public final Coordinate piecePosition;
	public final Alliance alliance;
	
	private boolean initialMove = true;
	
	public Piece(final PieceType pieceType,
			final Coordinate piecePosition,
			final Alliance alliance)
	{
		this.pieceType = pieceType;
		this.piecePosition = piecePosition;
		this.alliance = alliance;
	}

	public abstract String toString();
	
	public PieceType pieceType()
	{
		return this.pieceType;
	}

	public Coordinate piecePosition()
	{
		return this.piecePosition;
	}
	
	public Alliance getAlliance()
	{
		return this.alliance;
	}

	public void initialMoveMade()
	{
		this.initialMove = false;
	}

	public boolean isInitialMove()
	{
		return this.initialMove;
	}
	
	public abstract ArrayList<Move> legalMoves(final Board board);
	
	public abstract Piece movePiece(Coordinate destinationCoordinate);

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Piece other = (Piece) obj;
		if (this.alliance != other.getAlliance())
			return false;
		if (this.piecePosition == null) {
			if (other.piecePosition != null)
				return false;
		} else if (!this.piecePosition.equals(other.piecePosition()))
			return false;
		return this.pieceType == other.pieceType();
	}
}
