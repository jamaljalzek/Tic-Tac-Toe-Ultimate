package programLogic.computerOpponentLogic.hardMode.subComponents;

import programLogic.Game;
import programLogic.GameBoard;

public class PreventPlayerFromWinning
{
	private static int playerTileCount;
	private static int emptyColumnIndex, emptyRowIndex;
	
	
	public static void blockPlayerIfTheyAreAboutToWin()
	{
		checkRows();
		if (Game.isStillComputersTurn())
		{
			checkColumns();
		}
		if (Game.isStillComputersTurn())
		{
			checkDiagonals();
		}
	}
	
	
	private static void checkRows()
	{
		for (int currentRow = 0; currentRow < GameBoard.getDimension(); ++currentRow)
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
		for (int currentColumn = 0; currentColumn < GameBoard.getDimension(); ++currentColumn)
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
		int numberOfSpotsInEveryRowColumnAndDiagonal = GameBoard.getDimension();
		return playerTileCount == (numberOfSpotsInEveryRowColumnAndDiagonal - 1);
	}
	
	
	private static void blockPlayerByClaimingSpotBeforeTheyCan(int row, int column)
	{
		GameBoard.letComputerClaimSpotOnGameBoard(row, column);
	}
	
	
	private static void checkColumns()
	{
		for (int currentColumn = 0; currentColumn < GameBoard.getDimension(); ++currentColumn)
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
		for (int currentRow = 0; currentRow < GameBoard.getDimension(); ++currentRow)
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
		for (int row = 0, column = 0; row < GameBoard.getDimension() && column < GameBoard.getDimension(); ++row, ++column)
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
		for (int row = GameBoard.getDimension() - 1, column = 0; row >= 0 && column < GameBoard.getDimension(); --row, ++column)
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
