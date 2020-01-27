package programLogic.computerOpponentLogic.mediumMode.subComponents;

public class SelectPathClosestToCompletionByComputer
{
	private static int bestRowCount, bestColumnCount, bestDiagonalCount;
	
	
	public static void selectPathClosestToCompletionByComputer()
	{
		determineIfAllOptionsAreEqual();
		determineIfWeWillSelectARow();
		determineIfWeWillSelectAColumn();
		determineIfWeWillSelectADiagonal();
	}
	
	
	private static void determineIfAllOptionsAreEqual()
	{
		if (canNoRowColumnOrDiagonalCanBeWonByTheComputer() || doesTheBestRowColumnAndDiagonalHaveTheSameAmountOfCompletion())
		{
			PickSpotRandomly.onGameBoard();
		}
	}
	
	
	private static boolean canNoRowColumnOrDiagonalCanBeWonByTheComputer()
	{
		boolean canAnyRowBeWonByTheComputer = SearchForRowClosestToCompletionByComputer.canAnyRowBeWonByTheComputer();
		boolean canAnyColumnBeWonByTheComputer = SearchForColumnClosestToCompletionByComputer.canAnyColumnBeWonByTheComputer();
		boolean canEitherDiagonalBeWonByTheComputer = SearchForDiagonalClosestToCompletionByComputer.canEitherDiagonalBeWonByTheComputer();
		return (!canAnyRowBeWonByTheComputer || !canAnyColumnBeWonByTheComputer || !canEitherDiagonalBeWonByTheComputer);
	}
	
	
	private static boolean doesTheBestRowColumnAndDiagonalHaveTheSameAmountOfCompletion()
	{
		bestRowCount = SearchForRowClosestToCompletionByComputer.bestRowCount();
		bestColumnCount = SearchForColumnClosestToCompletionByComputer.bestColumnCount();
		bestDiagonalCount = SearchForDiagonalClosestToCompletionByComputer.bestDiagonalCount();
		return (bestRowCount == bestColumnCount && bestColumnCount == bestDiagonalCount);
	}
	
	

	
	
	private static void determineIfWeWillSelectARow()
	{
		// R > C >= D or R > D >= C
		if (bestRowCount >= bestColumnCount && bestColumnCount >= bestDiagonalCount ||
			bestRowCount >= bestDiagonalCount && bestDiagonalCount >= bestColumnCount)
		{
			int chosenRow = SearchForRowClosestToCompletionByComputer.bestRowLocation();
			PickSpotRandomly.inChosenRow(chosenRow);
		}
	}
	
	
	private static void determineIfWeWillSelectAColumn()
	{
		// C > R >= D or C > D >= R
		if (bestColumnCount >= bestRowCount && bestRowCount >= bestDiagonalCount ||
			bestColumnCount >= bestDiagonalCount && bestDiagonalCount >= bestRowCount)
		{
			int chosenColumn = SearchForColumnClosestToCompletionByComputer.bestColumnLocation();
			PickSpotRandomly.inChosenColumn(chosenColumn);
		}
	}
	
	
	private static void determineIfWeWillSelectADiagonal()
	{
		// D > R >= C or D > C >= R
		if (bestDiagonalCount >= bestRowCount && bestRowCount >= bestColumnCount ||
			bestDiagonalCount >= bestColumnCount && bestColumnCount >= bestRowCount)
		{
			int chosenDiagonalRowLocation = SearchForDiagonalClosestToCompletionByComputer.bestDiagonalLocation();
			PickSpotRandomly.inChosenDiagonal(chosenDiagonalRowLocation);
		}
	}
	
} // End of class.
