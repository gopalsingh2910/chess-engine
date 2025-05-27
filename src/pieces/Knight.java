package pieces;

import coordinate.Coordinate;
import move.AttackMove;
import move.Move;
import move.NonAttackMove;
import player.Alliance;
import tile.Board;

import java.util.ArrayList;

public class Knight extends Piece
{
	public Knight(final PieceType pieceType,
			final Coordinate piecePosition,
			final Alliance alliance)
	{
		super(pieceType, piecePosition, alliance);
	}

	@Override
	public String toString()
	{
		return "Kn";
	}

	@Override
	public ArrayList<Move> legalMoves(final Board board)
	{
		final ArrayList<Move> LegalMoves = new ArrayList<>();
		Coordinate destinationSquare;

		//[-1, -2]
		destinationSquare = new Coordinate(this.piecePosition.X-1, this.piecePosition.Y-2);
		if(destinationSquare.inRange())
		{
			if(board.isEmptyTile(destinationSquare))
			{
				LegalMoves.add(new NonAttackMove(board, this, destinationSquare));
			}else if(board.getPiece(destinationSquare).getAlliance()!=this.alliance)
			{
				LegalMoves.add(new AttackMove(board, this, destinationSquare, board.getPiece(destinationSquare)));
			}
		}
		//[1, -2]
		destinationSquare = new Coordinate(this.piecePosition.X+1, this.piecePosition.Y-2);
		if(destinationSquare.inRange())
		{
			if(board.isEmptyTile(destinationSquare))
			{
				LegalMoves.add(new NonAttackMove(board, this, destinationSquare));
			}else if(board.getPiece(destinationSquare).getAlliance()!=this.alliance)
			{
				LegalMoves.add(new AttackMove(board, this, destinationSquare, board.getPiece(destinationSquare)));
			}
		}
		//[-2, -1]
		destinationSquare = new Coordinate(this.piecePosition.X-2, this.piecePosition.Y-1);
		if(destinationSquare.inRange())
		{
			if(board.isEmptyTile(destinationSquare))
			{
				LegalMoves.add(new NonAttackMove(board, this, destinationSquare));
			}else if(board.getPiece(destinationSquare).getAlliance()!=this.alliance)
			{
				LegalMoves.add(new AttackMove(board, this, destinationSquare, board.getPiece(destinationSquare)));
			}
		}
		//[2, -1]
		destinationSquare = new Coordinate(this.piecePosition.X+2, this.piecePosition.Y-1);
		if(destinationSquare.inRange())
		{
			if(board.isEmptyTile(destinationSquare))
			{
				LegalMoves.add(new NonAttackMove(board, this, destinationSquare));
			}else if(board.getPiece(destinationSquare).getAlliance()!=this.alliance)
			{
				LegalMoves.add(new AttackMove(board, this, destinationSquare, board.getPiece(destinationSquare)));
			}
		}
		//[-1, 2]
		destinationSquare = new Coordinate(this.piecePosition.X-1, this.piecePosition.Y+2);
		if(destinationSquare.inRange())
		{
			if(board.isEmptyTile(destinationSquare))
			{
				LegalMoves.add(new NonAttackMove(board, this, destinationSquare));
			}else if(board.getPiece(destinationSquare).getAlliance()!=this.alliance)
			{
				LegalMoves.add(new AttackMove(board, this, destinationSquare, board.getPiece(destinationSquare)));
			}
		}
		//[1, 2]
		destinationSquare = new Coordinate(this.piecePosition.X+1, this.piecePosition.Y+2);
		if(destinationSquare.inRange())
		{
			if(board.isEmptyTile(destinationSquare))
			{
				LegalMoves.add(new NonAttackMove(board, this, destinationSquare));
			}else if(board.getPiece(destinationSquare).getAlliance()!=this.alliance)
			{
				LegalMoves.add(new AttackMove(board, this, destinationSquare, board.getPiece(destinationSquare)));
			}
		}
		//[-2, 1]
		destinationSquare = new Coordinate(this.piecePosition.X-2, this.piecePosition.Y+1);
		if(destinationSquare.inRange())
		{
			if(board.isEmptyTile(destinationSquare))
			{
				LegalMoves.add(new NonAttackMove(board, this, destinationSquare));
			}else if(board.getPiece(destinationSquare).getAlliance()!=this.alliance)
			{
				LegalMoves.add(new AttackMove(board, this, destinationSquare, board.getPiece(destinationSquare)));
			}
		}
		//[2, 1]
		destinationSquare = new Coordinate(this.piecePosition.X+2, this.piecePosition.Y+1);
		if(destinationSquare.inRange())
		{
			if(board.isEmptyTile(destinationSquare))
			{
				LegalMoves.add(new NonAttackMove(board, this, destinationSquare));
			}else if(board.getPiece(destinationSquare).getAlliance()!=this.alliance)
			{
				LegalMoves.add(new AttackMove(board, this, destinationSquare, board.getPiece(destinationSquare)));
			}
		}
		return LegalMoves;
	}

	@Override
	public Piece movePiece(final Coordinate destinationCoordinate)
	{
		// TODO Auto-generated method stub
		Piece piece = new Knight(PieceType.KNIGHT, destinationCoordinate, this.alliance);
		piece.initialMoveMade();
		return piece;
	}
}
