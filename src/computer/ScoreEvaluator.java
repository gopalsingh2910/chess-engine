package computer;

import coordinate.Coordinate;
import move.Move;
import pieces.Piece;
import pieces.PieceType;
import player.Alliance;
import player.Player;
import tile.Board;

public class ScoreEvaluator
{
	private static final float CHECK_BONUS = 10f;
	private static final float CENTER_BONUS = 50f;
	private static final float QUEEN_BONUS = -200f;
	private static final float SPECIAL_BONUS = 25f;
	private static final float MOBILITY_BONUS = 1f;
	private static final float SUB_SPECIAL_BONUS = 20f;
	private static final float DOUBLE_PAWN_BONUS = -80f;
	private static final float SAME_PIECE_BONUS = -150f;
	private static final float STALEMATE_BONUS = 5000f;
	private static final float CHECKMATE_BONUS = 10000f;
	private static final float KING_MOBILITY_BONUS = -250f;
	private static final float ISOLATED_PAWN_BONUS = -200f;
	private static final float PREVIOUS_MOVE_BONUS = -400f;
	private static final float SPECIAL_NEGATIVE_BONUS = -25f;
	private static final float ROOK_MOVE_BONUS = -300f;

	private final Player player;
	private final Move previousMove;
	private Move currentMove;

	public ScoreEvaluator(final Player player,
						  final Move lastMove)
	{
		this.player = player;
		this.previousMove = lastMove;
	}

	public int evaluate(final Board board,
						final int depth,
						final Move currentMove)
	{
		this.currentMove = currentMove;
		return (this.player.getAlliance().isBlack()) ?
				(int) ((scorePlayer(board, board.getPlayer(Alliance.BLACK), depth)
						- scorePlayer(board, board.getPlayer(Alliance.WHITE), depth))):
				(int) ((scorePlayer(board, board.getPlayer(Alliance.WHITE), depth)
						- scorePlayer(board, board.getPlayer(Alliance.BLACK), depth)));
	}

	private float scorePlayer(final Board board,
							  final Player player,
							  final int depth)
	{
		return queen() +
				sameMove(board) +
				samePiece() +
				mobility(player) +
				limitation(player) +
				piecePoints(player) +
				check(player, depth) +
				checkmate(player, depth) +
				doublePawn(board, player) +
				blockedPawn(board, player) +
				isolatedPawn(board, player) +
				centerPoints(player, board) +
				stalemateBonus(player, depth);
	}

	private float centerPoints(final Player player,
							   final Board board)
	{
		float score = 0f;
		final Coordinate[] array = {Coordinate.create(3, 3), Coordinate.create(3, 4),
										 Coordinate.create(4, 3), Coordinate.create(4, 4)};
		for (Coordinate coordinate:array)
		{
			if (board.getPiece(coordinate) != null)
				if (!board.getPiece(coordinate).getAlliance().isOpponent(player.getAlliance()))
					score += CENTER_BONUS;
				else score -= CENTER_BONUS;
		}
		return score;
	}

	private float queen()
	{
		return (this.previousMove.pieceToMove().pieceType() == PieceType.QUEEN) ? QUEEN_BONUS:0f;
	}

	private float sameMove(final Board board)
	{
		if (board.getLastMove() == null || this.previousMove == null) return 0f;
		return (this.currentMove.equals(this.previousMove)) ?
				PREVIOUS_MOVE_BONUS:0f;
	}

	private float samePiece()
	{
		if (this.currentMove == null || this.previousMove == null) return 0f;
		return (this.currentMove.pieceToMove().equals(this.previousMove.pieceToMove()))?
				SAME_PIECE_BONUS:0f;
	}

	private float stalemateBonus(final Player player,
								 final int depth)
	{
		return player.opponentPlayer().isInStaleMate() ? STALEMATE_BONUS * depthBonus(depth):0;
	}

