package programLogic.computerOpponentLogic.hardModeAndVeryHardMode.subComponents.prioritizeWinningTheGameThisTurn.subComponents;

import programLogic.GameBoard;

public class AttemptToCompleteAColumn
{
	private static int currentColumn;
	private static int computerTileCount;

	
	public static void attemptToCompleteAColumn()
	{
		for (currentColumn = 0; currentColumn < GameBoard.getDimension(); ++currentColumn)
		{
			countTheNumberOfComputerTilesInThisColumn();
			if (canThisColumnBeCompletedThisTurn())
			{
				completeThisColumn();
				return;
			}
		}
	}
	
	
	private static void countTheNumberOfComputerTilesInThisColumn()
	{
		computerTileCount = 0;
		for (int currentRow = 0; currentRow < GameBoard.getDimension(); ++currentRow)
		{
			if (GameBoard.isThisSpotClaimedByComputer(currentRow, currentColumn))
			{
				++computerTileCount;
			}
		}
	}
	
	
	private static boolean canThisColumnBeCompletedThisTurn()
	{
		int numberOfSpotsInEveryColumn = GameBoard.getDimension();
		return computerTileCount == (numberOfSpotsInEveryColumn - 1);
	}
	
	
	private static void completeThisColumn()
	{
		for (int currentRow = 0; currentRow < GameBoard.getDimension(); ++currentRow)
		{
			if (!GameBoard.isChosenSpotAlreadyTaken(currentRow, currentColumn))
			{
				GameBoard.letComputerClaimSpotOnGameBoard(currentRow, currentColumn);
				return;
			}
		}
	}

} // End of class.
