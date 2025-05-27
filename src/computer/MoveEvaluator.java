package computer;

import gui.PawnPromotion;
import move.Move;
import player.MoveTransition;
import player.Player;
import tile.Board;

public class MoveEvaluator
{
	public Move calculateMove(final Board board,
							  final int level)
	{
		Move bestMove = null;
		int alpha = Integer.MIN_VALUE;
		final Player currentPlayer = board.currentPlayer();
		for (final Move move : currentPlayer.legalMoves()) {
			final MiniMax miniMax = new MiniMax(board.currentPlayer(), move);
			MoveTransition transition = move.execute();
			if (move.isPawnPromoting() && transition.isLegalMove())
				transition = PawnPromotion.promotePawn(board, move);
			if (transition.isLegalMove()) {
				final int score = miniMax.min(transition.transitionBoard(),
						alpha, level - 1, board.getLastMove());
				if (score > alpha) {
					alpha = score;
					bestMove = move;
				}
			}
		}
		return bestMove;
	}
}
