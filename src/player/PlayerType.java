package player;

public enum PlayerType
{
    COMPUTER {
        @Override
        public PlayerType opponentPlayerType()
        {
            return PlayerType.HUMAN;
        }

        @Override
        public boolean isHuman() {
            return false;
        }

        @Override
        public boolean isComputer() {
            return true;
        }
    },
    HUMAN {
        @Override
        public PlayerType opponentPlayerType()
        {
            return PlayerType.COMPUTER;
        }

        @Override
        public boolean isHuman() {
            return true;
        }

        @Override
        public boolean isComputer() {
            return false;
        }
    };

    public abstract PlayerType opponentPlayerType();
    public abstract boolean isHuman();
    public abstract boolean isComputer();
}
