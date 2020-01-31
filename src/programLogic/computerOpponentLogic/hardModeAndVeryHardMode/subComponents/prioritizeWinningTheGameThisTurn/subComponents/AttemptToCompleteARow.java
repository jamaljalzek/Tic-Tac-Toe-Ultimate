package programLogic.computerOpponentLogic.hardModeAndVeryHardMode.subComponents.prioritizeWinningTheGameThisTurn.subComponents;

import programLogic.GameBoard;

public class AttemptToCompleteARow
{
	private static int currentRow;
	private static int computerTileCount;

	
	public static void attemptToCompleteARow()
	{
		for (currentRow = 0; currentRow < GameBoard.getDimension(); ++currentRow)
		{
			countTheNumberOfComputerTilesInThisRow();
			if (canThisRowBeCompletedThisTurn())
			{
				completeThisRow();
				return;
			}
		}
	}
	
	
	private static void countTheNumberOfComputerTilesInThisRow()
	{
		computerTileCount = 0;
		for (int currentColumn = 0; currentColumn < GameBoard.getDimension(); ++currentColumn)
		{
			if (GameBoard.isThisSpotClaimedByComputer(currentRow, currentColumn))
			{
				++computerTileCount;
			}
		}
	}
	
	
	private static boolean canThisRowBeCompletedThisTurn()
	{
		int numberOfSpotsInEveryRow = GameBoard.getDimension();
		return computerTileCount == (numberOfSpotsInEveryRow - 1);
	}
	
	
	private static void completeThisRow()
	{
		for (int currentColumn = 0; currentColumn < GameBoard.getDimension(); ++currentColumn)
		{
			if (!GameBoard.isChosenSpotAlreadyTaken(currentRow, currentColumn))
			{
				GameBoard.letComputerClaimSpotOnGameBoard(currentRow, currentColumn);
				return;
			}
		}
	}

} // End of class.
