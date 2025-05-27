package computer;

import gui.PawnPromotion;
import move.Move;
import player.MoveTransition;
import player.Player;
import tile.Board;

import static tile.Board.isEndGame;

public class MiniMax
{
	public final ScoreEvaluator scoreEvaluator;

	MiniMax(final Player player,
			final Move lastMove)
	{
		this.scoreEvaluator = new ScoreEvaluator(player, lastMove);
	}

	public int max(final Board board,
				   final int beta,
				   final int node,
				   final Move curentMove)
	{
		if ((node == 0) || isEndGame(board.currentPlayer()))
			return this.scoreEvaluator.evaluate(board, node, curentMove);

		int alpha = Integer.MIN_VALUE;
		for (final Move move : board.currentPlayer().legalMoves())
		{
			MoveTransition transition = move.execute();
			if (transition.isLegalMove() && move.isPawnPromoting())
				transition = PawnPromotion.promotePawn(board, move);
			if (transition.isLegalMove())
			{
				final int score = min(transition.transitionBoard(), alpha, node - 1,
						transition.transitionBoard().getLastMove());
				alpha = Math.max(score, alpha);
				if (alpha >= beta) return alpha;
			}
		}
		return alpha;
	}

	public int min(final Board board,
				   final int alpha,
				   final int node,
				   final Move currentMove)
	{
		if ((node == 0) || isEndGame(board.currentPlayer()))
			return this.scoreEvaluator.evaluate(board, node, currentMove);

		int beta = Integer.MAX_VALUE;
		for (final Move move : board.currentPlayer().legalMoves())
		{
			MoveTransition transition = move.execute();
			if (transition.isLegalMove() && move.isPawnPromoting())
				transition = PawnPromotion.promotePawn(board, move);
			if (transition.isLegalMove())
			{
				final int score = max(transition.transitionBoard(), beta, node - 1,
						transition.transitionBoard().getLastMove());
				beta = Math.min(score, beta);
				if (alpha >= beta) return beta;
			}
		}
		return beta;
	}
}
