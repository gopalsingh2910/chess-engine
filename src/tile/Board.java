package tile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import coordinate.Coordinate;
import move.Move;
import pieces.*;
import player.*;
import pieces.PieceType;

public class Board
{
	public ArrayList<Piece> chessBoard;
	
	public final Player whitePlayer;
	public final Player blackPlayer;
	public final Player currentPlayer;
	
	public final ArrayList<Move> whiteLegalMoves;
	public final ArrayList<Move> blackLegalMoves;
	
	public final ArrayList<Piece> whiteActivePieces = new ArrayList<>();
	public final ArrayList<Piece> blackActivePieces = new ArrayList<>();

	private final Move lastMove;

	public Board(final Builder build)
	{
		this.chessBoard = createBoard(build);
		this.whiteLegalMoves = Move.possibleMoves(this, Alliance.WHITE);
		this.blackLegalMoves = Move.possibleMoves(this, Alliance.BLACK);
		
		for(Coordinate coordinate : Coordinate.MAX_TILES)
		{
			if(this.getPiece(coordinate)!=null)
			{
				if(this.getPiece(coordinate).getAlliance().isWhite())
					this.whiteActivePieces.add(this.getPiece(coordinate));
				else
					this.blackActivePieces.add(this.getPiece(coordinate));
			}
		}
		this.whitePlayer = new WhitePlayer(build.whitePlayer, this, this.whiteLegalMoves, this.blackLegalMoves);
		this.blackPlayer = new BlackPlayer(build.blackPlayer, this, this.whiteLegalMoves, this.blackLegalMoves);
		this.currentPlayer = build.nextMoveMaker.getPlayer(this.whitePlayer, this.blackPlayer);
		lastMove = build.getLastMove();
	}
	
	public static ArrayList<Piece> createBoard(Builder build)
	{
		ArrayList<Piece> board = new ArrayList<>();
		
		for(Coordinate coordinate : Coordinate.MAX_TILES)
			board.add(build.boardConfig.get(coordinate.position()));
		
		return board;
	}

    public static boolean isEndGame(final Player player)
    {
        return (player.isInCheckMate()
                	|| player.isInStaleMate());
    }

    public boolean isEmptyTile(final Coordinate destinationSquare)
	{
		return (chessBoard.get(destinationSquare.position())==null);
	}

	public Piece getPiece(final Coordinate destinationCoordinate)
	{
		return destinationCoordinate.inRange() ? chessBoard.get(destinationCoordinate.position()):null;
	}

	public ArrayList<Piece> getActivePieces(final Alliance alliance)
	{
		return (alliance.isBlack()) ? blackActivePieces:whiteActivePieces;
	}

	public Player currentPlayer()
	{
		return this.currentPlayer;
	}

	public Player getPlayer(final Alliance alliance)
	{
		return alliance.isBlack() ? blackPlayer:whitePlayer;
	}

	public static Board initializeBoard(final PlayerType whitePlayerType,
										final PlayerType blackPlayerType)
	{
		Builder build = new Builder(null);
		build.setPlayerType(whitePlayerType, blackPlayerType);
		build.setMoveMaker(Alliance.WHITE);

		//Black team
		build.boardConfig.put(new Coordinate(3, 0).position(), new King(PieceType.KING, new Coordinate(3, 0), Alliance.BLACK));
		build.boardConfig.put(new Coordinate(4, 0).position(), new Queen(PieceType.QUEEN, new Coordinate(4, 0), Alliance.BLACK));
		build.boardConfig.put(new Coordinate(1, 0).position(), new Knight(PieceType.KNIGHT, new Coordinate(1, 0), Alliance.BLACK));
		build.boardConfig.put(new Coordinate(6, 0).position(), new Knight(PieceType.KNIGHT, new Coordinate(6, 0), Alliance.BLACK));
		build.boardConfig.put(new Coordinate(2, 0).position(), new Bishop(PieceType.BISHOP, new Coordinate(2, 0), Alliance.BLACK));
		build.boardConfig.put(new Coordinate(5, 0).position(), new Bishop(PieceType.BISHOP, new Coordinate(5, 0), Alliance.BLACK));
		build.boardConfig.put(new Coordinate(0, 0).position(), new Rook(PieceType.ROOK, new Coordinate(0, 0), Alliance.BLACK));
		build.boardConfig.put(new Coordinate(7, 0).position(), new Rook(PieceType.ROOK, new Coordinate(7, 0), Alliance.BLACK));
		
		for(int i=0; i<8; i++)
			build.boardConfig.put(new Coordinate(i, 1).position(), new Pawn(PieceType.PAWN, new Coordinate(i, 1), Alliance.BLACK));
		
		//White team
		build.boardConfig.put(new Coordinate(3, 7).position(), new King(PieceType.KING, new Coordinate(3, 7), Alliance.WHITE));
		build.boardConfig.put(new Coordinate(4, 7).position(), new Queen(PieceType.QUEEN, new Coordinate(4, 7), Alliance.WHITE));
		build.boardConfig.put(new Coordinate(1, 7).position(), new Knight(PieceType.KNIGHT, new Coordinate(1, 7), Alliance.WHITE));
		build.boardConfig.put(new Coordinate(6, 7).position(), new Knight(PieceType.KNIGHT, new Coordinate(6, 7), Alliance.WHITE));
		build.boardConfig.put(new Coordinate(2, 7).position(), new Bishop(PieceType.BISHOP, new Coordinate(2, 7), Alliance.WHITE));
		build.boardConfig.put(new Coordinate(5, 7).position(), new Bishop(PieceType.BISHOP, new Coordinate(5, 7), Alliance.WHITE));
		build.boardConfig.put(new Coordinate(0, 7).position(), new Rook(PieceType.ROOK, new Coordinate(0, 7), Alliance.WHITE));
		build.boardConfig.put(new Coordinate(7, 7).position(), new Rook(PieceType.ROOK, new Coordinate(7, 7), Alliance.WHITE));
		
		for(int i=0; i<8; i++)
			build.boardConfig.put(new Coordinate(i, 6).position(), new Pawn(PieceType.PAWN, new Coordinate(i, 6), Alliance.WHITE));

		return build.getBoard();
	}

	public static Map<Integer, Piece> generateBoard()
	{
		Map<Integer, Piece> map = new HashMap<>();
		for(Coordinate coordinate : Coordinate.MAX_TILES)
			map.put(coordinate.position(), null);
		return map;
	}

	public Move getLastMove()
	{
		return this.lastMove;
	}
}
