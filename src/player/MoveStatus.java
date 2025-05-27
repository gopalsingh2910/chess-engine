package player;

public enum MoveStatus
{
    ILLEGAL {
        @Override
        public boolean isLegal() {
            return false;
        }

        @Override
        public boolean isInvalid() {
            return false;
        }

        @Override
        public boolean isIlLegal() {
            return true;
        }
    },
    INVALID {
        @Override
        public boolean isLegal() {
            return false;
        }

        @Override
        public boolean isInvalid() {
            return true;
        }

        @Override
        public boolean isIlLegal() {
            return false;
        }
    },
    LEGAL {
        @Override
        public boolean isLegal() {
            return true;
        }

        @Override
        public boolean isInvalid() {
            return false;
        }

        @Override
        public boolean isIlLegal() {
            return false;
        }
    };

    public abstract boolean isLegal();
    public abstract boolean isInvalid();
    public abstract boolean isIlLegal();
}
