package player;

public enum Alliance
{
	WHITE
	{
		@Override
		public boolean isWhite()
		{
			return true;
		}

		@Override
		public boolean isBlack()
		{
			return false;
		}
		
		@Override
		public int getDirection()
		{
			return -1;
		}

		@Override
		public Alliance opponentAlliance()
		{
			return BLACK;
		}

		@Override
		public boolean isOpponent(final Alliance alliance)
		{
			return (alliance == Alliance.BLACK);
		}

		@Override
		public Player getPlayer(final Player whitePlayer,
				final Player blackPlayer)
		{
			// TODO Auto-generated method stub
			return whitePlayer;
		}
	},
	
	BLACK
	{
		@Override
		public boolean isWhite()
		{
			return false;
		}

		@Override
		public boolean isBlack()
		{
			return true;
		}

		@Override
		public int getDirection()
		{
			return 1;
		}

		@Override
		public Alliance opponentAlliance()
		{
			return WHITE;
		}

		@Override
		public boolean isOpponent(final Alliance alliance)
		{
			return (alliance==Alliance.WHITE);
		}

		@Override
		public Player getPlayer(final Player whitePlayer,
				final Player blackPlayer)
		{
			// TODO Auto-generated method stub
			return blackPlayer;
		}
	};
	
	public abstract boolean isOpponent(final Alliance alliance);
	public abstract boolean isWhite();
	public abstract boolean isBlack();
	public abstract int getDirection();
	public abstract Alliance opponentAlliance();
	public abstract Player getPlayer(final Player whitePlayer, final Player blackPlayer);

}
