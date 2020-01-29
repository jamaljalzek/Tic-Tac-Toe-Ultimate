package programLogic.computerOpponentLogic.mediumMode.subComponents;

import programLogic.GameBoard;

public class SearchForColumnClosestToCompletionByComputer
{
	private static int computerTileCount;
	private static int bestColumnLocation, bestColumnCount;
	private static boolean canAnyColumnBeWonByTheComputer;
	
	
	public static void searchForColumnClosestToCompletionByComputer()
	{
		canAnyColumnBeWonByTheComputer = false;
		bestColumnCount = 0;
		for (int currentColumn = 0; currentColumn < GameBoard.getDimension(); ++currentColumn)
		{
			searchRowsInCurrentColumn(currentColumn);
			trackColumnWithMostComputerTilesInIt(currentColumn);
		}
	}
	
	
	private static void searchRowsInCurrentColumn(int currentColumn)
	{
		computerTileCount = 0;
		for (int currentRow = 0; currentRow < GameBoard.getDimension(); ++currentRow)
		{
			if (hasThePlayerAlreadyBlockedThisColumn(currentRow, currentColumn))
			{
				computerTileCount = -1;
				return;
			}
			if (GameBoard.isThisSpotClaimedByComputer(currentRow, currentColumn))
			{
				++computerTileCount;
			}
		}
		canAnyColumnBeWonByTheComputer = true;
	}
	
	
	private static void trackColumnWithMostComputerTilesInIt(int currentColumn)
	{
		if (computerTileCount > bestColumnCount)
		{
			bestColumnLocation = currentColumn;
			bestColumnCount = computerTileCount;
		}
	}
	
	
	private static boolean hasThePlayerAlreadyBlockedThisColumn(int row, int column)
	{
		return GameBoard.isThisSpotClaimedByPlayer(row, column);
	}
	
	
	public static boolean canAnyColumnBeWonByTheComputer()
	{
		return canAnyColumnBeWonByTheComputer;
	}
	
	
	public static int bestColumnCount()
	{
		return bestColumnCount;
	}
	
	
	public static int bestColumnLocation()
	{
		return bestColumnLocation;
	}

} // End of class.
