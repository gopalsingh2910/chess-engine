package coordinate;

public class Coordinate
{
	public final int X;
	public final int Y;
	public static final Coordinate[] MAX_TILES = getMaxCoordinate();
	
	public Coordinate(final int x, final int y)
	{
		this.X = x;
		this.Y = y;
	}

	public static Coordinate create(final int x, final int y)
	{
		return new Coordinate(x, y);
	}

    public int getX()
	{
		return this.X;
	}

	@Override
	public String toString()
	{
		String[] alphabets = {"a", "b", "c", "d", "e", "f", "g", "h"};
		return alphabets[this.X] + this.Y;
	}

	public int getY()
	{
		return this.Y;
	}
	
	public int position()
	{
		return this.Y*8+this.X;
	}
	
	public boolean inRange()
	{
		return (this.X>=0 && this.X<8 && this.Y>=0 && this.Y<8);
	}

	public Coordinate add(final Coordinate coordinate)
	{
		return new Coordinate(this.X + coordinate.getX(), this.Y + coordinate.getY());
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordinate other = (Coordinate) obj;
		if (this.X != other.X)
			return false;
        return this.Y == other.Y;
    }

	private static Coordinate[] getMaxCoordinate()
	{
		Coordinate[] tileCoordinates = new Coordinate[64];
		
		for(int y = 0; y < 8; y++)
			for(int x = 0; x < 8; x++)
				tileCoordinates[y*8+x] = new Coordinate(x, y);
		
		return tileCoordinates;
	}

	public Coordinate flip()
	{
		return new Coordinate(this.getX(), 7 - this.getY());
	}
}
