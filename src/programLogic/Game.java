package programLogic;


public class Game
{
	private static int numberOfSpotsClaimed; // initially = 0
	private static String playerSymbol, computerSymbol;
	private static boolean hasEnded;
	private static boolean isPlayersTurn;
	private static String difficulty; // Set to either “EASY”, “MEDIUM”, “HARD”, etc.
	

	public static int getNumberOfSpotsClaimed()
	{
		return numberOfSpotsClaimed;
	}


	public static void incrementNumberOfSpotsClaimed()
	{
		++numberOfSpotsClaimed;
	}


	public static boolean hasEnded()
	{
		return hasEnded;
	}


	public static void setHasEnded(boolean hasEnded)
	{
		Game.hasEnded = hasEnded;
	}


	public static String getDifficulty()
	{
		return difficulty;
	}


	public static void setDifficulty(String difficulty)
	{
		Game.difficulty = difficulty;
	}


	public static String getPlayerSymbol()
	{
		return playerSymbol;
	}
	
	
	public static void setPlayerSymbol(String playerSymbol)
	{
		Game.playerSymbol = playerSymbol;
	}


	public static String getComputerSymbol()
	{
		return computerSymbol;
	}


	public static void setComputerSymbol(String computerSymbol)
	{
		Game.computerSymbol = computerSymbol;
	}


	public static void setPlayersTurn(boolean isPlayersTurn)
	{
		Game.isPlayersTurn = isPlayersTurn;
	}


	public static boolean isPlayersTurn()
	{
		return isPlayersTurn;
	}
	
	
	public static boolean isStillComputersTurn()
	{
		return !Game.isPlayersTurn();
	}
		
} // End of class.
