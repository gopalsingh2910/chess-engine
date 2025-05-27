package player;

import move.Move;
import pieces.Piece;
import tile.Board;

import java.util.ArrayList;

public abstract class Player
{
	public final Board board;
	public final ArrayList<Move> legalMoves;
	public final PlayerType playerType;
	public final ArrayList<Move> opponentMoves;
	public final ArrayList<Piece> whiteActivePieces;
	public final ArrayList<Piece> blackActivePieces;
	public final Piece King;

	public Player(final PlayerType playerType,
				  final Board board,
				  final ArrayList<Move> legalMoves,
				  final ArrayList<Move> opponentMoves)
	{
		this.board = board;
		
		this.legalMoves = legalMoves;
		this.opponentMoves = opponentMoves;
		this.whiteActivePieces = this.board.getActivePieces(Alliance.WHITE);
		this.blackActivePieces = this.board.getActivePieces(Alliance.BLACK);
		this.King = getKing();
		this.playerType = playerType;
	}

	public abstract Piece getKing();

	public abstract ArrayList<Piece> activePieces();

	public abstract Alliance getAlliance();

	public abstract boolean isInCheck();

	public abstract boolean isInCheckMate();

	public abstract boolean isInStaleMate();

	public abstract ArrayList<Move> legalMoves();

	public abstract PlayerType playerType();

	public abstract Player opponentPlayer();

	public abstract boolean isLegalMove(final Move move);

	@Override
	public String toString()
	{
		return this.getAlliance().isBlack() ? "Black":"White";
	}
}
