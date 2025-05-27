package move;

import java.util.ArrayList;

import coordinate.*;
import pieces.Piece;
import player.Alliance;
import player.MoveTransition;
import tile.Board;

public abstract class Move
{
	public final Coordinate destinationPosition;
	public final Piece piece;
	public final Piece attackedPiece;
	public final Board board;
	
	public static final int QUEEN_SIDE = 1;
	public static final int KING_SIDE = -1;
	
	public Move(final Board board,
			final Piece piece,
			final Coordinate destinationPosition,
			final Piece attackedPiece)
	{
		this.board = board;
		this.piece = piece;
		this.destinationPosition = destinationPosition;
		this.attackedPiece = attackedPiece;
	}

	public abstract Piece pieceToMove();

	public abstract MoveTransition execute();

	public abstract Piece attackedPiece();

	public abstract Piece getRook();

	public abstract Coordinate destinationCoordinate();

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Move other = (Move) obj;
		if (attackedPiece == null) {
			if (other.attackedPiece != null)
				return false;
		} else if (!attackedPiece.equals(other.attackedPiece))
			return false;
		if (destinationPosition == null) {
			if (other.destinationPosition != null)
				return false;
		} else if (!destinationPosition.equals(other.destinationPosition))
			return false;
		if (piece == null)
		{
			return other.piece == null;
		} else return piece.equals(other.piece);
	}

	public static ArrayList<Move> possibleMoves(final Board board, 
												final Alliance alliance)
	{
		ArrayList<Move> moves = new ArrayList<>();
		
		for(Coordinate coordinate : Coordinate.MAX_TILES)
			if(board.getPiece(coordinate)!=null)
				if (board.getPiece(coordinate).getAlliance()==alliance)
					moves.addAll(board.getPiece(coordinate).legalMoves(board));
		return moves;
	}

	public static Move createMove(final Piece piece,
								  final Coordinate tileCoordinate,
								  final ArrayList<Move> moves)
	{
		if(tileCoordinate == null || moves.isEmpty())
			return null;
		else
			for(final Move move : moves)
				if(move.pieceToMove().equals(piece)
						&& move.destinationCoordinate().equals(tileCoordinate))
					return move;
		return null;
	}

    public abstract boolean isPawnPromoting();
}
