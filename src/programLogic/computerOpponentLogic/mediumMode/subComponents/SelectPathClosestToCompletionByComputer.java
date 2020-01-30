package programLogic.computerOpponentLogic.mediumMode.subComponents;

public class SelectPathClosestToCompletionByComputer
{
	private static int bestRowCount, bestColumnCount, bestDiagonalCount;
	
	
	public static void selectPathClosestToCompletionByComputer()
	{
		if (areAllOptionsAreEqual())
		{
			PickSpotRandomly.onGameBoard();
			return;
		}
		if (isItARowClosestToCompletionByComputer())
		{
			int chosenRow = SearchForRowClosestToCompletionByComputer.bestRowLocation();
			PickSpotRandomly.inChosenRow(chosenRow);
			return;
		}
		if (isItAColumnClosestToCompletionByComputer())
		{
			int chosenColumn = SearchForColumnClosestToCompletionByComputer.bestColumnLocation();
			PickSpotRandomly.inChosenColumn(chosenColumn);
			return;
		}
		if (isItADiagonalClosestToCompletionByComputer())
		{
			int chosenDiagonalRowLocation = SearchForDiagonalClosestToCompletionByComputer.bestDiagonalLocation();
			PickSpotRandomly.inChosenDiagonal(chosenDiagonalRowLocation);
			return;
		}
	}
	
	
	private static boolean areAllOptionsAreEqual()
	{
		return (canNoRowColumnAndDiagonalCanBeWonByTheComputer() || doesTheBestRowColumnAndDiagonalHaveTheSameAmountOfCompletion());
	}
	
	
	private static boolean canNoRowColumnAndDiagonalCanBeWonByTheComputer()
	{
		boolean canAnyRowBeWonByTheComputer = SearchForRowClosestToCompletionByComputer.canAnyRowBeWonByTheComputer();
		boolean canAnyColumnBeWonByTheComputer = SearchForColumnClosestToCompletionByComputer.canAnyColumnBeWonByTheComputer();
		boolean canEitherDiagonalBeWonByTheComputer = SearchForDiagonalClosestToCompletionByComputer.canEitherDiagonalBeWonByTheComputer();
		return (!canAnyRowBeWonByTheComputer && !canAnyColumnBeWonByTheComputer && !canEitherDiagonalBeWonByTheComputer);
	}
	
	
	private static boolean doesTheBestRowColumnAndDiagonalHaveTheSameAmountOfCompletion()
	{
		bestRowCount = SearchForRowClosestToCompletionByComputer.bestRowCount();
		bestColumnCount = SearchForColumnClosestToCompletionByComputer.bestColumnCount();
		bestDiagonalCount = SearchForDiagonalClosestToCompletionByComputer.bestDiagonalCount();
		return (bestRowCount == bestColumnCount && bestColumnCount == bestDiagonalCount);
	}
	
	
	private static boolean isItARowClosestToCompletionByComputer()
	{
		// R > C >= D or R > D >= C
		return (bestRowCount >= bestColumnCount && bestColumnCount >= bestDiagonalCount ||
				bestRowCount >= bestDiagonalCount && bestDiagonalCount >= bestColumnCount);
	}
	
	
	private static boolean isItAColumnClosestToCompletionByComputer()
	{
		// C > R >= D or C > D >= R
		return (bestColumnCount >= bestRowCount && bestRowCount >= bestDiagonalCount ||
				bestColumnCount >= bestDiagonalCount && bestDiagonalCount >= bestRowCount);
	}
	
	
	private static boolean isItADiagonalClosestToCompletionByComputer()
	{
		// D > R >= C or D > C >= R
		return (bestDiagonalCount >= bestRowCount && bestRowCount >= bestColumnCount ||
				bestDiagonalCount >= bestColumnCount && bestColumnCount >= bestRowCount);
	}
	
} // End of class.
