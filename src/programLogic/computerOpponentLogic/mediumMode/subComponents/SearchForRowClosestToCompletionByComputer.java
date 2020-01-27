package programLogic.computerOpponentLogic.mediumMode.subComponents;

import oldPackage.Game;
import programLogic.GameBoard;

public class SearchForRowClosestToCompletionByComputer
{
	private static int computerTileCount;
	private static int bestRowLocation, bestRowCount;
	private static boolean canAnyRowBeWonByTheComputer;
	
	
	public static void searchForRowClosestToCompletionByComputer()
	{
		canAnyRowBeWonByTheComputer = false;
		bestRowCount = 0;
		for (int currentRow = 0; currentRow < Game.thisGame().dimension; ++currentRow)
		{
			searchColumnsInCurrentRow(currentRow);
			trackRowWithMostComputerTilesInIt(currentRow);
		}
	}
	
	
	private static void searchColumnsInCurrentRow(int currentRow)
	{
		computerTileCount = 0;
		for (int currentColumn = 0; currentColumn < Game.thisGame().dimension; ++currentColumn)
		{
			if (hasThePlayerAlreadyBlockedThisRow(currentRow, currentColumn))
			{
				computerTileCount = -1;
				return;
			}
			if (GameBoard.isThisSpotClaimedByComputer(currentRow, currentColumn))
			{
				++computerTileCount;
			}
		}
		canAnyRowBeWonByTheComputer = true;
	}
	
	
	private static void trackRowWithMostComputerTilesInIt(int currentRow)
	{
		if (computerTileCount > bestRowCount)
		{
			bestRowLocation = currentRow;
			bestRowCount = computerTileCount;
		}
	}

	
	private static boolean hasThePlayerAlreadyBlockedThisRow(int row, int column)
	{
		return GameBoard.isThisSpotClaimedByPlayer(row, column);
	}
	
	
	public static boolean canAnyRowBeWonByTheComputer()
	{
		return canAnyRowBeWonByTheComputer;
	}
	
	
	public static int bestRowCount()
	{
		return bestRowCount;
	}
	
	
	public static int bestRowLocation()
	{
		return bestRowLocation;
	}
	
} // End of class.
