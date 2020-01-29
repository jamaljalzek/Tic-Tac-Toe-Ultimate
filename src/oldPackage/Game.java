package oldPackage;


public class Game
{
	private static Game thisGame;
	public final int dimension;
	public int numberOfSpotsClaimed;
	public final String playerSymbol, computerSymbol;
	public GameTile [] [] gameBoard;
	public boolean hasEnded;
	public boolean isPlayersTurn;
	protected String difficulty; // Set to either “EASY”, “MEDIUM”, “HARD”, etc.
	
	
	public Game (int dimension, String playerSymbol, String computerSymbol, String difficulty)
	{
		Game.thisGame = this;
		this.dimension = dimension;
		this.numberOfSpotsClaimed = 0;
		this.playerSymbol = playerSymbol;
		this.computerSymbol = computerSymbol;
		this.hasEnded = false;
		this.difficulty = difficulty;	
	}
	
	
	public static Game thisGame()
	{
		return Game.thisGame;
	}
	
	
	public static boolean hasEnded()
	{
		return Game.thisGame.hasEnded;
	}
	
	
	public static boolean isPlayersTurn()
	{
		return Game.thisGame.isPlayersTurn;
	}
	
	
	public static boolean isStillComputersTurn()
	{
		return !Game.isPlayersTurn();
	}
	
	
	public static String getDifficulty()
	{
		return Game.thisGame.difficulty;
	}
	
	
	public static String getPlayerSymbol()
	{
		return Game.thisGame.playerSymbol;
	}
	
	
	public static String getComputerSymbol()
	{
		return Game.thisGame.computerSymbol;
	}
		
} // End of class.
