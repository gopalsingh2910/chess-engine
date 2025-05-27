package pieces;

import coordinate.Coordinate;
import move.AttackMove;
import move.Castling;
import move.Move;
import move.NonAttackMove;
import player.Alliance;
import tile.Board;

import java.util.ArrayList;

public class King extends Piece
{
	public King(final PieceType pieceType,
			final Coordinate piecePosition,
			final Alliance alliance)
	{
		super(pieceType, piecePosition, alliance);
	}

	@Override
	public String toString() {
		return "K";
	}

	@Override
	public ArrayList<Move> legalMoves(final Board board)
	{
		final ArrayList<Move> LegalMoves = new ArrayList<>();
		Coordinate destinationSquare;

		//[0, -1]
		destinationSquare = new Coordinate(this.piecePosition.X, this.piecePosition.Y-1);
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
		//[0, 1]
		destinationSquare = new Coordinate(this.piecePosition.X, this.piecePosition.Y+1);
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
		//[-1, 0]
		destinationSquare = new Coordinate(this.piecePosition.X-1, this.piecePosition.Y);
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
		//[1, 0]
		destinationSquare = new Coordinate(this.piecePosition.X+1, this.piecePosition.Y);
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
		//[-1, -1]
		destinationSquare = new Coordinate(this.piecePosition.X-1, this.piecePosition.Y-1);
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
		//[1, -1]
		destinationSquare = new Coordinate(this.piecePosition.X+1, this.piecePosition.Y-1);
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
		//[-1, 1]
		destinationSquare = new Coordinate(this.piecePosition.X-1, this.piecePosition.Y+1);
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
		//[1, 1]
		destinationSquare = new Coordinate(this.piecePosition.X+1, this.piecePosition.Y+1);
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
		//Castling
		if(isInitialMove())
		{
			//left
			destinationSquare = new Coordinate(1, (this.alliance.isBlack()) ? 0:7);
			if(board.getPiece(new Coordinate(0, destinationSquare.Y))!=null
				&& board.getPiece(new Coordinate(0, destinationSquare.Y)).pieceType().isRook()
				&& board.getPiece(new Coordinate(0, destinationSquare.Y)).isInitialMove()
				&& board.isEmptyTile(destinationSquare)
				&& board.isEmptyTile(new Coordinate(2, destinationSquare.Y)))
			{
				LegalMoves.add(new Castling(board, this, new Rook(PieceType.ROOK,
						new Coordinate(2, (this.alliance.isBlack() ? 0:7)), this.alliance), Move.KING_SIDE, destinationSquare));
			}
			//right
			destinationSquare = new Coordinate(5, (this.alliance.isBlack()) ? 0:7);
			if(board.getPiece(new Coordinate(7, destinationSquare.Y))!=null
				&& board.getPiece(new Coordinate(7, destinationSquare.Y)).pieceType().isRook()
				&& board.getPiece(new Coordinate(7, destinationSquare.Y)).isInitialMove()
				&& board.isEmptyTile(new Coordinate(4, destinationSquare.Y))
				&& board.isEmptyTile(destinationSquare)
				&& board.isEmptyTile(new Coordinate(6, destinationSquare.Y)))
			{
				LegalMoves.add(new Castling(board, this, new Rook(PieceType.ROOK,
						new Coordinate(4, (this.alliance.isBlack() ? 0:7)), this.alliance), Move.QUEEN_SIDE, destinationSquare));
			}
		}
		return LegalMoves;
	}

	@Override
	public Piece movePiece(final Coordinate destinationCoordinate)
	{
		// TODO Auto-generated method stub
		Piece piece = new King(PieceType.KING, destinationCoordinate, this.alliance);
		piece.initialMoveMade();
		return piece;
	}
}
