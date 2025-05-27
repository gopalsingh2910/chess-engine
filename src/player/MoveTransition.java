package player;

import move.Move;
import tile.Board;

public class MoveTransition
{
	public final Move move;
	public final Board transitionBoard;
	private final MoveStatus moveStatus;
	
	public MoveTransition(final Board transitionBoard,
						  final Move move,
						  final MoveStatus moveStatus)
	{
		this.move = move;
		this.transitionBoard = transitionBoard;
		this.moveStatus = moveStatus;
	}

	public MoveStatus moveStatus()
	{
		return this.moveStatus;
	}

	public boolean isLegalMove()
	{
		return this.moveStatus.isLegal();
	}

	public Board transitionBoard()
	{
		return this.transitionBoard;
	}
}