	public float limitation(final Player player)
	{
		float score = 0;
		for (final Piece piece : player.activePieces())
		{
			final int x = piece.piecePosition().getX();
			final int y = piece.piecePosition().getY();
			if (piece.pieceType().isKing())
			{
				score += KING_MOBILITY_BONUS;
				//black
				if (player.getAlliance().isBlack())
				{
					if (y == 0) score += SPECIAL_BONUS;
					else score += SPECIAL_NEGATIVE_BONUS;
				} else {
					if (y == 7) score += SPECIAL_BONUS;
					else score += SPECIAL_NEGATIVE_BONUS;
				}
			} else if (piece.pieceType() == PieceType.QUEEN)
			{
				if (piece.getAlliance().isBlack())
				{
					if (y > 2 || x < 2 || x > 5) score += SPECIAL_NEGATIVE_BONUS * 2;
				} else {
					if (y < 5 || x < 2 || x > 5) score += SPECIAL_NEGATIVE_BONUS * 2;
				}
			} else if (piece.pieceType() == PieceType.BISHOP)
			{
				if (piece.isInitialMove()) score += SPECIAL_BONUS;
				//black
				if (piece.getAlliance().isBlack())
				{
					if (y > 4 || x == 0 || x == 7) score += SPECIAL_NEGATIVE_BONUS;
					else score += SPECIAL_BONUS;
				} else {
					if (y < 2 || x == 0 || x == 7) score += SPECIAL_NEGATIVE_BONUS;
					else score += SPECIAL_BONUS;
				}
			} else if (piece.pieceType() == PieceType.KNIGHT)
			{
				if (piece.isInitialMove()) score += SPECIAL_NEGATIVE_BONUS;
				//black
				if (piece.getAlliance().isBlack())
				{
					if (y < 2 || x < 2 || x > 5) score += SPECIAL_NEGATIVE_BONUS;
				} else {
					if (y > 5 || x < 2 || x > 5) score += SPECIAL_NEGATIVE_BONUS;
				}
			} else if (piece.pieceType == PieceType.ROOK)
			{
				if (this.previousMove.pieceToMove().equals(piece)) {
					score += ROOK_MOVE_BONUS;
				}
			} else if (piece.pieceType() == PieceType.PAWN)
			{
				//black
				if (piece.getAlliance().isBlack())
				{
					if ((x < 2 || x > 5) && y != 1) score += SPECIAL_NEGATIVE_BONUS;
					if ((x > 2 && x < 5) && y != 1) score += y * SPECIAL_BONUS;
				} else {
					if ((x < 2 || x > 5) && y != 1) score += SPECIAL_NEGATIVE_BONUS;
					if ((x > 2 && x < 5) && y != 1) score += (7 - y) * SPECIAL_BONUS;
				}
			}
		}
		return score;
	}

	private float checkmate(final Player player,
							final int depth)
	{
		return player.opponentPlayer().isInCheckMate() ? CHECKMATE_BONUS * depthBonus(depth):0;
	}

	private float check(final Player player,
						final int depth)
	{
		return player.opponentPlayer().isInCheck() ? CHECK_BONUS * depthBonus(depth):0;
	}

	private float depthBonus(final int depth)
	{
		return SPECIAL_BONUS * (5 - depth);
	}

	private float mobility(final Player player)
	{
		return player.legalMoves().size() * MOBILITY_BONUS;
	}

	private float isolatedPawn(final Board board,
							   final Player player)
	{
		float isolatedPawn = 0;
		for(final Piece piece : player.activePieces())
		{
			if (!piece.pieceType().isPawn()) continue;
			final Piece left = board.getPiece(piece.piecePosition()
					.add(new Coordinate((piece.piecePosition().getX() - 1), piece.piecePosition().getY() +
					(piece.getAlliance().isBlack() ? -1:1))));
			final Piece right = board.getPiece(piece.piecePosition()
					.add(new Coordinate((piece.piecePosition().getX() + 1), piece.piecePosition().getY() +
							(piece.getAlliance().isBlack() ? -1:1))));
			if (left == null && right == null) {
				isolatedPawn += ISOLATED_PAWN_BONUS;
				continue;
			}

			if (left != null
					&& (left.getAlliance().isOpponent(piece.getAlliance()) || !left.pieceType().isPawn()))
				isolatedPawn += ISOLATED_PAWN_BONUS;
			else isolatedPawn += SUB_SPECIAL_BONUS;
			if (right != null
					&& (!right.pieceType().isPawn() || right.getAlliance().isOpponent(piece.getAlliance())))
				isolatedPawn += ISOLATED_PAWN_BONUS;
			else isolatedPawn += SUB_SPECIAL_BONUS;
		}
		return isolatedPawn;
	}

	private float doublePawn(final Board board,
							 final Player player)
	{
		float doublePawn = 0;
		for (final Piece piece : player.activePieces())
		{
			if (piece.pieceType().isPawn())
				for (int y = piece.piecePosition().getX(); y < 8; y++)
					if (board.getPiece(new Coordinate(piece.piecePosition().getX(), y)) != null
							&& board.getPiece(new Coordinate(piece.piecePosition().getX(),
							y)).getAlliance() == piece.getAlliance())
						doublePawn += DOUBLE_PAWN_BONUS;
					else doublePawn += SUB_SPECIAL_BONUS;
		}
		return doublePawn;
	}

	private float blockedPawn(final Board board,
							  final Player player)
	{
		float blockedPawn = 0;
		final Coordinate coordinate = new Coordinate(0, player.getAlliance().isBlack() ? 1:-1);
		for (final Piece piece : player.activePieces())
			if (piece.pieceType().isPawn() && board.getPiece(piece.piecePosition().add(coordinate)) != null)
				blockedPawn += SPECIAL_NEGATIVE_BONUS * 2;
			else blockedPawn += SUB_SPECIAL_BONUS;
		return blockedPawn;
	}

	private float piecePoints(final Player player)
	{
		float point = 0f;
		for(Piece piece : player.activePieces())
			point += piece.pieceType().getPoints();
		return point;
	}
}
