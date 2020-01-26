package programLogic.prioritizeWinningTheGameThisTurn.subComponents.attemptToCompleteADiagonal;

import programLogic.GameBoard;

public class AttemptToCompleteTheDownRightDiagonal
{
	private static int computerTileCount;

	
	public static void attemptToCompleteTheDownRightDiagonal()
	{
		countTheNumberOfComputerTilesInTheDownRightDiagonal();
		if (AttemptToCompleteADiagonal.canThisDiagonalBeCompletedThisTurn(computerTileCount))
		{
			completeTheDownRightDiagonal();
			return;
		}
	}
	
	private static void countTheNumberOfComputerTilesInTheDownRightDiagonal()
	{
		computerTileCount = 0;
		for (int currentRow = 0, currentColumn = 0;
				 currentRow < GameBoard.getDimension() && currentColumn < GameBoard.getDimension();
				 ++currentRow, ++currentColumn)
		{
			if (GameBoard.isThisSpotClaimedByComputer(currentRow, currentColumn))
			{
				++computerTileCount;
			}
		}
	}
	
	
	private static void completeTheDownRightDiagonal()
	{
		for (int currentRow = 0, currentColumn = 0;
				 currentRow < GameBoard.getDimension() && currentColumn < GameBoard.getDimension();
				 ++currentRow, ++currentColumn)
		{
			if (!GameBoard.isChosenSpotAlreadyTaken(currentRow, currentColumn))
			{
				GameBoard.letComputerClaimSpotOnGameBoard(currentRow, currentColumn);
				return;
			}
		}
	}

} // End of class.
