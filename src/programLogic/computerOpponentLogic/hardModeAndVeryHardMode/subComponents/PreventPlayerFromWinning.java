package programLogic.computerOpponentLogic.hardModeAndVeryHardMode.subComponents;

import programLogic.Game;
import programLogic.GameBoard;
import programLogic.computerOpponentLogic.mediumMode.subComponents.PickSpotRandomly;

public class PreventPlayerFromWinning
{
	private static int xTurnsAwayFromWinning;
	private static int playerTileCount;
	
	
	public static void blockPlayerIfTheyXTurnsAwayFromWinning(int xTurnsAwayFromWinning)
	{
		PreventPlayerFromWinning.xTurnsAwayFromWinning = xTurnsAwayFromWinning;
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
			if (isPlayerXSpotsAwayFromCompletion())
			{
				PickSpotRandomly.inChosenRow(currentRow);
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
		}
	}
	
	
	private static boolean hasTheComputerAlreadyBlockedThisRowColumnOrDiagonal(int row, int column)
	{
		return GameBoard.isThisSpotClaimedByComputer(row, column);
	}
	
	
	private static boolean isPlayerXSpotsAwayFromCompletion()
	{
		int numberOfSpotsInEveryRowColumnAndDiagonal = GameBoard.getDimension();
		return playerTileCount == (numberOfSpotsInEveryRowColumnAndDiagonal - xTurnsAwayFromWinning);
	}
	
	
	private static void checkColumns()
	{
		for (int currentColumn = 0; currentColumn < GameBoard.getDimension(); ++currentColumn)
		{
			searchRowsInCurrentColumn(currentColumn);
			if (isPlayerXSpotsAwayFromCompletion())
			{
				PickSpotRandomly.inChosenColumn(currentColumn);
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
		}
	}
	
	
	private static void checkDiagonals()
	{
		checkDownRightDiagonal();
		if (isPlayerXSpotsAwayFromCompletion())
		{
			PickSpotRandomly.inChosenDiagonal(0);
			return;
		}
		checkUpRightDiagonal();
		if (isPlayerXSpotsAwayFromCompletion())
		{
			int bottomRowIndex = GameBoard.getDimension() - 1;
			PickSpotRandomly.inChosenDiagonal(bottomRowIndex);
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
		}
	}
	
} // End of class.

