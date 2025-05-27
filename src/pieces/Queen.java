package pieces;

import coordinate.Coordinate;
import move.AttackMove;
import move.Move;
import move.NonAttackMove;
import player.Alliance;
import tile.Board;

import java.util.ArrayList;

public class Queen extends Piece
{
	public Queen(final PieceType pieceType,
			final Coordinate piecePosition,
			final Alliance alliance)
	{
		super(pieceType, piecePosition, alliance);
	}

	@Override
	public String toString() {
		return "Q";
	}

	@Override
	public ArrayList<Move> legalMoves(final Board board)
	{
		final ArrayList<Move> LegalMoves = new ArrayList<>();
		Coordinate destinationSquare;

		//[-1, -1]
		for(int x=-1, y=-1; true; x--, y--)
		{
			destinationSquare = new Coordinate(this.piecePosition.X+x, this.piecePosition.Y+y);
			if(destinationSquare.inRange())
			{
				if(board.isEmptyTile(destinationSquare))
				{
					LegalMoves.add(new NonAttackMove(board, this, destinationSquare));
				}else if(board.getPiece(destinationSquare).getAlliance()!=this.alliance)
				{
					LegalMoves.add(new AttackMove(board, this, destinationSquare, board.getPiece(destinationSquare)));
					break;
				}else break;
			}else break;
		}
		//[1, -1]
		for(int x=1, y = -1; true; x++, y--)
		{
			destinationSquare = new Coordinate(this.piecePosition.X+x, this.piecePosition.Y+y);
			if(destinationSquare.inRange())
			{
				if(board.isEmptyTile(destinationSquare))
				{
					LegalMoves.add(new NonAttackMove(board, this, destinationSquare));
				}else if(board.getPiece(destinationSquare).getAlliance()!=this.alliance)
				{
					LegalMoves.add(new AttackMove(board, this, destinationSquare, board.getPiece(destinationSquare)));
					break;
				}else break;
			}else break;
		}
		//[-1, 1]
		for(int x=-1, y = 1; true; x--, y++)
		{
			destinationSquare = new Coordinate(this.piecePosition.X+x, this.piecePosition.Y+y);
			if(destinationSquare.inRange())
			{
				if(board.isEmptyTile(destinationSquare))
				{
					LegalMoves.add(new NonAttackMove(board, this, destinationSquare));
				}else if(board.getPiece(destinationSquare).getAlliance()!=this.alliance)
				{
					LegalMoves.add(new AttackMove(board, this, destinationSquare, board.getPiece(destinationSquare)));
					break;
				}else break;
			}else break;
		}
		//[1, 1]
		for(int x=1, y=1; true; x++, y++)
		{
			destinationSquare = new Coordinate(this.piecePosition.X+x, this.piecePosition.Y+y);
			if(destinationSquare.inRange())
			{
				if(board.isEmptyTile(destinationSquare))
				{
					LegalMoves.add(new NonAttackMove(board, this, destinationSquare));
				}else if(board.getPiece(destinationSquare).getAlliance()!=this.alliance)
				{
					LegalMoves.add(new AttackMove(board, this, destinationSquare, board.getPiece(destinationSquare)));
					break;
				}else break;
			}else break;
		}
		//[0, -1]
		for(int x=0, y=-1; true; y--)
		{
			destinationSquare = new Coordinate(this.piecePosition.X+x, this.piecePosition.Y+y);
			if(destinationSquare.inRange())
			{
				if(board.isEmptyTile(destinationSquare))
				{
					LegalMoves.add(new NonAttackMove(board, this, destinationSquare));
				}else if(board.getPiece(destinationSquare).getAlliance()!=alliance)
				{
					LegalMoves.add(new AttackMove(board, this, destinationSquare, board.getPiece(destinationSquare)));
					break;
				}else break;
			}else break;
		}
		//[0, 1]
		for(int x=0, y=1; true; y++)
		{
			destinationSquare = new Coordinate(this.piecePosition.X+x, this.piecePosition.Y+y);
			if(destinationSquare.inRange())
			{
				if(board.isEmptyTile(destinationSquare))
				{
					LegalMoves.add(new NonAttackMove(board, this, destinationSquare));
				}else if(board.getPiece(destinationSquare).getAlliance()!=this.alliance)
				{
					LegalMoves.add(new AttackMove(board, this, destinationSquare, board.getPiece(destinationSquare)));
					break;
				}else break;
			}else break;
		}
		//[-1, 0]
		for(int x=-1, y=0; true; x--)
		{
			destinationSquare = new Coordinate(this.piecePosition.X+x, this.piecePosition.Y+y);
			if(destinationSquare.inRange())
			{
				if(board.isEmptyTile(destinationSquare))
				{
					LegalMoves.add(new NonAttackMove(board, this, destinationSquare));
				}else if(board.getPiece(destinationSquare).getAlliance()!=this.alliance)
				{
					LegalMoves.add(new AttackMove(board, this, destinationSquare, board.getPiece(destinationSquare)));
					break;
				}else break;
			}else break;
		}
		//[1, 0]
		for(int x=1, y=0; true; x++)
		{
			destinationSquare = new Coordinate(this.piecePosition.X+x, this.piecePosition.Y+y);
			if(destinationSquare.inRange())
			{
				if(board.isEmptyTile(destinationSquare))
				{
					LegalMoves.add(new NonAttackMove(board, this, destinationSquare));
				}else if(board.getPiece(destinationSquare).getAlliance()!=this.alliance)
				{
					LegalMoves.add(new AttackMove(board, this, destinationSquare, board.getPiece(destinationSquare)));
					break;
				}else break;
			}else break;
		}	
		return LegalMoves;
	}

	@Override
	public Piece movePiece(final Coordinate destinationCoordinate)
	{
		// TODO Auto-generated method stub
		Piece piece = new Queen(PieceType.QUEEN, destinationCoordinate, this.alliance);
		piece.initialMoveMade();
		return piece;
	}

}
