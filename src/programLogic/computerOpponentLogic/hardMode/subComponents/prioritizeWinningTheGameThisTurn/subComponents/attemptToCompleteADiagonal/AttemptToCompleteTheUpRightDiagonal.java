package programLogic.computerOpponentLogic.hardMode.subComponents.prioritizeWinningTheGameThisTurn.subComponents.attemptToCompleteADiagonal;

import programLogic.GameBoard;

public class AttemptToCompleteTheUpRightDiagonal
{
	private static int computerTileCount;

	
	public static void attemptToCompleteTheUpRightDiagonal()
	{
		countTheNumberOfComputerTilesInTheUpRightDiagonal();
		if (AttemptToCompleteADiagonal.canThisDiagonalBeCompletedThisTurn(computerTileCount))
		{
			completeTheUpRightDiagonal();
			return;
		}
	}

	
	private static void countTheNumberOfComputerTilesInTheUpRightDiagonal()
	{
		computerTileCount = 0;
		int bottomRow = GameBoard.getDimension() - 1;
		for (int currentRow = bottomRow, currentColumn = 0;
				 currentRow >= 0 && currentColumn < GameBoard.getDimension();
				 --currentRow, ++currentColumn)
		{
			if (GameBoard.isThisSpotClaimedByComputer(currentRow, currentColumn))
			{
				++computerTileCount;
			}
		}
	}
	
	
	private static void completeTheUpRightDiagonal()
	{
		int bottomRow = GameBoard.getDimension() - 1;
		for (int currentRow = bottomRow, currentColumn = 0;
				 currentRow >= 0 && currentColumn < GameBoard.getDimension();
				 --currentRow, ++currentColumn)
		{
			if (!GameBoard.isChosenSpotAlreadyTaken(currentRow, currentColumn))
			{
				GameBoard.letComputerClaimSpotOnGameBoard(currentRow, currentColumn);
				return;
			}
		}
	}

} // End of class.
