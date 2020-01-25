package programLogic;

import oldPackage.Game;

public class PreventPlayerFromWinning
{
	private static int playerTileCount;
	private static int emptyColumnIndex, emptyRowIndex;
	private static boolean hasComputerBlockedPlayerThisTurn;
	
	
	public static void blockPlayerIfTheyAreAboutToWin()
	{
		hasComputerBlockedPlayerThisTurn = false;
		checkRows();
		if (!hasComputerBlockedPlayerThisTurn)
		{
			checkColumns();
		}
		if (!hasComputerBlockedPlayerThisTurn)
		{
			checkDiagonals();
		}
	}
	
	
	private static void checkRows()
	{
		for (int currentRow = 0; currentRow < Game.thisGame().dimension; ++currentRow)
		{
			searchColumnsInCurrentRow(currentRow);
			if (isPlayerOneSpotAwayFromCompletion())
			{
				blockPlayerByClaimingSpotBeforeTheyCan(currentRow, emptyColumnIndex);
				return;
			}
		}
	}
	
	
	private static void searchColumnsInCurrentRow(int currentRow)
	{
		playerTileCount = 0;
		for (int currentColumn = 0; currentColumn < Game.thisGame().dimension; ++currentColumn)
		{
			if (hasTheComputerAlreadyBlockedThisRowColumnOrDiagonal(currentRow, currentColumn))
			{
				playerTileCount = -1;
				return;
			}
			if (GameBoard.isThisSpotClaimedByPlayer(currentRow, currentColumn))
			{
				++playerTileCount;
			}
			emptyColumnIndex = currentColumn;
		}
	}
	
	
	private static boolean hasTheComputerAlreadyBlockedThisRowColumnOrDiagonal(int row, int column)
	{
		return GameBoard.isThisSpotClaimedByComputer(row, column);
	}
	
	
	private static boolean isPlayerOneSpotAwayFromCompletion()
	{
		int numberOfSpotsInEveryRowColumnAndDiagonal = Game.thisGame().dimension;
		return playerTileCount == (numberOfSpotsInEveryRowColumnAndDiagonal - 1);
	}
	
	
	private static void blockPlayerByClaimingSpotBeforeTheyCan(int row, int column)
	{
		GameBoard.letComputerClaimSpotOnGameBoard(row, column);
		hasComputerBlockedPlayerThisTurn = true;
	}
	
	
	private static void checkColumns()
	{
		for (int currentColumn = 0; currentColumn < Game.thisGame().dimension; ++currentColumn)
		{
			searchRowsInCurrentColumn(currentColumn);
			if (isPlayerOneSpotAwayFromCompletion())
			{
				blockPlayerByClaimingSpotBeforeTheyCan(emptyRowIndex, currentColumn);
				return;
			}
		}
	}
	
	
	private static void searchRowsInCurrentColumn(int currentColumn)
	{
		playerTileCount = 0;
		for (int currentRow = 0; currentRow < Game.thisGame().dimension; ++currentRow)
		{
			if (hasTheComputerAlreadyBlockedThisRowColumnOrDiagonal(currentRow, currentColumn))
			{
				playerTileCount = -1;
				return;
			}
			if (GameBoard.isThisSpotClaimedByPlayer(currentRow, currentColumn))
			{
				++playerTileCount;
			}
			emptyRowIndex = currentRow;
		}
	}
	
	
	private static void checkDiagonals()
	{
		checkDownRightDiagonal();
		if (isPlayerOneSpotAwayFromCompletion())
		{
			blockPlayerByClaimingSpotBeforeTheyCan(emptyRowIndex, emptyColumnIndex);
			return;
		}
		checkUpRightDiagonal();
		if (isPlayerOneSpotAwayFromCompletion())
		{
			blockPlayerByClaimingSpotBeforeTheyCan(emptyRowIndex, emptyColumnIndex);
		}
	}
	
	
	private static void checkDownRightDiagonal()
	{
		playerTileCount = 0;
		for (int row = 0, column = 0; row < Game.thisGame().dimension && column < Game.thisGame().dimension; ++row, ++column)
		{
			if (hasTheComputerAlreadyBlockedThisRowColumnOrDiagonal(row, column))
			{
				playerTileCount = -1;
				return;
			}
			if (GameBoard.isThisSpotClaimedByPlayer(row, column))
			{
				++playerTileCount;
			}
			emptyRowIndex = row;
			emptyColumnIndex = column;
		}
	}
	
	
	private static void checkUpRightDiagonal()
	{
		playerTileCount = 0;
		for (int row = Game.thisGame().dimension - 1, column = 0; row >= 0 && column < Game.thisGame().dimension; --row, ++column)
		{
			if (hasTheComputerAlreadyBlockedThisRowColumnOrDiagonal(row, column))
			{
				playerTileCount = -1;
				return;
			}
			if (GameBoard.isThisSpotClaimedByPlayer(row, column))
			{
				++playerTileCount;
			}
			emptyRowIndex = row;
			emptyColumnIndex = column;
		}
	}
	
} // End of class.
