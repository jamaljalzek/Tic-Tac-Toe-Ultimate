package programLogic;

import java.util.Random;

import oldPackage.Game;

public class MediumMode
{
	private static int computerTileCount;
	private static int bestRowLocation, bestRowCount, chosenRow;
	private static int bestColumnLocation, bestColumnCount, chosenColumn;
	private static int bestDiagonalLocation, bestDiagonalCount, chosenDiagonal;
	
	
	/**
	 * During this difficulty, the computer does use a strategy to win. Specifically, it will try
	 * to place its tiles in a row, column, or diagonal to win.
	 * The strategy can be described as follows:
	 * 1. Find the row that is closest to being completed (by the computer).
	 * 2. Find the column that is closest to being completed.
	 * 3. Find the diagonal (out of the 2 that go through the center) that is closest to being completed.
	 * 4. Finally, select the most favorable row, column, or diagonal, and randomly choose an available spot inside.
	 *  
	 * @param Game.thisGame()
	 */
	public static void mediumMode()
	{
		if (Game.thisGame().hasEnded)
		{
			return;
		}
		searchForRowClosestToCompletionByComputer();
		searchForColumnClosestToCompletionByComputer();
		searchForDiagonalClosestToCompletionByComputer();		
		selectPathClosestToCompletionByComputer();
	}
	
	
	private static void searchForRowClosestToCompletionByComputer()
	{
		bestRowLocation = -1;
		bestRowCount = 0;
		for (int currentRow = 0; currentRow < Game.thisGame().dimension; ++currentRow)
		{
			searchColumnsInCurrentRow(currentRow);
			trackRowWithMostComputerTilesInIt(currentRow);
		}
		// We have now searched all of the rows on the game board.
		// bestRowLocation will be equal to -1 if all rows have at least 1 spot that is claimed by the player.
		// In this case, we set bestRowCount to -1 so rows will not be considered in selectBestPath:
		if (bestRowLocation == -1)
		{
			bestRowCount = -1;
		}
	}
	
	
	private static void trackRowWithMostComputerTilesInIt(int currentRow)
	{
		if (computerTileCount > bestRowCount)
		{
			bestRowLocation = currentRow;
			bestRowCount = computerTileCount;
		}
	}
	
	
	private static void searchColumnsInCurrentRow(int currentRow)
	{
		computerTileCount = 0;
		for (int currentColumn = 0; currentColumn < Game.thisGame().dimension; ++currentColumn)
		{
			if (hasThePlayerAlreadyBlockedThisRowColumnOrDiagonal(currentRow, currentColumn))
			{
				computerTileCount = -1;
				return;
			}
			if (GameBoard.isThisSpotClaimedByComputer(currentRow, currentColumn))
			{
				++computerTileCount;
			}
		}
	}
	
	
	private static boolean hasThePlayerAlreadyBlockedThisRowColumnOrDiagonal(int row, int column)
	{
		return GameBoard.isThisSpotClaimedByPlayer(row, column);
	}
	
	
	private static void searchForColumnClosestToCompletionByComputer()
	{
		bestColumnLocation = -1;
		bestColumnCount = 0;
		for (int currentColumn = 0; currentColumn < Game.thisGame().dimension; ++currentColumn)
		{
			searchRowsInCurrentColumn(currentColumn);
			trackColumnWithMostComputerTilesInIt(currentColumn);
		}
		// We have now searched all of the columns on the game board.
		// bestColumnLocation will be equal to -1 if all columns have at least 1 spot that is claimed by the player.
		// In this case, we set bestColumnCount to -1 so columns will not be considered in selectBestPath:
		if (bestColumnLocation == -1)
		{
			bestColumnCount = -1;
		}
	}
	
	
	private static void trackColumnWithMostComputerTilesInIt(int currentColumn)
	{
		if (computerTileCount > bestColumnCount)
		{
			bestColumnLocation = currentColumn;
			bestColumnCount = computerTileCount;
		}
	}
	
	
	private static void searchRowsInCurrentColumn(int currentColumn)
	{
		computerTileCount = 0;
		for (int currentRow = 0; currentRow < Game.thisGame().dimension; ++currentRow)
		{
			if (hasThePlayerAlreadyBlockedThisRowColumnOrDiagonal(currentRow, currentColumn))
			{
				computerTileCount = -1;
				return;
			}
			if (GameBoard.isThisSpotClaimedByComputer(currentRow, currentColumn))
			{
				++computerTileCount;
			}
		}
	}

	
	private static void searchForDiagonalClosestToCompletionByComputer()
	{				
		bestDiagonalLocation = -1;
		searchDownRightDiagonal();
		searchUpRightDiagonal();
	}
	
	
	private static void searchDownRightDiagonal()
	{
		computerTileCount = 0;
		for (int row = 0, column = 0; row < Game.thisGame().dimension && column < Game.thisGame().dimension; ++row, ++column)
		{
			if (hasThePlayerAlreadyBlockedThisRowColumnOrDiagonal(row, column))
			{
				computerTileCount = -1;
				return;
			}
			if (GameBoard.isThisSpotClaimedByComputer(row, column))
			{
				++computerTileCount;
			}
		}
		trackDiagonalWithMostComputerTilesInIt(0);
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
			if (hasThePlayerAlreadyBlockedThisRowColumnOrDiagonal(row, column))
			{
				computerTileCount = -1;
				return;
			}
			if (GameBoard.isThisSpotClaimedByComputer(row, column))
			{
				++computerTileCount;
			}
		}
		trackDiagonalWithMostComputerTilesInIt(bottomRow);
	}
	
	
	private static void selectPathClosestToCompletionByComputer()
	{
		//DEBUGGING();
		determineIfAllOptionsAreEqual();
		determineIfWeWillSelectARow();
		determineIfWeWillSelectAColumn();
		determineIfWeWillSelectADiagonal();
		GameBoard.letComputerClaimSpotOnGameBoard(chosenRow, chosenColumn);
	}
	
	
	private static void DEBUGGING()
	{
		System.out.println("best row count: " + bestRowCount + '\n' + 
				   "best row index: " + bestRowLocation);
		
		System.out.println("best column count: " + bestColumnCount + '\n' + 
				   "best column index: " + bestColumnLocation);
		
		System.out.println("best diagonal count: " + bestDiagonalCount + '\n' + 
				   "best diagonal index: " + bestDiagonalLocation);
	}
	
	
	private static void determineIfAllOptionsAreEqual()
	{
		if (canNoRowColumnOrDiagonalCanBeWonByTheComputer() || bestRowCount == bestColumnCount && bestColumnCount == bestDiagonalCount)
		{
			chooseRandomSpotOnGameBoard();
		}
	}
	
	
	private static boolean canNoRowColumnOrDiagonalCanBeWonByTheComputer()
	{
		return (bestRowLocation == -1 && bestColumnLocation == -1 && bestDiagonalLocation == -1);
	}
	
	
	private static void chooseRandomSpotOnGameBoard()
	{
		Random randomIndex = new Random();
		do
		{
			chosenRow = randomIndex.nextInt(Game.thisGame().dimension);
			chosenColumn = randomIndex.nextInt(Game.thisGame().dimension);
		}
		while (GameBoard.isChosenSpotAlreadyTaken(chosenRow, chosenColumn));
	}
	
	
	private static void determineIfWeWillSelectARow()
	{
		// R > C >= D or R > D >= C
		if (bestRowCount >= bestColumnCount && bestColumnCount >= bestDiagonalCount ||
			bestRowCount >= bestDiagonalCount && bestDiagonalCount >= bestColumnCount)
		{
			chooseRandomSpotInBestRow();
		}
	}
	
	
	private static void determineIfWeWillSelectAColumn()
	{
		// C > R >= D or C > D >= R
		if (bestColumnCount >= bestRowCount && bestRowCount >= bestDiagonalCount ||
			bestColumnCount >= bestDiagonalCount && bestDiagonalCount >= bestRowCount)
		{
			chooseRandomSpotInBestColumn();
		}
	}
	
	
	private static void determineIfWeWillSelectADiagonal()
	{
		// D > R >= C or D > C >= R
		if (bestDiagonalCount >= bestRowCount && bestRowCount >= bestColumnCount ||
			bestDiagonalCount >= bestColumnCount && bestColumnCount >= bestRowCount)
		{
			chooseRandomSpotInBestDiagonal();
		}
	}
	
	
	private static void chooseRandomSpotInBestRow()
	{
		Random randomIndex = new Random();
		chosenRow = bestRowLocation;
		do
		{
			chosenColumn = randomIndex.nextInt(Game.thisGame().dimension);
		}
		while (GameBoard.isChosenSpotAlreadyTaken(chosenRow, chosenColumn));
	}
	
	
	private static void chooseRandomSpotInBestColumn()
	{
		chosenColumn = bestColumnLocation;
		Random randomIndex = new Random();
		do
		{
			chosenRow = randomIndex.nextInt(Game.thisGame().dimension);
		}
		while (GameBoard.isChosenSpotAlreadyTaken(chosenRow, chosenColumn));
	}
	
	
	private static void chooseRandomSpotInBestDiagonal()
	{
		Random randomIndex = new Random();
		if (bestDiagonalLocation == 0)
		{
			do
			{
				chosenRow = randomIndex.nextInt(Game.thisGame().dimension);
				chosenColumn = chosenRow;
			}
			while (GameBoard.isChosenSpotAlreadyTaken(chosenRow, chosenColumn));
		}
		else // bestDiagonalLocation == bottom row index
		{
			chosenColumn = bestColumnLocation;
			do
			{
				// The relationship between the row index and the column index is row + column = dimension - 1.
				// Or, given a row, we can rewrite this relationship as column = dimension - 1 - row.
				chosenRow = randomIndex.nextInt(Game.thisGame().dimension);
				chosenColumn = Game.thisGame().dimension - 1 - chosenRow;
			}
			while (GameBoard.isChosenSpotAlreadyTaken(chosenRow, chosenColumn));
		}	
	}

} // End of class.
