package pieces;

public enum PieceType
{
    KING
    {
        @Override
        public boolean isKing()
        {
            return true;
        }

        @Override
        public boolean isRook()
        {
            /* TODO Auto-generated method stub */
            return false;
        }

        @Override
        public boolean isPawn()
        {
            // TODO Auto-generated method stub
            return false;
        }

        @Override
        public String toString()
        {
            // TODO Auto-generated method stub
            return "king";
        }

        @Override
        public float getPoints()
        {
            // TODO Auto-generated method stub
            return 0f;
        }
    },

    QUEEN
    {
        @Override
        public boolean isKing()
        {
            // TODO Auto-generated method stub
            return false;
        }

        @Override
        public boolean isRook()
        {
            // TODO Auto-generated method stub
            return false;
        }

        @Override
        public boolean isPawn()
        {
            // TODO Auto-generated method stub
            return false;
        }

        @Override
        public String toString()
        {
            // TODO Auto-generated method stub
            return "queen";
        }

        @Override
        public float getPoints()
        {
            // TODO Auto-generated method stub
            return 900f;
        }
    },

    BISHOP
    {
        @Override
        public boolean isKing()
        {
            // TODO Auto-generated method stub
            return false;
        }

        @Override
        public boolean isRook()
        {
            // TODO Auto-generated method stub
            return false;
        }

        @Override
        public boolean isPawn()
        {
            // TODO Auto-generated method stub
            return false;
        }

        @Override
        public String toString()
        {
            // TODO Auto-generated method stub
            return "bishop";
        }

        @Override
        public float getPoints()
        {
            // TODO Auto-generated method stub
            return 350f;
        }
    },

    KNIGHT
    {
        @Override
        public boolean isKing()
        {
            // TODO Auto-generated method stub
            return false;
        }

        @Override
        public boolean isRook()
        {
            // TODO Auto-generated method stub
            return false;
        }

        @Override
        public boolean isPawn()
        {
            // TODO Auto-generated method stub
            return false;
        }

        @Override
        public String toString() {
            // TODO Auto-generated method stub
            return "knight";
        }

        @Override
        public float getPoints() {
            // TODO Auto-generated method stub
            return 350f;
        }
    },

    ROOK
    {
        @Override
        public boolean isKing()
        {
            // TODO Auto-generated method stub
            return false;
        }

        @Override
        public boolean isRook()
        {
            return true;
        }

        @Override
        public boolean isPawn()
        {
            // TODO Auto-generated method stub
            return false;
        }

        @Override
        public String toString()
        {
            // TODO Auto-generated method stub
            return "rook";
        }

        @Override
        public float getPoints()
        {
            // TODO Auto-generated method stub
            return 500f;
        }
    },

    PAWN
    {
        @Override
        public boolean isKing()
        {
            // TODO Auto-generated method stub
            return false;
        }

        @Override
        public boolean isRook()
        {
            // TODO Auto-generated method stub
            return false;
        }

        @Override
        public boolean isPawn()
        {
            return true;
        }

        @Override
        public String toString()
        {
            // TODO Auto-generated method stub
            return "pawn";
        }

        @Override
        public float getPoints()
        {
            // TODO Auto-generated method stub
            return 100f;
        }
    };

    public abstract boolean isKing();
    public abstract boolean isRook();
    public abstract boolean isPawn();
    public abstract String toString();
    public abstract float getPoints();
}
