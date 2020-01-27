package programLogic.computerOpponentLogic.mediumMode.subComponents;

import oldPackage.Game;
import programLogic.GameBoard;

public class SearchForDiagonalClosestToCompletionByComputer
{
	private static int computerTileCount;
	private static int bestDiagonalLocation, bestDiagonalCount;
	private static boolean canEitherDiagonalBeWonByTheComputer;
	
	
	public static void searchForDiagonalClosestToCompletionByComputer()
	{				
		canEitherDiagonalBeWonByTheComputer = false;
		searchDownRightDiagonal();
		searchUpRightDiagonal();
	}
	
	
	private static void searchDownRightDiagonal()
	{
		computerTileCount = 0;
		for (int row = 0, column = 0; row < Game.thisGame().dimension && column < Game.thisGame().dimension; ++row, ++column)
		{
			if (hasThePlayerAlreadyBlockedThisDiagonal(row, column))
			{
				computerTileCount = -1;
				return;
			}
			if (GameBoard.isThisSpotClaimedByComputer(row, column))
			{
				++computerTileCount;
			}
		}
		canEitherDiagonalBeWonByTheComputer = true;
		trackDiagonalWithMostComputerTilesInIt(0);
	}
	
	
	private static boolean hasThePlayerAlreadyBlockedThisDiagonal(int row, int column)
	{
		return GameBoard.isThisSpotClaimedByPlayer(row, column);
	}
	
	
	private static void trackDiagonalWithMostComputerTilesInIt(int diagonalJustSearchedRowLocation)
	{
		if (computerTileCount > bestDiagonalCount)
		{
			bestDiagonalLocation = diagonalJustSearchedRowLocation;
		}
	}
	
	
	private static void searchUpRightDiagonal()
	{
		computerTileCount = 0;
		int bottomRow = Game.thisGame().dimension - 1;
		for (int row = bottomRow, column = 0; row >= 0 && column < Game.thisGame().dimension; --row, ++column)
		{
			if (hasThePlayerAlreadyBlockedThisDiagonal(row, column))
			{
				computerTileCount = -1;
				return;
			}
			if (GameBoard.isThisSpotClaimedByComputer(row, column))
			{
				++computerTileCount;
			}
		}
		canEitherDiagonalBeWonByTheComputer = true;
		trackDiagonalWithMostComputerTilesInIt(bottomRow);
	}
	
	
	public static boolean canEitherDiagonalBeWonByTheComputer()
	{
		return canEitherDiagonalBeWonByTheComputer;
	}
	
	
	public static int bestDiagonalCount()
	{
		return bestDiagonalCount;
	}

	
	public static int bestDiagonalLocation()
	{
		return bestDiagonalLocation;
	}
	
} // End of class.
